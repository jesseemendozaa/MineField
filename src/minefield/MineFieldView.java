package minefield;

import java.awt.*;
import javax.swing.*;
import mvc.Model;
import mvc.View;

public class MineFieldView extends View 
{

    //TODO Change patches depending on model

    private final JButton[][] grid;
    private final int gridSize = 20;

    public MineFieldView(Model model) {  
        super(model != null ? model : new Model() 
        {
            @Override
            public void changed() {}
        });

        setLayout(new GridLayout(gridSize, gridSize));
        grid = new JButton[gridSize][gridSize];

        for (int row = 0; row < gridSize; row++)
            for (int col = 0; col < gridSize; col++)
                add(grid[row][col] = new JButton("?"));
    }
}