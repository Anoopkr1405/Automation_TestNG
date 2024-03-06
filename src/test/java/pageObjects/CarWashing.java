package pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelSheets;

public class CarWashing extends BasePage {

	public CarWashing(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//span[text()='Maybe Later']")
	WebElement handlePopup;
	
	@FindBy(xpath="//input[@id='city-auto-sug']")
	WebElement Location;
	
	@FindBy(xpath="//div[@class='location_text font14 fw400 color007']")
	WebElement detectLocation;
	
	@FindBy(xpath="//input[@id='main-auto']")
	WebElement search_CarWash;
	
	@FindBy(xpath="//li[@id='react-autowhatever-main-auto-suggest--item-0']")
	WebElement carWashAutoSuggestion;
	
	@FindBy(xpath="//div[@class='search_button']")
	WebElement clickSrchBtn;

	@FindBy(xpath="//div[@class='jsx-abd03633235cbc61 all_filter_container']")
	WebElement AllFilters;
	
	@FindBy(xpath="//span[@aria-label='4.0+']")
	WebElement selectRating;
	
	@FindBy(xpath="//button[text()='Apply Filters']")
	WebElement applyFilter;
	
	@FindBy(xpath="//div[@class='jsx-3349e7cd87e12d75 resultbox_totalrate mr-6 font14 fw700 colorFFF']")
	List<WebElement> checkRating;
	
	@FindBy(xpath="//div[@class='jsx-3349e7cd87e12d75 resultbox_countrate ml-12 mr-12 font14 fw400 color777']")
	List<WebElement> Customer_vote;
	
	@FindBy(xpath="//div[@class=\"jsx-d50474da94ce5ee0 jd_modal_close jdicon\"]")
	WebElement afterFilterPopup;
	
	@FindBy(xpath="//div[@class='jsx-3349e7cd87e12d75 resultbox_title_anchor  line_clamp_1']")
	List<WebElement> CarSrvcs_Name;
	
	@FindBy(xpath="//span[text()='Show Number']")
	List<WebElement> show_number;
	
	@FindBy(xpath="//div[contains(@class,'button_flare')]")
	List<WebElement> contact_flare;
	
	@FindBy(xpath="//span[contains(@class,'callcontent')]")
	List<WebElement> contact_Num;
	
	//div[contains(@class,'button_flare')]/following::span[contains(@class,'callcontent')]
	
	public void handlePopupWindow() {
		try {
			handlePopup.click();
			System.out.println("Popup appeared and Handled");
		}
		catch(Exception e) {
			System.out.println("Popup didn't appeared");
		}
		
	}
	
	
	public void enterLocation() throws InterruptedException {
		Location.clear();
		Location.click();
		Thread.sleep(2000);
	}
	
	public void selectLocation() {
		detectLocation.click();
	}
	
	public void enterCarWashServices() throws InterruptedException {
		search_CarWash.clear();
		String Text=ExcelSheets.readSearchQueryFromExcel("ReadCarWashing.xlsx","Sheet1", 0, 0);
		search_CarWash.sendKeys(Text);
		Thread.sleep(2000);
	}
	
	public void selectButton() throws InterruptedException {
		carWashAutoSuggestion.click();
		Thread.sleep(2000);
	}
	public void clickSearchButton() throws InterruptedException
	{
		clickSrchBtn.click();
		Thread.sleep(2000);
		
	}
	
	public void clickAllFilters() throws InterruptedException {
		AllFilters.click();
		System.out.println("All filters btn clicked");
		Thread.sleep(2000);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",selectRating);
		
	}
	
	public void clickRating() throws InterruptedException {
		selectRating.click();
		//Thread.sleep(2000);
	}
	public void clickApplyFilter() throws InterruptedException {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",applyFilter);
		//Thread.sleep(3000);
		
	}

	public void handleLastPopupWindow() {
		try {
		afterFilterPopup.click();
		Alert alert = driver.switchTo().alert();
        // Dismiss the alert to close the popup
        alert.dismiss();
        System.out.println("Popup appeared & handled");
		}
		catch(Exception e) {
			System.out.println("Popup doesn't appeared");
		}
		System.out.println("----------------------------------------------------");
	}
	
	
	public void clickContactFlareBtn() throws InterruptedException {

			for(int i=0;i<show_number.size();i++) {
				String text=show_number.get(i).getText();
				if(text.equalsIgnoreCase("Show Number")) {			
			      contact_flare.get(i).click();
			      Thread.sleep(2000);
			}
		}
			Thread.sleep(2000);	
	}
		
	public void printCarSrvcNames() throws IOException, InterruptedException {
		int cust_Vote = 0;
		float ratingVal=0;
		System.out.println("\033[0;1m" +"Customer Vote is greater than 20 and Rating is greater than 4.0"+ "\033[0m");
		for(int i = 0; i<5; i++) {
			WebElement Rating = checkRating.get(i);
			String ratingText=Rating.getText();
			ratingVal=Float.parseFloat(ratingText);
			//System.out.println(ratingVal);
		
		WebElement votes = Customer_vote.get(i);
			
		String votingText=votes.getText();
		String votingValue=votingText.replaceAll("\\D+", "");
		 cust_Vote=Integer.parseInt(votingValue);
		 //System.out.println(cust_Vote);
		
		if(ratingVal>4.0 && cust_Vote>20) {
		 
			String name=CarSrvcs_Name.get(i).getText();
			String contactNum=contact_Num.get(i).getText();
			//String rating=ratingVal.getText();
			System.out.println("Result " + (i + 1) + ": " + name+" - "+"Phone Number:"+contactNum+" - "+"Rating:"+ratingVal
					+"-"+"Customer Vote:"+cust_Vote);
			}
		}
		//contact_Num = driver.findElements(By.xpath("//span[@class='jsx-3349e7cd87e12d75 callcontent']"));
		ExcelSheets.writeCarSrvcsDetails(CarSrvcs_Name,contact_Num);
		Thread.sleep(2000);
		System.out.println("------------------------------------------------------------------------");
		 
		
		}
		
		
	}

	


