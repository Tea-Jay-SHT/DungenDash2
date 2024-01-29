/**
 * @author tkelly
 * @createdOn 1/29/2024 at 10:50 AM
 * @projectName DungenDash2Redux
 * @packageName com.wedontgetpaidenough.dungendash2.controller;
 */
package com.wedontgetpaidenough.dungendash2.controller;

public class SpecialEventController {
    public SpecialEventController(){

    }
    public void specialEvents(String event){
        switch(event){
            case "boom":
                System.out.println("boom");
                break;
        }
    }
}
