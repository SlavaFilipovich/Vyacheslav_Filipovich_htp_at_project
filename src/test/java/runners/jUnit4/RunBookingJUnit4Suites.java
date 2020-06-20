package runners.jUnit4;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import tests.booking.AdditionToTripListTest;
import tests.booking.BookingHotelsTest;
import tests.booking.HeaderElementsTest;
import tests.booking.RegistrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({BookingHotelsTest.class,
                    AdditionToTripListTest.class,
                    HeaderElementsTest.class,
                    RegistrationTest.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class RunBookingJUnit4Suites {

}
