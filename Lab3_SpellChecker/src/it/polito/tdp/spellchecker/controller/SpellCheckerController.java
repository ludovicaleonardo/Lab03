package it.polito.tdp.spellchecker.controller;
import it.polito.tdp.spellchecker.model.Dictionary;

	import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
	import javafx.event.ActionEvent;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.ChoiceBox;
	import javafx.scene.control.Label;
	import javafx.scene.control.TextArea;

	public class SpellCheckerController {
		
		Dictionary model;
		
		List<String> parole = new LinkedList<String>();

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private ChoiceBox<String> menuScelta;

	    @FXML
	    private Button btnSpell;

	    @FXML
	    private Label labErrori;

	    @FXML
	    private Button btnClear;

	    @FXML
	    private Label labTempi;
	    
	    @FXML
	    private TextArea txtResult;

	    @FXML
	    private TextArea txtErrori;


	    @FXML
	    void doClearText(ActionEvent event) {
	    	txtResult.clear();
	    	txtErrori.clear();
	    	model.rwSbagliate.clear();
	    	model.cont=0;
	    	model.rw.clear();
	    	parole.clear();
	    	labTempi.setText(" ");
	    	labErrori.setText(" ");
	 
	    }

	    @FXML
	    void doSpellCheck(ActionEvent event) {
	       	long start = System.nanoTime();
	    		if(menuScelta.getValue().compareTo("Italian")==0) {
	    			model.dizionarioCorrente=model.dizionarioItaliano;
	    		}
	    		else model.dizionarioCorrente=model.dizionarioInglese;
	    		
	    		String parola = txtResult.getText();
	    		parola = parola.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]?]", "");
	    		parola = parola.toLowerCase();
	    		String parolaDaControllare[] = parola.split(" ");
	    		for(int i=0; i<parolaDaControllare.length;i++) {
	    			parole.add(parolaDaControllare[i]);
	    		}
	    		//model.spellCheckTextDicotomic(parole);
	    		model.spellCheckTextLinear(parole);
	    		txtErrori.setText(model.stampaStringa(model.rwSbagliate));
	    		labErrori.setText("The text contains "+model.cont+" errors");
	    		long stop = System.nanoTime();
	    	    labTempi.setText("Spell check completed in "+(((Integer.parseInt(String.valueOf(stop-start)))/3600)+" nanoseconds\n"));
	    }
	    
	    

	    @FXML
	    void initialize() {
	        assert menuScelta != null : "fx:id=\"menuScelta\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert labErrori != null : "fx:id=\"labErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert labTempi != null : "fx:id=\"labTempi\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
	    
	    }
	
	    public void setModel(Dictionary model) {
			this.model = model;
			this.menuScelta.getItems().add("English");
			this.menuScelta.getItems().add("Italian");
			//Alternativa alle due righe su 
			//this.menuScelta.setItems(FXCollections.observableArrayList("English","Italian"));
			this.model.loadAll();
			
	    	}
	}
