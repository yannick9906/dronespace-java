package tk.yannickfelix.dronespace.entities.drones;

import tk.yannickfelix.dronespace.actions.ActionHandler;
import tk.yannickfelix.dronespace.actions.DroneActionHandler;
import tk.yannickfelix.dronespace.cmds.Command;
import tk.yannickfelix.dronespace.entities.Entity;
import tk.yannickfelix.dronespace.gui.iGUI;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by yanni on 24.06.2016.
 * @license
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International
 * License. To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 * @author Yannick Félix
 */
public class Drone extends Entity {
    protected int currRoom, currEntity;
    protected String name;
    protected float maxHealth, damage, maxCargo, maxEnergy, baseWeight, baseEnergyDraw;
    protected float currHealth, currCargoSize, currEnergyLevel, currEnergyConsumption;
    protected double currEnergyEnd;
    protected ArrayList<Command> commands;

    protected ActionHandler ac;
    protected iGUI gInterface;

    public Drone(int droneID, float maxHealth, float maxCargo, float maxEnergy, float baseWeight, float baseEnergyDraw, float damage, String name, iGUI gInterface) {
        super(droneID, EntityType.DRONE);
        this.maxHealth = maxHealth;
        this.maxCargo = maxCargo;
        this.maxEnergy = maxEnergy;
        this.baseWeight = baseWeight;
        this.baseEnergyDraw = baseEnergyDraw;
        this.damage = damage;
        this.name = name;
        this.ac = new DroneActionHandler();
        this.gInterface = gInterface;
    }

    public String[] getCmd() {
        String[] array = new String[commands.size()];
        for(int i = 0; i < commands.size(); i++) {
            array[i] = commands.get(i).getCommand();
        }
        return array;
    }

    public void takeDamage(float amount) {
        currHealth -= amount;
        if(currHealth > 0) {
            //Todo notify
        } else {
            if(currHealth < 0) takeDeed(amount);
            currHealth = 0;
        }
    }

    public void takeDeed(float amount) {
        //Todo notify
    }

    public void move(float distance) {

    }

    public void update() {
        updateEnergy();
    }

    public String getInfo() {
        return String.format("%1$03d: %2$s (%3$.0fEU; %4$.0fl)", entityID, name, currEnergyLevel, currCargoSize);
    }

    public String getDetailedInfo() {
        return String.format(new Locale("en"),
                "Name:    <b>%1$s</b>\n" +
                "Typ:     None\n" +
                "Energie: %2$6.1f/%3$6.1fEU (%4$.2f EU/s)\n" +
                "Cargo:   %5$6.1f/%6$6.1fl  (%7$6.1fkg + %8$6.1fkg)\n" +
                "Health:  %9$6.1f/%10$6.1fHP (Damage: %11$02.1f)\n",
                name, currEnergyLevel, maxEnergy,
                baseEnergyDraw+currEnergyConsumption,
                currCargoSize, maxCargo, baseWeight, 0f,
                currHealth, maxHealth, damage);
    }

    public boolean handleCmd(String cmd) {
        cmd = cmd.replace("drone ","");
        for(Command command : commands) {
            if(command.isThisCommand(cmd)) {
                command.runCommand(ac);
                return true;
            }
        }
        return false;
    }

    public void startEnergyConsumption(float rate, int ms) {
        currEnergyConsumption = rate;
        currEnergyEnd = System.currentTimeMillis() + ms;
    }

    public void updateEnergy() {
        if(System.currentTimeMillis() >= currEnergyEnd) currEnergyConsumption = 0;

        if(currEnergyLevel > 0) currEnergyLevel += (currEnergyConsumption + baseEnergyDraw) / 1; //Todo Fps

        if(currEnergyLevel >= maxEnergy) currEnergyLevel = maxEnergy;

        if(currEnergyLevel < 0) {
            currEnergyLevel = 0;
            //Todo Notify
        }
    }

    public int getCurrRoom() {
        return currRoom;
    }

    public void setCurrRoom(int currRoom) {
        this.currRoom = currRoom;
    }

    public int getCurrEntity() {
        return currEntity;
    }

    public void setCurrEntity(int currEntity) {
        this.currEntity = currEntity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getMaxCargo() {
        return maxCargo;
    }

    public void setMaxCargo(float maxCargo) {
        this.maxCargo = maxCargo;
    }

    public float getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(float maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public float getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(float baseWeight) {
        this.baseWeight = baseWeight;
    }

    public float getBaseEnergyDraw() {
        return baseEnergyDraw;
    }

    public void setBaseEnergyDraw(float baseEnergyDraw) {
        this.baseEnergyDraw = baseEnergyDraw;
    }

    public float getCurrHealth() {
        return currHealth;
    }

    public void setCurrHealth(float currHealth) {
        this.currHealth = currHealth;
    }

    public float getCurrCargoSize() {
        return currCargoSize;
    }

    public void setCurrCargoSize(float currCargoSize) {
        this.currCargoSize = currCargoSize;
    }

    public float getCurrEnergyLevel() {
        return currEnergyLevel;
    }

    public void setCurrEnergyLevel(float currEnergyLevel) {
        this.currEnergyLevel = currEnergyLevel;
    }

    public float getCurrEnergyConsumption() {
        return currEnergyConsumption;
    }

    public void setCurrEnergyConsumption(float currEnergyConsumption) {
        this.currEnergyConsumption = currEnergyConsumption;
    }

    public double getCurrEnergyEnd() {
        return currEnergyEnd;
    }

    public void setCurrEnergyEnd(double currEnergyEnd) {
        this.currEnergyEnd = currEnergyEnd;
    }

    public ActionHandler getAc() {
        return ac;
    }

    public void setAc(ActionHandler ac) {
        this.ac = ac;
    }


}
