package com.restassured.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;


public class Logger {
	org.apache.log4j.Logger logger ;
	
	public Logger(String classFile) {
		PropertyConfigurator.configure("log4j.properties");
		logger = LogManager.getLogger(classFile);
	}
	
	public org.apache.log4j.Logger getLogger() {
		return logger;
	}
}
