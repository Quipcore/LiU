package vinjetter.TDDC77.JavaFXtest;  
  
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;  

public class Main extends Application  
{  
	private Stage stage;
 
    @Override  
    public void start(Stage stage2) throws Exception {  

    	stage = stage2;
        Scene scene = logInScene();
        stage2.setX(560);
        stage2.setY(140);
        
        stage2.setScene(scene);
        stage2.show();
    }  
    
    public Scene logInScene(){
    	
        Pane root = new Pane();
        Button playButton = new Button("Play");
        playButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>(){
            public void handle(ActionEvent t){
                  stage.setScene(Rolling());
            }
       });
        root.getChildren().add(playButton);
        return new Scene(root);
    }
    
    public Scene Rolling(){
    	VBox box = new VBox();
    	//box.setPrefWidth(1920);
    	//box.setPrefHeight(1020);
        
        String source = "src/mediaplayer/NeverGonnaGiveYouUp.mp4";
        Media media = new Media(Paths.get(source).toUri().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(100);

        MediaView mediaView = new MediaView(mediaPlayer);
        box.getChildren().add(mediaView);
        box.setAlignment(Pos.CENTER);
        
        final Scene scene = new Scene(box,800,800);
        scene.setFill(null);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        
        return new Scene(box);
    }
    
    public static void main(String[] args) {  
        launch(args);  
    }  
      
}  