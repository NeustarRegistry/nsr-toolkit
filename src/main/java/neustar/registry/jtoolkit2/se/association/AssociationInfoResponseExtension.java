package neustar.registry.jtoolkit2.se.association;

import static neustar.registry.jtoolkit2.se.ExtendedObjectType.ASSOCIATION;

import javax.xml.xpath.XPathExpressionException;

import neustar.registry.jtoolkit2.se.ResponseExtension;
import neustar.registry.jtoolkit2.xml.XMLDocument;

/**
 * <p>Extension for the EPP Domain Info response, representing the Association Info aspects of the
 * Association extension.</p>
 *
 * <p>Use this to access info data for an Association Contact ID as provided in an EPP Domain Info response compliant
 * with RFC5730 and RFC5731. Such a service element is sent by a compliant EPP server in response to a valid
 * Domain Info command with the Association extension.</p>
 *
 * <p>For flexibility, this implementation extracts the data from the response using XPath queries, the expressions
 * for which are defined statically.</p>
 *
 * @see neustar.registry.jtoolkit2.se.DomainInfoCommand
 */
public class AssociationInfoResponseExtension extends ResponseExtension {
    private static final long serialVersionUID = -4403413192868009866L;

    private static final String NAMESPACE_PREFIX = "/" + ASSOCIATION.getName() + ":";
    private static final String XPATH_PREFIX = ResponseExtension.EXTENSION_EXPR + NAMESPACE_PREFIX
            + ResponseExtension.INFO + NAMESPACE_PREFIX + "contact";
    private static final String INDEX_EXPR = XPATH_PREFIX + "[IDX]/";
    private static final String ID_TYPE_EXPR = INDEX_EXPR + "/@type";
    private static final String ID_EXPR = INDEX_EXPR + NAMESPACE_PREFIX + "id/text()";
    private static final String PW_EXPR = INDEX_EXPR + NAMESPACE_PREFIX + "authInfo/" + NAMESPACE_PREFIX + "pw/text()";

    private static final String CHKDATA_COUNT_EXPR = "count(" + XPATH_PREFIX + ")";

    private boolean initialised;
    private AssociationContact[] contacts;

    @Override
    public void fromXML(XMLDocument xmlDoc) throws XPathExpressionException {

        int cdCount = xmlDoc.getNodeCount(CHKDATA_COUNT_EXPR);

        contacts = new AssociationContact[cdCount];

        for (int i = 0; i < cdCount; i++) {
            String id = xmlDoc.getNodeValue(replaceIndex(ID_EXPR, i + 1));

            initialised = id != null;

            contacts[i] = new AssociationContact(id, xmlDoc.getNodeValue(replaceIndex(PW_EXPR, i + 1)),
                                                     xmlDoc.getNodeValue(replaceIndex(ID_TYPE_EXPR, i + 1)));
        }
    }

    @Override
    public boolean isInitialised() {
        return initialised;
    }

    public AssociationContact[] getContacts() {
        return contacts;
    }

    public static String replaceIndex(final String inputExpr, final int index) {
        return inputExpr.replaceAll("IDX", String.valueOf(index));
    }
}
