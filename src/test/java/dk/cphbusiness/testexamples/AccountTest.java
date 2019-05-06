package dk.cphbusiness.testexamples;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class AccountTest {
  
  public AccountTest() {
    }
  
  @Test
  public void testCreateAccountIsNotNull() {
    Account account = new Account("A38");
    assertNotNull(account);
    
    }

  @Test
  public void testCreateAccountHasNumberAndZeroBalance() {
    Account account = new Account("A38");
    String number = account.getNumber();
    long balance = account.getBalance();
    assertThat(number, is("A38"));
    assertThat(balance, is(0L));
    }


  
  }
