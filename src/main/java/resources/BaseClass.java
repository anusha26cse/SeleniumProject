package resources;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
    public WebDriver driver;
    public Properties prop;
    public WebDriver initializerDriver() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\anush\\IdeaProjects\\FinalAssighment\\src\\main\\java\\resources\\data.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        if (browserName.equals("InternetExplorer")) {
            //execute in InternetExplorer driver
            System.setProperty("webdriver.ie.driver", "C:\\Users\\anush\\IdeaProjects\\E2Eproject\\src\\main\\java\\resources\\IEDriverServer.exe");

            driver = new InternetExplorerDriver();
        } else if (browserName.equals("chrome")) {

            System.setProperty("webdriver.chrome.driver","C:\\Users\\anush\\IdeaProjects\\E2Eproject\\src\\main\\java\\resources\\chromedriver.exe");
//ChromeOptions options= new ChromeOptions();
//options.addArguments("headless");
            driver = new ChromeDriver();

        } else if (browserName.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\anush\\IdeaProjects\\E2Eproject\\src\\main\\java\\resources\\geckodriver.exe");

            //create driver object for chrome brower
            // classname=ChromeDriver
            driver = new FirefoxDriver();
        }
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    return driver;
    }
    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot st=(TakesScreenshot) driver;
        File source=st.getScreenshotAs(OutputType.FILE);
        String destinationFile=System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
        FileUtils.copyFile(source,new File(destinationFile));
        return destinationFile;

    }
}
