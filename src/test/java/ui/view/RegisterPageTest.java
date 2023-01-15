package ui.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.*;

public class RegisterPageTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverHelper.getDriver();
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        loginPage.loginAs("director@ucll.be", "t");
        driver.get(Config.BASE_URL+ "Controller?command=Register");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_register_with_all_correct_parameters() {
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setLastName("Jansens");
        registerPage.setFirstName("Jans");
        registerPage.setEmail("JansJansens@gmail.com");
        registerPage.setPassword("password");
        registerPage.clickSubmit();
        OverviewPage overviewPage = PageFactory.initElements(driver, OverviewPage.class);
        assertEquals("Overview",driver.getTitle());
        assertTrue(overviewPage.containsUserWithName("Jans"));
    }

    @Test
    public void test_register_with_all_incorrect_parameters() {
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setLastName("");
        registerPage.setFirstName("");
        registerPage.setEmail("");
        registerPage.setPassword("");
        registerPage.clickSubmit();

        assertTrue(registerPage.hasErrorMessage("No last name given"));
        assertTrue(registerPage.hasErrorMessage("No firstname given"));
        assertTrue(registerPage.hasErrorMessage("No email given"));
        assertTrue(registerPage.hasErrorMessage("No password given"));
    }

    @Test
    public void test_register_with_duplicate_email() {
        RegisterPage registerPage = PageFactory.initElements(driver, RegisterPage.class);
        registerPage.setLastName("Jansens");
        registerPage.setFirstName("Jos");
        registerPage.setEmail("director@ucll.be");
        registerPage.setPassword("password");
        registerPage.clickSubmit();

        assertTrue(registerPage.hasErrorMessage("Email already exists"));
    }
}