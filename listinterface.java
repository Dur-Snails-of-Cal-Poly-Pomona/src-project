//listinterface.java
public interface listinterface<T>
{
    // BagInterface.java
 
    int getCurrentSize();
    boolean isEmpty();
    void add(T newEntry);
    void add (int newPosition,T newEntry);          //Adds newEntry at position newPosition within the list
    T remove(int givenPosition);                    // removes and returns the entry at position givenPosition
    T replace (int givenPosition, T newEntry);      //replaces the entry at postion givenPosition with newEntry
    void clear();
    T getEntry(int givenPosition);                  //retrieves entry at pisition givenPosition
    boolean contains(T anEntry);
    T[] toArray();

    //T remove();
    //boolean remove(T anEntry);
    //int getFrequencyOf(T anEntry);

}
