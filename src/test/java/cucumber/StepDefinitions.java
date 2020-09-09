package cucumber;


import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	
	private final WebDriver driver = new ChromeDriver();
	
	@Given("I am on the Google homepage")
	public void i_am_on_the_Google_homepage() {
	    driver.get("https://www.google.com");
	}

	@When("I enter {string} into the search box")
	public void i_enter_into_the_search_box(String string) {
		WebElement queryBox = driver.findElement(By.name("q"));
		queryBox.sendKeys(string);
	}

	@When("A click the search button")
	public void a_click_the_search_button() {
	    WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]"));
	    searchButton.click();
	}

	@Then("the first result should be the offical athletics website")
	public void the_first_result_should_be_the_offical_athletics_website() {
		WebElement result = driver.findElement(By.cssSelector("#rso > div:nth-child(1) > link"));
	    assertTrue(result.getAttribute("href").equalsIgnoreCase("https://usctrojans.com/"));
	}
	
	@After()
	public void cleanup() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
