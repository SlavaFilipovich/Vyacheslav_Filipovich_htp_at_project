package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps/cucumber"},
        features = {"src/test/resources/features/booking_features/BookingHotelsTest.feature"},
        //tags = {"@qa or @prod"},
        monochrome = false,
        snippets = SnippetType.CAMELCASE,
        strict = true)

public class HotelsTestRunner {
}
