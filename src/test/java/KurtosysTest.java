import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class KurtosysTest
{
    static WebDriver driver = null;

    @Test
    public void Resources() throws InterruptedException, IOException {
        //Launch the browser
        ChromeOptions chromeOptions= new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.kurtosys.com/");
        driver.manage().window().maximize();

        //Select resources
        driver.findElement(By.xpath("//button[contains(.,'Accept All Cookies')]")).click();
        driver.findElement(By.xpath("(//span[@class='elementor-icon-list-text'])[81]")).click();
        driver.findElement(By.xpath("//h2[@class='elementor-heading-title elementor-size-default'][contains(.,'White Papers')]")).click();

        //Verify title
        WebElement papers = driver.findElement(By.xpath("//h2[@class='elementor-heading-title elementor-size-default'][contains(.,'White Papers')]"));
        Assert.assertEquals(papers.getText(),"White Papers");

        //Select white paper tile
        driver.findElement(By.xpath("//a[@href='https://www.kurtosys.com/white-papers/eu-rule-change-bolsters-need-for-fast-localized-fund-website-platforms-2/'][contains(.,'UCITS White Paper')]")).click();

        Thread.sleep(5000);
        //Enter personal details
        driver.findElement(By.xpath("//label[ text() = 'First Name']//following::input[1]")).sendKeys("Nkanyiso");
        driver.findElement(By.xpath("//*[@id=\"18882_231671pi_18882_231671\"]")).sendKeys("Sithole");
        driver.findElement(By.xpath("//*[@id=\"18882_231673pi_18882_231673\"]")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"18882_231675pi_18882_231675\"]")).sendKeys("Mazikhon");
        driver.findElement(By.xpath("//*[@id=\"18882_231677pi_18882_231677\"]")).sendKeys("Deco");
        driver.findElement(By.xpath("//*[@id=\"pardot-form\"]/p[2]/input")).click();

        //Takesnapshot of the error
        WebElement ErrorMsg = driver.findElement(By.xpath("//*[@id=\"18882_231673pi_18882_231673\"]"));
        TakeScreenshot("ErrorMsg");

        //Validate error messages
        WebElement firstname_error = driver.findElement(By.xpath("//*[@id=\"pardot-form\"]/div[1]/p"));
        Assert.assertEquals(firstname_error.getText(),"This field is required.");

    }

    public static void TakeScreenshot(String ErrorMsg)
            throws IOException
    {
        // Creating instance of File
        File File = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(File,new File("C:\\Users\\NkanyisoMazibuko\\IdeaProjects\\KurtosysQAassessment\\src\\test\\java\\ErrorMsg.jpeg"));
    }

    @AfterTest
    public void CloseBrowser()
    {
       driver.close();
    }
}
