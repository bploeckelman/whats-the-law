package org.whatsthelaw.whatsthelaw.members;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberDetail {

    private Boolean currentMember;

    private Identifiers identifiers;
    private String officialWebsiteUrl;

    private String honorificName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffixName;
    private String nickName;
    private String directOrderName;
    private String invertedOrderName;

    private String state;
    private String district;

    private Depiction depiction;
    private Address addressInformation;
    private String birthYear;
    private String deathYear;
    private String party;
    private PartyHistory[] partyHistory;

    private Term[] terms;
    private Leadership[] leadership;
    private SponsoredLegislation sponsoredLegislation;
    private CosponsoredLegislation cosponsoredLegislation;

    private ZonedDateTime updateDate;

    public record Identifiers(String bioguideId) {}
    public record Telephone(String phoneNumber) {}
    public record Address(String city, String district, Integer zipCode, String officeAddress, Telephone officeTelephone) {}
    public record Depiction(String attribution, String imageUrl) {}
    public record SponsoredLegislation(Integer count, String url) {}
    public record CosponsoredLegislation(Integer count, String url) {}
    public record Leadership(Integer congress, Boolean current, String type) {}
    public record PartyHistory(Integer startYear, Integer endYear, String partyCode, String partyName) {}
    public record Term(Integer congress, String chamber, String memberType, String stateCode, String stateName, Integer termBeginYear, Integer termEndYear) {}

}
