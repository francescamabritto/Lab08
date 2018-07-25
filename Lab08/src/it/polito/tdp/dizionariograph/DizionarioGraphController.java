/**
 * Sample Skeleton for 'DizionarioGraph.fxml' Controller Class
 */

package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	Model model = new Model();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtLettere"
    private TextField txtLettere; // Value injected by FXMLLoader

    @FXML // fx:id="txtParola"
    private TextField txtParola; // Value injected by FXMLLoader
    
    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnGrafo"
    private Button btnGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnVicini"
    private Button btnVicini; // Value injected by FXMLLoader

    @FXML // fx:id="btnGrado"
    private Button btnGrado; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    		// Graph<String, DefaultEdge> grafo = new SimpleGraph<>(DefaultEdge.class);
		model.createGraph(this.txtLettere.getAnchor());
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    		List<String> listaVicini = new ArrayList<>(model.displayNeighbours(this.txtParola.getText()));
    		this.txtResult.appendText(listaVicini.toString());
    }
    
    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    		int maxDegree = model.findMaxDegree();
    		this.txtResult.appendText(Integer.toString(maxDegree));
    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtLettere.clear();
    	this.txtParola.clear();
    	this.txtResult.clear();
    }
    
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtLettere != null : "fx:id=\"txtLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGrafo != null : "fx:id=\"btnGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnVicini != null : "fx:id=\"btnVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGrado != null : "fx:id=\"btnGrado\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
    
    
}
