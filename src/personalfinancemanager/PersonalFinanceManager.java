/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package personalfinancemanager;

import java.util.Scanner;

/**
 *
 * @author dell
 */
public class PersonalFinanceManager {

    /**
     * @param args the command line arguments
     */
    static Messages msgs = new Messages();
    
    public static double calcBalance() {
         TransactionManager trs = new TransactionManager();
         String[][] transactions = trs.getTransactions();
         double balance = 0;
         
         for (int i = 0;i<transactions.length;i++) {
             if(transactions[i][1].strip().toLowerCase().equals("true")){                 
                 balance += Double.parseDouble(transactions[i][0]);
             }else if(transactions[i][1].strip().toLowerCase().equals("false")) {
                 balance -= Double.parseDouble(transactions[i][0]);
             }
         }
        return balance;
    }
    
    public static void displayMenu() {
        System.out.println(
                "--- PERSONAL FINANCE MANAGER ---\n"
                +"1. Add Transaction\n"
                +"2. View Transactions\n"
                +"3. Remove Transaction\n"
                +"4. Add Budget\n"
                +"5. View Budgets\n"
                +"6. Remove Budget\n"
                +"7. Add Goal\n"
                +"8. View Goals\n"
                +"9. Quit\n"
        );
    }
    
    
    public static double gt0doubleInput(){
        Scanner inpx = new Scanner(System.in);
        double a = 0;
        while (a<=0) {
            try {
                a = inpx.nextDouble();
                if(a<=0){
                    msgs.msg(1, "!Must be greater than 0!");
                }
            }catch(Exception e){
                msgs.msg(1, "!Enter a valid amount!");
                a = 0;
            }
            
            inpx.nextLine();
        }
        return a;
    }
    
    
    
    
    public static void main(String[] args) {
        Goal goalManager = new Goal();
        BudgetManager bmr = new BudgetManager();
        TransactionManager trs = new TransactionManager();
        Scanner inp = new Scanner(System.in);
        double balance = calcBalance();
              
        while(true) {
            balance = calcBalance();
            if(balance<0){
                msgs.msg(2, " Clear your debts ");
            }
            msgs.msg(3, " Current Balance: Rs "+balance+" ");
            displayMenu();
            System.out.print(">>");
            char choice = inp.nextLine().charAt(0);
            switch(choice){
                case '1':
                    boolean isIncome = false;
                    System.out.print("Enter Amount: ");
                    double a = gt0doubleInput();
                    System.out.print("Expense - 0\nIncome - 1\n>>");
                    char option = inp.nextLine().charAt(0);
                    if(option=='1')
                        isIncome = true;
                    
                    if(balance<0 && !isIncome) {
                        msgs.msg(2, " Clear your debts to continue spending ");
                        break;
                    }
                    
                    System.out.print("Provide a short description or category:");
                    String desc = inp.nextLine().strip().toLowerCase().replaceAll(",", "");
                    
                    
                    
                    if(bmr.getBudgetForCategory(desc)!=-1){
                        if((trs.sumTransactionAmounts(desc)+a)>bmr.getBudgetForCategory(desc)){
                            msgs.msg(2, " This transaction exceeds your budget ");
                            break;
                        }
                        
                    }
                    
                    
                    trs.addTransaction(a, isIncome, desc);
                    msgs.msg(0, " Transaction added successfully ");
                    if(bmr.getBudgetForCategory(desc)!=-1)
                        msgs.msg(3, " Remaining budget is "+(bmr.getBudgetForCategory(desc)-trs.sumTransactionAmounts(desc)));
                    
                    break;
                case '2':
                    String[][] data = trs.getTransactions();
                    msgs.msg(3, " Transaction History ");
                    for(int i=0;i<data.length;i++){
                        if(data[i][1].strip().toLowerCase().equals("true")){
                            msgs.msg(5, "[->]  Recieved Rs "+data[i][0]+". "+data[i][2]+". ");
                        }else{
                            msgs.msg(6, "[<-] Spent Rs "+data[i][0]+". "+data[i][2]+". ");
                        }
                    }
                    break;
                    
                case '3':
                    msgs.msg(3, " Delete Transaction ");
                    System.out.print("Enter category: ");
                    String delTransaction = inp.nextLine();
                    trs.removeTransaction(delTransaction);
                    break;
                    
                case '4':
                    msgs.msg(3, " Add Budget ");
                    System.out.print("Enter Amount: ");
                    double limit = gt0doubleInput();
                    System.out.print("Enter category: ");
                    String budget = inp.nextLine().strip().toLowerCase().replaceAll(",", "");
                    bmr.addBudget(limit, budget); 
                    break;
                    
                case '5':
                    System.out.println();
                    msgs.msg(3, " Budgets ");
                    String[][] budgetsData = bmr.getBudgets();
                    if(budgetsData.length==0){
                        msgs.msg(2, "No budgets found.");
                        break;
                    }
                    for(int i=0;i<budgetsData.length;i++) {
                        msgs.msg(4, "- Rs."+budgetsData[i][0]+" for "+budgetsData[i][1]);
                    }
                    break;
                    
                case '6':
                    msgs.msg(3, " Delete budget ");
                    System.out.print("Enter category: ");
                    String deleteBudget = inp.nextLine();
                    bmr.removeBudget(deleteBudget);
                    break;
                    
                case '7':
                    System.out.println("Under development");
                    break;

                default:
                    System.out.println("Invalid option. Try again.\n---");
            }
        }

        
    }
    
}
