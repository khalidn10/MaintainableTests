package maintainable_tests_calendar_ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class CalendarUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Khalid\\Documents\\Documents\\Courses\\Selenium\\Apps\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		// Load Spicejet website and maximise window
		driver.get("https://www.spicejet.com/");
		driver.manage().window().maximize();
		
		// Select Depart Date calendar
		driver.findElement(By.id("ctl00_mainContent_view_date1")).click();
		
		// Create variables for left and right sides of calendar
		WebElement calendarLeft = driver.findElement(By.cssSelector("[class*='ui-datepicker-group-first']"));
		WebElement calendarRight = driver.findElement(By.cssSelector("[class*='ui-datepicker-group-last']"));
		
		// Create variables for month elements in left and right sides of calendar
		WebElement monthLeft = calendarLeft.findElement(By.cssSelector("[class='ui-datepicker-month']"));
		WebElement monthRight = calendarRight.findElement(By.cssSelector("[class='ui-datepicker-month']"));
		
		// Declare variable for dates in left and right sides of calendar
		List<WebElement> dates;
		
		// Create variables for text in month elements
		String monthL = monthLeft.getText();
		String monthR = monthRight.getText();
		
		// Assign values to be selected in Depart Date calendar 
		String month = "October";
		String date = "10";
		
		// If month text in left side of calendar equals the required month
		if (monthL.equals(month))
		{
			// Call method to select required depart date
			dates = calendarLeft.findElements(By.tagName("a"));
			selectDate(dates, date);
		}
		// If month text in right side of calendar equals the required month
		else if (monthR.equals(month))
		{
			// Call method to select required depart date
			dates = calendarRight.findElements(By.tagName("a"));
			selectDate(dates, date);
		}
		// Otherwise
		else
		{
			// For up to 10 times
			for (int i=0; i<10; i++)
			{
				// Move to the next month
				driver.findElement(By.cssSelector(".ui-datepicker-next.ui-corner-all")).click();
				
				// Update  variables for right side of calendar
				calendarRight = driver.findElement(By.cssSelector("[class*='ui-datepicker-group-last']"));
				monthRight = calendarRight.findElement(By.cssSelector("[class='ui-datepicker-month']"));
				monthR = monthRight.getText();
				
				// If month text in right side of calendar equals the required month
				if (monthR.equals(month))
				{
					// Call method to select required depart date
					dates = calendarRight.findElements(By.tagName("a"));
					selectDate(dates, date);
					break;
				}
			}
		}
				
	}

	// Method to select required depart date
	private static void selectDate(List<WebElement> we, String d)
	{
		// Check for each date for required month in calendar
		for (int i=0; i<we.size(); i++)
		{
			// If date matches required date
			if (we.get(i).getText().equals(d))
			{
				// Select date 
				we.get(i).click();
				break;
			}
		}
	}
	
}
