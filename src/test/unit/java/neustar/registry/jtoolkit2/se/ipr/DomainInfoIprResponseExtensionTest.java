package neustar.registry.jtoolkit2.se.ipr;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import neustar.registry.jtoolkit2.se.DomainInfoResponse;
import neustar.registry.jtoolkit2.xml.XMLParser;

public class DomainInfoIprResponseExtensionTest {
    private XMLParser parser = new XMLParser();

    @Test
    public void testResponse() throws Exception {
        String iprClass = "nonResolver";
        String iprName = "Ipr Name";
        String iprNumber = "12345";
        String blockInfoXml =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                        "<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\" " +
                        "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                        "xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">" +
                        "<response>" +
                        "<result code=\"1000\">" +
                        "<msg>Command completed successfully</msg>" +
                        "</result>" +
                        "<resData>" +
                        "<infData xmlns=\"urn:ietf:params:xml:ns:domain-1.0\"" +
                        " xsi:schemaLocation=\"urn:ietf:params:xml:ns:domain-1.0 domain-1.0.xsd\">" +
                        "<name>example.com</name>" +
                        "<roid>D0000003-AR</roid>" +
                        "<registrant>EXAMPLE</registrant>" +
                        "<clID>Registrar</clID>" +
                        "<crDate>2006-02-09T15:44:58.0Z</crDate>" +
                        "<exDate>2008-02-10T00:00:00.0Z</exDate>" +
                        "</infData>" +
                        "</resData>" +
                        "<extension>" +
                        "<ipr:infData xmlns:ipr=\"urn:afilias:params:xml:ns:ipr-1.1\">" +
                        "<ipr:name>" + iprName + "</ipr:name>" +
                        "<ipr:number>" + iprNumber + "</ipr:number>" +
                        "<ipr:class>" + iprClass + "</ipr:class>" +
                        "</ipr:infData>" +
                        "</extension>" +
                        "<trID><clTRID>ABC-12345</clTRID><svTRID>54322-XYZ</svTRID></trID>" +
                        "</response>" +
                        "</epp>";
        final DomainInfoIprResponseExtension domainInfoIprResponseExtension = new DomainInfoIprResponseExtension();
        DomainInfoResponse response = new DomainInfoResponse();
        response.registerExtension(domainInfoIprResponseExtension);
        response.fromXML(parser.parse(blockInfoXml));

        assertThat(response.getResults()[0].getResultCode(), is(1000));
        assertThat(response.getResults()[0].getResultMessage(), is("Command completed successfully"));
        IprDetails iprDetails = domainInfoIprResponseExtension.getIprDetails();
        assertThat(iprDetails.getClassType(), is(iprClass));
        assertThat(iprDetails.getName(), is(iprName));
        assertThat(iprDetails.getNumber(), is(iprNumber));
    }
}
