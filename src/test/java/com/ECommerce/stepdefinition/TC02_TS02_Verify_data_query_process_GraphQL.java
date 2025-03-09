package com.ECommerce.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC02_TS02_Verify_data_query_process_GraphQL {

	@Given("Verify the query data file and intiate the process")
	public void verify_the_query_data_file_and_intiate_the_process() {

	}

	@When("Procced the GQL flow based on data create through mutation")
	public void procced_the_gql_flow_based_on_data_create_through_mutation() {

	}

	@Then("^Verify location data :(.*),(.*) and (.*)$")
	public void verify_location_data_LocationName_LocationType_and_LocationDimension(String LocationName,
			String LocationType, String LocationDimension) {
		System.out.println("cityName : '" + LocationName + "'");
		System.out.println("cityType : '" + LocationType + "'");
		System.out.println("cityDimention : '" + LocationDimension + "'");
	}

	@Then("^Verify charactar data :(.*),(.*),(.*),(.*) and (.*)$")
	public void verify_charactar_data_CharacterName_CharacterType_CharacterStatus_CharecterSpecies_and_CharecterGender(
			String CharactarName, String CharactarType, String CharactarStatus, String CharactarSpecies,
			String CharactarGender) {
		System.out.println("charactarName : '" + CharactarName + "'");
		System.out.println("charatarType : '" + CharactarType + "'");
		System.out.println("charactarStatus : '" + CharactarStatus + "'");
		System.out.println("charecterSpecies : '" + CharactarSpecies + "'");
		System.out.println("charecterGender : '" + CharactarGender + "'");
	}

	@Then("^Verify episode data :(.*),(.*) and (.*)$")
	public void verify_episode_data_dark_born_jan_and(String EpisodeName, String EpisodeAirDate, String EpisodeID) {
		System.out.println("EpisodeName : '"+EpisodeName+"'");
		System.out.println("EpisodeAirDate : '"+EpisodeAirDate+"'");
		System.out.println("EpisodeID : '"+EpisodeID+"'");
	}
}
