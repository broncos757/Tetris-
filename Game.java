import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import java.util.ArrayList;

public class Game {
    private Pane gamePane;
    private Board b;
    private Square[][] board;

    private Piece p;

    ArrayList<Piece> oldPieces = new ArrayList();
    public Game(Pane gPane){
        gamePane = gPane;
        b = new Board();
        board = b.getBoard();
        for(int i = 0; i < 14; i++){
            for(int j = 0; j < 23; j++){
                gamePane.getChildren().addAll(board[i][j].getNode());
            }
        }
        this.makePiece();
        this.setupTimeline();
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
            Timeline timeline = new Timeline(kf);
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
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
        //why does the while loop not work
            /*
            while(checkOldPieces(0,1)){
                p.moveDown();
            }

             */
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
