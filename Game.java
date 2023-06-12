import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
public class Game {
    private Pane gamePane;
    private Board b;
    private Square[][] board;

    private Piece p;
    private Timeline timeline;
    private Text t;

    ArrayList<Piece> oldPieces = new ArrayList();
    private Boolean paused = false;
    public Game(Pane gPane){
        gamePane = gPane;
        b = new Board();
        board = b.getBoard();
        for(int i = 0; i < 14; i++){
            for(int j = 0; j < 23; j++){
                gamePane.getChildren().addAll(board[i][j].getNode());
            }
        }

        gamePane.setFocusTraversable(true);
        gamePane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                if(isRightLegal()) {
                    moveRight();
                }
            }
            else if(event.getCode() == KeyCode.LEFT) {
                if(isLeftLegal()) {
                    moveLeft();
                }
            }
            else if(event.getCode() == KeyCode.DOWN) {
                if(isDownLegal()) {
                    moveDown();
                }
            }
            else if(event.getCode() == KeyCode.SPACE) {
                moveAllTheWayDown();
            }
            else if(event.getCode() == KeyCode.UP) {
                rotatePiece();
            }
            else if(event.getCode() == KeyCode.P) {
                pauseTimeline();
            }
        });
        Button b = new Button("Quit Game");
        b.setOnAction(event -> {
            System.exit(0);
        });

        this.makePiece();
        this.setupTimeline();
    }
    public void rotatePiece(){
        p.rotatePiece();
    }

    public void makePiece() {
        int num = (int) (7 * Math.random());
        switch (num) {
            case 0:
                p = new Piece(1);
                gamePane.getChildren().addAll(p.getNode1(), p.getNode2(), p.getNode3(), p.getNode4());
                break;
            case 1:
                p = new Piece(2);
                gamePane.getChildren().addAll(p.getNode1(), p.getNode2(), p.getNode3(), p.getNode4());
                break;
            case 2:
                p = new Piece(3);
                gamePane.getChildren().addAll(p.getNode1(), p.getNode2(), p.getNode3(), p.getNode4());
                break;
            case 3:
                p = new Piece(4);
                gamePane.getChildren().addAll(p.getNode1(), p.getNode2(), p.getNode3(), p.getNode4());
                break;
            case 4:
                p = new Piece(5);
                gamePane.getChildren().addAll(p.getNode1(), p.getNode2(), p.getNode3(), p.getNode4());
                break;
            case 5:
                p = new Piece(6);
                gamePane.getChildren().addAll(p.getNode1(), p.getNode2(), p.getNode3(), p.getNode4());
                break;
            default:
                p = new Piece(7);
                gamePane.getChildren().addAll(p.getNode1(), p.getNode2(), p.getNode3(), p.getNode4());
                break;
        }
    }
        public void setupTimeline(){
            KeyFrame kf = new KeyFrame(Duration.seconds(.5), (ActionEvent e) -> this.updatePos());
            timeline = new Timeline(kf);
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
        public void pauseTimeline(){
            if (paused){
                gamePane.getChildren().removeAll(t);
               timeline.play();
                paused = false;
            }
            else{
                timeline.pause();
                t = new Text("game paused");
                t.setFont(Font.font("Arial", 24));
                t.setFill(Color.ORANGE);
                t.setY(300);
                t.setX(150);
                gamePane.getChildren().addAll(t);
                paused = true;
            }
        }
        public void updatePos(){
            if(this.gameOver()){
                System.exit(0);
            }
            if(this.isDownLegal() && checkOldPieces(0,1)) {
                    p.moveDown();
            }
            else{
                oldPieces.add(p);
                this.makePiece();
            }
            if(this.isBottomFull()){
                this.removeBottomRow();
            };
        }

        public void moveRight(){
            if(checkOldPieces(1,0)) {
                p.moveRight();
            }
        }
        public void moveLeft(){
        if(checkOldPieces(-1,0)) {
            p.moveLeft();
        }
        }
        public void moveDown(){
        if(checkOldPieces(0,1)) {
            p.moveDown();
        }
        }

        public void moveAllTheWayDown(){

            if(this.checkOldPieces(0,1)&& this.isDownLegal()){
                p.moveDown();
                this.moveAllTheWayDown();
            }
        }


        public boolean gameOver(){
        for(int i = 0; i < oldPieces.size(); i++){
            if(oldPieces.get(i).getOne().getY()/Constants.SQUARE_WIDTH < 4 ||
                    oldPieces.get(i).getTwo().getY()/Constants.SQUARE_WIDTH <4 ||
                    oldPieces.get(i).getThree().getY()/Constants.SQUARE_WIDTH <4 ||
                    oldPieces.get(i).getFour().getY()/Constants.SQUARE_WIDTH <4){
                return true;
            }
        }
            return false;
        }

        public boolean isLeftLegal(){
            if(board[p.Square1X()-1][p.Square1Y()].getColor() == Color.BLACK &&
                    board[p.Square2X()-1][p.Square2Y()].getColor() == Color.BLACK &&
                    board[p.Square3X()-1][p.Square3Y()].getColor() == Color.BLACK &&
                    board[p.Square4X()-1][p.Square4Y()].getColor() == Color.BLACK){
                return true;
            }
            return false;
        }
        public boolean isBottomFull(){
            for(int i = 1; i<13; i++){
                int x = 0;
                for(int j = 0; j < oldPieces.size(); j++){
                    Piece p = oldPieces.get(j);
                    if(p.Square1X() == i && p.Square1Y() == 21){
                        x+=1;
                    }
                    if(p.Square2X() == i && p.Square2Y() == 21){
                        x+=1;
                    }
                    if(p.Square3X() == i && p.Square3Y() == 21){
                        x+=1;
                    }
                    if(p.Square4X() == i && p.Square4Y() == 21){
                        x+=1;
                    }
                }
                if(x==0){

                    return false;
                }
            }
            System.out.println("true");
            return true;
        }
        public void removeBottomRow(){
            for(int i = 0; i < oldPieces.size(); i++){
                Piece p = oldPieces.get(i);
                p.getOne().setY(p.getOne().getY()/Constants.SQUARE_WIDTH  +1);
                if((int) p.getOne().getY()/Constants.SQUARE_WIDTH >21){
                    p.getOne().setY(1000);
                }
                p.getTwo().setY(p.getTwo().getY()/Constants.SQUARE_WIDTH  +1);
                if((int) p.getTwo().getY()/Constants.SQUARE_WIDTH >21){
                    p.getTwo().setY(1000);
                }
                p.getThree().setY(p.getThree().getY()/Constants.SQUARE_WIDTH  +1);
                if((int) p.getThree().getY()/Constants.SQUARE_WIDTH >21){
                    p.getThree().setY(1000);
                }
                p.getFour().setY(p.getFour().getY()/Constants.SQUARE_WIDTH  +1);
                if((int) p.getFour().getY()/Constants.SQUARE_WIDTH >21){
                    p.getFour().setY(1000);
                }
            }
        }
        public boolean isRightLegal(){
            if(board[p.Square1X()+1][p.Square1Y()].getColor() == Color.BLACK &&
                    board[p.Square2X()+1][p.Square2Y()].getColor() == Color.BLACK &&
                    board[p.Square3X()+1][p.Square3Y()].getColor() == Color.BLACK &&
                    board[p.Square4X()+1][p.Square4Y()].getColor() == Color.BLACK){
                return true;
            }
            return false;
        }
    public boolean isDownLegal(){
        if(board[p.Square1X()][p.Square1Y()+1].getColor() == Color.BLACK &&
                board[p.Square2X()][p.Square2Y()+1].getColor() == Color.BLACK &&
                board[p.Square3X()][p.Square3Y()+1].getColor() == Color.BLACK &&
                board[p.Square4X()][p.Square4Y()+1].getColor() == Color.BLACK){
            return true;
        }
        return false;
    }
    public Boolean checkOldPieces(int x, int y){
        for(int i = 0; i <oldPieces.size(); i++){
            if(oldPieces.get(i).getOne().getX()/Constants.SQUARE_WIDTH == p.getOne().getX()/Constants.SQUARE_WIDTH + x &&
                    oldPieces.get(i).getOne().getY()/Constants.SQUARE_WIDTH == p.getOne().getY()/Constants.SQUARE_WIDTH +y){
                return false;
            }
            if(oldPieces.get(i).getOne().getX()/Constants.SQUARE_WIDTH == p.getTwo().getX()/Constants.SQUARE_WIDTH + x&&
                    oldPieces.get(i).getOne().getY()/Constants.SQUARE_WIDTH == p.getTwo().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getOne().getX()/Constants.SQUARE_WIDTH == p.getThree().getX()/Constants.SQUARE_WIDTH + x&&
                    oldPieces.get(i).getOne().getY()/Constants.SQUARE_WIDTH == p.getThree().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getOne().getX()/Constants.SQUARE_WIDTH == p.getFour().getX()/Constants.SQUARE_WIDTH +x&&
                    oldPieces.get(i).getOne().getY()/Constants.SQUARE_WIDTH == p.getFour().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getTwo().getX()/Constants.SQUARE_WIDTH == p.getOne().getX()/Constants.SQUARE_WIDTH +x&&
                    oldPieces.get(i).getTwo().getY()/Constants.SQUARE_WIDTH == p.getOne().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getTwo().getX()/Constants.SQUARE_WIDTH == p.getTwo().getX()/Constants.SQUARE_WIDTH+x &&
                    oldPieces.get(i).getTwo().getY()/Constants.SQUARE_WIDTH == p.getTwo().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getTwo().getX()/Constants.SQUARE_WIDTH == p.getThree().getX()/Constants.SQUARE_WIDTH+x &&
                    oldPieces.get(i).getTwo().getY()/Constants.SQUARE_WIDTH == p.getThree().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getTwo().getX()/Constants.SQUARE_WIDTH == p.getFour().getX()/Constants.SQUARE_WIDTH +x&&
                    oldPieces.get(i).getTwo().getY()/Constants.SQUARE_WIDTH == p.getFour().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getThree().getX()/Constants.SQUARE_WIDTH == p.getOne().getX()/Constants.SQUARE_WIDTH +x&&
                    oldPieces.get(i).getThree().getY()/Constants.SQUARE_WIDTH == p.getOne().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getThree().getX()/Constants.SQUARE_WIDTH == p.getTwo().getX()/Constants.SQUARE_WIDTH+x &&
                    oldPieces.get(i).getThree().getY()/Constants.SQUARE_WIDTH == p.getTwo().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getThree().getX()/Constants.SQUARE_WIDTH == p.getThree().getX()/Constants.SQUARE_WIDTH +x&&
                    oldPieces.get(i).getThree().getY()/Constants.SQUARE_WIDTH == p.getThree().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getThree().getX()/Constants.SQUARE_WIDTH == p.getFour().getX()/Constants.SQUARE_WIDTH+x &&
                    oldPieces.get(i).getThree().getY()/Constants.SQUARE_WIDTH == p.getFour().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getFour().getX()/Constants.SQUARE_WIDTH == p.getOne().getX()/Constants.SQUARE_WIDTH+x &&
                    oldPieces.get(i).getFour().getY()/Constants.SQUARE_WIDTH == p.getOne().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getFour().getX()/Constants.SQUARE_WIDTH == p.getTwo().getX()/Constants.SQUARE_WIDTH+x &&
                    oldPieces.get(i).getFour().getY()/Constants.SQUARE_WIDTH == p.getTwo().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getFour().getX()/Constants.SQUARE_WIDTH == p.getThree().getX()/Constants.SQUARE_WIDTH+x &&
                    oldPieces.get(i).getFour().getY()/Constants.SQUARE_WIDTH == p.getThree().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
            if(oldPieces.get(i).getFour().getX()/Constants.SQUARE_WIDTH == p.getFour().getX()/Constants.SQUARE_WIDTH+x &&
                    oldPieces.get(i).getFour().getY()/Constants.SQUARE_WIDTH == p.getFour().getY()/Constants.SQUARE_WIDTH+y){
                return false;
            }
        }
        return true;
    }
}

