package neustar.registry.jtoolkit2.se.eps;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.xml.xpath.XPathExpressionException;

import neustar.registry.jtoolkit2.se.DataResponse;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.StandardCommandType;
import neustar.registry.jtoolkit2.xml.XMLDocument;

/**
 * Use this to access IPR Number and the lists of associated trademark labels.
 * Such a service element is sent by a compliant EPP server in response to a valid EPS exempt
 * command, implemented by the EpsExemptCommand class.
 *
 * @see EpsExemptCommand
 */
public class EpsExemptResponse extends DataResponse {

    private static final long serialVersionUID = -7501698464402166104L;

    private static final String ED_COUNT_EXPR = "count(" + RES_DATA_EXPR + "/eps:empData/eps:ed)";
    private static final String ED_INT_EXPR = RES_DATA_EXPR + "/eps:empData/eps:ed[IDX]";

    private static final String LABEL_IDENT_EXPR = "/eps:label/text()";

    private Map<String, EpsExemption[]> results;

    public EpsExemptResponse() {
        super(StandardCommandType.CHECK, ExtendedObjectType.EPS);
        results = new HashMap<>();
    }

    protected String getQueryLabel(final XMLDocument xmlDoc, String qry) throws XPathExpressionException {
        return xmlDoc.getNodeValue(qry);
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) {
        super.fromXML(xmlDoc);

        if (!resultArray[0].succeeded()) {
            return;
        }

        try {
            int cdCount = xmlDoc.getNodeCount(ED_COUNT_EXPR);

            for (int i = 0; i < cdCount; i++) {
                final String edQuery = replaceIndex(ED_INT_EXPR, i + 1);
                String labelQry = edQuery + LABEL_IDENT_EXPR;
                results.put(getQueryLabel(xmlDoc, labelQry), createExemptions(xmlDoc, edQuery));
            }
        } catch (XPathExpressionException xpee) {
            maintLogger.warning(xpee.getMessage());
        }
    }

    private EpsExemption[] createExemptions(XMLDocument xmlDoc, String edQuery) throws XPathExpressionException {

        EpsExemption[] epsExemptions;
        final String exemptionQry = edQuery + "/eps:exemptions/eps:exemption";
        final int chkExemptions = xmlDoc.getNodeCount("count(" + exemptionQry + ")");
        epsExemptions = new EpsExemption[chkExemptions];
        for (int j = 0; j < chkExemptions; j++) {
            final String exempt = replaceIndex(exemptionQry + "[IDX]", j + 1);
            final String iprQuery = exempt + "/eps:iprID";
            epsExemptions[j] = new EpsExemption(xmlDoc.getNodeValue(iprQuery));
            epsExemptions[j].setLabels(getLabels(xmlDoc, exempt));
        }

        return epsExemptions;
    }

    private String[] getLabels(XMLDocument xmlDoc, String exempt) throws XPathExpressionException {
        String[] labelStr;
        final String labelQry = exempt + "/eps:labels";
        final int nodeCount = xmlDoc.getNodeCount("count(" + labelQry + "/*)");
        labelStr = new String[nodeCount];
        for (int j = 0; j < nodeCount; j++) {
            labelStr[j] = xmlDoc.getNodeValue(replaceIndex(labelQry + "/eps:label[IDX]", j + 1));
        }
        return labelStr;
    }

    public Set<String> getNames() {
        return results.keySet();
    }

    public EpsExemption[] getExemptions(String label) {
        return results.get(label);
    }

}

