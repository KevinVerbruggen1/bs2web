package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class RegisterPage extends Page {

    @FindBy(id="lastName")
    private WebElement lastNameField;

    @FindBy(id="firstName")
    private WebElement firstNameField;

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="team")
    private WebElement teamField;

    @FindBy(id="signUp")
    private WebElement submitButton;

    public RegisterPage (WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"register.jsp");
    }

    public void setFirstName(String firstName) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void setTeam(String team) {
        teamField.clear();
        teamField.sendKeys(team);
    }

    public void clickSubmit() {
        submitButton.click();
    }



}