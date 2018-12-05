package neustar.registry.jtoolkit2.se.sync;

import java.util.GregorianCalendar;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.ErrorPkg;
import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.CommandExtension;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.xml.XMLWriter;
import org.w3c.dom.Element;

/**
 * Models the EPP Extension for synchronising the expiry date of a Domain Name.
 */
public class SyncDomainUpdateCommandExtension implements CommandExtension {

    private static final long serialVersionUID = -6020999765974335341L;

    private final String syncExpiryDate;

    /**
     * @param exDate The domain expiry date. Required.
     * @throws IllegalArgumentException if {@code exDate} is {@code null}.
     */
    public SyncDomainUpdateCommandExtension(GregorianCalendar exDate) {
        if (exDate == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage("se.domain.update.sync.exDate.missing"));
        }

        syncExpiryDate = EPPDateFormatter.toXSDateTime(exDate);
    }

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();

        final Element updateElement = xmlWriter.appendChild(extensionElement, "update",
                ExtendedObjectType.SYNC.getURI());
        final Element exDateElement = xmlWriter.appendChild(updateElement, "exDate");
        exDateElement.setTextContent(syncExpiryDate);
    }
}
