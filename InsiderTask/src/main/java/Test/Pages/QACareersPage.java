package Test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class QACareersPage {
    WebDriver driver;
    WebDriverWait wait;
    public QACareersPage(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    By seeAllJobsButton= By.xpath("(//a[normalize-space()='See all QA jobs'])[1]");
    By qaTitle=By.xpath("(//h1)[1]");
    public String getHeaderText() {
        //Gets header's text
        return driver.findElement(qaTitle).getText().trim();
    }
    public void clickToSeeAllJobsButton(){
        //Clicks to see all jobs button
        driver.findElement(seeAllJobsButton).click();
    }
}
