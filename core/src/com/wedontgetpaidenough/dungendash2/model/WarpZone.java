/**
 * @author tkelly
 * @createdOn 1/28/2024 at 11:54 PM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.model;
 */
package com.wedontgetpaidenough.dungendash2.model;

import java.awt.*;

public class WarpZone {
    Point spawnLocation;
    String destanation;

    public WarpZone(Point spawnLocation, String destanation) {
        this.spawnLocation = spawnLocation;
        this.destanation = destanation;
    }
    public Point getSpawnLocation() {
        return spawnLocation;
    }
    public String getDestanation() {
        return destanation;
    }
}