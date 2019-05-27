package br.com.appium.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.com.appium.infra.AbstractPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;

public class FormularioPage extends AbstractPage {

	public FormularioPage(AppiumDriver<WebElement> driver) {
		super(driver);
	}

	public void acessar() {
		this.getAcoes().clicarPorTexto("Formulário");
	}

	public void preencherNome(String nome) {

		this.getAcoes().preencher(MobileBy.AccessibilityId("nome"), nome);

	}

	public void selecionarNoCombo(String opcao) {

		this.getAcoes().clicar(MobileBy.AccessibilityId("console"));
		this.getAcoes().esperarPorElementoVisivel(By.className("android.widget.FrameLayout"));
		this.getAcoes().clicarPorTexto(opcao);

	}

	public void salvar() {
		this.getAcoes().clicarPorTexto("SALVAR");
	}

	public String getNomeSalvo() {

		return this.getResposta("Nome");
	}

	public String getConsoleSalvo() {

		return this.getResposta("Console");
	}

	private String getResposta(String campo) {
		By xpath = By.xpath(String.format("//*[contains(@text, '%s:')]", campo));
		String resposta = this.getDriver().findElement(xpath).getText();
		String[] repostasPartes = resposta.split(":");

		if (repostasPartes == null || repostasPartes.length < 2) {
			return "";
		}

		return repostasPartes[1].trim();
	}

}
