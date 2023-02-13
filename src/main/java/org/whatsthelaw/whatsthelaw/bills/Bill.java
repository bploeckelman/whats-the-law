package org.whatsthelaw.whatsthelaw.bills;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bill {

    public enum Type { HR, SR }

    public record Action(LocalDate actionDate, String text) {}

    private int congress;
    private Action latestAction;
    private String number;
    private String originChamber;
    private String originChamberCode;
    private String title;
    private String type;
    private LocalDate updateDate;
    private ZonedDateTime updateDateIncludingText;
    private String url;

    // detailed properties ----------------------------------------------------

    public record Amendments(int count, String url) {}
    public record CboCostEstimate(String description, ZonedDateTime pubDate, String title, String url) {}
    public record CommitteeReport(String citation, String url) {}
    public record Committees(int count, String url) {}
    public record Cosponsors(int count, int countIncludingWithdrawnCosponsors, String url) {}
    public record Law(String number, String type) {}
    public record PolicyArea(String name) {}
    public record RelatedBills(int count, String url) {}
    public record Sponsor(String bioguideId, int district, String firstName, String middleName, String lastName, String fullName, String isByRequest, String party, String state, String url) {}
    public record Subjects(int count, String url) {}
    public record Summaries(int count, String url) {}
    public record TextVersions(int count, String url) {}
    public record Titles(int count, String url) {}

    private Amendments amendments;
    private CboCostEstimate[] cboCostEstimates;
    private CommitteeReport[] committeeReports;
    private Committees committees;
    private String constitutionalAuthorityStatementText;
    private Cosponsors cosponsors;
    private LocalDate introductedDate;
    private Law[] laws;
    private PolicyArea policyArea;
    private RelatedBills relatedBills;
    private Sponsor[] sponsors;
    private Subjects subjects;
    private Summaries summaries;
    private TextVersions textVersions;
    private Titles titles;

}
