package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps/cucumber/silver_screen"},
        features = {"src/test/resources/features/silverScreen/silverScreen.feature"},
        //tags = {"@qa or @prod"},
        monochrome = false,
        snippets = SnippetType.CAMELCASE,
        strict = true)

public class SilverScreenRunner {
}
