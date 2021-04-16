package neustar.registry.jtoolkit2.se.association;

import neustar.registry.jtoolkit2.se.AddRemType;

/**
 * Use this to specify attributes to add to a domain object in a domain update
 * EPP command service element.  The DomainUpdateCommand uses an instance of
 * this to build the appropriate elements in order to request the addition of
 * these attributes to a domain object.
 */
public class AssociationAdd extends AssociationAddRem {
    private static final long serialVersionUID = -1134547077407469383L;

    /**
     * Add associations to a domain.
     *
     * @param contacts A list of contact identifiers to associate
     * with a domain.  To not add any contacts, this parameter must
     * be null.
     *
     */
    public AssociationAdd(AssociationContact[] contacts) {
        super(AddRemType.ADD, contacts);
    }

}

