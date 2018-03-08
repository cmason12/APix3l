/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anapixel;

import static anapixel.APix3l.autoCrop;
import static anapixel.NewStage.HEIGHT;
import static anapixel.NewStage.WIDTH;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author cmason12
 */
public class SaveImageOpts {
    protected static TitledPane saveOpts = new TitledPane();
    protected static TextField txtWidth = new TextField(((int)(.5*WIDTH)/(APix3l.rowSize+1))+"");
    protected static TextField txtHeight= new TextField(((int)(.8*HEIGHT)/(APix3l.colSize))+"");
    SaveImageOpts(){
        VBox allItems = new VBox();
        saveOpts.setText("Save Image Options");
        
        HBox pixWidthBox = new HBox();
        Label lblPixel = new Label("Pixel\n-----------------------");
        Label lblWidth = new Label("W: ");
        
        pixWidthBox.getChildren().addAll(lblWidth, txtWidth);
        //(.8*HEIGHT)/(maxRow)
        HBox pixHeightBox = new HBox();
        Label lblHeight = new Label("H:  ");
        pixHeightBox.getChildren().addAll(lblHeight, txtHeight);
        
        CheckBox autoCrop = new CheckBox("Auto Crop");
        autoCrop.setSelected(true);
        Button btnPrieview = new Button("Preview");
        allItems.getChildren().addAll(lblPixel, pixWidthBox, pixHeightBox,
                new Label(" "), autoCrop, btnPrieview);
        saveOpts.setContent(allItems);
        
     autoCrop.setOnMousePressed((MouseEvent event) -> {
         autoCrop.setSelected(APix3l.autoCrop);
         APix3l.autoCrop = !APix3l.autoCrop ;
         if(APix3l.autoCrop)APix3l.autoCropItem.setText("AutoCrop: ON");
         else APix3l.autoCropItem.setText("AutoCrop: OFF");
        });
     btnPrieview.setOnMousePressed((MouseEvent event) -> {
         try {
         Stage prevStage = new Stage();
         prevStage.setTitle("Preview");
         Pane test = new Pane();
         ImageView prevBox = 
         APix3l.stg.priviewImage(Integer.parseInt(txtWidth.getText()),
                 Integer.parseInt(txtHeight.getText()));//VBox prevImage = 
         test.getChildren().add(prevBox);
         Scene prevScene = new Scene(test, prevBox.getImage().getWidth(),
                 prevBox.getImage().getHeight());
         prevStage.setScene(prevScene);
         prevStage.show();
         } catch (Exception e){}
        });
    }
    
    public static TitledPane retTP(){
        return saveOpts;
    }
}
