

//linkedlist.java

import java.io.Serializable;

public class LinkedList<T> implements ListInterface<T>, Serializable
{
    private Node <T> firstNode;
    private int numberOfEntries;
    
    /** Default Constructor */
    public LinkedList()
    {
        initializeDataFields();
    }

    /** Removes all entries from this list. */
    public void clear()
    {
        initializeDataFields();
    }

    /** Initializes the class's data fields to indicate an empty list. */ 
    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries =0;
    }


    /** Sees whether this list is empty.
      * @return  True if the list is empty, or false if not. 
      */
    public boolean isEmpty()
    {
     boolean result=false;

     if (numberOfEntries ==0 /*|| getCurrentSize()==0 */)
     {
        if( firstNode == null)
        {
            result = true;
        }
     }
     else 
     {
        result = false;
     }
     return result;
    }
    
    /** Adds a new entry to the end of this list. Entries currently in the list are unaffected. The list's size is increased by 1. 
      * @param newEntry  The object to be added as a new entry. 
      */
    public void add(T newEntry)
    {
        Node<T> newNode = new Node<T>(newEntry);

        if (isEmpty()==true) //if nothing is here than make newNode the private firstNode
        {
            this.firstNode = newNode;
        }
        else
        {
            Node<T> lastNode = getNodeAt(numberOfEntries);
            lastNode.setNextNode(newNode);
        }

        numberOfEntries++;
    }    

    /** Adds a new entry at a specified position within this list. Entries originally at and above the specified position are at the next higher position within the list. The list's size is increased by 1.       
     * @param newPosition  An integer that specifies the desired position of the new entry.
     * @param newEntry     The object to be added as a new entry.
     * @throws  IndexOutOfBoundsException if either newPosition < 1 or newPosition > getLength() + 1.
     */
    @Override
    public void add (int givenPosition, T newEntry)
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries +1))
        {
            Node<T> newNode = new Node<T>(newEntry);
            if (givenPosition ==1)
            {
                newNode.setNextNode(firstNode);
                firstNode = newNode;
            }
            else
            {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeAfter = nodeBefore.getNextNode();
                newNode.setNextNode(nodeAfter);
                nodeBefore.setNextNode(newNode);

            }
            numberOfEntries++;
        }
        else
        {
            throw new IndexOutOfBoundsException("Illegal position given to add operation.");
        }
    }

    /** Removes the entry at a given position from this list. Entries originally at positions higher than the given position are at the next lower position within the list, and the list's size is decreased by 1.
     * @param givenPosition  An integer that indicates the position of the entry to be removed.
     * @return  A reference to the removed entry.
     * @throws  IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength().
     */
    @Override
    public T remove (int givenPosition)
    {
        T result = null;
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {

            if (givenPosition ==1)
            {
                result = firstNode.getData();
                firstNode = firstNode.getNextNode();
            }
            else
            {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();
                Node<T> nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);
            }
            numberOfEntries--;
            return result;
        }
        else
        {
            throw new IndexOutOfBoundsException("Illegal position given to add operation.");
        }
    }

    /**
     * Gets the node at given position.
     * @param givenPosition An integer that indicates the position of the node to be returned.
     * @return Returns the node at the given position.
     */
    private Node<T> getNodeAt(int givenPosition)
    {
        Node<T> currentNode = firstNode;

        for (int counter = 1; counter < givenPosition; counter++)
        {
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }
    
    /** Gets the length of this list.
     * @return  The integer number of entries currently in the list. 
     */
    @Override
    public int getCurrentSize() 
    {
        return numberOfEntries;
    }
    
    /** Replaces the entry at a given position in this list.
     * @param givenPosition  An integer that indicates the position of the entry to be replaced.
     * @param newEntry  The object that will replace the entry at the position givenPosition.
     * @return  The original entry that was replaced.
     * @throws  IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength(). 
     */
    @Override
    public T replace(int givenPosition, T newEntry) 
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {

            Node <T> desiredNode = getNodeAt(givenPosition);
            T originalEntry = desiredNode.getData();
            desiredNode.setData(newEntry);
            return originalEntry;
        }
        else
        {
            //throw new IndexOutofBoundsException("Illegal position given to replace operation.");
            return null;
        }
    }

    /** Retrieves the entry at a given position in this list.
     * @param givenPosition  An integer that indicates the position of the desired entry.
     * @return  A reference to the indicated entry.
     * @throws  IndexOutOfBoundsException if either givenPosition < 1 or givenPosition > getLength(). 
     */
    @Override
    public T getEntry(int givenPosition) 
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            return getNodeAt(givenPosition).getData();
        }
        else
        {
            return null;
            //throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
        }
    }

    /** Sees whether this list contains a given entry.
     * @param anEntry  The object that is the desired entry.
     * @return  True if the list contains anEntry, or false if not. 
     */
    @Override
    public boolean contains(T anEntry) 
    {
        boolean found = false;
        Node <T> currentNode = firstNode;

        while ((currentNode != null) && !found)
        {
            if (anEntry.equals(currentNode.getData()))
            {
                found = true;
            }
            else
            {
                currentNode = currentNode.getNextNode();
            }
        }
        return found;
    }

    /** Retrieves all entries that are in this list in the order in which they occur in the list.
     * @return  A newly allocated array of all the entries in the list. If the list is empty, the returned array is empty. 
     */
    @Override
    public T[] toArray() {
        @SuppressWarnings ("unchecked")
        T[] array = (T[])new Object[numberOfEntries];

        int index =0;
        Node <T> counterNode = firstNode;
        while ((index < numberOfEntries) && (counterNode != null))
        {
            array[index] = counterNode.getData();
            index++;
            counterNode = counterNode.getNextNode();
        }

        return array;
    }


    /**
     * 
     */
    public void printListall()
    {
        int index =1;

        System.out.println("====");
        for(Node<T> current = firstNode; current != null; current = current.getNextNode())
        {
            System.out.println(index + "\t" + current.getData());
            index++;
        }
        System.out.println("====");
    }


}
