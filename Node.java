package project0semisterlongproject;
//Node.java
public class Node<T>
{
    private T data;
    private Node<T> next;

    Node (T dataPortion)
    {
        this.data= dataPortion;
        this.next = null;
    }
    
    private Node(T dataPortion, Node<T> nextNode)
    {
        this.data = dataPortion;
        this.next = nextNode;
    }

    T getData()
    {
        return this.data;
    }

    void setData(T newData)
    {
        this.data = newData;
    }

    Node<T> getNextNode()
    {
        return this.next;
    }

    void setNextNode(Node<T> nextNode)
    {
        this.next = nextNode;
    }

    

}
