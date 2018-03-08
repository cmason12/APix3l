/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anapixel;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cmason12
 */
public class PickColor {
    private static ColorPicker colorPicker = new ColorPicker();
    private TitledPane colorPane = new TitledPane();
    private Label lblColor = new Label("Color:");
    private static TextField txtColor = new TextField();
    private Button btnSub = new Button("_Submit:");
    private VBox colorBox = new VBox();
    private static String[] recentColors = new String[10];
    private int index = 0;
    
    private Label lblSpace = new Label(" ");
    private Label lblRecents = new Label("Recents:");
    private Pixel[] recents = new Pixel[21];
    private HBox row1 = new HBox();
    private HBox row2 = new HBox();
    private HBox row3 = new HBox();
    private HBox reBox = new HBox();
    public static Rectangle eyeDropper = new Rectangle();
  
            
    
    
    PickColor(){
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setOffsetX(3.0);
        dropShadow.setOffsetY(3.0);
        dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
        dropShadow.setColor(Color.BLACK);
 
        eyeDropper.setWidth(20);
        eyeDropper.setHeight(20);
        eyeDropper.setTranslateX(80);
        eyeDropper.setFill(new ImagePattern(new Image("http://icons.iconarchive.com/icons/designcontest/vintage/256/Eyedropper-icon.png")));
       
        
        colorPane.setExpanded(true);
        colorPane.setText("Colors: ");
        txtColor.setMaxWidth(100);
        //colorPane.setContent();
        for (int i = 0; i < 21; i++){
            Pixel newR = new Pixel(20,20, Color.CHARTREUSE);
          newR.retRect();
      
        recents[i] = newR;
            
        }
        colorPicker.setValue(Color.CORAL);
        txtColor.setText(colorPicker.getValue() + "");
        
        colorPicker.setOnAction(new EventHandler() {
            public void handle(Event t) {
            System.out.println(colorPicker.getValue() + "");
            txtColor.setText(colorPicker.getValue() + "");
          
            recents[index].setFill(colorPicker.getValue());
            if(index != 20) index++;
            else index = 0;
            }
        });
        reBox.getChildren().addAll(lblRecents, eyeDropper);
        row1.getChildren().addAll(recents[0].retRect(),recents[1].retRect(), recents[2].retRect(),
                recents[3].retRect(), recents[4].retRect(), recents[5].retRect(), recents[6].retRect());
        row2.getChildren().addAll(recents[7].retRect(), recents[8].retRect(), recents[9].retRect(),
                recents[10].retRect(), recents[11].retRect(), recents[12].retRect(), recents[13].retRect());
        row3.getChildren().addAll(recents[14].retRect(), recents[15].retRect(), recents[16].retRect(),
                recents[17].retRect(), recents[18].retRect(), recents[19].retRect(), recents[20].retRect());
        colorBox.getChildren().addAll(colorPicker, lblColor, txtColor,
                btnSub, lblSpace, reBox, row1, row2, row3);
        
        colorPane.setContent(colorBox);
        
         btnSub.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                colorPicker.setValue(Color.web(txtColor.getText()));
                recents[index].setFill(colorPicker.getValue());
                if(index != 20) index++;
                else index = 0;
            }
        });
         
         eyeDropper.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent t) -> {
            eyeDropper.setEffect(dropShadow);
        });
         eyeDropper.addEventFilter(MouseEvent.MOUSE_EXITED, (MouseEvent t) -> {
            if (!Pixel.edActive)
                eyeDropper.setEffect(null);
            else
                eyeDropper.setEffect(dropShadow);
        });
         eyeDropper.setOnMouseClicked((MouseEvent event) -> {
            if (!Pixel.edActive){
                Pixel.edActive = true;
                eyeDropper.setEffect(dropShadow);
            } else {
                Pixel.edActive = false;
                eyeDropper.setEffect(null);
            }
        });
        
    }
    
    public TitledPane getCBox (){
        //colorPane.expandedProperty().set(false);
        return colorPane;
    }
    
    public static  void setColor(Color newCol){
        colorPicker.setValue(newCol);
        txtColor.setText(newCol.toString());
    }
    public static Color getColor(){
        return colorPicker.getValue();
    }
}
