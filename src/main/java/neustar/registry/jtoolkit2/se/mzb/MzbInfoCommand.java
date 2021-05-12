package neustar.registry.jtoolkit2.se.mzb;

import neustar.registry.jtoolkit2.ErrorPkg;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.ObjectCommand;
import neustar.registry.jtoolkit2.se.StandardCommandType;

/**
 * Mapping of EPP mzb:info command
 * specified by the GoDaddy Registry EPP extensions document.
 * Use this class to generate an GoDaddy Registry-compliant XML document, given
 * simple input parameters.  The toXML method in Command serialises this object
 * to XML.
 *
 * @see MzbInfoResponse
 */
public class MzbInfoCommand extends ObjectCommand {
    private static final long serialVersionUID = -9129030981710943397L;

    /**
     * Create an mzb:info command with the specified identifier.
     *
     * @param roid The roid of the eps object to retrieve information about.
     */
    public MzbInfoCommand(String roid) {
        super(StandardCommandType.INFO, ExtendedObjectType.MZB);
        appendRoid(roid);
    }

    /**
     * Create an mzb:info command with the specified identifier and authorisation
     * information.
     *  @param roid The roid of the eps object to retrieve information about.
     *
     * @param pw The password of the identified mzb:object (also known as authInfo)
     */
    public MzbInfoCommand(String roid, String pw) {
        this(roid);
        appendAuthInfo(pw);
    }

    private void appendAuthInfo(String pw) {
        if (pw == null) {
            return;
        }

        xmlWriter.appendChild(xmlWriter.appendChild(objElement, "authInfo"), "pw").setTextContent(pw);
    }

    private void appendRoid(String roid) {

        if (roid == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.info.missing_arg"));
        }

        xmlWriter.appendChild(objElement, "roid").setTextContent(roid);
    }

}

