package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class DeletePageTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverHelper.getDriver();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAs("director@ucll.be", "t");
        this.driver.get(Config.BASE_URL+ "Controller?command=Overview");
    }

    @After
    public void clean() {
        driver.quit();
    }

    /*
    test echt delete
    press confirm
    press cancel
    change id
     */

    //test delete
    @Test
    public void test_delete_user_get_succesfully_deleted() {
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        overviewPage.deleteUserWithEmail("employee@ucll.be");
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.clickYes();
        OverviewPage overviewPage2 = PageFactory.initElements(driver, OverviewPage.class);
        assertEquals("Overview",driver.getTitle());
        assertFalse(overviewPage2.containsUserWithName("employee@ucll.be"));
    }

    @Test
    public void test_delete_user_cancel() {
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        int beforeRows = overviewPage.getUserCount();
        overviewPage.deleteUserWithEmail("teamleader@ucll.be");
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.clickNo();
        OverviewPage overviewPage2 = PageFactory.initElements(driver, OverviewPage.class);
        assertTrue(overviewPage2.getUserCount() == beforeRows);
    }

    @Test
    public void test_delete_with_no_userid_throws_error() {
        this.driver.get(Config.BASE_URL+ "Controller?command=Delete");
        DeletePage deletePage = PageFactory.initElements(driver, DeletePage.class);
        deletePage.clickYes();
        assertEquals("Overview",driver.getTitle());
        assertTrue(deletePage.hasErrorMessage("No user has been found to delete"));
    }
}