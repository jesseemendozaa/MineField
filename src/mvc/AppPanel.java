package mvc;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// AppPanel is the MVC controller
public class AppPanel extends JPanel implements Subscriber, ActionListener  {

    protected Model model;
    protected AppFactory factory;
    protected View view;
    protected JPanel controlPanel;
    private JFrame frame;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;
    private JButton north, northEast, northWest, south, southEast, southWest, east, west;


    public AppPanel(AppFactory factory) {


/*
        turtleModel = new TurtleModel();
        view = new DrawPanel(turtleModel);
        control = new ControlPanel(turtleModel, view);

        this.setLayout(new GridLayout(1, 2));
        this.add(control);
        this.add(view);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("Turtle Graphics");
        frame.setSize(500, 300);
        frame.setVisible(true);

*/
        // initialize fields here

        this.factory = factory;
        this.model = factory.makeModel();
        this.view = factory.makeView();

        controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

        JButton north = new JButton("North");
        JButton northWest = new JButton ("North West");
        JButton northEast = new JButton ("North East");
        JButton east = new JButton("East");
        JButton west = new JButton("West");
        JButton south = new JButton("South");
        JButton southWest = new JButton ("South West");
        JButton southEast = new JButton ("South East");

        this.add(controlPanel);
        this.add(view);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        //frame.setTitle(factory.getTitle());
        frame.setTitle("Minefield");
        frame.setSize(500, 300);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    public void display() { frame.setVisible(true); }

    public void update() {  /* override in extensions if needed */ }

    public Model getModel() { return model; }

    // called by file/open and file/new
    public void setModel(Model newModel) {
        this.model.subscribe(this);
        this.model = newModel;
        this.model.subscribe(this);
        // view must also unsubscribe then resubscribe:
        view.setModel(this.model);
        model.changed();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);
/*
        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);
    */

        JMenu editMenu = Utilities.makeMenu("Edit", new String[] {"North", "North East", "North West", "South", "South East", "South West", "East", "West"}, this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    

    public void actionPerformed(ActionEvent ae) {
        try {
            String cmmd = ae.getActionCommand();

            if (cmmd.equals("Save")) {
                Utilities.save(model, false);
            } else if (cmmd.equals("SaveAs")) {
                Utilities.save(model, true);
            } else if (cmmd.equals("Open")) {
                Model newModel = Utilities.open(model);
                if (newModel != null) setModel(newModel);
            } else if (cmmd.equals("New")) {
                Utilities.saveChanges(model);
                setModel(factory.makeModel());
                // needed cuz setModel sets to true:
                model.setUnsavedChanges(false);
            } else if (cmmd.equals("Quit")) {
                Utilities.saveChanges(model);
                System.exit(0);
            } 
             else if (cmmd.equals("About")) {
                Utilities.inform(factory.about());
            } else if (cmmd.equals("Help")) {
                Utilities.inform(factory.getHelp());
            } 
            /*
           else if (cmmd.equals("About"))
           { Utilities.inform("Group 9 CS 151"); }
           else if (cmmd.equals("Help"))
           { Utilities.inform("You can move the miner using the directional buttons and via the Edit Menu. Numbers will display how many mines are adjacent to block "); }
           */
          
            else { // must be from Edit menu
                Command command = factory.makeEditCommand(cmmd);
                command.execute();
            }
            
        }
        
        catch (Exception e) {
            handleException(e);
        }
    }


    // EDIT MENU for Turtle Graphics

    /*
// Edit Menu
        else if (cmmd.equals("North"))
        {
            control.TurtleMove(0, -1);
        }
                
        else if (cmmd.equals("South"))
        {
            control.TurtleMove(0, 1);
        }
                
        else if (cmmd.equals("East"))
        {
            control.TurtleMove(1, 0);
        }
                
        else if (cmmd.equals("West"))
        {
            control.TurtleMove(-1, 0);
        }
                
    */

    protected void handleException(Exception e) {
        Utilities.error(e);
    }
}
/*

   class ControlPanel extends JPanel 
    {
        private TurtleModel turtleModel;
        private DrawPanel view;
        private JButton north, south, east, west, clear, pen, color;

        public ControlPanel(TurtleModel model, DrawPanel view) 
        {
            this.turtleModel = model;
            this.view = view;
            
            setBackground(Color.LIGHT_GRAY); // Background
            setLayout(new GridLayout(4, 2, 40, 40));

            JButton north = new JButton("North");
            JButton east = new JButton("East");
            JButton west = new JButton("West");
            JButton south = new JButton("South");
            JButton clear = new JButton("Clear");
            JButton pen = new JButton("Pen");
            JButton color = new JButton("Color");

            // Action listener for each button
            north.addActionListener(e -> TurtleMove(0, -1));
            south.addActionListener(e -> TurtleMove(0, 1));
            east.addActionListener(e -> TurtleMove(1, 0));
            west.addActionListener(e -> TurtleMove(-1, 0));
            clear.addActionListener(e -> view.ClearTurtle());
            pen.addActionListener(e -> PenStatus());
            color.addActionListener(e -> chooseColor());

            add(north);
            add(east);
            add(west);
            add(south);
            add(clear);
            add(pen);
            add(color);
        }

        */
