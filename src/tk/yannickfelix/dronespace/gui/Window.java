package tk.yannickfelix.dronespace.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yanni on 23.06.2016.
 */
public class Window extends JFrame implements iGUI {

    private boolean isFullscreen = false;
    private int sizeX, sizeY;
    private int timeSinceLastFrame = 0;
    private int lastFrame = 0, frameCount = 0, frameCountStart = 0, lastFrameCount = 0;

    public GameConsole gameConsole;
    public GameInput gameInput;
    public JScrollPane scrollPane;

    /**
     * @param title String
     * @param sizeX int
     * @param sizeY int
     */
    public Window(String title, int sizeX, int sizeY) {
        super();
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.setTitle(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        scrollPane = new JScrollPane();
        gameConsole = new GameConsole();
        gameInput = new GameInput();

        gameInput.setBounds(-20,-20,1,1);

        this.add(gameInput);
        this.add(gameConsole);
        this.setSize(sizeX, sizeY);
        this.setVisible(true);

        gameConsole.writeTick(this.getWidth());
        gameConsole.printMessage("Testing testing.", "left", "Testy the Tester", false, true, true);
        gameConsole.printMessage("Testing testing.", "right", "Testy the Tester", false, true, true);
        gameConsole.printMessage("Testing testing.", "center", "Testy the Tester", false, true, true);
    }

    @Override
    public void update() {
        gameConsole.writeTick(this.getWidth());
        gameConsole.updateInputting(gameInput.getCurrentText(), this.getWidth());
        gameInput.update();
    }

    @Override
    public void printMessage(String text, String side, String name, boolean autowrap, boolean writing, boolean newline) {
        gameConsole.printMessage(text, side, name, autowrap, writing, newline);
    }

    @Override
    public String getUserText() {
        return gameInput.getUserText();
    }
}
