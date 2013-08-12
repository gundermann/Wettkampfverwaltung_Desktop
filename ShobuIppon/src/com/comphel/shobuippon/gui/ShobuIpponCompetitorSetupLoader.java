package com.comphel.shobuippon.gui;


import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShobuIpponCompetitorSetupLoader extends Application {
	
	private ShobuIpponCompetitorSetupController setupController;
	private Stage stage;
	private static boolean firstStarted = true;
	
	public static void main(String[] arg){
		launch(arg);
	}
	
	public ShobuIpponCompetitorSetupLoader(){
		if(!firstStarted){
		try {
			start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		firstStarted = false;
	}

	@Override
	public void start(Stage primeStage) throws Exception {
		this.stage = primeStage;
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				 "shobuippon_competitor_setup.fxml"));
		 
		 AnchorPane root = (AnchorPane) fxmlLoader.load();
		 
		 setupController = fxmlLoader.getController();
		 setupController.setStage(stage);

		 
         stage.setTitle("Shobu-Ippon Competitor Setup");
         Scene scene = new Scene(root);
 		 ObservableList<String> cssStyle = CssLoader.loadSkin(this.getClass(), "data/buttons.css");
 		 scene.getStylesheets().clear();
 		 scene.getStylesheets().addAll(cssStyle);
         stage.setScene(scene);
         stage.setResizable(false);
         stage.show();
		
	}
	
	

}
