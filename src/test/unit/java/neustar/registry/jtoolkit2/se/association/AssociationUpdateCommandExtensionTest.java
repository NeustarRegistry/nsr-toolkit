package neustar.registry.jtoolkit2.se.association;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;
import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.DomainUpdateCommand;

public class AssociationUpdateCommandExtensionTest {
    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void shouldGenerateValidXMLForAddContact() {
        String domainName = "jtkutest.com.au";
        String id = "BD-001";
        String authInfo = "11@@qqWW";
        String type = "membership";

        AssociationContact contact = new AssociationContact(id, authInfo, type);
        Command cmd = new DomainUpdateCommand("jtkutest.com.au");
        AssociationAdd add = new AssociationAdd(new AssociationContact[] {contact});
        AssociationUpdateCommandExtension ext = new AssociationUpdateCommandExtension(add, null);
        try {
            cmd.appendExtension(ext);
            String xml = cmd.toXML();
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                    + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                    + "<command>"
                    + "<update>"
                    + "<update xmlns=\"urn:ietf:params:xml:ns:domain-1.0\" "
                    + "xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">"
                    + "<name>" + domainName + "</name>"
                    + "</update>"
                    + "</update>"
                    + "<extension>"
                    + "<update xmlns=\"urn:afilias:params:xml:ns:association-1.0\">"
                    + "<add>"
                    + "<contact type=\"" + type + "\">"
                    + "<id>" + id + "</id>"
                    + "<authInfo><pw>" + authInfo + "</pw></authInfo>"
                    + "</contact>"
                    + "</add>"
                    + "</update>"
                    + "</extension>"
                    + "<clTRID>JTKUTEST.20070101.010101.0</clTRID>"
                    + "</command>"
                    + "</epp>";
            assertEquals(expectedXml, xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldGenerateValidXMLForRemContact() {
        String domainName = "jtkutest.com.au";
        String id = "BD-001";
        String authInfo = "11@@qqWW";
        String type = "membership";

        AssociationContact contact = new AssociationContact(id, authInfo, type);
        Command cmd = new DomainUpdateCommand("jtkutest.com.au");
        AssociationRem rem = new AssociationRem(new AssociationContact[] {contact});
        AssociationUpdateCommandExtension ext = new AssociationUpdateCommandExtension(null, rem);
        try {
            cmd.appendExtension(ext);
            String xml = cmd.toXML();
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                    + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                    + "<command>"
                    + "<update>"
                    + "<update xmlns=\"urn:ietf:params:xml:ns:domain-1.0\" "
                    + "xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">"
                    + "<name>" + domainName + "</name>"
                    + "</update>"
                    + "</update>"
                    + "<extension>"
                    + "<update xmlns=\"urn:afilias:params:xml:ns:association-1.0\">"
                    + "<rem>"
                    + "<contact type=\"" + type + "\">"
                    + "<id>" + id + "</id>"
                    + "<authInfo><pw>" + authInfo + "</pw></authInfo>"
                    + "</contact>"
                    + "</rem>"
                    + "</update>"
                    + "</extension>"
                    + "<clTRID>JTKUTEST.20070101.010101.0</clTRID>"
                    + "</command>"
                    + "</epp>";
            assertEquals(expectedXml, xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldGenerateValidXMLForAddRemContact() {
        String domainName = "jtkutest.com.au";
        String id = "BD-001";
        String authInfo = "11@@qqWW";
        String type = "membership";

        AssociationContact contact = new AssociationContact(id, authInfo, type);
        Command cmd = new DomainUpdateCommand("jtkutest.com.au");
        AssociationAdd add = new AssociationAdd(new AssociationContact[] {contact});
        AssociationRem rem = new AssociationRem(new AssociationContact[] {contact});
        AssociationUpdateCommandExtension ext = new AssociationUpdateCommandExtension(add, rem);
        try {
            cmd.appendExtension(ext);
            String xml = cmd.toXML();
            String expectedXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                    + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                    + "<command>"
                    + "<update>"
                    + "<update xmlns=\"urn:ietf:params:xml:ns:domain-1.0\" "
                    + "xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">"
                    + "<name>" + domainName + "</name>"
                    + "</update>"
                    + "</update>"
                    + "<extension>"
                    + "<update xmlns=\"urn:afilias:params:xml:ns:association-1.0\">"
                    + "<add>"
                    + "<contact type=\"" + type + "\">"
                    + "<id>" + id + "</id>"
                    + "<authInfo><pw>" + authInfo + "</pw></authInfo>"
                    + "</contact>"
                    + "</add>"
                    + "<rem>"
                    + "<contact type=\"" + type + "\">"
                    + "<id>" + id + "</id>"
                    + "<authInfo><pw>" + authInfo + "</pw></authInfo>"
                    + "</contact>"
                    + "</rem>"
                    + "</update>"
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