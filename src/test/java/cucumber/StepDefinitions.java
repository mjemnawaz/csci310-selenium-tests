package cucumber;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for Cucumber tests.
 */
public class StepDefinitions {
	
	private final WebDriver driver = new ChromeDriver();
	private String productID = "";
	
	@Given("I am on the home page")
	public void i_am_on_the_home_page() {
	    driver.get("https://www.youtube.com/");
	    driver.manage().window().maximize();
	}
	
	@Given("there is a video on the home page")
	public void there_is_a_video_on_the_home_page() {
		assertTrue("no videos on home page",driver.findElements(By.cssSelector(
				".ytd-rich-grid-renderer:nth-child(1) #details , .ytd-rich-grid-renderer:nth-child(1) #meta")).size()>0);
	}
	
	@When("I click on a video's title or thumbnail")
	public void i_click_on_a_video_s_title_or_thumbnail() {
		driver.findElement(By.cssSelector(
				".ytd-rich-grid-renderer:nth-child(1) #details , .ytd-rich-grid-renderer:nth-child(1) #meta")).click();
	}
	
	@Then("the video should open and start playing")
	public void the_video_should_open_and_start_playing() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#movie_player")));
		assertTrue("Could not find video",driver.findElements(By.cssSelector("#movie_player")).size()>0);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String s = driver.findElement(By.cssSelector(".ytp-play-button")).getAttribute("title");
		assertTrue("Could not verify video is playing: "+ s,s.equals("Pause (k)"));
	}

	@Given("I have a video open")
	public void i_have_a_video_open() {
	    i_am_on_the_home_page();
	    there_is_a_video_on_the_home_page();
	    i_click_on_a_video_s_title_or_thumbnail();
	    WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#movie_player")));
		assertTrue("Could not find video",driver.findElements(By.cssSelector("#movie_player")).size()>0);
	}
	
	@Given("the video is playing")
	public void the_video_is_playing() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String s = driver.findElement(By.cssSelector(".ytp-play-button")).getAttribute("title");
		if (s.equals("Play (k)")) driver.findElement(By.cssSelector(".ytp-play-button")).click();
	}
	
	@When("I click on the video")
	public void i_click_on_the_video() {
		driver.findElement(By.cssSelector(".ytp-play-button")).click();
	}
	
	@Then("the video should pause")
	public void the_video_should_pause() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String s = driver.findElement(By.cssSelector(".ytp-play-button")).getAttribute("aria-label");
		assertTrue("Could not verify video is paused: "+ s,s.equals("Play (k)"));
	}
	
	@Given("the video is paused")
	public void the_video_is_paused() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String s = driver.findElement(By.cssSelector(".ytp-play-button")).getAttribute("aria-label");
		if (s.equals("Pause (k)")) driver.findElement(By.cssSelector(".ytp-play-button")).click();
	}
	
	@Then("the video should play")
	public void the_video_should_play() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String s = driver.findElement(By.cssSelector(".ytp-play-button")).getAttribute("aria-label");
		assertTrue("Could not verify video is playing: "+ s,s.equals("Pause (k)"));
	}
	
	@Given("I am on new products page")
	public void i_am_on_new_products_page() {
	    driver.get("https://jolse.com/category/face-brush/1231/");
	    driver.manage().window().maximize();
	}
	@Given("there is a product listing")
	public void there_is_a_product_listing() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".box")));
	    assertTrue("Cannot locate product listings",driver.findElements(By.cssSelector(".box")).size() > 0);
	}
	@When("I hover over the product listing")
	public void i_hover_over_the_product_listing() {
		Actions actions = new Actions(driver);
		WebElement productListing = driver.findElement(By.cssSelector(".box"));
		actions.moveToElement(productListing);
	}
	@When("I click on the cart icon")
	public void i_click_on_the_cart_icon() {
		String s = 
				driver.findElement(By.cssSelector(".name > a")).getAttribute("href");
		productID = s.substring(s.indexOf('=')+1,s.indexOf('=')+6);
	    driver.findElement(By.cssSelector(".cart")).click();
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@Then("the product should be added to cart")
	public void the_product_should_be_added_to_cart() {
		driver.get("https://jolse.com/order/basket.html");
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".xans-order-list .product")));
		String s = driver.findElement(By.cssSelector(".xans-order-list .product > a")).getAttribute("href");
		s = s.substring(s.indexOf('=')+1,s.indexOf('=')+6);
		assertTrue("got " + s + ", should be " + productID,s.equals(productID));
	}
	@Given("I am on the cart page")
	public void i_am_on_the_cart_page() {
		i_am_on_new_products_page();
		there_is_a_product_listing();
		i_hover_over_the_product_listing();
		i_click_on_the_cart_icon();
		driver.get("https://jolse.com/order/basket.html");
		driver.manage().window().maximize();
	}
	@Given("there is a product in cart")
	public void there_is_a_product_in_cart() {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".xans-order-list .product")));
		assertTrue("No products in cart.",driver.findElements(By.cssSelector(".xans-order-list .product")).size()>0);
		String s = driver.findElement(By.cssSelector(".xans-order-list .product > a")).getAttribute("href");
		productID = s.substring(s.indexOf('=')+1,s.indexOf('=')+6);
	}
	@When("I click on the {string} button by the product")
	public void i_click_on_the_button_by_the_product(String string) {
		driver.findElement(By.cssSelector(".mT3~ .mT3+ .mT3")).click();
	}
	@When("I click the {string} confirmation button")
	public void i_click_the_confirmation_button(String string) {
		driver.switchTo().alert().accept();
	}
	@Then("the product should be removed from the cart")
	public void the_product_should_be_removed_from_the_cart() {
		if (driver.findElements(By.cssSelector(".xans-order-list .product")).size()>1) {
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".xans-order-list .product")));
			String s = driver.findElement(By.cssSelector(".xans-order-list .product > a")).getAttribute("href");
			assertTrue("The product is still in the cart.",!productID.equals(s.substring(s.indexOf('=')+1,s.indexOf('=')+6)));
		}
	}
	
	@Given("that I am on the login page")
	public void that_I_am_on_the_login_page() {
	    driver.get("https://github.com/login");
	    driver.manage().window().maximize();
	}
	@When("I enter a valid github username into the {string} field")
	public void i_enter_a_valid_github_username_into_the_field(String string) {
	    driver.findElement(By.cssSelector("#login_field")).sendKeys("310project7username@gmail.com");
	}
	@When("I enter the valid corresponding address into the {string} field")
	public void i_enter_the_valid_corresponding_address_into_the_field(String string) {
		driver.findElement(By.cssSelector("#password")).sendKeys("310project7password");
	}
	@When("I click the {string} button")
	public void i_click_the_button(String string) {
	    driver.findElement(By.cssSelector(".btn-block")).click();
	}
	@Then("I should sign in to a valid pre-existing account")
	public void i_should_sign_in_to_a_valid_pre_existing_account() {
	    assertTrue("Not signed in!",driver.findElements(By.cssSelector(".avatar-user")).size()>0);
	}
	@Then("I should see an error message displaying {string}")
	public void i_should_see_an_error_message_displaying(String string) {
	    assertTrue("No error message!",
	    		driver.findElement(By.cssSelector("#js-flash-container > div > div")).getText().equals(string));
	}
	@Given("I am on the calculator page")
	public void i_am_on_the_calculator_page() {
	    driver.get("https://www.calculatorsoup.com/calculators/math/basic.php");
	    driver.manage().window().maximize();
	}
	@When("I click a number {int}")
	public void i_click_a_number(Integer int1) {
	    if (int1==1) driver.findElement(By.cssSelector("input[name=\"one\"]")).click();
	}
	@When("I click the addition symbol")
	public void i_click_the_addition_symbol() {
		driver.findElement(By.cssSelector("input[name=\"add\"]")).click();
	}
	@Then("I should see the number {int}")
	public void i_should_see_the_number(Integer int1) {
	    driver.findElement(By.cssSelector("input[name=\"calculate\"]")).click();
		assertTrue("incorrect number: " + driver.findElement(By.cssSelector("#display")).getAttribute("value"),
				driver.findElement(By.cssSelector("#display")).getAttribute("value").contentEquals(Integer.toString(int1)));
	}
	@When("I click the subtraction symbol")
	public void i_click_the_subtraction_symbol() {
		driver.findElement(By.cssSelector("input[name=\"subtract\"]")).click();
	}
	
	@Given("I am on the home page of allrecipes.com")
	public void i_am_on_the_home_page_of_allrecipes_com() {
	    driver.get("https://www.allrecipes.com");
	    driver.manage().window().maximize();
	}
	@When("I navigate to the recipes page")
	public void i_navigate_to_the_recipes_page() {
	    driver.get("https://www.allrecipes.com/recipes/");
	}
	@When("I enter {string} into the search bar")
	public void i_enter_into_the_search_bar(String string) {
	    driver.findElement(By.cssSelector("#primary-search")).sendKeys(string);
	}
	@When("I click Enter")
	public void i_click_Enter() {
		driver.findElement(By.cssSelector("#primary-search")).sendKeys(Keys.ENTER);
	}
	@Then("I should see a card for {string} recipes")
	public void i_should_see_a_card_for_recipes(String string) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".grid-col__h3")));
	    assertTrue("Wrong return, should be spaghetti, is: " + driver.findElement(By.cssSelector(".grid-col__h3")).getText(),
	    		driver.findElement(By.cssSelector(".grid-col__h3")).getText().contentEquals("Spaghetti"));
	}
	@Given("I am on the search results page")
	public void i_am_on_the_search_results_page() {
	    driver.get("https://www.allrecipes.com/search/results/?wt=spaghetti");
	    driver.manage().window().maximize();
	}
	@When("I click the Edit button")
	public void i_click_the_Edit_button() {
	    driver.findElement(By.cssSelector(".edit-search-button")).click();
	}
	@When("I enter {string} into the {string} bar")
	public void i_enter_into_the_bar(String string, String string2) {
	    driver.findElement(By.cssSelector("#includeIngText")).sendKeys(string);
	}
	@When("I click on Enter")
	public void i_click_on_Enter() {
		driver.findElement(By.cssSelector("#includeIngText")).sendKeys(Keys.ENTER);
	}
	@Then("I should see {string} at the top of the search results")
	public void i_should_see_at_the_top_of_the_search_results(String string) {
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".search-results__text--include")));
	    assertTrue("do not see " + string + "at the top: " + driver.findElement(By.cssSelector(".search-results__text--include")).getText(),
	    		driver.findElement(By.cssSelector(".search-results__text--include")).getText().equals(string));
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
