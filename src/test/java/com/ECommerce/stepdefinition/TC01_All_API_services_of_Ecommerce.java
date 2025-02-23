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
	orderDetailsResponce OrderDetailsResp = null;

	@Given("^Login to access server and create access token for (.*) and (.*)$")
	public void login_to_access_server_and_create_access_token(String userID, String Password) {
		loginRequest LogReq = new loginRequest();
		LogReq.setUserEmail(userID);
		LogReq.setUserPassword(Password);
		loginResp = given().spec(loginSpecifications()).body(LogReq).when().post("/api/ecom/auth/login").then()
				.extract().response().as(loginResponce.class);
		System.out.println("  [INFO] - " + loginResp.getToken().toString());
		Assert.assertTrue("[ERROR - Token level - LOGIN LEVEL] ",
				loginResp.getToken() != null && loginResp.getUserId() != null);
	}

	@When("^Add the product in application with (.*),(.*) and (.*)$")
	public void add_the_product_in_application_with_productName_productPrice_and_productDescription(String productName,
			String productPrice, String productDescription) {
		RequestSpecification addProductinSite = given().spec(SpecificationswithToken(loginResp.getToken()))
				.param("productName", productName).param("productAddedBy", loginResp.getUserId().toString())
				.param("productSubCategory", "fashion").param("productCategory", "shoes")
				.param("productPrice", productPrice).param("productDescription", productDescription)
				.param("productFor", "men")
				.multiPart("productImage", new File(new java.io.File("").getAbsolutePath() + "/pumaShoes.png"));
		CreateProductResp = addProductinSite.when().post("/api/ecom/product/add-product").then().extract()
				.as(createProductResponce.class);
		System.out.println("  [INFO] - " + CreateProductResp.getProductId());
		Assert.assertTrue(" [ERROR - Issue while creating order] ",
				CreateProductResp.getProductId().toString() != null);
	}

	@Then("Verify the product details with given details")
	public void verify_the_product_details_with_given_details() {
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
		System.out.println("  [INFO] [PRODUCT ORDER ID] - " + CreateOrderResp.getProductOrderId().get(0));
		System.out.println("  [INFO] [PRODUCT ORDER] - " + CreateOrderResp.getOrders().get(0));
		Assert.assertTrue(" [ERROR - Product details - Unable to verify product details] ",
				CreateOrderResp.getProductOrderId().get(0).toString() != null);
	}

	@And("^Verify the order details with (.*),(.*) and (.*)$")
	public void verify_the_order_details_with_given_details(String productName, String productPrice,
			String productDescription) {
		OrderDetailsResp = given()
				.spec(SpecificationswithTokenandQPM(loginResp.getToken(),
						CreateOrderResp.getOrders().get(0).toString()))
				.when().get("/api/ecom/order/get-orders-details").then().extract().as(orderDetailsResponce.class);
		Assert.assertTrue(" [ERROR - Order details - Error while verifing the order] - ",
				OrderDetailsResp.getData().getOrderById() != null);
		System.out.println("  [INFO] [ORERED BY ID] - " + OrderDetailsResp.getData().getOrderById());
		Assert.assertTrue(" [ERROR - Order details not matching  - Product Name]", OrderDetailsResp.getData().getProductName().contains(productName));
		Assert.assertTrue(" [ERROR - Order details not matching  - Product Price]", OrderDetailsResp.getData().getOrderPrice().contains(productPrice));
	}

	@And("Delete the added product from E-commerce service")
	public void delete_the_added_product_from_e_commerce_service() {
		deleteResponce DeleteProductResp = new deleteResponce();
		try {
			Thread.sleep(10000);
			DeleteProductResp = given()
					.spec(DeletedSpecificationswithToken(loginResp.getToken(), CreateProductResp.getProductId())).when()
					.delete("/api/ecom/product/delete-product/{PRODUCTID}").then().extract().as(deleteResponce.class);
		} catch (Exception e) {
			System.out.println("  [INFO] [DELETE ORDER] - " + e.toString());
		}
		Assert.assertTrue(" [ERROR - Delete level - Unable to delete product] ",
				DeleteProductResp.getMessage().contains("Deleted Successfully"));
	}
}
