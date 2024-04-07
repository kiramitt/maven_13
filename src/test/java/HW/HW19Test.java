package HW;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.FirstBaseTest;

import java.time.Duration;

public class HW19Test extends FirstBaseTest {
    @Test
    public void jenkinsTest() {
        String jobName = "Test Job";
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        getDriver().get("http://localhost:8080/");
        getDriver().findElement(By.id("j_username")).sendKeys("admin");
        getDriver().findElement(By.id("j_password")).sendKeys("admin");
        getDriver().findElement(By.name("Submit")).click();

        try {
            getDriver().findElement(By.linkText("New Item")).click();
        } catch (Exception e) {
            getDriver().findElement(By.linkText("Create a job")).click();
        }
        getDriver().findElement(By.id("name")).sendKeys(jobName);
        getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
        getDriver().findElement(By.id("ok-button")).click();

        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(jobName, getDriver().findElement(By.className("job-index-headline")).getText());
    }
}
