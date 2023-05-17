package com.example.testbing.tests;

import com.example.testbing.pages.MainPage;
import com.example.testbing.pages.ResultsPage;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;

import java.time.Duration;
import java.util.List;

public class BingSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1600, 900));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.bing.com/");

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void searchResultTest() {
        String input = "QA auto";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);

        ResultsPage rp = new ResultsPage(driver);

        assertEquals(input, rp.getTextFromSearchField(), "текст не совпал");
    }

    @Test
    public void TestResultsField() {
        String input = "Selenium";
        MainPage mp = new MainPage(driver);
        mp.sendText(input);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.and(
                ExpectedConditions.attributeContains(By.cssSelector("h2 > a[href]"), "href", "selenium"),
                ExpectedConditions.elementToBeClickable(By.cssSelector("h2 > a[href]"))
        ));

        ResultsPage rp = new ResultsPage(driver);
        rp.clickElement(0);
        ArrayList<String> tabs = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        WebDriverWait waitTwo = new WebDriverWait(driver, Duration.ofSeconds(3));
        waitTwo.until(ExpectedConditions.urlContains("selenium.dev"));
        String expected = "https://www.selenium.dev/";
        assertEquals(expected, driver.getCurrentUrl());

    }
}
