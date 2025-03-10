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


    public AppPanel(AppFactory factory) {

        this.factory = factory;
        this.model = factory.makeModel();
        this.view = factory.makeView(model);

        controlPanel = new JPanel();
        this.setLayout((new GridLayout(1, 2)));
        this.add(controlPanel);
        this.add(view);

        frame = new SafeFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenuBar());
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);
    }

    public void display() { frame.setVisible(true); }

    public void update() {  /* override in extensions if needed */ }

    public Model getModel() { return model; }

    // called by file/open and file/new
    public void setModel(Model newModel) {

        this.model.unsubscribe(this);
        this.model.unsubscribe(view);
        this.model = newModel;
        this.model.subscribe(this);
        view.setModel(this.model);
        this.model.subscribe(view);
        model.changed();
    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        // add file, edit, and help menus
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New",  "Save", "SaveAs", "Open", "Quit"}, this);
        result.add(fileMenu);

        JMenu editMenu = Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        result.add(editMenu);

        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        result.add(helpMenu);

        return result;
    }

    

    public void actionPerformed(ActionEvent ae) {
        try {
            String cmmd = ae.getActionCommand();
            Object source = ae.getSource();

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
            else { // must be from Edit menu
                Command command = factory.makeEditCommand(model, cmmd, source);
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
