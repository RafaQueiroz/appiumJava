package br.com.appium.testesFuncionais;

import br.com.appium.infra.AbstractBaseTest;
import br.com.appium.pageObjects.CliquePage;
import org.junit.jupiter.api.Test;

public class CliqueTestAbstract extends AbstractBaseTest {

	CliquePage cliquePage;

	@Test
	public void cliqueLongoTeste() {

		this.cliquePage = new CliquePage(this.getDriver());
		this.cliquePage.acessar();
		this.cliquePage.realizaCliqueDuplo();

	}
}
