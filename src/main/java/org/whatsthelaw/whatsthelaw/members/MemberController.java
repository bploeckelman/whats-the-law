package org.whatsthelaw.whatsthelaw.members;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @GetMapping
    public String basePath() {
        return "members";
    }

}
