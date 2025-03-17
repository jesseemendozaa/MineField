package minefield;

import java.awt.*;
import javax.swing.*;
import mvc.Model;
import mvc.View;

public class MineFieldView extends View 
{
    //TODO Change patches depending on model

    private JLabel[][] grid;

    public MineFieldView(MineField m)
    {
        super(m);
        MineField mf = (MineField)model;
        setLayout(new GridLayout(MineField.world_size, MineField.world_size));
        grid = new JLabel[MineField.world_size][MineField.world_size];

        for (int row = 0; row < MineField.world_size; row++) 
        {
            for (int col = 0; col < MineField.world_size; col++) 
            {
                grid[row][col] = new JLabel(mf.getMap()[row][col].getDisplay());
                grid[row][col].setOpaque(true);
                grid[row][col].setBackground(Color.LIGHT_GRAY);
                grid[row][col].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                add(grid[row][col]);
            }
        }


        updateView();
    }

    public void update(){
        updateView();
    }

    public void updateView() {
        MineField mf = (MineField)model;
        for (int row = 0; row < MineField.world_size; row++) 
        {
            for (int col = 0; col < MineField.world_size; col++) 
            {
                grid[row][col].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                grid[row][col].setText(mf.getMap()[row][col].getDisplay()); // Update
            }
        }

        // White Border Path
        for (MineField.Patch i : mf.getPath())
        {
            grid[i.getX()][i.getY()].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        }

        // Black Border 
        MineField.Patch location = mf.getLocation();
        grid[location.getX()][location.getY()].setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        // Green Border (Goal)
        MineField.Patch goal = mf.getGoal();
        grid[goal.getX()][goal.getY()].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

        revalidate();
        repaint();
    }
}