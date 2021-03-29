package neustar.registry.jtoolkit2.se.eps;

import neustar.registry.jtoolkit2.ErrorPkg;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.ObjectCommand;
import neustar.registry.jtoolkit2.se.StandardCommandType;

/**
 * Mapping of EPP eps:delete command
 * specified by the GoDaddy Registry EPP extensions document.
 * Use this class to generate an GoDaddy Registry-compliant XML document, given
 * simple input parameters.  The toXML method in Command serialises this object
 * to XML.
 *
 * @see EpsDeleteResponse
 */
public class EpsDeleteCommand extends ObjectCommand {
    private static final long serialVersionUID = -3723213074751854975L;

    /**
     * @throws IllegalArgumentException if {@code roid} is {@code null}.
     */
    public EpsDeleteCommand(String roid) {
        super(StandardCommandType.DELETE, ExtendedObjectType.EPS);

        if (roid == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.eps.delete.roid.missing_arg"));
        }

        xmlWriter.appendChild(objElement, "roid").setTextContent(roid);
    }

}

