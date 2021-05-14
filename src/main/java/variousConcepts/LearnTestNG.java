package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LearnTestNG {

	WebDriver driver;
	String browser;

	@BeforeClass
	public void readConfig() {

		// BufferedReader, InputStream, FileReader, Scanner

		Properties prop = new Properties();

		try {
			InputStream input = new FileInputStream("./src/main/java/conifg/config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver.exe");
			driver = new FirefoxDriver();

		}

		driver.get("https://www.techfios.com/billing/?ng=admin");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
		
	}

	@Test
	public void windowHandle() {
		
		Random random = new Random();
		int randomNumber = random.nextInt(); 
		
		WebElement USERNAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement LOGIN_BUTTON_ELEMENT = driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]"));
		
		USERNAME_ELEMENT.sendKeys(randomNumber + "demo@techfios.com");
		PASSWORD_ELEMENT.sendKeys("abc123");
		LOGIN_BUTTON_ELEMENT.click();
		
		WebElement DASHBOARD_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(), 'Dashboard')]"));
		Assert.assertEquals(DASHBOARD_ELEMENT.getText(), "Dashboard", "Wrong page");
		
	}

}
