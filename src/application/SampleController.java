package application;

import java.net.URL;
import java.time.Month;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class SampleController {
	
	private Model m = new Model();
	
	public void setModel(Model m){
		this.m=m;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Month> comboMese;

    @FXML
    private Button btnUmidita;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtResult;

    @FXML
    void doCalcola(ActionEvent event) {
    	Month mese = comboMese.getValue();
    	if(mese == null){
    		txtResult.appendText("Seleziona un mese!\n");
    		return ;
    	}

    }

    @FXML
    void doUmidita(ActionEvent event) {
    	txtResult.clear();
    	Month mese = comboMese.getValue();
    	if(mese == null){
    		txtResult.appendText("Seleziona un mese!\n");
    		return ;
    	}
    	
    	List<String> elenco = m.getElenco(mese);
    	for(String e : elenco){
    		txtResult.appendText(e + " \n");
    	}

    }

    @FXML
    void initialize() {
        assert comboMese != null : "fx:id=\"comboMese\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Sample.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Sample.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Sample.fxml'.";

        comboMese.getItems().addAll(m.getMesi());
    }
}
