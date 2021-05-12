package neustar.registry.jtoolkit2.se.mzb;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.xml.XMLDocument;
import neustar.registry.jtoolkit2.xml.XMLParser;

public class MzbCreateResponseTest {

    private static final XMLParser PARSER = new XMLParser();

    @Test
    public void shouldReturnRoid() throws Exception {
        final MzbCreateResponse response = new MzbCreateResponse();
        final XMLDocument doc = PARSER.parse(getCreateResponseExpectedXml("BXXX-YYY"));
        response.fromXML(doc);
        assertEquals("BXXX-YYY", response.getRoid());
    }

    @Test
    public void shouldReturnExpiryDate() throws Exception {
        final MzbCreateResponse response = new MzbCreateResponse();
        final XMLDocument doc = PARSER.parse(getCreateResponseExpectedXml("BXXX-YYY"));
        response.fromXML(doc);
        assertEquals(EPPDateFormatter.fromXSDateTime("2001-04-03T22:00:00.0Z"), response.getExpiryDate());
    }

    @Test
    public void shouldReturnCreateDate() throws Exception {
        final MzbCreateResponse response = new MzbCreateResponse();
        final XMLDocument doc = PARSER.parse(getCreateResponseExpectedXml("BXXX-YYY"));
        response.fromXML(doc);
        assertEquals(EPPDateFormatter.fromXSDateTime("1999-04-03T22:00:00.0Z"), response.getCreateDate());
    }

    private static String getCreateResponseExpectedXml(final String roid) {
        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        result.append("<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"");
        result.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        result.append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">");
        result.append("<response>");
        result.append("<result code=\"1000\">");
        result.append("<msg>Command completed successfully</msg>");
        result.append("</result>");
        result.append("<resData>");
        result.append("<mzb:creData xmlns:mzb=\"urn:gdreg:params:xml:ns:mzb-1.0\"");
        result.append(" xsi:schemaLocation=\"urn:gdreg:params:xml:ns:mzb-1.0 mzb-1.0.xsd\">");
        result.append("<mzb:roid>").append(roid).append("</mzb:roid>");
        result.append("<mzb:crDate>1999-04-03T22:00:00.0Z</mzb:crDate>");
        result.append("<mzb:exDate>2001-04-03T22:00:00.0Z</mzb:exDate>");
        result.append("</mzb:creData>");
        result.append("</resData>");
        result.append("<trID>");
        result.append("<clTRID>ABC-12345</clTRID>");
        result.append("<svTRID>54321-XYZ</svTRID>");
        result.append("</trID>");
        result.append("</response>");
        result.append("</epp>");

        return result.toString();
    }

}
