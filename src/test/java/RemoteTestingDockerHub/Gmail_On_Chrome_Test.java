package RemoteTestingDockerHub;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Gmail_On_Chrome_Test {

    Docker docker = new Docker();

    @BeforeSuite
    public void startDockerScale() throws IOException, InterruptedException {
        File fi = new File("output.txt");
        if (fi.delete()) {
            System.out.println("file deleted successfully");
        }
        docker.startDocker();
    }

    @AfterSuite
    public void stopDockerDeleteFile() throws IOException, InterruptedException {
        docker.stopDocker();
    }

    @Test
    public void ChromeTest() throws MalformedURLException {
        // Create an instance of EdgeOptions and add arguments
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("start-maximized");

        // Create an instance of RemoteWebDriver with EdgeOptions and remote URL
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to Google service
        driver.get("https://www.gmail.com");

        System.out.println(driver.getTitle() + " :: " + driver.getCurrentUrl());

    }
}
