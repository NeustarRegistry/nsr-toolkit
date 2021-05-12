package neustar.registry.jtoolkit2.se.mzb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;
import neustar.registry.jtoolkit2.se.Command;

public class MzbUpdateCommandTest {

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void shouldChangeAuthInfoAndRegistrant() {
        Command cmd = new MzbUpdateCommand("XXX-RRR", "password", "registrant", false);
        try {
            String xml = cmd.toXML();
            assertEquals(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                            + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                            + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                            + "<command><update>"
                            + "<update xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\""
                            + " xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">"
                            + "<roid>XXX-RRR</roid>"
                            + "<chg><registrant>registrant</registrant><authInfo><pw>password</pw></authInfo></chg>"
                            + "</update></update><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>",
                    xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldChangeRegistrant() {
        Command cmd = new MzbUpdateCommand("XXX-RRR", null, "registrant", false);
        try {
            String xml = cmd.toXML();
            assertEquals(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                            " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                            " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                            "<command><update>" +
                            "<update xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\"" +
                            " xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">" +
                            "<roid>XXX-RRR</roid>" +
                            "<chg><registrant>registrant</registrant></chg>" +
                            "</update></update><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>",
                    xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }


    @Test
    public void shouldChangeAuthInfo() {
        Command cmd = new MzbUpdateCommand("XXX-RRR", "password", null, false);
        try {
            String xml = cmd.toXML();
            assertEquals(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                            " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                            " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                            "<command><update>" +
                            "<update xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\"" +
                            " xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">" +
                            "<roid>XXX-RRR</roid>" +
                            "<chg><authInfo><pw>password</pw></authInfo></chg>" +
                            "</update></update><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>",
                    xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }


    @Test
    public void shouldBeAbleToRemoveAuthInfo() {
        Command cmd = new MzbUpdateCommand("XXX-RRR", null, null, true);
        try {
            String xml = cmd.toXML();
            assertEquals(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                            " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                            " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                            "<command><update>" +
                            "<update xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\"" +
                            " xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">" +
                            "<roid>XXX-RRR</roid>" +
                            "<chg><authInfo><null/></authInfo></chg>" +
                            "</update></update><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>",
                    xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldProvideNoChangeWhenRegistrantAndAuthInfoNotSupplied() {
        Command cmd = new MzbUpdateCommand("XXX-RRR", null, null, false);
        try {
            String xml = cmd.toXML();
            assertEquals(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                            " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                            " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                            "<command><update>" +
                            "<update xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\"" +
                            " xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">" +
                            "<roid>XXX-RRR</roid><chg/>" +
                            "</update></update><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>",
                    xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

}
