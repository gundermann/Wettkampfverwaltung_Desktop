package com.comphel.shobuippon.gui;

import com.comphel.common.definition.CompetitorNameInCompetition;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ShobuIpponFinishingViewLoader extends Application{
	
	private CompetitorNameInCompetition winner;
	
	private ShobuIpponFinishingViewController finishingController;

	public ShobuIpponFinishingViewLoader(CompetitorNameInCompetition winner){
		this.winner = winner;
		try {
			start(new Stage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				 "shobuippon_finishing_view.fxml"));
		 
		AnchorPane root = (AnchorPane) fxmlLoader.load();
		 
		finishingController = fxmlLoader.getController();
		 
		 
        stage.setTitle("Shobu-Ippon");
        Scene scene = new Scene(root);
        ObservableList<String> cssStyle = CssLoader.loadSkin(this.getClass(), "data/buttons.css");
		scene.getStylesheets().clear();
		scene.getStylesheets().addAll(cssStyle);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
        finishingController.setStage(stage);
        finishingController.setWinner(winner);
	}

}
