package paginas;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MensagemPage {
    private WebDriver navegador;

    public MensagemPage(WebDriver navegador){

        this.navegador = navegador;
    }

    public MensagemPage validarMensagemSucesso(){
        String mensagemToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("Produto adicionado com sucesso", mensagemToast);

        return this;
    }

    public FormularioDeEdicaoProdutoPage validarMensagemErro(){
        String mensagemToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);

        return new FormularioDeEdicaoProdutoPage(navegador);
    }
}
