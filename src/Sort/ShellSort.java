package src.Sort;

public class ShellSort {

    public static <AnyType extends Comparable<? super AnyType>>
    void doubleShellSort( AnyType[] arr ) {
        for( int gap = arr.length / 2; gap > 0; gap /= 2 )
            for( int i = gap; i < arr.length; i++ ) {
                AnyType data = arr[i];
                int j;
                for( j = i; j >= gap && data.compareTo( arr[j] ) < 0; j -= gap )
                    arr[j] = arr[ j - gap ];
                arr[j] = data;
            }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void hibbardShellSort( AnyType[] arr ) {
        Integer[] hibbard = hibbard( arr.length );
        for( int i = hibbard.length - 1; i >= 0; i-- ) {
            int gap = hibbard[i];
            for (int j = gap; j < arr.length; j++ ) {
                AnyType data = arr[j];
                int k;
                for (k = j; k >= gap && data.compareTo( arr[k] ) < 0; k -= gap )
                    arr[k] = arr[ k - gap ];
                arr[k] = data;
            }
        }
    }

    private static Integer[] hibbard( int length ) {
        java.util.List<Integer> hibbard = new java.util.ArrayList<>();
        for( int i=1; i < length; ) {
            hibbard.add( i );
            i = i * 2 + 1;
        }
        return hibbard.toArray( new Integer[0] );
    }

}
