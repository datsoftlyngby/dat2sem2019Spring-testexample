package dk.cphbusiness.testexamples;

import java.rmi.AccessException;
import static org.hamcrest.CoreMatchers.any;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class AccountTest {
  @Mock
  private Bank bank;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    }
  
  @Test
  public void testCreateAccountIsNotNull() {
    Account account = new Account(bank, "A38");
    assertNotNull(account);
    
    }

  @Test
  public void testCreateAccountHasNumberAndZeroBalance() {
    Account account = new Account(bank, "A38");
    String number = account.getNumber();
    long balance = account.getBalance();
    assertThat(number, is("A38"));
    assertThat(balance, is(0L));
    }

  @Test
  public void testMoveAmount() throws Exception {
    Account target = new Account(bank, "4711");
    when(bank.find("4711")).thenReturn(target);
    Account source = new Account(bank, "A38");
    source.move(10000L, "4711");
    assertThat(source.getBalance(), is(-10000L));
    assertThat(target.getBalance(), is(10000L));
    }

  @Test(expected = AccountException.class)
  public void testMoveAmountFailsOnNullTarget() throws Exception {
    Account source = new Account(bank, "A38");
    when(bank.find("NOSUCHNUMBER")).thenReturn(null);
    source.move(10000L, "NOSUCHNUMBER");
    }
  
  @Test
  public void testMoveToNullTargetLeavesSourceUntouched() throws Exception {
    Account source = new Account(bank, "A38");
    when(bank.find("NOSUCHNUMBER")).thenReturn(null);
    try {
      source.move(10000L, "NOSUCHNUMBER");
      }
    catch (AccountException ae) {
      assertThat(source.getBalance(), is(0L));
      }
    }
  
  @Test
  public void testMoveAmountFailsOnNegativeAmount() throws Exception {
    Account source = new Account(bank, "A38");
    Account target = new Account(bank, "4711");
    when(bank.find("4711")).thenReturn(target);
    try {
      source.move(-10000L, "4711");
      fail("Should not allow negative amount");
      }
    catch (AccountException ae) {
      assertThat(source.getBalance(), is(0L));
      assertThat(target.getBalance(), is(0L));
      }
    }
  
  }
