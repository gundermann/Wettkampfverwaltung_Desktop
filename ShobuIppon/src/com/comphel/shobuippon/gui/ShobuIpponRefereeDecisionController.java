package com.comphel.shobuippon.gui;

import com.comphel.common.definition.CompetitorNameInCompetition;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import business.Match;

public class ShobuIpponRefereeDecisionController {

	@FXML
	private Button akaWins;
	
	@FXML
	private Button shiroWins;
	
	@FXML
	private Button hikiwake;
	
	private Match match;

	private Stage stage;
	
	
	@FXML
	public void akaWins(){
		match.setWinner(CompetitorNameInCompetition.Aka);
		close();
	}
	
	private void close() {
		stage.close();
	}

	@FXML
	public void shiroWins(){
		match.setWinner(CompetitorNameInCompetition.Aka);
		close();
	}
	
	@FXML
	public void hikiwake(){
		match.nextRound();
		close();
	}
	
	public void setMatch(Match match) {
		this.match = match;
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void init(){
		hikiwake.setDisable(match.getRound() > 2);
	}

}
