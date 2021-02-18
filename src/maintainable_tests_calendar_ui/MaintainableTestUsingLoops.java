package maintainable_tests_calendar_ui;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MaintainableTestUsingLoops {

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
		
		// While List contains a link
		while(col1FooterLinkItr.hasNext())
		{
			// Open the link in a new tab (by pressing Ctrl+Enter on link)
			col1FooterLinkItr.next().sendKeys(ctrlEntr);
			//Thread.sleep(30000);
		}
		
		// Create Set of window handles to all new tabs
		Set<String> col1FooterLinkTabs = driver.getWindowHandles();
		
		// Create Iterator for Set of window handles
		Iterator<String> col1FooterLinkTabHndlr = col1FooterLinkTabs.iterator();
		
		// While Set has a window handle 
		while(col1FooterLinkTabHndlr.hasNext())
		{
			//Req't 4 - Print out the title of the tab using its window handle 
			System.out.println("Page title of link: " + driver.switchTo().window(col1FooterLinkTabHndlr.next()).getTitle());
			//Thread.sleep(5000);
		}
	}

}
