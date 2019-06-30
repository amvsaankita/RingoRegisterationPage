package com.RingGo.RingGo_Register;

import java.awt.Color;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

public class Sample_class {

	public static void main(String[] args) {
		
		long aNumber = 0; 


int a=10;
		
byte[] encodedBytes = Base64.encodeBase64("Qait@1234".getBytes());
System.out.println("encodedBytes "+ new String(encodedBytes));

byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
System.out.println("decodedBytes "+ new String(decodedBytes));
System.out.println(new String(encodedBytes));

		

		  
		String colorString = "rgba(201, 255, 202)";
		String str2 =colorString.replace("rgba(", "");
		System.out.println(str2);
		String str3 =str2.replace(")","");
		System.out.println(str3);
		String str [] = str3.split(",");
		System.out.println(str[1]);
	String first_2=	str[1].replaceAll("\\s", "");
		//sentence.replaceAll("\\s", "");
		
		int first = Integer.parseInt(first_2);
		
		
		
		System.out.println(first);
		//int b = Integer.parseInt(s);
		//System.out.println(b);
		
		
		/*
		 * System.out.println(colorString); Color str =Color.decode("#C9FFCA");
		 * str.toString(); System.out.println(str);
		 */
		
		
		
		/*
		 * boolean matchvalue =colorString.equals(str.toString());
		 * System.out.println("value "+matchvalue);
		 */
	//	System.out.print((aNumber));
		// TODO Auto-generated method stub

		
//	Random random = new Random();
//		
//		int start =1000000000;
//		long end =9999999999l;
//		int i =1;
//		while( i==1) {
//		    long range = (long)end - (long)start +1;
//		    // compute a fraction of the range, 0 <= frac < range
//		    long fraction = (long)(range * random.nextDouble());
//		    int randomNumber =  (int)(fraction + start);    
//		    
//		    System.out.println("inside while loop");
//		    if(randomNumber>0) {
//			    System.out.println("Generated : " + randomNumber);
//			    i++;
//			    break;
//		    }
//		    else {
//		    	continue;
//		    }}

	int []a4 =	getRGB("C9FFCA");
		
		System.out.println(a4[1]);
		}

	
	 public static int[] getRGB(final String rgb)
	 {
	     final int[] ret = new int[3];
	     for (int i = 0; i < 3; i++)
	     {
	         ret[i] = Integer.parseInt(rgb.substring(i * 2, i * 2 + 2), 16);
	     }
	     return ret;
	 }
	private static void createrandomINteger(int aStart, long aEnd, Random aRandom){
	   
	    //get the range, casting to long to avoid overflow problems

	  }
}
