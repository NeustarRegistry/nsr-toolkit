package neustar.registry.jtoolkit2.se.eps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;
import neustar.registry.jtoolkit2.se.Command;

public class EpsRenewCommandTest {

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void shouldCreateXmlWithRoidExpireDateAndPeriod() {
        Command cmd = new EpsRenewCommand(
                "XXX-YYY",
                EPPDateFormatter.fromXSDateTime("2006-12-25T00:00:00.0Z"),
                5);
        try {
            String xml = cmd.toXML();
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                    " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                    " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                    "<command><renew><renew xmlns=\"http://ns.uniregistry.net/eps-1.0\"" +
                    " xsi:schemaLocation=\"http://ns.uniregistry.net/eps-1.0 eps-1.0.xsd\">" +
                    "<roid>XXX-YYY</roid>" +
                    "<curExpDate>2006-12-25</curExpDate>" +
                    "<period>5</period>" +
                    "</renew></renew><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

}
