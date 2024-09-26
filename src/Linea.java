import java.awt.Graphics;


public class Linea extends Trazo {
    public Linea(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawLine(x1, y1, x2, y2);
    }
}