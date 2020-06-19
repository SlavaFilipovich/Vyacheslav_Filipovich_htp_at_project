package runners.jUnit4;


import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;
import tests.web_service.WebServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({WebServiceTest.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class RunWebServicesJUnit4Suites {
}
