package src.HashTable;

public class ProbingHashTable<AnyType> {

    private static final int DEFAULT_CAPACITY = 101;
    private static final int MIN_CAPACITY = 11;

    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private HashItem<AnyType>[] hashTable;
    private int size;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public ProbingHashTable() { this( DEFAULT_CAPACITY ); }

    public ProbingHashTable( int capacity ) {
        hashTable = new HashItem[ nextPrime( capacity ) ];
        java.util.Arrays.fill( hashTable, null );
        size = 0;
    }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public void clear() {
        hashTable = new HashItem[ DEFAULT_CAPACITY ];
        java.util.Arrays.fill( hashTable, null );
        size = 0;
    }

    public boolean contains( AnyType data ) {
        int pos = findPos( data );
        return !deleted( pos );
    }

    public void insert( AnyType data ) {
        int pos = findPos( data );

        if( !deleted( pos ) ) {
            hashTable[ pos ].count++;
            return ;
        }

        hashTable[ pos ] = new HashItem<>( data );
        size++;

        if( size > hashTable.length / 2 )
            reHash();
    }

    public void remove( AnyType data ) {
        int pos = findPos( data );

        if( !deleted( pos ) ) {
            hashTable[ pos ].count--;
            if( hashTable[ pos ].count < 0 )
                hashTable[ pos ].count = 0;
        }
    }

    public void removeAll( AnyType data ) {
        int pos = findPos( data );

        if( !deleted( pos ) )
            hashTable[ pos ].count = 0;
    }


    //-------------------------------------------------------------------------------
    //--------------------------------| inner class |--------------------------------
    //-------------------------------------------------------------------------------


    private static class HashItem<AnyType> {

        public AnyType data;
        public int count;


        public HashItem( AnyType data ) { this( data, 1 ); }
        public HashItem( AnyType data, int count ) {
            this.data = data; this.count = count;
        }

    }


    //-----------------------------------------------------------------------------------
    //--------------------------------| private methods |--------------------------------
    //-----------------------------------------------------------------------------------


    private int findPos( AnyType data ) { return findQuadraticPos( data ); }

    private boolean deleted( int pos ) {
        return hashTable[ pos ] == null || hashTable[ pos ].count == 0;
    }

    //-----------------------------------------------------------------------------------

    private int findLinearPos( AnyType data ) {
        int pos = myHash( data );

        while( hashTable[ pos ] != null && !hashTable[ pos ].data.equals( data ) ) {
            pos++;
            if( pos > hashTable.length )
                pos -= hashTable.length;
        }

        return pos;
    }

    private int findQuadraticPos( AnyType data ) {
        int pos = myHash( data );

        int offset = 1;
        while( hashTable[ pos ] != null && !hashTable[ pos ].data.equals( data ) ) {
            pos += offset;
            offset += 2;
            if( pos > hashTable.length )
                pos -= hashTable.length;
        }

        return pos;
    }

    private int findDoubleHashPos( AnyType data ) {
        int pos = myHash( data );

        while( hashTable[ pos ] != null && !hashTable[ pos ].data.equals( data ) ) {
            pos += anotherHash( data );
            if( pos > hashTable.length )
                pos -= hashTable.length;
        }

        return pos;
    }

    //-----------------------------------------------------------------------------------

    private int myHash( AnyType data ) {
        int hashVal = data.hashCode();
        hashVal %= hashTable.length;
        if( hashVal < 0 )
            hashVal += hashTable.length;
        return hashVal;
    }

    private int anotherHash( AnyType data ) {
        int hashVal = data.hashCode();

        int prime = nextPrime( hashTable.length / 3 );
        if( hashTable.length == MIN_CAPACITY )
            prime = 7;

        return prime - ( hashVal % prime );
    }

    private void reHash() {
        HashItem<AnyType>[] old = hashTable;
        hashTable = new HashItem[ nextPrime( hashTable.length * 2 ) ];
        java.util.Arrays.fill( hashTable, null );

        size = 0;
        for( int i=0; i < old.length; i++ )
            if( old[i] != null && old[i].count != 0 )
                insert( old[i].data );
    }

    //-----------------------------------------------------------------------------------

    private static int nextPrime( int num ) {
        if( num < MIN_CAPACITY )
            return MIN_CAPACITY;

        if( num % 2 == 0 )
            num++;
        while( !isPrime(num) )
            num += 2;
        return num;
    }

    private static boolean isPrime( int odd ) {
        for( int i=3; i * i <= odd; i++ )
            if( odd % i == 0 )
                return false;
        return true;
    }

}