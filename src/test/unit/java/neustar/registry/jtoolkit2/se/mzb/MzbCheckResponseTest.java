package neustar.registry.jtoolkit2.se.mzb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import neustar.registry.jtoolkit2.xml.XMLDocument;
import neustar.registry.jtoolkit2.xml.XMLParser;

public class MzbCheckResponseTest {
    private static final String XML =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                + "<response><result code=\"1000\"><msg>Command completed successfully</msg></result><resData>"
                + "<chkData xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\""
                + " xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">"
                + "<cd><label>example</label><roids><roid>XXX-YYY</roid></roids></cd>"
                + "</chkData>"
                + "</resData><trID><clTRID>ABC-12345</clTRID><svTRID>54322-XYZ</svTRID></trID></response></epp>";
    private MzbCheckResponse response;

    @Before
    public void setUp() throws Exception {
        response = new MzbCheckResponse();
        XMLParser parser = new XMLParser();
        XMLDocument doc = parser.parse(XML);
        response.fromXML(doc);
    }

    @Test
    public void shouldReturnRoidsForLabel() {
        assertTrue(response.getNames().contains("example"));
        assertEquals("XXX-YYY", response.getRoids("example")[0]);
    }

    @Test
    public void testGetResults() {
        assertEquals(1000, response.getResults()[0].getResultCode());
    }

    @Test
    public void testGetCLTRID() {
        assertEquals("ABC-12345", response.getCLTRID());
    }

}
