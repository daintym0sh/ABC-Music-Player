package sound;
/**
 * Token makes objects using the enumerable data types in the Type class
 * 
 * @author John
 */
public class Token {
    private final Type type;
    private final String value;
    /**
     * Constructs and initializes Token objects
     * 
     * @param type - an enumerable constant from the class Type
     * @param value - a String equal to the value of a token
     */
    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }
    /**
     * A getter that returns an enumerable constant 
     * 
     * @return an enumerable constant held by the variable type
     */
    public Type getType() {
        return type;
    }
    /**
     * A getter that returns a String
     * 
     * @return a String held by the variable value
     */
    public String getValue() {
        return value;
    }
}

