package com.ECommerce.Utile;

public class mutationRequests {
	public String createLocation(String LocationName, String LocationType, String LocationDimention) {
		String body = "{\"query\":\"mutation {\\n  createLocation(location: {name: \\\"" + LocationName
				+ "\\\", type: \\\"" + LocationType + "\\\", dimension: \\\"" + LocationDimention
				+ "\\\"}) {\\n    id\\n  }\\n}\\n\",\"variables\":null}";
		return body;
	}

	public String createCharacter(String CharacterName, String CharacterType, String CharacterStatus,
			String CharacterSpecies, String CharacterGender, String originId, String locationId) {
		String Body = "{\"query\":\"mutation {\\n  createCharacter(character: {name: \\\"" + CharacterName
				+ "\\\", type: \\\"" + CharacterType + "\\\", status: \\\"" + CharacterStatus + "\\\", species: \\\""
				+ CharacterSpecies + "\\\", gender: \\\"" + CharacterGender + "\\\", image: \\\"NA\\\", originId:"
				+ originId + ", locationId: " + locationId + "}) {\\n    id\\n  }\\n}\\n\",\"variables\":null}";
		return Body;
	}

	public String createEpisode(String EpisodeName, String EpisodeAirDate, String EpisodeID) {
		String Body = "{\"query\":\"mutation {\\n  createEpisode(episode: {name: \\\"" + EpisodeName
				+ "\\\", air_date: \\\"" + EpisodeAirDate + "\\\", episode: \\\"" + EpisodeID
				+ "\\\"}) {\\n    id\\n  }\\n}\\n\",\"variables\":null}";
		return Body;
	}

	public String associateCharecterLocation(String episodeID, String charactarID) {
		String Body = "{\"query\":\"mutation {\\n  associateEpisodeCharacter(episodeId: " + episodeID
				+ ", characterId: " + charactarID + ") {\\n    status\\n  }\\n}\\n\",\"variables\":null}";
		return Body;
	}
}
