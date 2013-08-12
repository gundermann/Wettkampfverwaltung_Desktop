package com.comphel.shobuippon.gui;



import com.comphel.common.business.Clock;
import com.comphel.common.business.Stopwatch;

import business.KumiteCompetitor;
import business.Match;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ShobuIpponMainGuiLoader extends Application {
	
	ShobuIpponMainController mainController;
	KumiteCompetitor aka;
	KumiteCompetitor shiro;
	boolean isFinal;
	
	public ShobuIpponMainGuiLoader(KumiteCompetitor aka, KumiteCompetitor shiro, boolean isFinal) {
		this.aka = aka;
		this.shiro = shiro;
		this.isFinal = isFinal;
		
		try {
			start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				 "shobuippon_main.fxml"));
		 
		 AnchorPane root = (AnchorPane) fxmlLoader.load();
		 
		 mainController = fxmlLoader.getController();
		 
		 //TODO new Clock() auslagern nach Stopwatch
		 Match match = new Match( aka, shiro, new Stopwatch(new Clock()), mainController, isFinal);
		 mainController.setMatch(match);
		 mainController.setStage(stage);

		 
         stage.setTitle("Shobu-Ippon");
         Scene scene = new Scene(root);
         ObservableList<String> cssStyle = CssLoader.loadSkin(this.getClass(), "data/buttons.css");
 		 scene.getStylesheets().clear();
 		 scene.getStylesheets().addAll(cssStyle);
         stage.setScene(scene);
         stage.setResizable(false);
         stage.show();
         
         mainController.init();
    
	}



}
