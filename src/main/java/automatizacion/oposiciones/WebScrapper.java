package automatizacion.oposiciones;

import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @author Spuzi
 */
public class WebScrapper {

	protected WebDriver driver = null;
	private String CHROME_DRIVER_PATH = "src\\main\\resources\\webdrivers\\chromedriver.exe";
	private String FIREFOX_DRIVER_PATH = "src\\main\\resources\\webdrivers\\geckodriver.exe";
	public ArrayList<String> urls = new ArrayList<>();

	public WebScrapper() {
		initializeChromeDriver();
	}

	private void initializeChromeDriver() {
		System.out.println("Arrancando chrome driver.");
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		ChromeOptions chromeOptions = new ChromeOptions();
		// To execute selenium without a graphical interface
		chromeOptions.addArguments("headless");
		driver = new ChromeDriver(chromeOptions);
	}

	public void listUrls() {
		urls.forEach(value -> System.out.println(value));
	}
	
	protected void visitUrl(String url) {
		System.out.println("Visitando: " + url);
		driver.get(url);
	}

	protected void stopChromeDriver() {
		System.out.println("Cerrando chrome driver.");
		driver.close();
		driver.quit();
	}
}
