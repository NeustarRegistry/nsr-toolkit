package neustar.registry.jtoolkit2.se.eps;

import java.util.GregorianCalendar;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.ErrorPkg;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.ObjectCommand;
import neustar.registry.jtoolkit2.se.StandardCommandType;

/**
 * Mapping of EPP eps:renew command
 * specified by the GoDaddy Registry EPP extensions document.
 * Use this class to generate an GoDaddy Registry-compliant XML document, given
 * simple input parameters.  The toXML method in Command serialises this object
 * to XML.
 *
 * @see EpsRenewResponse
 */
public class EpsRenewCommand extends ObjectCommand {
    private static final long serialVersionUID = -3723213074751854975L;

    /**
     * @throws IllegalArgumentException if {@code roid} or {@code exDaten} is {@code null}.
     */
    public EpsRenewCommand(String roid, GregorianCalendar exDate) {
        super(StandardCommandType.RENEW, ExtendedObjectType.EPS);

        if (roid == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.eps.renew.roid.missing_arg"));
        }

        if (exDate == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.eps.renew.curExpDate.missing_arg"));
        }

        xmlWriter.appendChild(objElement, "roid").setTextContent(roid);
        xmlWriter.appendChild(objElement, "curExpDate").setTextContent(EPPDateFormatter.toXSDate(exDate));
    }

    public EpsRenewCommand(String roid, GregorianCalendar exDate, Integer period) {
        this(roid, exDate);
        xmlWriter.appendChild(objElement, "period").setTextContent(String.valueOf(period));
    }

}

