package br.com.appium.infra;

import java.io.File;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class InfraAppium {

	private AppiumDriverLocalService server;

	private AppiumDriver<WebElement> driver;
	
	private int implicitTimeoutInSeconds = 10;

	/**
	 * Inicia o servidor Appium
	 */
	public void startServer() {

		server = new AppiumServiceBuilder()
				.usingPort(4723)
				.withIPAddress("0.0.0.0")
				.withAppiumJS(new File("/home/rafael/.npm-global/bin/appium"))
				.usingDriverExecutable(new File("/usr/bin/node"))
				.build();
		server.start();

	}

	/**
	 * Cria o driver.
	 */
	public AppiumDriver<WebElement> createDriver() {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformName", "Android");

		String apkPath = Paths.get(System.getenv("HOME"), "Downloads", "CTAppium-1-1.apk").toString();
		capabilities.setCapability("app", apkPath);
		capabilities.setCapability("plataformVersion", "7.0.0");
		capabilities.setCapability("avd", "AppiumDev7");
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("resetKeyboard", true);
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
