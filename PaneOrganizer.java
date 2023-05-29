
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

public class PaneOrganizer {
    private BorderPane root;
    private Pane gamePane;

    private Game g;

    public PaneOrganizer() {
        this.root = new BorderPane();
        this.gamePane = new Pane();
        root.setCenter(gamePane);
        g = new Game(gamePane);
    }


    public Pane getRoot(){
        return root;
    }


}
