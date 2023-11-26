package utili;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class setup  {

	public static WebDriver driver;
	 Properties prop;
	
	public   setup()  {
		
	String filename= System.getProperty("user.dir")+"\\src\\test\\resources\\data.properties";
	try {
		
		FileInputStream  fis= new FileInputStream(filename);
		prop=new Properties();
			prop.load(fis);
			}
	catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("basic details file not found"+filename);
		}
	} 
	
	public void browger() {
		String urlname=prop.getProperty("URL");
	 String browsername=prop.getProperty("Browser");
	if(browsername.equalsIgnoreCase("chrome"))
	{	
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
		driver =new ChromeDriver(co);
		driver.get(urlname);
		utilities.window();
	}
	else if(browsername.equalsIgnoreCase("edge"))
	{	
		WebDriverManager.edgedriver().setup();
		driver =new EdgeDriver();
		driver.get(urlname);
		utilities.window();
	}
	else if(browsername.equalsIgnoreCase("firefox"))
	{	
		WebDriverManager.firefoxdriver().setup();
		driver =new FirefoxDriver();
		driver.get(urlname);
		utilities.window();
	}
	else {
		System.out.println("please inter vaild weddriver");
	}
	if(prop.getProperty("maximize").equalsIgnoreCase("true"))
		driver.manage().window().maximize();
	}

}
