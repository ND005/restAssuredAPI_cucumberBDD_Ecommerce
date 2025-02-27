package com.ECommerce.Utile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class e_Commerce_SpecBuilers {
	public static RequestSpecification loginSpecifications() throws IOException {
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri(globalData("baseURL"))
				.setContentType(ContentType.JSON).build();
		return RequestSpec;
	}

	public static RequestSpecification SpecificationswithToken(String Token) throws IOException {
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri(globalData("baseURL"))
				.addHeader("Authorization", Token).build();
		return RequestSpec;
	}

	public static RequestSpecification SpecificationswithTokenandQPM(String Token, String QueryParameter)
			throws IOException {
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri(globalData("baseURL"))
				.addHeader("Authorization", Token).addQueryParam("id", QueryParameter).build();
		return RequestSpec;
	}

	public static RequestSpecification SpecificationswithTokenJSON(String Token) throws IOException {
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri(globalData("baseURL"))
				.addHeader("Authorization", Token).setContentType(ContentType.JSON).build();
		return RequestSpec;
	}

	public static RequestSpecification DeletedSpecificationswithToken(String Token, String ProductID)
			throws IOException {
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri(globalData("baseURL"))
				.addHeader("Authorization", Token).addPathParam("PRODUCTID", ProductID).setContentType(ContentType.JSON)
				.build();
		return RequestSpec;
	}

	public static String globalData(String key) throws IOException {
		Properties Prop = new Properties();
		FileInputStream Fis = new FileInputStream(
				new java.io.File("").getAbsolutePath() + "/src/test/resources/globalData.properties");
		Prop.load(Fis);
		System.out.println("  [INFO] BASE PROPERTY - " + Prop.getProperty(key).toString());
		return Prop.getProperty(key).toString();
	}
}
