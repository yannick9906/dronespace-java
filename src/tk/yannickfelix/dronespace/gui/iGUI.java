package tk.yannickfelix.dronespace.gui;

import java.awt.event.KeyListener;

/**
 * Created by yanni on 23.06.2016
 */
public interface iGUI {
    void printMessage(String text, String side, String name, boolean autowrap, boolean writing, boolean newline);
    String getUserText();
    void setUserText(String text);
    boolean hasUserTypedSomething();
    void removeLastLine();
    void addText(String text);
    void update();
    void addKeyListener(KeyListener l);
}
