/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personalfinancemanager;

/**
 *
 * @author dell
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MiniDatabase {
//    private static final String transactionsFilePath = "D:\\Abdullah\\Java\\PersonalFinanceManager\\data\\transactions.csv";
    private static final String transactionsFilePath = "data\\transactions.csv";
    private static final String budgetsFilePath = "data\\budgets.csv";
    

    public MiniDatabase() {
        createFiles();
    }
    
    

    private void createFiles() {
        createFile(transactionsFilePath);
        createFile(budgetsFilePath);
    }

    private void createFile(String filePath) {
        try {
            File file = new File(filePath);
            if (file.createNewFile()) {
                System.out.println("File created: " + filePath);
            }
            
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + filePath);
            e.printStackTrace();
        }
    }
    
    private static int countLinesInFile(String fileName) {
        int lines = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.out.println("Could not count lines in the file: " + e.getMessage());
        }
        return lines;
    }

    public void addTransaction(String transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(transactionsFilePath, true))) {
            writer.write(transaction);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while adding a transaction. "+ e.getMessage());
        }
    }
    
    public String[][] readTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(transactionsFilePath))) {
        int noOfLines = 0;
        while (reader.readLine() != null) {
            noOfLines++;
        }

        String[][] transactions = new String[noOfLines][3];

        try (BufferedReader newReader = new BufferedReader(new FileReader(transactionsFilePath))) {
            String line;
            int index = 0;
            while ((line = newReader.readLine()) != null) {
                transactions[index] = line.split(",");
                index++;
            }
        }

        return transactions;
        } catch (IOException e) {
            System.out.println("Error.");
            e.printStackTrace();
            return new String[0][0];
        }
    }

    

    
    public void addBudget(String budget) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(budgetsFilePath, true))){
            writer.write(budget);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error: could't add budget.");
            e.printStackTrace();
        }
    }
    
    public String[][] readBudgets() {
    try (BufferedReader reader = new BufferedReader(new FileReader(budgetsFilePath))) {
        int noOfLines = 0;
        while (reader.readLine() != null) {
            noOfLines++;
        }

        String[][] budgetsArray = new String[noOfLines][2];

        try (BufferedReader newReader = new BufferedReader(new FileReader(budgetsFilePath))) {
            String line;
            int index = 0;
            while ((line = newReader.readLine()) != null) {
                budgetsArray[index] = line.split(",");
                index++;
            }
        }

        return budgetsArray;
    } catch (IOException e) {
        System.out.println("Error.");
        e.printStackTrace();
        return new String[0][0];
    }
}
    
    public boolean budgetExists(String category) {
        String [][] budgets = readBudgets();
        for(int i=0; i<budgets.length;i++){
            if(budgets[i][1].strip().toLowerCase().equals(category))
                return true;
        }
        
        return false;
    }
    
    
    public void deleteBudget(String category) {
        int lineCount = countLinesInFile(budgetsFilePath);

        if (lineCount == 0) {
            System.out.println("Category not found.");
            return;
        }

        String[][] budgetData = new String[lineCount][2];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(budgetsFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (!values[1].equalsIgnoreCase(category)) {
                    budgetData[index][0] = values[0];
                    budgetData[index][1] = values[1];
                    index++;
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read the budget file: " + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new File(budgetsFilePath))) {
            for (int i = 0; i < index; i++) {
                writer.println(budgetData[i][0] + "," + budgetData[i][1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not update budget data: " + e.getMessage());
        }
    }
    
    public boolean transactionExists(String category) {
    try (BufferedReader br = new BufferedReader(new FileReader(transactionsFilePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");

            if (values[2].equals(category)) {
                return true;
            }
        }
    } catch (IOException e) {
        System.out.println("Could not read the transaction file: " + e.getMessage());
    }

    return false;
}
    
    public void deleteTransaction(String category) {

    int lineCount = countLinesInFile(transactionsFilePath);

    String[][] transactionData = new String[lineCount][3];
    int index = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(transactionsFilePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");

            if (!values[2].equals(category)) {
                transactionData[index][0] = values[0];
                transactionData[index][1] = values[1];
                transactionData[index][2] = values[2];
                index++;
            }
        }
    } catch (IOException e) {
        System.out.println("Could not read the transaction file: " + e.getMessage());
    }

    try (PrintWriter writer = new PrintWriter(new File(transactionsFilePath))) {
        for (int i = 0; i < index; i++) {
            writer.println(transactionData[i][0] + "," + transactionData[i][1] + "," + transactionData[i][2]);
        }
        System.out.println("Transaction for category '" + category + "' deleted successfully.");
    } catch (FileNotFoundException e) {
        System.out.println("Could not save updated transaction data: " + e.getMessage());
    }
}
    public void clearUserData() {
        File budgets = new File(budgetsFilePath);
        File transactions = new File(budgetsFilePath);
        File goals = new File(budgetsFilePath);
    }

}

