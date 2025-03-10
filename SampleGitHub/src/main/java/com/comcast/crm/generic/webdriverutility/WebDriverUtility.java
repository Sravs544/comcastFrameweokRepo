package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	// implicit wait

	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	// explicit wait

	public void waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// switch to child window

	public void switchNewBrowserTab(WebDriver driver, String partialURL) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
		String windowID = it.next();
		driver.switchTo().window(windowID);
		String actUrl = driver.getCurrentUrl();
		if (actUrl.contains(partialURL)) {
		break;
			}
		}
	}

	// switch to parent window
	public void switchToTabOnTitle(WebDriver driver, String partialTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);
			String actUrl = driver.getTitle();
			if (actUrl.contains(partialTitle)) {
				break;
			}
		}
	}

	// switch to frame
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrame(WebDriver driver, String nameID) {
		driver.switchTo().frame(nameID);
	}

	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}
	// switch to alert

	public void switchToAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void switchToAlertAndCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	// select class

	public void select(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByContainsVisibleText(text);
	}

	public void select(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	// mouse actions
	public void mousoveOnElement(WebDriver driver, WebElement element) {

		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}

	public void doubleclick(WebDriver driver, WebElement element) {

		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}

}
