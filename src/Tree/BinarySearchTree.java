package src.Tree;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private Node<AnyType> root;
    private int size;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public BinarySearchTree() { clear(); }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() { root = null; size = 0; }

    //----------------------------------------------------------------------------------

    public boolean contains( AnyType data ) { return contains( data, root ); }

    public void insert( AnyType data ) { root = insert( data, root ); size++; }

    public void remove( AnyType data ) { root = remove( data, root ); size--; }

    //----------------------------------------------------------------------------------

    public AnyType findMin() {
        if( isEmpty() )
            throw new java.util.NoSuchElementException();
        return findMin( root );
    }

    public AnyType findMax() {
        if( isEmpty() )
            throw new java.util.NoSuchElementException();
        return findMax( root );
    }

    //-------------------------------------------------------------------------------
    //--------------------------------| inner class |--------------------------------
    //-------------------------------------------------------------------------------


    private static class Node<AnyType> {

        public AnyType data;
        public Node<AnyType> left;
        public Node<AnyType> right;


        public Node( AnyType data ) { this( data, null, null ); }

        public Node( AnyType data, Node<AnyType> left, Node<AnyType> right ) {
            this.data = data; this.left = left; this.right = right;
        }

    }


    //-----------------------------------------------------------------------------------
    //--------------------------------| private methods |--------------------------------
    //-----------------------------------------------------------------------------------


    private boolean contains( AnyType data, Node<AnyType> node ) {
        if( node == null )
            return false;

        int compareResult = data.compareTo( node.data );
        if( compareResult < 0 )
            return contains( data, node.left );
        else if( compareResult > 0 )
            return contains( data, node.right );
        else
            return true;
    }

    private Node<AnyType> insert( AnyType data, Node<AnyType> node ) {
        if( node == null )
            return new Node<>( data, null, null );

        int compareResult = data.compareTo( node.data );
        if( compareResult < 0 )
            node.left = insert( data, node.left );
        else if( compareResult > 0 )
            node.right = insert( data, node.right );


        return node;
    }

    private Node<AnyType> remove( AnyType data, Node<AnyType> node ) {
        if( node == null )
            return node;

        int compareResult = data.compareTo( node.data );
        if( compareResult < 0 )
            node.left = remove( data, node.left );
        else if( compareResult > 0 )
            node.right = remove( data, node.right );
        else if( node.left != null && node.right != null ) {
            node.data = findMax( node.left );
            node.left = remove( node.data, node.left );
        } else
            node = ( node.left != null ) ? node.left : node.right;

        return node;
    }

    //-----------------------------------------------------------------------------------

    private AnyType findMin( Node<AnyType> node ) {
        if( node.left == null )
            return node.data;
        return findMin( node.left );
    }

    private AnyType findMax( Node<AnyType> node ) {
        if( node.right == null )
            return node.data;
        return findMax( node.right );
    }

}