package com.ECommerce.Utile;

public class mutationRequests {
	public String createLocation(String LocationName, String LocationType, String LocationDimention) {
		String Body = "mutation {createLocation(location: {name: \"" + LocationName + "\", type: \"" + LocationType
				+ "\", dimension: \"" + LocationDimention + "\"}){id}}";
		return Body;
	}

	public String createCharacter(String CharacterName, String CharacterType, String CharacterStatus,
			String CharecterSpecies, String CharecterGender, String originId, String locationId) {
		String Body = "mutation{createCharacter(character:{name:\"" + CharacterName + "\", type:\"" + CharacterType
				+ "\", status: \"" + CharacterStatus + "\", species: \"" + CharecterSpecies + "\", gender: \""
				+ CharecterGender + "\", image: \"NA\", originId: " + originId + ", locationId: " + locationId
				+ "}) {id}}";
		return Body;
	}

	public String createEpisode(String EpisodeName, String EpisodeAirDate, String EpisodeID) {
		String Body = "mutation{createEpisode(episode: {name: \"" + EpisodeName + "\", air_date: \"" + EpisodeAirDate
				+ "\", episode: \"" + EpisodeID + "\"}) {id}}";
		return Body;
	}
}
