package tk.yannickfelix.dronespace.gui;

/**
 * Created by yanni on 23.06.2016.
 */
public interface iGUI {
    void printMessage(String text, String side, String name, boolean autowrap, boolean writing, boolean newline);
    String getUserText();
    void update();
}
