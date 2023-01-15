package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class EditPageTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverHelper.getDriver();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAs("director@ucll.be", "t");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_edit_user_with_new_parameters_will_update() {
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        overviewPage.editUserWithEmail("employee@ucll.be");
        EditPage editPage = PageFactory.initElements(driver, EditPage.class);
        editPage.setLastName("Jansens");
        editPage.setFirstName("Jos");
        assertEquals("Edit",driver.getTitle());
        editPage.clickSubmit();

        assertEquals("Overview",driver.getTitle());
        assertTrue(overviewPage.containsUserWithName("Jos"));

    }

    @Test
    public void test_edit_user_with_empty_fields_will_throw_errors() {
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        overviewPage.editUserWithEmail("teamleader@ucll.be");
        EditPage editPage = PageFactory.initElements(driver, EditPage.class);
        editPage.setLastName("");
        editPage.setFirstName("");
        editPage.setEmail("");
        editPage.clickSubmit();

        assertEquals("Edit",driver.getTitle());
        assertTrue(editPage.hasErrorMessage("No last name given"));
        assertTrue(editPage.hasErrorMessage("No firstname given"));
        assertTrue(editPage.hasErrorMessage("No email given"));

    }

    @Test
    public void test_edit_user_with_existing_email_will_throw_error() {
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        overviewPage.editUserWithEmail("director@ucll.be");
        EditPage editPage = PageFactory.initElements(driver, EditPage.class);
        editPage.setEmail("teamleader@ucll.be");
        editPage.setFirstName("Jefke");
        editPage.clickSubmit();

        assertEquals("Edit",driver.getTitle());
        assertTrue(editPage.hasErrorMessage("Email already exists"));
    }

}
