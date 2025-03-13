package minefield;

import mvc.*;

public class MineFieldFactory implements AppFactory{

    //TODO REPLACE MODEL WITH MINEFIELDMODEL
    public Model makeModel() { return new MineField(); }

    public View makeView(Model m) {
        return new MineFieldView((MineField)m);
    }

    public String[] getEditCommands() {
        return new String[] {"North", "South", "East", "West", "Northwest", "Southwest", "Northeast", "Southeast"};
    }

    // source added 3/15 to support text fields
    public Command makeEditCommand(Model model, String type, Object source) {
        return new MoveCommand(model, type, source);
    }

    public String getTitle() { return "Minefield Game"; }

    public String[] getHelp() {
        return new String[] {"Move to the green patch using the movement buttons",
                "Each patch you step on tells you how many mines are in neighbor patches",
                "If you step on the mine, the game is over"};
    }

    public String about() {
        return "Minefield version 1.0. Copyright 2025. Designed by Vidit Chavarkar, Jesse Mendoza, Peter Vo";
    }

}
