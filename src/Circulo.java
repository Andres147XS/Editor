import java.awt.Graphics;

public class Circulo extends Trazo {
    public Circulo(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void dibujar(Graphics g) {
        int radio = (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        g.drawOval(x1 - radio, y1 - radio, 2 * radio, 2 * radio);
    }
}
