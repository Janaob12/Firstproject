import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;

public class MyTestCases4 {
	
	//Jana 
	WebDriver driver = new ChromeDriver();
	WebDriver driver2 = new EdgeDriver();

	String myWebsite = "https://automationteststore.com/";
	String[] firstNames = { "ahmad", "ali", "anas", "omar", "ayat", "alaa", "sawsan", "Rama" };
	String[] LastNames = { "Khaled", "mustafa", "Mohammad", "abdullah", "malek", "omar" };
	Random rand = new Random();
	String GlobalUserName = "";

	String GlobalUserNameForTheLogin = "";

	String Password = "ILoveMyMom123!@#";

	@BeforeTest
	public void mySetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(myWebsite);

	}

	@Test(priority = 1, enabled = false)
	public void SignUp() throws InterruptedException

	{
		int RandomIndexForTheFirstName = rand.nextInt(firstNames.length);
		int RandomIndexForTheLastName = rand.nextInt(LastNames.length);

		String UserFirstName = firstNames[RandomIndexForTheFirstName];
		String UserLastName = LastNames[RandomIndexForTheLastName];

		int randomNumberForTheEmail = rand.nextInt(564548);

		String domainName = "@gmail.com";

		String email = UserFirstName + UserLastName + randomNumberForTheEmail + domainName;

		driver.findElement(By.linkText("Login or register")).click();
		Thread.sleep(3000);
		WebElement SignUpButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		SignUpButton.click();

		WebElement FirstNameInput = driver.findElement(By.id("AccountFrm_firstname"));
		FirstNameInput.sendKeys(UserFirstName);

		GlobalUserName = UserFirstName;
		System.out.println(GlobalUserName);
		WebElement LastNameInput = driver.findElement(By.id("AccountFrm_lastname"));
		LastNameInput.sendKeys(UserLastName);
		WebElement EmailButton = driver.findElement(By.id("AccountFrm_email"));
		EmailButton.sendKeys(email);
		WebElement AdressNameButton = driver.findElement(By.id("AccountFrm_address_1"));
		AdressNameButton.sendKeys("Amman");
		WebElement CityNameButton = driver.findElement(By.id("AccountFrm_city"));
		CityNameButton.sendKeys("Capital City");
		WebElement CountryInput = driver.findElement(By.id("AccountFrm_country_id"));

		Select selector = new Select(CountryInput);
		int randomCountry = rand.nextInt(1, 240);
		selector.selectByIndex(randomCountry);
		Thread.sleep(3000);

		WebElement ZoneIdInput = driver.findElement(By.id("AccountFrm_zone_id"));
		Select selector2 = new Select(ZoneIdInput);
		int randomState = rand.nextInt(1, 6);
		selector2.selectByIndex(randomState);

		WebElement PostCodeButton = driver.findElement(By.id("AccountFrm_postcode"));
		PostCodeButton.sendKeys("13310");
		WebElement LoginNameInput = driver.findElement(By.id("AccountFrm_loginname"));

		String LocalUserName = UserFirstName + UserLastName + randomNumberForTheEmail;
		LoginNameInput.sendKeys(LocalUserName);

		GlobalUserNameForTheLogin = LocalUserName;
		WebElement PasswordInput = driver.findElement(By.id("AccountFrm_password"));
		PasswordInput.sendKeys(Password);
		WebElement ConfirmPasswordInput = driver.findElement(By.id("AccountFrm_confirm"));
		ConfirmPasswordInput.sendKeys(Password);

		Thread.sleep(3000);
		WebElement AgreeCheckBox = driver.findElement(By.id("AccountFrm_agree"));
		AgreeCheckBox.click();
		WebElement ContinueButton = driver.findElement(By.xpath("//button[@title='Continue']"));
		ContinueButton.click();

	}

	@Test(priority = 2, enabled = false)
	public void Logout() throws InterruptedException {
		Thread.sleep(5000);
		WebElement UserNav = driver.findElement(By.id("customer_menu_top"));
		Actions action = new Actions(driver);
		action.moveToElement(UserNav).perform();
		driver.findElement(By.linkText("Not " + GlobalUserName + "? Logoff")).click();
		;
	}

	@Test(priority = 3, enabled = false)
	public void Login() {

		System.out.println(GlobalUserNameForTheLogin);

		driver.findElement(By.linkText("Login or register")).click();

		WebElement LoginInput = driver.findElement(By.id("loginFrm_loginname"));
		LoginInput.sendKeys(GlobalUserNameForTheLogin);

		WebElement PasswordInput = driver.findElement(By.id("loginFrm_password"));

		PasswordInput.sendKeys(Password);

		WebElement LoginButton = driver.findElement(By.xpath("//button[@title='Login']"));

		LoginButton.click();
	}

	@Test(priority = 4)
	public void AddItemToTheCart() throws InterruptedException {
		String[] WebsitesForTheItem = { "https://automationteststore.com/index.php?rt=product/category&path=68",
				"https://automationteststore.com/index.php?rt=product/category&path=36",
				"https://automationteststore.com/index.php?rt=product/category&path=43",
				"https://automationteststore.com/index.php?rt=product/category&path=49",
				"https://automationteststore.com/index.php?rt=product/category&path=58",
				"https://automationteststore.com/index.php?rt=product/category&path=52",
				"https://automationteststore.com/index.php?rt=product/category&path=65" };
		
		int randomIndex = rand.nextInt(WebsitesForTheItem.length);
		driver.get(WebsitesForTheItem[randomIndex]);
		
		WebElement ListOfITem = driver.findElement(By.cssSelector(".thumbnails.row"));
		int totalNumberOfItems = ListOfITem.findElements(By.tagName("li")).size();
		int RandomIdexForTheItem = rand.nextInt(totalNumberOfItems);
		Thread.sleep(3000);
		ListOfITem.findElements(By.tagName("li")).get(RandomIdexForTheItem).click();
		WebElement Container = driver.findElement(By.cssSelector(".thumbnails.grid.row.list-inline"));
		int numberOfPRoducts = Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).size();
		int randomIndexForTheproduct = rand.nextInt(numberOfPRoducts);
		Container.findElements(By.cssSelector(".col-md-3.col-sm-6.col-xs-12")).get(randomIndexForTheproduct).click();
		Thread.sleep(5000);
		WebElement ULList = driver.findElement(By.className("productpagecart"));
		int LiItem = ULList.findElements(By.tagName("li")).get(0).findElements(By.tagName("span")).size();
		if (LiItem > 0) {
			driver.get(myWebsite);

			System.out.println("sorry the item out of the stock ");
			String ExpectedResult = "https://automationteststore.com/";
			String ActualResult = driver.getCurrentUrl();
            org.testng.Assert.assertEquals(ActualResult, ExpectedResult, "soso"); }
		
		else {

			driver.findElement(By.className("cart")).click();
			;
			Thread.sleep(2000);
			String ActualResult = driver.findElement(By.className("heading1")).getText();
			String ExpectedResult = "Shopping Cart";

			org.testng.Assert.assertEquals(ActualResult, ExpectedResult.toUpperCase());
			boolean ExpectedValueForCheckOut = true;
			boolean ActualValueForCheckOut = driver.findElement(By.id("cart_checkout1")).isDisplayed();
			org.testng.Assert.assertEquals(ActualValueForCheckOut, ExpectedValueForCheckOut, "soso hi");
		}
	}



		



	}


