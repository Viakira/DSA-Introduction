package src.Sort;

import java.util.ArrayList;

public class BucketSort {

    public static void bucketSort( Integer[] arr ) {
        int min = arr[0];
        int max = arr[0];
        for( Integer num : arr ) {
            min = ( min > num ) ? num : min;
            max = ( max < num ) ? num : max;
        }
        bucketSort( arr, min, max );
    }

    public static void bucketSort( Integer[] arr, int min, int max ) {
        Integer[] buckets = new Integer[ max - min + 1 ];
        java.util.Arrays.fill( buckets, 0 );

        for( Integer num : arr )
            buckets[ num - min ]++;

        for( int i=0, j=0; i < buckets.length; i++ ) {
            while( buckets[i]-- != 0 )
                arr[ j++ ] = i + min;
        }
    }

    // assume all strings have same length
    // assume all characters in ASCII
    public static void radixSort( String[] arr ) {
        final int BUCKETS = 256;
        java.util.ArrayList<String>[] buckets = new java.util.ArrayList<>[ BUCKETS ];
        for( int i=0; i < BUCKETS; i++ )
            buckets[i] = new ArrayList<>();

        int stringLen = arr[0].length();
        for( int pos = stringLen - 1; pos >= 0; pos-- ) {
            for( String s : arr )
                buckets[ s.charAt( pos ) ].add( s );
            int idx = 0;
            for( ArrayList<String> bucket : buckets ) {
                for( String s : bucket )
                    arr[ idx++ ] = s;
                bucket.clear();
            }
        }
    }

    // assume all strings have same length
    // assume all characters in ASCII
    public static void countingRadixSort( String[] arr ) {
        final int BUCKETS = 256;

        int length = arr.length;
        String[] buffer = new String[ length ];

        int stringLen = arr[0].length();
        String[] in = arr;
        String[] out = buffer;
        for( int pos = stringLen - 1; pos >= 0; pos-- ) {
            int[] count = new int[ BUCKETS + 1 ];
            java.util.Arrays.fill( count, 0 );

            for( int i=0; i < length; i++ )
                count[ in[i].charAt( pos ) + 1 ]++;

            for( int i=1; i <= BUCKETS; i++ )
                count[i] += count[ i-1 ];

            for( int i=0; i < length; i++ )
                out[ count[ in[i].charAt( pos ) ]++ ] = in[i];

            String[] tmp = in;
            in = out;
            out = tmp;
        }

        if( stringLen % 2 == 1 )
            for( int i=0; i < arr.length; i++ )
                out[i] = in[i];
    }

}
