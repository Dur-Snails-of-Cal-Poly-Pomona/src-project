import java.util.LinkedList;

/**
 * A class that represents a bag implemented using a linked list.
 *
 * @param <T> the type of elements in the bag
 */
public class LinkedBag<T> {
    private LinkedList<T> bag;

    /**
     * Constructs an empty LinkedBag.
     */
    public LinkedBag() {
        bag = new LinkedList<>();
    }

    /**
     * Adds an item to the bag.
     *
     * @param item the item to be added
     */
    public void add(T item) {
        bag.add(item);
    }

    /**
     * Removes an item from the bag.
     *
     * @param item the item to be removed
     * @return true if the item was successfully removed, false otherwise
     */
    public boolean remove(T item) {
        return bag.remove(item);
    }

    /**
     * Checks if the bag contains a specific item.
     *
     * @param item the item to be checked
     * @return true if the item is found in the bag, false otherwise
     */
    public boolean contains(T item) {
        return bag.contains(item);
    }

    /**
     * Returns the number of items in the bag.
     *
     * @return the size of the bag
     */
    public int size() {
        return bag.size();
    }

    /**
     * Checks if the bag is empty.
     *
     * @return true if the bag is empty, false otherwise
     */
    public boolean isEmpty() {
        return bag.isEmpty();
    }

    /**
     * Removes all items from the bag.
     */
    public void clear() {
        bag.clear();
    }

    /**
     * Returns a new bag that contains the intersection of this bag and another bag.
     *
     * @param otherBag the other bag to find the intersection with
     * @return a new bag that contains the intersection of this bag and the other bag
     */
    public LinkedBag<T> intersection(LinkedBag<T> otherBag) {
        LinkedBag<T> intersectionBag = new LinkedBag<>();

        for (T item : bag) {
            if (otherBag.contains(item)) {
                intersectionBag.add(item);
            }
        }

        return intersectionBag;
    }

    /**
     * Returns a new bag that contains the difference between this bag and another bag.
     *
     * @param otherBag the other bag to find the difference with
     * @return a new bag that contains the difference between this bag and the other bag
     */
    public LinkedBag<T> difference(LinkedBag<T> otherBag) {
        LinkedBag<T> differenceBag = new LinkedBag<>();

        for (T item : bag) {
            if (!otherBag.contains(item)) {
                differenceBag.add(item);
            }
        }

        return differenceBag;
    }

    /**
     * Returns a new bag that contains the union of this bag and another bag.
     *
     * @param otherBag the other bag to find the union with
     * @return a new bag that contains the union of this bag and the other bag
     */
    public LinkedBag<T> union(LinkedBag<T> otherBag) {
        LinkedBag<T> unionBag = new LinkedBag<>();

        // Add all items from this bag to the union bag
        for (T item : bag) {
            unionBag.add(item);
        }

        // Add all items from the other bag to the union bag
        for (T item : otherBag.bag) {
            unionBag.add(item);
        }

        return unionBag;
    }
}