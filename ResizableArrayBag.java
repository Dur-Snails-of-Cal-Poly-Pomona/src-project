<<<<<<< HEAD
import java.util.Arrays;

public class ResizableArrayBag<T> implements BagInterface<T> {
    
    private T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private boolean integrityOK = false; 
    private static final int MAX_CAPACITY = 10000;

    public ResizableArrayBag()
    {
        numberOfEntries = 0;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[DEFAULT_CAPACITY];
        integrityOK = true;
        bag = tempBag;
    }

    public ResizableArrayBag(int capacity)
    {
        numberOfEntries = 0;
        @SuppressWarnings("unchecked")
        T[] tempBag = (T[])new Object[capacity];
        integrityOK = true;
        bag = tempBag;
    }

    /**
     * Throws an exception if this object is not initialized.
     */
    private void checkIntegrity()
    {
        if(!integrityOK)
        {
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    }
    
    /**
     * Gets the current number of entries in this bag.
     * @return The integer number of entries currently in the bag.
     */
    public int getCurrentSize() 
    {
        return numberOfEntries;
    }

    /**
     * Sees whether this bag is empty.
     * @return True if the bag is empty, or false if not.
     */
    public boolean isEmpty() 
    {
        return numberOfEntries == 0;
    }

    /**
     * Adds a new entry to this bag.
     * @param newEntry The object to be added as a new entry.
     * @return True if the addition is successful, false if not.
     */
    public boolean add(T newEntry)
    {
        checkIntegrity();
        if(isArrayFull())
        {
            doubleCapacity();
        }
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;  
    }

    /**
     * Doubles the capacity
     * Precondition: checkIntegrity has been called.
     */
    private void doubleCapacity()
    {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);
    } 

    /**
     * Checks if the capacity of the bag is over the max capacity.
     * @param capacity The capacity of the bag.
     */
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a bag whose " + "capacity exeeds allowed " + "maximum of " + MAX_CAPACITY);
    } 

    /**
     * Determines if the bag is full
     * @return True if bag is full, false if bag is not full.
     */
    private boolean isArrayFull()   
    {
        if(numberOfEntries == bag.length)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Removes one unspecified entry from this bag, if possible.
     * @return Either the removed entry if removal was successful, or null.
     */
    public T remove()
    {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);		
        return result;        
    }

    /**
     * Removes one occurence of a given entry from this bag, if possible.
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, or false if not.
     */
    public boolean remove(T anEntry)
    {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }

    /**
     * Finds the index at where the entry is, if possible.
     * @param anEntry The entry to be located.
     * @return -1 if the entry could not be located, or any positive number (index of entry in the bag).
     */
    private int getIndexOf(T anEntry) 
    {
        int where = -1;
        boolean found = false;
        int index = 0;

        while (!found && (index < numberOfEntries))
        {
            if (anEntry.equals(bag[index]))
            {
                found = true;
                where = index;
            } 
            index++;
        } 
        return where;
    }

    /**
     * Removes entry at given index.
     * @param givenIndex The index where the entry should be.
     * @return The entry that was just removed, if no entry then null.
     */
    private T removeEntry(int givenIndex) 
    {
        T result = null;

        if(!isEmpty() && (givenIndex >= 0))
        {
            result = bag[givenIndex];
            bag[givenIndex] = bag[numberOfEntries - 1]; 
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
        }
        return result;
    } 

    /**
     * Removes all entries from this bag.
     */
    public void clear() 
    {
        while(!isEmpty())
        {
            remove();
        }
    }

    /**
     * Counts the number of times a given entry appears in this bag.
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in the bag.
     */
    public int getFrequencyOf(T anEntry)
    {
        checkIntegrity();
        int counter = 0;

        for (int index = 0; index < numberOfEntries; index++)
        {
            if (anEntry.equals(bag[index]))
            {
                counter++;
            }
        }
        return counter; 
    }

    /**
     * Tests whether this bag contains a given entry.
     * @param anEntry The entry to find.
     * @return True if the bag contains anEntry, or false if not.
     */
    public boolean contains(T anEntry)
    {
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    }

    /**
     * Retrieves all entries that are in this bag.
     * @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty.
     */
    public T[] toArray()
    {
        checkIntegrity();
        @SuppressWarnings("unchecked")
        T[] arr = (T[])new Object[numberOfEntries];
        for(int index = 0; index < numberOfEntries; index++)
        {
            arr[index] = bag[index];
        }
        return arr;
    }
