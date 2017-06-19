package com.smsi.pattern.model.recordcache.selfclear.delay;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayWheelExecutor {
	private WheelSlot[] wheel;
	private Integer currentIndex = 0;
	private Integer wheelSize;
	private ScheduledExecutorService scheduleExecutor;
	
	public DelayWheelExecutor(Integer wheelSize){
		this.wheelSize = wheelSize;
		this.wheel = new WheelSlot[wheelSize];
	}
	
	private int getCurrTimeSeconds(){
		return (int)new Date().getTime() / 1000;
	}
	
	public void start(){
		if(scheduleExecutor == null){
			scheduleExecutor = Executors.newSingleThreadScheduledExecutor();
			scheduleExecutor.scheduleAtFixedRate(new WheelTask(), 0, 1, TimeUnit.SECONDS);
		}
	}
	
	public boolean addDelayTask(AbsDelayTask task){
		int taskDelaySecs = task.getDelayTime().getDelayTimeAllSeconds();
		int turns = taskDelaySecs / this.wheelSize;
		int locate = taskDelaySecs % this.wheelSize;
		WheelSlot slot = wheel[locate];
		if(slot == null){
			slot = new WheelSlot();
			wheel[locate] = slot;
		}
		return slot.addTask(new WrapperDelayTask(task, turns));
		
	}
	
	private void cyclicCurrentIndex(){
		this.currentIndex++;
		if(this.currentIndex >= this.wheelSize){
			this.currentIndex = 0;
		}
	}
	
	private class WheelTask implements Runnable{

		public void run() {
			// TODO Auto-generated method stub
			WheelSlot currentSlot = wheel[currentIndex];
			if(currentSlot != null){
				Set<WrapperDelayTask> tasks = currentSlot.getTasks();
				if(!tasks.isEmpty()){
					for(Iterator<WrapperDelayTask> iter = tasks.iterator(); iter.hasNext(); ){
						WrapperDelayTask task = iter.next();
						if(task.isItsTurn()){
							task.getRealTask().run();//同步执行
							iter.remove();
						}else{
							task.turnDecrease();//轮次减1
						}
						
					}
				}
			}
			
			cyclicCurrentIndex();
		}
		
	}
	
	private class WrapperDelayTask{
		private Integer turns;
		private AbsDelayTask task;
		
		public WrapperDelayTask(AbsDelayTask task, Integer turns){
			this.task = task;
			this.turns = turns;
		}
		
		public boolean isItsTurn(){
			return turns == 0;
		}
		
		public void turnDecrease(){
			this.turns = this.turns - 1;
		}
		
		public AbsDelayTask getRealTask(){
			return this.task;
		}
	}
	
	private class WheelSlot{
		private Set<WrapperDelayTask> tasks = new HashSet<WrapperDelayTask>();
		
		public WheelSlot(){
			
		}
		
		public boolean addTask(WrapperDelayTask task){
			return this.tasks.add(task);
		}
		
		public Set<WrapperDelayTask> getTasks(){
			return tasks;
		}
	}
}
