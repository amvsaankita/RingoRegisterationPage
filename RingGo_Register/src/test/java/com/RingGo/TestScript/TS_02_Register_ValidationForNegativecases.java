package com.RingGo.TestScript;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

public class TS_02_Register_ValidationForNegativecases {

	WebDriver driver;
	WebDriverWait wait;

	public static ExtentTest test1;

	public static ExtentReports report;

	@BeforeClass
	public void setup() {

		String path = System.getProperty("user.dir"); // return project folder path

		String driverpath = path + "\\drivers\\chromedriver.exe"; // return driver folder path
		System.setProperty("webdriver.chrome.driver", driverpath);
		report = new ExtentReports(System.getProperty("user.dir") + "\\RingoRegisterationPage_Negative_Scenario.html");
		test1 = report.startTest("RingoRegisterationPage_Negative_Scenario");

		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);

		String user_name = "myRingGoTest";
		String password = "W4t3Rf4lls";
		driver.get("http://" + user_name + ":" + password + "@myrgo-preprod.ctt.co.uk/register");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	}

	 @Test(priority = 1)
	public void ValidationForMandatoryField() throws InterruptedException {
		test1.log(LogStatus.INFO, "ValidationForMandatoryField");

		driver.findElement(By.id("labyrinth_UserDetails_next")).click();

		WebElement warning_Notification = driver.findElement(By.xpath("//ul[@class='warning-notification']"));
		wait.until(ExpectedConditions.visibilityOf(warning_Notification));
		verify_Text(warning_Notification, "There is an error in the form. Please check for details below.");
		test1.log(LogStatus.PASS, "There is an error in the form. Please check for details below.");


		WebElement mobilenoWarning_notification = driver
				.findElement(By.xpath("//input[@id='field-Member_CLI']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(mobilenoWarning_notification));
		verify_Text(mobilenoWarning_notification, "Mobile Number is required");
		test1.log(LogStatus.PASS, "Mobile Number is required");


		WebElement emailWarning_notification = driver
				.findElement(By.xpath("//input[@id='field-Member_Email']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(emailWarning_notification));
		verify_Text(emailWarning_notification, "Email address is required");
		test1.log(LogStatus.PASS, "Email address is required");


		WebElement pwWarning_notification = driver
				.findElement(By.xpath("//input[@id='field-MemberPassword']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(pwWarning_notification));
		verify_Text(pwWarning_notification, "Password is required");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,650)", "");
		Thread.sleep(3000);

		WebElement captcha_notification = driver
				.findElement(By.xpath("//label[@id='label-recaptcha']/following-sibling::div//span"));
		wait.until(ExpectedConditions.visibilityOf(captcha_notification));
		verify_Text(captcha_notification, "Please complete the Recaptcha.");
		test1.log(LogStatus.PASS, "Email address is required");

		WebElement termsAndCondition_notification = driver
				.findElement(By.xpath("//input[@name='terms']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(termsAndCondition_notification));
		verify_Text(termsAndCondition_notification, "You must agree to the Terms & Conditions");
		test1.log(LogStatus.PASS, "You must agree to the Terms & Conditions");


	}

	@Test(priority = 9)
	public void validation_For_Mandatory_Mobile_Special_Char_Starts_With_Neg() throws InterruptedException {
		test1.log(LogStatus.INFO, "validation_For_Mandatory_Mobile_Special_Char_Starts_With_Neg");

		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys("-98765475321");
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);

		WebElement mobilenoWarning_notification = driver
				.findElement(By.xpath("//input[@id='field-Member_CLI']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(mobilenoWarning_notification));
		verify_Text(mobilenoWarning_notification, "Mobile Number must be numeric");
		test1.log(LogStatus.PASS, "Mobile Number must be numeric");

		Thread.sleep(4000);

		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).clear();

	}

	@Test(priority = 3)
	public void validationForMandatoryMobileSpecialCharStartsWithPlus() throws InterruptedException {
		test1.log(LogStatus.INFO, "validationForMandatoryMobileSpecialCharStartsWithPlus");

		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys("+98765565321");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys(Keys.ENTER);

		WebElement mobilenoWarning_notification = driver
				.findElement(By.xpath("//input[@id='field-Member_CLI']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(mobilenoWarning_notification));
		verify_Text(mobilenoWarning_notification, "Mobile Number must be numeric");
		test1.log(LogStatus.PASS, "Mobile Number must be numeric");


		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).clear();

	}

	@Test(priority = 4)
	public void validationForMandatoryMobileLowerRange() throws InterruptedException {
		test1.log(LogStatus.INFO, "validationForMandatoryMobileLowerRange");

		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys("345456545");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys(Keys.ENTER);

		WebElement mobilenoWarning_notification = driver
				.findElement(By.xpath("//input[@id='field-Member_CLI']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(mobilenoWarning_notification));
		verify_Text(mobilenoWarning_notification, "Telephone number must be longer than 9 characters");
		test1.log(LogStatus.PASS, "Telephone number must be longer than 9 characters");


		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).clear();

	}

	@Test(priority = 5)
	public void validationForMandatoryMobileHigherRange() throws InterruptedException {
		test1.log(LogStatus.INFO, "validationForMandatoryMobileHigherRange");

		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys("34545654565344");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys(Keys.ENTER);

		WebElement mobilenoWarning_notification = driver
				.findElement(By.xpath("//input[@id='field-Member_CLI']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(mobilenoWarning_notification));
		verify_Text(mobilenoWarning_notification, "Telephone number must be 13 or less characters");
		test1.log(LogStatus.PASS, "Telephone number must be 13 or less characters");


		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).clear();

	}

	@Test(priority = 6)
	public void validationForMandatoryMobileSpclCharAndAlpha() throws InterruptedException {
		test1.log(LogStatus.INFO, "validationForMandatoryMobileSpclCharAndAlpha");

		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys("asd$#");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys(Keys.ENTER);

		WebElement mobilenoWarning_notification = driver
				.findElement(By.xpath("//input[@id='field-Member_CLI']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(mobilenoWarning_notification));
		verify_Text(mobilenoWarning_notification, "Mobile Number must be numeric");
		test1.log(LogStatus.PASS, "Mobile Number must be numeric");


		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).clear();

	}

	@Test(priority = 7)
	public void validationForMandatoryEmail() throws InterruptedException {
		test1.log(LogStatus.INFO, "validationForMandatoryEmail");

		driver.findElement(By.xpath("//input[@id='field-Member_Email']")).sendKeys("sdfsdf@.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='field-Member_Email']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);

	
		
		WebElement emailerrormessage = driver
				.findElement(By.xpath("//span[@id='emailmsgbox']"));
		wait.until(ExpectedConditions.visibilityOf(emailerrormessage));
		verify_Text(emailerrormessage, "Email is invalid");
		
		driver.findElement(By.xpath("//input[@id='field-Member_Email']")).clear();

		Thread.sleep(2000);

		test1.log(LogStatus.PASS, "Email is invalid");


	}
	
	
	@Test(priority = 8)
	public void validationForMandatoryEmail_2() throws InterruptedException {
		test1.log(LogStatus.INFO, "validationForMandatoryEmail");

		driver.findElement(By.xpath("//input[@id='field-Member_Email']")).sendKeys("sdfsdf");
		driver.findElement(By.xpath("//input[@id='field-Member_Email']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);

		WebElement emailWarning_notification = driver
				.findElement(By.xpath("//input[@id='field-Member_Email']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(emailWarning_notification));
		verify_Text(emailWarning_notification, "Email address is invalid");
		test1.log(LogStatus.PASS, "Email address is invalid");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='field-Member_Email']")).clear();


	}

	

	@Test(priority = 2)
	public void validationForMandatoryPassword() throws InterruptedException {
		test1.log(LogStatus.INFO, "validationForMandatoryPassword");

		driver.findElement(By.xpath("//input[@id='field-MemberPassword']")).sendKeys("sdfsdf@.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='field-MemberPassword']")).sendKeys(Keys.ENTER);

		WebElement pwWarning_notification = driver
				.findElement(By.xpath("//input[@id='field-MemberPassword']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(pwWarning_notification));
		verify_Text(pwWarning_notification, "The password does not meet the correct format.");
		test1.log(LogStatus.PASS, "The password does not meet the correct format.");

		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).clear();

	}

	@AfterClass
	public void closeSetup() {
		test1.log(LogStatus.INFO, "Browser closed");

		report.endTest(test1);

		report.flush();
		driver.close();
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test1.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			test1.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath;
			try {
				screenshotPath = TS_01_Register_ValidationForPositiveCases.getScreenshot(driver, result.getName());

				// To add it in the extent report
				test1.log(LogStatus.FAIL, test1.addScreenCapture(screenshotPath));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (result.getStatus() == ITestResult.SKIP) {
			test1.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		// ending test
		// endTest(logger) : It ends the current test and prepares to create HTML report
		report.endTest(test1);
	}

	// Creating a method getScreenshot and passing two parameters
	// driver and screenshotName
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		// below line is just to append the date format with the screenshot name to
		// avoid duplicate names
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		// Returns the captured file path
		return destination;
	}

	public static boolean verify_Text(WebElement ele, String expected_result) {
		String actual_warningMessage = ele.getText();
		boolean test_WarningMessage = actual_warningMessage.equals(expected_result);
		System.out.println("correct warning message dispalyed -----" + expected_result);
		System.out.println(actual_warningMessage);

		Assert.assertTrue(test_WarningMessage, "correct warning message dispalyed");
		return test_WarningMessage;
	}

}
