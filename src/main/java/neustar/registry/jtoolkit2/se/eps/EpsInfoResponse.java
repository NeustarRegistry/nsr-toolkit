package neustar.registry.jtoolkit2.se.eps;

import java.util.GregorianCalendar;
import javax.xml.xpath.XPathExpressionException;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.InfoResponse;
import neustar.registry.jtoolkit2.xml.XMLDocument;

/**
 * Mapping of EPP eps:info command response
 * specified by the GoDaddy Registry EPP extensions document.
 * Use this class to process an GoDaddy Registry-compliant XML document.
 * Such a service element is sent by a compliant EPP server in response
 * to a valid EPS info command, implemented by the EpsInfoCommand class.
 *
 * @see EpsInfoCommand
 */
public class EpsInfoResponse extends InfoResponse {

    protected static final String EPS_ROID_EXPR = EpsInfoResponse.exprReplace(ROID_EXPR);
    protected static final String EPS_CR_ID_EXPR = EpsInfoResponse.exprReplace(CR_ID_EXPR);
    protected static final String EPS_UP_ID_EXPR = EpsInfoResponse.exprReplace(UP_ID_EXPR);
    protected static final String EPS_CL_ID_EXPR = EpsInfoResponse.exprReplace(CL_ID_EXPR);
    protected static final String EPS_CR_DATE_EXPR = EpsInfoResponse.exprReplace(CR_DATE_EXPR);
    protected static final String EPS_UP_DATE_EXPR = EpsInfoResponse.exprReplace(UP_DATE_EXPR);
    protected static final String EPS_TR_DATE_EXPR = EpsInfoResponse.exprReplace(TR_DATE_EXPR);

    protected static final String EPS_INF_DATA_EXPR = EpsInfoResponse.exprReplace(INF_DATA_EXPR);
    protected static final String EPS_PW_EXPR = EPS_INF_DATA_EXPR + "/eps:authInfo/eps:pw/text()";
    protected static final String EPS_REGISTRANT_EXPR = EPS_INF_DATA_EXPR + "/eps:registrant/text()";
    protected static final String EPS_EX_DATE_EXPR = EPS_INF_DATA_EXPR + "/eps:exDate/text()";
    protected static final String EPS_LABEL_EXPR = EPS_INF_DATA_EXPR + "/eps:label/text()";

    private static final long serialVersionUID = -5948394715740177139L;

    private String pw;
    private String registrantID;
    private GregorianCalendar exDate;
    private String exDateStr;
    private String[] labels;

    public EpsInfoResponse() {
        super(ExtendedObjectType.EPS);
    }

    protected String roidExpr() {
        return EPS_ROID_EXPR;
    }

    protected String crIDExpr() {
        return EPS_CR_ID_EXPR;
    }

    protected String upIDExpr() {
        return EPS_UP_ID_EXPR;
    }

    protected String clIDExpr() {
        return EPS_CL_ID_EXPR;
    }

    protected String crDateExpr() {
        return EPS_CR_DATE_EXPR;
    }

    protected String upDateExpr() {
        return EPS_UP_DATE_EXPR;
    }

    protected String trDateExpr() {
        return EPS_TR_DATE_EXPR;
    }

    @Override
    protected String statusExpr() {
        return null;
    }

    @Override
    protected String statusCountExpr() {
        return null;
    }

    protected static String exprReplace(String expr) {
        return expr.replaceAll(OBJ, "eps");
    }

    public String getPW() {
        return pw;
    }

    public GregorianCalendar getExpireDate() {
        return exDate;
    }

    public String getRegistrantID() {
        return registrantID;
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) {
        super.fromXML(xmlDoc);

        if (!resultArray[0].succeeded()) {
            return;
        }

        try {
            pw = xmlDoc.getNodeValue(EPS_PW_EXPR);
            registrantID = xmlDoc.getNodeValue(EPS_REGISTRANT_EXPR);
            exDateStr = xmlDoc.getNodeValue(EPS_EX_DATE_EXPR);
            if (exDateStr != null) {
                exDate = EPPDateFormatter.fromXSDateTime(exDateStr);
            }
            labels = xmlDoc.getNodeValues(EPS_LABEL_EXPR);

        } catch (XPathExpressionException xpee) {
            maintLogger.warning(xpee.getMessage());
        }
    }

    @Override
    public String toString() {
        String retval = super.toString();
        retval += "(roid = " + getROID() + ")(registrant-id = " + registrantID + ")";

        if (exDateStr != null) {
            retval += "(expiry-date = " + exDateStr + ")";
        }

        return retval;
    }

    public String[] getLabels() {
        return labels;
    }
}
