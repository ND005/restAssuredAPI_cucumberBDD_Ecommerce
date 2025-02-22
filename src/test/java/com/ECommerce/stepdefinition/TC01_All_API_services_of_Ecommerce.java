package com.ECommerce.stepdefinition;

import static io.restassured.RestAssured.given;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import com.ECommerce.Utile.e_Commerce_SpecBuilers;
import com.ECommerce.POJO.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.specification.RequestSpecification;

public class TC01_All_API_services_of_Ecommerce extends e_Commerce_SpecBuilers {

	loginResponce loginResp = null;
	createProductResponce CreateProductResp = null;
	createOrderResponce CreateOrderResp = null;

	@Given("^Login to access server and create access token for (.*) and (.*)$")
	public void login_to_access_server_and_create_access_token(String userID, String Password) {
		loginRequest LogReq = new loginRequest();
		LogReq.setUserEmail(userID);
		LogReq.setUserPassword(Password);
		loginResp = given().spec(loginSpecifications()).body(LogReq).when().post("/api/ecom/auth/login").then()
				.extract().response().as(loginResponce.class);
		System.out.println(" [INFO] "+loginResp.getToken().toString());
		Assert.assertTrue("[ERROR - Token level - LOGIN LEVEL] ",
				loginResp.getToken() != null && loginResp.getUserId() != null);
	}

	@When("Add the product in application and Order that product using API services")
	public void add_the_product_in_application_and_order_that_product_using_api_services() {

		RequestSpecification addProductinSite = given().spec(SpecificationswithToken(loginResp.getToken()))
				.param("productName", "Jaguar Shoes").param("productAddedBy", loginResp.getUserId().toString())
				.param("productSubCategory", "fashion").param("productCategory", "shoes").param("productPrice", "6969")
				.param("productDescription", "Puma Nike Jaguar").param("productFor", "men")
				.multiPart("productImage", new File(new java.io.File("").getAbsolutePath() + "/pumaShoes.png"));
		CreateProductResp = addProductinSite.when().post("/api/ecom/product/add-product").then().extract()
				.as(createProductResponce.class);
		System.out.println(" [INFO] "+CreateProductResp.getProductId());
		Assert.assertTrue(" [ERROR - Issue while creating order] ",CreateProductResp.getProductId().toString()!=null);
	}

	@Then("Verify the Order an Product details with given details")
	public void verify_the_order_an_product_details_with_given_details() {
		createProductSubRequest SubRequest = new createProductSubRequest();
		SubRequest.setCountry("India");
		SubRequest.setProductOrderedId(CreateProductResp.getProductId().toString());
		// Converting that sublist into array
		List<createProductSubRequest> cosr = new ArrayList<createProductSubRequest>();
		// Adding that array value into class with return type of array
		cosr.add(SubRequest);
		// Now calling the main order class
		createOrderRequest CreateOrderReq = new createOrderRequest();
		CreateOrderReq.setOrders(cosr);
		
		RequestSpecification createOrder = given().spec(SpecificationswithTokenJSON(loginResp.getToken()))
				.body(CreateOrderReq);
		CreateOrderResp = createOrder.when().post("api/ecom/order/create-order").then().extract()
				.as(createOrderResponce.class);
		System.out.println(" [PRODUCT ORDER ID]" + CreateOrderResp.getProductOrderId().get(0));
		System.out.println(" [PRODUCT ORDER]" + CreateOrderResp.getOrders().get(0));
		System.out.println(" [ORDER CREATED - DONE]");
		Assert.assertTrue(" [ERROR - Delete level - Unable to delete product] ",CreateOrderResp.getProductOrderId().get(0).toString()!=null);
	}

	@And("Delete the added product from E-commerce service")
	public void delete_the_added_product_from_e_commerce_service() {
		deleteResponce DeleteProductResp = new deleteResponce();
		DeleteProductResp = given()
				.spec(DeletedSpecificationswithToken(loginResp.getToken(), CreateProductResp.getProductId())).when()
				.delete("/api/ecom/product/delete-product/{PRODUCTID}").then().extract().as(deleteResponce.class);
		Assert.assertTrue(" [ERROR - Delete level - Unable to delete product] ",DeleteProductResp.getMessage().contains("Deleted Successfully"));
	}
}
