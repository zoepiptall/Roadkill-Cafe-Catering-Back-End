package com.project2.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {"features/Login.feature"},
		glue= {"com.project2.gluecode"}
		)

public class TestRunner {

}
