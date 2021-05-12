package neustar.registry.jtoolkit2.se.mzb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;
import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.Period;

public class MzbCreateCommandTest {

    @Before
    public void setUp() {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
    }

    @Test
    public void shouldCreateCommandWithRequiredFields() {
        Command cmd = new MzbCreateCommand(MzbType.standard, Collections.singletonList("jtkutest"), "jtkUT3st",
                "registrant", new Period(1), null);
        try {
            String xml = cmd.toXML();
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                    + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                    + "<command><create>"
                    + "<create xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\""
                    + " type=\"standard\""
                    + " xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">"
                    + "<labels><label>jtkutest</label></labels>"
                    + "<period>1</period>"
                    + "<registrant>registrant</registrant>"
                    + "<authInfo><pw>jtkUT3st</pw></authInfo>"
                    + "</create></create><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

    @Test
    public void shouldCreateCommandWithRequiredFieldsAndIntellectualPropertyId() {
        Command cmd = new MzbCreateCommand(MzbType.standard, Collections.singletonList("jtkutest"), "jtkUT3st",
                "registrant", new Period(1), "3111246");
        try {
            String xml = cmd.toXML();
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\""
                    + " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
                    + " xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">"
                    + "<command><create><create xmlns=\"urn:gdreg:params:xml:ns:mzb-1.0\""
                    + " type=\"standard\" xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">"
                    + "<labels><label>jtkutest</label></labels>"
                    + "<period>1</period>"
                    + "<registrant>registrant</registrant>"
                    + "<iprID>3111246</iprID>"
                    + "<authInfo><pw>jtkUT3st</pw></authInfo>"
                    + "</create></create><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", xml);
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

}
