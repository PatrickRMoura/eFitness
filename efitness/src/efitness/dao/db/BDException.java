package efitness.dao.db;

/**
 *
 * @author Patrick Moura
 */
public class BDException extends RuntimeException{
    public BDException(String s) {
        super(s);
    }

    public BDException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public BDException(Throwable throwable) {
        super(throwable);
    } 
}
