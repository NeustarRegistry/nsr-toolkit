package neustar.registry.jtoolkit2.se.mzb;

import neustar.registry.jtoolkit2.se.CheckCommand;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;

/**
 * A DomainCheckCommand is used to check the availability of domain objects
 * in a Registry.  Instances of this class generate RFC5730 and RFC5731
 * compliant domain check EPP command service elements via the toXML method.
 *
 * @see MzbCheckResponse
 */
public class MzbCheckCommand extends CheckCommand {
    private static final long serialVersionUID = 3050267498759687925L;

    /**
     * Check the availability of the single identified block.
     *
     * @param name The name of the block to check the availability of.
     */
    public MzbCheckCommand(String name) {
        super(ExtendedObjectType.MZB, name);
    }

    /**
     * Check the availability of at least one block.
     *
     * @param names The names of the blocks to check the availability of.
     */
    public MzbCheckCommand(String[] names) {
        super(ExtendedObjectType.MZB, names);
    }
}

