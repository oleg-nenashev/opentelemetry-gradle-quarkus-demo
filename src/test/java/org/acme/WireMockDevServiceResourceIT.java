package org.acme;

import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.Disabled;

// FIXME: https://github.com/quarkiverse/quarkus-wiremock/issues/106
@QuarkusIntegrationTest
@Disabled
public class WireMockDevServiceResourceIT extends WireMockDevServiceResourceTest {
    // re-use Quarkus tests as integration tests
}

