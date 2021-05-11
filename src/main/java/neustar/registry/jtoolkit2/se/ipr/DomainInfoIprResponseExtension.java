package neustar.registry.jtoolkit2.se.ipr;

import java.util.Date;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.se.ResponseExtension;
import neustar.registry.jtoolkit2.xml.XMLDocument;

/**
 * <p>Extension for the EPP Domain Info response, representing the IPR Info aspect of the Intellectual Property Rights
 * extension.</p>
 *
 * <p>Use this to access the IPR details as provided in an EPP Domain Info response compliant
 * with RFC5730 and RFC5731. Such a service element is sent by a compliant EPP server in response to a valid
 * Domain Info command.</p>
 *
 * <p>For flexibility, this implementation extracts the data from the response using XPath queries, the expressions
 * for which are defined statically.</p>
 *
 * @see neustar.registry.jtoolkit2.se.DomainInfoCommand
 * @see neustar.registry.jtoolkit2.se.DomainInfoResponse
 */
public final class DomainInfoIprResponseExtension extends ResponseExtension {

    private static final long serialVersionUID = -2441248857298156911L;
    private static final String IPR_EXT = ResponseExtension.EXTENSION_EXPR + "/ipr:infData";
    private static final String IPR_NAME = ResponseExtension.EXTENSION_EXPR + "/ipr:infData/ipr:name";
    private static final String IPR_NUMBER = ResponseExtension.EXTENSION_EXPR + "/ipr:infData/ipr:number";
    private static final String CC_LOCALITY = ResponseExtension.EXTENSION_EXPR + "/ipr:infData/ipr:ccLocality";
    private static final String ENTITLEMENT = ResponseExtension.EXTENSION_EXPR + "/ipr:infData/ipr:entitlement";
    private static final String APP_DATE = ResponseExtension.EXTENSION_EXPR + "/ipr:infData/ipr:appDate";
    private static final String REG_DATE = ResponseExtension.EXTENSION_EXPR + "/ipr:infData/ipr:regDate";
    private static final String FORM = ResponseExtension.EXTENSION_EXPR + "/ipr:infData/ipr:form";
    private static final String PRE_VERIFIED = ResponseExtension.EXTENSION_EXPR + "/ipr:infData/ipr:preVerified";
    private static final String TYPE = ResponseExtension.EXTENSION_EXPR + "/ipr:infData/ipr:type";
    private static final String CLASS = ResponseExtension.EXTENSION_EXPR + "/ipr:infData/ipr:class";
    private IprDetails iprDetails;
    private boolean initialized;
    /**
     * @see ResponseExtension#fromXML(XMLDocument)
     */
    public void fromXML(final XMLDocument xmlDoc)
            throws XPathExpressionException {
        Node dataNode = xmlDoc.getElement(IPR_EXT);
        if (dataNode != null) {
            String iprName = xmlDoc.getNodeValue(IPR_NAME);
            String iprNumber = xmlDoc.getNodeValue(IPR_NUMBER);
            String ccLocality = xmlDoc.getNodeValue(CC_LOCALITY);
            IprEntitlement entitlement = getIprEntitlement(xmlDoc);
            Date appDate = getDate(xmlDoc, APP_DATE);
            Date regDate = getDate(xmlDoc, REG_DATE);
            IprForm form = getIprForm(xmlDoc);
            String iprClass = xmlDoc.getNodeValue(CLASS);
            String preVerified = xmlDoc.getNodeValue(PRE_VERIFIED);
            String type = xmlDoc.getNodeValue(TYPE);

            iprDetails = new IprDetails(iprName, iprNumber, ccLocality, entitlement, appDate, regDate, form, iprClass,
                    preVerified, type);

            initialized = iprName != null
                    || iprNumber != null
                    || ccLocality != null
                    || entitlement != null
                    || appDate != null
                    || regDate != null
                    || form != null
                    || iprClass != null
                    || preVerified != null
                    || type != null;
        }
    }

    private IprForm getIprForm(XMLDocument xmlDoc) throws XPathExpressionException {
        String iprFormString = xmlDoc.getNodeValue(FORM);
        IprForm form = null;
        if (iprFormString != null) {
            form = IprForm.valueOf(iprFormString);
        }
        return form;
    }

    private Date getDate(XMLDocument xmlDoc, String date) throws XPathExpressionException {
        String appDateString = xmlDoc.getNodeValue(date);
        Date appDate = null;
        if (appDateString != null) {
            appDate = EPPDateFormatter.fromXSDateTime(appDateString).getTime();
        }
        return appDate;
    }

    private IprEntitlement getIprEntitlement(XMLDocument xmlDoc) throws XPathExpressionException {
        String entitlementString = xmlDoc.getNodeValue(ENTITLEMENT);
        IprEntitlement entitlement = null;
        if (entitlementString != null) {
             entitlement = IprEntitlement.valueOf(entitlementString);
        }
        return entitlement;
    }

    public IprDetails getIprDetails() {
        return iprDetails;
    }

    @Override
    public boolean isInitialised() {
        return initialized;
    }
}
