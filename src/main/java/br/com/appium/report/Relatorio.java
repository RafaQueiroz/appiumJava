package br.com.appium.report;

import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Relatorio {

	private ExtentReports report;

	private ExtentReporter htmlReporter;

	private ExtentTest classeDeTeste;

	private ExtentTest testeAtual;

	public Relatorio() {

		this.report = new ExtentReports();
		this.htmlReporter = new ExtentHtmlReporter(
				Paths.get(System.getProperty("user.dir"),"target", "relatorio", "relatorio.html").toFile());

		this.report.attachReporter(this.htmlReporter);
	}

	/**
	 * Cria uma nó para uma classe de teste no relatório.
	 * 
	 * @param nome
	 */
	public void criarClasseDeTeste(String nome) {
		this.classeDeTeste = this.report.createTest(nome);
	}

	public Relatorio adicionarTeste(String nome) {

		this.testeAtual = this.classeDeTeste.createNode(nome);
		return this;
	}

	public void passar() {
		this.testeAtual.pass("");
	}

	public void falhar(Throwable throwable) {
		this.testeAtual.fail(throwable);
	}

	public void pular(Throwable throwable) {
		this.testeAtual.skip(throwable);
	}

	public void gerar() {
		this.report.flush();
	}

}
