package neustar.registry.jtoolkit2.se.mzb;

import java.util.GregorianCalendar;
import javax.xml.xpath.XPathExpressionException;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.se.DataResponse;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.StandardCommandType;
import neustar.registry.jtoolkit2.xml.XMLDocument;

/**
 * Mapping of EPP mzb:renew command response
 * specified by the GoDaddy Registry EPP extensions document.
 * Use this to access mzb:renew response data. Such a service element
 * is sent by a compliant EPP server in response to a valid mzb:create
 * command, implemented by the EpsRenewCommand class.
 *
 * @see MzbRenewCommand
 */
public class MzbRenewResponse extends DataResponse {
    private static final long serialVersionUID = -5724827272682186647L;

    private static final String ROID_EXPR = RES_DATA_EXPR + "/mzb:renData/mzb:roid/text()";
    private static final String EX_DATE_EXPR = RES_DATA_EXPR + "/mzb:renData/mzb:exDate/text()";

    private String roid;
    private GregorianCalendar exDate;

    public MzbRenewResponse() {
        super(StandardCommandType.RENEW, ExtendedObjectType.MZB);
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

