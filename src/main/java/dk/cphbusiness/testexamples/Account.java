package dk.cphbusiness.testexamples;

public class Account {
  private final String number;
  private long balance = 0L;
  
  public Account(String number) {
    this.number = number;
    }
  
  public long getBalance() {
    return balance;
    }
  
  public String getNumber() {
    return number;
    }

  public void move(long amount, Account target) throws AccountException {
    if (target == null) throw new AccountException();
    if (amount < 0L) throw new AccountException();
    this.balance -= amount;
    target.balance += amount;
    }
  
  }
