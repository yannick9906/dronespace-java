package tk.yannickfelix.dronespace;

import tk.yannickfelix.dronespace.entities.Entity;
import tk.yannickfelix.dronespace.entities.drones.Drone;
import tk.yannickfelix.dronespace.entities.drones.DroneTransport;
import tk.yannickfelix.dronespace.gui.iGUI;

import java.util.ArrayList;

/**
 * Created by yanni on 23.06.2016.
 */
public class EntitiesController {
    private ArrayList<Entity> entities = new ArrayList<>();
    private int selDrone = -1;
    private iGUI gui;

    public EntitiesController(iGUI gui) {
        this.gui = gui;
        entities.add(new DroneTransport(0, 1000, 1000, 450, 20, -0.01f, 0.1f, "Dronezius", gui));
        entities.add(new DroneTransport(1, 1000, 1000, 450, 20, -0.01f, 0.1f, "Dronezius", gui));
    }

    private void load() {
        //Load json and give it to the EntitiesFactory
    }

    public void update() {

    }

    public Entity get(int id) {
        return entities.get(id);
    }

    public ArrayList<Entity> getByType(Entity.EntityType type) {
        ArrayList<Entity> entities = new ArrayList<>();
        for(Entity entity : this.entities) {
            if(entity.isEntityType(type)) {
                entities.add(entity);
            }
        }
        return entities;
    }

    public boolean handleCmd(String cmd) {

        if(cmd.equals("drones")) {
            ArrayList<Entity> drones = getByType(Entity.EntityType.DRONE);
            gui.printMessage("<b>Available drones:</b>", "left", "", false, true, true);
            if(drones.size() > 0) {
                for (Entity drone : drones) {
                    gui.printMessage(drone.getInfo(), "left", "", false, true, false);
                }
                gui.printMessage("Type in <i>\"drone sel {droneid}\"</i> to select a drone", "left", "", false, true, true);
            } else {
                gui.printMessage("<i>No drones available</i>\nActually, that's very sad...", "left", "", false, true, true);
            }
            return true;
        } else if(cmd.startsWith("drone sel")) {
            int requestedID = Integer.parseInt(cmd.split(" ")[2]);
            ArrayList<Entity> drones = getByType(Entity.EntityType.DRONE);
            if(requestedID >= 0 && requestedID < drones.size()) {
                selDrone = requestedID;
                gui.printMessage("<b>Selected Drone</b> <i>"+drones.get(selDrone).getEntityID()+"</i>", "left", "", false, true, true);
            } else {
                gui.printMessage("I'm sorry. I simply can't select this drone.", "left", "", false, true, true);
            }
            return true;
        } else if(cmd.startsWith("drone ")) {
            if(selDrone >= 0) {
                ArrayList<Entity> drones = getByType(Entity.EntityType.DRONE);
                Drone drone = (Drone) drones.get(selDrone);
                try {
                    drone.handleCmd(cmd);
                } catch(Exception e) {
                    gui.printMessage("kbIONUEJBNÖUFbojndjfnsöoiehfnjnFÖJenöIWföonföo\nifhpöwsebnföjusbndfjgnkgfmwsnfosökjengöike\nsegjuujsbpguebnpgbnepugbnpeu", "left", "", false, true, true);
                }
            } else {
                gui.printMessage("You have to select a drone before doing this.", "left", "", false, true, true);
            }
            return true;
        } else if(cmd.equals("interact")) {

            return true;
        } else if(cmd.startsWith("interact ")) {

            return true;
        } else return false;
    }
}
