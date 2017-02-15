/**
 * Created by massejus000 on 2/13/2017.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


//implements: method inheritance
//extends: class inheritance
public class Game extends JPanel implements ActionListener {

    Timer timer;
    int positionX, positionY;
    ArrayList<Entity> entities;

    //create new rectangle around door to act as barrier
    Door door = new Door(Color.cyan, (int)(25 + getWidth()-100*Math.random()), (int)(25 + getHeight()-50*Math.random()), 40, 25, this);

    public Game() {

        //sets up JFrame properties
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Shapes");
        setPreferredSize(new Dimension(600, 800));
        setBackground(Color.black);

        /*
        ImageIcon pic = newImageIcon("./src/pic.png");
        Image icon = pic.getImage();
        frame.setIconImage(icon);
         */
        frame.add(this);

        addMouseMotionListener(new MouseMotionAdapter(){ //creates new class of addMouseMotionListener
            @Override

            public void mouseMoved(MouseEvent e){
                super.mouseMoved(e);
                positionX = e.getX();
                positionY = e.getY();
            }

        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                setCursor(getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
                        new Point(0,0), null));
            }
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /* for(int i = 0; i < entities.size(); i++){
            entities.get(i).move();
         */ //long way to make everything move

            /*for(Entity obj : entities) <----this was commented out because not all objects are moving the same way here
                obj.move(); //short way to make things move
                            //obj.move() also moves ALL objects instead of having to do it individually*/

        collisions();
        entities.get(0).playerMove();

        for (int i = 0; i < entities.size(); i++){ //moves our objects
            entities.get(i).move();
        }

        repaint();
    }

    public void init() {

        //food is circle, fam is square; we'll do this last though since it's time consuming

        entities = new ArrayList<Entity>();

        entities.add(new Circle(Color.red, getWidth()/2, getHeight()/2, 20, this)); //player
        entities.add(new Sugar(Color.green, (int)(25 + getWidth()-100*Math.random()),(int)(25 + getHeight()-50*Math.random()), 20, 20, this));


        for(int i = 0; i < 50; i++){ //sugar
            entities.add(new Sugar(Color.green, (int)(25 + getWidth()-100*Math.random()),(int)(25 + getHeight()-50*Math.random()), 20, 20, this));
        }

        for(int i = 0; i < 1; i++){ //special sugar; creates clone
            entities.add(new Sugar(Color.PINK, (int)(25 + getWidth()-100*Math.random()),(int)(25 + getHeight()-50*Math.random()), 20, 20, this));
        }
        /*redBall = new Circle(Color.red, getWidth()/2, getHeight()/2, 20, this);
        greenBall = new Circle(Color.green, getWidth()/2, getHeight()/2, 10, this);
        blueSquare = new Food(Color.blue, getWidth()/2, getHeight()/2, 15, this); DON'T DO THIS*/

        for (int i = 0; i < 1; i++){ //Mr. Law
            entities.add(new Circle(Color.blue, (int)(25 + getWidth()-100*Math.random()),(int)(25 + getHeight()-50*Math.random()), 20, this));
        }

        for (int i = 0; i < 2; i++){ //his kids
            entities.add(new Circle(Color.orange, (int)(25 + getWidth()-100*Math.random()),(int)(25 + getHeight()-50*Math.random()), 20, this));
        }
    }

    public void collisions() {
        for (int i = 1; i < entities.size(); i++){ //player eats sugar
            if (entities.get(0).collides(entities.get(i))){
                if (entities.get(i) instanceof Sugar){ //check what instanceof means
                    entities.remove(i);
                }

                else if (entities.get(i) instanceof Circle){ //"Kills" player
                    JOptionPane.showMessageDialog(null, "You died!");
                    System.exit(0);
                }
            }
        }
    }

    public void run(){
        timer = new Timer(1000/60, this);
        timer.start();

    }

    public void paint(Graphics g){

        super.paint(g);
        door.paint(g);

        for(Entity obj : entities) //for each loop
            obj.paint(g); //paint method from Entity class
    }

    /*If no paint method was in Entity class, we would have to use Circle.paint(), Rectangle.paint(), etc.
      With paint method in super class, we can paint every object inside the ArrayList instead of individually
     */

    public static void main(String[] args){
        Game game = new Game();
        game.init();
        game.run();
    }

}
