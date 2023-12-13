package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import subway.exception.ErrorMessage;
import subway.exception.SubwayException;

public class SectionRepository {

    private static final List<Section> sections = new ArrayList<>();

    public static List<Section> sections() {
        return Collections.unmodifiableList(sections);
    }

    public static void addSection(Section section) {
        sections.add(section);
    }

    public static Section findSection(final Station leftStation, final Station rightStation) {
        return sections.stream()
                .filter(section -> section.hasStation(leftStation, rightStation))
                .findAny()
                .orElseThrow(() -> new SubwayException(ErrorMessage.INVALID_SECTION));
    }
}
