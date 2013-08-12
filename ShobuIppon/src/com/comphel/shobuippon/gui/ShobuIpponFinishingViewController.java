package com.comphel.shobuippon.gui;

import com.comphel.common.definition.CompetitorNameInCompetition;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ShobuIpponFinishingViewController {

	@FXML
	private Label winner;
	
	private Stage stage;
	
	
	@FXML
	public void newMatch(){
		new ShobuIpponCompetitorSetupLoader();
		close();
	}
	
	@FXML 
	public void close(){
		this.stage.close();
	}
	

	
	public void setWinner(CompetitorNameInCompetition winner) {
		this.winner.setText(winner.name());
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	

}
