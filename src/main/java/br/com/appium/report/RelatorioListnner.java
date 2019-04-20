package br.com.appium.report;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class RelatorioListnner extends RunListener {

	private static final Description FAILED = Description.createTestDescription("failed", "failed");

	private static final Description SKIPED = Description.createTestDescription("skiped", "skiped");

	private Relatorio relatorio;

	public RelatorioListnner(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	@Override
	public void testFailure(Failure failure) throws Exception {

		relatorio.falhar(failure.getException());
		failure.getDescription().addChild(FAILED);
		super.testFailure(failure);
	}

	@Override
	public void testIgnored(Description description) throws Exception {

		relatorio.pular(null);
		super.testIgnored(description);
	}

	@Override
	public void testFinished(Description description) throws Exception {

		if (!description.getChildren().contains(FAILED) && !description.getChildren().contains(SKIPED)) {
			relatorio.passar();
		}

		super.testFinished(description);
	}

}
