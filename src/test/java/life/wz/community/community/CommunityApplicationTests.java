package life.wz.community.community;

import life.wz.community.community.Service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    NotificationService notificationService;

    @Test
    void contextLoads() {
        System.out.println(notificationService);
    }

}
