import core.Map;
import utils.MapParser;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;

public class MainWindow extends JFrame {

    private JPanel mainPanel = new JPanel();
    private JPanel sideBar = new JPanel();
    private JPanel renderArea = new JPanel();
    private Canvas canvas = new Canvas();
    private BufferStrategy strategy = null;
    private Graphics graphics = null;

    private int canvasHeight = 0;
    private int canvasWidth = 0;

    // Render necessaries
    private Map world;

    public MainWindow(String _title) {
        setTitle(_title);
        setLayout(new FlowLayout());
        initComponents();
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        postPack();
        world = MapParser.parse("src/main/resources/grid1.txt");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        render();
    }

    public void initComponents() {
        JButton loadMap = new JButton("Reload Canvas");
        loadMap.addActionListener(e -> render());
        sideBar.add(loadMap);
        sideBar.setPreferredSize(new Dimension(300, 600));
        mainPanel.add(sideBar);
        renderArea.setPreferredSize(new Dimension(600, 600));
        mainPanel.add(renderArea);
    }

    public void postPack() {
        canvasWidth = renderArea.getWidth();
        canvasHeight = renderArea.getHeight();
        canvas.setSize(canvasWidth, canvasHeight);
        renderArea.add(canvas);
        canvas.setBackground(Color.white);
        canvas.setIgnoreRepaint(true);
        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
        graphics = strategy.getDrawGraphics();
    }

    public void render() {
        int gridUnit = 10;
        int gridUnitY = 10;
        canvas.paint(graphics);
        do {
            do {
                graphics = strategy.getDrawGraphics();
                graphics.setColor(Color.white);
                graphics.fillRect(0, 0, canvasWidth, canvasHeight);

                int gridCase = 0;
                for (int i = 0; i < world.rows(); i++) {
                    for (int j = 0; j < world.cols(); j++) {
                        gridCase = world.value(i, j);
                        graphics.setColor(Color.white);
                        graphics.fillRect(j * gridUnit + 3,i * gridUnitY + 3, gridUnit - 3, gridUnitY - 3);
                        switch (gridCase) {
                            case 1:
                                graphics.setColor(Color.BLACK);
                                graphics.fillRect(j * gridUnit + 3 , i * gridUnitY + 3, gridUnit - 3, gridUnitY - 3);
                                break;
                            default:
                                break;
                        }
                    }
                }
            } while (strategy.contentsRestored());
            strategy.show();
            Toolkit.getDefaultToolkit().sync();
        } while (strategy.contentsLost());
    }
}
