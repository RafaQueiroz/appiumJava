package br.com.appium.testesFuncionais;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.appium.infra.AbstractBaseTest;
import br.com.appium.pageObjects.FormularioPage;

/**
 * Hello world!
 */
public class FormularioTestAbstract extends AbstractBaseTest {

	private WebDriverWait wait;

	private final int DEFAULT_WAIT = 10;

	private FormularioPage formulario;

	@BeforeEach
	public void testSetUp() {
		this.wait = new WebDriverWait(this.getDriver(), DEFAULT_WAIT);
	}

	@Test
	public void esperaSplashDesaparecerTest() {

		this.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Splash']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='Splash!']")));
		Assertions.assertTrue(this.getAcoes().estaVisivel(By.xpath("//android.widget.TextView[@text='Abas']")));

	}

	@Test
	public void enviaFormularioComSucesso() {
		this.formulario = new FormularioPage(this.getDriver());
		this.formulario.acessar();
		this.formulario.preencherNome("Rafael Queiroz");
		this.formulario.selecionarNoCombo("PS4");
		this.formulario.salvar();

		Assertions.assertEquals("Rafael Queiroz", this.formulario.getNomeSalvo());
		Assertions.assertEquals("ps4", this.formulario.getConsoleSalvo());
	}

	@Test
	public void falhaPropositalmenteTest() {
		Assertions.fail("Falhou!");
	}

	@AfterEach
	public void testTearDown() {
	}
}
