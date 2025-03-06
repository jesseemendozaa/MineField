package mvc;

import javax.swing.*;

public class View extends JPanel implements Subscriber{

    Model model;

    public View(Model model){
        this.model = model;
        model.subscribe(this);
    }

    public void setModel(Model model){
        this.model = model;
    }

    public Model getModel(){
        return model;
    }

    @Override
    public void update() {
        repaint();
    }
}
