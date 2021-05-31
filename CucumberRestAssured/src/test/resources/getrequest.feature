@RestAssuredGET
Feature: RestAssured
  I want to test GET with RestAssured

	@GET
   Scenario: Test REST service with GET
   	Given I have a base get uri as "https://reqres.in" 
   	And I have get basepath as "/api/users/"
   	And I have the following headers for get:
   	|content-type  |  text/json   |
   	|Test          |  Checking    |
   	When I do a method "GET" request on "2"
   	Then I log the response body
   	And assert following in the response body
   		|data.email	|janet.weaver@reqres.in	|
