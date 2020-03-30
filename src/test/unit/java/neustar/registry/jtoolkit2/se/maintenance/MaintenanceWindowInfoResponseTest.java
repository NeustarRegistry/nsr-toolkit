package neustar.registry.jtoolkit2.se.maintenance;

import static neustar.registry.jtoolkit2.se.maintenance.MaintenanceWindowInfoResponseBuilder.infoResponseBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import neustar.registry.jtoolkit2.xml.ParsingException;
import neustar.registry.jtoolkit2.xml.XMLDocument;
import neustar.registry.jtoolkit2.xml.XMLParser;

public class MaintenanceWindowInfoResponseTest {

    private static final XMLParser PARSER = new XMLParser();

    @Test
    public void shouldReturnIdFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id").build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        assertThat(response.getId(), is("maint-win-id"));
    }

    @Test
    public void shouldReturnSystemFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id").build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        System[] systems = response.getSystems();
        assertThat(systems, is(notNullValue()));
        assertThat(systems.length, is(1));
        System system = systems[0];
        assertThat(system.getName(), is("EPP"));
        assertThat(system.getHost(), is("epp.registry.example"));
        assertThat(system.getImpact(), is(Impact.blackout));
    }

    @Test
    public void shouldReturnEnvironmentFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id").build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        Environment environment = response.getEnvironment();
        assertThat(environment, is(notNullValue()));
        assertThat(environment, is(Environment.production));
    }

    @Test
    public void shouldReturnStartDateFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id")
                                        .withStartDate("2017-09-30T06:00:00Z")
                                        .build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        Date startDate = response.getStartDate();
        assertThat(startDate, is(notNullValue()));
        Calendar instance = getCalendar(Date.UTC(117, 8, 30, 6, 0, 0));
        assertThat(startDate, is(instance.getTime()));
    }

    @Test
    public void shouldReturnEndDateFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id")
                                        .withEndDate("2017-09-30T14:25:57Z")
                                        .build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        Date endDate = response.getEndDate();
        assertThat(endDate, is(notNullValue()));
        Calendar instance = getCalendar(Date.UTC(117, 8, 30, 14, 25, 57));
        assertThat(endDate, is(instance.getTime()));
    }

    @Test
    public void shouldReturnReasonFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id")
                                        .build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        Reason reason = response.getReason();
        assertThat(reason, is(notNullValue()));
        assertThat(reason, is(Reason.planned));
    }

    @Test
    public void shouldReturnDetailFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id")
                                        .build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        String detail = response.getDetail();
        assertThat(detail, is(notNullValue()));
        assertThat(detail, is("https://www.registry.example/notice?123"));
    }

    @Test
    public void shouldReturnDescriptionFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id")
                                        .build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        String description = response.getDescription();
        assertThat(description, is(notNullValue()));
        assertThat(description, is("free text"));
    }

    @Test
    public void shouldReturnTldsFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id").build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        String[] tlds = response.getTlds();
        assertThat(tlds, is(notNullValue()));
        assertThat(tlds.length, is(2));
        assertThat(tlds[0], is("example"));
        assertThat(tlds[1], is("test"));
    }

    @Test
    public void shouldReturnInterventionFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id").build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        Intervention intervention = response.getIntervention();
        assertThat(intervention, is(notNullValue()));
        assertThat(intervention.isImplementation(), is(true));
        assertThat(intervention.isConnection(), is(true));
    }

    @Test
    public void shouldReturnStatusFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id").build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        Status status = response.getStatus();
        assertThat(status, is(notNullValue()));
        assertThat(status, is(Status.active));
    }

    @Test
    public void shouldReturnCreateDateFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id")
                .withCreateDate("2017-02-08T22:10:00Z")
                .build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        Date createDate = response.getCreateDate();
        assertThat(createDate, is(notNullValue()));
        Calendar instance = getCalendar(Date.UTC(117, 1, 8, 22, 10, 0));
        assertThat(createDate, is(instance.getTime()));
    }

    @Test
    public void shouldReturnUpdateDateFromXml() throws ParsingException {
        MaintenanceWindowInfoResponse response = new MaintenanceWindowInfoResponse();
        String xml = infoResponseBuilder("maint-win-id")
                .withUpdateDate("2017-03-08T22:10:00Z")
                .build();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        Date updateDate = response.getUpdateDate();
        assertThat(updateDate, is(notNullValue()));
        Calendar instance = getCalendar(Date.UTC(117, 2, 8, 22, 10, 0));
        assertThat(updateDate, is(instance.getTime()));
    }

    private Calendar getCalendar(long utc) {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        instance.setTimeInMillis(utc);
        return instance;
    }

}
