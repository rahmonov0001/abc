package step_definitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.runner.RunWith;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.*;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils {

	RequestSpecification req;
	Response resWhen;
	Response resThen;
	static String placeId;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		req = given().spec(requestSpecification()).body(TestDataBuild.addPlacePayload(name, language, address));
	}

	@When("User calls {string} with {string} http Request")
	public void user_calls_with_post_http_request(String resource, String method) {
		if (method.equalsIgnoreCase("POST"))
			resWhen = req.when().post(APIResources.valueOf(resource).getResource());
		else if (method.equalsIgnoreCase("GET"))
			resWhen = req.when().get(APIResources.valueOf(resource).getResource());
	}

	@Then("The API call got succes with status code {int}")
	public void the_api_call_got_succes_with_status_code(int int1) {
		resThen = resWhen.then().extract().response();
		assertEquals(resThen.getStatusCode(), int1);
	}

	@Then("{string} in Response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
		String actualValue = getJsonPath(resThen, key);
		assertEquals(actualValue, expectedValue);
	}

	@Then("verify placeId created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		placeId = getJsonPath(resThen, "place_id");
		req = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_post_http_request(resource, "GET");
		String actualName = getJsonPath(resWhen.then().extract().response(), "name");
		assertEquals(actualName, expectedName);
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		req = given().spec(requestSpecification()).body(TestDataBuild.deletePlacePayload(placeId));
	}
		
	
	

}