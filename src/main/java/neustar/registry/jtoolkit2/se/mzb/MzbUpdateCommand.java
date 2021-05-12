package neustar.registry.jtoolkit2.se.mzb;

import org.w3c.dom.Element;

import neustar.registry.jtoolkit2.ErrorPkg;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.ObjectCommand;
import neustar.registry.jtoolkit2.se.Response;
import neustar.registry.jtoolkit2.se.StandardCommandType;

/**
 * Mapping of EPP mzb:update command response
 * specified by the GoDaddy Registry EPP extensions document.
 * Use this to access mzb:update response data. Such a service element
 * is sent by a compliant EPP server in response to a valid mzb:update
 * command, implemented by the EpsUpdateCommand class.
 *
 * @see Response
 */
public class MzbUpdateCommand extends ObjectCommand {
    private static final long serialVersionUID = 2409916920503111390L;

    /**
     * The complete set of attributes of a domain which may be updated as per
     * RFC5731.
     * @throws IllegalArgumentException if {@code roid} is {@code null}.
     */
    public MzbUpdateCommand(String roid, String pw, String registrantID, boolean removeAuthInfo) {
        super(StandardCommandType.UPDATE, ExtendedObjectType.MZB);

        if (roid == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.eps.update.roid.missing"));
        }

        xmlWriter.appendChild(objElement, "roid").setTextContent(roid);

        Element chg = xmlWriter.appendChild(objElement, "chg");
        if (registrantID != null || pw != null || removeAuthInfo) {
            if (registrantID != null) {
                xmlWriter.appendChild(chg, "registrant").setTextContent(registrantID);
            }
            if (pw != null) {
                xmlWriter.appendChild(xmlWriter.appendChild(chg, "authInfo"), "pw").setTextContent(pw);
            } else if (removeAuthInfo) {
                xmlWriter.appendChild(xmlWriter.appendChild(chg, "authInfo"), "null");
            }
        }
    }

}

