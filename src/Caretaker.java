import java.util.ArrayList;

/**
 * Created by Nikola on 04.05.2017..
 * Swen final project
 * Created in: 22 : 07
 */
class Caretaker {

    private ArrayList<ImageMemento> mementos = new ArrayList<>();

    void addMemento(ImageMemento m) {
        mementos.add(m);
    }

    ImageMemento getMemento(int i) {
        return mementos.get(i);
    }

    int getMementosSize() {
        return mementos.size();
    }

    void wipeMementos(int from) {
        for(int i = (mementos.size()-1); i > from; i--){
            mementos.remove(i);
        }
    }

}
