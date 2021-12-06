import exception.SaqueMaiorQueSaldoException;
import model.Conta;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;


public class TestConta {

    Conta conta;

    @BeforeEach
    public void prepararContexto(){

        conta = new Conta();
        conta.depositar(150);
    }

    @Test
    @DisplayName("TC-01: Realizar saque com saldo na conta")
    public void SacarValorComSaldo(){

        conta.sacar(25);

        Assert.assertEquals(125.0, conta.getSaldo(), 0.1);
    }

    @Test
    @DisplayName("TC-02: Não realizar saque com saldo negativo")
    public void NaoDeveSacarValorComSaldoNegativo(){

        Assertions.assertThrows(SaqueMaiorQueSaldoException.class,()-> {
            conta.sacar(155.0);
        });

        Assertions.assertEquals(150, conta.getSaldo(),0.1);
    }

    @Test
    @DisplayName("TC-03: Não realizar saque maior que o saldo")
    public void SacarValorMaiorQueSaldo(){

        conta.sacar(155);

        Assert.assertEquals(0, conta.getSaldo(), 0.1);
    }

    @Test
    @DisplayName("TC-04: Não deve demorar mais que 3 segundos para finalizar a operação")
    public void naoDeveDemorarMaisQueTresSegundos(){

        Assertions.assertTimeout(Duration.ofSeconds(3), ()->{
            conta.sacarLento(3.0);
        });
    }
}
