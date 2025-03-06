package mvc;

import java.util.ArrayList;

public abstract class Publisher {

    private ArrayList<Subscriber> subscribers;

    public Publisher() {
        subscribers = new ArrayList<>();
    }

    public void notifySubscribers(){
        for(Subscriber s : subscribers){
            s.update();
        }
    }

    public void subscribe(Subscriber s){
        subscribers.add(s);
    }

    public void unsubscribe(Subscriber s){
        subscribers.remove(s);
    }
}
