package com.restassured.tests;

import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.cucumber.datatable.DataTable;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredGETTest {

		RequestSpecification request = null;
		Response response = null;
		ValidatableResponse vResponse = null;

@Given("I have a base get uri as {string}")
public void i_have_a_base_get_uri_as(String baseUri) {
	System.out.println("*****"+baseUri);
	RestAssured.baseURI = baseUri;
	request = RestAssured.given();
	//	request = request.baseUri(baseUri);
}

@Given("I have get basepath as {string}")
public void i_have_get_basepath_as(String basePath) {
	request = request.basePath(basePath);
}

@Given("I have the following headers for get:")
public void i_have_the_following_headers_for_get(DataTable headersTable) {
	Map<String,String> headerMap = headersTable.asMap(String.class,String.class);
	
	for(String key: headerMap.keySet()) {
		request = request.headers(key,headerMap.get(key));
	}
}

@When("I do a method {string} request on {string}")
public void i_do_a_method_request_on(String method, String userId) {
		if("POST".equals(method)) {
			response = request.get(userId);
		}else if("GET".equals(method)){
			response = request.get(userId);
		}
}

@Then("I log the response body")
public void i_log_the_response_body() {
	vResponse = response.then().log().body();
	
}

@Then("assert following in the response body")
public void assert_following_in_the_response_body(DataTable assertData) {
	Map<String,String> assertMap = assertData.asMap(String.class, String.class);
	
	for(String key : assertMap.keySet()) {
		vResponse.and().assertThat().body(key,equalTo(assertMap.get(key)));
	}
	
}




}
