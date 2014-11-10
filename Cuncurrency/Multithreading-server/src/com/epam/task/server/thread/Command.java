package com.epam.task.server.thread;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Command implements Delayed {

	private long startTime;
	private String command;
	private String name;
	private String result;

	public Command(String name, String command, long delay) {
		startTime = System.currentTimeMillis() + delay;
		this.command = command;
		this.name = name;
	}

	public String getCommand() {
		return command;
	}

	public String getName() {
		return name;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public String getResult(){
		return result;
	}
	
	@Override
	public int compareTo(Delayed o) {
		if (this.startTime < ((Command) o).startTime) {
			return -1;
		}
		if (this.startTime > ((Command) o).startTime) {
			return 1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		long diff = startTime - System.currentTimeMillis();
		return unit.convert(diff, TimeUnit.MILLISECONDS);
	}

}
