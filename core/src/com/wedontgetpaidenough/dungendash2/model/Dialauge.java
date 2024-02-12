/**
 * @author tkelly
 * @createdOn 1/28/2024 at 10:46 PM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.model;
 */
package com.wedontgetpaidenough.dungendash2.model;

import com.badlogic.gdx.utils.JsonValue;
import com.wedontgetpaidenough.dungendash2.model.enums.State;

import java.util.ArrayList;

public class Dialauge {
    private GameState state;
    private int iterator = 0;
    private ArrayList<String> talkingSprites = new ArrayList<>();
    private ArrayList<String> text = new ArrayList<>();
    private ArrayList<String> eventFunctions = new ArrayList<>();
    private String eventFunction;
    public Dialauge(JsonValue dialaugeData,GameState state){
        for(JsonValue jason: dialaugeData){
            talkingSprites.add(jason.getString("talkingSprite"));
            text.add(jason.getString("text"));
            if (jason.has("eventFunction")){
                eventFunctions.add(jason.getString("eventFunction"));
            }else{
                eventFunctions.add("none");
            }
        }
        this.state = state;
    }
    public ArrayList<String> getSprites(){return talkingSprites;}
    public ArrayList<String> getText(){return text;}
    public int getIterator(){return iterator;}
    public void next() {
        iterator += 1;
        state.getEventController().specialEvents(eventFunctions.get(iterator-1));
        if(iterator == talkingSprites.size()){
            state.setGameState(State.Playing);
            iterator -=1;
        }
    }
}
