package minefield;

import mvc.*;

public class MineFieldFactory implements AppFactory{

    //Will return errors until MineField is made
    public Model makeModel() { return new MineField(); }

    public View makeView(Model m) {
        return new MineFieldView((Minefield)m);
    }

    public String[] getEditCommands() {
        return new String[] {"North", "South", "East", "West", "Northwest", "Southwest", "Northeast", "Southeast"};
    }

    // source added 3/15 to support text fields
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type.equals("North"))
            return new NorthCommand(model);
        else if (type.equals("South"))
            return new SouthCommand(model);
        else if (type.equals("East"))
            return new EastCommand(model);
        else if (type.equals("West"))
            return new WestCommand(model);
        else if (type.equals("Northwest"))
            return new NorthwestCommand(model);
        else if (type.equals("Northeast"))
            return new NortheastCommand(model);
        else if (type.equals("Southwest"))
            return new SouthwestCommand(model);
        else if (type.equals("Southeast"))
            return new SoutheastCommand(model);
        return null;
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
