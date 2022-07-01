package com.skorek.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class CheckoutPage extends PageObject {
    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"sc-subtotal-amount-activecart\"]/span")
    private WebElement totalPriceElement;

    public BigDecimal getTotalPrice(){
        String total_price_text = totalPriceElement.getText();
        total_price_text = total_price_text.replace("$", "").replace(",", "");
        Double total_price_in_double = Double.parseDouble(total_price_text);
        return new BigDecimal(total_price_in_double).setScale(2);
    }

    public boolean containsString(String product_name) {
        return driver.getPageSource().contains(product_name);
    }
}
