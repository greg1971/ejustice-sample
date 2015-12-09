package com.backoffice;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RemoveTaxonomyNode {
	
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

	@Test
	public void test() {
		
		// First the user must login to the back-end
		try {
			Utilities.LoginBackend(driver);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			verificationErrors.append(e1.toString());
		}
		System.out.println("Succesful login");
		/*
		driver.get(Utilities.baseUrl
				+ "/ejusticeportalbackend/taxonomymanagement.do");
		*/
		
		driver.navigate().to(Utilities.baseUrl
				+ "/ejusticeportalbackend/taxonomymanagement.do");
		
		System.out.println("Manage Taxonomies has been accessed");

		//Click on the cross + icon next to "Law"in order to unfold the contents of the taxonomy 
		driver.findElement(By.xpath("html/body/form/div[2]/div[2]/div/div/div/div[1]/span/span[2]")).click();
		driver.findElement(By.xpath("//a[.='testSanityAutomate...']")).click();
//		driver.findElement(By.xpath("//a[contains(text(),'testSanityAutomate...')]")).click();
		driver.findElement(By.id("updateButtonId")).click();
		System.out.println("I entered the Update Taxonomy page for deleting the node");
		driver.findElement(By.name("Delete")).click();
		System.out.println("Taxonomy node is going to be deleted");
		
		//click ok on the javascript popup dialog box in order to confirm the delition
		driver.switchTo().alert().accept();
		
	}

}
