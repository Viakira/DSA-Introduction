package src.Heap;

public class LeftistHeap<AnyType extends Comparable<? super AnyType>> {


    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private Node<AnyType> root;
    private int size;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public LeftistHeap() { clear(); }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() { root = null; size = 0; }

    //----------------------------------------------------------------------------------

    public void merge( LeftistHeap<AnyType> another ) {
        if( this == another )
            return ;

        root = mergeTest( root, another.root );
        another.root = null;
    }

    public AnyType findMin() { return root.data; }

    public void insert( AnyType data ) {
        root = merge( root, new Node<>( data ) );
    }

    public AnyType deleteMin() {
        if( isEmpty() )
            throw new java.util.NoSuchElementException();

        AnyType min = findMin();
        root = merge( root.left, root.right );

        return min;
    }


    //-------------------------------------------------------------------------------
    //--------------------------------| inner class |--------------------------------
    //-------------------------------------------------------------------------------


    private static class Node<AnyType> {

        public AnyType data;
        public Node<AnyType> left;
        public Node<AnyType> right;
        public int npl;


        public Node( AnyType data ) { this( data, null, null ); }

        public Node( AnyType data, Node<AnyType> left, Node<AnyType> right ) {
            this.data = data; this.left = left; this.right = right; npl = 0;
        }

    }

    //-----------------------------------------------------------------------------------
    //--------------------------------| private methods |--------------------------------
    //-----------------------------------------------------------------------------------

    private Node<AnyType> mergeTest( Node<AnyType> h1, Node<AnyType> h2 ) {
        if( h1 == null )
            return h2;
        if( h2 == null )
            return h1;

        if( h1.data.compareTo( h2.data ) < 0 )
            return merge( h1, h2 );
        else
            return merge( h2, h1 );
    }

    private Node<AnyType> merge( Node<AnyType> h1, Node<AnyType> h2 ) {
        if( h1.left == null )
            h1.left = h2;
        else {
            h1.right = merge( h1.right, h2 );
            if( h1.left.npl < h1.right.npl ) {
                Node<AnyType> h = h1.left;
                h1.left = h1.right;
                h1.right = h;
            }
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }

}
