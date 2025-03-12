package com.ECommerce.stepdefinition;

import static io.restassured.RestAssured.given;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Assert;
import com.ECommerce.Utile.deleteRequest;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;

public class TC02_TS03_Verify_data_deletion_process_GraphQL {
	private String path = new java.io.File("").getAbsolutePath() + "\\dataProp.txt";
	private String locatioID;
	private String characterID;
	private String episodeID;
	deleteRequest GDL = new deleteRequest();

	@When("Identify the data ID required to delete")
	public void identify_the_data_id_required_to_delete() {
		try {
			List<String> allIds = Files.readAllLines(Paths.get(path));
			for (String specID : allIds) {
				if (specID.split(":")[0].contains("createLocationID"))
					locatioID = specID.split(":")[1];
				else if (specID.split(":")[0].contains("createCharacterID"))
					characterID = specID.split(":")[1];
				else if (specID.split(":")[0].contains("createEpisodeID"))
					episodeID = specID.split(":")[1];
			}
			Assert.assertTrue("  [INFO]: ID is Null", locatioID != null && characterID != null && episodeID != null);
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		System.out.println("  [STEP:PASSED]");
	}

	@Then("Verify the delete process of character data")
	public void verify_the_delete_process_of_character_data() {

		JsonPath JS = new JsonPath(
				given().header("content-Type", "application/json").body(GDL.characterDelete(characterID)).when()
						.post("https://rahulshettyacademy.com/gq/graphql").then().extract().asString());
		Assert.assertTrue("  [ERROR] ERROR in delete character ",
				JS.getString("data.deleteCharacters.charactersDeleted").contains("1"));
		System.out.println("  [STEP:PASSED]");

	}

	@Then("Verify the delete process of location data")
	public void verify_the_delete_process_of_location_data() {

		JsonPath JS = new JsonPath(
				given().header("content-Type", "application/json").body(GDL.LocationDeletion(locatioID)).when()
						.post("https://rahulshettyacademy.com/gq/graphql").then().extract().asString());
		Assert.assertTrue("  [ERROR] ERROR in delete location ",
				JS.getString("data.deleteLocations.locationsDeleted").contains("1"));
		System.out.println("  [STEP:PASSED]");

	}

	@Then("Verify the delete process of episode data")
	public void verify_the_delete_process_of_episode_data() {
		JsonPath JS = new JsonPath(given().header("content-Type", "application/json").body(GDL.episodeDelete(episodeID))
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().asString());
		Assert.assertTrue("  [ERROR] ERROR in delete location ",
				JS.getString("data.deleteEpisodes.episodesDeleted").contains("1"));
		System.out.println("  [STEP:PASSED]");
	}
}
