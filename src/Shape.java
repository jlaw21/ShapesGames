import java.awt.*;

public class Shape {

    Color color;
    int x, y, width, height, startingX, startingY;
    double dx, dy;

    boolean moveDown = false;

    final int SPEED = 2;

    public Shape(Color color, int x, int y, int width, int height){

        this.color = color;
        this.x = x;
        this.y = y;
        startingX = x;
        startingY = y;
        this.width = width;
        this.height = height;
        dx = SPEED;
        dy = SPEED;

    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public boolean collidesWith(Shape other){

        return this.getBounds().intersects(other.getBounds());

    }


    public void paint(Graphics g){

    }

    public void move(int minWidth, int maxWidth, int minHeight, int maxHeight, boolean horizontal, boolean vertical ){

        moveDown = false;

        //predictive movement
        double nextLeft = x + dx;
        double nextRight = x + width + dx;
        double nextTop = y + dy;
        double nextBottom = y + height + dy;

        if((nextTop <= minHeight || nextBottom > maxHeight)){
            dy*=-1;

        }

        if(horizontal && (nextLeft <= minWidth || nextRight > maxWidth)){
            dx *= -1;
            moveDown = true;
        }

        if(horizontal){
            x += dx;
        }

        if(vertical){
            y += dy;
        }

    }

}
