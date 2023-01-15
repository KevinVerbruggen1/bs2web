package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public abstract class Page {

    WebDriver driver;
    String path = Config.BASE_URL;

    public Page (WebDriver driver) {
        this.driver = driver;
    }

    public String getPath() {
        return path;
    }

    public String getTitle () {
        return driver.getTitle();
    }

    public boolean hasErrorMessage(String message) {
        List<WebElement> errorMsgs = this.driver.findElements(By.id("errorItem"));

        for (WebElement errorMsg : errorMsgs) {
            if (errorMsg.getText().equals(message)) {
                return true;
            }
        }
        return false;
    }

}
