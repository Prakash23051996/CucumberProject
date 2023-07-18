package Basemethods;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.base.Function;

import Wait.FrameworkConstant;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.*;

public class BaseClass {

	protected static WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	static ExtentHtmlReporter htmlReporter;

	public static WebElement sendKeys(String locatorType, String locator, String value) throws Exception {
		WebElement element = null;
		try {
			if (locatorType.equalsIgnoreCase("id")) {
				element = driver.findElement(By.id(locator));
				element.sendKeys(value);
			} else if (locatorType.equalsIgnoreCase("name")) {
				element = driver.findElement(By.name(locator));
				element.sendKeys(value);
			} else if (locatorType.equalsIgnoreCase("Xpath")) {
				element = driver.findElement(By.xpath(locator));
				element.sendKeys(value);
			} else if (locatorType.equalsIgnoreCase("ClassName")) {
				element = driver.findElement(By.className(locator));
				element.sendKeys(value);
			} else if (locatorType.equalsIgnoreCase("cssSelector")) {
				element = driver.findElement(By.cssSelector(locator));
				element.sendKeys(value);
			} else if (locatorType.equalsIgnoreCase("linkText")) {
				element = driver.findElement(By.linkText(locator));
				element.sendKeys(value);
			} else if (locatorType.equalsIgnoreCase("partialLinkText")) {
				element = driver.findElement(By.partialLinkText(locator));
				element.sendKeys(value);
			} else {
				element = driver.findElement(By.tagName(locator));
				element.sendKeys(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error Message:" + e.getMessage());
		}
		info(getCurrentDateAndTime("dd/MM/yyyy hh:mm:ss a") + element.toString());
		return element;
	}

	public static WebElement clickTheElement(String locatorType, String locator) throws Exception {
		WebElement element = null;
		try {
			if (locatorType.equalsIgnoreCase("id")) {
				element = driver.findElement(By.id(locator));
				element.click();
			} else if (locatorType.equalsIgnoreCase("name")) {
				element = driver.findElement(By.name(locator));
				element.click();
			} else if (locatorType.equalsIgnoreCase("Xpath")) {
				element = driver.findElement(By.xpath(locator));
				element.click();
			} else if (locatorType.equalsIgnoreCase("ClassName")) {
				element = driver.findElement(By.className(locator));
				element.click();
			} else if (locatorType.equalsIgnoreCase("cssSelector")) {
				element = driver.findElement(By.cssSelector(locator));
				element.click();
			} else if (locatorType.equalsIgnoreCase("linkText")) {
				element = driver.findElement(By.linkText(locator));
				element.click();
			} else if (locatorType.equalsIgnoreCase("partialLinkText")) {
				element = driver.findElement(By.partialLinkText(locator));
				element.click();
			} else {
				element = driver.findElement(By.tagName(locator));
				element.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error Message:" + e.getMessage());
		}
		info(getCurrentDateAndTime("dd/MM/yyyy hh:mm:ss a") + element.toString());
		return element;
	}

	public static WebDriver browser(String browserType) throws Exception {
		try {
			if (browserType.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserType.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (browserType.equalsIgnoreCase("safari")) {
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();
			} else if (browserType.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browserType.equalsIgnoreCase("internetExplore")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			} else {
				WebDriverManager.chromedriver().setup();
//				ChromeOptions ChromeOptions = new ChromeOptions().addArguments("incognito");
				driver = new ChromeDriver(new ChromeOptions().addArguments("incognito"));
			}
		} catch (Exception exe) {
			exe.printStackTrace();
			throw new Exception(exe.getMessage());
		}
		return driver;
	}

	public static void browserLaunch(String url) throws Exception {
		try {
			driver.get(url);
//			driver.navigate().to(url);
			maximize();
			extentReport();
			test.log(Status.INFO, "Browser launched successfully");
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static void wait(int seconds) throws Exception {
		try {
			Thread.sleep(seconds * FrameworkConstant.GLOBAL_POLL);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static boolean elementClickableWait(String locatorType, String locator) throws Exception {
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, FrameworkConstant.GLOBAL_WAIT);
			if (locatorType.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
			} else if (locatorType.equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
			} else if (locatorType.equalsIgnoreCase("className")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.className(locator)));
			} else if (locatorType.equalsIgnoreCase("name")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.name(locator)));
			} else if (locatorType.equalsIgnoreCase("tagName")) {
				wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locator)));
			} else {
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locator)));
			}
			flag = true;
			return flag;
		} catch (Exception exe) {
			exe.printStackTrace();
			return flag;

		}
	}

	public static void waitForElementToVisibile(String locatorType, String locator) throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, FrameworkConstant.GLOBAL_WAIT);
			if (locatorType.equalsIgnoreCase("xpath")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			} else if (locatorType.equalsIgnoreCase("id")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locator)));
			} else if (locatorType.equalsIgnoreCase("className")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locator)));
			} else if (locatorType.equalsIgnoreCase("name")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locator)));
			} else if (locatorType.equalsIgnoreCase("tagName")) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locator)));
			} else {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locator)));
			}
		} catch (Exception exe) {
			exe.printStackTrace();
			throw new Exception(exe.getMessage());
		}
	}

	public static void waitForPagetoLoad() throws Exception {
		try {
			deleteAllCookies();
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, 120);
			wait.until(pageLoadCondition);
		} catch (Exception exe) {
			exe.printStackTrace();
			throw new Exception(exe.getMessage());
		}
	}

	public void waitForPageLoad(int seconds) {
		Wait<WebDriver> wait = new WebDriverWait(driver, seconds);
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
				System.out.println("Current Window State       : "
						+ String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	public WebElement getElement(String locatorType, String locator) throws Exception {
		WebElement element = null;
		try {
			if (locatorType.equalsIgnoreCase("id")) {
				element = driver.findElement(By.id(locator));
			} else if (locatorType.equalsIgnoreCase("name")) {
				element = driver.findElement(By.name(locator));
			} else if (locatorType.equalsIgnoreCase("Xpath")) {
				element = driver.findElement(By.xpath(locator));
			} else if (locatorType.equalsIgnoreCase("ClassName")) {
				element = driver.findElement(By.className(locator));
			} else if (locatorType.equalsIgnoreCase("cssSelector")) {
				element = driver.findElement(By.cssSelector(locator));
			} else if (locatorType.equalsIgnoreCase("linkText")) {
				element = driver.findElement(By.linkText(locator));
			} else if (locatorType.equalsIgnoreCase("partialLinkText")) {
				element = driver.findElement(By.partialLinkText(locator));
			} else {
				element = driver.findElement(By.tagName(locator));
			}
		} catch (Exception exe) {
			exe.printStackTrace();
			throw new Exception(exe.getMessage());
		}
		info(getCurrentDateAndTime("dd/MM/yyyy hh:mm:ss a") + element.toString());
		return element;
	}

	public static List<WebElement> getElements(String locatorType, String locator) throws Exception {
		List<WebElement> element = null;
		try {
			if (locatorType.equalsIgnoreCase("ID")) {
				element = driver.findElements(By.id(locator));
			} else if (locatorType.equalsIgnoreCase("NAME")) {
				element = driver.findElements(By.name(locator));
			} else if (locatorType.equalsIgnoreCase("Xpath")) {
				element = driver.findElements(By.xpath(locator));
			} else if (locatorType.equalsIgnoreCase("ClassName")) {
				element = driver.findElements(By.className(locator));
			} else if (locatorType.equalsIgnoreCase("CSSSelector")) {
				element = driver.findElements(By.cssSelector(locator));
			} else if (locatorType.equalsIgnoreCase("LinkText")) {
				element = driver.findElements(By.linkText(locator));
			} else if (locatorType.equalsIgnoreCase("PartialLinkText")) {
				element = driver.findElements(By.partialLinkText(locator));
			} else {
				element = driver.findElements(By.tagName(locator));
			}
		} catch (Exception exe) {
			throw new Exception(exe.getMessage());
		}
		info(getCurrentDateAndTime("dd/MM/yyyy hh:mm:ss a") + element.toString());
		return element;
	}

	public static WebElement moveToElementAndClick(String locatorType, String locator) throws Exception {
		WebElement element = null;
		try {
			if (locatorType.equalsIgnoreCase("id")) {
				element = driver.findElement(By.id(locator));
			} else if (locatorType.equalsIgnoreCase("name")) {
				element = driver.findElement(By.name(locator));
			} else if (locatorType.equalsIgnoreCase("Xpath")) {
				element = driver.findElement(By.xpath(locator));
			} else if (locatorType.equalsIgnoreCase("ClassName")) {
				element = driver.findElement(By.className(locator));
			} else if (locatorType.equalsIgnoreCase("cssSelector")) {
				element = driver.findElement(By.cssSelector(locator));
			} else if (locatorType.equalsIgnoreCase("linkText")) {
				element = driver.findElement(By.linkText(locator));
			} else if (locatorType.equalsIgnoreCase("partialLinkText")) {
				element = driver.findElement(By.partialLinkText(locator));
			} else {
				element = driver.findElement(By.tagName(locator));
			}
		} catch (Exception exe) {
			exe.printStackTrace();
			throw new Exception(exe.getMessage());
		}
		Actions ac = new Actions(driver);
		ac.moveToElement(element).build().perform();
		info(getCurrentDateAndTime("dd/MM/yyyy hh:mm:ss a") + element.toString());
		return element;
	}

	public static boolean openNewWindow() throws Exception {
		boolean flag = true;
		try {
			((JavascriptExecutor) driver).executeScript("window.open()");
		} catch (Exception e) {
			flag = false;
			throw new Exception("Error Message:" + e.getMessage());

		}
		return flag;
	}

	public static void javaScriptClick(WebElement element) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static void scrollToElement(WebElement element) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static void scrollUp(WebElement element) throws Exception {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", element);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static Object javaScriptGetAttribut(WebElement element) throws Exception {
		try {
			return ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value')", element);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static Object javaScriptGetAttribute(WebElement elment) throws Exception {
		try {
			return ((JavascriptExecutor) driver).executeScript("argyments[0].getAttribute('value')", elment);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static boolean deleteAllCookies() throws Exception {
		boolean flag = true;
		try {
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			flag = false;
			throw new Exception("Error Message:" + e.getMessage());
		}
		return flag;
	}

	public static String getTitle() throws Exception {
		try {
			return driver.getTitle();
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static String getCurrentURL() throws Exception {
		try {
			return driver.getCurrentUrl();
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static boolean createFolder(String folderName) throws Exception {
		boolean folder = false;
		try {
			File file = new File(FileLocation.path + folderName);
			folder = file.mkdir();
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
		return folder;
	}

	public static String getText(String locatorType, String locator) throws Exception {
		String text;
		try {
			if (locatorType.equalsIgnoreCase("id")) {
				text = driver.findElement(By.id(locator)).getText();
			} else if (locatorType.equalsIgnoreCase("name")) {
				text = driver.findElement(By.name(locator)).getText();
			} else if (locatorType.equalsIgnoreCase("Xpath")) {
				text = driver.findElement(By.xpath(locator)).getText();
			} else if (locatorType.equalsIgnoreCase("ClassName")) {
				text = driver.findElement(By.className(locator)).getText();
			} else if (locatorType.equalsIgnoreCase("cssSelector")) {
				text = driver.findElement(By.cssSelector(locator)).getText();
			} else if (locatorType.equalsIgnoreCase("linkText")) {
				text = driver.findElement(By.linkText(locator)).getText();
			} else if (locatorType.equalsIgnoreCase("partialLinkText")) {
				text = driver.findElement(By.partialLinkText(locator)).getText();
			} else {
				text = driver.findElement(By.tagName(locator)).getText();
			}
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
		info(locator);
		return text;
	}

	public static boolean switchToNewWindow() throws Exception {
		boolean flag = true;
		try {
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			info("Parent: " + parentWindow + "\r" + "All Window: " + allWindows);
			for (String newWindow : allWindows) {
				if (!parentWindow.equals(newWindow)) {
					driver.switchTo().window(newWindow);
					info("Window handled successfully");
				}
			}
		} catch (Exception e) {
			flag = false;
			throw new Exception("Error Message:" + e.getMessage());
		}
		return flag;
	}

	public static boolean createNewFile(String extension) throws Exception {
		boolean createNewFile = false;
		try {
			File file = new File(FileLocation.path + extension);
			createNewFile = file.createNewFile();
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
		return createNewFile;
	}

	public static boolean deletefile(String fileName) throws Exception {
		boolean deletefile = false;
		try {
			File file = new File(FileLocation.newPath + fileName);
			deletefile = file.delete();
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
		return deletefile;
	}

	public static boolean emptyFolder(String extension) throws Exception {
		boolean deletedFile = false;
		try {
			File location = new File(FileLocation.path);
			for (File f : location.listFiles()) {
				if (f.getAbsolutePath().contains(extension)) {
					deletedFile = f.delete();
				}
			}
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
		return deletedFile;
	}

	public static boolean moveFileToAnotherFolder(String folderName) throws Exception {
		boolean isFolderMoved = true;
		try {
			Files.move(Paths.get(FileLocation.path + folderName), Paths.get(FileLocation.newPath + folderName));
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
		return isFolderMoved;
	}

	public static boolean checkTheFolder(String folderName) throws Exception {
		boolean folder = false;
		try {
			File file = new File(FileLocation.path + folderName);
			folder = file.isDirectory();
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
		return folder;
	}

	public void fileRename(String path, String renamePath) throws Exception {
		try {
			File fileName = new File(path);
			File rename = new File(renamePath);
			fileName.renameTo(rename);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public void zipFolder() {
		try {
			// The directory to be zipped
			File directoryToZip = new File("C:\\Users\\prakashj\\Pictures\\Downloads");

			// The name of the zip file
			String zipFileName = "New folder.zip";

			// Create a new FileOutputStream
			FileOutputStream fos = new FileOutputStream(zipFileName);

			// Create a new ZipOutputStream
			ZipOutputStream zos = new ZipOutputStream(fos);

			// Call the zipDirectory method
			zipDirectory(directoryToZip, directoryToZip.getName(), zos);

			// Close the ZipOutputStream
			zos.close();

			// Close the FileOutputStream
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void zipDirectory(File directory, String baseName, ZipOutputStream zos) throws IOException {
		// Get a list of all the files in the directory
		File[] files = directory.listFiles();

		// Loop through all the files
		for (File file : files) {
			if (file.isDirectory()) {
				// If the file is a directory, call the zipDirectory method
				String filePath = baseName + "/" + file.getName();
				zipDirectory(file, filePath, zos);
			} else {
				// If the file is not a directory, create a new ZipEntry and write the file to
				// the ZipOutputStream
				String filePath = baseName + "/" + file.getName();
				ZipEntry ze = new ZipEntry(filePath);
				zos.putNextEntry(ze);
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}
				fis.close();
				zos.closeEntry();
			}
		}
	}

	public static String getDataFromPropertyFile(String key) throws Exception {
		try {
			FileInputStream fis = new FileInputStream(FileLocation.propertyFileLocation);
			Properties properties = new Properties();
			properties.load(fis);
			return properties.getProperty(key);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static boolean pdfReaderDoc() throws Exception {
		boolean isFileExists = true;
		File file = new File(FileLocation.pdfPath);
		isFileExists = file.exists();
		if (isFileExists) {
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);
			info(text);
			Files.write(Paths.get("D:\\New folder\\File.txt"), text.getBytes());
		} else {
			info("FAIL:-File Not Found");
		}
		return isFileExists;
	}

	public static String pdfReaderDOC() throws Exception {
		String text = null;
		File file = new File(FileLocation.pdfPath);
		boolean isFileExists = file.exists();
		if (isFileExists) {
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			text = pdfStripper.getText(document);
		} else {
			info("FAIL: File not found");
		}
		return text;
	}

	public static String listOfFileInFolder() throws Exception {
		String fileName = null;
		try {
			File f = new File(FileLocation.path);
			boolean isFileExist = f.exists();
			if (isFileExist) {
				File[] fileList = f.listFiles();
				for (File file : fileList) {
					fileName = file.getName();
				}
			} else {
				info("FAIL: File not found");
			}
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
		return fileName;
	}

	public void selectByValue(String value, WebElement element) throws Exception {
		try {
			Select sec = new Select(element);
			sec.selectByValue(value);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static boolean closeCurrentTab() throws Exception {
		boolean flag = false;
		try {
			driver.close();
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			throw new Exception("Error Message:" + e.getMessage());
		}

	}

//	public static boolean quit() throws Exception {
//		boolean flag = true;
//		try {
//			driver.quit();
//		} catch (Exception e) {
//			flag = false;
//			throw new Exception("Error Message:" + e.getMessage());
//		}
//		return flag;
//	}

	public static boolean maximize() throws Exception {
		boolean flag = true;
		try {
			driver.manage().window().maximize();
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static String getCurrentDateAndTime(String dateAndTimeFormate) throws Exception {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateAndTimeFormate);
			LocalDateTime localDateTime = LocalDateTime.now();
			return dtf.format(localDateTime);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static String takesScreenshot() throws Exception {
		String absolutePath;
		try {
			File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File Dest = new File(
					System.getProperty("user.dir") + "\\ScreenShot\\screenshot" + System.currentTimeMillis() + ".png");
			FileUtils.copyFile(screenshotAs, Dest);
			absolutePath = Dest.getAbsolutePath();
			test.addScreenCaptureFromPath("image");
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
		return absolutePath;
	}

//	public static void extentReport() throws Exception {
//		try {
//			report = new ExtentReports(
//					System.getProperty("user.dir") + "\\Reports\\ExtentReport" + System.currentTimeMillis() + ".html");
//			test = report.startTest("Test");
//			report.endTest(test);
//			report.flush();
//		} catch (Exception e) {
//			throw new Exception("Error Message:" + e.getMessage());
//		}
//	}

	public static void extentReport() throws Exception {
		try {
			report = new ExtentReports();
			htmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "\\Reports\\Report" + System.currentTimeMillis() + ".html");
			test = report.createTest("TestCase");
			report.attachReporter(htmlReporter);
			report.flush();
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static void reportPass(String message) throws Exception {
//		extentReport();
		test.log(Status.PASS, message);
	}

	public static void reportFail(String message) throws Exception {
//		extentReport();
		takesScreenshot();
		test.log(Status.FAIL, message);
	}

	public static void info(String message) throws Exception {
		try {
			System.out.println(message);
//			test.log(Status.INFO, message);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static boolean acceptAlert() throws Exception {
		boolean flag = false;
		try {
			Alert ar = driver.switchTo().alert();
			ar.accept();
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static boolean dismissAlert() throws Exception {
		boolean flag = true;
		try {
			Alert ar = driver.switchTo().alert();
			ar.dismiss();
		} catch (Exception e) {
			flag = false;
			throw new Exception("Error Message:" + e.getMessage());
		}
		return flag;
	}

	public static boolean writeInExcel(String value) throws Exception {
		boolean flag = false;
		try {
			FileOutputStream FileOutputStream = new FileOutputStream(FileLocation.ExcelFile);
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Passenger-Details");
			XSSFRow header = sheet.createRow(0);
			header.createCell(0).setCellValue("Page title");
			XSSFRow row = sheet.createRow(1);
			row.createCell(0).setCellValue(value);
			workbook.write(FileOutputStream);
			FileOutputStream.close();
			workbook.close();
			info("Excel file has been generated successfully.");
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static String ReadCellData(String sheetName, int vRow, int vColumn) throws Exception {
		try {
			FileInputStream FileInputStream = new FileInputStream(FileLocation.ExcelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(FileInputStream);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row row = sheet.getRow(vRow);
			Cell cell = row.getCell(vColumn);
			return cell.getStringCellValue();
		} catch (NullPointerException e) {
			throw new Exception("Error Message:" + e.getMessage());
		} catch (FileNotFoundException e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

	public static void refreshThePage() throws Exception {
		try {
//			driver.navigate().refresh();

			((JavascriptExecutor) driver).executeScript("location.reload()");

//			Robot robot = new Robot();
//			robot.keyPress(KeyEvent.VK_F5);
//			robot.keyRelease(KeyEvent.VK_F5);
		} catch (Exception e) {
			throw new Exception("Error Message:" + e.getMessage());
		}
	}

//	public static int getRandomNumber() throws Exception {
//		try {
//			String number = getDataFromPropertyFile("RandomNumber");
//			Random random = new Random();
//			return random.nextInt(Integer.valueOf(number));
//		} catch (Exception e) {
//			throw new Exception("Error Message:" + e.getMessage());
//		}
//	}
//
//	public static int getRandomNumRange(int minValue, int maxValue) throws Exception {
//		try {
//			Random random = new Random();
//			int minimum = minValue;
//			int maximum = maxValue;
//			return random.nextInt(maximum - minimum + 1) + minimum;
//		} catch (Exception e) {
//			throw new Exception("Error Message:" + e.getMessage());
//		}
//	}
//
//	public static String getRandomString() throws Exception {
//		try {
//			String characters = getDataFromPropertyFile("char");
//			Random random = new Random();
//			StringBuilder stringBuilder = new StringBuilder();
//			for (int i = 0; i < 3; i++) {
//				stringBuilder.append(characters.charAt(random.nextInt(characters.length())));
//			}
//			return stringBuilder.toString();
//		} catch (Exception e) {
//			throw new Exception("Error Message: " + e.getMessage());
//		}
//	}

	public static String extractTextFromImage() throws Exception {
		try {
			Tesseract tesseract = new Tesseract();
			tesseract.setDatapath(FileLocation.tessData);
			return tesseract.doOCR(new File(FileLocation.sampleImage));
		} catch (TesseractException e) {
			e.printStackTrace();
			throw new Exception("Error Message: " + e.getMessage());
		}
	}

//	public String randomNumber(int number) {
//		return RandomStringUtils.randomNumeric(number);
//	}
//	public String randomNumberic(int number) {
//		return RandomStringUtils.randomAlphanumeric(10);
//	}
}
