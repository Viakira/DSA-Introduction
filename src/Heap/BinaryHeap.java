package src.Heap;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {

    private static final int DEFAULT_CAPACITY = 10;

    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private AnyType[] heap;
    private int size;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public BinaryHeap() { this( DEFAULT_CAPACITY ); }

    public BinaryHeap( int capacity ) {
        heap = (AnyType[]) new Object[ capacity ];
        size = 0;
    }

    public BinaryHeap( AnyType[] array ) {
        size = array.length;
        heap = (AnyType[]) new Object[ size * 2 ];
        for( int i=0; i < array.length; i++ )
            heap[ i+1 ] = array[i];

        buildHeap();
    }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() { heap = (AnyType[]) new Object[ DEFAULT_CAPACITY ]; size = 0; }

    //----------------------------------------------------------------------------------

    public void insert( AnyType data ) {
        if( size == heap.length - 1 )
            enlargeCapacity( heap.length * 2 );

        int hole = ++size;
        for( heap[0] = data; data.compareTo( heap[ hole / 2 ] ) < 0; hole /= 2 )
            heap[ hole ] = heap[ hole / 2 ];
        heap[ hole ] = data;
    }

    public AnyType findMin() { return heap[1]; }

    public AnyType deleteMin() {
        if( isEmpty() )
            throw new java.util.NoSuchElementException();

        AnyType min = findMin();
        heap[1] = heap[ size-- ];
        percolateDown( 1 );

        return min;
    }


    //-----------------------------------------------------------------------------------
    //--------------------------------| private methods |--------------------------------
    //-----------------------------------------------------------------------------------


    private void enlargeCapacity( int capacity ) {
        if( capacity < size + 1 )
            return ;

        AnyType[] old = heap;
        heap = (AnyType[]) new Object[ capacity ];
        for( int i=1; i < size; i++ )
            heap[i] = old[i];
    }

    private void percolateDown( int hole ) {
        AnyType data = heap[ hole ];

        for( int child; hole * 2 <= size; hole = child ) {
            child = hole * 2;
            if( child != size && heap[ child ].compareTo( heap[ child + 1 ] ) < 0 )
                child++;
            if( heap[ child ].compareTo( data ) < 0 )
                heap[ hole ] = heap[ child ];
            else
                break;
        }

        heap[ hole ] = data;
    }

    private void buildHeap() {
        for( int i = size / 2; i > 0; i-- )
            percolateDown( i );
    }

}
