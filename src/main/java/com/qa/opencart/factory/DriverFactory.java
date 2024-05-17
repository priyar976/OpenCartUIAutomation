package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {
	
	
	Properties prop;
	OptionsManager om;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static String highlight;
	
	public WebDriver initDriver(Properties prop) {
		
		om = new OptionsManager(prop);
		
		String browserName = prop.getProperty("browser");
		
		Log.info("Current browser selection : "+browserName);
		
		highlight = prop.getProperty("highlight");
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(om.getChromeOptions()));			
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(om.getFirefoxOptions()));
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(om.getEdgeOptions()));
			break;

		default:
			
			Log.error("Incorrect browser selection! "+browserName);
			throw new BrowserException("NO BROWSER FOUND");
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		
		String envName = System.getProperty("env");
		
		if(envName==null)
			envName="qa";
		try {
		switch (envName.toLowerCase().trim()) {
		
		case "qa":
			ip = new FileInputStream("src/test/resource/config/config.qa.properties");
			break;
		case "dev":
			ip = new FileInputStream("src/test/resource/config/config.dev.properties");
			break;
		case "uat":
			ip = new FileInputStream("src/test/resource/config/config.uat.properties");
			break;
		case "prod":
			ip = new FileInputStream("src/test/resource/config/config.properties");
			break;

		default:
			
			Log.info("Please pass correct environment...");
			throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND);
		
		}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		Log.info("---Testing in environment--- "+envName);
		return prop;
	}
	
	/**
	 * take screenshot
	 */
	
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);//temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
