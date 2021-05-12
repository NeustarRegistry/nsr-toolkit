package neustar.registry.jtoolkit2.se.mzb;

import java.util.List;

import org.w3c.dom.Element;

import neustar.registry.jtoolkit2.ErrorPkg;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.ObjectCommand;
import neustar.registry.jtoolkit2.se.Period;
import neustar.registry.jtoolkit2.se.StandardCommandType;

/**
 * Mapping of EPP urn:X-gdreg:params:xml:ns:eps-2.0 create command
 * specified by the GoDaddy Registry EPP extensions document.
 * Use this class to generate an GoDaddy Registry-compliant XML document, given
 * simple input parameters.  The toXML method in Command serialises this object
 * to XML.
 *
 * @see MzbCreateResponse
 */
public class MzbCreateCommand extends ObjectCommand {
    private static final long serialVersionUID = -3723213074751854975L;

    /**
     * Most verbose constructor for a mzb:create EPP command. All core EPP
     * mzb:create attributes may be set using this constructor.
     *
     * @throws IllegalArgumentException if {@code names} is {@code null}.
     */
    public MzbCreateCommand(MzbType type, List<String> labels, String pw, String registrantID, Period period,
                            String intellectualPropertyRightsId) {
        super(StandardCommandType.CREATE, ExtendedObjectType.MZB);

        if (labels == null || labels.isEmpty()) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.eps.create.labels.missing_arg"));
        }
        if (type == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.eps.create.type.missing_arg"));
        }
        if (period == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.eps.create.period.missing_arg"));
        }
        if (registrantID == null || registrantID.isEmpty()) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.eps.create.registrantID.missing_arg"));
        }

        objElement.setAttribute("type", type.name());

        addLabelsToXml(labels);

        if (period != null) {
            xmlWriter.appendChild(objElement, "period").setTextContent(String.valueOf(period.getPeriod()));
        }

        if (registrantID != null) {
            xmlWriter.appendChild(objElement, "registrant").setTextContent(registrantID);
        }

        if (intellectualPropertyRightsId != null) {
            xmlWriter.appendChild(objElement, "iprID").setTextContent(intellectualPropertyRightsId);
        }
        xmlWriter.appendChild(xmlWriter.appendChild(objElement, "authInfo"), "pw").setTextContent(pw);
    }

    private void addLabelsToXml(List<String> labels) {

        final Element labelsElement = xmlWriter.appendChild(objElement, "labels");

        for (String label : labels) {
            xmlWriter.appendChild(labelsElement, "label").setTextContent(label);
        }
    }

}

