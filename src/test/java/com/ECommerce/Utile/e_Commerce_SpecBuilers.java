package com.ECommerce.Utile;

import static io.restassured.RestAssured.given;
import com.ECommerce.POJO.loginRequest;
import com.ECommerce.POJO.loginResponce;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class e_Commerce_SpecBuilers {
	loginResponce loginResp = null;

	public loginResponce loginSpecifications() {
		loginRequest Req = new loginRequest();
		Req.setUserEmail("apiTesting@gmail.com");
		Req.setUserPassword("ABC123abc");
		
		RequestSpecification RequestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();

		loginResp = given().spec(RequestSpec).body(Req).when().post("/api/ecom/auth/login").then().extract().response()
				.as(loginResponce.class);
		return loginResp;
	}

}
