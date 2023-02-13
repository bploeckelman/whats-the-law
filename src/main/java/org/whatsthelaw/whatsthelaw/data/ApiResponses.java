package org.whatsthelaw.whatsthelaw.data;

import org.whatsthelaw.whatsthelaw.bills.Bill;

public class ApiResponses {

    public record Pagination(int count, String next) {}
    public record Request(String contentType, String format) {}

    public record BillResponse(
            Bill[] bills,
            Pagination pagination,
            Request request
    ) {};

}
