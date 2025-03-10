import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ByUsingSkincare {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get("https://www.clinique.in/");

		driver.manage().window().maximize();

		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.linkText("Skincare"))).perform();

		List<WebElement> link = driver.findElements(By.xpath("//a[contains(text(),'SHOP ALL SKINCARE')]/../../../div[1]"));

		for (WebElement links : link) {
			System.out.println(links.getText());
		}
	}

}
