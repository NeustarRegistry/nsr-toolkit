package neustar.registry.jtoolkit2.se.eps;

import java.util.GregorianCalendar;
import javax.xml.xpath.XPathExpressionException;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.se.DataResponse;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.StandardCommandType;
import neustar.registry.jtoolkit2.xml.XMLDocument;

/**
 * Mapping of EPP eps:renew command response
 * specified by the GoDaddy Registry EPP extensions document.
 * Use this to access eps:renew response data. Such a service element
 * is sent by a compliant EPP server in response to a valid eps:create
 * command, implemented by the EpsRenewCommand class.
 *
 * @see EpsRenewCommand
 */
public class EpsRenewResponse extends DataResponse {
    private static final long serialVersionUID = -5724827272682186647L;

    private static final String ROID_EXPR = RES_DATA_EXPR + "/eps:renData/eps:roid/text()";
    private static final String EX_DATE_EXPR = RES_DATA_EXPR + "/eps:renData/eps:exDate/text()";

    private String roid;
    private GregorianCalendar exDate;

    public EpsRenewResponse() {
        super(StandardCommandType.RENEW, ExtendedObjectType.EPS);
    }

    public String getRoid() {
        return roid;
    }

    public GregorianCalendar getExpiryDate() {
        return exDate;
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) {
        super.fromXML(xmlDoc);

        if (!resultArray[0].succeeded()) {
            return;
        }

        try {
            roid = xmlDoc.getNodeValue(ROID_EXPR);
            exDate = EPPDateFormatter.fromXSDateTime(xmlDoc.getNodeValue(EX_DATE_EXPR));
        } catch (XPathExpressionException xpee) {
            maintLogger.warning(xpee.getMessage());
        }
    }
}

