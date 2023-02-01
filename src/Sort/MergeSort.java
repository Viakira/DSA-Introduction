package src.Sort;

public class MergeSort {

    public static <AnyType extends Comparable<? super AnyType>>
    void mergeSort( AnyType[] arr ) {
        AnyType[] tmpArr = (AnyType[]) new Comparable[ arr.length ];
        mergeSort( arr, tmpArr, 0, arr.length - 1 );
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort( AnyType[] arr, AnyType[] tmpArr, int left, int right ) {
        if( left >= right )
            return ;

        int center = ( left + right ) / 2;
        mergeSort( arr, tmpArr, left, center );
        mergeSort( arr, tmpArr, center + 1, right );
        merge( arr, tmpArr, left, right );
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void merge( AnyType[] arr, AnyType[] tmpArr, int leftPos, int rightEnd ) {
        int leftEnd = ( leftPos + rightEnd ) / 2;
        int rightPos = leftEnd + 1;
        int pos = leftPos;
        int length = rightEnd - leftPos + 1;

        while( leftPos <= leftEnd && rightPos <= rightEnd )
            if( arr[ leftPos ].compareTo( arr[ rightPos ] ) < 0 )
                tmpArr[ pos++ ] = arr[ leftPos++ ];
            else
                tmpArr[ pos++ ] = arr[ rightPos++ ];

        while( leftPos <= leftEnd )
            tmpArr[ pos++ ] = arr[ leftPos++ ];
        while( rightPos <= rightEnd )
            tmpArr[ pos++ ] = arr[ rightPos++ ];

        for( int i=0; i < length; i++, rightEnd-- )
            arr[ rightEnd ] = tmpArr[ rightEnd ];
    }

}
