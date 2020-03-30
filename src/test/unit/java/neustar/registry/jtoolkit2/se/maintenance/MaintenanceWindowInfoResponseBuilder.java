package neustar.registry.jtoolkit2.se.maintenance;

public final class MaintenanceWindowInfoResponseBuilder {

    private final String id;

    private String startDate;
    private String endDate;
    private String createDate;
    private String updateDate;

    private MaintenanceWindowInfoResponseBuilder(String id) {
        this.id = id;
    }

    public static MaintenanceWindowInfoResponseBuilder infoResponseBuilder(String id) {
        return new MaintenanceWindowInfoResponseBuilder(id);
    }

    public String build() {
        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"")
                .append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"")
                .append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">")
                .append("<response>")
                .append("<result code=\"1000\">")
                .append("<msg>Command completed successfully</msg>")
                .append("</result>")
                .append(buildResponse())
                .append("<trID>")
                .append("<clTRID>ABC-12345</clTRID>")
                .append("<svTRID>54321-XYZ</svTRID>")
                .append("</trID>")
                .append("</response>")
                .append("</epp>");
        return result.toString();
    }

    public String buildList() {
        final StringBuilder result = new StringBuilder();
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<epp xmlns=\"urn:ietf:params:xml:ns:epp-1.0\"")
                .append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"")
                .append(" xsi:schemaLocation=\"urn:ietf:params:xml:ns:epp-1.0 epp-1.0.xsd\">")
                .append("<response>")
                .append("<result code=\"1000\">")
                .append("<msg>Command completed successfully</msg>")
                .append("</result>")
                .append(buildListResponse())
                .append("<trID>")
                .append("<clTRID>ABC-12345</clTRID>")
                .append("<svTRID>54321-XYZ</svTRID>")
                .append("</trID>")
                .append("</response>")
                .append("</epp>");
        return result.toString();
    }

    public String buildResponse() {
        if (startDate == null) {
            startDate = "2017-09-30T06:00:00Z";
        }
        if (endDate == null) {
            endDate = "2017-09-30T06:00:00Z";
        }
        if (createDate == null) {
            createDate = "2017-03-08T22:10:00Z";
        }
        final StringBuilder result = new StringBuilder();
        result.append("<resData>")
              .append("<maint:infData xmlns:maint=\"urn:ietf:params:xml:ns:maintenance-0.2\">")
              .append("<maint:maint>")
              .append("<maint:id>" + id + "</maint:id>")
              .append("<maint:systems>")
              .append("<maint:system>")
              .append("<maint:name>EPP</maint:name>")
              .append("<maint:host>epp.registry.example</maint:host>")
              .append("<maint:impact>blackout</maint:impact>")
              .append("</maint:system>")
              .append("</maint:systems>")
              .append("<maint:environment type=\"production\"/>")
              .append("<maint:start>" + startDate + "</maint:start>")
              .append("<maint:end>" + endDate + "</maint:end>")
              .append("<maint:reason>planned</maint:reason>")
              .append("<maint:detail>")
              .append("https://www.registry.example/notice?123")
              .append("</maint:detail>")
              .append("<maint:description>free text</maint:description>")
              .append("<maint:tlds>")
              .append("<maint:tld>example</maint:tld>")
              .append("<maint:tld>test</maint:tld>")
              .append("</maint:tlds>")
              .append("<maint:intervention>")
              .append("<maint:connection>true</maint:connection>")
              .append("<maint:implementation>true</maint:implementation>")
              .append("</maint:intervention>")
              .append("<maint:status>active</maint:status>")
              .append("<maint:crDate>" + createDate + "</maint:crDate>");
        if (updateDate != null) {
            result.append("<maint:upDate>" + updateDate + "</maint:upDate>");
        }
        result.append("</maint:maint>")
              .append("</maint:infData>")
              .append("</resData>");
        return result.toString();
    }
    public String buildListResponse() {
        if (startDate == null) {
            startDate = "2017-09-30T06:00:00Z";
        }
        if (endDate == null) {
            endDate = "2017-09-30T06:00:00Z";
        }
        if (createDate == null) {
            createDate = "2017-03-08T22:10:00Z";
        }
        final StringBuilder result = new StringBuilder();
        result.append("<resData>")
              .append("<maint:infData xmlns:maint=\"urn:ietf:params:xml:ns:maintenance-0.2\">")
              .append("<maint:list>")
              .append("<maint:maint>")
              .append("<maint:id>" + id + "</maint:id>")
              .append("<maint:start>" + startDate + "</maint:start>")
              .append("<maint:end>" + endDate + "</maint:end>")
              .append("<maint:crDate>" + createDate + "</maint:crDate>");
        if (updateDate != null) {
            result.append("<maint:upDate>" + updateDate + "</maint:upDate>");
        }
        result.append("</maint:maint>")
              .append("</maint:list>")
              .append("</maint:infData>")
              .append("</resData>");
        return result.toString();
    }

    public MaintenanceWindowInfoResponseBuilder withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public MaintenanceWindowInfoResponseBuilder withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public MaintenanceWindowInfoResponseBuilder withCreateDate(String createDate) {
        this.createDate = createDate;
        return this;
    }

    public MaintenanceWindowInfoResponseBuilder withUpdateDate(String updateDate) {
        this.updateDate = updateDate;
        return this;
    }
}

