package neustar.registry.jtoolkit2.se.eps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;
import neustar.registry.jtoolkit2.se.Command;

public class EpsDeleteCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void shouldCreateCommandWithRequiredFields() {
        Command cmd = new EpsDeleteCommand("ROID-XXX");
        try {
            String xml = cmd.toXML();
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                    + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                    + "<command><delete><delete xmlns=\"http://ns.uniregistry.net/eps-1.0\""
                    + " xsi:schemaLocation=\"http://ns.uniregistry.net/eps-1.0 eps-1.0.xsd\">"
                    + "<roid>ROID-XXX</roid>"
                    + "</delete></delete><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldCreateCommandWithRequiredFieldsAndIntellectualPropertyId() {
        thrown.expect(IllegalArgumentException.class);
        Command cmd = new EpsDeleteCommand(null);
        try {
            cmd.toXML();
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

}
