package com.restassured.runners;

import cucumber.api.CucumberOptions;

import org.junit.runner.RunWith;

import com.restassured.logger.Logger;

import cucumber.api.junit.Cucumber;


	

@RunWith(Cucumber.class)				
@CucumberOptions(features="/home/archanakitsgmai/eclipse-workspace/CucumberRestAssured/src/test/resources/restassured.feature"
,glue={"com.restassured.tests"}
//,plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
,plugin = {"progress"}
,tags = {"@POST,@GET"}
,monochrome = true)						
public class RestAssuredRunner
{		
	public static Logger logger = new Logger(RestAssuredRunner.class.getSimpleName());
}
