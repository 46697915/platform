package com.wxsoft.axis.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config")
public class Config {
	private int maxPathRouteThread;
	private boolean calculateAll;

	public int getMaxPathRouteThread() {
		return maxPathRouteThread;
	}

	public void setMaxPathRouteThread(int maxPathRouteThread) {
		this.maxPathRouteThread = maxPathRouteThread;
	}

	public boolean getCalculateAll() {
		return calculateAll;
	}

	public void setCalculateAll(boolean calculateAll) {
		this.calculateAll = calculateAll;
	}
}
