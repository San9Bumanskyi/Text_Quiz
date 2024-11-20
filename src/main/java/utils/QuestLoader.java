package utils;

import model.Quest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestLoader {
    public static Map<String, List<Quest>> loadAllQuests() {
        Map<String, List<Quest>> allQuests = new HashMap<>();

        List<Quest> adventureQuest = new ArrayList<>();
        adventureQuest.add(new Quest("adventure_1", "You stand before two doors. To the left, a whisper; to the right, silence. Where will you go?",
                new String[]{"To the left", "To the right"}, 0, "You chose the correct door!"));
        adventureQuest.add(new Quest("adventure_2", "A skeleton stands before you. Do you attack or run?",
                new String[]{"Attack", "Run"}, 0, "You defeated the skeleton and found a treasure!"));
        allQuests.put("adventure", adventureQuest);

        List<Quest> dungeonQuest = new ArrayList<>();
        dungeonQuest.add(new Quest("dungeon_1", "You find yourself in a dark dungeon. Will you move forward?",
                new String[]{"Forward", "Backward"}, 0, "You proceed further into the dungeon."));
        dungeonQuest.add(new Quest("dungeon_2", "An old mage appears before you. Will you talk or attack?",
                new String[]{"Talk", "Attack"}, 0, "The mage taught you a powerful spell!"));
        allQuests.put("dungeon", dungeonQuest);


        return allQuests;
    }
}
