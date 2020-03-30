package neustar.registry.jtoolkit2.se.maintenance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import neustar.registry.jtoolkit2.Timer;
import neustar.registry.jtoolkit2.se.CLTRID;

public class MaintenanceWindowInfoCommandTest {

    private MaintenanceWindowInfoCommand cmd1;

    @Before
    public void setUp() throws Exception {
        Timer.setTime("20070101.010101");
        CLTRID.setClID("JTKUTEST");
        cmd1 = new MaintenanceWindowInfoCommand("maint-win-id");
    }

    @Test
    public void shouldNotReturnNullObject() {
        assertNotNull(cmd1);
    }

    @Test
    public void shouldReturnCorrectCommandName() {
        assertEquals("info", cmd1.getCommandType().getCommandName());
    }

    @Test
    public void shouldReturnCorrectObjectType() {
        assertEquals("maint", cmd1.getObjectType().getName());
    }

    @Test
    public void shouldReturnValidXml() {
        try {
            assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?><epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0"
                    + "\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation="
                    + "\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\"><command><info><info xmlns="
                    + "\"urn:ietf:params:xml:ns:maintenance-0.2\" xsi:schemaLocation=" +
                    "\"urn:ietf:params:xml:ns:maintenance-0.2 maintenance-0.2.xsd\"><id>maint-win-id</id></info>"
                    + "</info><clTRID>JTKUTEST.20070101.010101.0</clTRID></command></epp>", cmd1.toXML());
        } catch (SAXException saxe) {
            fail(saxe.getMessage());
        }
    }

}

