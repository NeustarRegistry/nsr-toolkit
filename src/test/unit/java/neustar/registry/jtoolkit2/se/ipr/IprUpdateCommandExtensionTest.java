package neustar.registry.jtoolkit2.se.ipr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;
import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.CommandExtension;
import neustar.registry.jtoolkit2.se.DomainUpdateCommand;

public class IprUpdateCommandExtensionTest {

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void shouldChangeIprDetails() {
        IprDetails details = new IprDetails();
        details.setNumber("1");
        details.setName("someThing");
        details.setCcLocality("au");
        details.setEntitlement(IprEntitlement.coOwner);
        details.setAppDate(new Date(Timer.now()));
        details.setRegDate(new Date(Timer.now()));
        details.setForm(IprForm.government);
        details.setClassType("Classy");
        details.setPreVerified("Yes");
        details.setType("Type");

        CommandExtension ext = new IprUpdateCommandExtension(details);

        String domainName = "jtkutest.com.au";
        final Command cmd = new DomainUpdateCommand(domainName);
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
                    + "<update xmlns=\"urn:afilias:params:xml:ns:ipr-1.1\">"
                    + "<chg>"
                    + "<name>someThing</name>"
                    + "<number>1</number>"
                    + "<ccLocality>au</ccLocality>"
                    + "<entitlement>co-owner</entitlement>"
                    + "<appDate>2006-12-31</appDate>"
                    + "<regDate>2006-12-31</regDate>"
                    + "<form>government</form>"
                    + "<class>Classy</class>"
                    + "<preVerified>Yes</preVerified>"
                    + "<type>Type</type>"
                    + "</chg>"
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
    public void shouldRemoveIprDetails() {

        CommandExtension ext = new IprUpdateCommandExtension(true);

        String domainName = "jtkutest.com.au";
        final Command cmd = new DomainUpdateCommand(domainName);
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
                    + "<update xmlns=\"urn:afilias:params:xml:ns:ipr-1.1\">"
                    + "<rem/>"
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