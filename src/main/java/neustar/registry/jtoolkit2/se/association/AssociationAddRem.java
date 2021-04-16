package neustar.registry.jtoolkit2.se.association;

import org.w3c.dom.Element;

import neustar.registry.jtoolkit2.se.AddRemType;
import neustar.registry.jtoolkit2.se.Appendable;
import neustar.registry.jtoolkit2.xml.XMLWriter;

/**
 * Specification of how to write the add and rem elements to a domain update
 * command.  Use subclasses of this to set attributes to add or remove from a
 * domain object.
 */
public abstract class AssociationAddRem implements Appendable {

    private static final long serialVersionUID = -5023022665651367748L;

    private final String type;
    private AssociationContact[] contacts;

    /**
     * Maximal specification of the attribute values which may be added or
     * removed from a domain. Each of the parameters is optional, but at least
     * one must be specified.
     */
    public AssociationAddRem(AddRemType type, AssociationContact[] contacts) {
        this.type = type.toString();

        if (contacts != null) {
            this.contacts = contacts.clone();
        }
    }

    public Element appendToElement(XMLWriter xmlWriter, Element parent) {
        Element addRem = xmlWriter.appendChild(parent, type);

        if (contacts != null) {
            for (AssociationContact contact : contacts) {
                contact.addContact(xmlWriter, addRem);
            }
        }

        return addRem;
    }

}

