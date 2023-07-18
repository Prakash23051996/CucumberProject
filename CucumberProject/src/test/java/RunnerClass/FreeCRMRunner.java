package RunnerClass;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/FeatureFile/FreeCRM.feature", glue = { "StepDefinition",
		"HooksClass" }, plugin = { "pretty", "html:target/cucumber-reports" }, dryRun = false, monochrome = true)
public class FreeCRMRunner {

	
	
}
