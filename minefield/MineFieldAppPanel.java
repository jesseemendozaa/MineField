package minefield;

import java.awt.*;
import javax.swing.*;
import mvc.*;

public class MineFieldAppPanel extends AppPanel 
{
    private MineFieldView view;

    public MineFieldAppPanel(AppFactory factory) 
    {
        super(factory);

        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 2, 10, 10));
        String[] dir = {"NW", "N", "NE", "W", "E", "SW", "S", "SE"};

        for (int i = 0; i < dir.length; i++) 
        {
            JButton button = new JButton(dir[i]);
            controlPanel.add(button);
        }

        add(controlPanel, BorderLayout.WEST); // Control Panel on left side

        // Minefield on right side
        view = new MineFieldView(null);
        add(view, BorderLayout.CENTER); 
    }

    public static void main(String[] args) 
    {
        AppFactory factory = new AppFactory() {
            
            @Override
            public Model makeModel() { return null; }

            @Override
            public View makeView(Model m) { return new MineFieldView(null); }

            @Override
            public String[] getEditCommands() { return new String[]{"North", "North East", "North West",  "South", "South East", "South West", "East", "West"}; }

            @Override
            public Command makeEditCommand(Model model, String type, Object source) { return null; }

            @Override
            public String getTitle() { return "MineField"; }

            @Override
            public String[] getHelp() { return new String[]{"Use buttons to move the miner carefully without stepping on a mine."}; }

            @Override
            public String about() { return "Minefield: CS 151 Sec 02: Group 9"; }};

        AppPanel panel = new MineFieldAppPanel(factory);
        panel.display();
    }
}