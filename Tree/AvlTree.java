package Tree;

public class AvlTree<AnyType extends Comparable<? super AnyType>> {

    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private Node<AnyType> root;
    private int size;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public AvlTree() { clear(); }


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
        public int height;


        public Node( AnyType data ) { this( data, null, null ); }

        public Node(AnyType data, Node<AnyType> left, Node<AnyType> right ) {
            this.data = data; this.left = left; this.right = right; height = 0;
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

    private Node<AnyType> insert(AnyType data, Node<AnyType> node ) {
        if( node == null )
            return new Node<>( data, null, null );

        int compareResult = data.compareTo( node.data );
        if( compareResult < 0 )
            node.left = insert( data, node.left );
        else if( compareResult > 0 )
            node.right = insert( data, node.right );

        return balance( node );
    }

    private Node<AnyType> remove(AnyType data, Node<AnyType> node ) {
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

        return balance( node );
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

    //-----------------------------------------------------------------------------------

    private int height( Node<AnyType> node ) {
        return node == null ? -1 : node.height;
    }

    //-----------------------------------------------------------------------------------

    private Node<AnyType> balance( Node<AnyType> node ) {
        if( node == null )
            return node;

        if( height( node.left ) - height( node.right ) > 1 )
            if( height( node.left.left ) >= height( node.left.right ) )
                node = leftRotate( node );
            else
                node = rightLeftRotate( node );
        else if( height( node.right ) - height( node.left ) > 1 )
            if( height( node.right.right ) >= height( node.right.left ) )
                node = rightRotate( node );
            else
                node = leftRightRotate( node );

        node.height = Math.max( height(node.left), height(node.right) ) + 1;

        return node;
    }

    private Node<AnyType> leftRotate( Node<AnyType> node ) {
        Node<AnyType> left = node.left;
        node.left = left.right;
        left.right = node;

        node.height = Math.max( height( node.left ), height( node.right ) ) + 1;
        left.height = Math.max( height( left.left ), height( left.right) ) + 1;

        return left;
    }

    private Node<AnyType> rightRotate( Node<AnyType> node ) {
        Node<AnyType> right = node.right;
        node.right = right.left;
        right.left = node;

        node.height = Math.max( height( node.left ), height( node.right ) ) + 1;
        right.height = Math.max( height( right.right ), height( right.right) ) + 1;

        return right;
    }

    private Node<AnyType> leftRightRotate( Node<AnyType> node ) {
        node.left = rightRotate( node.left );
        return leftRotate( node );
    }

    private Node<AnyType> rightLeftRotate( Node<AnyType> node ) {
        node.right = leftRotate( node.right );
        return rightRotate( node );
    }

}
