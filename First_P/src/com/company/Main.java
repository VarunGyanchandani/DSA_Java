package com.company;
import java.util.Scanner;
class BankAccount
{
    int accno,accbal;
    String name;
    void setdata()
    {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter the acount number:");
        accno=s.nextInt();
        System.out.println("Enter the acount name:");
        name=s.next();
        System.out.println("Enter the acount balance:");
        accbal=s.nextInt();
    }
    void display()
    {
        System.out.println(accno);
        System.out.println(name);
        System.out.println(accbal);
    }
    void withdraw(int t)
    {
        System.out.println("Total amount:"+(accbal-t));
    }
    void deposit(int t)
    {
        System.out.println("Total amount:"+(accbal+t));
    }
    void transfer(BankAccount a1,BankAccount a2)
    {
        int t;
        Scanner s=new Scanner(System.in);
        System.out.println("Enter the amount to transfer:");
        t=s.nextInt();
        a1.withdraw(t);
        a2.deposit(t);
    }
}
class Main
{
    public static void main(String[] args)
    {
        BankAccount b1=new BankAccount();
        BankAccount b2=new BankAccount();
        b1.setdata();
        b1.display();
        b2.setdata();
        b2.display();
        BankAccount t1=new BankAccount();
        t1.transfer(b1, b2);
    }
}

