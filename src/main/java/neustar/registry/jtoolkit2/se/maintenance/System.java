package neustar.registry.jtoolkit2.se.maintenance;

import java.io.Serializable;

public class System implements Serializable {

    private static final long serialVersionUID = -2981199489658954126L;

    private final String name;
    private final String host;
    private final Impact impact;

    public System(String name, String host, Impact impact) {
        this.name = name;
        this.host = host;
        this.impact = impact;
    }

    public String getName() {
        return name;
    }

    public String getHost() {
        return host;
    }

    public Impact getImpact() {
        return impact;
    }
}
