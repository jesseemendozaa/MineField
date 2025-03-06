package mvc;

import java.io.Serializable;

public abstract class Model extends Publisher implements Serializable {

    private boolean unsavedChanges;
    private String fileName;

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
