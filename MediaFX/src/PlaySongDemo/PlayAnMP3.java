package PlaySongDemo;

/**
 * This code will play any song assuming that file is in folder songfiles. 
 * 
 * Programmer Rick Mercer
 */
import java.io.File;
import java.net.URI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class PlayAnMP3 extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  private int songsPlayed = 0;

  @Override
  public void start(Stage stage) throws Exception {
    BorderPane pane = new BorderPane();
    String path = "songfiles/Capture.mp3";
    pane.setCenter( new Label(path));
    playASong(path);
    // Put the pane in a sized Scene and show the GUI
    Scene scene = new Scene(pane, 255, 85); // 255 pixels wide, 85 pixels tall
    stage.setScene(scene);
    // Don't forget to show the running app:
    stage.show();
  }

  
  private void playASong(String path) {
   
    // Need a File and URI object so the path works on all OSs
    File file = new File(path);
    URI uri = file.toURI();
    System.out.println(uri);
    // Play one mp3 and and have code run when the song ends
    Media media = new Media(uri.toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();
      
    mediaPlayer.setOnEndOfMedia(new Waiter());
    System.out.println("You may need to shut this App down");
 
    }
  
  private class Waiter implements Runnable {
    @Override
    public void run() {
      songsPlayed++;
      System.out.println("Song ended, play song #" + songsPlayed);
      Platform.exit();
    }
  }
}