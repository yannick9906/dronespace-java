package tk.yannickfelix.dronespace.cmds;

import tk.yannickfelix.dronespace.actions.ActionHandler;

/**
 * Created by yanni on 24.06.2016.
 */
public class Command {
    private boolean hasArgs = false;
    private String command = "";

    public Command(String command) {
        if(command.contains("*")) {
            this.command = command.replace("*","".replace(" ", "")).toLowerCase();
            hasArgs = true;
        } else {
            this.command = command;
        }
    }

    public boolean isThisCommand(String cmd) {
        if(hasArgs) {
            String[] args = cmd.split(" ");
            //Todo set arg action
            cmd = cmd.replace(" ", "".toLowerCase());
            return cmd.startsWith(command);
        } else {
            return command.equals(cmd) || command.startsWith(cmd) || cmd.startsWith(command);
        }
    }

    public void runCommand(ActionHandler ac) {
        //todo actionhandler
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
