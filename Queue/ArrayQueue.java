package Queue;

public class ArrayQueue<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;

    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private AnyType[] queue;
    private int head;
    private int end;
    private int size;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public ArrayQueue() { clear(); }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        queue = (AnyType[]) new Object[ DEFAULT_CAPACITY ];
        head = end = size = 0;
    }

    //----------------------------------------------------------------------------------

    public void enqueue( AnyType data ) {
        if( size == queue.length )
            ensureCapacity( size * 2 + 1 );

        queue[ end ] = data;
        end = ( end + 1 ) % queue.length;

        size++;
    }

    public AnyType dequeue() {
        if( isEmpty() )
            throw new java.util.NoSuchElementException();

        AnyType old = queue[ head ];
        head = ( head + 1 ) % queue.length;

        size--;
        return old;
    }

    //----------------------------------------------------------------------------------

    public void ensureCapacity( int capacity ) {
        if( capacity < size )
            return ;

        AnyType[] old = queue;
        queue = (AnyType[]) new Object[ capacity ];
        for( int i=0; i < size; i++ )
            queue[i] = old[i];
    }

    public void trimToSize() { ensureCapacity( size ); }

}