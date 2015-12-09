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
import org.openqa.selenium.support.ui.*;

//import com.movierama.MovieRamaUtilities;

public class Dynforms01 { 
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

	public void accessDynamicFormsMemberStateAndFormFamily() {
		/**
		 * Access Dynamic forms
		 */
		driver.get(Utilities.baseUrl
				+ "/ejusticeportalbackend/showDynamicForms.do");
		System.out.println("Manage Dynamic Forms has been accessed");
		/**
		 * Select Member states: Bulgaria
		 */
		
			
		new Select(driver.findElement(By.id("memberStateId")))
				.selectByVisibleText("Bulgaria");
		System.out.println("Member State Select item found");
		System.out.println("Bulgaria selected");

		new Select(driver.findElement(By.id("formId")))
				.selectByVisibleText("European Payment Order");
		System.out.println("Form Families select box found");
		System.out.println("EPO clicked");

		/**
		 * Verify that the "Activate fusion map" is disabled
		 *
		 */
		assertTrue(!driver.findElement(By.id("formNameBean")).isEnabled());
	}

	public void accessDynamicFormsFormFamilyAndFormId() {
		/**
		 * Access Dynamic forms
		 */
		driver.get(Utilities.baseUrl
				+ "/ejusticeportalbackend/showDynamicForms.do");
		System.out.println("Manage Dynamic Forms has been accessed");
		/**
		 * Select Form Family: ToE
		 */
		new Select(driver.findElement(By.id("formId")))
				.selectByVisibleText("Taking of Evidence");
		System.out.println("Form Families accessed");
		System.out.println("Taking of Evidence found");

		new Select(driver.findElement(By.id("formNameId")))
				.selectByVisibleText("Form A");
		System.out.println("Form ids box found");
		System.out.println("ToE A clicked");

		/**
		 * Verify that the "Active check box" is disabled
		 *
		 */
		assertTrue(!driver.findElement(By.name("dynamicFormMSBean"))
				.isEnabled());
	}

	/**
	 * Regression test on successful save of the "Active" box It verifies that
	 * the change is saved for Bulgaria > EPO in both cases (box is ticked or
	 * unticked)
	 * 
	 * @throws InterruptedException
	 */
	private void testManualTabActive() throws InterruptedException {
		/**
		 * "Active" check box is ticked, so it is unticked and changes are saved
		 */
		if (driver.findElement(By.id("dynamicFormMSBeanmsActive")).isSelected()) {
			System.out
					.println("I am going to untick the active box for Bulgaria/EPO");
			driver.findElement(By.id("dynamicFormMSBeanmsActive")).click();
			System.out.println("active box for Bulgaria/EPO unticked");
			/**
			 * Save changes
			 */
			driver.findElement(By.id("updatebtn")).click();
			/**
			 * Verify that the changes have been saved
			 */
			if (driver
					.findElement(
							By.xpath("//ul[contains(text(),'Dynamic Forms data updated successfully.')]"))
					.isDisplayed()) {
				System.out.println("Succesful Update");
				Thread.sleep(3000);
				/**
				 * verify that the "Active" element is now unticked
				 */
				assertTrue(!driver.findElement(
						By.id("dynamicFormMSBeanmsActive")).isSelected());
			}
			/**
			 * Verify that the changes have been saved, when clicking on MS,
			 * page is refreshed
			 */
			new Select(driver.findElement(By.id("memberStateId")))
					.selectByVisibleText("Bulgaria");
			System.out.println("Member State Select item found");
			System.out.println("Bulgaria re-selected");

			assertTrue(!driver.findElement(By.id("dynamicFormMSBeanmsActive"))
					.isSelected());
			System.out.println("Succesful update is cross checked");
			Thread.sleep(3000);
		}
		/**
		 * "Active" check box is unticked, so it is ticked and changes are saved
		 */
		else {
			System.out
					.println("I am going to tick the active box for Bulgaria/EPO");
			driver.findElement(By.id("dynamicFormMSBeanmsActive")).click();
			System.out.println("active box for Bulgaria/EPO ticked");
			/**
			 * Save changes
			 */
			driver.findElement(By.id("updatebtn")).click();
			/**
			 * Verify that the changes have been saved
			 */
			if (driver
					.findElement(
							By.xpath("//ul[contains(text(),'Dynamic Forms data updated successfully.')]"))
					.isDisplayed()) {
				System.out.println("Succesful Update");
				Thread.sleep(6000);
			}
			/**
			 * verify that the "Active" element is now ticked
			 */
			assertTrue(driver.findElement(By.id("dynamicFormMSBeanmsActive"))
					.isSelected());
			/**
			 * Verify that the changes have been saved
			 */
			new Select(driver.findElement(By.id("memberStateId")))
					.selectByVisibleText("Bulgaria");
			System.out.println("Member State Select item found");
			new Select(driver.findElement(By.id("memberStateId")))
					.selectByVisibleText("Bulgaria");

			System.out.println("Bulgaria re-selected");
			assertTrue(driver.findElement(By.id("dynamicFormMSBeanmsActive"))
					.isSelected());
			System.out.println("Succesful update is cross checked");
			Thread.sleep(3000);
		}
	}

