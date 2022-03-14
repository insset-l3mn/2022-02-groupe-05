import React, {useCallback, useEffect, useState} from 'react';
import ReactFlow, {
    ReactFlowProvider,
    addEdge,
    removeElements,
    isNode,
} from 'react-flow-renderer';
import axios from "axios";
import dagre from "dagre";

const elements = [
    {
        id: '1',
        type: 'input', // input node
        data: { label: 'Input Node' },
        position: { x: 250, y: 25 },
    },
    // default node
    {
        id: '2',
        // you can also pass a React component as a label
        data: { label: <div>Default Node</div> },
        position: { x: 100, y: 125 },
    },
    {
        id: '3',
        type: 'output', // output node
        data: { label: 'Output Node' },
        position: { x: 250, y: 250 },
    },
    // animated edge
    { id: 'e1-2', source: '1', target: '2', animated: true },
    { id: 'e2-3', source: '2', target: '3' },
];




export default function ChooseSkill(){

    const [graph, setGraph] = useState([])
    const [load, setLoad] = useState(false)

    const dagreGraph = new dagre.graphlib.Graph();
    dagreGraph.setDefaultEdgeLabel(() => ({}));

    const nodeWidth = 172;
    const nodeHeight = 36;

    const getLayoutedElements = (elements, direction = 'TB') => {
        const isHorizontal = direction === 'LR';
        dagreGraph.setGraph({ rankdir: direction });

        elements.forEach((el) => {
            if (isNode(el)) {
                dagreGraph.setNode(el.id, { width: nodeWidth, height: nodeHeight });
            } else {
                dagreGraph.setEdge(el.source, el.target);
            }
        });

        dagre.layout(dagreGraph);

        return elements.map((el) => {
            if (isNode(el)) {
                const nodeWithPosition = dagreGraph.node(el.id);
                el.targetPosition = isHorizontal ? 'left' : 'top';
                el.sourcePosition = isHorizontal ? 'right' : 'bottom';

                el.position = {
                    x: nodeWithPosition.x - nodeWidth / 2 + Math.random() / 1000,
                    y: nodeWithPosition.y - nodeHeight / 2,
                };
            }

            return el;
        });
    };

    useEffect(async () => {
        setGraph([])
        setLoad(false)
        await axios.get("http://localhost:8080/gestion-formation-BE/api/graph/global")
            .then((response) => {
                console.log(response["data"])
                response["data"].map((item) => {
                    if (!item["target"]) {
                        let node = {}
                        node.id = item["id"]
                        node.position = {x: 0, y: 0}
                        node.data = item["data"][0]
                        node.dragging = false
                        setGraph(prevState => [...prevState, node])

                    } else {
                        let edge = {}
                        edge.id = item["id"]
                        edge.target = JSON.stringify(item["target"])
                        edge.source = JSON.stringify(item["source"])
                        setGraph(prevState => [...prevState, edge])

                    }
                })
            })
        setLoad(true)
    }, [])


    const t = () => {
        console.log(graph.length)
        console.log(graph)
    }

    const onClickElement = (event, node) => {
        console.log(node)
    }

    return (
        <div className="layoutflow" style={{ height: 1000 }}>

            { load &&
                <ReactFlowProvider>
                    <ReactFlow
                        elements={getLayoutedElements(graph, 'LR')}
                        nodesDraggable={false}
                        onElementClick={onClickElement}
                        connectionLineType="smoothstep"
                    />
                </ReactFlowProvider>
            }



            <button onClick={t}>click</button>
        </div>
    );

}