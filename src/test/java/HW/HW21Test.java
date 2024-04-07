package HW;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
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
        String itemClassName = "";
        switch (item) {
            case "Freestyle project":
                itemClassName = "hudson_model_FreeStyleProject";
                break;
            case "Pipeline":
                itemClassName = "org_jenkinsci_plugins_workflow_job_WorkflowJob";
                break;
            case "Multi-configuration project":
                itemClassName = "hudson_matrix_MatrixProject";
                break;
            case "Folder":
                itemClassName = "com_cloudbees_hudson_plugins_folder_Folder";
                break;
            case "Multibranch Pipeline":
                itemClassName = "org_jenkinsci_plugins_workflow_multibranch_WorkflowMultiBranchProject";
                break;
            case "Organization Folder":
                itemClassName = "jenkins_branch_OrganizationFolder";
                break;
        }
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

        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(By.cssSelector("tr[id='job_"+projectName+"'] a"))).build().perform();
//        getDriver().findElement(By.cssSelector("tr[id='job_"+projectName+"'] a")).click();
        getDriver().findElement(By.cssSelector("button.jenkins-menu-dropdown-chevron[data-href='http://localhost:8080/job/"+projectName+"/']")).click();
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
