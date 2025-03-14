package minefield;

import java.awt.*;
import javax.swing.*;
import mvc.Model;
import mvc.View;
import stoplightSim2.StopLightShape;
import stoplightSim2.Stoplight;

public class MineFieldView extends View 
{

    //TODO Change patches depending on model

    private JLabel[][] grid;
    private MineField model;

    public MineFieldView(Model m) {
        super(m);

        this.model = (MineField)m;

        setLayout(new GridLayout(MineField.world_size, MineField.world_size));
        grid = new JLabel[MineField.world_size][MineField.world_size];

        for (int row = 0; row < MineField.world_size; row++)
            for (int col = 0; col < MineField.world_size; col++)
                add(grid[row][col] = new JLabel(model.getMap()[row][col].getDisplay()));
    }

}