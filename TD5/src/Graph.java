import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Graph {

	private List<Node> nodes = new ArrayList<Node>(); // Noeuds
	private List<Edge> edges = new ArrayList<Edge>();; // Les arcs
	static final double inf = 99999;
	public Graph() {
		
	}

	public void readFromFile(String filePath,String separtor){
		try {

			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String docLine = reader.readLine();
			String[] line = docLine.split(separtor);

			for(int id = 1; id < line.length + 1; id++){
				Node node = new Node(id, line[id - 1]);
				nodes.add(node);
			}

			while(docLine != null){
				double weight;
				int origin = 0;

				for(int i = 0; i < line.length; i++) {

					if(line[i].isEmpty()){ break; }

					if(line[i].equals("inf")){ weight = inf; }

					else{ weight = Double.parseDouble(line[i]); }

					Edge edge = new Edge(nodes.get(origin),nodes.get(i),weight);
					edges.add(edge);
				}
				origin++;
			}
			reader.close();
		}
		catch (IOException e){
			System.err.println("Cannot read file");

		}



	}
	
	public List<Edge> getOutEdges(Node source) {
		List<Edge> outEdges = new LinkedList<Edge>();

		for(Edge edge : edges){
			if(edge.getSource() == source)
				outEdges.add(edge);
		}
		
		return outEdges;	
	}
	
	public List<Edge> getInEdges(Node dest) {
		List<Edge> inEdges = new LinkedList<Edge>() ;

		for(Edge edge : edges){
			if(edge.getSource() == dest)
				inEdges.add(edge);
		}

		return inEdges;		
	}
	// Accesseurs 
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public Node getNodeByName(String name){
		for (Node node : nodes) {
			if(node.getName().equals(name)){
				return node;
			}
		}
		return null;
	}
	
	public Node getNodeById(int id){
		for (Node node : nodes) {
			if(node.getId()==id){
				return node;
			}

		}
		return null;
	}
}
