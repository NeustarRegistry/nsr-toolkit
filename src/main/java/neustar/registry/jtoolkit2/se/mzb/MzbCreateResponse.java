package neustar.registry.jtoolkit2.se.mzb;

import java.util.GregorianCalendar;
import javax.xml.xpath.XPathExpressionException;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.se.CreateResponse;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.xml.XMLDocument;

/**
 * Mapping of EPP mzb:create command response
 * specified by the GoDaddy Registry EPP extensions document.
 * Use this to access mzb:create response data. Such a service element
 * is sent by a compliant EPP server in response to a valid mzb:create
 * command, implemented by the EpsCreateCommand class.
 *
 * @see MzbCreateCommand
 */
public class MzbCreateResponse extends CreateResponse {
    private static final long serialVersionUID = -5724827272682186647L;

    private static final String EPS_CR_DATE_EXPR = exprReplace(CR_DATE_EXPR);
    private static final String EPS_ROID_EXPR = exprReplace(CRE_DATA_EXPR) + "/mzb:roid/text()";
    private static final String EPS_EX_DATE_EXPR = exprReplace(CRE_DATA_EXPR) + "/mzb:exDate/text()";

    private String roid;
    private GregorianCalendar exDate;

    public MzbCreateResponse() {
        super(ExtendedObjectType.MZB);
    }

    protected String crDateExpr() {
        return EPS_CR_DATE_EXPR;
    }

    protected static String exprReplace(String expr) {
        return expr.replaceAll(OBJ, ExtendedObjectType.MZB.getName());
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
            roid = xmlDoc.getNodeValue(EPS_ROID_EXPR);
            exDate = EPPDateFormatter.fromXSDateTime(xmlDoc.getNodeValue(EPS_EX_DATE_EXPR));
        } catch (XPathExpressionException xpee) {
            maintLogger.warning(xpee.getMessage());
        }
    }
}

