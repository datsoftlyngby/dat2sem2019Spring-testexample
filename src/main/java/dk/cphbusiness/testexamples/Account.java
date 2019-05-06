package dk.cphbusiness.testexamples;

public class Account {
  private Bank bank;
  private final String number;
  private long balance = 0L;
  
  public Account(Bank bank, String number) {
    this.bank = bank;
    this.number = number;
    }
  
  public long getBalance() {
    return balance;
    }
  
  public String getNumber() {
    return number;
    }

  public void move(long amount, String targetNumber) throws AccountException {
    Account target = bank.find(targetNumber);
    if (target == null) throw new AccountException();
    if (amount < 0L) throw new AccountException();
    this.balance -= amount;
    target.balance += amount;
    }
  
  }
