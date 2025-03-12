package com.ECommerce.TestRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/Test - Features/TC02 - Verify all data services wsing GraphQL.feature", glue = {"com.ECommerce.stepdefinition"})
public class TestRunner {
}
