package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTables {
    public WebDriver driver;
    By rows = By.xpath("//table[@id='customers']/tbody/tr");

    By colomnHeader = By.xpath("//table[@id='customers']/tbody/tr/th");
   // private By dummy=By.xpath("//*[@class='w3-white w3-padding notranslate w3-padding-16']");

    public WebTables(WebDriver driver) {
        this.driver = driver;
    }

    /*public List<WebElement>GetDummy(){
        return driver.findElements(dummy);
    }*/
    public int rows() {
        return driver.findElements(rows).size();
    }

    public int colomn() {
        return driver.findElements(colomnHeader).size();
    }

    public List<WebElement> ColomnHeader() {
        return driver.findElements(colomnHeader);
    }

    public void TableData() {
        for (int i = 2; i <= rows(); i++) {
            for (int j = 1; j <= colomn(); j++) {
                By tData = By.xpath("//table[@id='customers']/tbody/tr[" + i + "]/td[" + j + "]");
                String values = driver.findElement(tData).getText();
                System.out.format("%-35s", values);
            }
            System.out.println("");
        }
    }
}
