package Lessons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Lesson15_Test {
    @Test
    public void test() {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        String title = driver.getTitle();
        Assert.assertEquals("Web form", title);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        textBox.sendKeys("Selenium");
        submitButton.click();

        WebElement message = driver.findElement(By.id("message"));
        message.getText();

        driver.quit();
    }
    @Test
    public void testGoogle() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        WebElement text = driver.findElement(By.id("APjFqb"));
        text.sendKeys("Selenium");

        Thread.sleep(1000); // make it 500

        WebElement button = driver.findElement(By.className("gNO89b"));
        button.click();

        WebElement link = driver.findElement(By.xpath("//a[@href = 'https://www.selenium.dev/']/h3"));
        String resultText = link.getText();

        Assert.assertEquals(resultText, "Selenium");
    }
}
