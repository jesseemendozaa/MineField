package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {

    private boolean unsavedChanges;
    private String fileName;
    private int x, y;
/*
    public TurtleModel() 
            {
                this.x = 250;
                this.y = 250;
                this.path = new ArrayList<>();
                path.add(new int[]{x, y}); //append
            }

            public void move(int xCord, int yCord) 
            {
                x = x + xCord;
                y = y + yCord;

                if (penDown) 
                {
                    path.add(new int[]{x, y}); //append
                }
                unsavedChanges = true;
            }

    */
   
   public void move(int xCord, int yCord)
    {
        x = x + xCord;
        y = y + yCord;
    }
    
    public Model() {
        unsavedChanges = true;
        fileName = null;
    }

    public void changed(){
        notifySubscribers();
    }

    public boolean getUnsavedChanges(){
        return unsavedChanges;
    }

    public void setUnsavedChanges(boolean b){
        unsavedChanges = b;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String s){
        fileName = s;
    }

}
