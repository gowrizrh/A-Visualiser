import javax.swing.*;
import java.awt.*;

public class JCanvas extends JPanel {

    public JCanvas() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("My Panel", 10, 20);
        g.setColor(Color.WHITE);

        g.fillRect(0, 0, 400, 400);
    }
}
