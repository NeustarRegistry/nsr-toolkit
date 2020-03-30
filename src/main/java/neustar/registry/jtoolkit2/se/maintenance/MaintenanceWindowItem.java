package neustar.registry.jtoolkit2.se.maintenance;

import java.io.Serializable;
import java.util.Date;

public class MaintenanceWindowItem implements Serializable {

    private static final long serialVersionUID = -1028941476970430162L;

    private final String id;
    private final Date startDate;
    private final Date endDate;
    private final Date createDate;
    private final Date updateDate;

    public MaintenanceWindowItem(String id, Date startDate, Date endDate, Date createDate, Date updateDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }
}
