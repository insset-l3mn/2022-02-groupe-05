import {isNode} from "react-flow-renderer";
import axios from "axios";

export const initialNodes = [];
export const initialEdges = [];

axios.get("http://localhost:8080/gestion-formation-BE/api/graph/global")
	.then((response) => {
		response["data"].map((item) => {
			if (isNode(item)) {
				let node = {}
				node.id = item["id"]
				node.position = {x: 0, y: 0}
				node.data = item["data"][0].toString()
				node.dragging = false
				if(node.data.context === "Skill"){
					node.style = {...node.style, backgroundColor: "#0041D0", color:"#fff"}
				}else if(node.data.context === "Subdomain"){
					node.style = {...node.style, backgroundColor: "#FF0072", color:"#fff"}
				}else{
					node.style = {...node.style, backgroundColor: "#F8F9F9"}
				}
				initialNodes.push(node)

			} else {
				let edge = {}
				edge.id = item["id"]
				edge.target = JSON.stringify(item["target"])
				edge.source = JSON.stringify(item["source"])
				initialEdges.push(edge)

			}
		})
	})
