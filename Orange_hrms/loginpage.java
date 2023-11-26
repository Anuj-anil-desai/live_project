package Orange_hrms;

import java.io.IOException;

import org.openqa.selenium.By;

import utili.utilities;

public class loginpage extends utilities {

	By username= By.name("username");
	By password= By.name("password");
	By clickButton= By.xpath("//button[@type='submit']");
	
	By newpage=By.xpath("//div[@class='oxd-topbar-header-title']//h6");	
	By textError=By.xpath("//form//span");
	By invaildError= By.xpath("(//div[@class='orangehrm-login-error']//p)[1]");
	
	
	public  String log(String user,String pass,String input) throws IOException {
		
		String result=null;
		if(input.equals("valid")) {
			utilities.window();
			driver.findElement(username).sendKeys(user);
			driver.findElement(password).sendKeys(pass);
			driver.findElement(clickButton).click();
			result= loginpage.errormessage(newpage);
		}
		else if(input.equals("Blank")) {
			driver.findElement(username).sendKeys(user);
			driver.findElement(password).sendKeys(pass);
			driver.findElement(clickButton).click();
			result= loginpage.errormessage(textError);	
		}	
		else if(input.equals("invalid")) {
			driver.findElement(username).sendKeys(user);
			driver.findElement(password).sendKeys(pass);
			driver.findElement(clickButton).click();
			result= loginpage.errormessage(invaildError);
		}
		return result;	
			}

	
	
}
