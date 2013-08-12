package com.comphel.shobuippon.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CssLoader {

	
	protected static final ObservableList<String> loadSkin(Class<?> currentClass, String cssFileName) {
		ObservableList<String> cssStyle = FXCollections.observableArrayList();
		cssStyle.addAll(currentClass.getResource(cssFileName).toExternalForm());
		return cssStyle;
	}
}
