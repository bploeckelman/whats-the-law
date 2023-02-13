package org.whatsthelaw.whatsthelaw.bills;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.whatsthelaw.whatsthelaw.data.ApiResponses;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillsController {

    private final RestTemplate http;

    @GetMapping
    @Cacheable("bills")
    public ResponseEntity<Bill[]> basePath() {
        var responseBody = http.getForObject("/bill", ApiResponses.BillResponse.class);
        if (responseBody == null) {
            return ResponseEntity.internalServerError().build();
        }
        var bills = responseBody.bills();
        return ResponseEntity.ok(bills);
    }

}
