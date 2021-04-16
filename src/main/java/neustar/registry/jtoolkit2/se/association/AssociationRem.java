package neustar.registry.jtoolkit2.se.association;

import neustar.registry.jtoolkit2.se.AddRemType;

/**
 * Use this to specify attributes to remove from a domain object in a domain
 * update EPP command service element.  The DomainUpdateCommand uses an
 * instance of this to build the appropriate elements in order to request the
 * removal of these attributes from a domain object.
 */
public class AssociationRem extends AssociationAddRem {
    private static final long serialVersionUID = 8320662022260490572L;

    /**
     * Remove associations from a domain.
     *
     * @param contacts A list of contact identifiers to
     * disassociate from a domain. To not remove any contacts,
     * this parameter must be null.
     *
     */
    public AssociationRem(AssociationContact[] contacts) {
        super(AddRemType.REM, contacts);

    }
}
