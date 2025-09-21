package com.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
//import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.failsafe.internal.util.Durations;

public class BasePage {
	public static  WebDriver driver;
	public  static WebDriverWait wait;
	static String value;
    public static JavascriptExecutor js;
	public static void launchBrowsers(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
	public static void windowsMax() {
		driver.manage().window().maximize();
	}

	public static String url(String url) {
		driver.get(url);
		return url;
	}

	public static void typeData(WebElement element, String data) {
		element.sendKeys(data);
	}

	public static void click(WebElement element) {
		element.click();
	}

	public static void clear(WebElement element) {
		element.clear();
	}

	public static WebElement findElementid(String element) {
		return driver.findElement(By.id(element));

	}
	public static List<WebElement> tagName(String element) {
		return driver.findElements(By.tagName(element));
	}


	public static WebElement findElementname(String name) {
		return driver.findElement(By.name(name));

	}

	public static WebElement findElementxpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	
	public static List<WebElement> xpaths(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}
	

	public static void implicityWait(int n) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	}
   
	public static void clik(WebElement element,int n) {
		wait=new WebDriverWait(driver, Duration.ofSeconds(n));
	    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public static void type(WebElement element, String text) {
		wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
	}

	public static String getTexts(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	}

	public static boolean isDisplayed(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
	}
  
	public static String readProp(String Key) {
		String value=null;
		try {
			
			FileReader fil=new FileReader("C:\\Users\\HP\\eclipse-workspace\\AssesmentTask\\src\\test\\resources\\Credentials\\data.properities");
			Properties prop=new Properties();
				prop.load(fil);
				value=prop.getProperty(Key);
						} catch (FileNotFoundException e) {
							
							e.printStackTrace();}
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return value;
		}
	public static void switchToNewWindow() {
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }
	public static void scrollDown(WebElement element) {
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false)", element); 
		}
	public static void selectValue(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);
	}

	public static String captureScreenshot(String testName) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String screenshotDirPath = System.getProperty("user.dir") +"\\target\\screenshots";
        File screenshotDir = new File(screenshotDirPath);
        if (!screenshotDir.exists()) {
            screenshotDir.mkdir();
        }

        String screenshotPath = screenshotDirPath+" "+testName + " _" + timestamp + ".png";

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);
            FileHandler.copy(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return screenshotPath;
    }

	public static String readfromexcel(int prow, int pcell, String sheetname) {

		try {
			String fpath = "C:\\Users\\91994\\eclipse-workspace-newEclipse\\CucumberProj\\src\\test\\resources\\DemoQA\\DemoQA.xlsx";

			File f = new File(fpath);
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = new XSSFWorkbook(fis);

			Sheet sheet = wb.getSheet(sheetname);
			Row row = sheet.getRow(prow);
			Cell cell = row.getCell(pcell);
			int celltype = cell.getCellType();
			if (celltype == 1) {
				value = cell.getStringCellValue();
				// System.out.println(value);
			} else if (celltype == 0) {
				if (DateUtil.isCellDateFormatted(cell)) {
					Date dateCellValue = cell.getDateCellValue();
					SimpleDateFormat sc = new SimpleDateFormat("dd/MM/yyyy");
					value = sc.format(dateCellValue);
					// System.out.println(value);

				} else {
					{
						double numvalue = cell.getNumericCellValue();
						long a = (long) numvalue;
						value = String.valueOf(a);
					}
				}
			}

		}

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

	public static String writetoexcel(WebElement element, String sheetname, int datarow, int datacell) {
		String orderno = element.getAttribute("value");
		System.out.println(orderno);

		try {
			String fpath = "C:\\Users\\91994\\eclipse-workspace\\Junit\\src\\test\\resources\\Excel\\Adacitylogin.xlsx";

			File f = new File(fpath);
			FileInputStream fis = new FileInputStream(f);
			Workbook wb = new XSSFWorkbook(fis);
			Sheet csheet = wb.createSheet(sheetname);
			Row crow = csheet.createRow(datarow);
			Cell ccell = crow.createCell(datacell);
			ccell.setCellValue(orderno);
			FileOutputStream fos = new FileOutputStream(f);
			wb.write(fos);
			System.out.println("Created");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderno;
	}

	public static void mouseActions(String key) {
		Actions a = new Actions(driver);
		switch (key) {
		case "down":
			a.keyDown(key);
			break;

		case "up":
			a.keyUp(key);
			break;

		case "click":
			a.click();

			break;

		default:
			System.out.println("Wrong key");
		}
	}

	public static void frameGetText(WebElement element, int index) {
		WebDriver move = driver.switchTo().frame(element);
		String text = element.getText();
		System.out.println(text);

	}

	public static void keyActions(String k) throws AWTException {
		Robot r = new Robot();
		switch (k) {
		case "enter":
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			break;

		case "escape":
			r.keyPress(KeyEvent.VK_ESCAPE);
			r.keyRelease(KeyEvent.VK_ESCAPE);
			break;

		case "down":
			r.keyPress(KeyEvent.VK_DOWN);
			r.keyRelease(KeyEvent.VK_DOWN);
			break;

		case "up":
			r.keyPress(KeyEvent.VK_UP);
			r.keyRelease(KeyEvent.VK_UP);
			break;

		case "pageUp":
			r.keyPress(KeyEvent.VK_PAGE_UP);
			r.keyRelease(KeyEvent.VK_PAGE_UP);
			break;

		case "pageDown":
			r.keyPress(KeyEvent.VK_PAGE_DOWN);
			r.keyRelease(KeyEvent.VK_PAGE_DOWN);
			break;
		case "tab":
			r.keyPress(KeyEvent.VK_TAB);
			r.keyRelease(KeyEvent.VK_TAB);
			break;
		case "right":
			r.keyPress(KeyEvent.VK_RIGHT);
			r.keyRelease(KeyEvent.VK_RIGHT);
			break;

		default:
			System.out.println("Wrong key");
		}
	}

	public static void webtabledata() {

		List<WebElement> trow = driver.findElements(By.tagName("tr"));
		System.out.println("Rows of the table is: " + trow.size());
		List<WebElement> tcol = driver.findElements(By.tagName("th"));
		System.out.println("Column of the Table Is: " + tcol.size());

		for (int i = 1; i < tcol.size(); i++) {
			System.out.println(tcol.get(i).getText());
		}
		System.out.println("<-------------------->");
		for (int i = 0; i < trow.size(); i++) {
			List<WebElement> rowcol = trow.get(i).findElements(By.tagName("td"));
			System.out.println("All Data from the Line No: " + i);
			for (int j = 0; j < rowcol.size(); j++) {
				System.out.println(rowcol.get(j).getText());
			}
			System.out.println("***********");

		}

	}

}
