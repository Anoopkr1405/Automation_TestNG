package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.FreeListing;
import testBase.BaseClass;

public class TC_002_LauchFreeListing extends BaseClass{
	
	
	@Test(priority=1,groups= {"sanity","master"})
	public void launchFreeListing() throws InterruptedException, IOException {
		FreeListing fl=new FreeListing(driver);
		
		fl.LaunchFreeListing();
		BaseClass.logger.info("FreeListing is Launched.......");
		
		fl.enterWrongDetail();
		BaseClass.logger.info("Wrong detail is given in input box.......");
	}
	
	
	@Test(priority=2,groups= {"sanity","master"})
	public void clickStartNowBtn() throws InterruptedException, IOException {
		
		FreeListing fl=new FreeListing(driver);
		fl.clickStartNowBtn();
		BaseClass.logger.info("Start Now Button is clicked.......");
	}
	
	@Test(priority=3,groups= {"regression","master"})
	public void printInvalidMessage() throws InterruptedException, IOException {
		FreeListing fl=new FreeListing(driver);
		fl.printInvalidMsg();
		BaseClass.logger.info("Invalid Message is printed.......");
	}
	
	@Test(priority=4,groups= {"regression","master"})
	public void navigateBack() {
		FreeListing fl=new FreeListing(driver);
		fl.navigateBack();
		BaseClass.logger.info("Navigating Back to original Window.......");
	}

}
