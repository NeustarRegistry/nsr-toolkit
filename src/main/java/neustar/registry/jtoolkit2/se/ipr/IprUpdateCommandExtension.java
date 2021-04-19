package neustar.registry.jtoolkit2.se.ipr;

import static neustar.registry.jtoolkit2.se.ExtendedObjectType.IPR;

import org.w3c.dom.Element;

import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.CommandExtension;
import neustar.registry.jtoolkit2.xml.XMLWriter;


/**
 * <p>Extension for the EPP Domain Update command, representing the Update IPR aspect of the
 * Domain Name IPR extension.</p>
 *
 * <p>Use this to mark the ID of a domain name IPR details to update as part of an EPP Domain Update command
 * compliant with RFC5730 and RFC5731. The response expected from a server should be
 * handled by a generic Response object.</p>
 *
 * @see neustar.registry.jtoolkit2.se.DomainUpdateCommand
 * @see neustar.registry.jtoolkit2.se.Response
 */
public class IprUpdateCommandExtension implements CommandExtension {

    private static final long serialVersionUID = 5202343696850193788L;
    private final IprDetails chg;
    private final boolean removeIprDetails;

    public IprUpdateCommandExtension(IprDetails chg) {
        this.chg = chg;
        this.removeIprDetails = false;
    }

    public IprUpdateCommandExtension(boolean removeIprDetails) {
        super();
        this.chg = null;
        this.removeIprDetails = removeIprDetails;
    }

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element updateElement = xmlWriter.appendChild(extensionElement, "update", IPR.getURI());

        if (chg != null) {
            chg.appendToElement(xmlWriter, xmlWriter.appendChild(updateElement, "chg"));
        } else if (removeIprDetails) {
            xmlWriter.appendChild(updateElement, "rem");
        }

    }

}
