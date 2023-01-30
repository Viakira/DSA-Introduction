package Queue;

public class LinkedQueue<AnyType> {

    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private Node<AnyType> head;
    private Node<AnyType> end;
    private int size;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public LinkedQueue() { clear(); }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        end = new Node<>( null, null );
        head = new Node<>( null, end );
        size = 0;
    }

    //----------------------------------------------------------------------------------

    public void enqueue( AnyType data ) {
        end.data = data;
        end.next = new Node<>( null, null );
        end = end.next;

        size++;
    }

    public AnyType dequeue() {
        if( isEmpty() )
            throw new java.util.NoSuchElementException();

        AnyType data = head.next.data;
        head.next = head.next.next;

        size--;
        return data;
    }

    //-------------------------------------------------------------------------------
    //--------------------------------| inner class |--------------------------------
    //-------------------------------------------------------------------------------


    private static class Node<AnyType> {

        public AnyType data;
        public Node<AnyType> next;

        public Node( AnyType data, Node<AnyType> next ) {
            this.data = data; this.next = next;
        }

    }

}