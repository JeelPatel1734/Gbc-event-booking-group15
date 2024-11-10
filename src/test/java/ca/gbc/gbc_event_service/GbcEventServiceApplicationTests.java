package ca.gbc.gbc_event_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class GbcEventServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
