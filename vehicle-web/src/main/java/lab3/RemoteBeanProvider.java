package lab3;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public final class RemoteBeanProvider {

    private RemoteBeanProvider() {
    }

    private static final Hashtable<String, String> CONTEXT_PROPS = new Hashtable<>();

    private static final String MODULE_NAME = System.getenv("EJB_MODULE");

    static {
        CONTEXT_PROPS.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
    }

    @SuppressWarnings("unchecked")
    public static <T> T provide(Class<T> remoteInterface) throws NamingException {
        Context context = new InitialContext(CONTEXT_PROPS);
        String name = "ejb:/" +
                MODULE_NAME + "/" +
                remoteInterface.getSimpleName() + "Bean!" +
                remoteInterface.getName();
        return (T) context.lookup(name);
    }
}