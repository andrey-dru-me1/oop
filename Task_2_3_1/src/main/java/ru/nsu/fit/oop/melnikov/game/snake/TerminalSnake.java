package ru.nsu.fit.oop.melnikov.game.snake;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.TextColor;
import java.io.IOException;

public class TerminalSnake {

  public static void main(String[] args) throws IOException {
    Terminal terminal = new DefaultTerminalFactory().createTerminal();
    Screen screen = new TerminalScreen(terminal);
    Panel panel = new Panel();
    BasicWindow window = new BasicWindow();
    window.setComponent(panel);
    MultiWindowTextGUI gui =
        new MultiWindowTextGUI(
            screen, new DefaultWindowManager(), new EmptySpace(TextColor.ANSI.BLUE));
    gui.addWindowAndWait(window);
  }
}
