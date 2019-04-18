package br.com.appium.infra;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

public class TestRunner extends BlockJUnit4ClassRunner {

	private BaseTest test;

	private Class<?> testClass;

	public TestRunner(Class<?> klass) throws Exception {
		super(klass);

		if (!BaseTest.class.isAssignableFrom(klass)) {
			throw new Exception(String.format("A classe de teste deve herdar de %s", BaseTest.class.toString()));
		}

		this.testClass = klass;

	}

	@Override
	protected Object createTest() throws Exception {

		if (this.test == null) {
			this.test = (BaseTest) this.testClass.getConstructor().newInstance();
			this.test.setUp();
		}

		return this.test;
	}

	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		return super.methodInvoker(method, test);
	}

	@Override
	public void run(RunNotifier notifier) {

		super.run(notifier);
		this.test.tearDown();
	}

}
