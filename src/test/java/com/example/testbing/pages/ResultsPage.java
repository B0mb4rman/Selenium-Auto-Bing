package com.example.testbing.pages;

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
    public void clickElement(int num) {
        results.get(num).click();
        System.out.println("Нажатие на результат " + num);
    }

    public ResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}