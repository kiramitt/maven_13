package Lessons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class Lesson21_Test extends BaseTest {
    @Test
    public void jenkinsTest() {
        WebElement welcomeText = getDriver().findElement(By.cssSelector(".empty-state-block > h1"));
        Assert.assertEquals(welcomeText.getText(), "Welcome to Jenkins!");
    }
}
