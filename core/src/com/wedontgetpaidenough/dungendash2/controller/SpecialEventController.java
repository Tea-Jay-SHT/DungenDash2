/**
 * @author tkelly
 * @createdOn 1/29/2024 at 10:50 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.controller;
 */
package com.wedontgetpaidenough.dungendash2.controller;

import com.wedontgetpaidenough.dungendash2.model.GameState;
import com.wedontgetpaidenough.dungendash2.model.WarpZone;
import com.wedontgetpaidenough.dungendash2.model.enums.State;

import java.awt.*;

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
            case "0TitleSelection":
                state.setGameState(State.Playing);
                state.switchMap(new WarpZone(new Point(512,512),"test"));
                state.getAudioController().swapMusic();
                break;
            case "1TitleSelection": // load
                break;
            case "2TitleSelection":  //settings
                break;
            case "3TitleSelection":  //quit
                System.exit(0);
        }
    }
}
