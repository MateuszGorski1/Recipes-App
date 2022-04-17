
package databaseproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;


 
public class DisplayData {
    DatabaseFunctions databaseFunctions = new DatabaseFunctions();
    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int mode;
    private JFrame frame;
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    JTextPane pane1, pane2, pane3;
     
     public DisplayData( int mode ) throws SQLException{
         this.mode = mode;
         initialize();
     }
     
      public void initialize() throws SQLException{
                if(mode == 1){
                    displayDailymenus();
                }
                else if( mode == 2 ){
                    displayRecipes();
                }
                else if( mode == 3){
                    displayIngredients();
                }
    }
      
    public void displayDailymenus() throws SQLException{
        int rows = databaseFunctions.getDailymenusSize();
        int firstId = databaseFunctions.getFirstDailymenuId();
        frame = new JFrame("Dailymenus");
        frame.setResizable(false);
        frame.setBounds(width/3, height/3, width/2, height/2);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        Container c = frame.getContentPane();
        GridLayout layout = new GridLayout(rows + 1, 3, 2, 2);
        panel.setLayout(layout);
        JLabel label1 = new JLabel("Name of dailymenu:");
        JLabel label2 = new JLabel("Calories:");
        JLabel label3 = new JLabel("Recipes:");
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        for( int i = firstId ; i <= rows + firstId - 1; i++){
            pane1 = new JTextPane();
            pane2 = new JTextPane();
            pane3 = new JTextPane();
            pane1.setEditable(false);
            pane2.setEditable(false);
            pane3.setEditable(false);
            pane1.setBackground(Color.GREEN);
            pane2.setBackground(Color.GREEN);
            pane3.setBackground(Color.GREEN);
            pane1.setText(databaseFunctions.getNameOfDailymenuById(i));
            pane2.setText(Integer.toString(databaseFunctions.getCaloriesOfDailymenuById(i)));
            ArrayList<Integer> list = databaseFunctions.getRecipesIdsIdsByDailymenusId(i);
            for(int j = 0; j < list.size(); j++){
                String nameOfRecipe = databaseFunctions.getNameOfRecipeById(list.get(j));
                if(j == list.size() - 1) pane3.setText(pane3.getText() + nameOfRecipe);
                else{
                    pane3.setText(pane3.getText() + nameOfRecipe + ", ");
                }
            }
            panel.add(pane1);
            panel.add(pane2);
            panel.add(pane3);
           // panel1.add(panel);
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        c.add(scrollPane);
    }
    
    public void displayRecipes() throws SQLException{
        int rows = databaseFunctions.getRecipesSize();
        int firstId = databaseFunctions.getFirstRecipeId();
       // panel1.setLayout(new GridLayout(rows + 1,3,0,0));
        frame = new JFrame("Recipes");
        frame.setResizable(false);
        frame.setBounds(width/3, height/3, width/2, height/2);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        Container c = frame.getContentPane();
        GridLayout layout = new GridLayout(rows+1, 3, 2, 2);
        panel.setLayout(layout);
        JLabel label1 = new JLabel("Name of recipe:");
        JLabel label2 = new JLabel("Calories:");
        JLabel label3 = new JLabel("Ingredients:");
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        for( int i  = firstId ; i <= rows + firstId - 1; i++){
            pane1 = new JTextPane();
            pane2 = new JTextPane();
            pane3 = new JTextPane();
            pane1.setEditable(false);
            pane2.setEditable(false);
            pane3.setEditable(false);
            pane1.setBackground(Color.GREEN);
            pane2.setBackground(Color.GREEN);
            pane3.setBackground(Color.GREEN);
            pane1.setText(databaseFunctions.getNameOfRecipeById(i));
            pane2.setText(Integer.toString(databaseFunctions.getCaloriesOfRecipeById(i)));
            ArrayList<Integer> list = databaseFunctions.getIngredientsIdsByRecipesId(i);
            for(int j = 0; j < list.size(); j++){
                String nameOfIngredient = databaseFunctions.getNameOfIngredientById(list.get(j));
                if(j == list.size() - 1) pane3.setText(pane3.getText() + nameOfIngredient);
                else{
                    pane3.setText(pane3.getText() + nameOfIngredient + ", ");
                }
            }
            
            panel.add(pane1);
            panel.add(pane2);
            panel.add(pane3);
           // panel1.add(panel);
        }
      
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        c.add(scrollPane);
        
    }
    
    public void displayIngredients() throws SQLException{ 
        int rows = databaseFunctions.getIngredientsSize();
        int firstId = databaseFunctions.getFirstIngredientId();
        System.out.println(firstId);
       // panel1.setLayout(new GridLayout(rows + 1,3,0,0));
        frame = new JFrame("Ingredients");
        frame.setResizable(false);
        frame.setBounds(width/3, height/3, width/2, height/2);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        Container c = frame.getContentPane();
        GridLayout layout = new GridLayout(rows+1, 3, 2, 2);
        panel.setLayout(layout);
        panel.setVisible(true);
        JLabel label1 = new JLabel("Name of ingredient:");
        JLabel label2 = new JLabel("Calories:");
        JLabel label3 = new JLabel("Quantity in grams:");
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        for( int i = firstId ; i <= rows + firstId - 1; i++){
            pane1 = new JTextPane();
            pane2 = new JTextPane();
            pane3 = new JTextPane();
            pane1.setEditable(false);
            pane2.setEditable(false);
            pane3.setEditable(false);
            pane1.setBackground(Color.GREEN);
            pane2.setBackground(Color.GREEN);
            pane3.setBackground(Color.GREEN);
            pane1.setText(databaseFunctions.getNameOfIngredientById(i));
            pane2.setText(Integer.toString(databaseFunctions.getCaloriesOfIngredientById(i)));
            pane3.setText(Integer.toString(databaseFunctions.getQuantityOfIngredientById(i)));
            panel.add(pane1);
            panel.add(pane2);
            panel.add(pane3);
           // panel1.add(panel);
           
        }
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        c.add(scrollPane);
    }
      
      
}
