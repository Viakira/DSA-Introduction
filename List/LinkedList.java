package List;

public class LinkedList<AnyType> implements Iterable<AnyType> {


    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private Node<AnyType> head;
    private Node<AnyType> end;
    private int size;
    private int modCount;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public LinkedList() { modCount = 0; clear(); }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        head = new Node<>( null, null, null );
        end = new Node<>( null, head, null );
        head.next = end;

        size = 0;
        modCount++;
    }

    //----------------------------------------------------------------------------------

    public AnyType get( int idx ) { return getNode(idx).data; }

    public AnyType set( int idx, AnyType data ) {
        Node<AnyType> node = getNode(idx);
        AnyType old = node.data;
        node.data = data;
        return old;
    }

    public void insert( int idx, AnyType data ) {
        if( idx == size )
            add(data);
        addBefore( getNode( idx ), data );
    }

    public AnyType remove( int idx ) { return remove( getNode(idx) ); }

    public boolean add( AnyType data ) { addBefore( end, data ); return true; }

    //----------------------------------------------------------------------------------


    public java.util.Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }


    //-------------------------------------------------------------------------------
    //--------------------------------| inner class |--------------------------------
    //-------------------------------------------------------------------------------


    private static class Node<AnyType> {

        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;


        public Node( AnyType data, Node<AnyType> prev, Node<AnyType> next ) {
            this.data = data; this.prev = prev; this.next = next;
        }

    }

    private class LinkedListIterator implements java.util.Iterator<AnyType> {

        private Node<AnyType> current = head.next;
        private int expectedModCount = modCount;
        private boolean canRemove = false;


        public boolean hasNext() { return current != end; }

        public AnyType next() {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException();
            if( !hasNext() )
                throw new java.util.NoSuchElementException();

            AnyType next = current.data;
            current = current.next;
            canRemove = true;
            return next;
        }

        public void remove() {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException();
            if( !canRemove )
                throw new IllegalStateException();

            expectedModCount++;
            canRemove = false;
            LinkedList.this.remove( current.prev );
        }

    }


    //-----------------------------------------------------------------------------------
    //--------------------------------| private methods |--------------------------------
    //-----------------------------------------------------------------------------------


    private Node<AnyType> getNode( int idx ) {
        if( idx < 0 || idx >= size )
            throw new ArrayIndexOutOfBoundsException();

        Node<AnyType> node;
        if( idx < size / 2 ) {
            node = head.next;
            for( int i=0; i<idx; i++ )
                node = node.next;
        } else {
            node = end;
            for( int i=size; i>idx; i-- )
                node = node.prev;
        }

        return node;
    }

    private void addBefore( Node<AnyType> node, AnyType data ) {
        Node<AnyType> newNode = new Node<>( data, node.prev, node );
        node.prev.next = newNode;
        node.prev = newNode;

        size++;
        modCount++;
    }

    private AnyType remove( Node<AnyType> node ) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

        size--;
        modCount++;
        return node.data;
    }

}