package neustar.registry.jtoolkit2.se.mzb;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.xpath.XPathExpressionException;

import neustar.registry.jtoolkit2.se.DataResponse;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.StandardCommandType;
import neustar.registry.jtoolkit2.xml.XMLDocument;

/**
 * Use this to access roid data for blocks as provided in an EPP
 * EPS check response compliant.  Such a service element is sent
 * by a compliant EPP server in response to a valid EPS check
 * command, implemented by the EpsCheckCommand class.
 *
 * @see MzbCheckCommand
 */
public class MzbCheckResponse extends DataResponse {

    private static final long serialVersionUID = -7501698464402166104L;

    private static final String MZB_CHKDATA_COUNT_EXPR = "count(" + RES_DATA_EXPR + "/mzb:chkData/*)";
    private static final String MZB_CHKDATA_IND_EXPR = RES_DATA_EXPR + "/mzb:chkData/mzb:cd[IDX]";
    private static final String MZB_CHKDATA_IDENT_EXPR = "/mzb:label/text()";

    private Map<String, String[]> results;

    public MzbCheckResponse() {
        super(StandardCommandType.CHECK, ExtendedObjectType.MZB);
        results = new HashMap<String, String[]>();
    }

    protected String chkDataCountExpr() {
        return MZB_CHKDATA_COUNT_EXPR;
    }

    protected String chkDataIndexExpr() {
        return MZB_CHKDATA_IND_EXPR;
    }

    protected String chkDataTextExpr() {
        return MZB_CHKDATA_IDENT_EXPR;
    }

    protected String getKey(final XMLDocument xmlDoc, final String qry) throws XPathExpressionException {
        return xmlDoc.getNodeValue(qry + chkDataTextExpr());
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) {
        super.fromXML(xmlDoc);

        if (!resultArray[0].succeeded()) {
            return;
        }

        try {
            int cdCount = xmlDoc.getNodeCount(chkDataCountExpr());

            for (int i = 0; i < cdCount; i++) {
                String qry = replaceIndex(chkDataIndexExpr(), i + 1);
                results.put(getKey(xmlDoc, qry), getRoidStr(xmlDoc, qry));
            }
        } catch (XPathExpressionException xpee) {
            maintLogger.warning(xpee.getMessage());
        }
    }

    public Set<String> getNames() {
        return results.keySet();
    }

    public String[] getRoids(String label) {
        return results.get(label);
    }

    private String[] getRoidStr(XMLDocument xmlDoc, String qry) throws XPathExpressionException {
        String[] roidStr;
        final String roidQry = qry + "/mzb:roids";
        final int chkRoids = xmlDoc.getNodeCount("count(" + roidQry + "/*)");
        roidStr = new String[chkRoids];
        for (int j = 0; j < chkRoids; j++) {
            roidStr[j] = xmlDoc.getNodeValue(replaceIndex(roidQry + "[IDX]/mzb:roid", j + 1));
        }
        return roidStr;
    }

}

