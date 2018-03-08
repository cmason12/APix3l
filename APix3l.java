/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anapixel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class APix3l extends Application {
    private GridSizeDef gsd = new GridSizeDef();
    private PickColor color_Box = new PickColor();
    protected static int colSize = 50;
    protected static int rowSize = 50;
    public static VBox gridBox = new VBox();
    private ImageView back = new ImageView("https://image.freepik.com/free-vector/abstract-metal-background_1048-6730.jpg");
    private ImageView exit = new ImageView("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/3d-transparent-glass-icons-alphanumeric/068009-3d-transparent-glass-icon-alphanumeric-crossing.png");
    private ImageView min = new ImageView("http://cdn.mysitemyway.com/etc-mysitemyway/icons/legacy-previews/icons/3d-transparent-glass-icons-symbols-shapes/016926-3d-transparent-glass-icon-symbols-shapes-minimize.png");
    private MenuBar mBar = new MenuBar();
    private Menu fileMenu = new Menu("File");
    private Menu optionsMenu = new Menu("Options");
    private static ScrollPane parentFrame = new ScrollPane();
    private static Pane gridFrame = new Pane();
    
    private MenuItem saveItem = new MenuItem("Save");
    private MenuItem saveAsItem = new MenuItem("Save As");
    private MenuItem openItem = new MenuItem("Open");
    private MenuItem fillerItem = new MenuItem("---------");
    private MenuItem renameItem = new MenuItem("Rename");
    private MenuItem BACKGROUNDItem = new MenuItem("Background\n------------");
    public static MenuItem autoCropItem = new MenuItem("AutoCrop: ON");
    protected static boolean autoCrop = true;
    private MenuItem backItem = new MenuItem("Back-> Cur");
    private MenuItem resetBackItem = new MenuItem("Reset->Back");
    public static String fileName = "ledgaurd";
    public static boolean editedStage = false;
    
    public static NewStage stg = new NewStage(colSize, rowSize, (600), (600));
    private static double xOffset = 0;
    private static double yOffset = 0;
    
    public static void  setSize(int col, int row){
        colSize = col;
        rowSize = row;
    };
    
    public static void  setGrid(VBox newStage){
        gridFrame.getChildren().remove(gridBox);
        gridBox = newStage;
        gridFrame.getChildren().add(gridBox);
    };
    
    public void setBackgroundColor (String web){
        parentFrame.setStyle("-fx-background: " + web.toString().substring(2) + ";");
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("/Resources/Shrooom.png"));
        exit.setFitWidth(20);
        exit.setFitHeight(20);
        min.setFitWidth(20);
        min.setFitHeight(20);
    
        mBar.getMenus().addAll(fileMenu, optionsMenu);
        mBar.setStyle("-fx-border-color:grey; -fx-background-color: #92a29cff;");
        
        VBox toolPanel = new VBox();
        HBox horz = new HBox();
        StackPane root = new StackPane();
        
        Scene scene = new Scene(root, 600, 600);
        root.prefHeightProperty().bind(scene.heightProperty());
        root.prefWidthProperty().bind(scene.widthProperty());
        gridFrame.prefHeightProperty().bind(scene.heightProperty());
 
        gridFrame.prefWidthProperty().bind(scene.widthProperty().multiply(1.333));
          gridBox = new VBox();
        
        System.out.println(scene.getWidth() + "");
        System.out.println(scene.getHeight() + "");
        gridBox = stg.retBox();
        gridFrame.getChildren().add(gridBox);
        gridFrame.setTranslateY(20);
        gridFrame.setTranslateX(20);
     
        toolPanel.prefHeightProperty().bind(scene.heightProperty());
        toolPanel.prefWidthProperty().bind(scene.widthProperty().subtract(scene.getWidth()* (2/3)));
        
        toolPanel.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
        parentFrame.prefHeightProperty().bind(scene.heightProperty().subtract(20));
        parentFrame.prefWidthProperty().bind(scene.widthProperty().multiply(1.333));
        parentFrame.setContent(gridFrame);
        parentFrame.setFitToWidth(true);
        parentFrame.setFitToHeight(true);
        parentFrame.setStyle("-fx-background: rgba(4, 31, 3, 0.9);");
        
        
        horz.getChildren().addAll(parentFrame, toolPanel);
        root.getChildren().addAll( horz, back, mBar, exit, min);
        mBar.setTranslateY(-290);
        exit.setTranslateY(-290);
        exit.setTranslateX(280);
        min.setTranslateY(-290);
        min.setTranslateX(260);
        
        DropShadow dropShadow = new DropShadow();
    dropShadow.setRadius(5.0);
    dropShadow.setColor(Color.RED);
    
    fileMenu.getItems().addAll(saveItem, saveAsItem, openItem, 
            fillerItem,renameItem);
    optionsMenu.getItems().addAll(autoCropItem, BACKGROUNDItem, 
            backItem, resetBackItem);    
        horz.setTranslateY(20);
        back.toBack();
        back.setFitWidth(scene.getWidth()*2);
        back.setFitHeight(scene.getHeight());
        primaryStage.setScene(scene);
        
       
        SaveImageOpts sop = new SaveImageOpts();
        toolPanel.getChildren().addAll(gsd.getTP(), color_Box.getCBox(),
                sop.retTP());
        toolPanel.requestFocus();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        exit.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
               stg.save();
               Platform.exit();
            }
        });
        
        exit.setOnMouseEntered(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
               exit.setEffect(dropShadow);
            }
        });
        exit.setOnMouseExited(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
               exit.setEffect(null);
            }
        });
        
        
        min.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
               primaryStage.setIconified(true);
            }
        });
        
        min.setOnMouseEntered(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
               min.setEffect(dropShadow);
            }
        });
        min.setOnMouseExited(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
               min.setEffect(null);
            }
        });
        
        mBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        mBar.setOnMouseDragged((MouseEvent event) -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        
        renameItem.setOnAction((ActionEvent event) -> {
                fileName = NewStage.inpBox("File Name: ");
        });
        saveItem.setOnAction((ActionEvent event) -> {
                stg.save();
        });
        saveAsItem.setOnAction((ActionEvent event) -> {
                fileName = "ledgaurd";
                stg.save();
        });
        openItem.setOnAction((ActionEvent event) -> {
                gridFrame.getChildren().remove(gridBox);
                gridBox = stg.open();
                gridFrame.getChildren().add(gridBox);
        });
        
        backItem.setOnAction((ActionEvent event) -> {
               setBackgroundColor (PickColor.getColor().toString());
        });
        resetBackItem.setOnAction((ActionEvent event) -> {
               parentFrame.setStyle("-fx-background: rgba(4, 31, 3, 0.9);");
        });
        
        autoCropItem.setOnAction((ActionEvent event) -> {
               autoCrop = !autoCrop;
               
               if(autoCrop)autoCropItem.setText("AutoCrop: ON");
               else autoCropItem.setText("AutoCrop: OFF");
        });
        
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
