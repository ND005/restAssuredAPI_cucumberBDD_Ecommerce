package com.ECommerce.Utile;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class e_Commerce_SpecBuilers {
	public static RequestSpecification loginSpecifications() {
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		return RequestSpec;
	}

	public static RequestSpecification SpecificationswithToken(String Token) {
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", Token).build();
		return RequestSpec;
	}

	public static RequestSpecification SpecificationswithTokenandQPM(String Token, String QueryParameter) {
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", Token).addQueryParam("id", QueryParameter).build();
		return RequestSpec;
	}

	public static RequestSpecification SpecificationswithTokenJSON(String Token) {
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", Token).setContentType(ContentType.JSON).build();
		return RequestSpec;
	}

	public static RequestSpecification DeletedSpecificationswithToken(String Token, String ProductID) {
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", Token).addPathParam("PRODUCTID", ProductID).setContentType(ContentType.JSON)
				.build();
		return RequestSpec;
	}

}
