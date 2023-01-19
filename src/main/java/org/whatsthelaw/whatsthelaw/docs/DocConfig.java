package org.whatsthelaw.whatsthelaw.docs;

import com.github.difflib.text.DiffRowGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocConfig {

    @Bean(name = "diffInline")
    public DiffRowGenerator diffGeneratorInline() {
        return DiffRowGenerator.create()
                .showInlineDiffs(true)
                .mergeOriginalRevised(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")  // markdown strikethrough
                .newTag(f -> "**") // markdown bold
                .build();
    }

    @Bean(name = "diffSideBySide")
    public DiffRowGenerator diffGeneratorSideBySide() {
        return DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")  // markdown strikethrough
                .newTag(f -> "**") // markdown bold
                .build();
    }

}
