package com.skorek;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:checkout.feature",
        glue="classpath:com.skorek",
        plugin="html:target/selenium-report"

)
public class RunJunit {
}
