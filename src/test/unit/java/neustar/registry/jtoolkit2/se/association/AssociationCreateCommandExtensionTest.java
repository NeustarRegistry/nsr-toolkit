package neustar.registry.jtoolkit2.se.association;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;
import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.DomainCreateCommand;

public class AssociationCreateCommandExtensionTest {
    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void shouldGenerateValidXML() {
        String domainName = "jtkutest.com.au";
        String id = "BD-001";
        String authInfo = "11@@qqWW";
        String type = "membership";

        AssociationContact contact = new AssociationContact(id, authInfo, type);
        final Command cmd = new DomainCreateCommand(domainName, "jtkUT3st", "01241326211", new String[] {"TC-01"});
        AssociationCreateCommandExtension ext = new AssociationCreateCommandExtension(contact);
        try {
            cmd.appendExtension(ext);
            String xml = cmd.toXML();
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                    + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                    + "<command>"
                    + "<create>"
                    + "<create xmlns=\"urn:ietf:params:xml:ns:domain-1.0\" "
                    + "xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">"
                    + "<name>" + domainName + "</name>"
                    + "<registrant>01241326211</registrant><contact type=\"tech\">TC-01</contact>"
                    + "<authInfo><pw>jtkUT3st</pw></authInfo>"
                    + "</create>"
                    + "</create>"
                    + "<extension>"
                    + "<create xmlns=\"urn:afilias:params:xml:ns:association-1.0\">"
                    + "<contact type=\"" + type + "\">"
                    + "<id>" + id + "</id>"
                    + "<authInfo><pw>" + authInfo + "</pw></authInfo>"
                    + "</contact>"
                    + "</create>"
                    + "</extension>"
                    + "<clTRID>JTKUTEST.20070101.010101.0</clTRID>"
                    + "</command>"
                    + "</epp>";
            assertEquals(expectedXml, xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

}