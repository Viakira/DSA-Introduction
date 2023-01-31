package src.Heap;

public class BinomialQueue<AnyType extends Comparable<? super AnyType>> {

    private static final int DEFAULT_TREES = 1;

    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private Node<AnyType>[] trees;
    private int size;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public BinomialQueue() { clear(); }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        trees = (Node<AnyType>[]) new Object[ DEFAULT_TREES ];
        for( int i=0; i < DEFAULT_TREES; i++ )
            trees[i] = null;
        size = 0;
    }

    //----------------------------------------------------------------------------------

    public void merge( BinomialQueue<AnyType> another ) {
        if( this == another )
            return ;

        size += another.size;

        if( size > capacity() )
            enlargeCapacity( Math.max( trees.length, another.trees.length ) + 1 );

        Node<AnyType> carry = null;
        for( int i=0, j=1; j <= size; i++, j *= 2 ) {
            Node<AnyType> t1 = trees[i];
            Node<AnyType> t2 = i < another.trees.length ? another.trees[i] : null;

            int whichCase = t1 == null ? 0 : 1;
            whichCase += t2 == null ? 0 : 2;
            whichCase += carry == null ? 0 : 4;

            switch( whichCase ) {
                case 0 : // no trees
                case 1 : // only t1
                    break;
                case 2 : // only t2
                    trees[i] = t2;
                    another.trees[i] = null;
                    break;
                case 3 : // t1 merge t2
                    carry = combine( t1, t2 );
                    trees[i] = another.trees[i] = null;
                    break;
                case 4 : // only carry
                    trees[i] = carry;
                    carry = null;
                    break;
                case 5 : // t1 merge carry
                    carry = combine( t1, carry );
                    trees[i] = null;
                    break;
                case 6 : // t2 merge carry
                    carry = combine( t2, carry );
                    another.trees[i] = null;
                    break;
                case 7 : // t1 merge t2, retain carry
                    trees[i] = carry;
                    carry = combine( t1, t2 );
                    another.trees[i] = null;
                    break;
            }

        }

        for( int i=0; i < another.trees.length; i++ )
            another.trees[i] = null;
        another.size = 0;
    }

    public void insert( AnyType data ) {
        if( size == 0 )
            trees[0] = new Node<>( data, null, null );

        BinomialQueue<AnyType> another = new BinomialQueue<>();
        another.insert( data );

        merge( another );
    }

    public AnyType findMin() {
        if( isEmpty() )
            throw new java.util.NoSuchElementException();

        AnyType min = trees[0].data;
        for( int i=0; i < trees.length; i++ )
            if( trees[i] != null && min.compareTo( trees[i].data ) > 0 )
                min = trees[i].data;

        return min;
    }

    public AnyType deleteMin() {
        if( isEmpty() )
            throw new java.util.NoSuchElementException();

        int minIndex = findMinIndex();
        AnyType min = trees[ minIndex ].data;

        BinomialQueue<AnyType> deletedQueue = new BinomialQueue<>();
        deletedQueue.enlargeCapacity( minIndex + 1 );
        deletedQueue.size = ( 1 << minIndex ) - 1;

        Node<AnyType> deletedTree = trees[ minIndex ].firstChild;
        for( int i=minIndex - 1; i >= 0; i-- ) {
            deletedQueue.trees[ i ] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.trees[i].nextSibling = null;
        }

        trees[ minIndex ] = null;
        size -= deletedQueue.size + 1;

        merge( deletedQueue );

        return min;
    }


    //-------------------------------------------------------------------------------
    //--------------------------------| inner class |--------------------------------
    //-------------------------------------------------------------------------------


    private static class Node<AnyType> {

        public AnyType data;
        public Node<AnyType> firstChild;
        public Node<AnyType> nextSibling;


        public Node( AnyType data ) { this( data, null, null ); }

        public Node( AnyType data, Node<AnyType> firstChild, Node<AnyType> nextSibling ) {
            this.data = data; this.firstChild = firstChild; this.nextSibling = nextSibling;
        }

    }


    //-----------------------------------------------------------------------------------
    //--------------------------------| private methods |--------------------------------
    //-----------------------------------------------------------------------------------


    private int capacity() { return ( 1 << trees.length ) - 1; }

    private void enlargeCapacity( int capacity ) {
        if( capacity < size )
            return ;

        Node<AnyType>[] old = trees;
        trees = (Node<AnyType>[]) new Object[ capacity ];
        for( int i=0; i < trees.length; i++ )
            trees[i] = null;
        for( int i=0; i < old.length; i++ )
            trees[i] = old[i];
    }

    private Node<AnyType> combine( Node<AnyType> t1, Node<AnyType> t2 ) {
        if( t1.data.compareTo( t2.data ) > 0 )
            return combine( t2, t1 );
        t2.nextSibling = t1.firstChild;
        t1.firstChild = t2;

        return t1;
    }

    private int findMinIndex() {
        if( isEmpty() )
            throw new java.util.NoSuchElementException();

        AnyType min = trees[0].data;
        int minIndex = 0;
        for( int i=0; i < trees.length; i++ )
            if( trees[i] != null && min.compareTo( trees[i].data ) > 0 ) {
                min = trees[i].data;
                minIndex = i;
            }

        return minIndex;
    }

}
