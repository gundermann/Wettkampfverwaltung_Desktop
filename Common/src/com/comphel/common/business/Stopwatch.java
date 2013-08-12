package com.comphel.common.business;





public class Stopwatch {

	private Long lastTime;
	
	private Clock clock;

	private boolean isStopped = true;
	
	public Stopwatch(Clock clock) {
		this.clock = clock;
		this.setLastTime(0L);
	}
	
	
	public void updateClockByTime(Long time){
		this.lastTime = time;
	}
	
	/**
	 * Stops the clock
	 */
	public void handleStopped() {
		this.setLastTime(System.currentTimeMillis()-clock.getBase());
		isStopped = true;
		clock.stop();
	}

	
	/**
	 * if clock was initialized the clock begin from 00:00
	 * if clock was stopped the clock begins from saved time
	 */
	public void handleStart(){
		clock.setBase(System.currentTimeMillis()-this.lastTime);
		isStopped = false;
		clock.start();
		
	}
	
	public void reset(){
		handleStopped();
		this.setLastTime(0L);
		handleStart();
		handleStopped();
		this.setLastTime(0L);
		clock.reset();
	}
	
	
	public Clock getClock() {
		return clock;
	}

	public Long getTime() {
		return System.currentTimeMillis()-clock.getBase();
	}
	
	
	public void setLastTime(Long time) {
		this.lastTime = time;
	}


	public void showTime() {
		clock.setBase(System.currentTimeMillis()-this.lastTime);
		clock.start();
		clock.stop();
	}


	public Long getLastTime() {
		return lastTime;
	}


	public boolean isStopped() {
		return isStopped;
	}
	
}
