package neustar.registry.jtoolkit2.se.maintenance;

import static neustar.registry.jtoolkit2.se.maintenance.MaintenanceWindowInfoResponseBuilder.infoResponseBuilder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import neustar.registry.jtoolkit2.xml.ParsingException;
import neustar.registry.jtoolkit2.xml.XMLDocument;
import neustar.registry.jtoolkit2.xml.XMLParser;

public class MaintenanceWindowListInfoResponseTest {

    private static final XMLParser PARSER = new XMLParser();

    @Test
    public void shouldReturnItemFromXml() throws ParsingException {
        MaintenanceWindowListInfoResponse response = new MaintenanceWindowListInfoResponse();
        String xml = infoResponseBuilder("maint-win-id")
                .withStartDate("2017-09-30T06:00:00Z")
                .withEndDate("2017-09-30T14:25:57Z")
                .withCreateDate("2017-02-08T22:10:00Z")
                .withUpdateDate("2017-03-08T22:10:00Z")
                .buildList();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        MaintenanceWindowItem[] items = response.getItems();
        assertThat(items, is(notNullValue()));
        assertThat(items.length, is(1));
        MaintenanceWindowItem item = items[0];
        assertThat(item.getId(), is("maint-win-id"));

        Date startDate = item.getStartDate();
        assertThat(startDate, is(notNullValue()));
        Calendar instance = getCalendar(Date.UTC(117, 8, 30, 6, 0, 0));
        assertThat(startDate, is(instance.getTime()));

        Date endDate = item.getEndDate();
        assertThat(endDate, is(notNullValue()));
        instance = getCalendar(Date.UTC(117, 8, 30, 14, 25, 57));
        assertThat(endDate, is(instance.getTime()));

        Date createDate = item.getCreateDate();
        assertThat(createDate, is(notNullValue()));
        instance = getCalendar(Date.UTC(117, 1, 8, 22, 10, 0));
        assertThat(createDate, is(instance.getTime()));

        Date updateDate = item.getUpdateDate();
        assertThat(updateDate, is(notNullValue()));
        instance = getCalendar(Date.UTC(117, 2, 8, 22, 10, 0));
        assertThat(updateDate, is(instance.getTime()));
    }

    @Test
    public void shouldReturnItemWithNoUpdateDateFromXml() throws ParsingException {
        MaintenanceWindowListInfoResponse response = new MaintenanceWindowListInfoResponse();
        String xml = infoResponseBuilder("maint-win-id")
                .withStartDate("2017-09-30T06:00:00Z")
                .withEndDate("2017-09-30T14:25:57Z")
                .withCreateDate("2017-02-08T22:10:00Z")
                .buildList();
        XMLDocument doc = PARSER.parse(xml);
        response.fromXML(doc);
        MaintenanceWindowItem[] items = response.getItems();
        assertThat(items, is(notNullValue()));
        assertThat(items.length, is(1));
        MaintenanceWindowItem item = items[0];
        assertThat(item.getId(), is("maint-win-id"));

        Date startDate = item.getStartDate();
        assertThat(startDate, is(notNullValue()));
        Calendar instance = getCalendar(Date.UTC(117, 8, 30, 6, 0, 0));
        assertThat(startDate, is(instance.getTime()));

        Date endDate = item.getEndDate();
        assertThat(endDate, is(notNullValue()));
        instance = getCalendar(Date.UTC(117, 8, 30, 14, 25, 57));
        assertThat(endDate, is(instance.getTime()));

        Date createDate = item.getCreateDate();
        assertThat(createDate, is(notNullValue()));
        instance = getCalendar(Date.UTC(117, 1, 8, 22, 10, 0));
        assertThat(createDate, is(instance.getTime()));

        Date updateDate = item.getUpdateDate();
        assertThat(updateDate, is(nullValue()));
    }

    private Calendar getCalendar(long utc) {
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        instance.setTimeInMillis(utc);
        return instance;
    }

}
