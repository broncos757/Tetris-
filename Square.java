import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.Node;
public class Square {
    private Rectangle rect;
    public Square(){
        rect = new Rectangle(Constants.SQUARE_WIDTH, Constants.SQUARE_WIDTH);
        rect.setStroke(Color.BLACK);
    }

    public void setX(double x){
        rect.setX(x*Constants.SQUARE_WIDTH);
    }
    public void setY(double y){
        rect.setY(y*Constants.SQUARE_WIDTH);
    }
    public double getY(){
        return rect.getY();
    }

    public double getX(){
        return rect.getX();
    }

    public void setColor(Color c){
        rect.setFill(c);
    }
    public Paint getColor(){
        return rect.getFill();
    }

    public Node getNode(){
        return rect;
    }
}

