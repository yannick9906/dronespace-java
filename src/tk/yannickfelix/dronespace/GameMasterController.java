package tk.yannickfelix.dronespace;

import tk.yannickfelix.dronespace.gui.Window;
import tk.yannickfelix.dronespace.gui.iGUI;

import java.util.ArrayList;

/**
 * Created by yanni on 23.06.2016.
 */
public class GameMasterController {
    private ArrayList globalvars;
    private ArrayList<String> issuedCommands;
    private int selOldCommand = -1;

    private iGUI gui;

    public GameMasterController() {
        gui = new Window("dronespace", 1024, 576);
        while(true) {
            update();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {}
        }
    }

    public void update() {
        gui.update();
    }

    public void handleCmd(String cmd) {

    }

    public static void main(String[] args) {
        GameMasterController gc = new GameMasterController();
    }
}
