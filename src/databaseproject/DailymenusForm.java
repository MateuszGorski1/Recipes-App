
package databaseproject;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class DailymenusForm {
    DatabaseFunctions databaseFunctions = new DatabaseFunctions();
    private final String url = "jdbc:postgresql://sxterm.mat.umk.pl/mgorski?user=mgorski&password=***&ssl=false";
    private int mode;
    private int id = databaseFunctions.getDailymenusLastIndex() + 1;
    private JFrame frame;
    private JTextField textFieldId;
    private JTextField textFieldName;
    private JTextField textFieldCalories;
    private JComboBox comboBoxRecipes;
    private JComboBox comboBoxRecipes2;
    private JPanel panel = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,100,80));
    private JButton buttonMode;
    private JButton buttonExit = new JButton("Exit");
    JList list;
    String[] selectedElementsList;
    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public DailymenusForm(int mode) throws SQLException{
        this.mode = mode;
        initialize();
        buttonExit.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) { 
                   frame.dispose();
                }
        
        });
        
        buttonMode.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) { 
             
                    if(mode == 1){
                        try {
                           databaseFunctions.insertIntoDailymenus(list, id, textFieldName.getText());
                        } catch (SQLException ex) {
                            Logger.getLogger(RecipesForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                    else if( mode == 2){
                        try {
                            databaseFunctions.editDailymenus(comboBoxRecipes.getSelectedItem().toString(), list, textFieldId.getText());
                        } catch (SQLException ex) {
                            Logger.getLogger(RecipesForm.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    else if( mode == 3){
                       databaseFunctions.deleteFromDailymenus(comboBoxRecipes.getSelectedItem().toString());
                    }
                    
                    
                    frame.dispose();
                }
        
        });
    }
    public void initialize() throws SQLException{
                if(mode == 1){
                    initAddFrame();
                }
                else if( mode == 2 ){
                    initEditFrame();
                }
                else if( mode == 3){
                    initDeleteFrame();
                }
                
    }
    
     public void initAddFrame() throws SQLException{

                frame = new JFrame("Add dailymenu");
                frame.setResizable(false);
                Container c = frame.getContentPane();
                GridLayout layout = new GridLayout(5,2,40,80);
                GridLayout layout2 = new GridLayout(3,1,0,0);
                panel.setLayout(layout);
                panel2.setLayout(layout2);
                int frameWidth = frame.getSize().width;
                int frameHeight = frame.getSize().height;
		frame.setBounds(width/3, height/3, width/2, height/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
                textFieldId.setText(Integer.toString(id));
                textFieldId.setEnabled(false);
                
                JLabel labelId = new JLabel("id_d");
                
                textFieldName = new JTextField();
		textFieldName.setColumns(10);
                
                JLabel labelName = new JLabel("name");
 
                textFieldCalories = new JTextField();
		textFieldCalories.setColumns(10);
                textFieldCalories.setEnabled(false);
                

                JLabel labelCalories = new JLabel("calories / 100g");
                
                String[] recipesList = databaseFunctions.getRecipesList();
                
                list = new JList(recipesList); 
                list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                list.setLayoutOrientation(JList.VERTICAL_WRAP);
                list.setVisibleRowCount(2);
 
                JScrollPane listScroller = new JScrollPane(list);
                listScroller.setPreferredSize(new Dimension(240, 80));   
                listScroller.setViewportView(list);
             
                JLabel labelIdI = new JLabel("Select recipes you want to add:");
                
                frame.setVisible(true);
                panel.add(labelId);
                panel.add(textFieldId);
                panel.add(labelName);
                panel.add(textFieldName);
                panel.add(labelCalories);
                panel.add(textFieldCalories);
               
                
                panel1.setVisible(true);
                buttonMode = new JButton("Add");
                panel2.add(labelIdI);
                panel2.add(listScroller);
                panel1.add(panel);
                panel1.add(panel2);
                panel.add(buttonMode);
                panel.add(buttonExit);                
                c.add(panel1);
                
        
    }
    
    public void initEditFrame() throws SQLException{
                frame = new JFrame("Edit dailymenu");
                frame.setResizable(false);
                Container c = frame.getContentPane();
                panel.setLayout(new GridLayout(4,2,30,30));
                GridLayout layout2 = new GridLayout(3,1,0,0);
                panel2.setLayout(layout2);
                int frameWidth = frame.getSize().width;
                int frameHeight = frame.getSize().height;
		frame.setBounds(width/3, height/3, width/2 + 60, height/3 + 60);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                String[] dailymenusList = databaseFunctions.getDailymenusList();
                String[] columnDailymenus = databaseFunctions.getNameColumnFromDailymenus();
		comboBoxRecipes = new JComboBox(dailymenusList);
                comboBoxRecipes2 = new JComboBox(columnDailymenus);
                JLabel labelName1 = new JLabel("Select name of dailymenu you want to edit");
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
                JLabel labelTextfield = new JLabel("Type new value");
                JLabel labelTextfield1 = new JLabel("Select column");
                String[] recipesList = databaseFunctions.getRecipesList();
                list = new JList(recipesList); 
                list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                list.setLayoutOrientation(JList.VERTICAL_WRAP);
                list.setVisibleRowCount(2);
 
                JScrollPane listScroller = new JScrollPane(list);
                listScroller.setPreferredSize(new Dimension(240, 80));   
                listScroller.setViewportView(list);
             
                JLabel labelIdI = new JLabel("Select recipes again:");
                
                frame.setVisible(true);
                panel.add(labelName1);
                panel.add(comboBoxRecipes);
                panel.add(labelTextfield1);
                panel.add(comboBoxRecipes2);
                panel.add(labelTextfield);
                panel.add(textFieldId);
                panel1.setVisible(true);
                panel2.add(labelIdI);
                panel2.add(listScroller);
                panel1.setVisible(true);
                panel1.add(panel);
                panel1.add(panel2);
                
                buttonMode = new JButton("Edit");
                panel.add(buttonMode);
                panel.add(buttonExit);                
                c.add(panel1);
        
    }
    
    public void initDeleteFrame() throws SQLException{
                frame = new JFrame("Delete dailymenu");
                frame.setResizable(false);
                Container c = frame.getContentPane();
                panel.setLayout(new GridLayout(1,2,30,30));
                int frameWidth = frame.getSize().width;
                int frameHeight = frame.getSize().height;
		frame.setBounds(width/3, height/3, width/3, height/3);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                String[] dailymenusList = databaseFunctions.getDailymenusList();
                
		comboBoxRecipes = new JComboBox(dailymenusList);
                JLabel labelName = new JLabel("Select name of dailymenu you want to delete");
                
                frame.setVisible(true);

                panel.add(labelName);
                panel.add(comboBoxRecipes);
                panel1.add(panel);
                panel1.setVisible(true);
                buttonMode = new JButton("Delete");
                panel1.add(buttonMode);
                panel1.add(buttonExit);                
                c.add(panel1);
    }
 
}

