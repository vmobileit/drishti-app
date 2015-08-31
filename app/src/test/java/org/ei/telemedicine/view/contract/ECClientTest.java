package org.ei.telemedicine.view.contract;

import org.ei.telemedicine.util.DateUtil;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class ECClientTest {

    @Test
    public void shouldReturnTrueForIsBPLWhenEconomicStatusIsBPL() throws Exception {
        ECClient client = getClient().withEconomicStatus("BPL");

        assertTrue(client.isBPL());
    }

    @Test
    public void shouldReturnFalseForIsBPLWhenEconomicStatusIsAPLOrNothing() throws Exception {
        ECClient client = getClient().withEconomicStatus("APL");
        assertFalse(client.isBPL());

        client = getClient();
        assertFalse(client.isBPL());
    }

    @Test
    public void shouldReturnTrueWhenClientContainsCasteAsSC() throws Exception {
        ECClient SCClient = getClient().withCaste("SC");

        assertTrue(SCClient.isSC());
        assertFalse(SCClient.isST());
    }

    @Test
    public void shouldReturnTrueWhenClientContainsCasteAsST() throws Exception {
        ECClient STClient = getClient().withCaste("ST");

        assertFalse(STClient.isSC());
        assertTrue(STClient.isST());
    }

    @Test
    public void shouldReturn34YearsWhenDOBIs4_4_1980() {
        DateUtil.fakeIt(LocalDate.parse("2014-04-18"));

        final int age = getClient().withDateOfBirth(new LocalDate(1980, 4, 4).toString()).age();

        assertEquals(34, age);
    }

    @Test
    public void shouldReturn0YearsWhenDOBIs4_4_2014() {
        DateUtil.fakeIt(LocalDate.parse("2014-04-18"));

        int age = getClient().withDateOfBirth(new LocalDate(2014, 4, 4).toString()).age();

        assertEquals(0, age);
    }

    @Test
    public void ShouldReturn1YearsWhenDOBIs18_4_2013() {
        DateUtil.fakeIt(LocalDate.parse("2014-04-18"));

        final int age = getClient().withDateOfBirth(new LocalDate(2013, 4, 18).toString()).age();

        assertEquals(1, age);
    }

    @Test
    public void ShouldReturnUpperCaseIUDPerson() {
        String iudPerson = getClient().withIUDPerson("iudperson").iudPerson();

        assertEquals(iudPerson, "IUDPERSON");
    }

    @Test
    public void ShouldReturnUpperCaseIUDPlace() {
        String iudPerson = getClient().withIUDPerson("iudplace").iudPerson();

        assertEquals(iudPerson, "IUDPLACE");
    }

    @Test
    public void shouldSatisfyFilterForNameStartingWithSameCharacters() {
        boolean filterMatches = getClient().satisfiesFilter("Dr");

        assertTrue(filterMatches);
    }

    @Test
    public void shouldSatisfyFilterForECNumberStartingWithSameCharacters() {
        boolean filterMatches = getClient().satisfiesFilter("12");

        assertTrue(filterMatches);
    }

    @Test
    public void shouldNotSatisfyFilterForNameNotStartingWithSameCharacters() {
        boolean filterMatches = getClient().satisfiesFilter("shti");

        assertFalse(filterMatches);
    }

    @Test
    public void shouldSatisfyFilterForBlankName() {
        boolean filterMatches = getClient().satisfiesFilter("");

        assertTrue(filterMatches);
    }

    @Test
    public void shouldNotSatisfyFilterForECNumberNotStartingWithSameCharacters() {
        boolean filterMatches = getClient().satisfiesFilter("23");

        assertFalse(filterMatches);
    }

    @Test
    public void shouldSatisfyFilterForBlankECNumber() {
        boolean filterMatches = getClient().satisfiesFilter("");

        assertTrue(filterMatches);
    }

    private ECClient getClient() {
        return new ECClient("abcd", "Drishti", "husband1", "village1", 123);
    }
}
