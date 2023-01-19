package org.whatsthelaw.whatsthelaw.docs;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping({"/compare", "/compare/inline"})
    public String compareDocs(@RequestParam int congress, @RequestParam int bill, @RequestParam int v1, @RequestParam int v2) {
        return docs.compareInline(congress, bill, v1, v2);
    }

    @GetMapping("/compare/sidebyside")
    public String compareDocsSideBySide(@RequestParam int congress, @RequestParam int bill, @RequestParam int v1, @RequestParam int v2) {
        return docs.compareSideBySide(congress, bill, v1, v2);
    }

    @PostMapping("/")
    public String saveDoc(@RequestParam int congress, @RequestParam int bill, @RequestParam int version) {
        var doc = docs.put(congress, bill, version);
        return doc.toString();
    }

}
