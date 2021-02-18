package maintainable_tests_calendar_ui;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MaintainableTestUsingLoop {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*	Have following test requirements:
			
			1. How many links in entire webpage?
			2. How many links in footer only?
			3. How many links in first column of footer?
			4. What are webpage titles of links in first column of footer?
		*/
		
		// Set property for location of chromedriver.exe file
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Khalid\\Documents\\Documents\\Courses\\Selenium\\Apps\\chromedriver_win32\\chromedriver.exe");
		
		// Create driver object for Chrome browser
		WebDriver driver = new ChromeDriver();
				
		// Load automation practice website
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		
		// Req't 1 - Print out number of links in webpage
		System.out.println("Number of links in webpage: " + driver.findElements(By.tagName("a")).size());
		
		// Create web element instance for footer
		WebElement footerElement = driver.findElement(By.cssSelector("div#gf-BIG"));
		// Req't 2 - Print out number of links in footer
		System.out.println("Number of links in footer: " + footerElement.findElements(By.tagName("a")).size());
		
		// Create web element instance for first column of footer
		WebElement col1FooterElement = footerElement.findElement(By.xpath("table/tbody/tr/td[1]"));
		// Req't 3 - Print out number of links in first column of footer
		System.out.println("Number of links in first column of footer: " + col1FooterElement.findElements(By.tagName("a")).size());
		
		// Create string for Ctrl+Enter
		String ctrlEntr = Keys.chord(Keys.CONTROL,Keys.ENTER);
		
		// Create List of link objects in first column of footer
		List<WebElement> col1FooterLinks = col1FooterElement.findElements(By.tagName("a"));
		
		// Create Iterator for List of link objects
		Iterator<WebElement> col1FooterLinkItr = col1FooterLinks.iterator();
		
		// Declare variable for link object in first column of footer
		WebElement col1FooterLink;
		
		// Create handle to parent window
		Set<String> col1FooterLinkTabs = driver.getWindowHandles();
		
		// Create Iterator for window handle
		Iterator<String> col1FooterLinkTabHndl = col1FooterLinkTabs.iterator();
		
		// Store handle for parent window
		String parentWindowHandle = col1FooterLinkTabHndl.next();
		
		// While List contains a link
		while(col1FooterLinkItr.hasNext())
		{
			// Store and print text in link
			col1FooterLink = col1FooterLinkItr.next();
			System.out.print("The " + col1FooterLink.getText() + " link opens the webpage:\t");
			
			// Open the link in a new tab (by pressing Ctrl+Enter on link)
			col1FooterLink.sendKeys(ctrlEntr);
		
			// Update Set of window handles to include new tab
			col1FooterLinkTabs = driver.getWindowHandles();
			
			// Update Iterator for new Set of window handles
			col1FooterLinkTabHndl = col1FooterLinkTabs.iterator();
			
			// First handle is for parent tab so move to handle for new tab
			col1FooterLinkTabHndl.next();
			
			// Req't 4 - Print out the title of the new tab using its window handle
			// Note - must switch focus from parent tab to new tab first
			System.out.println(driver.switchTo().window(col1FooterLinkTabHndl.next()).getTitle());
			
			// Close the new tab
			driver.close();
			
			// Switch focus back to parent tab
			driver.switchTo().window(parentWindowHandle);
		}
	}

}
