import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
/**
 * This is the main class where your Tetris game will start.
 * The main method of this application calls launch, a JavaFX method
 * which eventually calls the start method below. You will need to fill
 * in the start method to start your game!
 *
 * Class comments here...
 */

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // Create top-level object, set up the scene, and show the stage here.
        PaneOrganizer organizer = new PaneOrganizer();
        //what should the width and height be
        Scene scene = new Scene(organizer.getRoot(), 420, 720);
        stage.setScene(scene);
        stage.show();
    }

    /*
     * Here is the mainline! No need to change this.
     */
    public static void main(String[] argv) {
        // launch is a method inherited from Application
        launch(argv);
    }
}
