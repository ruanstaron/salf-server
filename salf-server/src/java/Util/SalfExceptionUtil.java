package Util;

/**
 * @author cristhian
 */
public class SalfExceptionUtil {

    // lista de exceções do postgresql
    // https://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
    // chave única violada
    public static String UNIQUE_KEY = "23505";

    // violação de chave estrangeira
    public static String FOREIGN_KEY = "23503";

    // nenhum registro afetado (pelo cadastro, atualização ou exclusão)
    public static String NO_AFFECTED_ROWS = "98765";

    public static boolean tratavel(String code) {
        if (false
                || code.equals(UNIQUE_KEY)
                || code.equals(FOREIGN_KEY)
                || code.equals(NO_AFFECTED_ROWS)) {
            return true;
        }
        return false;
    }

    public static String toJson(String msg) {
        return "{\"error\": \"" + msg + "\"}";
    }

}
