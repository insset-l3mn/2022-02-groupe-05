import React, {useCallback, useEffect, useState} from 'react';
import dagre, {layout} from "dagre";
import ReactFlow,
{
    isNode,
    ReactFlowProvider,
    useEdgesState,
    useNodesState
}
    from "react-flow-renderer";
import axios from "axios";

export default function ChooseSkill(){

    const [load, setLoad] = useState(false)
    const [nodes, setNodes, onNodesChange] = useNodesState([]);
    const [edges, setEdges, onEdgesChange] = useEdgesState([]);

    const dagreGraph = new dagre.graphlib.Graph();
    dagreGraph.setDefaultEdgeLabel(() => ({}));

    const nodeWidth = 172;
    const nodeHeight = 36;

    const changeLoad = (l) => {
        setLoad(l)
    }

    const getLayoutedElements = (nodes, edges, direction = "TB") => {
        const isHorizontal = direction === "LR";
        dagreGraph.setGraph({ rankdir: direction });

        nodes.forEach((node) => {
            dagreGraph.setNode(node.id, { width: nodeWidth, height: nodeHeight });
        });

        edges.forEach((edge) => {
            dagreGraph.setEdge(edge.source, edge.target);
        });

        dagre.layout(dagreGraph);

        nodes.forEach((node) => {
            const nodeWithPosition = dagreGraph.node(node.id);
            node.targetPosition = isHorizontal ? "left" : "top";
            node.sourcePosition = isHorizontal ? "right" : "bottom";

            node.position = {
                x: nodeWithPosition.x - nodeWidth / 2,
                y: nodeWithPosition.y - nodeHeight / 2
            };

            return node;
        });
        return { nodes, edges };
    };

    useEffect(() => {
        axios.get("http://localhost:8080/gestion-formation-BE/api/graph/global")
            .then((response) => {
                response["data"].map((item) => {
                    if (isNode(item)) {
                        let node = {}
                        node.id = item["id"].toString();
                        node.position = {x: 0, y: 0}
                        node.data = item["data"][0]
                        // node.dragging = false
                        if (node.data.context === "Skill") {
                            node.style = {...node.style, backgroundColor: "#0041D0", color: "#fff"}
                        } else if (node.data.context === "Subdomain") {
                            node.style = {...node.style, backgroundColor: "#FF0072", color: "#fff"}
                        } else {
                            node.style = {...node.style, backgroundColor: "#F8F9F9"}
                        }
                        setNodes(prevState => [...prevState, node])

                    } else {
                        let edge = {}
                        edge.id = item["id"]
                        edge.target = JSON.stringify(item["target"])
                        edge.source = JSON.stringify(item["source"])
                        setEdges(prevState => [...prevState, edge])

                    }
                })
            })
        onLayout('LR')

    },[])

    const t = () => {
        changeLoad(true)

        console.log(nodes.length)
        console.log(nodes)
        console.log(edges.length)
        console.log(edges)
    }

    const onLayout = useCallback(
        (direction) => {
            setLoad(false)
            const { nodes: layoutedNodes, edges: layoutedEdges } = getLayoutedElements(
                nodes,
                edges,
                direction
            );

            setNodes([...layoutedNodes]);
            setEdges([...layoutedEdges]);
            console.log("slt")
            setLoad(true)

        },
        [nodes, edges]
    );

    const changeNodeColor = (node, color) => {
        console.log(node, color)
        /*setGraph((nds) =>
            [...nds,
            nds.map((item) => {
                if (item.id === node.id) {
                    item.style = { ...item.style, backgroundColor: color };
                }
                return item;
            })]
        );*/
        node.style = { ...node.style, backgroundColor: color };

    }

    const onClickElement = (event, node) => {
        if(node.data.selected){
            node.data.selected = false
        }else{
            node.data.selected = true
        }
        switch (node["data"].context){
            case 'Skill':
                console.log("Requete skill")
                changeNodeColor(node, "#000")
                break
            case 'Subdomain':
                console.log("Requete subdomain")
                break
            case 'Domain':
                console.log("Requete domain")
                break
            default:
                console.log("Defaut")
        }
    }

    return (
        <div className="layoutflow" style={{ height: 500 }}>
            { load &&
                <ReactFlow
                    nodes={nodes}
                    edges={edges}
                    //elements={getLayoutedElements(graph, 'LR')}
                    //onElementClick={onClickElement}
                    onNodesChange={onNodesChange}
                    onEdgesChange={onEdgesChange}
                    connectionLineType="smoothstep"
                    fitView
                />
            }

            <button onClick={t}>click</button>
            <button onClick={() => onLayout('LR')}>forme</button>
        </div>
    );

}