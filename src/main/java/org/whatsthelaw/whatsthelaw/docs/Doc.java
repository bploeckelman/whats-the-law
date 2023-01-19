package org.whatsthelaw.whatsthelaw.docs;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Doc {
    @Id
    private String id;
    private String congress;
    private String bill;
    private String version;
    private String body;

    public void set(int congress, int bill, int version, String body) {
        this.congress = String.valueOf(congress);
        this.bill = String.valueOf(bill);
        this.version = String.valueOf(version);
        this.body = body;
    }
}
