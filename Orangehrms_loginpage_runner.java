package test;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Orange_hrms.Add_Emp;
import Orange_hrms.loginpage;
import utili.utilities;

public class Orangehrms_loginpage_runner extends utilities {

	public class test extends loginpage {
		@BeforeTest()
		public void setup(){
			loginpage bro=new loginpage();
			bro.browger();
		}
		@AfterTest()
		public void close() throws IOException {
			loginpage.getsceenshot();
			driver.quit();
		}



		@Test(dataProvider="data")
		public void OpenHomePage(String username,String password,String input) throws IOException {
			test run= new test();

			String result=run.log(username,password,input);
			System.out.println(result);

			if(username.isBlank()|| password.isBlank()) {
				utilities.window();
				Assert.assertEquals( result,"Required","Wrong ErrorMessage for Blank Username/psasword!!!");
			}
			else if(input.equals("invaild")) {
				utilities.window();
				Assert.assertEquals(result,"Invalid credentials","Wrong ErrorMessage for invaild Username/psasword!!!");
			}
			else if(input.equals("vaild")) {
				utilities.window();
				Assert.assertEquals(result,"Dashboard","Wrong ErrorMessage for invaild Username/psasword !!!");
			}
		}

		@Test(dataProvider="details")
		public void Add_Employee(String admin,String status,String username,String password) throws InterruptedException, IOException {

			loginpage login = new loginpage();
			login.log("Admin", "admin123", "valid");
			Thread.sleep(10);
			String refaral_emp_name=	Add_Emp.File_details( admin,status, username, password);
			System.out.println("refaral_emp_name: "+refaral_emp_name);
			
			Thread.sleep(5000l);
			LinkedList<String> Details=new LinkedList<String>(); 
			Details.addAll(	Add_Emp.user_verify(username,admin,refaral_emp_name,status));
			System.out.println(Details);
		}




		@DataProvider (name="data")
		public String [][] information()
		{
			String [][] data= {
					{"Admin","admin123","valid"},
					{"Admin"," ","Blank"},
					{" ","admin123 ","Blank"},
					{"demo","demo123","invalid"}
			};
			return data;  
		}
		@DataProvider (name="details")
		public String [][] details()
		{
			String username ="anuj";
			Date date=new Date();
			username=username+"_"+date.toString().replaceAll(" ", "_").replaceAll(":", "_");
			
			String [][] data= {
					{"Admin","Enabled",username,"josh@202"}		
			};
			return data;  
		}

	}
}
