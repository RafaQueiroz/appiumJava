package br.com.appium.infra;

import java.io.File;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class InfraAppium {

	private AppiumDriverLocalService server;

	private AppiumDriver<WebElement> driver;

	private ArquivoConfig config;

	private int implicitTimeoutInSeconds = 10;

	public InfraAppium() {

		this.config = new ArquivoConfig(
				Paths.get(System.getProperty("user.home"), "resource", "config.ini").toString());

	}

	/**
	 * Inicia o servidor Appium
	 */
	public void startServer() {

		Map<String, Object> serverCapabilities = this.config.getServerCapabilities();

		this.server = new AppiumServiceBuilder().usingPort(4723).withIPAddress(serverCapabilities.get("serverIp").toString())
				.withAppiumJS(new File(serverCapabilities.get("appiumJs").toString()))
				.usingDriverExecutable(new File(serverCapabilities.get("nodeExe").toString()))
				.withArgument(GeneralServerFlag.LOG_LEVEL, "error").build();
		
		this.server.start();
	}

	/**
	 * Cria o driver.
	 */
	public AppiumDriver<WebElement> createDriver() {

		DesiredCapabilities capabilities = new DesiredCapabilities();

		Map<String, Object> driverCapabilities = this.config.getDriverCapabilities();

		for (String capKey : driverCapabilities.keySet()) {
			capabilities.setCapability(capKey, driverCapabilities.get(capKey));
		}

//		capabilities.setCapability("avdArgs", "-no-audio -no-window"); 

		if (server == null) {
			this.startServer();
		}

		this.driver = new AppiumDriver<WebElement>(this.server, capabilities);
		this.driver.manage().timeouts().implicitlyWait(implicitTimeoutInSeconds, TimeUnit.SECONDS);

		return this.driver;
	}

	/**
	 * Finaliza o Driver e o servidor do Appium.
	 */
	public void shutdown() {

		if (this.driver != null) {
			this.driver.quit();
		}

		if (this.server != null) {
			this.server.stop();
		}

	}

}
