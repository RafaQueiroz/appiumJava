package br.com.appium.testesFuncionais;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.appium.infra.BaseTest;
import br.com.appium.pageObjects.FormularioPage;

/**
 * Hello world!
 */
public class AppiumTest extends BaseTest {

	private WebDriverWait wait;

	private final int DEFAULT_WAIT = 10;

	private FormularioPage formulario;
	
	@Before
	public void testSetUp() {
		this.wait = new WebDriverWait(this.getDriver(), DEFAULT_WAIT);
	}

	@Test
	public void esperaSplashDesaparecerTest() {

		this.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Splash']")).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text()='Splash!']")));
		assertTrue(this.getAcoes().estaVisivel(By.xpath("//android.widget.TextView[@text='Abas']")));

	}
	
	@Test
	public void enviaFormularioComSucesso() {
		this.formulario = new FormularioPage(this.getDriver());
		this.formulario.acessar();
		this.formulario.preencherNome("Rafael Queiroz");
		this.formulario.selecionarNoCombo("PS4");
		this.formulario.salvar();
		
		Assert.assertEquals("Rafael Queiroz", this.formulario.getNomeSalvo());
		Assert.assertEquals("ps4", this.formulario.getConsoleSalvo());
	}

	@After
	public void testTearDown() {
	}
}
