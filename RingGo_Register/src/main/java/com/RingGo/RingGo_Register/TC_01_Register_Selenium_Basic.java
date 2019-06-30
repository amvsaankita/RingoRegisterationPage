package com.RingGo.RingGo_Register;

import java.awt.Color;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TC_01_Register_Selenium_Basic {
	
	


	
	// static WebDriver driver ;
	
	
	public static void main (String args[]) throws InterruptedException {
		
		//System.getProperty("user.dir");
		
		String path = System.getProperty("user.dir");   // return project folder path

		String   driverpath = path + "\\drivers\\chromedriver.exe";   // return driver folder path 
		System.setProperty("webdriver.chrome.driver",driverpath );

		WebDriver	 driver= new ChromeDriver();


		
		 
		String user_name="myRingGoTest";
		String password="W4t3Rf4lls";
		driver.get("http://"+user_name+":"+password+"@myrgo-preprod.ctt.co.uk/register");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		//TC01_verify title
		
		String expected_title="RingGo Cashless Parking Solution: Register for a RingGo account";
		String actual_title= driver.getTitle();
		boolean test = actual_title.equals(expected_title);
		System.out.println(test);
		Assert.assertTrue(test,"test case passed");
		
		
		//TC02_verify logo display
		
		boolean logo1=	driver.findElement(By.xpath("//img[@alt='RingGo']")).isDisplayed();
		System.out.println(logo1);
		Assert.assertTrue(logo1, "first logo displayed");
		
		boolean logo2= driver.findElement(By.xpath("//img[@alt='Part of PARK NOW']")).isDisplayed();
		System.out.println(logo2);
		Assert.assertTrue(logo2, "second logo displayed");
		
		
		//TC0301_verify Header tab_Park
	    boolean link_Park=	driver.findElement(By.linkText("Park")).isDisplayed();
	    Assert.assertTrue(link_Park, "link Park displayed");
	
	     //TC0302_verify Header tab_Permits
		boolean link_Permits=	driver.findElement(By.linkText("Permits")).isDisplayed();
		Assert.assertTrue(link_Permits, "link Permits displayed");
		
		//TC0303_verify Header tab_Receipts
		boolean link_Receipts=	driver.findElement(By.linkText("Receipts")).isDisplayed();
		Assert.assertTrue(link_Receipts, "link Receipts displayed");
		
		//TC0304_verify Header tab_More
		boolean link_More=	driver.findElement(By.id("toggleMore")).isDisplayed();
		Assert.assertTrue(link_More, "link More displayed");
			
			
		//TC04_verify cookies header
		
		String cookies_Header = driver.findElement(By.xpath("//div[@id='cookie-header-container']/p")).getText();
		String expected_cookies_Header= "Cookies help us deliver our services. By using our services, you agree to our use of cookies. Read our cookie policy";
		boolean test_cookies= cookies_Header.equals(expected_cookies_Header);
		Assert.assertTrue(test_cookies, "cookies message displayed");
		driver.findElement(By.id("cookie-dismiss")).click();
		Thread.sleep(5000);
		
		
		//TC05_verify search text box on header
		
		boolean search_Textbox= driver.findElement(By.id("query")).isEnabled();
		
		Assert.assertTrue(search_Textbox, "search text box displayed and enabled");
		System.out.println("search text box displayed and enabled");
		
		driver.findElement(By.id("query")).sendKeys("test_automation");
		
		
		//TC06_verify image on left side
		
		boolean image_Onleftside= driver.findElement(By.xpath("//li[@class='side-column column left-side-column']/a")).isDisplayed();
		Assert.assertTrue(image_Onleftside, "image displayed in left side");
		System.out.println("image displayed in left side  "+image_Onleftside);
		
		
		//TC07_verify all field Mandatory field on register page
		
	
		
		driver.findElement(By.id("labyrinth_UserDetails_next")).click();
		WebDriverWait wait= new WebDriverWait(driver, 20);
		

		WebElement warning_Notification= driver.findElement(By.xpath("//ul[@class='warning-notification']"));
		wait.until(ExpectedConditions.visibilityOf(warning_Notification));
		verify_Text(warning_Notification, "There is an error in the form. Please check for details below.");
	
	
		
		WebElement mobilenoWarning_notification= driver.findElement(By.xpath("//input[@id='field-Member_CLI']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(mobilenoWarning_notification));
		verify_Text(mobilenoWarning_notification, "Mobile Number is required");
		
		WebElement emailWarning_notification = driver.findElement(By.xpath("//input[@id='field-Member_Email']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(emailWarning_notification));
		verify_Text(emailWarning_notification, "Email address is required");
		
		WebElement pwWarning_notification = driver.findElement(By.xpath("//input[@id='field-MemberPassword']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(pwWarning_notification));
		verify_Text(pwWarning_notification, "Password is required");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,650)", "");
		Thread.sleep(3000);
		
		WebElement captcha_notification = driver.findElement(By.xpath("//label[@id='label-recaptcha']/following-sibling::div//span"));
		wait.until(ExpectedConditions.visibilityOf(captcha_notification));
		verify_Text(captcha_notification, "Please complete the Recaptcha.");

		WebElement termsAndCondition_notification = driver.findElement(By.xpath("//input[@name='terms']/following-sibling::span"));
		wait.until(ExpectedConditions.visibilityOf(termsAndCondition_notification));
		verify_Text(termsAndCondition_notification, "You must agree to the Terms & Conditions");
		
		
		//TC_08 verify positive cases for field
		
		boolean mobile_field=driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).isEnabled();
		Assert.assertTrue(mobile_field, "mobile field enabled");
		System.out.println("mobile field enabled");
		
		boolean email_field=driver.findElement(By.xpath("//input[@id='field-Member_Email']")).isEnabled();
		Assert.assertTrue(email_field, "email field enabled");
		System.out.println("email field enabled");
		
		boolean password_field=driver.findElement(By.xpath("//input[@id='field-MemberPassword']")).isEnabled();
		Assert.assertTrue(password_field, "password field enabled");
		System.out.println("password field enabled");
		
		boolean checkboxForMarketing=driver.findElement(By.xpath("//input[@name='Marketing']")).isEnabled();
		Assert.assertTrue(checkboxForMarketing, "checkbox for marketing enabled");
		System.out.println("checkbox for info via email and sms field enabled");
		
		boolean checkboxForDatasharing=driver.findElement(By.xpath("//input[@name='DataSharing']")).isEnabled();
		Assert.assertTrue(checkboxForDatasharing, "checkbox for datasharing enabled");
		System.out.println("checkbox for datasharing enabled");
		
		boolean checkboxForTerms=driver.findElement(By.xpath("//input[@name='terms']")).isEnabled();
		Assert.assertTrue(checkboxForTerms, "checkbox for Terms enabled");
		System.out.println( "checkbox for Terms enabled");
		
		//TC_09 verification of valid mobile,message and message color background
		driver.findElement(By.xpath("//input[@id='field-Member_CLI']")).sendKeys(generate_Uniqueno());
	
		
		//TC_10 verification of emailid
		String email= "Test_automation"+generate_Uniqueno()+"@gmail.com";
		System.out.println(email);
		driver.findElement(By.xpath("//input[@id='field-Member_Email']")).sendKeys(email);
		
		WebElement mobileno_verifyMessage = driver.findElement(By.xpath("//span[contains(text(),'Number available to register')]"));

		wait.until(ExpectedConditions.visibilityOf(mobileno_verifyMessage));
		
		
		// after you click on password
		driver.findElement(By.xpath("//input[@id='field-MemberPassword']")).sendKeys("Test@12345");
		
		WebElement email_verifyMessage = driver.findElement(By.xpath("//span[contains(text(),'Email available to register')]"));
		wait.until(ExpectedConditions.visibilityOf(email_verifyMessage));
		verify_Text(email_verifyMessage, "Email available to register");
		
		
		
		driver.findElement(By.xpath("//input[@name='terms']")).click();
		
		
		
		//verifyBackGroundColour(email_verifyMessage,"C9FFCA");
		
		
		//TC_11 verification of breadcrum
		boolean breadcrum=driver.findElement(By.xpath("//div[@class='breadcrumbs']/a[contains(.,'Home')]/following-sibling::strong")).isDisplayed();
		Assert.assertTrue(breadcrum, "breadcrum displayed");
		System.out.println("breadcrum displayed");
		driver.close();
		 
		
		
		
		
	}
	
	public static boolean verify_Text(WebElement ele, String expected_result) {
		String actual_warningMessage= ele.getText();
		boolean test_WarningMessage= actual_warningMessage.equals(expected_result);
		System.out.println("correct warning message dispalyed -----" +expected_result);
		System.out.println(actual_warningMessage);

		Assert.assertTrue(test_WarningMessage, "correct warning message dispalyed");
		return test_WarningMessage;
	}
	
	public static String generate_Uniqueno() {
Random random = new Random();
		
		long start =1000000000l;
		long end =9999999999l;
		int i =1;
		long final_randomNumber=0;
		while( i==1) {
		    long range = (long)end - (long)start +1;
		    // compute a fraction of the range, 0 <= frac < range
		    long fraction = (long)(range * random.nextDouble());
		    long randomNumber =  (long)(fraction + start);    
		    
		    System.out.println("inside while loop");
		    if(randomNumber>0) {
			    System.out.println("Generated : " + randomNumber);
			    final_randomNumber=randomNumber;
			    i++;
			   
			    break;
		    }
		    else {
		    	continue;
		    }}
	    String str1 = Long.toString(final_randomNumber); 
	    System.out.println(str1);
		return str1;
		
		
		
	}

	/*
	 * public static boolean verifyBackGroundColour(WebElement ele ,String
	 * colourcode) {
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * String colorString = ele.getCssValue("background-color");
	 * System.out.println(colorString); Color str =Color.decode(colourcode);
	 * str.toString(); System.out.println(str);
	 * 
	 * 
	 * boolean matchvalue =colorString.equals(str.toString());
	 * System.out.println("value "+matchvalue); return matchvalue; }
	 */
	
	
}
