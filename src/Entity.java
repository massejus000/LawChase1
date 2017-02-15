/**
 * Created by massejus000 on 2/13/2017.
 */

/*PLANNING:
    *power-up: player eats differently-colored sugar ball
    *clone/decoy is generated elsewhere
    * player breaks into Law's house and eats his food; he and children chase player
    *enemies/Mr. Law will chase decoy for a few seconds to give player chance to eat sugar balls without hindrance
    * clone is there for four seconds; after, Mr. Law continues to chase player
    * Death screen: Mr. Law beats you up
    * once all sugar balls are eaten; "door" opens and player advances to next level
 */

import java.awt.*;

public abstract class Entity {
//test for push and pull
    private Game game;
    private Color color;
    private int x, y, width, height;
    private double dx, dy;

    public Entity(Color color, int x, int y, int width, int height, Game game){

        this.game = game;
        this.color = color;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        //create random angles and speeds
        //check that dx and dy are not 0

        while(dx < 1 || dy < 1){
            double angle = 2 * Math.PI * Math.random();
            double speed = 4 + 8 * Math.random();
            dx = Math.cos(angle) * speed;
            dy = Math.sin(angle) * speed;
        }
    }

    //generic move method

    public void move(){
        double nextLeft = x + dx;
        double nextRight = x + width + dx;
        double nextTop = y + dy;
        double nextBottom = y + height + dy;

        if(nextTop <= 0 || nextBottom > game.getHeight())
            dy *= -1;

        if(nextLeft <= 0 || nextRight > game.getWidth())
            dx *= -1;

        x += dx;
        y += dy;
    }

    public void playerMove(){
        setX(game.positionX); //directly accessing the variable...it's bad code, so make a getter instead
        setY(game.positionY);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    public boolean collides(Entity other){
        return getBounds().intersects(other.getBounds());
    }

    public abstract void paint(Graphics g); //does not directly get called due to being abstract

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}