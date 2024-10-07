/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personalfinancemanager;

/**
 *
 * @author dell
 */
public class TransactionManager {
    MiniDatabase db = new MiniDatabase();
    Messages msgs = new Messages();

    public void addTransaction(double amount, boolean isIncome, String desc) {
        String transactionData = amount+","+isIncome+","+desc;
        db.addTransaction(transactionData);
    }
    
    public void removeTransaction(String delTransaction) {
        if (!db.transactionExists(delTransaction)) {
            msgs.msg(2, " Transaction not found. ");
            return;
        }
        db.deleteTransaction(delTransaction);
        msgs.msg(0, " Transaction deleted successfully! ");
    }
    
    public String[][] getTransactions() {
        String[][] transactions = db.readTransactions();
        return transactions;
    }
    
    public String[][] getTransactionsByCategory(String category) {
        String[][] transactionData = getTransactions();
        int count = 0;
        for (int i = 0; i < transactionData.length; i++) {
            if (transactionData[i][2].equals(category)) {
                count++;
            }
        }

        if (count == 0) {
            return new String[0][3];
        }

        String[][] result = new String[count][3];
        int index = 0;
        for (int i = 0; i < transactionData.length; i++) {
            if (transactionData[i][2].equals(category) && transactionData[i][1].toLowerCase().equals("false")) {
                result[index][0] = transactionData[i][0];
                result[index][1] = transactionData[i][1];
                result[index][2] = transactionData[i][2];
                index++;
            }
        }

        return result;
    }
    
    public double sumTransactionAmounts(String category) {
        String[][] transactions = getTransactionsByCategory(category);
        double totalAmount = 0.0;

        for (int i = 0; i < transactions.length; i++) {
            totalAmount += Double.parseDouble(transactions[i][0]);
        }

        return totalAmount;
    }
    
    
}
