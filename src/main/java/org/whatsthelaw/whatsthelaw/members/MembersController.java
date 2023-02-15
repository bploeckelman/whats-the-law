package org.whatsthelaw.whatsthelaw.members;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.whatsthelaw.whatsthelaw.config.AppProperties;
import org.whatsthelaw.whatsthelaw.data.ApiResponses;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MembersController {

    private final RestTemplate http;
    private final AppProperties props;

    @GetMapping
    @Cacheable("members")
    public ResponseEntity<Member[]> members() {
        var root = props.getCongressApi().rootUri();
        var path = "/member";
        var urlTemplate = UriComponentsBuilder.fromHttpUrl(root + path);
        var url = urlTemplate.encode().toUriString();
        var response = http.exchange(url, HttpMethod.GET, null, ApiResponses.MembersResponse.class);
        var responseBody = response.getBody();
        if (responseBody == null) {
            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.ok(responseBody.members());
        }
    }

    @GetMapping("/{bioguideId}")
    @Cacheable("member")
    public ResponseEntity<MemberDetail> member(@PathVariable String bioguideId) {
        var root = props.getCongressApi().rootUri();
        var path = "/member";
        var urlTemplate = UriComponentsBuilder.fromHttpUrl(root + path).pathSegment(bioguideId);
        var url = urlTemplate.encode().toUriString();
        var response = http.exchange(url, HttpMethod.GET, null, ApiResponses.MemberResponse.class);
        var responseBody = response.getBody();
        if (responseBody == null) {
            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.ok(responseBody.member());
        }
    }

}
