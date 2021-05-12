package neustar.registry.jtoolkit2.se.mzb;

import neustar.registry.jtoolkit2.se.Response;
import neustar.registry.jtoolkit2.xml.XMLDocument;

/**
 * Use this to access delete response data as provided in an
 * GoDaddy Registry EPP Extension EPS delete response.  Such a service element
 * is sent by a compliant EPP server in response to a valid EPS delete
 * command, implemented by the EpsDeleteCommand class.
 *
 * @see MzbDeleteCommand
 */
public class MzbDeleteResponse extends Response {
    private static final long serialVersionUID = -5724827272682186647L;

    public MzbDeleteResponse() {
        super();
    }

    @Override
    public void fromXML(XMLDocument xmlDoc) {
        super.fromXML(xmlDoc);

        if (!resultArray[0].succeeded()) {
            return;
        }

    }
}

