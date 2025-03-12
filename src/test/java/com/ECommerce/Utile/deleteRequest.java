package com.ECommerce.Utile;

public class deleteRequest {
	public String LocationDeletion(String locationID) {
		String Body = "{\"query\":\"mutation {\\n  deleteLocations(locationIds: [" + locationID
				+ "]) {\\n    locationsDeleted\\n  }\\n}\",\"variables\":null}";
		return Body;
	}

	public String characterDelete(String characterID) {
		String Body = "{\"query\":\"mutation {\\n deleteCharacters(characterIds: [" + characterID
				+ "]) {\\n    charactersDeleted\\n  }\\n}\",\"variables\":null}";
		return Body;
	}

	public String episodeDelete(String EpisodeID) {
		String Body = "{\"query\":\"mutation {\\n deleteEpisodes(episodeIds: [" + EpisodeID
				+ "]) {\\n    episodesDeleted\\n  }\\n\\n}\",\"variables\":null}";
		return Body;
	}

}
