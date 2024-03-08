package bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest{
    BankAccount bankAccount;

    @BeforeEach
    void setup(){
        int startingBalance = 0;
        bankAccount = new BankAccount(startingBalance);
    }

    @Test
    @DisplayName("Al crear una cuenta de banco no puedo retirar")
    void Withdraw_Initial_ReturnFalse(){
        int amount = 20;
        assertFalse(bankAccount.withdraw(amount));
    }

    @Test
    @DisplayName("La cantidad a retirar es negativa y salta una excepcion")
    void Withdraw_AmountNegative_ThrowError(){
        int amount = -10;

        assertThrows(IllegalArgumentException.class,()-> bankAccount.withdraw(amount));
    }

    @Test
    @DisplayName("Le introduzco un deposito y puedo retirar dinero")
    void Withdraw_WithEnoughDeposit_ReturnTrue(){
        int balance = 50;
        bankAccount.deposit(balance);

        int amount = 20;
        assertTrue(bankAccount.withdraw(amount));
        assertEquals(30, bankAccount.getBalance());
    }

    @Test
    @DisplayName("Salta un error si quiero añadir una cantidad negativa")
    void DepositMoney_AmountNegative_ThrowError(){
        int amount = -10;
        assertThrows(IllegalArgumentException.class,()-> bankAccount.deposit(amount));
    }

    @Test
    @DisplayName("El balance inicial es cero")
    void GetBalance_BalanceInitial_ReturnZero(){
        int expectedValue = 0;
        int returnValue = bankAccount.getBalance();
        assertEquals(expectedValue, returnValue);
    }

    @Test
    @DisplayName("Pago con algun parametro negativo lanza una excepcion")
    void Payment_NegativeParameter_ThrowError(){
        double totalAmount = 500;
        double interest = 1;
        int npayments = -2;
        
        assertThrows(IllegalArgumentException.class, ()->bankAccount.payment(totalAmount, interest, npayments));
    }

    @Test 
    @DisplayName("Pago para un mes")
    void Payment_ForOneMonth_Return2000(){
        double totalAmount = 1000;
        double interest = 2;
        int npayments = 500;
        double res = 2000;
        assertEquals(res, bankAccount.payment(totalAmount, interest, npayments));
    }

    @Test
    @DisplayName("Cantidad pendiente con algun parametro negativo lanza una excepcion")
    void AmountPending_NegativeParamenter_ThrowError(){
        double amount = -700;
        int month = 5;
        int pinterest = 1;
        int npayments = -3;

        assertThrows(IllegalArgumentException.class, ()->bankAccount.pending(amount, pinterest, npayments, month));
    }

    @Test 
    @DisplayName("Cantidad pendiente si el mes es cero es la cantidad")
    void AmountPending_MonthZero_ReturnAmount(){
        double amount = 200;
        int month = 0;
        int interest= 2;
        int npayments = 1;
        assertEquals(amount, bankAccount.pending(amount, interest, npayments, month));
    }

    @Test
    @DisplayName("Cantidad pendiente si el mes es distinto a cero")
    void AmountPending_MonthDifferentZero(){
        double amount = 200;
        int month = 1;
        int interest= 2;
        int npayments = 1;
        int res = 0;
        assertEquals(res, bankAccount.pending(amount, interest, npayments, month));
    }

}
