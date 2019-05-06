package dk.cphbusiness.testexamples;

import java.rmi.AccessException;
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

  @Test
  public void testMoveAmount() throws Exception {
    Account source = new Account("A38");
    Account target = new Account("4711");
    source.move(10000L, target);
    assertThat(source.getBalance(), is(-10000L));
    assertThat(target.getBalance(), is(10000L));
    }

  @Test(expected = AccountException.class)
  public void testMoveAmountFailsOnNullTarget() throws Exception {
    Account source = new Account("A38");
    source.move(10000L, null);
    }
  
  @Test
  public void testMoveToNullTargetLeavesSourceUntouched() throws Exception {
    Account source = new Account("A38");
    try {
      source.move(10000L, null);
      }
    catch (AccountException ae) {
      assertThat(source.getBalance(), is(0L));
      }
    }
  
  @Test
  public void testMoveAmountFailsOnNegativeAmount() throws Exception {
    Account source = new Account("A38");
    Account target = new Account("4711");
    try {
      source.move(-10000L, target);
      fail("Should not allow negative amount");
      }
    catch (AccountException ae) {
      assertThat(source.getBalance(), is(0L));
      assertThat(target.getBalance(), is(0L));
      }
    }
  
  }
