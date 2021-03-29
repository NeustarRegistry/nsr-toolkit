package neustar.registry.jtoolkit2.se.eps;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.xml.XMLDocument;
import neustar.registry.jtoolkit2.xml.XMLParser;

public class EpsRenewResponseTest {
    private static final String XML =
        "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><response><result code=\"1000\"><msg>Command completed successfully</msg></result><resData><eps:renData xmlns:eps=\"http://ns.uniregistry.net/eps-1.0\" xsi:schemaLocation=\"http://ns.uniregistry.net/eps-1.0 eps-1.0.xsd\"><eps:roid>XXX-RRR</eps:roid><eps:exDate>2005-04-03T22:00:00.0Z</eps:exDate></eps:renData></resData><trID><clTRID>ABC-12345</clTRID><svTRID>54322-XYZ</svTRID></trID></response></epp>";
    private EpsRenewResponse response;

    @Before
    public void setUp() throws Exception {
        response = new EpsRenewResponse();
        XMLParser parser = new XMLParser();
        XMLDocument doc = parser.parse(XML);
        response.fromXML(doc);
    }

    @Test
    public void testGetName() {
        assertEquals("XXX-RRR", response.getRoid());
    }

    @Test
    public void testGetExpiryDate() {
        assertEquals(
                EPPDateFormatter.fromXSDateTime("2005-04-03T22:00:00.0Z"),
                response.getExpiryDate());
    }

    @Test
    public void testGetCLTRID() {
        assertEquals("ABC-12345", response.getCLTRID());
    }
}
