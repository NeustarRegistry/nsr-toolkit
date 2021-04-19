package neustar.registry.jtoolkit2.se.ipr;

import java.util.Date;
import java.util.GregorianCalendar;

import org.w3c.dom.Element;

import neustar.registry.jtoolkit2.EPPDateFormatter;
import neustar.registry.jtoolkit2.xml.XMLWriter;

public class IprDetails {

    private String name;
    private String number;
    private String ccLocality;
    private IprEntitlement entitlement;
    private Date appDate;
    private Date regDate;
    private IprForm form;
    private String classType;
    private String preVerified;
    private String type;

    public void appendToElement(XMLWriter xmlWriter, Element parent) {

        if (name != null) {
            xmlWriter.appendChild(parent, "name").setTextContent(name);
        }
        if (number != null) {
            xmlWriter.appendChild(parent, "number").setTextContent(number);
        }
        if (ccLocality != null) {
            xmlWriter.appendChild(parent, "ccLocality").setTextContent(ccLocality);
        }
        if (entitlement != null) {
            xmlWriter.appendChild(parent, "entitlement").setTextContent(entitlement.getDescription());
        }
        if (appDate != null) {
            final GregorianCalendar date = new GregorianCalendar();
            date.setTime(appDate);
            xmlWriter.appendChild(parent, "appDate").setTextContent(EPPDateFormatter.toXSDate(date));
        }
        if (regDate != null) {
            final GregorianCalendar date = new GregorianCalendar();
            date.setTime(regDate);
            xmlWriter.appendChild(parent, "regDate").setTextContent(EPPDateFormatter.toXSDate(date));
        }
        if (form != null) {
            xmlWriter.appendChild(parent, "form").setTextContent(form.name());
        }
        if (classType != null) {
            xmlWriter.appendChild(parent, "class").setTextContent(classType);
        }
        if (preVerified != null) {
            xmlWriter.appendChild(parent, "preVerified").setTextContent(preVerified);
        }
        if (type != null) {
            xmlWriter.appendChild(parent, "type").setTextContent(type);
        }

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setCcLocality(String ccLocality) {
        this.ccLocality = ccLocality;
    }

    public void setEntitlement(IprEntitlement entitlement) {
        this.entitlement = entitlement;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public void setForm(IprForm form) {
        this.form = form;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setPreVerified(String preVerified) {
        this.preVerified = preVerified;
    }

    public void setType(String type) {
        this.type = type;
    }
}
