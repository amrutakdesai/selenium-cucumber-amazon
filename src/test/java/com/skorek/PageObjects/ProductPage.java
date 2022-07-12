package com.skorek.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class ProductPage extends PageObject {
    WebDriverWait wait;
    public ProductPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[@id='productTitle']")
    private WebElement productNameElement;

    @FindBy(xpath = "//span[@class='a-price aok-align-center']//span[@class='a-price-whole']")
    private WebElement productPriceElement;

    @FindBy(id = "quantity")
    private WebElement quantityMenu;

    @FindBy(xpath = "//input[@id='add-to-cart-button']")
    private WebElement addToChartButton;

    @FindBy(xpath = "//*[@id=\"siNoCoverage-announce\"]")
    private WebElement noCoverangeButton;

    @FindBy(xpath = "//a[@id=\"hlb-ptc-btn-native\"]")
    private WebElement proceedToCheckoutButton;

    public String getProductName(){
        return productNameElement.getText();
    }

    public BigDecimal getProductPrice()
    {
        String product_price_text = productPriceElement.getText();
        System.out.println(product_price_text);
        product_price_text = product_price_text.replace("$", "").replace(",", "");
        Double priceInDouble = Double.parseDouble(product_price_text);
        return new BigDecimal(priceInDouble).setScale(2);
    }

    public void setQuantity(int quantity) throws InterruptedException {
        Select quantitySelect = new Select(quantityMenu);
        quantitySelect.selectByValue(Integer.toString(quantity));
    }

    public void clickAddToChartAndDeclineCoverage() throws InterruptedException {
        addToChartButton.click();
        Thread.sleep(1000);  //ugly hack but nothing reasonable works
         wait = new WebDriverWait(driver, 2000);
        try
        {
            wait.until(ExpectedConditions.visibilityOf(noCoverangeButton));
            noCoverangeButton.click();
        }
        catch (org.openqa.selenium.TimeoutException e)
        {
            driver.findElement(By.xpath("//*[@id='a-popover-6']/div/div[1]/button")).click();
        }
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutButton));
            }

    public CheckoutPage goToCheckout(){
        driver.get("https://www.amazon.com/gp/cart/view.html/ref=nav_cart");
        return new CheckoutPage(driver);
    }




}
