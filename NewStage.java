/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anapixel;

import static anapixel.SaveImageOpts.txtHeight;
import static anapixel.SaveImageOpts.txtWidth;
import java.io.File;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author cmason12
 */
public class NewStage {
    
    private  VBox gridBox = new VBox();
    private static int maxRow = 0;
    private static int maxCol = 0;
    public static int WIDTH = 0;
    public static int HEIGHT = 0;
    
    NewStage(int col, int row, int maxWidth, int maxHeight){
        //maxWidth *= .95;
        WIDTH = maxWidth;
        HEIGHT = maxHeight;
        maxCol = col+1;
        maxRow = row+1;
        gridBox.setPrefHeight(col);
      System.out.println("Col: " + col);
      System.out.println("Row: " + row);
      System.out.println(maxWidth + " " + maxHeight);
        HBox gfRow = new HBox();
        for(int i = 0; i <= col; i++){
            
            Rectangle num;
            Pixel r = new Pixel(Color.TRANSPARENT);
            r.setFill(Color.TRANSPARENT);
            num = r.retRect();
            num.setWidth((.5*maxWidth)/(col+1));
            num.setHeight((.8*maxHeight)/(row+1));
            gfRow.getChildren().add(num);
            
            
        }
        gridBox.getChildren().add(gfRow);
        for(int i = 0; i < row; i++){
             gfRow = new HBox();
            Rectangle num;
            Pixel r = new Pixel( Color.TRANSPARENT);
            r.setFill(Color.TRANSPARENT);
            num = r.retRect();
            num.setWidth((.5*maxWidth)/(col+1));
            num.setHeight((.8*maxHeight)/(row+1));
            gfRow.getChildren().add(num);
            
            for(int j = 0; j < col; j++){
             r = new Pixel( Color.TRANSPARENT);
            num = r.retRect();
            num.setWidth((.5*maxWidth)/(col+1));
            num.setHeight((.8*maxHeight)/(row+1));

            gfRow.getChildren().add(num);
            
        }
            gridBox.getChildren().add(gfRow);
            
        }
     
        gridBox.setTranslateY(-15);
        gridBox.setTranslateX(-20);
       
        //gridFrame.setTranslateX(20);*/
      
    }

    
    public VBox retBox(){
        return gridBox;
    }
    
    public void save(){
        String path;// = saveName();\
        File saveFile;
        boolean proceed = true;
        try{
          //  setImage(path);
        PrintStream p;
        if(APix3l.editedStage){
            if (APix3l.fileName.equals("ledgaurd")){
            APix3l.fileName = saveName();

        }
            
        } else {
            proceed = false;
            APix3l.fileName = "";
        }
        saveFile = new File(APix3l.fileName+ ".txt");
        //p= new PrintStream(APix3l.fileName + ".txt");
        if ((!APix3l.fileName.equals("")) && saveFile.exists()){
            ButtonType foo = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType bar = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            Alert alert = new Alert(AlertType.WARNING,
                "This file already exists, Overwrite it?",
                foo,
                bar);

        alert.setTitle("File Already Exists");
        Optional<ButtonType> result = alert.showAndWait();
        if (!(result.isPresent() && result.get() == foo)) {
         proceed = false;
        }
        } 
        if(proceed){
        System.out.println("Saving.....");
        p= new PrintStream(saveFile);    
        p.println(APix3l.fileName);
        p.println(maxCol + " " + maxRow);
        for(int i = 0; i <= maxRow; i++){
            HBox temp = new HBox();
            temp = (HBox)gridBox.getChildren().get(i);//).getChildren().get(0);
            p.print(i + "\t");
            for(int j = 0; j < maxCol; j++){
                Color tempColor = (Color)((Rectangle)temp.getChildren().get(j)).getFill();
                
                if (tempColor !=  Color.TRANSPARENT){
                    p.print(j + " " + tempColor + "\t");
                }
            // Color tempColor = (((Rectangle)(temp.getChildren().get(j))).getFill());
            
        }
            p.print("-3");
            p.println();
     
            
        
        p.close();
        }
        }
        }catch (Exception e){};
        setImage(APix3l.fileName);
    }
    
    public static void msgBox(String dialogue) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("");
        alert.setContentText(dialogue); 
        
