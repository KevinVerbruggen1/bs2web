package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DeletePage extends Page {

    @FindBy(id = "Yes")
    private WebElement yesButton;

    @FindBy(id = "No")
    private WebElement noButton;

    public DeletePage (WebDriver driver) {
        super(driver);
    }

    public void clickYes() {
        yesButton.click();
    }

    public void clickNo() {
        noButton.click();
    }

    public boolean hasErrorMessage (String message) {
        List<WebElement> errorMsgs = driver.findElements(By.id("errorItem"));
 
        for (WebElement errorMsg : errorMsgs) {
            if (errorMsg.getText().equals(message)) {
                return true;
            }
        }
        return false;
    }
}
