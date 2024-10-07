/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personalfinancemanager;

/**
 *
 * @author dell
 */
public class Messages {
    public void msg(int type, String mesg){
        switch (type) {
            case 0:
                System.out.println("\u001B[30m"+"\u001B[42m"+mesg+"\u001B[0m");// Success
                break;
            case 1:
                System.out.println("\u001B[30m"+"\u001B[43m"+mesg+"\u001B[0m");// Warning
                break;
            case 2:
                System.out.println("\u001B[30m"+"\u001B[41m"+mesg+"\u001B[0m");// Error
                break;
            case 3:
                System.out.println("\u001B[37m"+"\u001B[44m"+mesg+"\u001B[0m");// Info or Headings
                break;
            case 4:
                System.out.println("\u001B[34m"+mesg+"\u001B[0m");// Heading or data
                break;
            case 5:
                System.out.println("\u001B[32m"+mesg+"\u001B[0m");// Recieved
                break;
            case 6:
                System.out.println("\u001B[31m"+mesg+"\u001B[0m");// Spent
                break;
            default:
                break;
        }
    }
}
