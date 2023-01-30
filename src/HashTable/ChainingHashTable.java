package src.HashTable;

import java.util.List;
import java.util.LinkedList;

public class ChainingHashTable<AnyType> {

    private static final int DEFAULT_CAPACITY = 101;
    private static final int MIN_CAPACITY = 11;

    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private List<AnyType>[] hashTable;
    private int size;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public ChainingHashTable() { this( DEFAULT_CAPACITY ); }
    public ChainingHashTable( int capacity ) {
        hashTable = new LinkedList[ nextPrime(capacity) ];
        for( int i=0; i < hashTable.length; i++ )
            hashTable[i] = new LinkedList<>();
        size = 0;
    }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public void clear() {
        hashTable = new LinkedList[ DEFAULT_CAPACITY ];
        for( int i=0; i < hashTable.length; i++ )
            hashTable[i] = new LinkedList<>();
        size = 0;
    }

    public boolean contains( AnyType data ) {
        List<AnyType> list = hashTable[ myHash(data) ];
        return list.contains( data );
    }

    public void insert( AnyType data ) {
        List<AnyType> list = hashTable[ myHash(data) ];
        if( !list.contains( data ) ) {
            list.add( data );
            size++;

            if( size > hashTable.length )
                reHash();
        }
    }

    public void remove( AnyType data ) {
        List<AnyType> list = hashTable[ myHash(data) ];
        if( list.contains( data ) ) {
            list.remove( data );
            size--;
        }
    }


    //-----------------------------------------------------------------------------------
    //--------------------------------| private methods |--------------------------------
    //-----------------------------------------------------------------------------------


    private int myHash( AnyType data ) {
        int hashVal = data.hashCode();
        hashVal %= hashTable.length;
        if( hashVal < 0 )
            hashVal += hashTable.length;
        return hashVal;
    }

    private void reHash() {
        List<AnyType>[] old = hashTable;
        hashTable = new LinkedList[ nextPrime( hashTable.length * 2 ) ];
        for( int i=0; i < hashTable.length; i++ )
            hashTable[i] = new LinkedList<>();

        size = 0;
        for( int i=0; i < old.length; i++ )
            for( AnyType data : old[i] )
                insert( data );
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