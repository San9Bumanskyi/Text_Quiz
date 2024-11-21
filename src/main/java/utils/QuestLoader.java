package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Quest;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestLoader {

    public static Map<String, List<Quest>> loadAllQuests() {
        Map<String, List<Quest>> allQuests = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream = QuestLoader.class.getClassLoader().getResourceAsStream("quests.json")) {
            if (inputStream == null) {
                throw new RuntimeException("quests.json not found in resources!");
            }
            allQuests = mapper.readValue(inputStream, new TypeReference<Map<String, List<Quest>>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load quests from quests.json", e);
        }

        return allQuests;
    }
}
