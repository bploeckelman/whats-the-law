package org.whatsthelaw.whatsthelaw.docs;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocService {

    private final DiffRowGenerator diffInline;
    private final DiffRowGenerator diffSideBySide;

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

    public String compareInline(int congress, int bill, int version1, int version2) {
        var doc1 = get(congress, bill, version1);
        var doc2 = get(congress, bill, version2);
        return compare(doc1, doc2, diffInline);
    }

    public String compareSideBySide(int congress, int bill, int version1, int version2) {
        var doc1 = get(congress, bill, version1);
        var doc2 = get(congress, bill, version2);
        return compare(doc1, doc2, diffSideBySide);
    }

    private String compare(String doc1, String doc2, DiffRowGenerator diffGenerator) {
        var doc1Lines = doc1.lines().toList();
        var doc2Lines = doc2.lines().toList();
        var diffRows = diffGenerator.generateDiffRows(doc1Lines, doc2Lines);
        var diff = diffRows.stream()
                .map(DiffRow::toString)
                .collect(Collectors.joining(System.lineSeparator()));
        return diff;
    }

    public static class DocNotFoundException extends ResponseStatusException {
        public final String docName;
        public DocNotFoundException(String docName) {
            super(HttpStatus.NOT_FOUND, String.format("Document not found: '%s'", docName));
            this.docName = docName;
        }
    }

}
