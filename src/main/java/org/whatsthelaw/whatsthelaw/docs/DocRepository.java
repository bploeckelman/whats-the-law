package org.whatsthelaw.whatsthelaw.docs;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DocRepository extends MongoRepository<Doc, String> {
    List<Doc> findAllByBill(String bill);
    List<Doc> findAllByCongress(String congress);
    List<Doc> findDocByCongressAndBillAndVersion(String congress, String bill, String version);
}
