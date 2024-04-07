package HW;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.NoSuchElementException;
import java.util.UUID;


public class HW21Test extends BaseTest {

    public void createItem(String itemName, String item) {
        try {
            getDriver().findElement(By.linkText("New Item")).click();
        } catch (NoSuchElementException e) {
            getDriver().findElement(By.linkText("Create a job")).click();
        }
        getDriver().findElement(By.id("name")).sendKeys(itemName);
        switch (item) {
            case "Freestyle project":
                getDriver().findElement(By.className("hudson_model_FreeStyleProject")).click();
                break;
            case "Pipeline":
                getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
                break;
            case "Multi-configuration project":
                getDriver().findElement(By.className("hudson_matrix_MatrixProject")).click();
                break;
            case "Folder":
                getDriver().findElement(By.className("com_cloudbees_hudson_plugins_folder_Folder")).click();
                break;
            case "Multibranch Pipeline":
                getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject")).click();
                break;
            case "Organization Folder":
                getDriver().findElement(By.className("jenkins_branch_OrganizationFolder")).click();
                break;
            default:
        }
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();
        Assert.assertEquals(getDriver().getCurrentUrl(), String.format("http://localhost:8080/job/%s/",itemName), item + "don`t create");
        getDriver().findElement(By.linkText("Dashboard")).click();
    }


    @Test
    public void US_01_01_006() {
    // https://trello.com/c/KjucoLou/37-us0101006-freestyleproject-move-to-folder
    // US_01.01.006 | FreestyleProject > Move to Folder
    // As a User, I want to move freestyle project to folder, so that I can organize a hierarchical directory structure,
    // and easily find and manage groups of projects.
    // Ensure the User able to:
    // choose freestyle project and folder in Dashboard or create new freestyle project and folder in New Item
    // find freestyle project and folder in Dashboard
    // move freestyle project to folder with "Move" option
    // find folder on Dashboard
    // find a moved freestyle project inside folder

        String projectName = "Freestyle-" + UUID.randomUUID();
        createItem(projectName,"Freestyle project");
        String folderName = "Folder-" + UUID.randomUUID();
        createItem(folderName,"Folder");

        getDriver().findElement(By.cssSelector("tr[id='job_"+projectName+"'] a")).click();
        getDriver().findElement(By.xpath("//a[contains(., 'Move')]")).click();

        Select select = new Select(getDriver().findElement(By.className("select")));
        select.selectByValue("/"+folderName);
        getDriver().findElement(By.name("Submit")).click();
        Assert.assertTrue(getDriver().findElement(By.id("main-panel")).getText().contains("Full project name: "+folderName+"/"+projectName));
        getDriver().findElement(By.linkText("Dashboard")).click();

        getDriver().findElement(By.cssSelector("tr[id='job_"+folderName+"'] a")).click();
        try {
            getDriver().findElement(By.cssSelector("tr[id='job_"+projectName+"'] a"));
            Assert.assertTrue(true);
        } catch (NoSuchElementException e) {
            Assert.fail("Freestyle project not found in folder");
        }
    }
}
