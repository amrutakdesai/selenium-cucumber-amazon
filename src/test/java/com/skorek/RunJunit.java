package com.skorek;



//import cucumber.api.CucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="classpath:checkout.feature",
        glue="classpath:com.skorek",
        plugin="html:target/selenium-report",

        dryRun = false,
        monochrome = true,
        publish = true

)
public class RunJunit {
}
