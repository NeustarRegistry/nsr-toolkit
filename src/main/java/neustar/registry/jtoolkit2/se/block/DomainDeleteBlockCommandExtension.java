package neustar.registry.jtoolkit2.se.block;

import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.CommandExtension;
import neustar.registry.jtoolkit2.se.ExtendedObjectType;
import neustar.registry.jtoolkit2.xml.XMLWriter;
import org.w3c.dom.Element;

/**
 * <p>Extension for the EPP Domain delete command, representing the Delete Block aspect of the Block extension.</p>
 *
 * <p>Use this to mark the ID of a Block to delete as part of an EPP Domain Delete command
 * compliant with RFC5730 and RFC5731. The response expected from a server should be
 * handled by a generic Response.</p>
 *
 * @see neustar.registry.jtoolkit2.se.DomainDeleteCommand
 * @see neustar.registry.jtoolkit2.se.Response
 * @see <a href="http://neustarregistry.github.io/doc/block-1.0/block-1.0.html">Block Extension Mapping for the
 * Extensible Provisioning Protocol (EPP)</a>
 */
public class DomainDeleteBlockCommandExtension implements CommandExtension {
    private static final long serialVersionUID = -3106443818428865374L;

    private String id;

    @Override
    public void addToCommand(Command command) {
        final XMLWriter xmlWriter = command.getXmlWriter();
        final Element extensionElement = command.getExtensionElement();
        final Element infoElement = xmlWriter.appendChild(extensionElement, "delete",
                ExtendedObjectType.BLOCK.getURI());

        xmlWriter.appendChild(infoElement, "id", ExtendedObjectType.BLOCK.getURI()).setTextContent(id);
    }

    public void setId(String id) {
        this.id = id;
    }
}
