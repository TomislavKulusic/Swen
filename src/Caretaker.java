import java.util.ArrayList;

/**
 * Created by Nikola on 04.05.2017..
 * Swen final project
 * Created in: 22 : 07
 * Memento pattern caretaker
 */
class Caretaker {

    private ArrayList<ImageMemento> mementos = new ArrayList<>();

    /**
     * Add an image state memento to an array list of mementos
     * @param m ImageMemento
     */
    void addMemento(ImageMemento m) {
        mementos.add(m);
    }

    /**
     * @param i Position in array list
     * @return Memento on given array list position
     */
    ImageMemento getMemento(int i) {
        return mementos.get(i);
    }

    /**
     * @return Amount of mementos in the array list
     */
    int getMementosSize() {
        return mementos.size();
    }

    /** Wipe all mementos from the end of the list to the specified index
     * @param from Index of wiping ending position
     */
    void wipeMementos(int from) {
        for(int i = (mementos.size()-1); i > from; i--){
            mementos.remove(i);
        }
    }

}
