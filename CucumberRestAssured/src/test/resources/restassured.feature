
@RestAssuredPOST
Feature: RestAssured
  I want to test POST with RestAssured

  @POST
  Scenario: Test REST service with POST
    Given I have base uri as "https://www.dataaccess.com"
    And I have base path as "/webservicesserver"
    And i have following headers:
    |	Content-Type 	| text/xml		|
    | test					| checking 	|
    And I use payload as "NumberConversionDollars.xml"
    When I do a "POST" request on "/NumberConversion.wso" 
    Then I log the body
    And assert following in the body
    	| Envelope.Body.NumberToDollarsResponse.NumberToDollarsResult | ten dollars and twenty two cents |

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
 	
