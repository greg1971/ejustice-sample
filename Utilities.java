package com.backoffice;
import org.openqa.selenium.NoSuchElementException;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * 
 * @author kavadias Class with static fields and methods for being reused by the
 *         Junit Test Cases
 */

public class Utilities {
	/**
	 * Constants to be used by the subclasses
	 */
	static final String baseUrl = "http://e-justice-test.intrasoft-intl.com/";

	/**
	 * Saves in the given filename all errors that may have been found in the
	 * unit test
	 */
	public static void writeMsgToFile(String msg, String filename) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(filename), "UTF8"));
			String outText = msg;
			out.write(outText);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Calculates in minutes the difference between an HTTP request made on the
	 * server
	 */
	public static long getResponseTime(long timeBeforeRequest,
			long timeAfterRequest) {
		System.out.println("Response time: "
				+ (timeAfterRequest - timeBeforeRequest) / 1000);
		return (timeAfterRequest - timeBeforeRequest) / 1000;
	}


	public static void LoginBackend (WebDriver vdriver) throws InterruptedException {
		
		vdriver.get(baseUrl + "/ejusticeportalbackend/login.do");
		vdriver.get("https://ecas.intrasoft-intl.com/cas/login?loginRequestId=ECAS_LR-52243-OZvG2iFTerA1PMvHszzRSYMZaGcnRrk8wlHpAxoU81za-xbg64zJwypHmVU89U5Y0ZS-7TgXtBvZ62TCLUazi4p4gK");
		vdriver.findElement(By.id("username")).clear();
		vdriver.findElement(By.id("username")).sendKeys("admin");
		vdriver.findElement(By.id("password")).clear();
		vdriver.findElement(By.id("password")).sendKeys("12345678");
		vdriver.findElement(By.cssSelector("input.button")).click();
		vdriver.get("http://e-justice-test.intrasoft-intl.com/ejusticeportalbackend/ecasLogin.do?back=Login");
		vdriver.findElement(By.name("proceed")).click();
		Thread.sleep(3000);
	}
	
	public static boolean IsTestElementPresent(WebDriver driver, String xpathExpression)
	{
	try
	{
	driver.findElement(By.xpath(xpathExpression));
	return true;
	}
	catch (NoSuchElementException e)
	{
		
	return false;
	}
	
	}
	
}
