
package databaseproject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class App extends JFrame  {
    DatabaseFunctions databaseFunctions = new DatabaseFunctions();
    private final JMenuBar menuBar = new JMenuBar();
    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private final String url = "jdbc:postgresql://sxterm.mat.umk.pl/mgorski?user=mgorski&password=***&ssl=false";
    JMenu menuDailymenu = new JMenu("Dailymenu");
    JMenu menuRecipes = new JMenu("Recipes");
    JMenu menuIngredients = new JMenu("Ingredients");
    JMenuItem submenuDailymenusAdd = new JMenuItem("Add");
    JMenuItem submenuDailymenusEdit = new JMenuItem("Edit");
    JMenuItem submenuDailymenusDelete = new JMenuItem("Delete");
    JMenuItem submenuRecipesAdd = new JMenuItem("Add");
    JMenuItem submenuRecipesEdit = new JMenuItem("Edit");
    JMenuItem submenuRecipesDelete = new JMenuItem("Delete");
    JMenuItem submenuIngredientsAdd = new JMenuItem("Add");
    JMenuItem submenuIngredientsEdit = new JMenuItem("Edit");
    JMenuItem submenuIngredientsDelete = new JMenuItem("Delete");
    JButton buttonDailymenus = new JButton("Show my dailymenus");
    JButton buttonRecipes = new JButton("Show my recipes");
    JButton buttonIngredients = new JButton("Show my ingredients");
    JPanel panel = new JPanel();
    JInternalFrame frameDailymenus = new JInternalFrame("Dailymenus",true,true,true,true);
    JInternalFrame frameRecipes = new JInternalFrame("Recipes",true,true,true,true);
    JInternalFrame frameIngredients = new JInternalFrame("Ingredients",true,true,true,true);
    String columnNameDailymenus1,columnNameDailymenus2,columnNameDailymenus3,columnNameDailymenus4;
    
        public App(){
            
            initComponents();
            buttonDailymenus.addActionListener(new ActionListener(){
               @Override
                public void actionPerformed(ActionEvent e) {
                   try {
                       DisplayData showDailymenus = new DisplayData(1);
                   } catch (SQLException ex) {
                       Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
            });

            buttonRecipes.addActionListener(new ActionListener(){
               @Override
                public void actionPerformed(ActionEvent e) {
                   try {
                       DisplayData showRecipes = new DisplayData(2);
                   } catch (SQLException ex) {
                       Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
            });
            buttonIngredients.addActionListener(new ActionListener(){
               @Override
                public void actionPerformed(ActionEvent e) {
                   try {
                       DisplayData showIngredients = new DisplayData(3);
                   } catch (SQLException ex) {
                       Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                   }
                }
            });
            
            
            
            submenuDailymenusAdd.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    try {
                        DailymenusForm form = new DailymenusForm(1);
                    } catch (SQLException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
            
            
            submenuDailymenusEdit.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    try {
                        DailymenusForm form = new DailymenusForm(2);
                    } catch (SQLException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
            
            submenuDailymenusDelete.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    try {
                        DailymenusForm form = new DailymenusForm(3);
                    } catch (SQLException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
             
            submenuRecipesAdd.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    try {
                        RecipesForm form = new RecipesForm(1);
                    } catch (SQLException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
            
            submenuRecipesEdit.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    try {
                        RecipesForm form = new RecipesForm(2);
                    } catch (SQLException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
             
             submenuRecipesDelete.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    try {
                        RecipesForm form = new RecipesForm(3);
                    } catch (SQLException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
            
            submenuIngredientsAdd.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    try {
                        IngredientsForm form = new IngredientsForm(1);
                    } catch (SQLException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
            
            submenuIngredientsEdit.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    try {
                        IngredientsForm form = new IngredientsForm(2);
                    } catch (SQLException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
            
            submenuIngredientsDelete.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    try {
                        IngredientsForm form = new IngredientsForm(3);
                    } catch (SQLException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                }
            });
            
           
        }
       
        public void initComponents(){
            initFrame();
            initMenu();
            initButtons();    
       }
     
        public void initFrame(){
           this.setTitle("Recipes");
           this.setDefaultCloseOperation(3);
           this.setVisible(true);
           this.setSize(this.width / 2, this.height / 2);      
           int frameWidth = this.getSize().width;
           int frameHeight = this.getSize().height;
           this.add(frameDailymenus);
           this.add(frameRecipes);
           this.add(frameIngredients);    
           this.setLocation((this.width - frameWidth)/2, (this.height - frameHeight)/2);
           
       }
        public void initMenu(){
            this.setJMenuBar(menuBar);
            menuBar.add(menuDailymenu);
            menuBar.add(menuRecipes);
            menuBar.add(menuIngredients);
            menuDailymenu.add(submenuDailymenusAdd);
            menuDailymenu.add(submenuDailymenusEdit);
            menuDailymenu.add(submenuDailymenusDelete);
            menuRecipes.add(submenuRecipesAdd);
            menuRecipes.add(submenuRecipesEdit);
            menuRecipes.add(submenuRecipesDelete);
            menuIngredients.add(submenuIngredientsAdd);
            menuIngredients.add(submenuIngredientsEdit);
            menuIngredients.add(submenuIngredientsDelete);
       }
        
        public void initButtons(){
            GridLayout layout = new GridLayout(1,3);
            panel.setLayout(layout); 
            panel.add(buttonDailymenus);
            panel.add(buttonRecipes);
            panel.add(buttonIngredients);
            panel.setVisible(true);
            this.add(panel);
        }
         
        public void showDailymenus() throws SQLException{
            int frameWidth = this.getSize().width;
            int frameHeight = this.getSize().height;
            frameDailymenus.setSize(frameWidth/2,frameHeight/2);
            frameDailymenus.setLocation(( frameWidth)/4, ( frameHeight)/4);
            frameDailymenus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameDailymenus.setVisible(true);
            frameDailymenus.setLayout(new BorderLayout());
            String[] columnNamesDailymenus = databaseFunctions.getColumnNamesFromDailymenus();
            Object[][] dailymenusData = databaseFunctions.getDailymenusData();
            JTable tableDailymenus = new JTable(dailymenusData,columnNamesDailymenus);
            JScrollPane scrollPane = new JScrollPane(tableDailymenus);
            tableDailymenus.setFillsViewportHeight(true);
            frameDailymenus.add(tableDailymenus.getTableHeader(),BorderLayout.PAGE_START);
            frameDailymenus.add(tableDailymenus,BorderLayout.CENTER);
            panel.setVisible(false);

        }
        
         public void showRecipes() throws SQLException{
            int frameWidth = this.getSize().width;
            int frameHeight = this.getSize().height;
            frameRecipes.setSize(frameWidth/2,frameHeight/2);
            frameRecipes.setLocation(( frameWidth)/4, ( frameHeight)/4);
            frameRecipes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameRecipes.setVisible(true);
            frameRecipes.setLayout(new BorderLayout());
            String[] columnNamesRecipes = databaseFunctions.getColumnNamesFromRecipes();
            Object[][] recipesData = databaseFunctions.getRecipesData();
            JTable tableRecipes = new JTable(recipesData,columnNamesRecipes);
            JScrollPane scrollPane = new JScrollPane(tableRecipes);
            tableRecipes.setFillsViewportHeight(true);
            frameRecipes.add(tableRecipes.getTableHeader(),BorderLayout.PAGE_START);
            frameRecipes.add(tableRecipes,BorderLayout.CENTER);
            panel.setVisible(false);
        }
         
          public void showIngredients() throws SQLException{
            int frameWidth = this.getSize().width;
            int frameHeight = this.getSize().height;
            frameIngredients.setSize(frameWidth/2,frameHeight/2);
            frameIngredients.setLocation(( frameWidth)/4, ( frameHeight)/4);
            frameIngredients.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frameIngredients.setVisible(true);
            frameIngredients.setLayout(new BorderLayout());
            String[] columnNamesIngredients = databaseFunctions.getColumnNamesFromIngredients();
            Object[][] ingredientsData = databaseFunctions.getIngredientsData();
            JTable tableIngredients = new JTable(ingredientsData,columnNamesIngredients);
            JScrollPane scrollPane = new JScrollPane(tableIngredients);
            tableIngredients.setFillsViewportHeight(true);
            frameIngredients.add(tableIngredients.getTableHeader(),BorderLayout.PAGE_START);
            frameIngredients.add(tableIngredients,BorderLayout.CENTER);
            panel.setVisible(false);
            
        }
          
          
    
}
