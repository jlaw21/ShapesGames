import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel implements ActionListener {

    final int _ENEMY_WIDTH = 25;
    final int _PLAYER_WIDTH = 20;
    final int _PLAYER_HEIGHT = 70;
    int _ENEMY_MOVE_RANGE = 0;


    List<Shape> actors = new ArrayList<Shape>();
    ArrayList<Shape> bullets = new ArrayList<Shape>();

    final int enemyNum = 5;

    Timer timer;

    public Board(){
        setPreferredSize(new Dimension(800,600));
        setBackground(Color.BLACK);
        timer = new Timer(1000/60, this);
        timer.start();
    }

    public void setup(){
        actors.add(0, new Player(Color.GREEN, 200, 500, _PLAYER_WIDTH, _PLAYER_HEIGHT));

        System.out.println(getWidth());
        for(int i = 0; i < enemyNum; i++){
            for(int j = 0; j < enemyNum; j++){
                actors.add(new Enemy(Color.RED, i*(int)(getWidth()/5.0), j*50, _ENEMY_WIDTH));
            }
        }

        _ENEMY_MOVE_RANGE = getWidth() - (actors.get(actors.size()-1).x);
        System.out.println(_ENEMY_MOVE_RANGE);
    }

    public void addBullets(){

        bullets.add(new Bullet(Color.blue, actors.get(0).x, actors.get(0).y, 10));

    }

    public void setPlayerPos(int x, int y){
        actors.get(0).setPosition(x, y);
    }

    public void checkCollisions(){

        for(int i = 0; i < bullets.size(); i++){
            for(int j = 0; j < actors.size(); j++){
                if(bullets.get(i).collidesWith(actors.get(j))){
                    System.out.println("HIT ENEMY");
                }
            }
        }

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        for(Shape actor:actors){
            actor.paint(g);
        }

        for(Shape bullet:bullets){
            bullet.paint(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int x = 0;
        int y = 0;

        checkCollisions();

        for(int i = 1; i < actors.size(); i++){
            x = actors.get(i).startingX;
            y = actors.get(i).startingY;

            actors.get(i).move(x, x + _ENEMY_MOVE_RANGE, y, y, true, false);

            if(actors.get(i).moveDown){
                actors.get(i).setPosition(actors.get(i).x, actors.get(i).y + 20);
            }

        }

        for(Shape bullet:bullets){
            bullet.dy = 8;
            bullet.move(0, 0, bullet.y, 0, false, true);
        }

        repaint();
    }
}
