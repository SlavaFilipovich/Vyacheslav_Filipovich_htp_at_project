package runners.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"steps"},
        features = {"src/test/resources/features/ws_features/LookForUsersByName.feature"},
        monochrome = false,
        snippets = SnippetType.CAMELCASE,
        strict = true)

public class WSFindUsersRunner {

}
