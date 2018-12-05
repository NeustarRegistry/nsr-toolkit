package neustar.registry.jtoolkit2.se.tmch;

import java.util.GregorianCalendar;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.CommandExtension;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.xml.XMLWriter;
import org.w3c.dom.Element;

/**
 * <p>Extension for the EPP Domain Create command, representing the Create aspect of the
 * Domain Name Trademark Clearing House extension.</p>
 *
 * <p>Use this to identify the domain's encoded signed mark data or notice id as part of the result of this command,
 * which is being submitted in as part of an EPP Domain Create command compliant with RFC5730 and RFC5731.
 * The response expected from a server should be handled by a Domain Create Response.</p>
 *
 * @see neustar.registry.jtoolkit2.se.DomainCreateCommand
 * @see <a href="http://neustarregistry.github.io/doc/tmch-1.0/tmch-1.0.html">Domain Name Trademark Clearing House
 * Extension Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class TmchDomainCreateCommandExtension implements CommandExtension {

    private static final long serialVersionUID = 2000050396747484091L;

    private String encodedSignedMarkData;
    private String noticeId;
    private GregorianCalendar notAfterDateTime;
    private GregorianCalendar acceptedDateTime;

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element tmchCreateElement = xmlWriter.appendChild(extensionElement, "create",
                ExtendedObjectType.TMCH.getURI());

        if (encodedSignedMarkData != null) {
            xmlWriter.appendChild(tmchCreateElement, "smd", ExtendedObjectType.TMCH.getURI())
                    .setTextContent(encodedSignedMarkData);

        }

        if (noticeId != null) {
            xmlWriter.appendChild(tmchCreateElement, "noticeID", ExtendedObjectType.TMCH.getURI())
                    .setTextContent(noticeId);
        }

        if (notAfterDateTime != null) {
            xmlWriter.appendChild(tmchCreateElement, "notAfter", ExtendedObjectType.TMCH.getURI())
                    .setTextContent(EPPDateFormatter.toXSDateTime(notAfterDateTime));
        }

        if (acceptedDateTime != null) {
            xmlWriter.appendChild(tmchCreateElement, "accepted", ExtendedObjectType.TMCH.getURI())
                    .setTextContent(EPPDateFormatter.toXSDateTime(acceptedDateTime));
        }
    }

    public void setEncodedSignedMarkData(String encodedSignedMarkData) {
        this.encodedSignedMarkData = encodedSignedMarkData;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public void setNotAfterDateTime(GregorianCalendar notAfterDateTime) {
        this.notAfterDateTime = notAfterDateTime;
    }

    public void setAcceptedDateTime(GregorianCalendar acceptedDateTime) {
        this.acceptedDateTime = acceptedDateTime;
    }
}
