package tk.yannickfelix.dronespace.entities.drones;

import tk.yannickfelix.dronespace.gui.iGUI;

import java.util.Locale;

/**
 * Created by yanni on 24.06.2016.
 */
public class DroneScout extends Drone {
    public DroneScout(int droneID, float maxHealth, float maxCargo, float maxEnergy, float baseWeight, float baseEnergyDraw, float damage, String name, iGUI gInterface) {
        super(droneID, maxHealth, maxCargo, maxEnergy, baseWeight, baseEnergyDraw, damage, name, gInterface);
    }

    public String getDetailedInfo() {
        return String.format(new Locale("en"),
                "Name:    <b>%1$s</b>\n" +
                "Typ:     Scout\n" +
                "Energie: %2$6.1f/%3$6.1fEU (%4$.2f EU/s)\n" +
                "Cargo:   %5$6.1f/%6$6.1fl  (%7$6.1fkg + %8$6.1fkg)\n" +
                "Health:  %9$6.1f/%10$6.1fHP (Damage: %11$02.1f)\n" +
                "%12$s @ %13$s",
                name, currEnergyLevel, maxEnergy,
                baseEnergyDraw+currEnergyConsumption,
                currCargoSize, maxCargo, baseWeight, 0f,
                currHealth, maxHealth, damage,
                "?", "?");
    }
}
