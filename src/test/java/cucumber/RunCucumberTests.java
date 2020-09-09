package cucumber;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Run all the cucumber tests in the current package.
 * Do not modify this file for your homework
 */
@RunWith(Cucumber.class)
@CucumberOptions(strict = true)
public class RunCucumberTests {

	@BeforeClass
	public static void setup() {
		WebDriverManager.chromedriver().setup();
	}

}
