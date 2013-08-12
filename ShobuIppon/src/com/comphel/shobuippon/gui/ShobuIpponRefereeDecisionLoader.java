package com.comphel.shobuippon.gui;

import business.Match;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShobuIpponRefereeDecisionLoader extends Application{
	
	private Match match;
	
	private ShobuIpponRefereeDecisionController refereeController;

	public ShobuIpponRefereeDecisionLoader(Match match){
		this.match = match;
		try {
			start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				 "shobuippon_referee_decision.fxml"));
		 
		 AnchorPane root = (AnchorPane) fxmlLoader.load();
		 
		 refereeController = fxmlLoader.getController();
		
		 refereeController.setMatch(match);
		 refereeController.setStage(stage);
		 refereeController.init();
		
		 stage.setTitle("Referee Decision");
		 Scene scene = new Scene(root);
	     ObservableList<String> cssStyle = CssLoader.loadSkin(this.getClass(), "data/buttons.css");
		 scene.getStylesheets().clear();
		 scene.getStylesheets().addAll(cssStyle);
	     stage.setScene(scene);
		 stage.setResizable(false);
		 stage.show();
	}

}
