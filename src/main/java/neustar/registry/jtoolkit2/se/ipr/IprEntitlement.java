package neustar.registry.jtoolkit2.se.ipr;

public enum IprEntitlement {

    owner("owner"),
    coOwner("co-owner"),
    assignee("assignee");

    private final String description;

    IprEntitlement(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
