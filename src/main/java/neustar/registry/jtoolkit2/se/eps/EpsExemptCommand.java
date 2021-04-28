package neustar.registry.jtoolkit2.se.eps;

import static neustar.registry.jtoolkit2.se.ExtendedObjectType.EPS;

import org.w3c.dom.Element;

import neustar.registry.jtoolkit2.ErrorPkg;
import neustar.registry.jtoolkit2.se.Command;
import neustar.registry.jtoolkit2.se.ObjectType;
import neustar.registry.jtoolkit2.se.StandardCommandType;

/**
 * A EpsExemptCommand is used to locate the IPR Number and all the labels
 * that have valid IPR details for the requested label
 *
 * @see EpsExemptResponse
 */
public class EpsExemptCommand extends Command {
    private static final long serialVersionUID = 3050267498759687925L;
    private static final String SE_OBJECT_MISSING_ARG = "se.object.missing_arg";

    /**
     * Check the availability of the single identified block.
     *
     * @param name The name of the label to check the availability of.
     */
    public EpsExemptCommand(String name) {
        super(StandardCommandType.CHECK);

        if (name == null) {
            throw new IllegalArgumentException(ErrorPkg.getMessage(SE_OBJECT_MISSING_ARG));
        }

        xmlWriter.appendChild(addCommandDefinition(), EPS.getIdentType()).setTextContent(name);
    }

    /**
     * Check the availability of at least one block.
     *
     * @param names The names of the labels to check the availability of.
     */
    public EpsExemptCommand(String[] names) {
        super(StandardCommandType.CHECK);

        if (names == null || names.length == 0) {
            throw new IllegalArgumentException(ErrorPkg.getMessage(SE_OBJECT_MISSING_ARG));
        }

        xmlWriter.appendChildren(addCommandDefinition(), EPS.getIdentType(), names);
    }

    public ObjectType getObjectType() {
        return EPS;
    }

    private Element addCommandDefinition() {
        Element objElement = xmlWriter.appendChild(cmdElement, "exempt", EPS.getURI());
        objElement.setAttribute("xsi:schemaLocation", EPS.getSchemaLocation());

        return objElement;
    }

}

