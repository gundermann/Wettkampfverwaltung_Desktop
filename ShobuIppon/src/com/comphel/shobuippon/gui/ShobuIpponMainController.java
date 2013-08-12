package com.comphel.shobuippon.gui;

import com.comphel.common.definition.CompetitorNameInCompetition;

import business.CompetitionListener;
import business.Match;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ShobuIpponMainController implements CompetitionListener{
	
	private Match match;
	
	@FXML
	private Label time;
	
	@FXML
	private Button btStartStop;
	
	@FXML
	private Label lbAka;
	
	@FXML
	private Button btAkaJogai;
	
	@FXML
	private Button btAkaAtenai;
	
	@FXML
	private Button btAkaMubobi;
	
	@FXML
	private Button btAkaWazari;
	
	@FXML
	private Label lbShiro;
	
	@FXML
	private Button btShiroWazari;
	
	@FXML
	private Button btShiroJogai;
	
	@FXML
	private Button btShiroAtenai;
	
	@FXML
	private Button btShiroMubobi;

	private Stage stage;
	
	
	
	@FXML
	private void startStop(){
		if(!match.getStoppwatch().isStopped()){
			stopClock();
		}else{
			startClock();
		}
	}
	
	private void startClock() {
		match.begin();
		btStartStop.setText("Stop");
	}
	
	private void stopClock() {
		match.pause();
		btStartStop.setText("Start");
	}
	
	@FXML
	private void akaJogai(){
		match.jogai(CompetitorNameInCompetition.Aka);
		btAkaJogai.setText("Jogai: " + String.valueOf(match.getAka().getJudgement().getJogai().toNumber()));
	}
	
	@FXML
	private void akaAtenai(){
		match.atenai(CompetitorNameInCompetition.Aka);
		btAkaAtenai.setText("Atenai: " + String.valueOf(match.getAka().getJudgement().getAtenai().toNumber()));
	}

	@FXML
	private void akaMubobi(){
		match.muobi(CompetitorNameInCompetition.Aka);
		btAkaMubobi.setText("Mubobi: " + String.valueOf(match.getAka().getJudgement().getMuobi().toNumber()));
	}
	
	@FXML
	private void akaWazari(){
		match.wazari(CompetitorNameInCompetition.Aka);
		btAkaWazari.setText(String.valueOf(match.getAka().getJudgement().getScore()));
	}
	
	@FXML
	private void shiroWazari(){
		match.wazari(CompetitorNameInCompetition.Shiro);
		btShiroWazari.setText(String.valueOf(match.getShiro().getJudgement().getScore()));
	}
	
	@FXML
	private void shiroJogai(){
		match.jogai(CompetitorNameInCompetition.Shiro);
		btShiroJogai.setText("Jogai: " + String.valueOf(match.getShiro().getJudgement().getJogai().toNumber()));
	}

	@FXML
	private void shiroAtenai(){
		match.atenai(CompetitorNameInCompetition.Shiro);
		btShiroAtenai.setText("Atenai: " + String.valueOf(match.getShiro().getJudgement().getAtenai().toNumber()));
	}
	
	@FXML
	private void shiroMubobi(){
		match.muobi(CompetitorNameInCompetition.Shiro);
		btShiroMubobi.setText("Mubobi: " + String.valueOf(match.getShiro().getJudgement().getMuobi().toNumber()));
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	@Override
	public void reactOnEvaluatedWinner() {
		stopClock();
		if(match.getWinner() == match.getAka()){
			new ShobuIpponFinishingViewLoader(CompetitorNameInCompetition.Aka);
		}
		else{
			new ShobuIpponFinishingViewLoader(CompetitorNameInCompetition.Shiro);
		}
		close();
		
	}

	@Override
	public void reactOnOutOfTime() {
		btStartStop.setText("Start");
		new ShobuIpponRefereeDecisionLoader(match);
	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	private void close(){
		stage.close();
	}
	
	public void init(){
		time.textProperty().bind(match.getStoppwatch().getClock().getTime()); 
		String aka = (match.getAka().getVorname() + " " + match.getAka().getNachname());
		lbAka.setText(aka);
		if(aka.equals(" ")){
			lbAka.setText(CompetitorNameInCompetition.Aka.toString());
		}
		
		String shiro = (match.getShiro().getVorname() + " " + match.getShiro().getNachname());
		lbShiro.setText(shiro);
		if(shiro.equals(" ")){
			lbShiro.setText(CompetitorNameInCompetition.Shiro.toString());
		}
	}
}
