package neustar.registry.jtoolkit2.se.eps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import neustar.registry.jtoolkit2.xml.XMLDocument;
import neustar.registry.jtoolkit2.xml.XMLParser;

public class EpsExemptResponseTest {

    private static final String XML =
    "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
    + "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\">"
    + "  <response>"
    + "    <result code=\"1000\">"
    + "    <msg>Command completed successfully</msg>"
    + "    </result>"
    + "    <resData>"
    + "     <eps:empData xmlns:eps=\"http://ns.uniregistry.net/eps-1.0\">"
    + "        <eps:ed>"
    + "          <eps:label>test-validate</eps:label>"
    + "          <eps:exemptions>"
    + "            <eps:exemption>"
    + "              <eps:iprID>3111246</eps:iprID>"
    + "              <eps:labels>"
    + "                <eps:label>test-andvalidate</eps:label>"
    + "                <eps:label>test-validate</eps:label>"
    + "              </eps:labels>"
    + "            </eps:exemption>"
    + "            <eps:exemption>"
    + "              <eps:iprID>3111246</eps:iprID>"
    + "              <eps:labels>"
    + "                <eps:label>sunrise-registration</eps:label>"
    + "              </eps:labels>"
    + "            </eps:exemption>"
    + "          </eps:exemptions>"
    + "        </eps:ed>"
    + "      </eps:empData>"
    + "    </resData>"
    + "    <trID>"
    + "      <clTRID>ABC-12345</clTRID>"
    + "      <svTRID>SRV-ID</svTRID>"
    + "    </trID>"
    + "  </response>"
    + "</epp>";

    private EpsExemptResponse response;

    @Before
    public void setUp() throws Exception {
        response = new EpsExemptResponse();
        XMLParser parser = new XMLParser();
        XMLDocument doc = parser.parse(XML);
        response.fromXML(doc);
    }

    @Test
    public void shouldReturnRoidsForLabel() {
        assertTrue(response.getNames().contains("test-validate"));
        assertEquals(1, response.getNames().size());
        final EpsExemption[] exemptions = response.getExemptions("test-validate");
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