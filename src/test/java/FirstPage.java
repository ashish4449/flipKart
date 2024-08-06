import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FirstPage {
	
	WebDriver driver;
	

	public FirstPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
		
	//WebElement search=driver.findElement(By.xpath("//input[@name='q']"));
	
	@FindBy(xpath="//input[@name='q']")
	WebElement search;
	
	@FindBy(xpath="//button[@title=\"Only Screen\"]")
	WebElement extOnlyScreen;
	
	@FindBy(xpath="//button[@title=\"None\"]")
	WebElement extSound;
	
	@FindBy(xpath="//span[normalize-space()='Start Recording']")
	WebElement StartRecBtn;
	
	
	public void Goto() throws InterruptedException, AWTException {
		driver.navigate().to("chrome-extension://hniebljpgcogalllopnjokppmgbhaden/index.html#/");
		extOnlyScreen.click();
		Thread.sleep(1000);
		extSound.click();
		Thread.sleep(1000);
		StartRecBtn.click();
		
		Robot rob= new Robot();
		
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyRelease(KeyEvent.VK_TAB);
		
		rob.keyPress(KeyEvent.VK_TAB);
		rob.keyRelease(KeyEvent.VK_TAB);
		
		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);
		
	 
		String url="https://www.flipkart.com/";
		((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", url);
		 switchToNewTab(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));				
	}
	
    private static void switchToNewTab(WebDriver driver) {
        // Get the handles of all open tabs
        Set<String> handles = driver.getWindowHandles();

        // Switch to the new tab (assuming it's the last one in the set)
        for (String handle : handles) {
            driver.switchTo().window(handle);
        }
    }
	
	public void itemSearch(String productName) {
		search.sendKeys(productName,Keys.ENTER);
	}
	
	public void EnterItem(String Value) throws InterruptedException, AWTException {
		Goto();
		itemSearch(Value);
	}


}
