package br.com.appium.infra;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;

public class Acoes {

	private AppiumDriver<WebElement> driver;

	private WebDriverWait wait;

	public int timeout = 10;

	protected Acoes(AppiumDriver<WebElement> driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, this.timeout);
	}

	/**
	 * Verifica se o elemento está presente na tela.
	 */
	public Boolean estaVisivel(By by) {

		try {
			this.driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public void clicarPorTexto(String texto) {
		
		String xpath = String.format("//*[@text='%s']", texto);
		By by = By.xpath(xpath);		
		this.clicar(by);
	}

	public void preencher(By by, String texto) {
		this.esperarPorElementoVisivel(by);
		this.driver.findElement(by).sendKeys(texto);
	}
	
	public void clicar(By by) {
		this.esperarPorElementoVisivel(by);
		this.driver.findElement(by).click();
	}

	public void esperarPorElementoVisivel(By by) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

}
