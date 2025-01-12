package Test.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Helper;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.fail;

public class CareersPage {
    //Driver Settings
    WebDriver driver;
    WebDriverWait wait;
    Helper helper= new Helper();

    public CareersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    By seeAllTeamItem = By.xpath("//a[normalize-space()='See all teams']");
    By locationNextItem=By.xpath("//i[@class='icon-arrow-right location-slider-next ml-4 text-xsmall text-dark']");
    By lifeAtInsiderContent=By.xpath("/html[1]/body[1]/div[2]/section[4]/div[1]/div[1]/div[1]/div[2]/div[1]/p[1]");
    By QA=By.xpath("//h3[normalize-space()='Quality Assurance']");
    public void scrollUntilSeeAllTeamsItemAndClick() {
        //Clicks to see all teams button
        WebElement element = driver.findElement(By.xpath("(//h3[normalize-space()='Sales'])"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        helper.sleepInSeconds(3);
        driver.findElement(seeAllTeamItem).click();
    }
    public void validateTeams() {
        //Checks all departments one by one
        Logger logger = Logger.getLogger(MainPage.class.getName());
        for (int i = 1; i <= 29; i += 2) {
            By dynamicTeamElement = By.cssSelector("body > div:nth-child(6) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > section:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(" + i + ") > div:nth-child(2) > a:nth-child(1) > h3:nth-child(1)");
            WebElement h3element = wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicTeamElement));
            String innerText = h3element.getText().trim();
            logger.info("corrected text (i=" + i + "): " + innerText);
            switch (i) {
                case 1:
                    if (innerText.equals("Customer Success")){ break;} else { fail(innerText+" Can not found");}

                    break;
                case 3:
                    if (innerText.equals("Sales")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 5:
                    if (innerText.equals("Product & Engineering")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 7:
                    if (innerText.equals("Finance & Business Support")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 9:
                    if (innerText.equals("Marketing")){ break;} else { fail(innerText+"Can not found");}

                    break;
                case 11:
                    if (innerText.equals("CEO’s Executive Office")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 13:
                    if (innerText.equals("Purchasing & Operations")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 15:
                    if (innerText.equals("People and Culture")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 17:
                    if (innerText.equals("Business Intelligence")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 19:
                    if (innerText.equals("Security Engineering")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 21:
                    if (innerText.equals("Partnership")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 23:
                    if (innerText.equals("Quality Assurance")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 25:
                    if (innerText.equals("Mobile Business Unit")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 27:
                    if (innerText.equals("Partner Support Development")){ break;} else { fail(innerText+"Can not found");}
                    break;
                case 29:
                    if (innerText.equals("Product Design")){ break;} else { fail(innerText+"Can not found");}
                    break;
            }
        }
    }
    public void validateLocations() {
        //It checks all locations by pressing the right arrow key once at a time to ensure other elements become visible.
        Logger logger = Logger.getLogger(MainPage.class.getName());
        List<String> cities = Arrays.asList("New York US", "Sao Paulo Brazil", "London United Kingdom", "Paris France", "Amsterdam Netherlands", "Helsinki Finland", "Warsaw Poland", "Sydney Australia", "Dubai United Arab Emirates", "Tokyo Japan", "Seoul Korea", "Singapore Singapore", "Bangkok Thailand", "Jakarta Indonesia", "Taipei Taiwan", "Manila Philippines", "Kuala Lumpur Malaysia", "Ho Chi Minh City Vietnam", "Istanbul Turkey", "Ankara Turkey", "Mexico City Mexico", "Lima Peru", "Buenos Aires Argentina", "Bogota Colombia", "Santiago Chile");
        for (int i = 1; i <= cities.size(); i++) {
            By cityLocator = By.xpath("//ul[@class='glide__slides']/li[" + i + "]");
            try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(cityLocator));
            }catch (Exception e){
                driver.findElement(locationNextItem).click();
            }
            WebElement cityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cityLocator));
            //Formats the city text sometimes it was getting texts like NEW YOK 08:08 we trim the other things and focused to city and country
            String actualCity = cityElement.getText().replace("\n", " ").replaceAll("\\s+", " ").replaceAll("\\b\\d{1,2}:\\d{2}(?::\\d{2})?\\b", "").trim();
            logger.info("Verified element (i=" + i + "): " + actualCity);
            if (cities.get(i-1).equals(actualCity)){
                System.out.println("City correct :"+actualCity);
            }else {
                fail("City is not correct :"+actualCity);
            }
            if (i==1){

                WebElement l=driver.findElement(By.xpath("(//h3[normalize-space()='Our Locations'])[1]"));
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", l);
                helper.sleepInSeconds(1);
            }
            driver.findElement(locationNextItem).click();
        }
    }
    public void validateLifeAtInsiderSection(String contentOfLifeAtInsider){
        //Checks the Life At Insider section and Validates the content
        WebElement l=driver.findElement(By.xpath("//h2[normalize-space()='Life at Insider']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", l);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lifeAtInsiderContent));
        WebElement contentElement=driver.findElement(lifeAtInsiderContent);
        String actualText=contentElement.getText().trim();
        if (contentOfLifeAtInsider.equals(actualText)){System.out.println("Content is correct");}else {fail("Content different");}

    }
    public void navigateToQAPage(){
        //Scrolls page slightly up and cllicks to QA Department
        WebElement l=driver.findElement(By.xpath("//h3[normalize-space()='Business Intelligence']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", l);
        helper.sleepInSeconds(1);
        driver.findElement(QA).click();
    }
}
