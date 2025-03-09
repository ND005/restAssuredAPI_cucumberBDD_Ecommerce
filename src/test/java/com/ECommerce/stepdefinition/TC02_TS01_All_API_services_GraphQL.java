package com.ECommerce.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC02_TS01_All_API_services_GraphQL {

	@Given("Verify the mutation data file and truncate the old file")
	public void verify_the_mutation_data_file() {
		// Verify mutation data the file in present folder
		// if(file exist){delete the file}
	}
	
	@When("Create the data file and intiate the process")
	public void create_the_data_file_and_initiate() {
		// Verify mutation data the file in present folder
		// if(file exist){delete the file}
	}

	@And("^Create createLocation data using (.*),(.*) and (.*) of the character$")
	public void create_location_data_using_city_type_dimention_of_the_character(String cityName, String cityType,
			String cityDimention) {
		System.out.println("cityName : '"+cityName+"'");
		System.out.println("cityType : '"+cityType+"'");
		System.out.println("cityDimention : '"+cityDimention+"'");
	}

	@And("^Create createCharacter data using (.*),(.*),(.*),(.*),(.*),CharecterImage,OriginId and LocationId of the character$")
	public void create_character_data_using_charecterName_charecterType_charecterStatus_charecterSpecies_charecterGender_origin_and_location_of_the_character(
			String charactarName, String charatarType, String charactarStatus, String charecterSpecies,
			String charecterGender) {
		System.out.println("charactarName : '"+charactarName+"'");
		System.out.println("charatarType : '"+charatarType+"'");
		System.out.println("charactarStatus : '"+charactarStatus+"'");
		System.out.println("charecterSpecies : '"+charecterSpecies+"'");
		System.out.println("charecterGender : '"+charecterGender+"'");
	}

	@And("^Create createEpisode data using (.*),(.*) and (.*) of the episode$")
	public void create_create_episode_data_using_dark_born_jan(String EpisodeName, String EpisodeAirDate,
			String EpisodeID) {
		System.out.println("EpisodeName : '"+EpisodeName+"'");
		System.out.println("EpisodeAirDate : '"+EpisodeAirDate+"'");
		System.out.println("EpisodeID : '"+EpisodeID+"'");
	}

	@Then("Associate the episode and character using associateEpisodeCharacter GQL")
	public void associate_the_episode_and_character_using_associate_episode_character_gql() {
		
	}

	@And("Verify the file created with createLocationID,createCharacterID and createEpisodeID")
	public void verify_the_file_created_with_create_location_id_create_character_id_and_create_episode_id() {
		// Verify mutation data the file in present folder
		// if(!file exist){terminate the test}
		// else{read added data}
	}
}
