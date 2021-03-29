package neustar.registry.jtoolkit2.se.eps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;
import neustar.registry.jtoolkit2.se.Command;

public class EpsUpdateCommandTest {

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void shouldChangeAuthInfoAndRegistrant() {
        Command cmd = new EpsUpdateCommand("XXX-RRR", "password", "registrant", false);
        try {
            String xml = cmd.toXML();
            assertEquals(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                            + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                            + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                            + "<command><update>"
                            + "<update xmlns=\"http://ns.uniregistry.net/eps-1.0\""
                            + " xsi:schemaLocation=\"http://ns.uniregistry.net/eps-1.0 eps-1.0.xsd\">"
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
        Command cmd = new EpsUpdateCommand("XXX-RRR", null, "registrant", false);
        try {
            String xml = cmd.toXML();
            assertEquals(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                            " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                            " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                            "<command><update>" +
                            "<update xmlns=\"http://ns.uniregistry.net/eps-1.0\"" +
                            " xsi:schemaLocation=\"http://ns.uniregistry.net/eps-1.0 eps-1.0.xsd\">" +
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
        Command cmd = new EpsUpdateCommand("XXX-RRR", "password", null, false);
        try {
            String xml = cmd.toXML();
            assertEquals(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                            " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                            " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                            "<command><update>" +
                            "<update xmlns=\"http://ns.uniregistry.net/eps-1.0\"" +
                            " xsi:schemaLocation=\"http://ns.uniregistry.net/eps-1.0 eps-1.0.xsd\">" +
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
        Command cmd = new EpsUpdateCommand("XXX-RRR", null, null, true);
        try {
            String xml = cmd.toXML();
            assertEquals(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                            " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                            " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                            "<command><update>" +
                            "<update xmlns=\"http://ns.uniregistry.net/eps-1.0\"" +
                            " xsi:schemaLocation=\"http://ns.uniregistry.net/eps-1.0 eps-1.0.xsd\">" +
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
        Command cmd = new EpsUpdateCommand("XXX-RRR", null, null, false);
        try {
            String xml = cmd.toXML();
            assertEquals(
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"" +
                            " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                            " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                            "<command><update>" +
                            "<update xmlns=\"http://ns.uniregistry.net/eps-1.0\"" +
                            " xsi:schemaLocation=\"http://ns.uniregistry.net/eps-1.0 eps-1.0.xsd\">" +
                            "<roid>XXX-RRR</roid><chg/>" +
                            "</update></update><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>",
                    xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

}
