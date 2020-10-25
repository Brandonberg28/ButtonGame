import java.util.*;
import java.text.*;
import java.io.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;

public class ButtonGame extends Application
{

   BorderPane root = new BorderPane();
   Button[][] buttonArray = new Button[4][4];
   Label l;
   int didUserWin;  
   
   public void start(Stage stage)
   {             
      HBox top = new HBox();
      top.setPrefSize(500,50);
      
      l = new Label("Welcome to the Game!");
      l.setBackground(new Background(new BackgroundFill(Color.CYAN,CornerRadii.EMPTY,Insets.EMPTY)));
      l.setPrefSize(500,10);
      top.getChildren().add(l);
      
      root.setTop(top);
      VBox emptySpace = new VBox();      
      emptySpace.setPrefSize(50,500);
      root.setLeft(emptySpace);
      
      //Create buttonArray and put inside CENTER of BorderPane
      GridPane gridPane = new GridPane();
      
      for(int i=0; i<4; i++)
      {
         for(int j=0; j<4; j++)
         {
            buttonArray[i][j] = new Button(i+""+j); 
            buttonArray[i][j].setPrefSize(100,100); 
            buttonArray[i][j].setOnAction(new ButtonListener());
            gridPane.add(buttonArray[i][j],j,i);    
         }        
      }
      root.setCenter(gridPane); 
     
      Scene scene = new Scene(root,500,500);
      stage.setScene(scene);
      stage.setTitle("Button Game");
      stage.show();
   }
   
      
   public static void main(String[]args)
   {
      launch(args);
   }
      
      
   public class ButtonListener implements EventHandler<ActionEvent>
   {
      public void handle(ActionEvent e)
      {             
         for(int i=0;i<4;i++) 
         {  
            for(int j=0;j<4;j++) //loops through each button every time
            {
               if(e.getSource() == buttonArray[i][j]) //this determines the location of the clicked button
               {
                  if(buttonArray[i][j].isVisible()) //checks if clicked button is visible 
                     {
                        buttonArray[i][j].setVisible(false); //if visible, then turn off
                     }
                     else
                     {
                        buttonArray[i][j].setVisible(true);
                     }  

                  if(i>0) //bounds check for indexes
                  {
                     if(buttonArray[i-1][j].isVisible()) //makes invisible if visible /vice versa 
                     {
                        buttonArray[i-1][j].setVisible(false);
                     }
                     else
                     {
                        buttonArray[i-1][j].setVisible(true);
                     }
                  }  
                  if(j>0) //bounds check for indexes
                  {   
                     if(buttonArray[i][j-1].isVisible())
                     {
                        buttonArray[i][j-1].setVisible(false);
                     }
                     else
                     {
                        buttonArray[i][j-1].setVisible(true);
                     }
                  }   
                  
                  if(j<3) //bounds check for indexes
                  {   
                     if(buttonArray[i][j+1].isVisible())
                     {
                        buttonArray[i][j+1].setVisible(false);
                     }
                     else
                     {
                        buttonArray[i][j+1].setVisible(true);
                     }  
                  }
                  
                  if(i<3) //bounds check for indexes
                  {   
                     if(buttonArray[i+1][j].isVisible())
                     {
                        buttonArray[i+1][j].setVisible(false);
                     }
                     else
                     {
                        buttonArray[i+1][j].setVisible(true);
                     }
                  }
               }
            }
            winChecker();
         }                 
      }
   }
   
   public void winChecker() //check if user won
   {
      didUserWin = 0; 
      for(int i=0; i<4; i++) //Print "You Win!" if all buttonArray are not visible
      {
         for(int j=0; j<4; j++)
         {
            Button tempButton = buttonArray[i][j];
            if(tempButton.isVisible() == false)
            {
               didUserWin++;
            }
            if(didUserWin == 16)
            {
            l.setText("You Win!");
            }
         }
      }
   }
      
}
