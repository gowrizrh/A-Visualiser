import core.Cell;
import core.Map;
import utils.MapParser;
import utils.Palette;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

public class MainWindow extends JFrame {

    private JPanel mainPanel = new JPanel();
    private JPanel sideBar = new JPanel();
    private JPanel renderArea = new JPanel();
    private Canvas canvas = new Canvas();
    private BufferStrategy strategy = null;
    private Graphics graphics = null;

    // Render necessaries
    private Map world;
    private int canvasHeight = 0;
    private int canvasWidth = 0;

    // State holders
    private Cell start = null;
    private Cell goal = null;

    public MainWindow(String _title) {
        setTitle(_title);
        setLayout(new FlowLayout());
        initComponents();
        setContentPane(mainPanel);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        postPack();
        world = MapParser.parse("src/main/resources/grid6.txt");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        render();
    }

    public void initComponents() {
        JButton loadMap = new JButton("Find Path");
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
        canvas.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && (start == null)) {
                    int gridUnit = canvasWidth / world.cols();
                    int x = e.getY() / gridUnit;
                    int y = e.getX() / gridUnit;
                    world.value(x, y, 6);
                    start = world.cell(x, y);
                } else if (SwingUtilities.isRightMouseButton(e) && (goal == null)) {
                    int gridUnit = canvasWidth / world.cols();
                    int x = e.getY() / gridUnit;
                    int y = e.getX() / gridUnit;
                    world.value(x, y, 9);
                    goal = world.cell(x, y);
                }

                render();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public void render() {
        int gridUnit = canvasWidth / world.cols();
        int gridUnitY = canvasHeight / world.rows();
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
                        graphics.fillRect(j * gridUnit + 3,i * gridUnitY + 3, gridUnit - 3, gridUnitY - 3);
                        switch (gridCase) {
                            case 0:
                                graphics.setColor(Palette.white);
                                break;
                            case 1:
                                graphics.setColor(Palette.black);
                                break;
                            case 6:
                                graphics.setColor(Palette.yellow);
                                break;
                            case 9:
                                graphics.setColor(Palette.blue);
                                break;
                            default:
                                graphics.setColor(Color.white);
                                break;
                        }
                        graphics.fillRect(j * gridUnit + 3 , i * gridUnitY + 3, gridUnit - 3, gridUnitY - 3);
                    }
                }
            } while (strategy.contentsRestored());
            strategy.show();
            Toolkit.getDefaultToolkit().sync();
        } while (strategy.contentsLost());
    }
}
