package neustar.registry.jtoolkit2.se.mzb;

public class MzbExemption {

    private String iprId;
    private String[] labels;

    public MzbExemption(String iprId) {
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
