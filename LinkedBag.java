import java.io.Serializable;

public final class LinkedBag <T> implements BagInterface <T>, Serializable
{
    private Node<T> firstNode;
    //private Node<T> lastNode;
    private int numEntries;


    public LinkedBag()
    {
        firstNode = null;
        //lastNode =null;
        numEntries = 0;
    }

    @Override
    public int getCurrentSize() 
    {
        return numEntries;
    }

    @Override
    public boolean isEmpty() 
    {
        boolean result = true;
        if (numEntries ==0)
        {
            result = false;
        }
        else
        {
            result = true;
        }
        return result;
    }

    @Override
    public boolean add(T newEntry) 
    {
        Node <T> newNode = new Node<>(newEntry);
        newNode.setNextNode(firstNode);
        firstNode = newNode;
        /*if (firstNode.getNextNode() == null && numEntries ==0)
        {
            lastNode = newNode;
        }*/
        numEntries++;
        return true;
    }

    @Override
    public T remove() 
    {
        T result = null;
        if (firstNode != null)
        {
            result = firstNode.getData();
            firstNode = firstNode.getNextNode();
            numEntries--;
        }
        return result;
    }

    @Override
    public boolean remove(T anEntry) 
    {
        boolean result = false;
        Node <T> nodeToRemove = getRefrenceTo(anEntry);

        if ( nodeToRemove != null)
        {
            //nodeToRemove = nodeToRemove.getNextNode();
            nodeToRemove.setData(firstNode.getData()); //
            firstNode = firstNode.getNextNode(); //

            numEntries--;
            result = true;
        }
        return result;
    }

    @Override
    public void clear() 
    {
        while (!isEmpty())
        {
            remove();
        }

    }

    @Override
    public int getFrequencyOf(T anEntry) 
    {
        int frequency =0;
        int counter =0;
        Node<T> nodeToCount = firstNode;

        while ((counter < numEntries) && nodeToCount != null)
        {
            if (nodeToCount.equals(anEntry))
            {
                frequency++;
            }
            counter++;
            nodeToCount = nodeToCount.getNextNode();
        }
        return frequency;
    }

    @Override
    public boolean contains(T anEntry) 
    {
        boolean result = false;
        Node <T> nodeExists = firstNode;
        while (nodeExists != null)
        {
            if (nodeExists.getData() == anEntry)
            {
                result = true;
            }
            else
            {
                nodeExists = nodeExists.getNextNode();
            }
        }

        return result;
    }

    @Override
    public T[] toArray() 
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numEntries];
    
        int index =0;
        Node <T> currentNode = firstNode;
        while ((index < numEntries) && (currentNode != null))
        {
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        }
        return result;
    }

    private Node <T> getRefrenceTo(T anEntry)
    {
        boolean found =false;
        Node <T> currentNode = firstNode;
        while (!found && (currentNode != null))
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
        return currentNode;
    }
    
}
