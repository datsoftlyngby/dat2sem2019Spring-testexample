package dk.cphbusiness.testexamples;

public interface Bank {
  Account find(String number) throws AccountException;
  }
