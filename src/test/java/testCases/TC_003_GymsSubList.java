package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import pageObjects.GymsSubList;
import testBase.BaseClass;

public class TC_003_GymsSubList extends BaseClass{
	
	@Test(priority=1,groups= {"sanity","master"})
	public void clickGymSection() throws InterruptedException, IOException {
		
		GymsSubList gs=new GymsSubList(driver);
		gs.FindGym();
		logger.info("Type gyms in search box.......");
	}
	
	@Test(priority=2,groups={"regression","master"})
	public void printGymSubList() throws InterruptedException, IOException {
		GymsSubList gs=new GymsSubList(driver);
		
		gs.printGymSubList();
		logger.info("Sub-Menu of Gyms are Printed.......");

	}
	
	@Test(priority=3,groups= {"regression","master"})
	public void printSubMenuList() throws IOException {
		GymsSubList gs=new GymsSubList(driver);
		gs.subMenuDetails();
		logger.info("Sub-Menu dropdown items list is printed.....");
		
	}

}
