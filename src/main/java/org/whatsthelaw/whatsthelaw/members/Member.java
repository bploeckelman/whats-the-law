package org.whatsthelaw.whatsthelaw.members;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member {

    private String bioguideId;
    private String url;
    private MemberDetail.Depiction depiction;

    private String name;
    private String party;
    private String state;
    private String district;

    // TODO - is key enumerable? seen 'House' and 'Senate' so far...
    //  are there other things like cabinet positions allowed here?
    private Map<String, Years[]> served;

    public record Years(Integer start, Integer end) {}

}
