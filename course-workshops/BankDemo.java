import java.util.Scanner;

public class BankDemo
{
  public static void main(String[] args)
  {

    CheckingAccount account = new CheckingAccount(101);
    account.deposit(500);
    Scanner sc = new Scanner(System.in);
    System.out.println("Колко пари искате да изтеглите?");

    double withdrawAmount =  readDouble(sc, 601);

    double rest = account.withdraw(withdrawAmount);
    System.out.println(rest);
  }

  static double readDouble(Scanner sc, double max){
    double withdrawAmount = sc.nextDouble();
    if (withdrawAmount <=0 || withdrawAmount > max){
      throw new InvalidInput("Можете да изтеглите сума до размера на баланса по сметката: 601лв");
    }
    return withdrawAmount;
  }
}
