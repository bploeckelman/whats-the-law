package org.whatsthelaw.whatsthelaw.docs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class DocService {

    public static class DocNotFoundException extends ResponseStatusException {
        public final String docName;
        public DocNotFoundException(String docName) {
            super(HttpStatus.NOT_FOUND, String.format("Document not found: '%s'", docName));
            this.docName = docName;
        }
    }

    public String get(int congress, int bill, int version) throws DocNotFoundException {
        var cwd = Paths.get("").toAbsolutePath().toString();
        log.debug("cwd: '{}'", cwd);

        var dir = Paths.get("docs");
        var docName = String.format("congress-%d-hr-%d-%d", congress, bill, version);
        try (var list = Files.list(dir)) {
            var optFilename = list
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(file -> file.contains(docName))
                    .findFirst();

            if (optFilename.isPresent()) {
                var filename = optFilename.get();
                log.info("Found document: '{}'", filename);
                var path = Path.of(dir.toString(), filename);
                var body = Files.readString(path);
                return body;
            } else {
                var fullDocName = String.format("%s/%s*", dir, docName);
                throw new DocNotFoundException(fullDocName);
            }
        } catch (IOException ex) {
            throw new DocNotFoundException(docName);
        }
    }


}
