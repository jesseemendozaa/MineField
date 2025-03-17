package minefield;

import java.awt.*;
import javax.swing.*;
import mvc.*;

public class MineFieldAppPanel extends AppPanel
{

    public MineFieldAppPanel(AppFactory factory) 
    {
        super(factory);
        String[] dir = factory.getEditCommands();

        controlPanel.setLayout(new GridLayout(4,2));

        for (int i = 0; i < dir.length; i++) 
        {
            JPanel p = new JPanel();
            JButton button = new JButton(dir[i]);
            button.addActionListener(this);
            p.add(button);
            controlPanel.add(p);
        }

    }

    public static void main(String[] args) 
    {

        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldAppPanel(factory);
        panel.display();
    }
}