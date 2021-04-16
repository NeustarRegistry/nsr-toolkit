package neustar.registry.jtoolkit2.se.association;

import org.w3c.dom.Element;

import neustar.registry.jtoolkit2.xml.XMLWriter;

public class AssociationContact {

    private final String contactId;
    private final String authInfo;
    private final String contactType;

    public AssociationContact(String contactId, String authInfo, String contactType) {
        this.contactId = contactId;
        this.authInfo = authInfo;
        this.contactType = contactType;
    }

    public String getAuthInfo() {
        return authInfo;
    }

    public String getContactId() {
        return contactId;
    }

    public String getContactType() {
        return contactType;
    }

    public void addContact(XMLWriter xmlWriter, Element createElement) {

        final Element contactElement = xmlWriter.appendChild(createElement, "contact", "type", getContactType());
        if (getContactId() != null) {
            xmlWriter.appendChild(contactElement, "id").setTextContent(getContactId());
            xmlWriter.appendChild(
                    xmlWriter.appendChild(
                            contactElement,
                            "authInfo"),
                    "pw").setTextContent(getAuthInfo());
        }
    }

}
