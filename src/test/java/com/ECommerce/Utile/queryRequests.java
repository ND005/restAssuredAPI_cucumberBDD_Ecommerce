package com.ECommerce.Utile;

public class queryRequests {
	public String charactarData(String charactarID) {
		String body = "{\"query\":\"{\\n  character(characterId: " + charactarID
				+ ") {\\n    id\\n    name\\n    type\\n    status\\n    species\\n    gender\\n    image\\n  }\\n}\\n\",\"variables\":null}";
		return body;
	}

	public String episodeData(String episodeID) {
		String body = "{\"query\":\"query {\\n  episode(episodeId:" + episodeID
				+ ") {\\n    id\\n    name\\n    air_date\\n    episode\\n    created\\n  }\\n}\",\"variables\":null}";
		return body;
	}

	public String locationData(String locationID) {
		String body = "{\"query\":\"{\\n  location(locationId: " + locationID
				+ ") {\\n    id\\n    name\\n    type\\n    dimension\\n    created\\n  }\\n}\\n\",\"variables\":null}";
		return body;
	}
}
