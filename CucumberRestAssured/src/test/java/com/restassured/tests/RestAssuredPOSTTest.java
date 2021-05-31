package com.restassured.tests;

import java.io.File;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredPOSTTest {
	
	RequestSpecification request = null;
	Response response = null;
	ValidatableResponse vResponse;
	
	Logger logger ;
	
	@Before
	public void setup() {
		request = RestAssured.given();
		PropertyConfigurator.configure("log4j.properties");
		logger = LogManager.getLogger(RestAssuredPOSTTest.class.getSimpleName());
	}

	@Given("^I have base uri as \"([^\"]*)\"$")
	public void i_have_base_url_as(String baseUri) throws Throwable {
		logger.fatal("archana" + baseUri);
		request = request.baseUri(baseUri);
	}

	@Given("^I have base path as \"([^\"]*)\"$")
	public void i_have_base_path_as(String basePath) throws Throwable {
		request = request.basePath(basePath);
	}

	@Given("^i have following headers:$")
	public void i_have_following_headers(DataTable headerData) throws Throwable {

	     Map<String,String> headerMap = headerData.asMap(String.class,String.class);
	    
	     for(String key: headerMap.keySet()) {
	    	 request = request.headers(key,headerMap.get(key));
	     }
	    
	}

	@Given("^I use payload as \"([^\"]*)\"$")
	public void i_use_payload_as(String fileName) throws Throwable {
		String payloadPath = System.getProperty("user.dir")+"/testdata/payload/"+fileName;
		File payLoadfile = new File(payloadPath) ;
		request = request.body(payLoadfile);
	}

	@When("^I do a \"([^\"]*)\" request on \"([^\"]*)\"$")
	public void i_do_a_request_on(String method, String uriPath) throws Throwable {
		logger.fatal(request);
			/*switch(method) {
			case "POST": response = request.post(uriPath); break;
			case "GET" : response = request.get(uriPath); break;
			}*/
		if("POST".equals(method)) {
			response = request.post(uriPath); 
		}else if("GET".equals(method)) {
			response = request.get(uriPath);
		}
	}

	@Then("^I log the body$")
	public void i_log_the_body() throws Throwable {
		
		String loginfo = response.then().log().body().toString();
		logger.fatal(loginfo);
		vResponse = response.then().log().body();
		logger.fatal(vResponse);
	}

	@Then("^assert following in the body$")
	public void assert_following_in_the_body(DataTable assertData) throws Throwable {
		Map<String,String> assertMap = assertData.asMap(String.class,String.class);
		
		vResponse.assertThat()
		.statusCode(HttpStatus.SC_OK);
		
		for(String key: assertMap.keySet()) {
			vResponse.assertThat().body(key, equalTo(assertMap.get(key)));
		}
		

	//	.body("Envelope.Body.NumberToDollarsResponse.NumberToDollarsResult",equalTo("ten dollars"));
	}

}
