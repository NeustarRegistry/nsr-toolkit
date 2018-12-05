package neustar.registry.jtoolkit2.se.app;

import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.CommandExtension;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.xml.XMLWriter;
import org.w3c.dom.Element;


/**
 * <p>Extension for the EPP Domain Update command, representing the Update Application aspect of the
 * Domain Name Application extension.</p>
 *
 * <p>Use this to mark the ID of a domain name application to update as part of an EPP Domain Update command
 * compliant with RFC5730 and RFC5731. The response expected from a server should be
 * handled by a generic Response object.</p>
 *
 * @see neustar.registry.jtoolkit2.se.DomainUpdateCommand
 * @see neustar.registry.jtoolkit2.se.Response
 * @see <a href="http://neustarregistry.github.io/doc/application-1.0/application-1.0.html">Domain Name Application
 * Extension Mapping for the Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainUpdateApplicationCommandExtension implements CommandExtension {

    private static final long serialVersionUID = 5202343696850193788L;
    private String applicationId;

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element createElement = xmlWriter.appendChild(extensionElement, "update",
                ExtendedObjectType.APP.getURI());

        xmlWriter.appendChild(createElement, "id", ExtendedObjectType.APP.getURI())
                .setTextContent(applicationId);

    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

}
