package tk.yannickfelix.dronespace;

import tk.yannickfelix.dronespace.gui.Window;
import tk.yannickfelix.dronespace.gui.iGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by yanni on 23.06.2016.
 */
public class GameMasterController{
    private ArrayList globalvars;
    private ArrayList<String> issuedCommands = new ArrayList<>();
    private int selOldCommand = -1;

    private EntitiesController entitiesController;

    private iGUI gui;

    public GameMasterController() {
        gui = new Window("dronespace", 1024, 576);
        entitiesController = new EntitiesController(gui);

        gui.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0);
                        break;
                    case KeyEvent.VK_F11:
                        //todo fullscreen
                        break;
                    case KeyEvent.VK_UP:
                        if(selOldCommand == -1 && issuedCommands.size() != 0) {
                            selOldCommand = issuedCommands.size() -1;
                            gui.setUserText(issuedCommands.get(selOldCommand));
                        } else if(issuedCommands.size() > 0) {
                            if(selOldCommand-1 >= 0) selOldCommand--;
                            gui.setUserText(issuedCommands.get(selOldCommand));
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(selOldCommand > -1) {
                            if(selOldCommand+1 < issuedCommands.size()) {
                                selOldCommand++;
                                gui.setUserText(issuedCommands.get(selOldCommand));
                            } else {
                                gui.setUserText("");
                                selOldCommand = -1;
                            }
                        }
                }
            }

            public void keyPressed(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

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
            gui.addText("\n");
            gui.printMessage(cmd, "right","",false, false, false);
            issuedCommands.add(cmd);
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
