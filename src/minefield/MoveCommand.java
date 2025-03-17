package minefield;

import mvc.*;

public class MoveCommand extends Command {

    MineField model;
    String type;
    Object source;

    public MoveCommand(Model model, String type, Object source){
        super(model);
        this.model = (MineField) model;
        this.type = type;
        this.source = source;
    }

    public void execute() {
        try {
            switch (type) {
                case "South":
                    model.moveSouth();
                    break;
                case "North":
                    model.moveNorth();
                    break;
                case "East":
                    model.moveEast();
                    break;
                case "West":
                    model.moveWest();
                    break;
                case "Northeast":
                    model.moveNortheast();
                    break;
                case "Northwest":
                    model.moveNorthwest();
                    break;
                case "Southeast":
                    model.moveSoutheast();
                    break;
                case "Southwest":
                    model.moveSouthwest();
                    break;
            }
        } catch (Exception e) {
            Utilities.error(e);
        }
    }

}
