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

    private EntitiesController entitiesController;

    private iGUI gui;

    public GameMasterController() {
        gui = new Window("dronespace", 1024, 576);
        entitiesController = new EntitiesController(gui);

        while(true) {
            update();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {}
        }
    }

    public void update() {
        if(gui.hasUserTypedSomething()) {
            String cmd = gui.getUserText();
            gui.removeLastLine();
            gui.printMessage(cmd, "right","",false, false, false);
            handleCmd(cmd);
        }
        gui.update();
    }

    public void handleCmd(String cmd) {
        cmd = cmd.toLowerCase();
        entitiesController.handleCmd(cmd);
    }

    public static void main(String[] args) {
        GameMasterController gc = new GameMasterController();
    }
}
