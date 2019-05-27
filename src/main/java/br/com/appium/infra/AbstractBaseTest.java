package br.com.appium.infra;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;


@ExtendWith({InfraExtension.class, RelatorioExtension.class})
public abstract class BaseTest {

	private InfraAppium infra;

	private Acoes acoes;

	public AppiumDriver<WebElement> getDriver() {

		return this.infra.getDriver();
	}

	public Acoes getAcoes() {

		if(this.acoes == null){
			this.acoes = new Acoes(this.getDriver());
		}

		return this.acoes;
	}

	public void setInfra(InfraAppium infraAppium){
		this.infra = infraAppium;
	}

}
