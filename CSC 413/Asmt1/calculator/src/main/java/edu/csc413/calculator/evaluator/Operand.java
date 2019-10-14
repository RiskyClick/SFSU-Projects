package edu.csc413.calculator.evaluator;
/**
 * Operand class used to represent an operand
 * in a valid mathmetical expression.
 */
public class Operand {
  /**
  * construct operand from string token.
  */
  private int fromInt;

  public Operand( String token ) {
      this.fromInt = Integer.parseInt(token);
    
  }
  /**
   * construct operand from integer
   */
  public Operand( int value ) {
      this.fromInt = value;
  }
  /**
   * return value of opernad
   */
  public int getValue() {
      return fromInt;
  }
  /**
   * Check to see if given token is a valid
   * operand.
   */
  public static boolean check( String token ) {
    try{
        Integer.parseInt(token);
    }
    catch(NumberFormatException i){
        return false;
    }
    return true;
  }
}
