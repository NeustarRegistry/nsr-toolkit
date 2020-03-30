package neustar.registry.jtoolkit2.se.maintenance;

import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.InfoCommand;

/**
 * Use this class to generate a standards-compliant XML document, given simple
 * input parameters.  The toXML method in Command serialises this object to
 * XML.
 */
public final class MaintenanceWindowInfoCommand extends InfoCommand {

    /**
     * Request that the retrieves the details of a Maintenance Window.
     *
     * @param id The id of the Maintenance Window to retrieve information about. Required.
     *
     * @throws IllegalArgumentException if {@code id} is {@code null}.
     */
    public MaintenanceWindowInfoCommand(String id) {
        super(ExtendedObjectType.MAINT, id);
    }

}

