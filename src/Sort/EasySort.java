package src.Sort;

public class EasySort {

    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort( AnyType[] arr ) {
        for( int i=1; i < arr.length; i++ ) {
            AnyType data = arr[i];
            int j;
            for( j = i; j > 0 && data.compareTo( arr[ j-1 ] ) < 0 ; j-- )
                arr[j] = arr[ j-1 ];
            arr[j] = data;
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void bubbleSort( AnyType[] arr ) {
        for( int i = arr.length - 1; i > 0; i-- )
            for( int j=0; j < i; j++ )
                if( arr[j].compareTo( arr[ j+1 ] ) > 0 ) {
                     AnyType data = arr[j];
                     arr[j] = arr[ j+1 ];
                     arr[ j+1 ] = data;
                }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void selectionSort( AnyType[] arr ) {
        for( int i=arr.length - 1; i > 0; i-- ) {
            int idx = 0;
            for( int j=1; j <= i; j++ )
                if( arr[ idx ].compareTo( arr[j] ) < 0 ) {
                    idx = j;
                }
            AnyType max = arr[ idx ];
            arr[ idx ] = arr[i];
            arr[i] = max;
        }
    }

}
