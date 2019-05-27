package br.com.appium.infra;

import org.junit.jupiter.api.extension.*;

public class InfraExtension implements BeforeAllCallback, AfterAllCallback, AfterEachCallback, TestInstancePostProcessor {


	private InfraAppium infraAppium;

	@Override
	public void beforeAll(ExtensionContext context) throws Exception {

		if(context.getRequiredTestClass().isAssignableFrom(AbstractBaseTest.class)){
			throw new Exception("A classe de teste deve herdar de AbstractBaseTest");
		}

		this.infraAppium = new InfraAppium();
		this.infraAppium.createDriver();

	}

	@Override
	public void afterEach(ExtensionContext context) throws Exception {

		this.infraAppium.getDriver().closeApp();
		this.infraAppium.getDriver().launchApp();

	}

	@Override
	public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {

		AbstractBaseTest test = (AbstractBaseTest) testInstance;
		test.setInfra(this.infraAppium);

	}

	@Override
	public void afterAll(ExtensionContext context) throws Exception {

		this.infraAppium.shutdown();

	}

}
