/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anapixel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author cmason12
 */
public class GridSizeDef {
    private TitledPane tp = new TitledPane();
    private Label lblCol = new Label("C:");
    private TextField txtCol = new TextField("50");
    private HBox colBox = new HBox();
    
    private Label lblX = new Label("\t\tX");
    private Label lblRow = new Label("R:");
    private TextField txtRow = new TextField("50");
    private HBox rowBox = new HBox();
    private VBox gsBox = new VBox();
    private Button btnSub = new Button("_Submit:");
    private final Label lblSpace = new Label(" ");
    GridSizeDef(){
        
        tp.setText("Grid Size");
        lblCol.setMinWidth(30);
        colBox.setSpacing(10);
        colBox.getChildren().addAll(lblCol, txtCol);
        
        
        
        
        lblRow.setMinWidth(30);
        rowBox.getChildren().addAll(lblRow, txtRow);
        rowBox.setSpacing(10);
        gsBox.getChildren().addAll(colBox, lblX, rowBox,
                lblSpace, btnSub);
        tp.setContent(gsBox);
        tp.setExpanded(true);
        txtCol.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent t) -> {
            char ar[] = t.getCharacter().toCharArray();
            char ch = ar[t.getCharacter().toCharArray().length - 1];
            if (!(ch >= '0' && ch <= '9')) {
                t.consume();
            }
        });
        
        txtRow.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
         public void handle( KeyEvent t ) {
            char ar[] = t.getCharacter().toCharArray();
            char ch = ar[t.getCharacter().toCharArray().length - 1];
            if (!(ch >= '0' && ch <= '9')) {
               t.consume();
            }
         }
      });
        
        btnSub.setOnAction((ActionEvent event) -> {
            APix3l.setSize(Integer.parseInt(txtCol.getText()), Integer.parseInt(txtRow.getText()));
           // APix3l.clearGrid();
            APix3l.setGrid((new NewStage(Integer.parseInt(txtCol.getText()),
                    Integer.parseInt(txtRow.getText()), NewStage.WIDTH, NewStage.HEIGHT)).retBox());
        });
    }
    
    public TitledPane getTP(){
        //tp.expandedProperty().set(false);
        return tp;
    }
}
