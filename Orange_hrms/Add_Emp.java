package Orange_hrms;

import java.time.Duration;
import java.util.LinkedList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import utili.setup;
import utili.utilities;

public class Add_Emp extends setup {

	static By Admin =By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']");

	static By user_role = By.xpath("(//div[@class='oxd-select-text-input'])[1]");

	static By Employee  = By.xpath("//input[@placeholder='Type for hints...']");
	static By status = By.xpath("(//div[@class='oxd-select-text-input'])[2]");	
	static By username=By.xpath("(//input[@autocomplete='off'])[1]");
	static By password=By.xpath("(//input[@autocomplete='off'])[2]");
	static By confirm_pass=By.xpath("(//input[@autocomplete='off'])[3]");
	static By save_button=By.xpath("//button[@type='submit']");
	static By pop_messaage=By.xpath("(//div[@class='oxd-toast-start']//p)[2]");
	static By Add_button=By.xpath("(//button[@type='button'])[5]");


	// (//div[@role='row'])[3]//div[@role='cell'][2]


	public static String File_details(String admin,String Status,String user,String pass) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		String Admin_name=driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).getText();
		utilities.click(Admin);
		utilities.click(Add_button);
		Thread.sleep(2000l);

		driver.findElement(user_role).click();	
		driver.findElement(user_role).sendKeys(Keys.ARROW_UP,Keys.ENTER);
		driver.findElement(Employee).click();	
		driver.findElement(Employee).sendKeys(Admin_name);
		Thread.sleep(5000l);
		driver.findElement(Employee).sendKeys(Keys.ARROW_UP,Keys.ENTER);
		driver.findElement(status).click();
		driver.findElement(status).sendKeys(Keys.ARROW_UP,Keys.ENTER);

		Thread.sleep(2000l);

		driver.findElement(username).sendKeys(user);
		driver.findElement(password).sendKeys(pass);

		driver.findElement(confirm_pass).sendKeys(pass);
		utilities.click(save_button);

		String message=utilities.errormessage(pop_messaage);
		System.out.println(message);

		return Admin_name ;

	}

	public static LinkedList<String> user_verify(String username,String user_Role,String empname,String status) throws InterruptedException {
		Thread.sleep(5000l);
		
		
		
		int rowcount=driver.findElements(By.xpath("//div[@class='oxd-table-card']")).size();		
		byte count=0;
		LinkedList<String> Details=new LinkedList<String>(); 

		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,250)", "");

		for(Byte i=1;i<=rowcount;i++) {

		String	ui_user_name=driver.findElement(By.xpath("(//div[@class='oxd-table-body']//div[@role='row'])["+i+"]//div[@role='cell']["+2+"]")).getText();
			Thread.sleep(2000l);
			js.executeScript("window.scrollBy(0,100)", "");


			if(ui_user_name.equals(username)) {
				System.out.println("user name from ui : "+ui_user_name);
				Details.add(ui_user_name);

				String value=null;
				for(byte j=3;j<=5;j++) {
					value=driver.findElement(By.xpath("(//div[@class='oxd-table-body']//div[@role='row'])["+i+"]//div[@role='cell']["+j+"]")).getText();
					Thread.sleep(2000l);

					if(value.equals(empname)) {
						System.out.print("Empname ="+value);
						count=+1;
						Details.add(value);

					}
					else if(value.equals(status)) {
						System.out.print("Status ="+value);
						count=+1;
						Details.add(value);

					}
					else if(value.equals(user_Role)) {
						System.out.print("Username="+value);
						count=+1;
						Details.add(value);

					}
				}
				break;
			}
			
		}




		if(count!=4) {
			System.out.println("One String not found");
			Assert.assertNull("Elemeent not found");
			return Details;

		}
		System.out.println("complete verification");
		return Details;
	}


}
