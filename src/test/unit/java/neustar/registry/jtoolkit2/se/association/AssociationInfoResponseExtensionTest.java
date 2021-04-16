package neustar.registry.jtoolkit2.se.association;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import neustar.registry.jtoolkit2.se.DomainInfoResponse;
import neustar.registry.jtoolkit2.xml.XMLDocument;
import neustar.registry.jtoolkit2.xml.XMLParser;

public class AssociationInfoResponseExtensionTest {

    private static final XMLParser PARSER = new XMLParser();

    @Test
    public void shouldReturnContact() throws Exception {
        final String domainName = "test.com";

        final DomainInfoResponse response = new DomainInfoResponse();
        final AssociationInfoResponseExtension re = new AssociationInfoResponseExtension();

        AssociationContact contact = new AssociationContact("contact", "authInfo", "membership");
        final XMLDocument doc = PARSER.parse(getExpectedXml(domainName, contact));
        response.registerExtension(re);
        response.fromXML(doc);

        assertEquals(domainName, response.getName());
        assertTrue("Extension should have been initialised", re.isInitialised());
        final AssociationContact[] contacts = re.getContacts();
        assertEquals(1, contacts.length);
        final AssociationContact contact1 = contacts[0];
        assertEquals("contact", contact1.getContactId());
        assertEquals("membership", contact1.getContactType());
        assertEquals("authInfo", contact1.getAuthInfo());
    }


    private static String getExpectedXml(final String domainName, final AssociationContact contact) {
        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"")
                .append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"")
                .append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">")
                .append("<response>")
                .append("<result code=\"1000\">")
                .append("<msg>Command completed successfully</msg>")
                .append("</result>")
                .append("<resData>")
                .append("<infData xmlns=\"urn:ietf:params:xml:ns:domain-1.0\"")
                .append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">")
                .append("<name>").append(domainName).append("</name>")
                .append("<roid>D0000003-AR</roid>")
                .append("<status s=\"ok\" lang=\"en\"/>")
                .append("<registrant>EXAMPLE</registrant>")
                .append("<contact type=\"tech\">EXAMPLE</contact>")
                .append("<ns>")
                .append("<hostObj>ns1.example.com.au</hostObj>")
                .append("<hostObj>ns2.example.com.au</hostObj>")
                .append("</ns>")
                .append("<host>ns1.example.com.au</host>")
                .append("<host>ns2.exmaple.com.au</host>")
                .append("<clID>Registrar</clID>")
                .append("<crID>Registrar</crID>")
                .append("<crDate>2006-02-09T15:44:58.0Z</crDate>")
                .append("<exDate>2008-02-10T00:00:00.0Z</exDate>")
                .append("<authInfo>")
                .append("<pw>0192pqow</pw>")
                .append("</authInfo>")
                .append("</infData>")
                .append("</resData>");

        result.append("<extension>")
                .append("<infData xmlns=\"urn:afilias:params:xml:ns:association-1.0\">")
                .append("<contact type=\"" + contact.getContactType() + "\">")
                .append("<id>" + contact.getContactId() + "</id>")
                .append("<authInfo><pw>" + contact.getAuthInfo() + "</pw></authInfo>")
                .append("</contact>")
                .append("</infData>")
                .append("</extension>");

        result.append("<trID>")
                .append("<clTRID>ABC-12345</clTRID>")
                .append("<svTRID>54321-XYZ</svTRID>")
                .append("</trID>")
                .append("</response>")
                .append("</epp>");
        return result.toString();
    }
}