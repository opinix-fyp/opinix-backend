package com.opinix.backend.service;

import com.opinix.backend.model.*;
import com.opinix.backend.repository.PollRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Service
public class PollImportService {
    private final PollRepository pollRepository; //currently we dont have a pollrepository, dont worry about it

    public PollImportService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll importFromCSV(String title, String source, InputStream csvInputStream) throws IOException{
        //using apache commons csv to parse the csv file
        //parse the csv file
        try (Reader reader = new InputStreamReader(csvInputStream)) {
            CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

            Map<String, Integer> headerMap = csvParser.getHeaderMap(); //columnName -> index

            Poll poll = new Poll();
            poll.setTitle(title);
            poll.setSource(source);

            //build questions from header row
            List<PollQuestion> questions = buildQuestions(headerMap);
            questions.forEach(poll::addQuestion); //add questions to poll

            //build responses (rows)
            int rowIndex = 0;
            for (CSVRecord record : csvParser){
                PollResponse response = new PollResponse();
                response.setRowIndex(rowIndex++);

                for (PollQuestion q : questions){
                    String raw = record.get(q.getColumnIndex());
                    PollAnswer a = new PollAnswer();

                    a.setQuestion(q);
                    a.setRawValue(raw);
                    response.addAnswer(a);
                }
                poll.addResponse(response);
            }
            return pollRepository.save(poll);
        }
    }
    private List<PollQuestion> buildQuestions(Map<String, Integer> headerMap){
        List<PollQuestion> questions = new ArrayList<>();

        headerMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()) //sort by index
                .forEach(entry -> {
                    String header = entry.getKey();
                    int index = entry.getValue();

                    PollQuestion q = new PollQuestion();
                    q.setColumnIndex(index);
                    q.setOriginalHeader(header);
                    q.setLabel(header); //for now, label is same as header, can be cleaned later

                    q.setType(detectType(header)); //default to text, can be changed later
                    q.setRole(detectRole(header, q.getType())); //default to other, can be changed later

                    questions.add(q);
                });
        return questions;
    }

    //detectType and detectRole heuristics.
    //these are designed to be simple right now, because ain't no way in hell we gonna be able to accurately detect EVERY SINGLE question types and roles just from the header name.
    //so these will just cover some, and the rest can be added on later #love
    private QuestionType detectType(String header){
        String h = header.toLowerCase();

        if (h.contains("time")){
            return QuestionType.TIMESTAMP;
        }
        if (h.contains("email")){
            return QuestionType.EMAIL;
        }
        if (h.contains("age") || h.contains("number") || h.contains("count")){
            return QuestionType.NUMBER;
        }
        if (h.contains("rating") || h.contains("score") || h.contains("rank")){
            return QuestionType.RATING;
        }
        if (h.contains("choice") || h.contains("option") || h.contains("select") || h.contains("pick") || h.contains("choose")){
            return QuestionType.CHOICE;
        }
        else {
            return QuestionType.TEXT;
        }
    }

    private QuestionRole detectRole(String header, QuestionType type){
        String h = header.toLowerCase();

        if(type == QuestionType.TIMESTAMP || h.contains("name") || h.contains("id") || h.contains("email") || h.contains("phone")){ //these are clear metadata examples, but theres gonna be more...
            return QuestionRole.METADATA;
        }

        if (h.contains("consent") || h.contains("agree") || h.contains("updates") || h.contains("newsletter")) {
            return QuestionRole.IGNORE;
        }

        if (type == QuestionType.RATING || type == QuestionType.CHOICE || type == QuestionType.TEXT){
            return QuestionRole.FEEDBACK;
        } //i know this is redundant, but we just keep it just in case...

        return QuestionRole.FEEDBACK; //default to feedback, because we want to be as inclusive as possible with the ml parsing, we can always change the role later if we want to ignore some questions
    }
}
