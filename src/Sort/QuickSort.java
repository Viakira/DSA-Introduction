package src.Sort;

public class QuickSort {

    public static final int CUTOFF = 10;

    public static <AnyType extends Comparable<? super AnyType>>
    void quickSort( AnyType[] arr ) {
        quickSort( arr, 0, arr.length - 1 );
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void quickSort( AnyType[] arr, int left, int right ) {
        if( left + CUTOFF > right )
            insertionSort( arr, left, right );

        AnyType pivot = median( arr, left, right );

        int i = left, j = right - 1;
        while(true) {
            while( arr[ ++i ].compareTo( pivot ) < 0 ) ;
            while( arr[ --j ].compareTo( pivot ) > 0 ) ;
            if( i < j )
                swap( arr, i, j );
            else
                break;
        }
        swap( arr, i, right - 1 );

        quickSort( arr, left, i-1 );
        quickSort( arr, i+1, right );
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType[] arr, int left, int right ) {
        for( int i=left; i <= right; i++ ) {
            AnyType data = arr[i];
            int j;
            for( j = i; j > 0 && data.compareTo( arr[ j-1 ] ) < 0 ; j-- )
                arr[j] = arr[ j-1 ];
            arr[j] = data;
        }
    }

    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median( AnyType[] arr, int left, int right ) {
        int center = ( left + right ) / 2;
        if( arr[ left ].compareTo( arr[ center ] ) > 0 )
            swap( arr, left, center );
        if( arr[ left ].compareTo( arr[ right ] ) > 0 )
            swap( arr, left, right );
        if( arr[center ].compareTo( arr[ right ] ) > 0 )
            swap( arr, center, right );

        swap( arr, center, right - 1 );
        return arr[ right - 1 ];
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void swap( AnyType[] arr, int idx1, int idx2 ) {
        AnyType data = arr[ idx1 ];
        arr[ idx1 ] = arr[ idx2 ];
        arr[ idx2 ] = data;
    }

}
