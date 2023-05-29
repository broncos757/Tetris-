import javafx.scene.paint.Color;
public class Board {
    private Square[][] board;
    public Board(){
        board = new Square[14][23];
        for(int i = 0; i < 14; i++){
            for(int j = 0; j < 23; j++){
                Square s = new Square();
                s.setColor(Color.BLUE);
                s.setX(i);
                s.setY(j);
                board[i][j] = s;
            }
        }

        for(int i = 1; i < 13; i++){
            for(int j = 1; j<22; j++){
                Square s = new Square();
                s.setColor(Color.BLACK);
                s.setX(i);
                s.setY(j);
                board[i][j] = s;
            }
        }

    }
    public Square[][] getBoard(){
        return board;
    }
}

