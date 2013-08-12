package com.comphel.common.business;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
public class Clock{

	private SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
	private String[] split;
	private SimpleStringProperty sspTime;
	private long time;
	final Timeline digitalTime;
	private long base;
	
	
	public Clock() {
	    sspTime = new SimpleStringProperty("00:00");
	    digitalTime = new Timeline(
			      new KeyFrame(Duration.seconds(1),
			        new EventHandler<ActionEvent>() {
			          @Override public void handle(ActionEvent actionEvent) {
			            updateTime();
			          }
			        }
			      )
			    );
	    digitalTime.stop();
	}
	
	public void stop() {
		digitalTime.stop();
	}

	public long getBase() {
		return base;
	}

	public void start() {
		 digitalTime.setCycleCount(Animation.INDEFINITE);
		digitalTime.play();
	}

	public void setBase(long base) {
		this.base = base;
		
	}
	
	public SimpleStringProperty getTime(){
		return sspTime;
	}
	
	public void updateTime() {
		this.time = System.currentTimeMillis()-base;
		split = sdf.format(new Date(this.time)).split(":");
		sspTime.set(split[0] + ":" + split[1] );
		//+ ":" + (split[2].length() == 1 ? "0" + split[2] : split[2].substring(0, 2)));
	}

	public void reset() {
		this.time = 0L;
		updateTime();
	}


}