	/**
	 * regression test on successful save of the "Activate Fusion Map" box. It
	 * verifies that the change is saved for ToE > A in both cases (box is
	 * ticked or unticked)
	 * 
	 * @throws InterruptedException
	 */
	private void testManualTabActiveFusionMap() throws InterruptedException {
		/**
		 * "Fusion Map" check box is ticked for ToE A so it is unticked and
		 * changes are saved
		 */
		if (driver.findElement(By.id("formNameBeanHasFusionMap")).isSelected()) {
			System.out
					.println("I am going to untick the FusionMap box for ToE/A");
			driver.findElement(By.id("formNameBeanHasFusionMap")).click();
			System.out.println("FusionMap box for ToE/A has been unticked");
			/**
			 * Save changes
			 */
			driver.findElement(By.id("updatebtn")).click();
			/**
			 * Verify that the changes have been saved
			 */
			if (driver
					.findElement(
							By.xpath("//ul[contains(text(),'Dynamic Forms data updated successfully.')]"))
					.isDisplayed()) {
				System.out.println("Succesful Update");
				Thread.sleep(3000);
				/**
				 * verify that the "FusionMap" element has successfully been
				 * unticked after the update button was clicked
				 */
				assertTrue(!driver.findElement(
						By.id("formNameBeanHasFusionMap")).isSelected());
			}
			/**
			 * Verify that the changes have been saved by refreshing (access the
			 * ToE)
			 */
			new Select(driver.findElement(By.id("formId")))
					.selectByVisibleText("Taking of Evidence");
			System.out.println("Form Family Select item found");
			System.out.println("ToE re-selected");

			assertTrue(!driver.findElement(By.id("formNameBeanHasFusionMap"))
					.isSelected());
			System.out.println("Succesful update is cross checked");
			Thread.sleep(3000);
		}
		/**
		 * "FusionMap" check box is unticked, so it is ticked and changes are
		 * saved
		 */
		else {
			System.out
					.println("I am going to tick the Activate FusionMap for selected Form (ToE/A)");
			driver.findElement(By.id("formNameBeanHasFusionMap")).click();
			System.out.println("Fusion Map box for ToE/A ticked");
			/**
			 * Save changes
			 */
			driver.findElement(By.id("updatebtn")).click();
			/**
			 * Verify that the changes have been saved
			 */
			if (driver
					.findElement(
							By.xpath("//ul[contains(text(),'Dynamic Forms data updated successfully.')]"))
					.isDisplayed()) {
				System.out.println("Succesful Update");
				Thread.sleep(3000);
			}
			/**
			 * verify that the "Fusion Map for ToE/A" element is now ticked
			 */

			assertTrue(driver.findElement(By.id("formNameBeanHasFusionMap"))
					.isSelected());
			/**
			 * Verify that the changes have been saved by refreshing (access the
			 * ToE)
			 */
			new Select(driver.findElement(By.id("formId")))
					.selectByVisibleText("Taking of Evidence");
			System.out.println("Form Family Select item found");

			System.out.println("TOE re-selected");
			assertTrue(driver.findElement(By.id("formNameBeanHasFusionMap"))
					.isSelected());
			System.out.println("Succesful update is cross checked");
			Thread.sleep(3000);
		}
	}

	@Test
	public void testManualTab() {
		// First the user must login to the back-end
		try {
			Utilities.LoginBackend(driver);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			verificationErrors.append(e1.toString());
		}
		System.out.println("Succesful login");
		accessDynamicFormsMemberStateAndFormFamily();

		for (int i = 1; i <= 2; i++) {
			try {
				testManualTabActive();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				verificationErrors.append(e.toString());
			}
		}
		driver.findElement(By.name("reset")).click();
		accessDynamicFormsFormFamilyAndFormId();

		for (int i = 1; i <= 2; i++) {
			try {
				testManualTabActiveFusionMap();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				verificationErrors.append(e.toString());
			}
		}
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
