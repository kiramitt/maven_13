package HW;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

import runner.FirstBaseTest;


public class HW15Test extends FirstBaseTest {
//    Задача
//    Необходимо написать любой тест на Selenium, можете использовать любой сайт (лучше не использовать сайт крупных
//    компаний, они будут защищаться от ваших тестов). Без разницы какой тест, главная задача это начать
//    практиковаться в написании Selenium кода.

    @Test
    public void kmFirstTest() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        getDriver().get("https://automationintesting.online");
        getDriver().findElement(By.id("name")).sendKeys("John Doe");
        getDriver().findElement(By.id("email")).sendKeys("johndoe@gmail.com");
        getDriver().findElement(By.id("phone")).sendKeys("12345678900");
        getDriver().findElement(By.id("subject")).sendKeys("Test Subject");
        getDriver().findElement(By.id("description")).sendKeys("Hello World Hello World");
        getDriver().findElement(By.id("submitContact")).click();

        Assert.assertEquals("Thanks for getting in touch John Doe!", getDriver().findElement(By.cssSelector(
                "#root > div > div > div.row.contact > div:nth-child(2) > div > h2")).getText());
    }
}