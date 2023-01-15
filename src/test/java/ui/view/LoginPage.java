package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends Page{

    @FindBy(id="email")
    private WebElement emailField;

    @FindBy(id="password")
    private WebElement passwordField;

    @FindBy(id="signIn")
    private WebElement submitButton;

    @FindBy(id="logOut")
    private WebElement logOutButton;

    public LoginPage (WebDriver driver) {
        super(driver);
        this.driver.get(Config.BASE_URL+"Controller");
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void setPassword(String password) {
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public void loginAs(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickSubmit();
    }

    public boolean hasErrorMessage(String message) {
        List<WebElement> errorMessages = driver.findElements(By.className("error"));
        for (WebElement errorMessage : errorMessages) {
            if (errorMessage.getText().equals(message)) {
                return true;
            }
        }
        return false;
    }
}
