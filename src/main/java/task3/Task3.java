package task3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Task3 {
    public static void main(String[] args) {

        Map<String, JsonNode> mapTitle = new HashMap<>();
        Map<String, JsonNode> mapValues = new HashMap<>();
        Map<String, JsonNode> mapValue = new HashMap<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get("C:\\values.json"));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode parser = objectMapper.readTree(reader);

            for (JsonNode pm : parser.path("values")) {
                List<JsonNode> list = new ArrayList<>();
                for (JsonNode el : pm) {
                    list.add(el);
                }

                JsonNode value = null;
                int countValue = 0;
                for (JsonNode el : list) {
                    if (countValue > 0) {
                        value = el;
                    }
                    //Value
                    if (countValue == 1) {
                        mapValue.put(String.valueOf(list.get(0)), value);
                    }
                    countValue++;
                }
            }

            reader.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            Reader reader = Files.newBufferedReader(Paths.get("C:\\tests.json"));

            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode parser = objectMapper.readTree(reader);

            for (JsonNode pm : parser.path("tests")) {
                List<JsonNode> list = new ArrayList<>();

                for (JsonNode el : pm) {
                    list.add(el);
                }

                int count = 0;
                for (JsonNode el : list) {

                    String key = String.valueOf(list.get(0));
                    //Title
                    if (count == 1) {
                        mapTitle.put(key, el);
                    }
                    //Values
                    if (count == 3) {
                        mapValues.put(key, el);
                    }
                    count++;
                }
            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("C:\\report.json"));
            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> report = new LinkedHashMap<>();

            List<Map> list = new ArrayList<>();

            for (String elTitle : mapTitle.keySet()) {
                for (String elValue : mapValue.keySet()) {

                    if (elTitle.equals(elValue)) {

                        if (mapValues.get(elTitle) == null) {

                            Map<String, Object> map = new LinkedHashMap<>();
                            map = new LinkedHashMap<>();
                            map.put("id", elTitle);
                            map.put("title", mapTitle.get(elTitle));
                            map.put("value", mapValue.get(elTitle));
                            list.add(map);
                        }

                        if (mapValues.get(elTitle) != null) {

                            Map<String, Object> map = new LinkedHashMap<>();
                            map = new LinkedHashMap<>();
                            map.put("id", elTitle);
                            map.put("title", mapTitle.get(elTitle));
                            map.put("value", mapValue.get(elTitle));

                            List<Map> list1 = new ArrayList<>();

                            for (JsonNode el : mapValues.get(elTitle)) {

                                int count = 0;
                                JsonNode key = null;
                                Map<String, Object> map1 = new LinkedHashMap<>();
                                for (JsonNode el1 : el) {

                                    if (count == 0) {
                                        key = el1;
                                        map1.put("id", el1);
                                    }
                                    if (count == 1) {
                                        map1.put("title", el1);
                                    }
                                    if (count == 2) {
                                        for (String elValue1 : mapValue.keySet()) {
                                            if (String.valueOf(key).equals(elValue1)) {
                                                map1.put("value", mapValue.get(elValue1));
                                                list1.add(map1);
                                            }
                                        }
                                    }
                                    if (count == 3) {

                                        List<Map> list2 = new ArrayList<>();

                                        for (JsonNode el2 : el1) {
                                            int count1 = 0;

                                            Map<String, Object> map2 = new LinkedHashMap<>();
                                            for (JsonNode el3 : el2) {

                                                if (count1 == 0) {
                                                    map2.put("id", el3);
                                                }
                                                if (count1 == 1) {
                                                    map2.put("title", el3);
                                                }
                                                if (count1 == 2) {
                                                    map2.put("values", el3);
                                                    list2.add(map2);

                                                    List<Map> list3 = new ArrayList<>();
                                                    for (JsonNode el4 : el3) {
                                                        int count2 = 0;
                                                        Map<String, Object> map3 = new LinkedHashMap<>();
                                                        JsonNode key1 = null;

                                                        for (JsonNode el5 : el4) {
                                                            if (count2 == 0) {
                                                                key1 = el5;
                                                                map3.put("id", el5);
                                                            }
                                                            if (count2 == 1) {
                                                                map3.put("title", el5);
                                                            }
                                                            if (count2 == 2) {
                                                                for (String elValue1 : mapValue.keySet()) {
                                                                    if (String.valueOf(key1).equals(elValue1)) {
                                                                        map3.put("value", mapValue.get(elValue1));
                                                                        list3.add(map3);
                                                                    }
                                                                }
                                                            }
                                                            count2++;
                                                        }
                                                    }
                                                    map2.put("values", list3);
                                                }
                                                count1++;
                                            }
                                        }
                                        map1.put("values", list2);
                                    }
                                    count++;
                                }
                                map.put("values", list1);
                            }
                            list.add(map);
                        }
                    }
                }
            }

            mapper = new ObjectMapper();

            report.put("report", list);

            writer.write(mapper.writeValueAsString(report));
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
