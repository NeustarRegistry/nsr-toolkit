package neustar.registry.jtoolkit2.se.eps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;

public class EpsInfoCommandTest {
    private EpsInfoCommand cmd1;

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
        cmd1 = new EpsInfoCommand("XXX-YYY");
    }

    @Test
    public void testEpsInfoCommand() {
        assertNotNull(cmd1);
    }

    @Test
    public void testGetCommandType() {
        assertEquals(cmd1.getCommandType().getCommandName(), "info");
    }

    @Test
    public void testGetObjectType() {
        assertEquals(cmd1.getObjectType().getName(), "eps");
    }

    @Test
    public void testToXML() {
        try {
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                    "<command><info><info xmlns=\"http://ns.uniregistry.net/eps-1.0\"" +
                    " xsi:schemaLocation=\"http://ns.uniregistry.net/eps-1.0 eps-1.0.xsd\">" +
                    "<roid>XXX-YYY</roid>" +
                    "</info></info><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", cmd1.toXML());
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldSupportHostsAttributeInDomainInfoCommandWithPassword() throws Exception {

        cmd1 = new EpsInfoCommand("XXX-YYY", "password");

        String outputXml = null;
        try {
            outputXml = cmd1.toXML();
        } catch (SAXException e) {
            fail("Parsing failed with message: " + e.getMessage());
        }

        String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                "<command><info><info xmlns=\"http://ns.uniregistry.net/eps-1.0\"" +
                " xsi:schemaLocation=\"http://ns.uniregistry.net/eps-1.0 eps-1.0.xsd\">" +
                "<roid>XXX-YYY</roid>" +
                "<authInfo><pw>password</pw></authInfo>" +
                "</info></info><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>";
        assertEquals("Should be expected output", expectedXml, outputXml);
    }

}

