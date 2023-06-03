package com.example.testbing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;



public class ResultsPage {
    private WebDriver driver;

    @FindBy(css = "#sb_form_q")
    private WebElement searchField;

    @FindBy(css = "h2 > a[href]")
    private List <WebElement> results;

    public String getTextFromSearchField(){
    String val = searchField.getAttribute("value");
    System.out.println("В строке поиска текста: " + val);
    return val;
    }
    public void goToUrl(int num, String urlText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains(results.get(num), "href", urlText),
                ExpectedConditions.elementToBeClickable(results.get(num))
        ));
        results.get(num).click();
        System.out.println("Переход по ссылке результата " + num);
    }

    public String getCurrentUrlOfResult(String partOfUrl){
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        WebDriverWait waitTwo = new WebDriverWait(driver, Duration.ofSeconds(3));
        waitTwo.until(ExpectedConditions.urlContains(partOfUrl));
        return driver.getCurrentUrl();
    }

    public ResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}