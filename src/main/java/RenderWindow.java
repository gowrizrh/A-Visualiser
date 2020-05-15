import javax.swing.*;

public class RenderWindow extends JFrame {
    private JButton generateMap;
    private JPanel mainPanel;
    private JCanvas JCanvas1;

    public RenderWindow(String _title) {
        super(_title);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    public void render() {
        System.out.println("Render call");
    }
}
