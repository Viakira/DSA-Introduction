package src.Sort;

public class HeapSort {

    public static <AnyType extends Comparable<? super AnyType>>
    void heapSort( AnyType[] arr ) {
        for( int i = arr.length / 2 - 1; i >= 0; i-- )
            percolateDown( arr, i, arr.length );
        for( int i = arr.length - 1; i > 0; i-- ) {
            AnyType data = arr[0];
            arr[0] = arr[i];
            arr[i] = data;
            percolateDown( arr, 0, i );
        }
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void percolateDown( AnyType[] arr, int idx, int end ) {
        AnyType data = arr[ idx ];
        for( int child; 2 * idx + 1 < end; idx = child ) {
            child = 2 * idx + 1;
            if( child != end - 1 && arr[ child ].compareTo( arr[ child + 1 ] ) < 0 )
                child++;
            if( data.compareTo( arr[ child ] ) < 0 )
                arr[ idx ] = arr[ child ];
            else
                break;
        }
        arr[ idx ] = data;
    }

}
