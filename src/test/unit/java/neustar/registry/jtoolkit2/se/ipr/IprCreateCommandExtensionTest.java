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
import neustar.registry.jtoolkit2.se.DomainCreateCommand;

public class IprCreateCommandExtensionTest {

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void shouldAddIprDetails() {
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

        CommandExtension ext = new IprCreateCommandExtension(details);

        String domainName = "jtkutest.com.au";
        String authInfo = "11@@qqWW";
        final Command cmd = new DomainCreateCommand(domainName, "jtkUT3st", "01241326211", new String[] {"TC-01"});
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
                    + "<create xmlns=\"urn:afilias:params:xml:ns:ipr-1.1\">"
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