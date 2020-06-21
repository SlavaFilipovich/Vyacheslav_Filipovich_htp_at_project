package at_classes.fake.fake;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"at_classes.fake"},
        features = {"src/test/resources/features/fake/FakeOne.feature"})

public class FakeRunner {
}
