package business;

import javafx.application.Platform;

import business.CompetitionListener;
import business.KumiteCompetitor;

import com.comphel.common.business.Stopwatch;
import com.comphel.common.definition.CompetitorNameInCompetition;
import definition.Config;

public class Match extends Thread{

	CompetitionListener compListener;
	
	boolean isFinale;
	
	private KumiteCompetitor winner;
	
	Stopwatch stopwatch;
	
	KumiteCompetitor aka;
	
	KumiteCompetitor shiro;
	
	Config control;
	
	boolean isPaused = true;
	
	int round = 1;

	public Match(KumiteCompetitor aka, KumiteCompetitor shiro, Stopwatch clock, CompetitionListener compListener, boolean isFianle){
		this.aka = aka;
		this.shiro = shiro;
		this.stopwatch = clock;
		this.compListener = compListener;
		this.stopwatch.reset();
		this.isFinale=isFianle;
		
		control = new Config();
		control.update(isFinale);
		
		setDaemon(true);
        setName("Controll-Thread");
		
	}
	
	public void begin(){
		stopwatch.handleStart();
		if(isPaused){
			changeState();
		}
		if(!this.isAlive() || isInterrupted()){
			start();
		}
	}
	
	public void run(){
		while(!isFinished()){
			checkIfFinished();
        }
	}

		

	private void checkIfFinished() {
		if(!isPaused){
			if(isOutOfTime()){
				pause();
				updateUI();
				interrupt();
			}
		}
	}

	private void updateUI() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {	
				compListener.reactOnOutOfTime();
			}
		});
	}

	public void pause(){
		stopwatch.handleStopped();
		if(!isPaused){
			changeState();
		}
	}
	
	private void changeState(){
		isPaused = (!isPaused);
	}
	
	private boolean isOutOfTime() {
		return stopwatch.getTime() > control.getTimeleft();
	}
	
	public void nextRound(){
		this.reset();
		this.round++;
	}
	
	public void wazari(CompetitorNameInCompetition comp){
		if(comp == CompetitorNameInCompetition.Aka){
			aka.getJudgement().addWazari();
		}
		else{
			shiro.getJudgement().addWazari();
		}
		
		evaluateWinner();
	}
	
	public void ippon(CompetitorNameInCompetition comp){
		if(comp == CompetitorNameInCompetition.Aka){
			aka.getJudgement().addIppon();
		}
		else{
			shiro.getJudgement().addIppon();
		}
		
		evaluateWinner();
	}	
	
	public void jogai(CompetitorNameInCompetition comp){
		if(comp == CompetitorNameInCompetition.Aka){
			aka.getJudgement().addJogai();
		}
		else{
			shiro.getJudgement().addJogai();
		}
	
		evaluateWinner();
	}
	
	public void muobi(CompetitorNameInCompetition comp){
		if(comp == CompetitorNameInCompetition.Aka){
			aka.getJudgement().addMuobi();
		}
		else{
			shiro.getJudgement().addMuobi();
		}
	
		evaluateWinner();
	}
	
	public void atenai(CompetitorNameInCompetition comp){
		if(comp == CompetitorNameInCompetition.Aka){
			aka.getJudgement().addAtenai();
		}
		else{
			shiro.getJudgement().addAtenai();
		}
		
		evaluateWinner();
	}
	
	
	public boolean isFinished(){
		return winner != null || aka.getJudgement().isHansuko() || shiro.getJudgement().isHansuko() || aka.getJudgement().getScore() == control.getWazariToWin() || shiro.getJudgement().getScore() == control.getWazariToWin(); 
	}
	
	public KumiteCompetitor getWinner(){
		return this.winner;
	}
	
	private void evaluateWinner(){
		if(isFinished()){
			if(aka.getJudgement().isHansuko()){
				setWinner(CompetitorNameInCompetition.Shiro);
			}
			else if(shiro.getJudgement().isHansuko()){
				setWinner(CompetitorNameInCompetition.Aka);
			}
			else if(shiro.getJudgement().getScore() == control.getWazariToWin()){
				setWinner(CompetitorNameInCompetition.Shiro);
			}
			else{
				setWinner(CompetitorNameInCompetition.Aka);
			}
		}
	}

	public void setWinner(CompetitorNameInCompetition comp) {
		if(comp == CompetitorNameInCompetition.Aka){
			this.winner = this.aka;
		}
		else{
			this.winner = this.shiro;
		}
		
		compListener.reactOnEvaluatedWinner();
	}
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	
	
	public Stopwatch getStoppwatch() {
		return stopwatch;
	}

	public void reset() {
		aka.clearAllJudgements();
		shiro.clearAllJudgements();
		stopwatch.reset();
	}

	public KumiteCompetitor getAka() {
		return aka;
	}

	public KumiteCompetitor getShiro() {
		return shiro;
	}
	
	
}
