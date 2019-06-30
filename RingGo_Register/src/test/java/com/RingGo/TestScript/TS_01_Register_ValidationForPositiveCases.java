package com.RingGo.TestScript;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TS_01_Register_ValidationForPositiveCases {

	WebDriver driver;
	WebDriverWait wait;
	public static ExtentTest test1;

	public static ExtentReports report;

	@BeforeClass
	public void setup() {

		String path = System.getProperty("user.dir"); // return project folder path

		String driverpath = path + "\\drivers\\chromedriver.exe"; // return driver folder path
		System.setProperty("webdriver.chrome.driver", driverpath);
		report = new ExtentReports(System.getProperty("user.dir")+"\\RingoRegisterationPage.html");
		test1 = report.startTest("RingoRegisterationPage");

		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);

		String user_name = "myRingGoTest";
		String password = "W4t3Rf4lls";
		driver.get("http://" + user_name + ":" + password + "@myrgo-preprod.ctt.co.uk/register");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	@Test
	public void verifyTitle() {
		
		test1.log(LogStatus.INFO, "verifyTitle");

		String expected_title = "RingGo Cashless Parking Solution: Register for a RingGo account";
		String actual_title = driver.getTitle();
		boolean test = actual_title.equals(expected_title);
		Assert.assertTrue(test, "test case passed");
		test1.log(LogStatus.PASS, "Title Verified");


	}

	@Test(dependsOnMethods = "verifyTitle")
	public void verifyLogo() {

		test1.log(LogStatus.INFO, "verifyLogo");

		boolean logo1 = driver.findElement(By.xpath("//img[@alt='RingGo']")).isDisplayed();
		System.out.println(logo1);
		Assert.assertTrue(logo1, "first logo displayed");

		boolean logo2 = driver.findElement(By.xpath("//img[@alt='Part of PARK NOW']")).isDisplayed();
		System.out.println(logo2);
		Assert.assertTrue(logo2, "second logo displayed");
		test1.log(LogStatus.PASS, "Logo displayed");


	}

	@Test(dependsOnMethods = "verifyLogo")
	public void veirfyHeader() {
		test1.log(LogStatus.INFO, "veirfyHeader");


		boolean link_Park = driver.findElement(By.linkText("Park")).isDisplayed();
		Assert.assertTrue(link_Park, "link Park displayed");
		test1.log(LogStatus.PASS, "link Park displayed");


		// TC0302_verify Header tab_Permits
		boolean link_Permits = driver.findElement(By.linkText("Permits")).isDisplayed();
		Assert.assertTrue(link_Permits, "link Permits displayed");
		test1.log(LogStatus.PASS, "link Permits displayed");


		// TC0303_verify Header tab_Receipts
		boolean link_Receipts = driver.findElement(By.linkText("Receipts")).isDisplayed();
		Assert.assertTrue(link_Receipts, "link Receipts displayed");
		test1.log(LogStatus.PASS, "link Receipts displayed");


		// TC0304_verify Header tab_More
		boolean link_More = driver.findElement(By.id("toggleMore")).isDisplayed();
		Assert.assertTrue(link_More, "link More displayed");
		test1.log(LogStatus.PASS, "link More displayed");

		
	}

	@Test(dependsOnMethods = "veirfyHeader")
	public void verifiyCookieDisplayed() {
		test1.log(LogStatus.INFO, "verifiyCookieDisplayed");

		String cookies_Header = driver.findElement(By.xpath("//div[@id='cookie-header-container']/p")).getText();
		String expected_cookies_Header = "Cookies help us deliver our services. By using our services, you agree to our use of cookies. Read our cookie policy";
		boolean test_cookies = cookies_Header.equals(expected_cookies_Header);
		Assert.assertTrue(test_cookies, "cookies message displayed");
		test1.log(LogStatus.PASS, "cookies message displayed");

		driver.findElement(By.id("cookie-dismiss")).click();
		test1.log(LogStatus.PASS, "cookies clicked");


	}

	@Test(dependsOnMethods = "verifiyCookieDisplayed")

	public void verifyBreadcrum() {
		test1.log(LogStatus.INFO, "verifyBreadcrum");

		boolean breadcrum = driver
				.findElement(By.xpath("//div[@class='breadcrumbs']/a[contains(.,'Home')]/following-sibling::strong"))
				.isDisplayed();
		Assert.assertTrue(breadcrum, "breadcrum displayed");
		System.out.println("breadcrum displayed");
		test1.log(LogStatus.PASS, "Breadcrum displayed");


	}

	@Test(dependsOnMethods = "verifyBreadcrum")
	public void verifySearchTextboxEnabled() {
		test1.log(LogStatus.INFO, "verifySearchTextboxEnabled");


		boolean search_Textbox = driver.findElement(By.id("query")).isEnabled();

		Assert.assertTrue(search_Textbox, "search text box displayed and enabled");
		System.out.println("search text box displayed and enabled");
		test1.log(LogStatus.PASS, "Search text box displayed and enabled");

		driver.findElement(By.id("query")).sendKeys("test_automation");

	}

	@Test(dependsOnMethods = "verifySearchTextboxEnabled")
	public void verifyLeftSideImage() {
		test1.log(LogStatus.INFO, "verifyLeftSideImage");

		boolean image_Onleftside = driver.findElement(By.xpath("//li[@class='side-column column left-side-column']/a"))
				.isDisplayed();
		Assert.assertTrue(image_Onleftside, "image displayed in left side");
		System.out.println("Image displayed in left side  " + image_Onleftside);
		test1.log(LogStatus.PASS, "image displayed in left side  ");

	}

	@Test(dependsOnMethods = "verifyLeftSideImage")
	public void verifyRegisterationFieldEnabled() {
		test1.log(LogStatus.INFO, "verifyRegisterationFieldEnabled");


		boolean mobile_field = driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).isEnabled();
		Assert.assertTrue(mobile_field, "mobile field enabled");
		System.out.println("mobile field enabled");
		test1.log(LogStatus.PASS, "Mobile field enabled");

		boolean email_field = driver.findElement(By.xpath("//input[@id='field-Member_Email']")).isEnabled();
		Assert.assertTrue(email_field, "email field enabled");
		System.out.println("email field enabled");
		test1.log(LogStatus.PASS, "Email field enabled");


		boolean password_field = driver.findElement(By.xpath("//input[@id='field-MemberPassword']")).isEnabled();
		Assert.assertTrue(password_field, "password field enabled");
		System.out.println("password field enabled");
		test1.log(LogStatus.PASS, "Password field enabled");


		boolean checkboxForMarketing = driver.findElement(By.xpath("//input[@name='Marketing']")).isEnabled();
		Assert.assertTrue(checkboxForMarketing, "checkbox for marketing enabled");
		System.out.println("checkbox for info via email and sms field enabled");
		test1.log(LogStatus.PASS, "Checkbox for info via email and sms field enabled");


		boolean checkboxForDatasharing = driver.findElement(By.xpath("//input[@name='DataSharing']")).isEnabled();
		Assert.assertTrue(checkboxForDatasharing, "checkbox for datasharing enabled");
		System.out.println("checkbox for datasharing enabled");
		test1.log(LogStatus.PASS, "Checkbox for datasharing enabled");


		boolean checkboxForTerms = driver.findElement(By.xpath("//input[@name='terms']")).isEnabled();
		Assert.assertTrue(checkboxForTerms, "checkbox for Terms enabled");
		System.out.println("checkbox for Terms enabled");
		test1.log(LogStatus.PASS, "Checkbox for Terms enabled");


	}

	@Test(dependsOnMethods = "verifyRegisterationFieldEnabled")
	public void verifyMessageforTextBoxWithCorrectData() {
		test1.log(LogStatus.INFO, "verifyMessageforTextBoxWithCorrectData");


		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys(generate_Uniqueno());

		// TC_10 verification of emailid
		String email = "Test_automation" + generate_Uniqueno() + "@gmail.com";
		System.out.println(email);
		driver.findElement(By.xpath("//input[@id='field-Member_Email']")).sendKeys(email);

		WebElement mobileno_verifyMessage = driver
				.findElement(By.xpath("//span[contains(text(),'Number available to register')]"));

		wait.until(ExpectedConditions.visibilityOf(mobileno_verifyMessage));

		// after you click on password
		driver.findElement(By.xpath("//input[@id='field-MemberPassword']")).sendKeys("Test@12345");

		WebElement email_verifyMessage = driver
				.findElement(By.xpath("//span[contains(text(),'Email available to register')]"));
		wait.until(ExpectedConditions.visibilityOf(email_verifyMessage));
		verify_Text(email_verifyMessage, "Email available to register");
		test1.log(LogStatus.PASS, "Email available to register");


		driver.findElement(By.xpath("//input[@name='terms']")).click();

	}

	@Test(dependsOnMethods = "verifyMessageforTextBoxWithCorrectData")
	public void verifyFooterDisplay() {
		
		test1.log(LogStatus.INFO, "verifyFooterDisplay");

		boolean footerpart1 = driver.findElement(By.xpath("//div[@id='waystopay']")).isDisplayed();
		Assert.assertTrue(footerpart1, "footerpart1 displayed");
		test1.log(LogStatus.PASS, "footerpart1 displayed");


		boolean footerpart2 = driver.findElement(By.xpath("//div[@id='greyfooter']")).isDisplayed();
		Assert.assertTrue(footerpart2, "footerpart1 displayed");
		test1.log(LogStatus.PASS, "footerpart1 displayed");


		boolean footercopyright = driver.findElement(By.xpath("//p[@id='copyright']")).isDisplayed();
		Assert.assertTrue(footercopyright, "footercopyright displayed");
		test1.log(LogStatus.PASS, "footercopyright displayed");

	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE){
			test1.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			test1.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		 String screenshotPath;
		try {
			screenshotPath = TS_01_Register_ValidationForPositiveCases.getScreenshot(driver, result.getName());
		
		 //To add it in the extent report 
		 test1.log(LogStatus.FAIL, test1.addScreenCapture(screenshotPath));
		 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }
	else if(result.getStatus() == ITestResult.SKIP){
		 test1.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		 }
		 // ending test
		 //endTest(logger) : It ends the current test and prepares to create HTML report
		report.endTest(test1);
		 }
		
		
		
		
	

	@AfterClass()
	public void CloseSetUp() {
		test1.log(LogStatus.INFO, "Browser closed");

		
		report.endTest(test1);
		
		report.flush();
		driver.close();

	}

	public static boolean verify_Text(WebElement ele, String expected_result) {
		String actual_warningMessage = ele.getText();
		boolean test_WarningMessage = actual_warningMessage.equals(expected_result);
		System.out.println("correct warning message dispalyed -----" + expected_result);
		System.out.println(actual_warningMessage);

		Assert.assertTrue(test_WarningMessage, "correct warning message dispalyed");
		return test_WarningMessage;
	}

	public static String generate_Uniqueno() {
		Random random = new Random();

		long start = 1000000000l;
		long end = 9999999999l;
		int i = 1;
		long final_randomNumber = 0;
		while (i == 1) {
			long range = (long) end - (long) start + 1;
			// compute a fraction of the range, 0 <= frac < range
			long fraction = (long) (range * random.nextDouble());
			long randomNumber = (long) (fraction + start);

			System.out.println("inside while loop");
			if (randomNumber > 0) {
				System.out.println("Generated : " + randomNumber);
				final_randomNumber = randomNumber;
				i++;

				break;
			} else {
				continue;
			}
		}
		String str1 = Long.toString(final_randomNumber);
		System.out.println(str1);
		return str1;

	}

	
	


//Creating a method getScreenshot and passing two parameters 
//driver and screenshotName
public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
                //below line is just to append the date format with the screenshot name to avoid duplicate names 
                String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
 TakesScreenshot ts = (TakesScreenshot) driver;
 File source = ts.getScreenshotAs(OutputType.FILE);
                //after execution, you could see a folder "FailedTestsScreenshots" under src folder
 String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
 File finalDestination = new File(destination);
 FileUtils.copyFile(source, finalDestination);
                //Returns the captured file path
 return destination;
}
}
