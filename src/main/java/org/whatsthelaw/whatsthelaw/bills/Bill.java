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

}
