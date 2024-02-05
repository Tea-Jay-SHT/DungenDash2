/**
 * @author tkelly
 * @createdOn 1/29/2024 at 10:50 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.controller;
 */
package com.wedontgetpaidenough.dungendash2.controller;

import com.wedontgetpaidenough.dungendash2.model.GameState;

public class SpecialEventController {
    private GameState state;
    public SpecialEventController(GameState state){
        this.state = state;
    }
    public void specialEvents(String event){
        switch(event){
            case "none":
                break;
            case "boom":
                System.out.println("boom");
                break;
            case "crash":
                System.exit(0);
        }
    }
}
