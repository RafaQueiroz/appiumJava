package br.com.appium.infra;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

public class ArquivoConfig {

	private Map<String, Object> serverCapabilities;

	private Map<String, Object> driverCapabilities;

	private Wini ini;

	public ArquivoConfig(String iniFilePath) {

		try {
			this.openIni(iniFilePath);
			this.loadServerConfig();
			this.loadDriverConfig();

		} catch (IOException e) {
			System.out.println(String.format("Não foi possível carregar o arquivo: %s", iniFilePath));
			e.printStackTrace();
		}

	}

	/**
	 * Carrega as configuraões do arquivo ini e mistura-as com as configurações
	 * padrão pré-definidas.
	 */
	private void loadDriverConfig() {

		this.driverCapabilities = this.defaultCapabilities();

		if (this.ini == null) {
			return;
		}

		Section section = ini.get("DRIVER");

		if (section == null) {
			return;
		}

		for (String cap : section.keySet()) {

			if (this.driverCapabilities.containsKey(cap)) {
				this.driverCapabilities.replace(cap, section.get(cap));
			} else {
				this.driverCapabilities.put(cap, section.get(cap));
			}

		}

	}

	/**
	 * Carrega as configuraões do arquivo ini e mistura-as com as configurações
	 * padrão pré-definidas.
	 */
	private void loadServerConfig() {

		this.serverCapabilities = this.defaultServerConfig();

		if (this.ini == null) {
			return;
		}

		Section section = ini.get("SERVER");

		if (section == null) {
			return;
		}
		
		for (String cap : section.keySet()) {

			if (this.serverCapabilities.containsKey(cap)) {
				this.serverCapabilities.replace(cap, section.get(cap));
			} else {
				this.serverCapabilities.put(cap, section.get(cap));
			}

		}

	}

	public void openIni(String configFilePath) throws InvalidFileFormatException, IOException {

		File configFile = new File(configFilePath);

		if (!configFile.exists()) {
			this.ini = null;
			return;
		}

		this.ini = new Wini(configFile);

	}

	/**
	 * Configurações que serão utilizadas caso o usuário não informe-as no arquivo
	 * de configuração.
	 * 
	 * @return
	 */
	private Map<String, Object> defaultServerConfig() {

		Map<String, Object> config = new HashMap<String, Object>();
		config.put("appiumJs", "/home/rafael/.npm-global/bin/appium");
		config.put("serverIp", "0.0.0.0");
		config.put("nodeExe", "/usr/bin/node");

		return config;
	}

	/**
	 * Valores que serão utilizados caso o usuário não passe as capabilities
	 * necessárias.
	 * 
	 * @return
	 */
	private Map<String, Object> defaultCapabilities() {

		Map<String, Object> config = new HashMap<String, Object>();

		config.put("deviceName", "Android Emulator");
		config.put("platformName", "Android");
		config.put("app", Paths.get(System.getenv("HOME"), "Downloads", "CTAppium-1-1.apk").toString());
		config.put("platformVersion", "7.1.1");
		config.put("avd", "Nexus_5X_API_25");
		config.put("noReset", "true");
		config.put("resetKeyboard", "true");

		return config;

	}

	public Map<String, Object> getServerCapabilities() {
		return serverCapabilities;
	}

	public Map<String, Object> getDriverCapabilities() {
		return driverCapabilities;
	}

}
