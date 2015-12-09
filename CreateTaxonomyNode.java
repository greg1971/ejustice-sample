package com.backoffice;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateTaxonomyNode {

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void AddNewTaxonomy() throws InterruptedException {
		List<WebElement> elements, elements2;

		// First the user must login to the back-end
		try {
			Utilities.LoginBackend(driver);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			verificationErrors.append(e1.toString());
		}
		System.out.println("Succesful login");

		driver.get(Utilities.baseUrl
				+ "/ejusticeportalbackend/taxonomymanagement.do");
		System.out.println("Manage Taxonomies has been accessed");

//		driver.findElement(By.xpath("html/body/form/div[2]/div[2]/div/div/div/div[3]/span/a")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Law')]")).click();		
		
		Thread.sleep(5000);

		driver.findElement(By.id("addButtonId")).click();
		System.out.println("I entered the Add New Taxonomy page");

		elements = driver.findElements(By
				.xpath("//*[starts-with(@name,'strName')]"));
		
		elements2 = driver.findElements(By
				.xpath("//*[starts-with(@name,'strRolloverText')]"));

		System.out.println("Number of found elements is:" + elements.size());
		System.out.println("Number of found elements2 is:" + elements2.size());

		for (WebElement ele : elements) {

			try {
				ele.sendKeys("testSanityAutomated");
			} catch (Error e) {
				verificationErrors.append("error in the string comparison"
						+ e.toString());
			}
		}
		
		for (WebElement ele2 : elements2) {

			try {
				ele2.sendKeys("testSanityAutomatedRollover");
			} catch (Error e) {
				verificationErrors.append("error in the string comparison"
						+ e.toString());
			}
		}
		
		driver.findElement(By.name("Updatenew")).click();
		System.out.println("The new taxonomy has been creted Succesfully");
	}

	@After
	public void tearDown() throws Exception {

		driver.quit();
		/**
		 * Any potential errors are logged into a file
		 */
		String verificationErrorString = verificationErrors.toString();
		Utilities.writeMsgToFile(verificationErrorString,
				"C:\\temp\\Dynforms01.txt");
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
