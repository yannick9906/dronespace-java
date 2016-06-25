package tk.yannickfelix.dronespace.gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/**
 * Created by yanni on 23.06.2016.
 */
public class Window extends JFrame implements iGUI {

    private boolean isFullscreen = false;
    private int sizeX, sizeY;
    private int timeSinceLastFrame = 0;
    private double lastFrame = System.nanoTime(), fps = 60;

    private JLayeredPane pane;
    public GameConsole gameConsole;
    public GameInput gameInput;
    public JScrollPane scrollPane;
    public JLabel fpsDisplay;

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
        pane = new JLayeredPane();
        this.add(pane);
        pane.setLayout(new BorderLayout());

        gameConsole = new GameConsole();
        gameInput = new GameInput();
        fpsDisplay = new JLabel();
        scrollPane = new JScrollPane(gameConsole);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        gameInput.setBounds(-20,-20,1,1);
        fpsDisplay.setBounds(0,0,100,20);
        fpsDisplay.setBackground(Color.BLACK);
        fpsDisplay.setForeground(Color.GREEN);
        fpsDisplay.setFont(new Font("Courier New", Font.PLAIN, 14));

        pane.add(gameInput, BorderLayout.CENTER);
        pane.add(scrollPane, BorderLayout.CENTER);
        //pane.add(fpsDisplay, BorderLayout.NORTH);
        //pane.setLayer(gameInput, -1);
        pane.setLayer(scrollPane, 0);
        //pane.setLayer(fpsDisplay, 1);
        setContentPane(pane);
        this.setSize(sizeX, sizeY);
        this.setVisible(true);

        gameConsole.writeTick(this.getWidth());
    }

    @Override
    public void update() {
        try {
            SwingUtilities.invokeAndWait(() -> {
                gameConsole.updateInputting(gameInput.getCurrentText(), this.getWidth());
                scrollPane.setBounds(0,0,super.getWidth()+10,super.getHeight());
                //gameConsole.setBounds(0,0,super.getWidth(),super.getHeight());
                gameConsole.writeTick(this.getWidth());
                gameInput.update();

                fps = 1000000000f / (float) (System.nanoTime() - lastFrame);
                lastFrame = System.nanoTime();
                if(System.currentTimeMillis() % 1000 <= 50) fpsDisplay.setText(String.format(new Locale("en"), "%1$.2fF/s", fps));
                pane.setLayer(fpsDisplay, 100);
            });
        } catch(InvocationTargetException | InterruptedException e) {}
    }

    @Override
    public void printMessage(String text, String side, String name, boolean autowrap, boolean writing, boolean newline) {
        gameConsole.printMessage(text, side, name, autowrap, writing, newline);
    }

    @Override
    public String getUserText() {
        return gameInput.getUserText();
    }

    @Override
    public void setUserText(String text) {
        gameInput.set(text);
    }

    @Override
    public boolean hasUserTypedSomething() {
        return gameInput.hasUserTypedSomething();
    }

    @Override
    public void removeLastLine() {
        gameConsole.removeLastLine();
    }

    @Override
    public void addText(String text) {
        gameConsole.addText(text);
    }

    @Override
    public void addKeyListener(KeyListener l) {
        gameInput.addKeyListener(l);
    }
}
