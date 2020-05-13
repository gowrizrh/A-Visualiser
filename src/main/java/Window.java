//
// Copyright (C) 2020 Gowri <gowrizrh@outlook.com>
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <https://www.gnu.org/licenses/>.

import core.Cell;
import core.Map;
import utils.MapParser;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

public class Window extends WindowAdapter implements WindowListener {

    private Frame frame = null;
    private Canvas canvas = null;
    private Graphics graph = null;
    private BufferStrategy strategy = null;
    private Starvis starvis = null;

    private Map world;
    private MapParser parser;

    public final static Color white = new Color(228, 214, 167);
    public final static Color black = new Color(25, 25, 35);
    public final static Color blue = new Color(14, 121, 178);
    public final static Color red = new Color(191, 19, 99);
    public final static Color green = new Color(29, 211, 176);
    public final static Color yellow = new Color(239, 202, 8);

    public final static int EMPTY = 0;
    public final static int WALL = 1;
    public final static int EXPLORED = 2;
    public final static int CLOSED = 5;
    public final static int START = 6;
    public final static int TARGET = 7;
    public final static int PATH = 8;
    public final static int MARK = 9;

    private int gridx;
    private int gridy;

    public Window() {
        super();
        parser = new MapParser();
        starvis = new Starvis();
        frame = new Frame();
        canvas = new Canvas();
        frame.setSize(805, 825);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        canvas.setSize(800, 800);
        frame.add(canvas);
        frame.addWindowListener(this);
        frame.dispose();
        frame.validate();
        frame.setTitle("A* Visualisation");
        frame.setVisible(true);
        canvas.setIgnoreRepaint(true);
        canvas.setBackground(Color.WHITE);
        canvas.createBufferStrategy(2);
        strategy = canvas.getBufferStrategy();
        graph = strategy.getDrawGraphics();
        initGrid();
        render();
    }

    public void initGrid() {
        world = parser.parse("src/main/resources/grid9.txt");
        gridx = world.x();
        gridy = world.y();
    }

    public void run() {
        starvis.find(new Cell(5, 7), new Cell(0, 0), world);
//        while (true) {
//            render();
//        }
    }

    public void render() {
        int gridUnit = 800 / gridx;
        int gridUnitY = 800 / gridy;
        canvas.paint(graph);
        do {
            do {
                graph = strategy.getDrawGraphics();
                graph.setColor(Color.WHITE);
                graph.fillRect(0, 0, 800, 800);
                int gridCase = EMPTY;
                for (int i = 0; i < gridx; i++) {
                    for (int j = 0; j < gridy; j++) {
                        gridCase = world.value(i, j);
                        graph.setColor(white);
                        graph.fillRect(j * gridUnit + 3,i * gridUnitY + 3, gridUnit - 3, gridUnitY - 3);
                        switch (gridCase) {
                            case WALL:
                                graph.setColor(black);
                                graph.fillRect(j * gridUnit + 3 , i * gridUnitY + 3, gridUnit - 3, gridUnitY - 3);
                                break;
                            case EXPLORED:
                                graph.setColor(green);
                                graph.fillRect(j * gridUnit + 3 , i * gridUnitY + 3, gridUnit - 3, gridUnitY - 3);
                                break;
                            case CLOSED:
                                graph.setColor(red);
                                graph.fillRect(j * gridUnit + 3, i * gridUnitY + 3, gridUnit - 3, gridUnitY - 3);
                                break;
                            case TARGET:
                            case START:
                            case PATH:
                                graph.setColor(yellow);
                                graph.fillRect(j * gridUnit + 3, i * gridUnitY + 3, gridUnit - 3, gridUnitY - 3);
                                break;
                            case MARK:
                                graph.setColor(blue);
                                graph.fillRect(j * gridUnit + 3, i * gridUnitY + 3, gridUnit - 3, gridUnitY - 3);
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

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
