package Jenkins;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.NoSuchElementException;
import java.util.UUID;


public class FreestyleProjectTest extends BaseTest {
    final String projectName = "Freestyle-" + UUID.randomUUID();
    final String folderName = "Folder-" + UUID.randomUUID();

    public void createItem(String itemName, String item) {
            getDriver().findElement(By.linkText("New Item")).click();

        getDriver().findElement(By.id("name")).sendKeys(itemName);
        String itemClassName = switch (item) {
            case "Freestyle project" -> "hudson_model_FreeStyleProject";
            case "Folder" -> "com_cloudbees_hudson_plugins_folder_Folder";
            default -> "";
        };
        getDriver().findElement(By.className(itemClassName)).click();
        Assert. assertEquals(getDriver().findElement(By.className(itemClassName)).getAttribute("aria-checked"),
                "true", item + "is not checked");

        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/job/"+itemName+"/",
                item + "is not created");
        getDriver().findElement(By.linkText("Dashboard")).click();
    }
    @Test
    public void testCreateFreestyleProject() {
//        US_00.001 | New Item > Create Freestyle Project
        createItem(projectName,"Freestyle project");

        getDriver().findElement(By.linkText(projectName)).click();

        getDriver().findElement(By.linkText("Configure")).click();

        Assert.assertEquals(getDriver().findElement(By.cssSelector("#breadcrumbs > li:nth-child(3)")).getText(),
                projectName, "Wrong project is opened");
    }
    @Test
    public void testAddDescription() {
//    US_01.001 | Freestyle project > Add description
        createItem(projectName,"Freestyle project");
        getDriver().findElement(By.linkText(projectName)).click();

        getDriver().findElement(By.linkText("Add description")).click();
        getDriver().findElement(By.name("description")).clear();
        getDriver().findElement(By.name("description")).sendKeys("Description for "+projectName);
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='description']/div[1]")).getText(),
                "Description for "+projectName);
    }
    @Test
    public void testMoveToFolder() {
//    US_01.006 | Freestyle project > Move to Folder
        createItem(projectName,"Freestyle project");
        createItem(folderName,"Folder");

        getDriver().findElement(By.linkText(projectName)).click();
        getDriver().findElement(By.linkText("Move")).click();

        Select select = new Select(getDriver().findElement(By.className("select")));
        select.selectByValue("/"+folderName);
        getDriver().findElement(By.name("Submit")).click();
        Assert.assertTrue(getDriver().findElement(By.id("main-panel")).getText().contains(
                "Full project name: "+folderName+"/"+projectName));
        getDriver().findElement(By.linkText("Dashboard")).click();

        getDriver().findElement(By.linkText(folderName)).click();
        try {
            getDriver().findElement(By.linkText(projectName));
            Assert.assertTrue(true);
        } catch (NoSuchElementException e) {
            Assert.fail("Freestyle project not found in folder");
        }
    }
    @Test
    public void testRenameProject() {
//    US_01.002 | Freestyle project > Rename project
        createItem(projectName,"Freestyle project");
        getDriver().findElement(By.linkText(projectName)).click();

        getDriver().findElement(By.linkText("Rename")).click();
        getDriver().findElement(By.name("newName")).clear();
        getDriver().findElement(By.name("newName")).sendKeys("New" + projectName);
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.className("job-index-headline")).getText(), "New" + projectName);
    }
    @Test
    public void testDeleteProject() {
//    US_01.004 | Freestyle project > Delete project
        createItem(projectName,"Freestyle project");
        getDriver().findElement(By.linkText(projectName)).click();

        getDriver().findElement(By.linkText("Delete Project")).click();
        getDriver().findElement(By.className("jenkins-!-destructive-color")).click();

        getDriver().get("http://localhost:8080/job/"+projectName);
        Assert.assertEquals(getDriver().findElement(By.cssSelector("#error-description > h2")).getText(), "Not Found");
    }

}
