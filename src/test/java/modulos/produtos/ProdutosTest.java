package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web do Módulo de Produtos")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){
        // Abrir navegador
        System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        // Maximizar a tela
        navegador.manage().window().maximize();

        // Definir um tempo de espera padrão de 5seg.
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navegar para a pagina da lojinha web
        navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }

    @Test
    @DisplayName("Nao e permitido registrar um pedido com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero(){

        // Fazer login
        new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("PS2")
                .informarValorDoProduto("0")
                .informarCoresDoProduto("Branco, Preto")
                .submeterFormularioProduto()

                .validarMensagemErro();

        }

    @Test
    @DisplayName("Nao e permitido registrar um pedido com valor acima de R$ 7.000")
    public void testNaoEPermitidoRegistrarProdutoComValorAcimaDe7000(){

        new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("PS4")
                .informarValorDoProduto("700001")
                .informarCoresDoProduto("Branco, Preto")
                .submeterFormularioProduto()

                .validarMensagemErro();
    }

    @Test
    @DisplayName("Posso adicionar produtos que estejam dentro dos valores R$ 0,01 e R$ 7.000")
    public void permitirRegistrarUmProdutoDentroDosValoresEstipulados(){
        new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeDoProduto("PS4")
                .informarValorDoProduto("7000")
                .informarCoresDoProduto("Branco, Preto")
                .submeterFormularioProduto()

                .validarMensagemSucesso();
    }

    @AfterEach
    public void afterEach(){
        // Vou fechar o navegador
        navegador.quit();
    }
}
