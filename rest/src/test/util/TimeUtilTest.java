package test.util;

import com.spothero.pricingengine.models.Day;
import com.spothero.pricingengine.util.TimeUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TimeUtilTest {

    @Test
    public void testParseDays_testEmptyString() {
        List<Day> dayList = TimeUtil.parseDays("");
        Assert.assertTrue(dayList.isEmpty());
    }

    @Test
    public void testParseDays_testValidString() {
        List<Day> expectedDays = List.of(Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY);
        List<Day> actualDays = TimeUtil.parseDays("mon,tues,wed,thurs,fri,sat,sun");
        Assert.assertEquals(expectedDays, actualDays);
    }

    @Test
    public void testParseDays_testValidString2() {
        List<Day> expectedDays = List.of(Day.MONDAY, Day.TUESDAY, Day.THURSDAY);
        List<Day> actualDays = TimeUtil.parseDays("mon,tues,thurs");
        Assert.assertEquals(expectedDays, actualDays);
    }

    @Test
    public void testParseDays_testInvalidString() {
        List<Day> actualDays = TimeUtil.parseDays("mon, zzdms");
        List<Day> expectedDays = List.of(Day.MONDAY, Day.INVALID);
        Assert.assertEquals(expectedDays, actualDays);
    }

    @Test
    public void testParseDays_testInvalidString2() {
        List<Day> actualDays = TimeUtil.parseDays("xyz");
        List<Day> expectedDays = List.of(Day.INVALID);
        Assert.assertEquals(expectedDays, actualDays);
    }
}
