import java.awt.*;

/**
 * Created by massejus000 on 2/13/2017.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.*;

public class Door extends Entity{

    public Door(Color color, int x, int y, int width, int height, Game game){
        super(color, x, y, width, height, game);

        //Color doorColor = new Color (153, 76, 0); CUSTOM COLOR IS BEING A BUTTHOLE
    }

    public boolean collides(Entity other){

    }

    public void paint(Graphics g){
        g.setColor(this.getColor());
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
