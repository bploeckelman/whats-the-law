package org.whatsthelaw.whatsthelaw.docs;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/docs")
@RequiredArgsConstructor
public class DocController {

    private final DocService docs;

    @GetMapping("/")
    public String getDoc(@RequestParam int congress, @RequestParam int bill) {
            var version = 0;
            return docs.get(congress, bill, version);
    }

}
