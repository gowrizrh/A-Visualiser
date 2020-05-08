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


    public Window() {
        super();
        frame = new Frame();
        canvas = new Canvas();
        frame.setSize(800, 800);
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
    }

    public void run() {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}
