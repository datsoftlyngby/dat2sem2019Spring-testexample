package dk.cphbusiness.testexamples;

public class MockBank implements Bank {

  @Override
  public Account find(String number) throws AccountException {
    if ("NOSUCHNUMBER".equals(number)) return null;
    return new Account(this, number);
    }
  
  }
