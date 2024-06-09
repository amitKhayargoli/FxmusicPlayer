import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.event.ChangeListener;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class musicController implements Initializable {

    @FXML
    private ImageView next;

    @FXML
    private ImageView pause;

    @FXML
    private ImageView previous;

    @FXML
    private ImageView playButton;

    @FXML
    private HBox song1;

    @FXML
    private Slider slider;

    @FXML
    private Label current;

    @FXML
    private Label trackLength;

    @FXML
    private Label trackName;

    @FXML
    private ImageView playlistButton;

    @FXML
    private ImageView playing;

    @FXML
    private VBox playlistPanel;

    @FXML
    private VBox nowPlayingPanel;

    private Media media;
    private MediaPlayer MediaPlayer;

    private File directory;
    private File[] files;

    private ArrayList<File> songs;

    private int songNumber;

    private Timer timer;
    private TimerTask task;
    private boolean running;

    String track;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        songs = new ArrayList<File>();

        directory = new File("RUTS");

        files = directory.listFiles();

        if (files != null) {

            for (File file : files) {

                songs.add(file);
                System.out.println(file);
            }
        }

        media = new Media(songs.get(songNumber).toURI().toString());
        MediaPlayer = new MediaPlayer(media);

    }

    @FXML
    void next(MouseEvent event) {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            MediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            MediaPlayer = new MediaPlayer(media);
            MediaPlayer.play();

            playButton.setVisible(false);
            pause.setVisible(true);

            track = songs.get(songNumber).toString().substring(8).replace(".mp3", "");
            trackName.setText(track);

        }

    }

    @FXML
    void showPlaylistPanel(MouseEvent event) {
        nowPlayingPanel.setVisible(false);
        playlistPanel.setVisible(true);
    }

    @FXML
    void showTrack(MouseEvent event) {
        //To display the name of the track without the location and the extension
        track = songs.get(songNumber).toString().substring(8).replace(".mp3", "");
        trackName.setText(track);

        if (MediaPlayer.getStatus() == Status.PLAYING) {  //error
            // trackName.setText(track.substring(8));
            nowPlayingPanel.setVisible(true);
            playlistPanel.setVisible(false);
        }

    }

    @FXML
    void pause(MouseEvent event) {
        MediaPlayer.pause();
        pause.setVisible(false);
        playButton.setVisible(true);
    }

    @FXML
    void playsong1(MouseEvent event) {
        MediaPlayer.play();
        pause.setVisible(true);
        playButton.setVisible(false);

    }

    @FXML
    void playMusic(MouseEvent event) {
        MediaPlayer.play();
        playButton.setVisible(false);
        pause.setVisible(true);
    }

    @FXML
    void previous(MouseEvent event) {

        if (songNumber > 0) {
            songNumber--;
            MediaPlayer.stop();
            media = new Media(songs.get(songNumber).toURI().toString());
            MediaPlayer = new MediaPlayer(media);
            MediaPlayer.play();

            playButton.setVisible(false);
            pause.setVisible(true);

            track = songs.get(songNumber).toString().substring(8).replace(".mp3", "");
            trackName.setText(track);


        }
    }

}
