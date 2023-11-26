

package utili;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class utilities extends setup{
	
	public static String errormessage(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));		
		return wait.until(ExpectedConditions.presenceOfElementLocated(element)).getText();
	}
	
	public static void click(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));		
		 wait.until(ExpectedConditions.presenceOfElementLocated(element)).click();
	}
	public static void file_text(By element,String str) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));		
		 wait.until(ExpectedConditions.presenceOfElementLocated(element)).sendKeys(str);
	}
	
	
	
	public static void window() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	public static void getsceenshot() throws IOException {
		String filename=System.getProperty("user.dir")+"\\src\\main\\resources\\reports\\testresult";
		File screenshot=((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		Date date=new Date();
		filename=filename+"_"+date.toString().replaceAll(" ", "_").replaceAll(":", "_")+".png";
		FileUtils.copyFile(screenshot, new File(filename));
	}

}
