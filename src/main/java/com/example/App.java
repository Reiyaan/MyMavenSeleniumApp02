package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class App 
{
    public static void main(String[] args) throws InterruptedException
    {
        // Firefox setup (stable)
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new FirefoxDriver(options);

        // ---------- WEBSITE 1 ----------
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        Thread.sleep(2000);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(3000);

        // ---------- WEBSITE 2 ----------
        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://practicetestautomation.com/practice-test-login/");

        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys("student");

        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("Password123");

        Thread.sleep(2000);
        driver.findElement(By.id("submit")).click();

        Thread.sleep(3000);

        // ---------- WEBSITE 3 ----------
        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://automationexercise.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("dismiss-button"))).click();
        } catch (Exception e) {
            System.out.println("No ad appeared");
        }

        WebElement addToCart = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-product-id='2']"))
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[text()='View Cart']")
        )).click();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("dismiss-button"))).click();
        } catch (Exception e) {
            System.out.println("No ad appeared after cart");
        }

        Thread.sleep(3000);

        driver.quit();
    }
}
