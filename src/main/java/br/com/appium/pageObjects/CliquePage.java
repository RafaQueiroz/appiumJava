package br.com.appium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.appium.infra.AbstractPage;
import io.appium.java_client.AppiumDriver;

public class CliquePage extends AbstractPage {

	public CliquePage(AppiumDriver<WebElement> driver) {
		super(driver);

	}

	public void acessar() {
		this.getAcoes().clicarPorTexto("Cliques");

	}

	public void realizaCliqueDuplo() {

		this.getAcoes().clicarPorTexto("Clique duplo");
		this.getAcoes().clicarPorTexto("Clique duplo");

	}

	public String getResultado() {

		List<WebElement> textViews = this.getDriver().findElements(By.className("android.widget.TextView"));

		return textViews.get(1).getText();
	}

}
