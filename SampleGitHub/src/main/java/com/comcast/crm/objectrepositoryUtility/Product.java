package com.comcast.crm.objectrepositoryUtility;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Product {

	@FindBy(xpath="//input[@alt='Create Product...']")
	private WebElement createproductImgBtn;
	
	@FindBy(name="search")
	private WebElement ele2;

}
