package neustar.registry.jtoolkit2.se.eps;

public class EpsExemption {

    private String iprId;
    private String[] labels;

    public EpsExemption(String iprId) {
        this.iprId = iprId;
    }

    public String getIprId() {
        return iprId;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }
}
