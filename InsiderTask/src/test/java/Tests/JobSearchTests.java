package Tests;

import Test.Pages.CareersPage;
import Test.Pages.MainPage;
import Test.Pages.OpenPositionsPage;
import Test.Pages.QACareersPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.TestWatcherExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class JobSearchTests {
    //Driver Settings
    static WebDriver driver = DriverFactory.initializeDriver();
    @RegisterExtension
    static TestWatcherExtension testWatcherExtension = new TestWatcherExtension(() -> driver);
    String headerText= ConfigReader.getProperty("headerText");
    String contentLifeInInsider="We’re here to grow and drive growth—as none of us did before. Together, we’re building a culture that inspires us to create our life’s work—and creates a bold(er) impact. We know that we’re smarter as a group than we are alone. Become one of us if you dare to play bigger.";
    String keywordForJobTitle1=ConfigReader.getProperty("keywordForJobTitle1");
    String keywordForJobTitle2=ConfigReader.getProperty("keywordForJobTitle2");
    String departmentName=ConfigReader.getProperty("departmentName");
    String locationName=ConfigReader.getProperty("locationName");
    String urlKeyword=ConfigReader.getProperty("urlKeyword");
    String urlForQAPage=ConfigReader.getProperty("urlForQAPage");
    MainPage mainPage = new MainPage(driver);
    CareersPage careersPage = new CareersPage(driver);
    QACareersPage qaCareersPage = new QACareersPage(driver);
    OpenPositionsPage openPositionsPage = new OpenPositionsPage(driver);
    @Test
    public void insiderCareerSearch() {
        //Calls Methods from the Pages
        mainPage.checkIfPageIsOpenedOrNot();
        mainPage.acceptCookies();
        mainPage.navigateToCompanySection();
        mainPage.validateCompanyMenu();
        mainPage.clickToCareersNavMenuItem();
        careersPage.scrollUntilSeeAllTeamsItemAndClick();
        careersPage.validateTeams();
        careersPage.validateLocations();
        careersPage.validateLifeAtInsiderSection(contentLifeInInsider);
        careersPage.navigateToQAPage();
        assertEquals(qaCareersPage.getHeaderText(),headerText);
        assertEquals(urlForQAPage,driver.getCurrentUrl(),"URL WRONG");
        qaCareersPage.clickToSeeAllJobsButton();
        openPositionsPage.clickToSelectLocationAndChoose();
        openPositionsPage.clickToSelectDepartment();
        openPositionsPage.checkThePresenceOfJobs();
        openPositionsPage.jobTitleControl(keywordForJobTitle1,keywordForJobTitle2);
        openPositionsPage.departmentControl(departmentName);
        openPositionsPage.locationControl(locationName);
        openPositionsPage.clickToViewRoleButton();
        openPositionsPage.closeInsiderPageSwitchTab();
        assertTrue(driver.getCurrentUrl().contains(urlKeyword),"It is not lever site url doesn't contains lever word");
    }
}

