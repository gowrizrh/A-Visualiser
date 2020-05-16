import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainWindow extends JFrame {

    private JPanel mainPanel = new JPanel();
    private JPanel sideBar = new JPanel();
    private JPanel renderArea = new JPanel();

    private Canvas canvas = new Canvas();

    public MainWindow(String _title) {
        setTitle(_title);
        mainPanel.setLayout(new GridBagLayout());
        prePack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(600, 400));
        setMaximumSize(new Dimension(900, 600));
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public void prePack() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        sideBar.setBackground(Color.RED);
        mainPanel.add(sideBar, c);

        c.gridx = 1;
        c.gridy = 0;
        canvas.setSize(400, 400);
        canvas.setBackground(Color.white);
        renderArea.add(canvas);
        renderArea.setBackground(Color.YELLOW);
        mainPanel.add(renderArea, c);
        mainPanel.setBackground(Color.blue);
    }
}
