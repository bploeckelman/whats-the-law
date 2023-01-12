package org.whatsthelaw.whatsthelaw.docs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class DocService {

    public String get(int congress, int bill, int version) throws IOException, FileNotFoundException {
        var cwd = Paths.get("").toAbsolutePath().toString();
        log.debug("cwd: '{}'", cwd);

        var dir = Paths.get("docs");
        var prefix = String.format("congress-%d-hr-%d-%d", congress, bill, version);
        try (var list = Files.list(dir)) {
            var optFilename = list
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(file -> file.contains(prefix))
                    .findFirst();
            if (optFilename.isPresent()) {
                var filename = optFilename.get();
                log.info("Found document: '{}'", filename);
                var path = Path.of(dir.toString(), filename);
                var body = Files.readString(path);
                return body;
            } else {
                var error = String.format("Document not found: '%s/%s*'", dir, prefix);
                log.error(error);
                throw new FileNotFoundException(error);
            }
        }
    }


}
