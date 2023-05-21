package newproject;

import java.time.Duration;

import org.apache.commons.exec.LogOutputStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class SampleTestNJTest {
	public WebDriver driver;
	
@Before
	public void goToUrl() {
   driver = new ChromeDriver();
   driver.get("https://www.demo.guru99.com/V4/");
   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));			
   driver.switchTo().frame("gdpr-consent-notice");
   WebElement closeButton = driver.findElement(By.id("save"));
   closeButton.click();
}
@Test
	public void	VerifyHomePageTitle() throws Exception {
	// Read test data from excel file
    // Method 	getDataFromExcel is defined in class Util
	String[][] testData = Util.getDataFromExcel("C:\\Users\\amjed\\Desktop\\Guru99\\Day03\\testData.xls","Data","testData");
	//Testing for all parameters stored in the Excel File
	String username;
	String password;
	String actualTitle;
	String actualBoxtitle;
	
			
	for (int i = 0; i < testData.length ; i++) {
		username = testData[i][0]; // get username
	    password = testData[i][1]; // get password
	    
	driver.findElement(By.name("uid")).clear();	
	driver.findElement(By.name("uid")).sendKeys(username);
	
	driver.findElement(By.name("password")).clear();
	driver.findElement(By.name("password")).sendKeys(password);
	
	driver.findElement(By.name("btnLogin")).click();

			
	 try{ 
		    
	       	Alert alt = driver.switchTo().alert();
			actualBoxtitle = alt.getText(); // get content of the Alter Message
			alt.accept();
			if (!actualBoxtitle.contains(Util.EXPECT_ERROR)) { // Compare Error Text with Expected Error Value
				System.out.println(actualBoxtitle);
				System.out.println("Test case SS[" + i + "]: Passed"); 
				System.out.println("iciuyguyi");
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
				WebElement LogOut = driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[15]/a"));
				System.out.println("ici");
				LogOut.click();
				
			} else {
			    System.out.println("Test case SS[" + i + "]: Failed");
			}
		}    
	    catch (NoAlertPresentException Ex){ 
	    	actualTitle = driver.getTitle();
			// On Successful login compare Actual Page Title with Expected Title
			if (actualTitle.contains(Util.EXPECT_TITLE)) {
			    System.out.println("Test case SS[" + i + "]: Passed");
			} else {
			    System.out.println("Test case SS[" + i + "]: Failed");
			}
	    }
	}	
		} 
@After
	public void TearDown(){
	//driver.quit();
}
}
