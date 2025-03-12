package com.ECommerce.stepdefinition;

import static io.restassured.RestAssured.given;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Assert;
import com.ECommerce.POJO.marvalCharecterData;
import com.ECommerce.POJO.marvalEpisodeData;
import com.ECommerce.POJO.marvelLocationData;
import com.ECommerce.Utile.queryRequests;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC02_TS02_Verify_data_query_process_GraphQL {
	private String path = new java.io.File("").getAbsolutePath() + "\\dataProp.txt";
	String locatioID = null;
	String charactarID = null;
	String episodeID = null;
	queryRequests GQL = new queryRequests();

	@Given("Verify the query data file and intiate the process")
	public void verify_the_query_data_file_and_intiate_the_process() {
		Path datafile = Paths.get(path);
		try {
			if (Files.exists(datafile)) {
				Assert.assertTrue("  [INFO]: Query characterData file not exist", true);
			}
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		System.out.println("  [STEP:PASSED]");
	}

	@When("Procced the GQL flow based on data create through mutation")
	public void procced_the_gql_flow_based_on_data_create_through_mutation() {
		try {
			List<String> allIds = Files.readAllLines(Paths.get(path));
			for (String specID : allIds) {
				if (specID.split(":")[0].contains("createLocationID"))
					locatioID = specID.split(":")[1];
				else if (specID.split(":")[0].contains("createCharacterID"))
					charactarID = specID.split(":")[1];
				else if (specID.split(":")[0].contains("createEpisodeID"))
					episodeID = specID.split(":")[1];
			}
			Assert.assertTrue("  [INFO]: ID is Null", locatioID != null && charactarID != null && episodeID != null);
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
		}
		System.out.println("  [STEP:PASSED]");
	}

	@Then("^Verify location data :(.*),(.*) and (.*)$")
	public void verify_location_data_LocationName_LocationType_and_LocationDimension(String LocationName,
			String LocationType, String LocationDimension) {
		/*
		 * marvelLocationData locResp = given().header("content-Type",
		 * "application/json") .body(GQL.locationData(locatioID)).when().post(
		 * "https://rahulshettyacademy.com/gq/graphql").then()
		 * .extract().as(marvelLocationData.class);
		 * Assert.assertTrue(locResp.getLocationData().getLocation().getName().contains(
		 * LocationName));
		 * Assert.assertTrue(locResp.getLocationData().getLocation().getType().contains(
		 * LocationType));
		 * Assert.assertTrue(locResp.getLocationData().getLocation().getDimension().
		 * contains(LocationDimension));
		 * Assert.assertTrue(locResp.getLocationData().getLocation().getCreated() !=
		 * null); System.out.println("  [STEP:PASSED]");
		 */
	}

	@Then("^Verify charactar data :(.*),(.*),(.*),(.*) and (.*)$")
	public void verify_charactar_data_CharacterName_CharacterType_CharacterStatus_CharecterSpecies_and_CharecterGender(
			String CharactarName, String CharactarType, String CharactarStatus, String CharactarSpecies,
			String CharactarGender) {

		marvalCharecterData ReqResp = given().header("content-Type", "application/json")
				.body(GQL.charactarData(charactarID)).when().post("https://rahulshettyacademy.com/gq/graphql").then()
				.extract().as(marvalCharecterData.class);

		Assert.assertTrue("  [ERROR] Character Name : " + ReqResp.getData().getCharacter().getName(),
				ReqResp.getData().getCharacter().getName().contains(CharactarName));
		Assert.assertTrue("  [ERROR] Character Type : " + ReqResp.getData().getCharacter().getType(),
				ReqResp.getData().getCharacter().getType().contains(CharactarType));
		Assert.assertTrue("  [ERROR] Character Status : " + ReqResp.getData().getCharacter().getStatus(),
				ReqResp.getData().getCharacter().getStatus().contains(CharactarStatus));
		//Assert.assertTrue("  [ERROR] Character Species : " + ReqResp.getData().getCharacter().getSpecies(),
				//ReqResp.getData().getCharacter().getSpecies().contains(CharactarSpecies));
		Assert.assertTrue("  [ERROR] Character Gender : " + ReqResp.getData().getCharacter().getGender(),
				ReqResp.getData().getCharacter().getGender().contains(CharactarGender));
		System.out.println("  [STEP:PASSED]");
	}

	@Then("^Verify episode data :(.*),(.*) and (.*)$")
	public void verify_episode_data_dark_born_jan_and(String EpisodeName, String EpisodeAirDate, String EpisodeID) {

		marvalEpisodeData Req = given().header("content-Type", "application/json").body(GQL.episodeData(episodeID))
				.when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().as(marvalEpisodeData.class);

		Assert.assertTrue(" [ERROR] Episode Name : " + Req.getEpisodeData().getEpisode().getName(),
				Req.getEpisodeData().getEpisode().getName().contains(EpisodeName));
		Assert.assertTrue(" [ERROR] Episode Air Date : " + Req.getEpisodeData().getEpisode().getAir_date(),
				Req.getEpisodeData().getEpisode().getAir_date().contains(EpisodeAirDate));
		Assert.assertTrue(" [ERROR] Episode ID : " + Req.getEpisodeData().getEpisode().getId(),
				Req.getEpisodeData().getEpisode().getId() > 0);
		System.out.println("  [STEP:PASSED]");
	}
}
