
package databaseproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JList;

public class DatabaseFunctions {
    private final String url = "jdbc:postgresql://sxterm.mat.umk.pl/mgorski?user=mgorski&password=***&ssl=false";
    
    
     public String[] getColumnNamesFromDailymenus() throws SQLException{
               String[] columnNamesDailymenus = new String[3];
           try {         
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM dailymenus");
             columnNamesDailymenus[0] = rset.getMetaData().getColumnName(1);
             columnNamesDailymenus[1] = rset.getMetaData().getColumnName(2);
             columnNamesDailymenus[2] = rset.getMetaData().getColumnName(3);
            rset.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return columnNamesDailymenus;
       }
     
      public String[] getColumnNamesFromRecipes() throws SQLException{
               String[] columnNamesRecipes = new String[3];
           try {         
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM recipes ");
             columnNamesRecipes[0] = rset.getMetaData().getColumnName(1);
             columnNamesRecipes[1] = rset.getMetaData().getColumnName(2);
             columnNamesRecipes[2] = rset.getMetaData().getColumnName(3);

            
            rset.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return columnNamesRecipes;
       }
      
       public String[] getColumnNamesFromIngredients() throws SQLException{
               String[] columnNamesIngredients = new String[4];
           try {         
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM ingredients");
             columnNamesIngredients[0] = rset.getMetaData().getColumnName(1);
             columnNamesIngredients[1] = rset.getMetaData().getColumnName(2);
             columnNamesIngredients[2] = rset.getMetaData().getColumnName(3);
             columnNamesIngredients[3] = rset.getMetaData().getColumnName(4);

            rset.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return columnNamesIngredients;
       }
       
       public Object[][] getDailymenusData() throws SQLException{
           Object[][] dailymenusData = new Object[this.getDailymenusSize()][3];
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM dailymenus ORDER BY id_d");
            int i = 0;

            while (rset.next())
            {
                   dailymenusData[i][0] = rset.getInt(1);
                   dailymenusData[i][1] = rset.getString(2);
                   dailymenusData[i][2] = rset.getInt(3);
                   i++;   
            }
            
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return dailymenusData;
       }
       
       public Object[][] getRecipesData() throws SQLException{
           Object[][] recipesData = new Object[this.getRecipesSize()][3];
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM recipes ORDER BY id_r");
            int i = 0;

            while (rset.next())
            {
                   recipesData[i][0] = rset.getInt(1);
                   recipesData[i][1] = rset.getString(2);
                   recipesData[i][2] = rset.getInt(3);
                   i++;   
            }
            
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return recipesData;
       }
        
         public Object[][] getIngredientsData() throws SQLException{
           Object[][] ingredientsData = new Object[this.getIngredientsSize()][4];
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM ingredients ORDER BY id_i");
            int i = 0;

            while (rset.next())
            {
                   ingredientsData[i][0] = rset.getInt(1);
                   ingredientsData[i][1] = rset.getString(2);
                   ingredientsData[i][2] = rset.getInt(3);
                   ingredientsData[i][3] = rset.getInt(4);
                   i++;   
            }
            
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return ingredientsData;
       }
         
          public int getDailymenusSize() throws SQLException{
            int size = 0;
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rset = stmt.executeQuery("SELECT * FROM dailymenus");
                if (rset != null) 
                {
                  rset.last();    
                  size = rset.getRow(); 
                }
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return size;
       }
        
        public int getRecipesSize() throws SQLException{
            int size = 0;
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rset = stmt.executeQuery("SELECT * FROM recipes");
                if (rset != null) 
                {
                  rset.last();    
                  size = rset.getRow(); 
                }
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return size;
       }
        
         public int getIngredientsSize() throws SQLException{
            int size = 0;
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rset = stmt.executeQuery("SELECT * FROM ingredients");
                if (rset != null) 
                {
                  rset.last();   
                  size = rset.getRow(); 
                }
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return size;
       }
         
       public int getIdOfIngredientByName(String nazwa){
         int id = 0;
         try {         
            String SQL = "SELECT id_i FROM ingredients WHERE name = ?";
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,nazwa);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
         return id;
     }
       
             public int getIdOfRecipeByName(String nazwa){
         int id = 0;
         try {         
            String SQL = "SELECT id_r FROM recipes WHERE name = ?";
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,nazwa);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
         return id;
     }
             
                 public int getIdOfDailymenuByName(String nazwa){
         int id = 0;
         try {         
            String SQL = "SELECT id_d FROM dailymenus WHERE name = ?";
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1,nazwa);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
         return id;
     }
       

        public String[] getIngredientList() throws SQLException{
           String[] ingredientList = new String[getIngredientsSize()];
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT name FROM ingredients");
            int i = 0;

            while (rset.next())
            {
                   ingredientList[i] = rset.getString(1);
                   i++;   
            }
            
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return ingredientList;
       }
     
        
        public String[] getRecipesList() throws SQLException{
           String[] getRecipesList = new String[this.getRecipesSize()];
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT  name FROM recipes");
            int i = 0;

            while (rset.next())
            {
                   getRecipesList[i] = rset.getString(1);
                   i++;   
                
            }
            
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return getRecipesList;
       }
        
        public String[] getDailymenusList() throws SQLException{
           String[] getDailymenusList = new String[this.getDailymenusSize()];
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT  name FROM dailymenus");
            int i = 0;

            while (rset.next())
            {
                   getDailymenusList[i] = rset.getString(1);
                   i++;         
            }
            
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return getDailymenusList;
       }
        
                
        public String[] getColumnNamesFromIngredientsWithoutID() throws SQLException{
               String[] columnNamesIngredients = new String[3];
           try {         
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM ingredients");
             columnNamesIngredients[0] = rset.getMetaData().getColumnName(2);
             columnNamesIngredients[1] = rset.getMetaData().getColumnName(3);
             columnNamesIngredients[2] = rset.getMetaData().getColumnName(4);


            rset.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return columnNamesIngredients;
       }
        
        
          public String[] getNameColumnFromRecipes() throws SQLException{
               String[] columnNamesRecipes = new String[1];
           try {         
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM recipes");
             columnNamesRecipes[0] = rset.getMetaData().getColumnName(2);

            rset.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return columnNamesRecipes;
       }
        
          
        
        public int getIngredientsToRecipesSize() throws SQLException{
            int size = 0;
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rset = stmt.executeQuery("SELECT *  FROM ingredientsToRecipes");
               if (rset != null) 
                {
                  rset.last();   
                  size = rset.getRow(); 
                }
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return size;
       }
        
         
        
        public void  insertIntoIngredients(int id_i, String name, int calories, int quantity) throws SQLException {
          try {
            String SQL = "INSERT INTO ingredients(id_i, name, calories, quantity) "
                + "VALUES(?,?,?,?)";
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1,id_i);
            pstmt.setString(2, name);
            pstmt.setInt(3, calories);
            pstmt.setInt(4, quantity);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
        }
        
        
         public void  editIngredients(Object selectedItem, String textFieldText, String name) throws SQLException {
          try {
              String SQL;
              if(selectedItem.equals("name")){
                    SQL = "UPDATE ingredients "
                + "SET name = ? "
                + "WHERE name = ?";
              }
              else if(selectedItem.equals("calories"))
              {
                  SQL = "UPDATE ingredients "
                + "SET calories = ? "
                + "WHERE name = ?";
                  
              }
              else {
                  SQL = "UPDATE ingredients "
                + "SET quantity = ? "
                + "WHERE name = ?";
                  
              }
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            if(selectedItem.equals("quantity") || selectedItem.equals("calories") ){
                int number = Integer.parseInt(textFieldText);
                   pstmt.setInt(1, number );
            }
            else{
                pstmt.setString(1, textFieldText);
            }
            pstmt.setString(2, name);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
        }
         
           public void  deleteFromIngredients(String selectedItem) throws SQLException {
          try {
            if(this.getRecipesSize() > 0 ) deleteIngredientFromIngredientToRecipes(selectedItem);
            String SQL = "DELETE FROM ingredients WHERE name = ? ";
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, selectedItem);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
        }
           
          public void  deleteIngredientFromIngredientToRecipes(String selectedItem) throws SQLException {
          try {
            String SQL = "DELETE FROM ingredientsToRecipes WHERE id_i = ? ";

            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, this.getIdOfIngredientByName(selectedItem));
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
          
        }
          
         public String[] getListSelectedElements(JList lista){
        int[] selectedIx =  lista.getSelectedIndices();
        String[] selectedElements = new String[selectedIx.length];
        for (int i = 0; i < selectedIx.length; i++) {
             selectedElements[i] = lista.getModel().getElementAt(selectedIx[i]).toString();
            }
        return selectedElements;
     }

          
       public int getSumOfCaloriesForRecipe(String[] selectedElementsList){
         int sumOfCalories = 0;
         try {         
             for ( int i = 0; i< selectedElementsList.length; i ++){

            String SQL = "SELECT calories FROM ingredients WHERE name = ?";
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            String nazwa = selectedElementsList[i];
            pstmt.setString(1,nazwa);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                sumOfCalories += rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            conn.close();
             }
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
         return sumOfCalories;
     }
       
        public int getSumOfCaloriesForDailymenu(String[] selectedElementsList){
         int sumOfCalories = 0;
         try {         
             for ( int i = 0; i< selectedElementsList.length; i ++){

            String SQL = "SELECT calories FROM recipes WHERE name = ?";
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            String nazwa = selectedElementsList[i];
            pstmt.setString(1,nazwa);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                sumOfCalories += rs.getInt(1);
            }
            rs.close();
            pstmt.close();
            conn.close();
             }
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
         return sumOfCalories;
     }
         
          
       
        public void insertIntoIngredientsToRecipes(JList list,int id) throws SQLException{
         
          try {
             String[] selectedElementsList;
             selectedElementsList = this.getListSelectedElements(list);
             String SQL2 = "INSERT INTO ingredientsToRecipes(id_r, id_i) "
                + "VALUES(?,?)";
             Connection conn = DriverManager.getConnection(this.url);
            int id_r = id - 1 ;
            for(int i = 0; i < selectedElementsList.length; i ++){
                PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
                int id_i = this.getIdOfIngredientByName(selectedElementsList[i]);
                pstmt2.setInt(1,id_r);
                pstmt2.setInt(2,id_i);
                pstmt2.executeUpdate();
                pstmt2.close();    
              }
              conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
     }
        
         public void deleteFromRecipesToDailymenusByIdR(String name){
         try {
            String SQL2 = "DELETE FROM recipesToDailymenus WHERE id_r = ? ";

            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
            pstmt2.setInt(1, this.getIdOfRecipeByName(name));
            pstmt2.executeUpdate();
            pstmt2.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
     }
         
            public void deleteFromIngredientsToRecipes(String name){
         try {
            String SQL2 = "DELETE FROM ingredientsToRecipes WHERE id_r = ? ";

            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
            pstmt2.setInt(1, this.getIdOfRecipeByName(name));
            pstmt2.executeUpdate();
            pstmt2.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
     }
            
          public void insertIntoRecipes(int id_r, String text,JList list) throws SQLException{
          try {
             String[] selectedElementsList = this.getListSelectedElements(list);
             String SQL = "INSERT INTO recipes(id_r, name, calories) "
                + "VALUES(?,?,?)";
             String SQL2 = "INSERT INTO ingredientsToRecipes(id_r, id_i) "
                + "VALUES(?,?)";
             Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            String name = text;
            int calories = this.getSumOfCaloriesForRecipe(selectedElementsList);
            pstmt.setInt(1,id_r);
            pstmt.setString(2, name);
            pstmt.setInt(3, calories);
            pstmt.executeUpdate();
            for(int i = 0; i < selectedElementsList.length; i ++){
                PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
                int id_i = this.getIdOfIngredientByName(selectedElementsList[i]);
                pstmt2.setInt(1,id_r);
                pstmt2.setInt(2,id_i);
                pstmt2.executeUpdate();
                pstmt2.close();    
              }
              pstmt.close();
              conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
     }
      
         public void  editRecipes(String selectedItem,JList list,String text, int id) throws SQLException {
          try {
              String name = selectedItem;
              this.deleteFromIngredientsToRecipes(selectedItem);
              String[] selectedElementsList = this.getListSelectedElements(list);
             if(this.getDailymenusSize() > 0) this.deleteFromRecipesToDailymenusByIdR(selectedItem);
              String SQL = "UPDATE recipes "
                + "SET name = ? "
                + "WHERE name = ?";
              
              String SQL2 = "UPDATE recipes "
                + "SET calories = ? "
                + "WHERE name = ?";
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
            int calories = this.getSumOfCaloriesForRecipe(selectedElementsList);
            pstmt.setString(1, text);
            pstmt.setString(2, name);
            pstmt2.setInt(1, calories);
            pstmt2.setString(2, selectedItem);
            pstmt2.executeUpdate(); 
            pstmt.executeUpdate(); 
            this.insertIntoIngredientsToRecipes(list,id + 1);
            pstmt.close();
            pstmt2.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
        }
            
       public void deleteFromRecipes(String selectedItem){
         try {
            String SQL = "DELETE FROM recipes WHERE name = ? ";
            String SQL2 = "DELETE FROM ingredientsToRecipes WHERE id_r = ? ";
            String SQL3 = "DELETE FROM recipesToDailymenus WHERE id_r = ?";
            this.deleteFromRecipesToDailymenusByIdR(selectedItem);
            int id_new = this.getIdOfRecipeByName(selectedItem);
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
            PreparedStatement pstmt3 = conn.prepareStatement(SQL3);
            pstmt.setString(1,selectedItem);
            pstmt2.setInt(1, this.getIdOfRecipeByName(selectedItem));
            pstmt3.setInt(1, this.getIdOfRecipeByName(selectedItem));
            pstmt3.executeUpdate();
            pstmt2.executeUpdate();
            pstmt.executeUpdate();   
            pstmt.close();
            pstmt2.close();
            pstmt3.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
     }
       
        public String[] getNameColumnFromDailymenus() throws SQLException{
               String[] columnNamesRecipes = new String[1];
           try {         
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM dailymenus");
             columnNamesRecipes[0] = rset.getMetaData().getColumnName(2);

            rset.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return columnNamesRecipes;
       }
     
        
         public void insertIntoRecipesToDailymenus(JList list,String selectedItem) throws SQLException{
         
          try {
             String[] selectedElementsList = this.getListSelectedElements(list);
             String SQL2 = "INSERT INTO recipesToDailymenus(id_d, id_r) "
                + "VALUES(?,?)";
             Connection conn = DriverManager.getConnection(this.url);
            for(int i = 0; i < selectedElementsList.length; i ++){
                PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
                int id_d = this.getIdOfDailymenuByName(selectedItem);
                int id_r = this.getIdOfRecipeByName(selectedElementsList[i]);
                pstmt2.setInt(1,id_d);
                pstmt2.setInt(2,id_r);
                pstmt2.executeUpdate();
                pstmt2.close();    
              }
              conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
     }
         
          public void deleteFromRecipesToDailymenusByIdD(String name){
         try {
            String SQL2 = "DELETE FROM recipesToDailymenus WHERE id_d = ? ";

            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
            pstmt2.setInt(1, this.getIdOfDailymenuByName(name));
            pstmt2.executeUpdate();
            pstmt2.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
     }
                   
          public void insertIntoDailymenus(JList list,int id_d, String text) throws SQLException{
         
          try {
             String[] selectedElementsList = this.getListSelectedElements(list);
             String SQL = "INSERT INTO dailymenus(id_d, name, calories) "
                + "VALUES(?,?,?)";
             String SQL2 = "INSERT INTO recipesToDailymenus(id_d, id_r) "
                + "VALUES(?,?)";
             Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            String name = text;
            int calories = this.getSumOfCaloriesForDailymenu(selectedElementsList);
            pstmt.setInt(1,id_d);
            pstmt.setString(2, name);
            pstmt.setInt(3, calories);
            pstmt.executeUpdate();
            for(int i = 0; i < selectedElementsList.length; i ++){
                PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
                int id_r = this.getIdOfRecipeByName(selectedElementsList[i]);
                pstmt2.setInt(1,id_d);
                pstmt2.setInt(2,id_r);
                pstmt2.executeUpdate();
                pstmt2.close();    
              }
              pstmt.close();
              conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
     }
          
           public void  editDailymenus(String selectedItem, JList list, String text) throws SQLException {
          try {
              this.deleteFromRecipesToDailymenusByIdD(selectedItem);
              String[] selectedElementsList = this.getListSelectedElements(list);
              String SQL = "UPDATE dailymenus "
                + "SET name = ? "
                + "WHERE name = ?";
              
              String SQL2 = "UPDATE dailymenus "
                + "SET calories = ? "
                + "WHERE name = ?";
            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
            int calories = this.getSumOfCaloriesForDailymenu(selectedElementsList);
            pstmt.setString(1, text);
            pstmt.setString(2, selectedItem);
            pstmt2.setInt(1, calories);
            pstmt2.setString(2, selectedItem);
            pstmt2.executeUpdate(); 
            this.insertIntoRecipesToDailymenus(list, selectedItem);
            pstmt.executeUpdate(); 
            
            pstmt.close();
            pstmt2.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
        }
    

             public void deleteFromDailymenus(String selectedItem){
         try {
            String SQL = "DELETE FROM dailymenus WHERE name = ? ";
            String SQL2 = "DELETE FROM recipesToDailymenus WHERE id_d = ? ";

            Connection conn = DriverManager.getConnection(this.url);
            PreparedStatement pstmt = conn.prepareStatement(SQL);
            PreparedStatement pstmt2 = conn.prepareStatement(SQL2);
            pstmt.setString(1,selectedItem);
            pstmt2.setInt(1, this.getIdOfDailymenuByName(selectedItem));
            pstmt2.executeUpdate();
            pstmt.executeUpdate();
            pstmt.close();
            pstmt2.close();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
     }
             
             public String getNameOfIngredientById(int id_i){
                 String name = new String();
                        try {         
                   String SQL = "SELECT name FROM ingredients WHERE id_i = ?";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   pstmt.setInt(1,id_i);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       name = rs.getString(1);
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                 return name;
             }
             
                public int getCaloriesOfIngredientById(int id_i){
                 int calories = 0;
                        try {         
                   String SQL = "SELECT calories FROM ingredients WHERE id_i = ?";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   pstmt.setInt(1,id_i);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       calories = rs.getInt(1);
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                 return calories;
             }
       
                
                   public int getQuantityOfIngredientById(int id_i){
                 int quantity = 0;
                        try {         
                   String SQL = "SELECT quantity FROM ingredients WHERE id_i = ?";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   pstmt.setInt(1,id_i);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       quantity = rs.getInt(1);
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                 return quantity;
             }
                   
             public String getNameOfRecipeById(int id_r){
                 String name = new String();
                        try {         
                   String SQL = "SELECT name FROM recipes WHERE id_r = ?";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   pstmt.setInt(1,id_r);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       name = rs.getString(1);
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                 return name;
             }
             
               public int getCaloriesOfRecipeById(int id_r){
                 int calories = 0;
                        try {         
                   String SQL = "SELECT calories FROM recipes WHERE id_r = ?";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   pstmt.setInt(1,id_r);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       calories = rs.getInt(1);
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                 return calories;
             }
       
               
               public String getNameOfDailymenuById(int id_d){
                 String name = new String();
                        try {         
                   String SQL = "SELECT name FROM dailymenus WHERE id_d = ?";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   pstmt.setInt(1,id_d);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       name = rs.getString(1);
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                 return name;
             }
             
               public int getCaloriesOfDailymenuById(int id_d){
                 int calories = 0;
                        try {         
                   String SQL = "SELECT calories FROM dailymenus WHERE id_d = ?";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   pstmt.setInt(1,id_d);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       calories = rs.getInt(1);
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                 return calories;
             }
               
               public ArrayList<Integer> getIngredientsIdsByRecipesId(int id_r){
                   ArrayList<Integer> list = new ArrayList<Integer>();
                   try {         
                   String SQL = "SELECT id_i FROM ingredientsToRecipes WHERE id_r = ?";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   pstmt.setInt(1,id_r);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       list.add(rs.getInt(1));
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                   return list;
               }
               
                public ArrayList<Integer> getRecipesIdsIdsByDailymenusId(int id_r){
                   ArrayList<Integer> list = new ArrayList<Integer>();
                   try {         
                   String SQL = "SELECT id_r FROM recipesToDailymenus WHERE id_d = ?";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   pstmt.setInt(1,id_r);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       list.add(rs.getInt(1));
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                   return list;
               }
               
               public int getFirstIngredientId(){
                 int id = 0;
                        try {         
                   String SQL = "SELECT id_i FROM ingredients ORDER BY id_i";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       id = rs.getInt(1);
                       break;
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                 return id;
             }
       
                public int getFirstRecipeId(){
                 int id = 0;
                        try {         
                   String SQL = "SELECT id_r FROM recipes ORDER BY id_r";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       id = rs.getInt(1);
                       break;
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                 return id;
             }
                
                 public int getFirstDailymenuId(){
                 int id = 0;
                        try {         
                   String SQL = "SELECT id_d FROM dailymenus ORDER BY id_d";
                   Connection conn = DriverManager.getConnection(this.url);
                   PreparedStatement pstmt = conn.prepareStatement(SQL);
                   ResultSet rs = pstmt.executeQuery();
                   while(rs.next()){
                       id = rs.getInt(1);
                       break;
                   }
                   rs.close();
                   pstmt.close();
                   conn.close();
               } catch (SQLException e) {
                   System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
               }
                 return id;
             }
                 
            public int getDailymenusLastIndex() throws SQLException{
            int id = 0;
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rset = stmt.executeQuery("SELECT id_d FROM dailymenus ORDER BY id_d");
                if (rset != null) 
                {
                  rset.last();    
                  id = rset.getInt(1); 
                }
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return id;
       }
        
        public int getRecipesLastIndex() throws SQLException{
            int id = 0;
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rset = stmt.executeQuery("SELECT id_r FROM recipes ORDER BY id_r");
                if (rset != null) 
                {
                  rset.last();    
                  id = rset.getInt(1); 
                }
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return id;
       }
        
         public int getIngredientsLastIndex() throws SQLException{
            int id = 0;
           try {         
             
            Connection conn = DriverManager.getConnection(this.url);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rset = stmt.executeQuery("SELECT id_i FROM ingredients ORDER BY id_i");
                if (rset != null) 
                {
                  rset.last();   
                  id = rset.getInt(1);  
                }
            rset.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            System.err.println("Blad Postgres: " + e.getErrorCode() + " " + e.getMessage());
        }
           return id;
       }
       
}
