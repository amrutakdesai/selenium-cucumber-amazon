package com.skorek;

import com.skorek.PageObjects.CheckoutPage;
import com.skorek.PageObjects.MainPage;
import com.skorek.PageObjects.ProductCategoryPage;
import com.skorek.PageObjects.ProductPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class StepDefinition {
    private static WebDriver driver;

    private String product_name;
    private BigDecimal product_price;
    private int number_of_products;

    private MainPage mainPage;
    private ProductCategoryPage productCategoryPage;
    private ProductPage productPage;
    private CheckoutPage checkoutPage;

    private static void setUpWebdriverVariables()
    {
        if (SystemUtils.IS_OS_LINUX)
        {
//            File f = new File("WebDriver/Linux/chromedriver");
//            System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
            WebDriverManager.chromedriver().setup();

        }
        if (SystemUtils.IS_OS_WINDOWS) {
           // File f = new File("WebDriver/Windows/chromedriver.exe");
           // System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
            WebDriverManager.chromedriver().setup();
        }
        if (SystemUtils.IS_OS_MAC) {
//            File f = new File("WebDriver/MacOS/chromedriver");
//            System.setProperty("webdriver.chrome.driver", f.getAbsolutePath());
            WebDriverManager.chromedriver().setup();
        }
    }

    @Before
    public static void SetUp(){
        setUpWebdriverVariables();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public static void cleanUp(Scenario s){

        if (s.isFailed()) {
            TakesScreenshot scrnShot = (TakesScreenshot)driver;
            byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
            s.attach(data, "image/png","Failed Step Name: " + s.getName());
        }
        else{
            s.log("Test passed");
        }

        driver.close();
        driver.quit();
    }


    @Given("^I am on Amazon page$")
    public void i_am_on_Amazon_page() {
        driver.get("http://www.amazon.com");
        mainPage = new MainPage(driver);
    }

    @And("^I go to search \"([^\"]*)\"$")
    public void iGoToCategory(String category) {
        productCategoryPage = mainPage.goToProductCategory(category);
    }

    @When("^I select position (\\d+)$")
    public void i_select_position(int position) {

        productPage = productCategoryPage.goToProductOnPosition(position);
        product_price = productPage.getProductPrice();
        product_name = productPage.getProductName();
    }

    @When("^I add it to shopping chart in quantity of (\\d+)$")
    public void i_add_it_to_shopping_chart_in_quantity_of(int quantity) throws InterruptedException {
        number_of_products = quantity;

        productPage.setQuantity(number_of_products);
        productPage.clickAddToChartAndDeclineCoverage();
    }

    @When("^I go to checkout$")
    public void i_go_to_checkout()  {
        checkoutPage = productPage.goToCheckout();
    }

    @Then("^I should see proper product name on item list$")
    public void i_should_see_proper_product_name_on_item_list() {
        assert checkoutPage.containsString(product_name);
    }

    @Then("^I sould see proper final price$")
    public void i_sould_see_proper_final_price() {

        BigDecimal multiply_price = product_price.multiply(new BigDecimal(number_of_products));
        BigDecimal checkoutPrice = checkoutPage.getTotalPrice();

        assert multiply_price.compareTo(checkoutPrice) == 0;
    }
   }
