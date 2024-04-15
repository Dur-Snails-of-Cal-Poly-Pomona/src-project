//linkedlist.java

import java.io.Serializable;

public class LinkedList<T> implements ListInterface<T>, Serializable 
{
    private Node <T> firstNode;
    private int numberOfEntries;
    

    public LinkedList()
    {
        initializeDataFields();
    }
    public void clear()
    {
        initializeDataFields();
    }

    private void initializeDataFields()
    {
        firstNode = null;
        numberOfEntries =0;
    }

    //** */

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

    private Node<T> getNodeAt(int givenPosition)
    {
        Node<T> currentNode = firstNode;

        for (int counter = 1; counter < givenPosition; counter++)
        {
            currentNode = currentNode.getNextNode();
        }
        return currentNode;
    }
    
    @Override
    public int getCurrentSize() 
    {
        return numberOfEntries;
    }
    
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




}
