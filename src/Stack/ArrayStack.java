package src.Stack;

public class ArrayStack<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;

    //------------------------------------------------------------------------------------
    //--------------------------------| member variables |--------------------------------
    //------------------------------------------------------------------------------------


    private AnyType[] stack;
    private int topOfStack;


    //--------------------------------------------------------------------------------
    //--------------------------------| constructors |--------------------------------
    //--------------------------------------------------------------------------------


    public ArrayStack() { this( DEFAULT_CAPACITY ); }
    public ArrayStack( int capacity ) { stack = (AnyType[]) new Object[ capacity ]; topOfStack = -1; }


    //----------------------------------------------------------------------------------
    //--------------------------------| public methods |--------------------------------
    //----------------------------------------------------------------------------------


    public int size() { return topOfStack + 1; }

    public boolean isEmpty() { return topOfStack == -1; }

    public void clear() { stack = (AnyType[]) new Object[ DEFAULT_CAPACITY ]; topOfStack = -1; }

    //----------------------------------------------------------------------------------

    public AnyType top() {
        if( isEmpty() )
            throw new java.util.EmptyStackException();

        return stack[ topOfStack ];
    }

    public void push( AnyType data ) {
        if( size() == stack.length )
            ensureCapacity( size() * 2 + 1 );

        stack[ ++topOfStack ] = data;
    }

    public AnyType pop() {
        if( isEmpty() )
            throw new java.util.EmptyStackException();

        return stack[ topOfStack-- ];
    }

    //----------------------------------------------------------------------------------

    public void ensureCapacity( int capacity ) {
        if( capacity < size() )
            return ;

        AnyType[] old = stack;
        stack = (AnyType[]) new Object[ capacity ];
        for( int i=0; i < size(); i++)
            stack[i] = old[i];
    }

    public void trimToSize() { ensureCapacity( size() ); }

}