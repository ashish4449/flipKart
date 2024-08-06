import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.awt.AWTException;
import java.io.File;

import org.openqa.selenium.WebDriver;
public class MainBase {

	public static void main(String[] args) throws InterruptedException, AWTException {
		ChromeOptions Co= new ChromeOptions();
		Co.addExtensions(new File("./Extensions/ScreenRecorder.crx"));
		WebDriver driver = new ChromeDriver(Co);		
		FirstPage FP = new FirstPage(driver);
		FP.EnterItem("Mobile");
		ProductSelection PS = new ProductSelection(driver);
		PS.searchResult("POCO C65 (Matte Black, 128 GB)");		
		Thread.sleep(2000);
		driver.quit();
	}

}
