package neustar.registry.jtoolkit2.se.maintenance;

import java.io.Serializable;

public class Intervention implements Serializable {

    private static final long serialVersionUID = -3151784971761413088L;

    private final boolean connection;
    private final boolean implementation;

    public Intervention(boolean connection, boolean implementation) {
        this.connection = connection;
        this.implementation = implementation;
    }

    public boolean isImplementation() {
        return implementation;
    }

    public boolean isConnection() {
        return connection;
    }
}
