package org.whatsthelaw.whatsthelaw.data;

import org.whatsthelaw.whatsthelaw.bills.Bill;
import org.whatsthelaw.whatsthelaw.members.Member;
import org.whatsthelaw.whatsthelaw.members.MemberDetail;

public class ApiResponses {

    public record Pagination(int count, String next) {}
    public record Request(String contentType, String format) {}

    public record BillsResponse(Bill[] bills, Pagination pagination, Request request) {}
    public record BillResponse(Bill bill, Request request) {}

    public record MembersResponse(Member[] members, Pagination pagination, Request request) {}
    public record MemberResponse(MemberDetail member, Pagination pagination, Request request) {}

}
