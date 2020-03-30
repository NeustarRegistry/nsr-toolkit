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
public class MaintenanceWindowInfoResponse extends DataResponse {

    private static final String TXT_EXPR = "/text()";

    private static final String MW_INF_DATA_EXPR = RESPONSE_EXPR + "/e:resData/maint:infData";
    private static final String MW_MAINT_EXPR = MW_INF_DATA_EXPR + "/maint:maint";
    private static final String MW_ID_EXPR = MW_MAINT_EXPR + "/maint:id" + TXT_EXPR;

    private static final String SYSTEMS_COUNT_EXPR = "count(" + MW_MAINT_EXPR + "/maint:systems/maint:system)";
    private static final String SYSTEMS_EXPR_IDX = MW_MAINT_EXPR + "/maint:systems/maint:system[IDX]";

    private static final String TLDS_COUNT_EXPR = "count(" + MW_MAINT_EXPR + "/maint:tlds/maint:tld)";
    private static final String TLDS_EXPR_IDX = MW_MAINT_EXPR + "/maint:tlds/maint:tld[IDX]" + TXT_EXPR;

    private static final String ENVIRONMENT_EXPR = MW_MAINT_EXPR + "/maint:environment/@type";
    private static final String START_DATE_EXPR = MW_MAINT_EXPR + "/maint:start" + TXT_EXPR;
    private static final String END_DATE_EXPR = MW_MAINT_EXPR + "/maint:end" + TXT_EXPR;
    private static final String REASON_EXPR = MW_MAINT_EXPR + "/maint:reason" + TXT_EXPR;
    private static final String DETAIL_EXPR = MW_MAINT_EXPR + "/maint:detail" + TXT_EXPR;
    private static final String DESCRIPTION_EXPR = MW_MAINT_EXPR + "/maint:description" + TXT_EXPR;
    private static final String INTERVENTION_EXPR_IDX = MW_MAINT_EXPR + "/maint:intervention";
    private static final String CONNECTION_EXPR_IDX = INTERVENTION_EXPR_IDX + "/maint:connection";
    private static final String IMPLEMENTATION_EXPR_IDX = INTERVENTION_EXPR_IDX + "/maint:implementation";

    private static final String STATUS_EXPR = MW_MAINT_EXPR + "/maint:status" + TXT_EXPR;

    private static final String CREATE_DATE_EXPR = MW_MAINT_EXPR + "/maint:crDate" + TXT_EXPR;
    private static final String UPDATE_DATE_EXPR = MW_MAINT_EXPR + "/maint:upDate" + TXT_EXPR;

    private String id;
    private System[] systems;
    private Environment environment;
    private Date startDate;
    private Date endDate;
    private Reason reason;
    private String detail;
    private String description;
    private String[] tlds;
    private Intervention intervention;
    private Status status;
    private Date createDate;
    private Date updateDate;

    public MaintenanceWindowInfoResponse() {
        super(StandardCommandType.INFO, ExtendedObjectType.MAINT);
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) {
        super.fromXML(xmlDoc);

        if (!resultArray[0].succeeded()) {
            return;
        }

        try {
            id = xmlDoc.getNodeValue(MW_ID_EXPR);

            processSystems(xmlDoc);
            processTlds(xmlDoc);

            environment = convertEnvironment(xmlDoc.getNodeValue(ENVIRONMENT_EXPR));

            startDate = extractDate(xmlDoc, START_DATE_EXPR);
            endDate = extractDate(xmlDoc, END_DATE_EXPR);

            reason = convertReason(xmlDoc.getNodeValue(REASON_EXPR));
            detail = xmlDoc.getNodeValue(DETAIL_EXPR);
            description = xmlDoc.getNodeValue(DESCRIPTION_EXPR);

            boolean connection = Boolean.parseBoolean(xmlDoc.getNodeValue(CONNECTION_EXPR_IDX));
            boolean implementation = Boolean.parseBoolean(xmlDoc.getNodeValue(IMPLEMENTATION_EXPR_IDX));

            intervention = new Intervention(connection, implementation);

            status = convertStatus(xmlDoc.getNodeValue(STATUS_EXPR));

            createDate = extractDate(xmlDoc, CREATE_DATE_EXPR);
            updateDate = extractDate(xmlDoc, UPDATE_DATE_EXPR);

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

    private void processSystems(XMLDocument xmlDoc) throws XPathExpressionException {
        int numberOfSystems = xmlDoc.getNodeCount(SYSTEMS_COUNT_EXPR);
        systems = new System[numberOfSystems];

        for (int k = 0; k < numberOfSystems; k++) {
            String systemExpr = ReceiveSE.replaceIndex(SYSTEMS_EXPR_IDX, k + 1);
            String name = xmlDoc.getNodeValue(systemExpr + "/maint:name" + TXT_EXPR);
            String host = xmlDoc.getNodeValue(systemExpr + "/maint:host" + TXT_EXPR);
            String impact = xmlDoc.getNodeValue(systemExpr + "/maint:impact" + TXT_EXPR);
            systems[k] = new System(name, host, convertImpact(impact));
        }
    }

    private void processTlds(XMLDocument xmlDoc) throws XPathExpressionException {
        int numberOfTlds = xmlDoc.getNodeCount(TLDS_COUNT_EXPR);
        tlds = new String[numberOfTlds];

        for (int k = 0; k < numberOfTlds; k++) {
            String tldExpr = ReceiveSE.replaceIndex(TLDS_EXPR_IDX, k + 1);
            tlds[k] = xmlDoc.getNodeValue(tldExpr);
        }
    }

    private Impact convertImpact(String impact) {
        try {
            return Impact.valueOf(impact);
        } catch (IllegalArgumentException e) {
           return null;
        }
    }

    private Environment convertEnvironment(String environment) {
        try {
            if (environment != null) {
                return Environment.valueOf(environment);
            }
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private Reason convertReason(String reason) {
        try {
            if (reason != null) {
                return Reason.valueOf(reason);
            }
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private Status convertStatus(String status) {
        try {
            if (status != null) {
                return Status.valueOf(status);
            }
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getId() {
        return id;
    }

    public System[] getSystems() {
        return systems;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Reason getReason() {
        return reason;
    }

    public String getDetail() {
        return detail;
    }

    public String getDescription() {
        return description;
    }

    public String[] getTlds() {
        return tlds;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
