package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	WordDAO dao = new WordDAO();
	private List<String> parole;
	Graph<String, DefaultEdge> graph;
	private String parolaMaxDegree;
	
	
	
	
	public String getParolaMaxDegree() {
		return this.parolaMaxDegree;
	}
	

	public void setParolaMaxDegree(String parolaMaxDegree) {
		this.parolaMaxDegree = parolaMaxDegree;
	}


	public void createGraph(int numeroLettere) {
		
		int count = 0;
		parole = new ArrayList<>(dao.getAllWordsFixedLength(numeroLettere));
		graph = new SimpleGraph<>(DefaultEdge.class);
		
		for(String p: parole) {
			graph.addVertex(p); 
			
		}
		
		for(String p1: parole) {
			
			for(String p2: parole) {
				if(p1.compareTo(p2)<0) { //controlla che p1 e p2 siano diversi e che non si ripetano
					// fare un for che controlla che ci siano esattamente (numeroLettere-1) lettere uguali
					count = 0;
					
					for(int i=0; i<numeroLettere && count<=2; i++) {
						if(p1.charAt(i) != p2.charAt(i))
							count++;
					}
					if(count == 1)
						graph.addEdge(p1, p2);
				}
			}
		}
		System.out.println("grafo: "+graph);
	}
	
	public List<String> displayNeighbours(String parolaInserita) {
//		Set<DefaultEdge> edges = this.graph.incomingEdgesOf(parolaInserita);
//		List<String> vicini = new ArrayList<>();
//		for(DefaultEdge e: edges) 
//			vicini.add(this.graph.getEdgeTarget(e));
		List<String> vicini = Graphs.neighborListOf(graph, parolaInserita); 
		return vicini;
	}

	public int findMaxDegree() {
		int maxDegree=-1;
		int degreeParola = 0;
		
		for(String s: parole) {
			degreeParola = graph.degreeOf(s);
			if(degreeParola>maxDegree) {
				maxDegree = degreeParola;
				this.parolaMaxDegree = new String(s);
			}	
		}
		return maxDegree;
	}
}
