package org.acme;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.Disabled;

// FIXME: https://github.com/quarkiverse/quarkus-wiremock/issues/106
@QuarkusIntegrationTest
@Disabled
public class GreetingResourceIT extends GreetingResourceTest {
    // Execute the same tests but in packaged mode.
}
