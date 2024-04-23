//Node.java

import java.io.Serializable;

public class Node<T> implements Serializable
{
    private T data;
    private Node<T> next;

    /**
     * Constructor that does not have a node after it.
     * @param dataPortion The data that is being store within the node.
     */
    Node (T dataPortion)
    {
        this.data= dataPortion;
        this.next = null;
    }
    
    /**
     * Constructor that has a node after it.
     * @param dataPortion The data that is being store within the node.
     * @param nextNode The node after the current node.
     */
    @SuppressWarnings("unused")
    private Node(T dataPortion, Node<T> nextNode)
    {
        this.data = dataPortion;
        this.next = nextNode;
    }

    /**
     * Gets the data stored in the node.
     * @return The data stored in the node.
     */
    T getData()
    {
        return this.data;
    }

    /**
     * Sets the date stored in the node.
     * @param newData The data being stored in the node.
     */
    void setData(T newData)
    {
        this.data = newData;
    }

    /**
     * Gets the next node.
     * @return The node after the current node.
     */
    Node<T> getNextNode()
    {
        return this.next;
    }

    /**
     * Sets the next node.
     * @param nextNode The node being set after the current node.
     */
    void setNextNode(Node<T> nextNode)
    {
        this.next = nextNode;
    }

    

}
