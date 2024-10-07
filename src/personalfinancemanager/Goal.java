/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package personalfinancemanager;

/**
 *
 * @author dell
 */
public class Goal {
    
    MiniDatabase db = new MiniDatabase();
    
    public void addGoal(String name, double targetAmount) {
        String goalData = name+","+targetAmount+","+"false";
        db.addGoal(goalData);
    }
    
    public void checkGoal() {}
    
    public String[][] getGoals() {
        String goals[][] = db.readGoals();
        return goals;
    }
}
