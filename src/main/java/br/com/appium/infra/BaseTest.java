package br.com.appium.infra;

import org.junit.After;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

@RunWith(TestRunner.class)
public abstract class BaseTest {

	private InfraAppium infra;

	private AppiumDriver<WebElement> driver;

	private Acoes acoes;

	protected void setUp() {
		this.infra = new InfraAppium();
		this.driver = infra.createDriver();
		this.acoes = new Acoes(this.driver);

	}

	protected void tearDown() {
		this.infra.shutdown();
	}

	@After
	public void afterTest() {
		this.getDriver().resetApp();
	}
	
	public AppiumDriver<WebElement> getDriver() {
		return this.driver;
	}

	public Acoes getAcoes() {
		return this.acoes;
	}

}
