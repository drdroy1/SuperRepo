/*
Team HexVsBin -- Maxwell Anderson, Dhruba Roy
APCS1 pd9
HW #44: This or That or Fourteen Other Things
2015-12-08
*/

public class Hexadecimal implements Comparable {

    private final static String HEXDIGITS = "0123456789ABCDEF"; 
    private int _decNum;
    private String _hexNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() 
    { 
  	_decNum = 0;
  	_hexNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) 
    {
        _decNum = n;
        _hexNum = decToHexR(n);
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative Hexadecimal number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) 
    {
       _hexNum = s;
       _decNum = hexToDec(s);
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() 
    { 
        return _hexNum;
//      return Integer.toString(_decNum); -- Used for testing hexToDec
    }

    /*=====================================
      String decToHex(int) -- converts base-10 input to Hexadecimal
      pre:  n >= 0
      post: returns String of bits
      eg  decToHex(0) -> "0"
      decToHex(1) -> "1"
      decToHex(2) -> "2"
      decToHex(3) -> "3"
      decToHex(14) -> "E"
      =====================================*/
    public static String decToHex( int n ) 
    {
      String s = "";
      while (!(n<16))
      {
        s = HEXDIGITS.substring((n%16), (n%16)+1) + s;
        n = (n-(n%16))/16;        
      }
      s = HEXDIGITS.substring(n, n+1) + s;
      return s;
    }

    public static String decToHexR( int n )
    { 
        if (n < 16)
        {
            return HEXDIGITS.substring(n, n+1);
        }
        String s = "";
        s = HEXDIGITS.substring((n%16), (n%16)+1);
        int k = (n-(n%16))/16;
        return (decToHexR(k)+s);
    }


    /*=====================================
      String hexToDec(String) -- converts base-10 input to Hexadecimal
      pre:  s represents non-negative Hexadecimal number
      post: returns String of bits
      eg  
      hexToDec("0") -> 0
      hexToDec("1") -> 1
      hexToDec("10") -> 10
      hexToDec("11") -> 11
      hexToDec("AA") -> 170
      =====================================*/
    public static int hexToDec( String s ) 
    {
      int a = 0;
      for (int i = 0; i < s.length(); i++)
      {
        a += (int)(Math.pow(16, s.length()-i-1))*HEXDIGITS.indexOf(s.substring(i, i+1));
      }
      return a;
    }

    public static int hexToDecR( String s ) 
    { 
      if (s.length() == 0)
      {
        return 0;
      }
      return ((int)(HEXDIGITS.indexOf((s.substring(0,1)))*Math.pow(16, s.length()-1)) + hexToDecR(s.substring(1, s.length())));
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal Hexadecimal values
      =============================================*/
      
    public boolean equals( Object other ) { 
      if (!(other instanceof Hexadecimal))
      {
      	throw new ClassCastException ( "\nMy first error message!" + " compareTo()/equals() input not a Hex.");
      }
      other = (Hexadecimal)other;
      if (other.toString().equals(this.toString()))
      {
        return true;
      }
      return false;
    }

    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/

public int compareTo( Object other ) {
      if (!(other instanceof Comparable)){
        throw new ClassCastException("Bad Input" + "compareTo() input not a Hexadecimal.");
      }
      if (other == null) {throw new NullPointerException ("Nothing in Object");}
      double value = 0.0;
      if (other instanceof Binary) {
	  int a = this._decNum;
	  int b =((Binary)other).getValue();
	  value = (double) (a - b);

      }
      else if (other instanceof Hexadecimal) {
	  int a = this._decNum;
	  int b = ((Hexadecimal)other)._decNum;
          value = (double)( a - b); }
   

      else {
	  double a = ((double)this._decNum);
	  double b = ((Rational)other).floatValue();
	  value = (double)(a - b); }

      if (value > 0.0) {return 1;}
      if (value == 0.0) {return 0;}
      else {return -1;}

  //main method for testing
    public static void main( String[] args ) {

	System.out.println();
	System.out.println( "Testing ..." );

	Hexadecimal h1 = new Hexadecimal(255); //FF
	Hexadecimal h2 = new Hexadecimal(256); //100
	Hexadecimal h3 = h1;                   //FF
	Hexadecimal h4 = new Hexadecimal(258); //102
	
	System.out.println(h1);
	System.out.println(h2);
	System.out.println(h3);
	System.out.println(h4);
	
	hexToDec functionality checked and worked. See toString()
	Hexadecimal h5 = new Hexadecimal("FF");
	Hexadecimal h6 = new Hexadecimal("100");
	Hexadecimal h7 = h1;
	Hexadecimal h8 = new Hexadecimal("102");
	
	System.out.println(h5);
	System.out.println(h6);
	System.out.println(h7);
	System.out.println(h8);
	

	System.out.println("\nTesting equals():");	
	Binary b1 = new Binary("10010");
	System.out.println(h1.equals(b1)); //Throws exception
	System.out.println(h1.equals(h3));   //Returns true
	System.out.println(h1.equals(h4));   //Returns false
	
	System.out.println("\nTesting compareTo():");
	System.out.println(h1.compareTo(h3));  //returns 0
	System.out.println(h1.compareTo(h4));  //returns negative
	System.out.println(h2.compareTo(h1));  //returns positive
	System.out.println(h3.compareTo(b1));//throws exception
    }//end main()

} //end class
