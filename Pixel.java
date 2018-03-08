/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anapixel;

import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cmason12
 */
public class Pixel {
    private Rectangle rect = new Rectangle();
    private static boolean isClicked = false;
    public Color fillColor;
    private static boolean delete = false;
    public static boolean edActive = false; 
     
    Pixel(Color color){
     
        rect.setStroke(Color.BLACK);
        rect.setFill(color);
        fillColor = Color.ALICEBLUE;
        
        rect.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent t) -> {
            rect.setStroke(Color.LIGHTBLUE);
        });
         rect.addEventFilter(MouseEvent.MOUSE_EXITED, (MouseEvent t) -> {
            rect.setStroke(Color.BLACK);
        });
         rect.addEventFilter(MouseEvent.MOUSE_PRESSED, (MouseEvent t) -> {
             APix3l.editedStage = true;
           if (t.getButton() == MouseButton.PRIMARY){
               if (fillColor != Color.TURQUOISE ) {
                   if (!edActive)
                       rect.setFill(PickColor.getColor());
                   else {
                   if ((Color)(rect.getFill()) != Color.TRANSPARENT){
                       PickColor.setColor((Color)(rect.getFill()));
                       PickColor.eyeDropper.setEffect(null);
                       edActive = false;
                   }
               }
               } 
               delete = false;
               if (isClicked) isClicked = false;
           } else if (t.getButton() == MouseButton.SECONDARY){
               delete = true;
               if (isClicked) isClicked = false;
             
                   rect.setFill(Color.TRANSPARENT);
           } else {
               isClicked = !isClicked;
           }
           
        });
        
         
             rect.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent t) -> {
    if(isClicked) {
         if (!delete) rect.setFill(PickColor.getColor());
         else rect.setFill(Color.TRANSPARENT);}
    
        });
         
         
    }
    Pixel(int width, int height, Color color){
     
        rect.setWidth(width);
        rect.setHeight(height);
        rect.setStroke(Color.BLACK);
        rect.setFill(color);
        fillColor = color;
        
        rect.addEventFilter(MouseEvent.MOUSE_ENTERED, (MouseEvent t) -> {
            rect.setStroke(Color.LIGHTBLUE);
        });
         rect.addEventFilter(MouseEvent.MOUSE_EXITED, (MouseEvent t) -> {
            rect.setStroke(Color.BLACK);
        });
         rect.addEventFilter(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
            PickColor.setColor(fillColor);
        });
         
    }
    
    public Rectangle retRect(){
        return rect;
    }
    
    public void setFill(Color fill){
        fillColor = fill;
        rect.setFill(fill);
    }
}
