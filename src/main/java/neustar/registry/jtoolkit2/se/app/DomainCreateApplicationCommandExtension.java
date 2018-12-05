package neustar.registry.jtoolkit2.se.app;

import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.CommandExtension;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.xml.XMLWriter;
import org.w3c.dom.Element;

/**
 * <p>Extension for the EPP Domain Create command, representing the Create Application and Allocate Application
 * aspects of the Domain Name Application extension.</p>
 *
 * <p>Use this to identify the domain name application phase or application id that this command is being submitted
 * in as part of an EPP Domain Create command compliant with RFC5730 and RFC5731. The response expected from a
 * server should be handled by a Domain Create Application Response.</p>
 *
 * @see neustar.registry.jtoolkit2.se.DomainCreateCommand
 * @see neustar.registry.jtoolkit2.se.app.DomainCreateApplicationResponseExtension
 * @see <a href="http://neustarregistry.github.io/doc/application-1.0/application-1.0.html">Domain Name Application
 * Extension Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainCreateApplicationCommandExtension implements CommandExtension {

    private static final long serialVersionUID = 5202343696850193788L;
    private String phase;
    private String applicationId;

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element createElement = xmlWriter.appendChild(extensionElement, "create",
                ExtendedObjectType.APP.getURI());
        if (phase != null) {
            xmlWriter.appendChild(createElement, "phase", ExtendedObjectType.APP.getURI()).setTextContent(phase);
        } else if (applicationId != null) {
            xmlWriter.appendChild(createElement, "id", ExtendedObjectType.APP.getURI()).setTextContent(applicationId);
        }
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
