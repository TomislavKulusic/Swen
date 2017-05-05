import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Nikola on 04.05.2017..
 */
public class Caretaker {

    private ArrayList<ImageMemento> mementos = new ArrayList<>();

    public void addMemento(ImageMemento m){
        mementos.add(m);
    }

    public ImageMemento getMemento(int i){
        return mementos.get(i);
    }

    public int getMementosSize(){
        return mementos.size();
    }

    public void wipeMementos(int from){

        for(int i = (mementos.size()-1); i > from; i--){
            mementos.remove(i);
        }

    }

}
