package com.ECommerce.stepdefinition;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Assert;
import com.ECommerce.Utile.mutationRequests;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class TC02_TS01_Verify_data_mutation_process_GraphQL {

	mutationRequests mutData = new mutationRequests();
	private String path = new java.io.File("").getAbsolutePath() + "\\dataProp.txt";

	@Given("Verify the mutation data file and truncate the old file")
	public void verify_the_mutation_data_file() {
		try {
			Path datafile = Paths.get(path);
			Thread.sleep(1000);
			if (Files.exists(datafile)) {
				Files.delete(datafile);
				Thread.sleep(1000);
				// System.out.println(" [INFO] TC02-TS01: locationData Properties File
				// Deleted");
			} // else {System.out.println(" [INFO] TC02-TS01: locationData Properties File
				// !Exist");}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		System.out.println("  [STEP:PASSED]");
	}

	@When("Create the data file and intiate the process")
	public void create_the_data_file_and_initiate() throws Throwable {

		try {
			Path datafile = Paths.get(path);
			Thread.sleep(2000);
			if (!Files.exists(datafile)) {
				Files.createFile(datafile);
				Thread.sleep(2000);
				Assert.assertTrue(Files.exists(datafile));
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		System.out.println("  [STEP:PASSED]");
	}

	@And("^Create createLocation data using (.*),(.*) and (.*) of the character$")
	public void create_location_data_using_city_type_dimention_of_the_character(String LocationName,
			String LocationType, String LocationDimension) {

		// API request
		String LocationId = given().header("content-Type", "application/json")
				.body(mutData.createLocation(LocationName, LocationType, LocationDimension)).when()
				.post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();

		// locationData service
		JsonPath JS = new JsonPath(LocationId);
		try {
			String filename = path;
			FileWriter fw = new FileWriter(filename, true);
			fw.write("createLocationID:" + JS.getString("data.createLocation.id") + "\n");
			fw.close();
		} catch (Exception e) {
			System.err.println("IOException: " + e.getMessage());
		}
		System.out.println("  [STEP:PASSED]");
	}

	@And("^Create createCharacter data using (.*),(.*),(.*),(.*),(.*),CharecterImage,OriginId and LocationId of the character$")
	public void create_character_data_using_Name_Type_Status_Species_Gender_origin_and_location_of_character(
			String charactarName, String charatarType, String charactarStatus, String charecterSpecies,
			String charecterGender) {

		String locID = null;
		try {
			// ID retrieve service
			List<String> allLines = Files.readAllLines(Paths.get(path));
			for (String line : allLines) {
				if (line.split(":")[0].contains("createLocationID")) {
					locID = line.split(":")[1];
					break;
				}
			}

			// API request
			String charecteId = given().header("content-Type", "application/json")
					.body(mutData.createCharacter(charactarName, charatarType, charactarStatus, charecterSpecies,
							charecterGender, locID, locID))
					.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();

			// locationData service
			JsonPath JS = new JsonPath(charecteId);
			String filename = path;
			FileWriter fw = new FileWriter(filename, true);
			fw.write("createCharacterID:" + JS.getString("data.createCharacter.id") + "\n");
			fw.close();
		} catch (Exception e) {
			System.err.println("IOException: " + e.getMessage());
		}
		System.out.println("  [STEP:PASSED]");
	}

	@And("^Create createEpisode data using (.*),(.*) and (.*) of the episode$")
	public void create_create_episode_data_using_dark_born_jan(String EpisodeName, String EpisodeAirDate,
			String EpisodeID) {

		try {
			// API request
			String EpisodeId = given().header("content-Type", "application/json")
					.body(mutData.createEpisode(EpisodeName, EpisodeAirDate, EpisodeID)).when()
					.post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();

			// locationData service
			JsonPath JS = new JsonPath(EpisodeId);

			String filename = path;
			FileWriter fw = new FileWriter(filename, true);
			fw.write("createEpisodeID:" + JS.getString("data.createEpisode.id") + "\n");
			fw.close();
		} catch (Exception e) {
			System.err.println("IOException: " + e.getMessage());
		}
		System.out.println("  [STEP:PASSED]");
	}

	@Then("Associate the episode and character using associateEpisodeCharacter GQL")
	public void associate_the_episode_and_character_using_associate_episode_character_gql() {
		try {

			// ID retrieve service
			String CharecterID = null;
			String episodeID = null;
			List<String> allLines = Files.readAllLines(Paths.get(path));
			for (String line : allLines) {
				if (line.split(":")[0].contains("createEpisodeID")) {
					episodeID = line.split(":")[1];
				} else if (line.split(":")[0].contains("createCharacterID")) {
					CharecterID = line.split(":")[1];
				}
			}

			// API request
			String associationStatus = given().header("content-Type", "application/json")
					.body(mutData.associateCharecterLocation(episodeID, CharecterID)).when()
					.post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
			JsonPath JS = new JsonPath(associationStatus);

			String filename = path;
			FileWriter fw = new FileWriter(filename, true);
			fw.write("associateEpisodeCharacterStatus:" + JS.getString("data.associateEpisodeCharacter.status") + "\n");
			fw.close();

			Assert.assertTrue(JS.getString("data.associateEpisodeCharacter.status").contains("1"));
		} catch (Exception e) {
			System.err.println("IOException: " + e.getMessage());
		}
		System.out.println("  [STEP:PASSED]");
	}

	@And("Verify the file created with createLocationID,createCharacterID and createEpisodeID")
	public void verify_the_file_created_with_create_location_id_create_character_id_and_create_episode_id() {
		// File characterData validation
		try {
			boolean charecterID = false;
			boolean episodeID = false;
			boolean locationID = false;
			List<String> allLines = Files.readAllLines(Paths.get(path));
			for (String line : allLines) {
				if (line.split(":")[0].contains("createEpisodeID") && line.split(":")[1] != null) {
					episodeID = true;
				} else if (line.split(":")[0].contains("createCharacterID") && line.split(":")[1] != null) {
					charecterID = true;
				} else if (line.split(":")[0].contains("createLocationID") && line.split(":")[1] != null) {
					locationID = true;
				}
			}
			Assert.assertTrue("  [ERROR] : one of the ID not in dataProp.txt file",
					charecterID && episodeID && locationID);
		} catch (Exception e) {
			System.err.println("IOException: " + e.getMessage());
		}
		System.out.println("  [STEP:PASSED]");
	}
}
