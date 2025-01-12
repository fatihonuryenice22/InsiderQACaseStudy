package Test.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class MainPage {
    //Driver settings
    WebDriver driver;
    WebDriverWait wait;
    public MainPage(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    By acceptCookiesButton= By.id("wt-cli-accept-all-btn");
    By insiderLogo=By.xpath("//img[@alt='insider_logo']");
    By companyNavBar=By.xpath("//a[normalize-space()='Company']");
    By aboutUsDropdown=By.xpath("//a[@class='dropdown-sub'][normalize-space()='About Us']");
    By newsRoomDropDown=By.xpath("//a[@class='dropdown-sub'][normalize-space()='Newsroom']");
    By partnershipsDropDown=By.xpath("//a[@class='dropdown-sub'][normalize-space()='Partnerships']");
    By integrationsDropDown=By.xpath("//a[@class='dropdown-sub'][normalize-space()='Integrations']");
    By contactUsDropDown=By.xpath("//a[@class='dropdown-sub'][normalize-space()='Contact Us']");
    By careersDropdown=By.xpath("//a[normalize-space()='Careers']");
    public void checkIfPageIsOpenedOrNot(){
       // Checks the logo if page is opened
       WebElement insiderLogoAttop= wait.until(ExpectedConditions.visibilityOfElementLocated(insiderLogo));
       if (insiderLogoAttop.isDisplayed()){System.out.println("Logo Visible");}else{fail("Logo invisible");}
   }
    public void acceptCookies(){
       //Accept cookies
        driver.findElement(acceptCookiesButton).click();
    }
    public void navigateToCompanySection(){
       //Clicks to Company Navbar
       driver.findElement(companyNavBar).click();
    }
    public void validateCompanyMenu(){
       //Validating company dropdown menu at top
        List<By> dropdowns = Arrays.asList(
                careersDropdown,
                aboutUsDropdown,
                contactUsDropDown,
                integrationsDropDown,
                partnershipsDropDown,
                newsRoomDropDown
        );
        List<String> expectedTexts = Arrays.asList(
                "Careers",
                "About Us",
                "Contact Us",
                "Integrations",
                "Partnerships",
                "Newsroom"
        );
        for (int i = 0; i < dropdowns.size(); i++) {
            try {
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdowns.get(i)));
                String actualText = element.getText().trim();

                if (element.isDisplayed() && actualText.equals(expectedTexts.get(i))) {
                    System.out.println("'" + expectedTexts.get(i) + "' Text is correct.");
                } else {
                    System.err.println("'" + expectedTexts.get(i) + "' Text is different " +
                            "Expected: '" + expectedTexts.get(i) + "', Found: '" + actualText + "'");
                }
            } catch (TimeoutException e) {
                System.err.println("'" + expectedTexts.get(i) + "' Couldn't find the dropdown");
            }
        }
    }
    public void clickToCareersNavMenuItem(){
       //Clicks to careers
       driver.findElement(careersDropdown).click();
    }
}
