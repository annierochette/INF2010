import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;


public class Bellman {
	private Graph graph;
	private Node sourceNode;
	private List<Vector<Double>> piTable =  new ArrayList<Vector<Double>>();
	private List<Vector<Integer>> rTable =  new ArrayList<Vector<Integer>>();
	
	public Bellman (Graph g) {
		this.graph = g;
	}
	
	public void setSourceNode(Node source) {
		this.sourceNode = source;
	}
	
	public void shortestPath() {
		// Compléter
	}
	
	public void  diplayShortestPaths() {
		String affichage = "=> Les chemins sont: \n";
		Stack<Node> path = new Stack<Node>();
		Node origin;

		for(int i = 1; i <= graph.getNodes().size(); i++){
			origin = graph.getNodeById(i);
			path.push(origin);

			int nextID= rTable.get(rTable.size() - 1).get(path.peek().getId()-1);
			if(nextID==0)
				nextID++;
			path.push(graph.getNodeById(nextID));

			for(; path.peek() != sourceNode && path.peek() != origin; ){

				nextID= rTable.get(rTable.size() - 1).get(path.peek().getId()-1);
				if(nextID==0)
					nextID++;
				path.push(graph.getNodeById(nextID));
			}

			if(origin == sourceNode && path.size() == 2){
				path.clear();
			}
			else if(path.peek() == origin){
				System.out.print(printPath(i,path,true,origin));

				return;
			}
			else{
				affichage += printPath(i, path, false, origin);
			}

		}
		System.out.print(affichage);
	}

	public String printPath(int index, Stack<Node> path, boolean negative, Node origin){


		if(negative == false) {
			String print = "[" + sourceNode.getName() + " - " + graph.getNodes().get(index - 1).getName() + "]" + piTable.get(piTable.size() - 1).get(index - 1)
					+ " : " + path.pop().getName();

			while (!path.isEmpty()) {
				print += " -> " + path.pop().getName();
			}
			print += "\n";

			return print;
		}
		else{
			String print = "=> Le graphe contient un circuit de coût négatif: \n[" + origin.getName() + " - " + origin.getName() + "]";

			while(!path.isEmpty()) {
				if (path.size() == 1)
					print += path.pop().getName();
				else
					print += path.pop().getName() + " -> ";
			}
			return print;
		}
	}


	public void displayTables() {
	 //Compléter
	}
}
