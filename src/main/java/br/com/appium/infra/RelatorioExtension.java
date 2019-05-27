package br.com.appium.infra;

import br.com.appium.report.Relatorio;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class RelatorioExtension implements BeforeAllCallback, TestWatcher, AfterAllCallback {

    private static Relatorio relatorio;

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {

        getRelatorio().criarClasseDeTeste(extensionContext.getRequiredTestClass().getSimpleName());

    }

    @Override
    public void testDisabled(ExtensionContext extensionContext, Optional<String> optional) {
        getRelatorio().adicionarTeste(extensionContext.getDisplayName()).pular();
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        getRelatorio().adicionarTeste(extensionContext.getDisplayName()).passar();
    }

    @Override
    public void testAborted(ExtensionContext extensionContext, Throwable throwable) {
        getRelatorio().adicionarTeste(extensionContext.getDisplayName()).falhar(throwable);
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        getRelatorio().adicionarTeste(extensionContext.getDisplayName()).falhar(throwable);
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        getRelatorio().gerar();
    }

    public Relatorio getRelatorio(){

        if(relatorio == null){
            relatorio = new Relatorio();
        }

        return relatorio;
    }
}
