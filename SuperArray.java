/*
Team Loner = Dhruba Roy
APCS1 Pd 9
HW #42: Array of Titanium
2015-12-02
*/
public class SuperArray implements ListComparable {
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private Comparable _lastPos;

    //size of this instance of SuperArray
    private Comparable _size;

		
    //~~~~~METHODS~~~~~
    //default constructor – initializes 10-item array
    public SuperArray() 
    { 
	_data = new Comparable[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() 
    { 
	String foo = "[";
	for( Comparable i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }

		
    //double capacity of this SuperArray
    private void expand() 
    { 
	Comparable[] temp = new Comparable[ _data.length * 2 ];
	for( Comparable i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

		
    //accessor -- return value at specified index
    public Comparable get( int index ) { return _data[index]; }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) 
    { 
 	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) {
	if (_lastPos >= _data.length) { //if array is full
		expand();
	}
		_data[++_lastPos] = newVal;
		size();
    }
    
    
    //inserts an item at index
    //shifts existing elements to the right
    public void add( Comparable index, Comparable newVal ) {
	if( index <= _lastPos ){

	    if( _lastPos >= _data.length - 1 ) { //if array is almost filled up
	    	expand();
	    }

	   Comparable temp = set( index, newVal ); //holds last val

       for(Comparable i = index + 1; i <= _lastPos + 1; i++){//also increments _lastPos
          temp = set(i, temp); //temp is redefined after the right side is done
          //System.out.prComparableln(temp + " " + this);      	
       }
       _lastPos++;
       size();
    }

    else {
    	add(newVal);
    }
}
         
   
    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( Comparable index ) {
    	
    Comparable temp = set( _lastPos, 0 ); //holds last val

	for (Comparable i = --_lastPos; i >= index; i--){ //also decrements _lastPos
	    temp = set(i, temp); //temp is redefined after the right side is done
	}
	size();
    }
    
    
    
    //return number of meaningful items in _data
    public int size() {
    	_size = _lastPos + 1;
		return _size;
    }
/*public boolean isSorted() {
	int counter = 1;
	while ((_data[counter - 1].compareTo(_data[counter]) < 0) && counter < _data.length) 
	    {counter += 1;}
	return (counter == _data.length - 1);
    }

    //linSearch
public int linSearch (Comparable obj) {
	int counter = 0;
	while (counter < _data.length) {
	    if (obj.compareTo (_data[counter]) == 0) {return counter;}
	    else {counter += 1;}
	}
	return -1;
    }
 */
    //main method for testing
    public static void main( String[] args ) 
    {
	SuperArray curtis = new SuperArray();
	System.out.println("Printing empty SuperArray curtis...");
	System.out.println(curtis);

	for( int i = 0; i < curtis._data.length; i++ ) {
	    curtis.set(i,i*2);
	    curtis._size++; //necessary bc no add() method yet
	}

	System.out.println("Printing populated SuperArray curtis...");
	System.out.println(curtis);

	System.out.println("testing get()...");
	for( int i = 0; i < curtis._size; i++ ) {
	    System.out.print( "item at index" + i + ":\t" );
	    System.out.println( curtis.get(i) );
	}

	System.out.println("Expanded SuperArray curtis:");
	curtis.expand();
	System.out.println(curtis);

	SuperArray mayfield = new SuperArray();
	System.out.println("Printing empty SuperArray mayfield...");
	System.out.println(mayfield);

	  mayfield.add(5);
	  mayfield.add(4);
	  mayfield.add(3);
	  mayfield.add(2);
	  mayfield.add(1);
	  
	  System.out.println("Printing populated SuperArray mayfield...");
	  System.out.println(mayfield);

	  mayfield.remove(3);
	  System.out.println("Printing SuperArray mayfield post-remove...");
	  System.out.println(mayfield);
	  mayfield.remove(3);
	  System.out.println("Printing SuperArray mayfield post-remove...");
	  System.out.println(mayfield);

	  mayfield.add(3,99);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
	  mayfield.add(2,88);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
	  mayfield.add(1,77);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
	//*****INSERT ANY ADDITIONAL TEST CALLS HERE*****
	  ListInt Iloveyou = new SuperArray();
    }//end main
		
}//end class
