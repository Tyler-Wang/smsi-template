package com.smsi.pattern.model.recordcache.selfclear.delay;

public class DelayTime {
	private Integer days;
	private Integer hours;
	private Integer minutes;
	private Integer seconds;
	
	private static final Integer HOURS_PER_DAY = 24;
	private static final Integer MINUTES_PER_HOUR = 60;
	private static final Integer SECONDS_PER_MINUTE = 60;
	
	public DelayTime(Integer days, Integer hours, Integer minutes, Integer seconds){
		this.days = days;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public Integer getDelayTimeAllSeconds(){
		return this.days * HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE
				+ this.hours * MINUTES_PER_HOUR * SECONDS_PER_MINUTE
				+ this.minutes * SECONDS_PER_MINUTE
				+ this.seconds;
	}

	@Override
	public String toString() {
		return "DelayTime [days=" + days + ", hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds + "]";
	}
	
	
}
