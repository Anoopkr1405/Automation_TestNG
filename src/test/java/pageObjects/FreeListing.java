package pageObjects;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelSheets;

public class FreeListing extends BasePage{
	static Properties p;

	public FreeListing(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//a[@href='https://www.justdial.com/Free-Listing?cta_from=W_hmpge_web_header_freelisting']")
	WebElement clickFreeListing;
	
	@FindBy(xpath="//input[@class='entermobilenumber_input__eCrdc input fw500']")
	WebElement EnterData;
	
	@FindBy(xpath="//button[@class='primarybutton undefined']")
	WebElement clickStartNow;
	
	@FindBy(xpath="//span[@class='undefined entermobilenumber_error__text__uPM09']")
	WebElement invalidMsg;
	
	@FindBy(xpath="//a[@class='header_logo__landing___K5b4']")
	WebElement navBack;
	
	
	public void LaunchFreeListing() {
		clickFreeListing.click();
		}
	
	public void enterWrongDetail() throws InterruptedException {
		EnterData.click();
		Thread.sleep(2000);
		 String number =ExcelSheets.readSearchQueryFromExcel("ReadCarWashing.xlsx", "Sheet1", 0, 1);
		    EnterData.sendKeys(number);
		
	}
	
	public void clickStartNowBtn() throws InterruptedException {
		Thread.sleep(2000);
		clickStartNow.click();
	}
	
	public void printInvalidMsg() throws InterruptedException, IOException {
		Thread.sleep(2000);
		String result=invalidMsg.getText();
		System.out.println("\033[0;1m"+"Invalid Message: "+"\033[0m"+"\033[31m"+result+"\033[0m");
		System.out.println("-----------------------------------------------------------");
	}
	
	public void navigateBack() {
		navBack.click();
	}
	

}
