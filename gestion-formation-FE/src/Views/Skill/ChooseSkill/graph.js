import {useEdgesState, useNodesState} from "react-flow-renderer";

const [nodes, setNodes, onNodesChange] = useNodesState([]);
const [edges, setEdges, onEdgesChange] = useEdgesState([]);