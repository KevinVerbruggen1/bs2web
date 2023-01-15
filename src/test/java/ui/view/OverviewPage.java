package ui.view;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class OverviewPage extends Page{

    @FindBy(id="overview")
    private WebElement editButton;

    @FindBy(id="delete")
    private WebElement deleteButton;


    public OverviewPage(WebDriver driver) {
        super(driver);
        this.driver.get(getPath()+"Controller?command=Overview");
    }

    public boolean containsUserWithName(String name) {
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(name)) {
                found=true;
            }
        }
        return found;
    }

    public void editUserWithEmail(String email){
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("table tr"));
    	for (WebElement listItem:listItems) {
            if (listItem.getText().contains(email)) {
            	listItem.findElement(By.id("edit")).click();
                return;
            }
        }
    }

    public void deleteUserWithEmail(String email){
        ArrayList<WebElement> listItems=(ArrayList<WebElement>) this.driver.findElements(By.cssSelector("table tr"));
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains(email)) {
                listItem.findElement(By.id("delete")).click();
                return;
            }
        }
    }

    public int getUserCount() {
        List<WebElement> rows = driver.findElements(By.cssSelector("tr"));
        return rows.size();
    }
}
