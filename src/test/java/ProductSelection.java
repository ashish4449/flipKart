import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductSelection {

	WebDriver driver;
	String ProductName;
	
	public ProductSelection(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class=\"KzDlHZ\"]")
	List<WebElement> Allprod;
	
	@FindBy(xpath="//a[@class=\"_9QVEpD\"]/span[normalize-space()='Next']")
	WebElement nextBtn;
	
	@FindBy(xpath="(//div[@class=\"KzDlHZ\"])[24]")
	WebElement lastEm;


	public WebElement FindProdByName() {
		String xpath = String.format("//div[@class=\"_75nlfW\"]/descendant::div[normalize-space()='%s']", ProductName);
		return driver.findElement(By.xpath(xpath));
	}
	public WebElement clickBtn() {
		String xpath = String.format(
				"//div[@class='tUxRFH']/descendant::div[normalize-space()='%s']/following-sibling::div[@class='_6NESgJ']",
				ProductName);
		return driver.findElement(By.xpath(xpath));
	}
	
	public void searchResult(String ProductName) throws InterruptedException, AWTException {
		this.ProductName=ProductName;
		int counter=1;
		boolean b= Allprod.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProductName));
		System.out.println(b);
		JavascriptExecutor js=(JavascriptExecutor)driver;
		if(b==true) {
			System.out.println(ProductName);			
			js.executeScript("arguments[0].scrollIntoView('true');", FindProdByName());
			clickBtn().click();
		} else {
			System.out.println("product not found on page 1");
			while(b==false) {
			js.executeScript("arguments[0].scrollIntoView('true');", lastEm);
			nextBtn.click(); 
			Thread.sleep(2000);
			// we are not next page
			boolean c= Allprod.stream().anyMatch(s->s.getText().equalsIgnoreCase(ProductName));			
			if(c==true) {
				js.executeScript("arguments[0].scrollIntoView('true');", FindProdByName());
				clickBtn().click();
				String CurrentPage = driver.findElement(By.xpath("//a[@class='cn++Ap A1msZJ']")).getText();
				System.out.println("Your Product " + ProductName + " found on page number " + CurrentPage + ".");
				
				Set<String> handles = driver.getWindowHandles();
				String firstTab = (String) handles.toArray()[0];
		        driver.switchTo().window(firstTab);
		        
		        driver.findElement(By.xpath("//span[normalize-space()='Stop']")).click();
		        Thread.sleep(1000);
		        driver.findElement(By.xpath("//button[normalize-space()=\"Continue\"]")).click();
		        Thread.sleep(1000);
		        driver.findElement(By.xpath("//button[normalize-space()=\"Optimize\"]")).click();
		        
		        driver.findElement(By.xpath("//span[normalize-space()='Save']")).click();
				break;
			}else {
				counter++;
				if(counter>=5) {
					System.out.println("Item not found on till page no. 5");
					break;
				}
			}
		}
		
		}
	}

}
