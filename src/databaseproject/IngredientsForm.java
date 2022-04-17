
package databaseproject;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;



public class IngredientsForm {
    DatabaseFunctions databaseFunctions = new DatabaseFunctions();
    private final String url = "jdbc:postgresql://sxterm.mat.umk.pl/mgorski?user=mgorski&password=***&ssl=false";
    private int mode;
    private int id = databaseFunctions.getIngredientsLastIndex() + 1;
    private JFrame frame;
    private JTextField textFieldId;
    private JTextField textFieldName;
    private JTextField textFieldCalories;
    private JTextField textFieldQuantity;
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,80,100));
    private JButton buttonMode;
    private JButton buttonExit = new JButton("Exit");
    private JComboBox comboBoxIngredients;
    private JComboBox comboBoxIngredients2;
    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    
    public IngredientsForm(int mode) throws SQLException{
        this.mode = mode;
        initialize();
        buttonExit.addActionListener(new ActionListener(){
            
         public void actionPerformed(ActionEvent e) { 
         
                   frame.dispose();
                }
        
        });
        
        buttonMode.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e) { 
                    if(mode == 1 ){
                        try {
                           databaseFunctions.insertIntoIngredients(id, textFieldName.getText(),Integer.parseInt(textFieldCalories.getText()), Integer.parseInt(textFieldQuantity.getText()));
                       } catch (SQLException ex) {
                           Logger.getLogger(DailymenusForm.class.getName()).log(Level.SEVERE, null, ex);
                       }
                       
                      }
                     else if(mode == 2){
                        try {
                            databaseFunctions.editIngredients(comboBoxIngredients2.getSelectedItem(), textFieldId.getText(),comboBoxIngredients.getSelectedItem().toString());
                        } catch (SQLException ex) {
                            Logger.getLogger(IngredientsForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         
                         }
                     else{
                        try {
                            databaseFunctions.deleteFromIngredients(comboBoxIngredients.getSelectedItem().toString());
                        } catch (SQLException ex) {
                            Logger.getLogger(IngredientsForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
    
    public void initAddFrame(){        
                frame = new JFrame("Add ingredient");
                frame.setResizable(false);
                Container c = frame.getContentPane();
                panel.setLayout(new GridLayout(5,3,30,45));
                int frameWidth = frame.getSize().width;
                int frameHeight = frame.getSize().height;
		frame.setBounds(width/3, height/3, width/3, height/2);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
                textFieldId.setText(Integer.toString(id));
                textFieldId.setEnabled(false);
                
                JLabel labelId = new JLabel("id_i");
                
                textFieldName = new JTextField();
		textFieldName.setColumns(10);
                
                JLabel labelName = new JLabel("name");
 
                textFieldCalories = new JTextField();
		textFieldCalories.setColumns(10);

                JLabel labelCalories = new JLabel("calories / 100g");
                
                textFieldQuantity = new JTextField();
		textFieldQuantity.setColumns(10);
                
                JLabel labelQuantity = new JLabel("quantity (in grams)");
                
                
                frame.setVisible(true);
                panel.add(labelId);
                panel.add(textFieldId);
                panel.add(labelName);
                panel.add(textFieldName);
                panel.add(labelCalories);
                panel.add(textFieldCalories);
                panel.add(labelQuantity);
                panel.add(textFieldQuantity);
                panel1.add(panel);
                panel1.setVisible(true);
                buttonMode = new JButton("Add");
                panel.add(buttonMode);
                panel.add(buttonExit);                
                c.add(panel1);
    }
    
      public void initEditFrame() throws SQLException{        
                frame = new JFrame("Edit ingredient");
                frame.setResizable(false);
                Container c = frame.getContentPane();
                panel.setLayout(new GridLayout(5,2,30,30));
                int frameWidth = frame.getSize().width;
                int frameHeight = frame.getSize().height;
		frame.setBounds(width/3, height/3, width/3, height/3 + 60);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                String[] ingredientList = databaseFunctions.getIngredientList();
                String[] columnIngredients = databaseFunctions.getColumnNamesFromIngredientsWithoutID();   
		comboBoxIngredients = new JComboBox(ingredientList);
                comboBoxIngredients2 = new JComboBox(columnIngredients);
                JLabel labelName1 = new JLabel("Select name of ingredient you want to edit");
		textFieldId = new JTextField();
		textFieldId.setColumns(10);
                JLabel labelTextfield = new JLabel("Type new value");
                JLabel labelTextfield1 = new JLabel("Select column");
                
                frame.setVisible(true);
                panel.add(labelName1);
                panel.add(comboBoxIngredients);
                panel.add(labelTextfield1);
                panel.add(comboBoxIngredients2);
                panel.add(labelTextfield);
                panel.add(textFieldId);
                

                panel1.add(panel);
                panel1.setVisible(true);
                buttonMode = new JButton("Edit");
                panel.add(buttonMode);
                panel.add(buttonExit);                
                c.add(panel1);
    }
    
    public void initDeleteFrame() throws SQLException{
                frame = new JFrame("Delete ingredient");
                frame.setResizable(false);
                Container c = frame.getContentPane();
                panel.setLayout(new GridLayout(1,2,30,30));
                int frameWidth = frame.getSize().width;
                int frameHeight = frame.getSize().height;
		frame.setBounds(width/3, height/3, width/3, height/3);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                
                String[] ingredientList = databaseFunctions.getIngredientList();
                
		comboBoxIngredients = new JComboBox(ingredientList);
                JLabel labelName = new JLabel("Select name of ingredient you want to delete");
                
                frame.setVisible(true);

                panel.add(labelName);
                panel.add(comboBoxIngredients);
                panel1.add(panel);
                panel1.setVisible(true);
                buttonMode = new JButton("Delete");
                panel1.add(buttonMode);
                panel1.add(buttonExit);                
                c.add(panel1);
        
    }
    
}
