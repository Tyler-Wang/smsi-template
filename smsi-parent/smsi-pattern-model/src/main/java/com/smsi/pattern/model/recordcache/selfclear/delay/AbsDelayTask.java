package com.smsi.pattern.model.recordcache.selfclear.delay;

public abstract class AbsDelayTask implements Runnable{
	protected DelayTime delayTime;
	
	protected AbsDelayTask(DelayTime delayTime){
		this.delayTime = delayTime;
	}

	public DelayTime getDelayTime() {
		return delayTime;
	}

	
}
