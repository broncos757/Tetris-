import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
public class PaneOrganizer {
    private BorderPane root;
    private Pane gamePane;

    private Game g;

    public PaneOrganizer(){
        this.root = new BorderPane();
        this.gamePane = new Pane();
        root.setCenter(gamePane);
        g = new Game(gamePane);

        gamePane.setFocusTraversable(true);
        gamePane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                if(g.isRightLegal()) {
                    g.moveRight();
                }
            }
            else if(event.getCode() == KeyCode.LEFT) {
                if(g.isLeftLegal()) {
                    g.moveLeft();
                }
            }
            else if(event.getCode() == KeyCode.DOWN) {
                if(g.isDownLegal()) {
                    g.moveDown();
                }
            }
            else if(event.getCode() == KeyCode.SPACE) {
                g.moveAllTheWayDown();
            }
        });
    }

    public Pane getRoot(){
        return root;
    }
}
