package neustar.registry.jtoolkit2.se.ipr;

import static neustar.registry.jtoolkit2.se.ExtendedObjectType.IPR;

import org.w3c.dom.Element;

import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.CommandExtension;
import neustar.registry.jtoolkit2.se.DomainCreateCommand;
import neustar.registry.jtoolkit2.xml.XMLWriter;

/**
 * <p>Extension for the EPP Domain Create command, representing the Create aspects of the Association extension.</p>
 *
 * <p>Use this to identify the association contact client assigned id that this command is being submitted
 * in as part of an EPP Domain Create command compliant with RFC5730 and RFC5731. The response expected from a
 * server should be handled by a Domain Create Response.</p>
 *
 * @see DomainCreateCommand
 */
public class IprCreateCommandExtension implements CommandExtension {
    private static final long serialVersionUID = 4324879283895987704L;
    private final IprDetails iprDetails;

    public IprCreateCommandExtension(IprDetails iprDetails) {
        this.iprDetails = iprDetails;
    }

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element createElement = xmlWriter.appendChild(extensionElement, "create", IPR.getURI());
        iprDetails.appendToElement(xmlWriter, createElement);
    }

}
