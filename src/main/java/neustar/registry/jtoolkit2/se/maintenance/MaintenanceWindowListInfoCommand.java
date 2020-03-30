package neustar.registry.jtoolkit2.se.maintenance;

import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.InfoCommand;

/**
 * Use this class to generate a standards-compliant XML document, given simple
 * input parameters.  The toXML method in Command serialises this object to
 * XML.
 */
public final class MaintenanceWindowListInfoCommand extends InfoCommand {

    /**
     * Request that the retrieves the list of Maintenance Windows.
     *
     */
    public MaintenanceWindowListInfoCommand() {
        super(ExtendedObjectType.MAINT);
        xmlWriter.appendChild(objElement, "list");
    }

}

