/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personalfinancemanager;



/**
 *
 * @author dell
 */
public class BudgetManager {
    static Messages msgs = new Messages();
    MiniDatabase db = new MiniDatabase();
    
    public void addBudget(double value, String category) {        
        if (db.budgetExists(category)){
            msgs.msg(1, category+" already exists.");
        }
        else{
            String budgetData = value+","+category;
            msgs.msg(0, " Budget added successfully ");
            db.addBudget(budgetData);
        }
    }
    
    public void removeBudget(String category) {
        
        if (db.budgetExists(category)){
            db.deleteBudget(category);
            msgs.msg(0, " Budget deleted successfully! ");
        }else{
            msgs.msg(2, " Budget not found. ");
        }
        
        
    }
    
    public boolean checkBudgetLimit() {
        return false;
    }
    
    public String[][] getBudgets() {
        String[][] budgets = db.readBudgets();
        return budgets;
    }
    
    public double getBudgetForCategory(String category) {
        String[][] budgetData = getBudgets();
        for (int i = 0; i < budgetData.length; i++) {
            if (budgetData[i][1].equals(category)) {
                return Double.parseDouble(budgetData[i][0]);
            }
        }
        return -1;
    }
}