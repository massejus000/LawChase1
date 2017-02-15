/**
 * Created by massejus000 on 2/13/2017.
 */

import java.awt.Color;
import java.awt.Graphics;

public class Sugar extends Entity {
    public Sugar(Color color, int x, int y, int width, int height, Game game) {
        super(color, x, y, width, height, game);
    }

    public boolean collides(Entity other){
        return getBounds().intersects(other.getBounds());
    }

    public void paint(Graphics g) {
        g.setColor(this.getColor());
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}
