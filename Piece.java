import javafx.scene.paint.Color;
import javafx.scene.Node;
public class Piece {
    private Square one;
    private Square two;
    private Square three;
    private Square four;
    private Square[][] b;
    private Board board;
    private int num;
    public Piece(int n){
        board = new Board();
        //I could pass this in instead
        b = board.getBoard();
        one = new Square();
        two = new Square();
        three = new Square();
        four = new Square();
        num = n;
        this.setX(6);
        this.setY(1);
        this.setColor();
    }
    public void setColor() {
        int num = (int) (6 * Math.random());
        switch (num) {
            case 0:
                one.setColor(Color.PURPLE);
                two.setColor(Color.PURPLE);
                three.setColor(Color.PURPLE);
                four.setColor(Color.PURPLE);
                break;
            case 1:
                one.setColor(Color.RED);
                two.setColor(Color.RED);
                three.setColor(Color.RED);
                four.setColor(Color.RED);
                break;
            case 2:
                one.setColor(Color.ORANGE);
                two.setColor(Color.ORANGE);
                three.setColor(Color.ORANGE);
                four.setColor(Color.ORANGE);
                break;
            case 3:
                one.setColor(Color.BLUE);
                two.setColor(Color.BLUE);
                three.setColor(Color.BLUE);
                four.setColor(Color.BLUE);
                break;
            case 4:
                one.setColor(Color.YELLOW);
                two.setColor(Color.YELLOW);
                three.setColor(Color.YELLOW);
                four.setColor(Color.YELLOW);
                break;
            default:
                one.setColor(Color.GREEN);
                two.setColor(Color.GREEN);
                three.setColor(Color.GREEN);
                four.setColor(Color.GREEN);
                break;
        }
    }

        public void setX(double x) {
            //change up order at some point
            switch (num) {
                case 1:
                        one.setX(x);
                        two.setX(x+1);
                        three.setX(x);
                        four.setX(x+1);
                    break;
                case 2:
                        one.setX(x);
                        two.setX(x+1);
                        three.setX(x+1);
                        four.setX(x+1);
                    break;
                case 3:
                        one.setX(x);
                        two.setX(x);
                        three.setX(x);
                        four.setX(x+1);
                    break;
                case 4:
                        one.setX(x);
                        two.setX(x);
                        three.setX(x);
                        four.setX(x);
                    break;
                case 5:
                        one.setX(x);
                        two.setX(x+1);
                        three.setX(x+1);
                        four.setX(x+2);

                    break;
                case 6:
                        one.setX(x);
                        two.setX(x+1);
                        three.setX(x+1);
                        four.setX(x+2);
                    break;
                    default:
                        one.setX(x);
                        two.setX(x+1);
                        three.setX(x+1);
                        four.setX(x+2);
                    break;
            }
        }
        public void setY(double y) {
            switch (num) {
                case 1:
                    one.setY(y);
                    two.setY(y);
                    three.setY(y+1);
                    four.setY(y+1);
                    break;
                case 2:
                    one.setY(y+2);
                    two.setY(y+2);
                    three.setY(y+1);
                    four.setY(y);
                    break;
                case 3:
                    one.setY(y);
                    two.setY(y+1);
                    three.setY(y+2);
                    four.setY(y+2);
                    break;
                case 4:
                    one.setY(y);
                    two.setY(y+1);
                    three.setY(y+2);
                    four.setY(y+3);
                    break;
                case 5:
                    one.setY(y);
                    two.setY(y);
                    three.setY(y+1);
                    four.setY(y+1);
                    break;
                case 6:
                    one.setY(y+1);
                    two.setY(y);
                    three.setY(y+1);
                    four.setY(y);
                    break;
                default:
                    one.setY(y+1);
                    two.setY(y);
                    three.setY(y+1);
                    four.setY(y+1);
                    break;
            }
        }
        public void moveDown(){
            one.setY(one.getY()/Constants.SQUARE_WIDTH+1);
            two.setY(two.getY()/Constants.SQUARE_WIDTH+1);
            three.setY(three.getY()/Constants.SQUARE_WIDTH+1);
            four.setY(four.getY()/Constants.SQUARE_WIDTH+1);
        }
        public void moveRight(){
            one.setX(one.getX()/Constants.SQUARE_WIDTH+1);
            two.setX(two.getX()/Constants.SQUARE_WIDTH+1);
            three.setX(three.getX()/Constants.SQUARE_WIDTH+1);
            four.setX(four.getX()/Constants.SQUARE_WIDTH+1);

        }
        public void moveLeft(){
            one.setX(one.getX()/Constants.SQUARE_WIDTH-1);
            two.setX(two.getX()/Constants.SQUARE_WIDTH-1);
            three.setX(three.getX()/Constants.SQUARE_WIDTH-1);
            four.setX(four.getX()/Constants.SQUARE_WIDTH-1);

        }

        public void rotatePiece(){
        double x2 = (one.getX() - one.getY() +two.getY())/Constants.SQUARE_WIDTH;
        double y2 = (one.getY()+ one.getX()-two.getX())/Constants.SQUARE_WIDTH;
        double x3 = (one.getX() - one.getY() +three.getY())/Constants.SQUARE_WIDTH;
        double y3 = (one.getY()+ one.getX()-three.getX())/Constants.SQUARE_WIDTH;
            double x4 = (one.getX() - one.getY() +four.getY())/Constants.SQUARE_WIDTH;
            double y4 = (one.getY()+ one.getX()-four.getX())/Constants.SQUARE_WIDTH;

        two.setX(x2);
        two.setY(y2);
        three.setX(x3);
        three.setY(y3);
        four.setX(x4);
        four.setY(y4);




        }

        public int Square1X(){
            return (int) one.getX()/Constants.SQUARE_WIDTH;

        }
        public int Square2X(){
            return (int) two.getX()/Constants.SQUARE_WIDTH;
        }
        public int Square3X(){
            return (int) three.getX()/Constants.SQUARE_WIDTH;
        }
        public int Square4X(){
            return (int) four.getX()/Constants.SQUARE_WIDTH;
        }
        public int Square1Y(){
            return (int) one.getY()/Constants.SQUARE_WIDTH;
        }
        public int Square2Y(){
            return (int) two.getY()/Constants.SQUARE_WIDTH;
        }
        public int Square3Y(){
            return (int) three.getY()/Constants.SQUARE_WIDTH;
        }
        public int Square4Y(){
            return (int) four.getY()/Constants.SQUARE_WIDTH;
        }
        public Node getNode1(){
            return one.getNode();
        }
        public Node getNode2(){
            return two.getNode();
        }
        public Node getNode3(){
            return three.getNode();
        }
        public Node getNode4(){
            return four.getNode();
        }

        public Square getOne(){
            return one;
        }
        public Square getTwo(){
            return two;
        }
        public Square getThree(){
            return three;
        }
        public Square getFour(){
            return four;
        }
}

