package Test.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Helper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

public class OpenPositionsPage {
    WebDriver driver;
    WebDriverWait wait;
    Helper helper=new Helper();

    public OpenPositionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    By ist = By.xpath("//option[@class='job-location istanbul-turkey']");
    By qa = By.xpath("(//option[@class='job-team qualityassurance'])[1]");
    By viewRoleButton=By.xpath("(//a[normalize-space()='View Role'])[1]");
    By firstJob=By.xpath("/html[1]/body[1]/section[3]/div[1]/div[1]/div[2]/div[1]/div[1]/p[1]");
    public void clickToSelectLocationAndChoose() {
        //Clicks to Location Select item
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='col-12 col-lg-6 form-group d-flex flex-column mb-0'])[1]")));
        WebElement locationDropdown = driver.findElement(By.xpath("(//div[@class='col-12 col-lg-6 form-group d-flex flex-column mb-0'])[1]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper.sleepInSeconds(10);
       locationDropdown.click();
       helper.sleepInSeconds(10);
       driver.findElement(ist).click();
    }
    public void clickToSelectDepartment() {
        //Clicks to Department Select item
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='col-12 col-lg-6 form-group d-flex flex-column mb-0'])[2]")));
        WebElement locationDropdown = driver.findElement(By.xpath("(//div[@class='col-12 col-lg-6 form-group d-flex flex-column mb-0'])[2]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        helper.sleepInSeconds(10);
        Actions actions = new Actions(driver);
        actions.moveToElement(locationDropdown).click().perform();
        helper.sleepInSeconds(3);
        driver.findElement(qa).click();
    }
    public void jobTitleControl(String keywordForTitle1, String keywordForTitle2) {
        //Takes titles as a list and checks if they contain our keywords about the jobs. Keywords are QA and Quality
        List<WebElement> elements = driver.findElements(By.xpath("//p[@class='position-title font-weight-bold']"));

        for (int i = 0; i < elements.size(); i++) {
            String text = elements.get(i).getText();
            System.out.println(text);
            if (text.contains(keywordForTitle1) || text.contains(keywordForTitle2)) {
                System.out.println("Element " + (i + 1) + ": '" + text + "' contains keywords");
            } else {
                fail("Element " + (i + 1) + ": '" + text + "' does not contain keywords");
            }
        }
    }
    public void departmentControl(String departmentKeyword) {
        //Takes departments as a list and checks if they are equal to departmentkeyword. departmenkeyword: Quailty Assurance
        List<WebElement> elements = driver.findElements(By.xpath("//span[@class='position-department text-large font-weight-600 text-primary']"));
        for (int i = 0; i < elements.size(); i++) {
            String text = elements.get(i).getText();
            System.out.println(text);
            if (text.equals(departmentKeyword)) {
                System.out.println("Element " + (i + 1) + ": '" + text + "' Department correct");
            } else {
                System.out.println("Element " + (i + 1) + ": '" + text + "' Department incorrect");
            }
        }

    }
    public void checkThePresenceOfJobs() {
        //Checks if the jobs are exists
        List<WebElement> jobElements = driver.findElements(By.id("jobs-list"));
        if (jobElements.size() > 0) {
            System.out.println("Matching job listings found");
            for (WebElement job : jobElements) {
                System.out.println("Job title: " + job.getText());
            }
        } else {
            fail("No jobs found for the selected criteria.");
        }
    }
    public void clickToViewRoleButton(){
        //Clicks to View role button in the posted one job and scrolls page slightly down
        WebElement l=driver.findElement(By.xpath("(//h3[contains(text(),'Browse')])[1]"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", l);
        helper.sleepInSeconds(1);
        driver.findElement(firstJob).click();
        driver.findElement(viewRoleButton).click();
    }
    public void closeInsiderPageSwitchTab(){
        //When we click the View Role button, it redirects us to the Lever site but insider page keeps open. It closes the Insider tab and keeps the Lever site open.
        Set<String> windowHandles = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<>(windowHandles);
        driver.switchTo().window(handlesList.get(0));
        driver.close();
        driver.switchTo().window(handlesList.get(1));

    }
    public void locationControl(String expectedLocation){
        //It takes Location elements' xpath as a list and checks if they are correct
        List<WebElement> elements = driver.findElements(By.xpath("//div[@class='position-location text-large']"));
        for (int i = 0; i < elements.size(); i++) {
            String text = elements.get(i).getText();
            System.out.println(text);
            if (text.equals(expectedLocation)) {
                System.out.println("Element " + (i + 1) + ": '" + text + "' Location correct");
            } else {
                fail("Element " + (i + 1) + ": '" + text + "' Location incorrect");
            }
        }

    }
}

