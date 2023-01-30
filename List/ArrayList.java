package List;

public class ArrayList<AnyType> implements Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;


    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private AnyType[] list;
    private int size;
    private int modCount;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public ArrayList() { this( DEFAULT_CAPACITY ); }

    public ArrayList( int capacity ) { list = (AnyType[]) new Object[ capacity ]; size = 0; modCount = 0; }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() { list = (AnyType[]) new Object[ DEFAULT_CAPACITY ]; size = 0; modCount++; }

    //----------------------------------------------------------------------------------

    public AnyType get( int idx ) {
        if( idx < 0 || idx >= size )
            throw new ArrayIndexOutOfBoundsException();

        return list[idx];
    }

    public AnyType set( int idx, AnyType data ) {
        if( idx < 0 || idx >= size )
            throw new ArrayIndexOutOfBoundsException();

        AnyType old = list[idx];
        list[idx] = data;
        return old;
    }

    public void insert( int idx, AnyType data ) {
        if( idx < 0 || idx > size )
            throw new ArrayIndexOutOfBoundsException();

        if( size == list.length )
            ensureCapacity( size * 2 + 1 );

        for( int i=size; i > idx; i--)
            list[i] = list[i-1];
        list[idx] = data;

        size++;
        modCount++;
    }

    public AnyType remove( int idx ) {
        if( idx < 0 || idx >= size )
            throw new ArrayIndexOutOfBoundsException();

        AnyType removed = list[idx];
        for( int i=idx; i < size - 1; i++ )
            list[i] = list[i+1];

        size--;
        modCount++;
        return removed;
    }

    public boolean add( AnyType data ) {
        insert( size, data );
        return true;
    }

    //----------------------------------------------------------------------------------

    public void ensureCapacity( int capacity ) {
        if( capacity < size )
            return ;

        AnyType[] old = list;
        list = (AnyType[]) new Object[ capacity ];
        for( int i=0; i < size; i++ )
            list[i] = old[i];
    }

    public void trimToSize() { ensureCapacity( size ); }

    //----------------------------------------------------------------------------------

    public java.util.Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }


    //-------------------------------------------------------------------------------
    //--------------------------------| inner class |--------------------------------
    //-------------------------------------------------------------------------------


    private class ArrayListIterator implements java.util.Iterator<AnyType> {

        private int current;
        private int expectedModCount = modCount;
        private boolean canRemove = false;


        public boolean hasNext() { return current < size; }

        public AnyType next() {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException();
            if( !hasNext() )
                throw new java.util.NoSuchElementException();

            canRemove = true;
            return list[current++];
        }

        public void remove() {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException();
            if( !canRemove )
                throw new IllegalStateException();

            canRemove = false;
            expectedModCount++;
            ArrayList.this.remove( --current );
        }

    }

}