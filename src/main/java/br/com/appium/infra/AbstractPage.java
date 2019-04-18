package br.com.appium.infra;

import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

abstract public class AbstractPage {

	private Acoes acoes;

	private AppiumDriver<WebElement> driver;

	public AbstractPage(AppiumDriver<WebElement> driver) {

		this.driver = driver;
		this.acoes = new Acoes(this.driver);
	}

	public Acoes getAcoes() {
		return acoes;
	}

	public AppiumDriver<WebElement> getDriver() {
		return driver;
	}

}
