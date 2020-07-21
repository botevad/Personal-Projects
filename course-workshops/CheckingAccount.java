public class CheckingAccount
{
  double balance;
  int number;

  public CheckingAccount(double balance)
  {
    this.balance = balance;
  }

  public double getBalance()
  {
    return balance;
  }

  public int getNumber()
  {
    return number;
  }

  public double deposit (double amount){
    assert amount > 0;
    return balance += amount;
  }
  public double withdraw (double amount){
    assert amount > 0;
    return balance -= amount;
  }


}
