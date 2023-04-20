package RemoteTestingDockerHub;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class GoogleDrive_On_FireFox_Test {
    @Test
    public void FireFoxTest() throws MalformedURLException {
        // Create an instance of EdgeOptions and add arguments
        FirefoxOptions options = new FirefoxOptions();
        // options.addArguments("start-maximized");
        // Create an instance of RemoteWebDriver with EdgeOptions and remote URL
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to Google service
        driver.get("https://drive.google.com/drive");
        System.out.println(driver.getTitle() + " :: " + driver.getCurrentUrl());
    }


}
