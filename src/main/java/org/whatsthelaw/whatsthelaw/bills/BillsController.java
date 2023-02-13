package org.whatsthelaw.whatsthelaw.bills;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.whatsthelaw.whatsthelaw.config.AppProperties;
import org.whatsthelaw.whatsthelaw.data.ApiResponses;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillsController {

    private final RestTemplate http;
    private final AppProperties app;

    @GetMapping
    @Cacheable("bills")
    public ResponseEntity<Bill[]> bills(@RequestParam(required = false) Integer congress,
                                        @RequestParam(required = false) Bill.Type type,
                                        @RequestParam(required = false) Integer number) {
        var root = app.getCongressApi().rootUri();
        var path = "/bill";
        var urlTemplate = UriComponentsBuilder.fromHttpUrl(root + path);
        if (congress != null) {
            urlTemplate.pathSegment(String.valueOf(congress));
            if (type != null) {
                urlTemplate.pathSegment(type.name());
                if (number != null) {
                    urlTemplate.pathSegment(String.valueOf(number));
                }
            }
        }
        var url = urlTemplate.encode().toUriString();

        var response = (number == null)
                ? http.exchange(url, HttpMethod.GET, null, ApiResponses.BillsResponse.class)
                : http.exchange(url, HttpMethod.GET, null, ApiResponses.BillResponse.class)
                ;
        var responseBody = response.getBody();
        if (responseBody == null) {
            return ResponseEntity.internalServerError().build();
        } else if (responseBody instanceof ApiResponses.BillsResponse body) {
            return ResponseEntity.ok(body.bills());
        } else if (responseBody instanceof ApiResponses.BillResponse body) {
            return ResponseEntity.ok(new Bill[] {body.bill()});
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
