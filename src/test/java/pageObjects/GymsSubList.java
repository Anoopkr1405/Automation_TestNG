package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelSheets;

public class GymsSubList extends BasePage{

	public GymsSubList(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//span[text()='Maybe Later']")
	WebElement handlePopup;
	
	@FindBy(xpath="//div[text()='Gym']")
	WebElement click_Gym;
	
	@FindBy(xpath="//ul[@id=\"filter_ul\"]/li")
	List<WebElement> SubList;
	
	@FindBy(xpath="//*[@class = 'jsx-6ab5af3a8693e5db animlabel']")
	List<WebElement> dropDown;
	
	
	public void handlePopupWindow() {
		
		handlePopup.click();
}
	
	public void FindGym() throws InterruptedException {
		click_Gym.click();
		//Thread.sleep(3000);
	}

	public void printGymSubList() throws InterruptedException, IOException {
		Thread.sleep(2000);
		System.out.println("\033[0;1m" +"Sub-Menu items Of Gyms are:"+"\033[0m");
		for(WebElement list:SubList) {
			System.out.println(list.getText());
			
			}
		ExcelSheets.writeGymSubList(SubList);
		//Thread.sleep(2000);
	}
	
	public void subMenuDetails() throws IOException {
	    List<String> sortList = new ArrayList<>();
	    List<String> amenitiesList = new ArrayList<>();
	    List<String> ratingsList = new ArrayList<>();

	    for (int i = 0; i < SubList.size(); i++) {
	        String Text = SubList.get(i).getText();
	        if (Text.contains("Sort")) {
	            System.out.println("\033[0;1m" + "-------------Sort By Dropdown List----------" + "\033[0m");
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].click();", SubList.get(i));
	            for (int j = 0; j < dropDown.size(); j++) {
	                String sort = dropDown.get(j).getText();
	                System.out.println(sort);
	                sortList.add(sort);
	            }
	        }

	        if (Text.contains("Amenities")) {
	            System.out.println("\033[0;1m" + "-------------Amentities Dropdown List----------" + "\033[0m");
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].click();", SubList.get(i));
	            for (int j = 0; j < dropDown.size(); j++) {
	                String amenities = dropDown.get(j).getText();
	                System.out.println(amenities);
	                amenitiesList.add(amenities);
	            }
	        }

	        if (Text.contains("Ratings")) {
	            System.out.println("\033[0;1m" + "-------------Ratings Dropdown List----------" + "\033[0m");
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("arguments[0].click();", SubList.get(i));
	            for (int j = 0; j < dropDown.size(); j++) {
	                String ratings = dropDown.get(j).getText();
	                System.out.println(ratings);
	                ratingsList.add(ratings);
	            }
	        }
	    }

	    ExcelSheets.writeGymSubMenu(sortList, amenitiesList, ratingsList);
	}
}