        alert.showAndWait();
    }
    
    
    public static String inpBox(String dialogue) {
        String input;
        
        input = "";
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("");
        dialog.setContentText(dialogue);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
        input = result.get();
        
        }
        return input;
}
    
     public static String saveName() {
        String name;
        int numLessThan;
        
        name = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
                while (name.length() > 20) {
                    name = inpBox("File Name: ");
                    try {
                        if (name.length() > 20) {
                            msgBox("Name too long, Maximum size 8 "
                                    + "characters");
                        }
                    } catch (Exception e) {
                        name = "        ";
                    }
                }
                
                return name;
    }
     
     private static File getOpenPath() {
        File path;

        final JFileChooser fc = new JFileChooser(System.getProperty("user.dir"));
        int returnVal;
        
        returnVal = fc.showOpenDialog(null);

        path = null;
        if (returnVal == 0) {
            path = fc.getSelectedFile();
        }

        
        return path;
    }
     
     public  VBox open(){
         File path = getOpenPath();
         try {
         Scanner inFile = new Scanner(path);
         
         APix3l.fileName = inFile.next();
         maxCol = Integer.parseInt(inFile.next());
         maxRow = Integer.parseInt(inFile.next());
         
         gridBox = new VBox();
         HBox gfRow = new HBox();
         for(int i = 0; i <= maxCol; i++){
            
            Rectangle num;
            Pixel r = new Pixel(Color.TRANSPARENT);
            r.setFill(Color.TRANSPARENT);
            num = r.retRect();
            num.setWidth((.5*WIDTH)/(maxRow));
            num.setHeight((.8*HEIGHT)/(maxRow));
            gfRow.getChildren().add(num);
            
            
        }
         gridBox.getChildren().add(gfRow);
         for (int i = 0; i < maxCol; i++){
             int colNum = -1;
             Color inColor = Color.TRANSPARENT;
             
             gfRow = new HBox();
              Rectangle num;
            Pixel r = new Pixel( Color.TRANSPARENT);
           // r.setFill(Color.TURQUOISE);
           r.setFill(Color.TRANSPARENT);
            num = r.retRect();
            num.setStroke(Color.BLACK);
            num.setWidth((.5*WIDTH)/(maxCol));
            num.setHeight((.8*HEIGHT)/(maxRow));
            gfRow.getChildren().add(num);
           
           inFile.next();
            colNum = Integer.parseInt(inFile.next());

            if (colNum != -3) inColor = Color.web(inFile.next());
            System.out.println("Color: " + inColor);
            for(int j = 0; j < maxCol; j++){
                System.out.println("Here");
            if (colNum != j){
                //r = new Pixel( Color.ALICEBLUE);
                r = new Pixel(Color.TRANSPARENT);
            num = r.retRect();
            num.setStroke(Color.BLACK);
            num.setWidth((.5*WIDTH)/(maxCol));
            num.setHeight((.8*HEIGHT)/(maxRow));
            } else {
                //r = new Pixel( inColor);
                r = new Pixel(inColor);
            num = r.retRect();
            num.setStroke(Color.BLACK);
            
            num.setWidth((.5*WIDTH)/(maxCol));
            num.setHeight((.8*HEIGHT)/(maxRow));
            colNum = Integer.parseInt(inFile.next());
           System.out.println("Col: " + colNum);
            if (colNum != -3) inColor = Color.web((inFile.next()));
            System.out.println("Color: " + inColor);
            }

            gfRow.getChildren().add(num);
            
        }
            gridBox.getChildren().add(gfRow);
            
         }
         }catch (Exception e){
         System.out.println("MISTAKES WERE MADE");}
         gridBox.setTranslateY(-15);
         gridBox.setTranslateX(-20);
         
         System.out.println("Successss");
         return gridBox;
     }
     
     
     private boolean emptyColumn(int testColumn){
         boolean empty = true;
         HBox temp = new HBox();
         
         
         for (int i = 0; i < maxRow; i++){
            temp = (HBox)gridBox.getChildren().get(i);//).getChildren().get(0);
            Color tempColor = (Color)((Rectangle)temp.getChildren().get(testColumn)).getFill();
            
            if (tempColor != Color.TRANSPARENT) empty = false;
        } 
        
         return empty;
     }
     
     private boolean emptyRow(int testRow){
         boolean empty = true;
         HBox temp = new HBox();
         
         
         for (int i = 0; i < maxCol; i++){
            temp = (HBox)gridBox.getChildren().get(testRow);//).getChildren().get(0);
            Color tempColor = (Color)((Rectangle)temp.getChildren().get(i)).getFill();
            
            if (tempColor != Color.TRANSPARENT) empty = false;
        } 
        
         return empty;
     }
     private  void setImage(String path){
         VBox newBox = new VBox();
         gridBox = APix3l.gridBox;
         
        
        try{
        for(int i = 0; i <= maxRow; i++){
            HBox temp = new HBox();
            HBox gfr = new HBox();
           if (!APix3l.autoCrop ||!emptyRow(i)){
               temp = (HBox)gridBox.getChildren().get(i);//).getChildren().get(0);
           // p.print(i + "\t");
            for(int j = 0; j <= maxCol; j++){
                if (!APix3l.autoCrop || !emptyColumn(j)){
                    Color tempColor = (Color)((Rectangle)temp.getChildren().get(j)).getFill();
                
                if (tempColor ==  Color.TRANSPARENT){
                    ((Rectangle)temp.getChildren().get(j)).setFill(Color.TRANSPARENT);
                    Pixel r =  (new Pixel(Color.TRANSPARENT));
                    r.setFill(Color.TRANSPARENT);
                    
                    Rectangle num = r.retRect();
                    num.setStroke(Color.TRANSPARENT);
                    num.setWidth(Integer.parseInt(txtWidth.getText()));
                    num.setHeight(Integer.parseInt(txtHeight.getText()));
                    gfr.getChildren().add(num);
                    //temp.getChildren().get(j)).setStroke(Color.TRANSPARENT);
                    //p.print(j + " " + tempColor + "\t");
                } else {
                    DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        
                    Pixel r =  (new Pixel(tempColor));
                    r.setFill(tempColor);
                    Rectangle num = r.retRect();
                    num.setStroke(tempColor);
                    dropShadow.setColor(tempColor);
                    num.setWidth(Integer.parseInt(txtWidth.getText()));
                    num.setHeight(Integer.parseInt(txtHeight.getText()));
                    num.setEffect(dropShadow);
                    gfr.getChildren().add(num);
                }
            // Color tempColor = (((Rectangle)(temp.getChildren().get(j))).getFill());
            
                }
        }
            newBox.getChildren().add(gfr);
       
            
        }
       // p.close();
        
        }

              
    
        }catch (Exception e){};
        
        SnapshotParameters parameters = new SnapshotParameters();
       parameters.setFill(javafx.scene.paint.Color.TRANSPARENT);
       WritableImage image = newBox.snapshot(parameters, null);

    File file = new File(path+".png");

    
        try{ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);}
        catch(Exception e){};
    }
     
     public  ImageView priviewImage(int givenW, int givenH){
         VBox newBox = new VBox();
         gridBox = APix3l.gridBox;
         ImageView ret  = new ImageView(); 
        
        try{
        for(int i = 0; i <= maxRow; i++){
            HBox temp = new HBox();
            HBox gfr = new HBox();
           if (!APix3l.autoCrop ||!emptyRow(i)){
               temp = (HBox)gridBox.getChildren().get(i);//).getChildren().get(0);
           // p.print(i + "\t");
            for(int j = 0; j <= maxCol; j++){
                if (!APix3l.autoCrop || !emptyColumn(j)){
                    Color tempColor = (Color)((Rectangle)temp.getChildren().get(j)).getFill();
                
                if (tempColor ==  Color.TRANSPARENT){
                    ((Rectangle)temp.getChildren().get(j)).setFill(Color.TRANSPARENT);
                    Pixel r =  (new Pixel(Color.TRANSPARENT));
                    r.setFill(Color.TRANSPARENT);
                    
                    Rectangle num = r.retRect();
                    num.setStroke(Color.TRANSPARENT);
                    num.setWidth(givenW);
                    num.setHeight(givenH);
                    gfr.getChildren().add(num);
                    //temp.getChildren().get(j)).setStroke(Color.TRANSPARENT);
                    //p.print(j + " " + tempColor + "\t");
                } else {
                    DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        
                    Pixel r =  (new Pixel(tempColor));
                    r.setFill(tempColor);
                    Rectangle num = r.retRect();
                    num.setStroke(tempColor);
                    dropShadow.setColor(tempColor);
                    num.setWidth(givenW);
                    num.setHeight(givenH);
                    num.setEffect(dropShadow);
                    gfr.getChildren().add(num);
                }
            // Color tempColor = (((Rectangle)(temp.getChildren().get(j))).getFill());
            
                }
        }
            newBox.getChildren().add(gfr);
       
            
        }
       // p.close();
        
        }
        SnapshotParameters parameters = new SnapshotParameters();
       parameters.setFill(javafx.scene.paint.Color.TRANSPARENT);
       Image image = newBox.snapshot(parameters, null);
       ret = new ImageView(image);       
    
        }catch (Exception e){};
        
        
        return ret;
    }
}
