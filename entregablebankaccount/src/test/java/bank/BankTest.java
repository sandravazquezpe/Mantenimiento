package bank;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest{
    BankAccount bankAccount;

    @BeforeEach
    void setup(){
        bankAccount = new BankAccount(0);
    }

    @Test
    @DisplayName("Al crear una cuenta de banco no puedo retirar")
    void withdrawInitialFalse(){
        int amount = 20;
        
        assertFalse(bankAccount.withdraw(amount));
    }

    @Test
    @DisplayName("Le introduzco un deposito y puedo retirar dinero")
    void withdrawWithDeposit(){
        bankAccount.deposit(50);

        int amount = 20;
        assertTrue(bankAccount.withdraw(amount));
    }

    @Test
    @DisplayName("Salta un error si quiero meter una cantidad negativa")
    void amountNegativeThrowError(){
        assertThrows(IllegalArgumentException.class,()-> bankAccount.deposit(-10));
    }

    @Test
    @DisplayName("El balance inicial es cero")
    void balanceInitialIsZero(){
        assertEquals(0, bankAccount.getBalance());
    }

    @Test 
    @DisplayName("Pago para un mes")
    void paymentForOneMonth(){
        double totalAmount = 1000;
        double interest = 2;
        int npayments = 500;
        double res = 2000;
        assertEquals(res, bankAccount.payment(totalAmount, interest, npayments));
    }

    @Test 
    @DisplayName("Cantidad pendiente")
    void pendingMonthZero(){
        double amount = 200;
        int month = 0;
        assertEquals(200, bankAccount.pending(amount, 2, 2, month));
    }

}