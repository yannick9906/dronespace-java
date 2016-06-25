package tk.yannickfelix.dronespace.entities;

import java.util.Locale;

/**
 * Created by yanni on 24.06.2016.
 */
public class Entity {
    public enum EntityType {DRONE, DOOR, ROOM, INFOTAB, CHARGINGPOT}
    protected int entityID;
    protected EntityType entityType;

    public Entity(int entityID, EntityType entityType) {
        this.entityID = entityID;
        this.entityType = entityType;
    }

    public String getInfo() {
        return "Entity";
    }

    public String getDetailedInfo() {
        return "Entity";
    }

    public int getEntityID() {
        return entityID;
    }

    public void setEntityID(int entityID) {
        this.entityID = entityID;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public boolean isEntityType(EntityType entityType) {
        return this.entityType == entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }
}

