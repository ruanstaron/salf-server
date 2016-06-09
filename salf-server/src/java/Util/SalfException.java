package Util;

/**
 * @author cristhian
 */
public class SalfException extends Exception {

    /**
     * Creates a new instance of <code>SalfException</code> without detail
     * message.
     */
    public SalfException() {
    }

    /**
     * Constructs an instance of <code>SalfException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public SalfException(String msg) {
        super(msg);
        System.out.println("SalfException: " + msg);
    }
    
    public String toJson() {
        return "{\"error\": \"" + super.getMessage() + "\"}";
    }
}
