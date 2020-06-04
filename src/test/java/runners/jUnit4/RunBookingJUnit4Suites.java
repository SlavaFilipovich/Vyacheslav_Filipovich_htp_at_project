package runners.jUnit4;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import tests.booking.BookingHotelsTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingHotelsTest.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RunBookingJUnit4Suites {

}
