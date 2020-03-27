package se.iuh.e2portal.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/announcement")
public class AnnouncementRESTController {
    @RequestMapping("/")
    public String getAllAnnouncement(){
        return "";
    }

}
