package neustar.registry.jtoolkit2.se.maintenance;

import java.util.Date;
import javax.xml.xpath.XPathExpressionException;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.se.DataResponse;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.se.ReceiveSE;
import neustar.registry.jtoolkit2.se.StandardCommandType;
import neustar.registry.jtoolkit2.xml.XMLDocument;

/**
 * Use this to access domain object information as provided in an EPP maintenance
 * window info response compliant with draft-ietf-regext-epp-registry-maintenance-00.
 * Such a service element is sent by a compliant EPP server in response to a valid
 * maintenance window info command, implemented by the MaintenanceWindowInfoCommand
 * class.
 *
 * @see MaintenanceWindowInfoCommand
 */
public class MaintenanceWindowListInfoResponse extends DataResponse {

    private static final String TXT_EXPR = "/text()";

    private static final String MW_INF_DATA_EXPR = RESPONSE_EXPR + "/e:resData/maint:infData";
    private static final String MW_LIST_EXPR = MW_INF_DATA_EXPR + "/maint:list";
    private static final String MW_MAINT_EXPR = MW_LIST_EXPR + "/maint:maint";

    private static final String MW_MAINT_COUNT_EXPR = "count(" + MW_MAINT_EXPR + ")";
    private static final String MW_MAINT_ITEM_EXPR_IDX = MW_MAINT_EXPR + "[IDX]";

    private MaintenanceWindowItem[] items;

    public MaintenanceWindowListInfoResponse() {
        super(StandardCommandType.INFO, ExtendedObjectType.MAINT);
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) {
        super.fromXML(xmlDoc);

        if (!resultArray[0].succeeded()) {
            return;
        }

        try {
            processItems(xmlDoc);
        } catch (XPathExpressionException xpee) {
            maintLogger.warning(xpee.getMessage());
        }
    }

    private Date extractDate(XMLDocument xmlDoc, String expression) throws XPathExpressionException {
        String exDateStr = xmlDoc.getNodeValue(expression);
        if (exDateStr != null) {
            return EPPDateFormatter.fromXSDateTime(exDateStr).getTime();
        }
        return null;
    }

    private void processItems(XMLDocument xmlDoc) throws XPathExpressionException {
        int numberOfDetails = xmlDoc.getNodeCount(MW_MAINT_COUNT_EXPR);
        items = new MaintenanceWindowItem[numberOfDetails];

        for (int k = 0; k < numberOfDetails; k++) {
            String itemExpr = ReceiveSE.replaceIndex(MW_MAINT_ITEM_EXPR_IDX, k + 1);
            String id = xmlDoc.getNodeValue(itemExpr + "/maint:id" + TXT_EXPR);
            Date startDate = extractDate(xmlDoc, itemExpr + "/maint:start" + TXT_EXPR);
            Date endDate = extractDate(xmlDoc, itemExpr + "/maint:end" + TXT_EXPR);
            Date createDate = extractDate(xmlDoc, itemExpr + "/maint:crDate" + TXT_EXPR);
            Date updateDate = extractDate(xmlDoc, itemExpr + "/maint:upDate" + TXT_EXPR);
            items[k] = new MaintenanceWindowItem(id, startDate, endDate, createDate, updateDate);
        }
    }

    public MaintenanceWindowItem[] getItems() {
        return items;
    }

}
