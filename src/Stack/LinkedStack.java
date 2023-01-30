package src.Stack;

public class LinkedStack<AnyType> {

    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private Node<AnyType> top;
    private int size;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public LinkedStack() { clear(); }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() { top = null; size = 0; }

    //----------------------------------------------------------------------------------

    public AnyType top() {
        if( isEmpty() )
            throw new java.util.EmptyStackException();

        return top.data;
    }

    public void push( AnyType data ) {
        Node<AnyType> node = new Node<>( data, top );
        top = node;

        size++;
    }

    public AnyType pop() {
        if( isEmpty() )
            throw new java.util.EmptyStackException();

        AnyType popped = top.data;
        top = top.next;
        return popped;
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