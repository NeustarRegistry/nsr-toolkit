package neustar.registry.jtoolkit2.se.eps;

import neustar.registry.jtoolkit2.se.CheckCommand;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;

/**
 * A EpsExemptCommand is used to locate the IPR Number and all the labels
 * that have valid IPR details for the requested label
 *
 * @see EpsExemptResponse
 */
public class EpsExemptCommand extends CheckCommand {
    private static final long serialVersionUID = 3050267498759687925L;

    /**
     * Check the availability of the single identified block.
     *
     * @param name The name of the label to check the availability of.
     */
    public EpsExemptCommand(String name) {
        super(ExtendedObjectType.EPS, name);
    }

    /**
     * Check the availability of at least one block.
     *
     * @param names The names of the labels to check the availability of.
     */
    public EpsExemptCommand(String[] names) {
        super(ExtendedObjectType.EPS, names);
    }
}

