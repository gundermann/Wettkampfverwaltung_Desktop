package com.comphel.shobuippon.gui;

import business.KumiteCompetitor;

import com.comphel.common.definition.Graduierung;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ShobuIpponCompetitorSetupController {


	@FXML
	private TextField akaVorname;
	
	@FXML
	private TextField akaNachname;
	
	@FXML
	private TextField shiroVorname;
	
	@FXML
	private TextField shiroNachname;
	
	@FXML
	private CheckBox isFinale;
	
	@FXML
	private Button start;

	private Stage stage;

	@FXML
	private void startMatch(){
		KumiteCompetitor aka = new KumiteCompetitor(akaVorname.getText(), akaNachname.getText(), 10, Graduierung.DAN1);
		KumiteCompetitor shiro = new KumiteCompetitor(shiroVorname.getText(), shiroNachname.getText(), 10, Graduierung.DAN1);
		 
		new ShobuIpponMainGuiLoader(aka, shiro, isFinale.isSelected());
		
		stage.close();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		
	}
}
