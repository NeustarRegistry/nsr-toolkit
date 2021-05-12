package neustar.registry.jtoolkit2.se.mzb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import neustar.registry.jtoolkit2.xml.XMLDocument;
import neustar.registry.jtoolkit2.xml.XMLParser;

public class MzbExemptResponseTest {

    private static final String XML =
    "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
    + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\">"
    + "  <response>"
    + "    <result code=\"1000\">"
    + "    <msg>Command completed successfully</msg>"
    + "    </result>"
    + "    <resData>"
    + "     <mzb:empData xmlns:mzb=\"urn:gdreg:params:xml:ns:mzb-1.0\">"
    + "        <mzb:ed>"
    + "          <mzb:label>test-validate</mzb:label>"
    + "          <mzb:exemptions>"
    + "            <mzb:exemption>"
    + "              <mzb:iprID>3111246</mzb:iprID>"
    + "              <mzb:labels>"
    + "                <mzb:label>test-andvalidate</mzb:label>"
    + "                <mzb:label>test-validate</mzb:label>"
    + "              </mzb:labels>"
    + "            </mzb:exemption>"
    + "            <mzb:exemption>"
    + "              <mzb:iprID>3111246</mzb:iprID>"
    + "              <mzb:labels>"
    + "                <mzb:label>sunrise-registration</mzb:label>"
    + "              </mzb:labels>"
    + "            </mzb:exemption>"
    + "          </mzb:exemptions>"
    + "        </mzb:ed>"
    + "      </mzb:empData>"
    + "    </resData>"
    + "    <trID>"
    + "      <clTRID>ABC-12345</clTRID>"
    + "      <svTRID>SRV-ID</svTRID>"
    + "    </trID>"
    + "  </response>"
    + "</epp>";

    private MzbExemptResponse response;

    @Before
    public void setUp() throws Exception {
        response = new MzbExemptResponse();
        XMLParser parser = new XMLParser();
        XMLDocument doc = parser.parse(XML);
        response.fromXML(doc);
    }

    @Test
    public void shouldReturnRoidsForLabel() {
        assertTrue(response.getNames().contains("test-validate"));
        assertEquals(1, response.getNames().size());
        final MzbExemption[] exemptions = response.getExemptions("test-validate");
        assertEquals(2, exemptions.length);
        assertEquals("3111246", exemptions[0].getIprId());
        assertEquals(2, exemptions[0].getLabels().length);
        assertEquals("test-andvalidate", exemptions[0].getLabels()[0]);
        assertEquals("test-validate", exemptions[0].getLabels()[1]);
        assertEquals("3111246", exemptions[1].getIprId());
        assertEquals(1, exemptions[1].getLabels().length);
        assertEquals("sunrise-registration", exemptions[1].getLabels()[0]);
    }

    @Test
    public void testGetResults() {
        assertEquals(1000, response.getResults()[0].getResultCode());
    }

    @Test
    public void testGetCLTRID() {
        assertEquals("ABC-12345", response.getCLTRID());
    }

}