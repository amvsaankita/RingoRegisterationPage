package com.RingGo.Base;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Baseclass {
	
	WebDriver driver;
	
	public Baseclass(WebDriver driver) {
		this.driver=driver;
		
	}
	
public String encyrptPassword(String Password) {
	
	
	byte[] encodedBytes = Base64.encodeBase64(Password.getBytes());
	System.out.println("encodedBytes "+ new String(encodedBytes));

	byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
	System.out.println("decodedBytes "+ new String(decodedBytes));
return new String(encodedBytes);

} 



public void validateAnchorTags() {
	 String url = "";
     HttpURLConnection huc = null;
     int respCode = 200;
	
	
	  List<WebElement> links = driver.findElements(By.tagName("a"));
      
      Iterator<WebElement> it = links.iterator();
      
      while(it.hasNext()){
          
          url = it.next().getAttribute("href");
          
          System.out.println(url);
      
          if(url == null || url.isEmpty()){
System.out.println("URL is either not configured for anchor tag or it is empty");
              continue;
          }
          
        
          
          try {
              huc = (HttpURLConnection)(new URL(url).openConnection());
              
              huc.setRequestMethod("HEAD");
              
              huc.connect();
              
              respCode = huc.getResponseCode();
              
              if(respCode >= 400){
                  System.out.println(url+" is a broken link");
              }
              else{
                  System.out.println(url+" is a valid link");
              }
                  
          } catch (MalformedURLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }
}



}