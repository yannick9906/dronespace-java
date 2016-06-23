package tk.yannickfelix.dronespace.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by yanni on 23.06.2016.
 */
public class GameInput extends JTextField {
    private String lastString;

    public GameInput() {
        super();
        super.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lastString = GameInput.super.getText();
                clearUserText();
            }
        });
    }

    public boolean hasUserTypedSomething() {
        return !lastString.equals("");
    }

    public String getUserText() {
        String string = lastString;
        lastString = "";
        return string;
    }

    public String getCurrentText() {
        return super.getText();
    }

    public void clearUserText() {
        super.setText("");
    }

    public void set(String text) {
        super.setText(text);
    }

    public void update() {
        super.grabFocus();
    }
}
