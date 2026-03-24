package com.opinix.backend.service;

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
import org.springframework.stereotype.Service;

import com.opinix.backend.model.Poll;
import com.opinix.backend.model.PollAnswer;
import com.opinix.backend.model.PollQuestion;
import com.opinix.backend.model.PollResponse;
import com.opinix.backend.model.QuestionRole;
import com.opinix.backend.model.QuestionType;
import com.opinix.backend.repository.PollRepository;

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

  private QuestionRole detectRole(String header, QuestionType type) {
    String h = header == null ? "" : header.trim().toLowerCase();

    // 1. METADATA (identity / demographic / admin)
    boolean isMetadata =
            type == QuestionType.TIMESTAMP
            || h.equals("name")
            || h.equals("full name")
            || h.contains("email")
            || h.contains("phone")
            || h.equals("respondent id")
            || h.equals("id")
            || h.equals("age")
            || h.equals("age range")
            || h.equals("gender")
            || h.equals("role")
            || h.equals("role / affiliation")
            || h.equals("faculty")
            || h.equals("department")
            || h.equals("faculty / department")
            || h.equals("course")
            || h.equals("class")
            || h.equals("section")
            || h.equals("year")
            || h.equals("year of study")
            || h.equals("location")
            || h.equals("poll title")
            || h.equals("event attended")
            || h.equals("attendance mode")
            || h.contains("student id")
            || h.contains("matric id")
            || h.contains("employee id")
            || h.contains("respondent id");

    if (isMetadata) {
        return QuestionRole.METADATA;
    }

    // 2. STRUCTURED QUESTIONS → IGNORE (even if TEXT)
    boolean isStructured =
            h.contains("(1-5)")
            || h.contains("(1 to 5)")
            || h.contains("rate")
            || h.contains("rating")
            || h.contains("satisfied")
            || h.contains("satisfaction")
            || h.contains("how likely")
            || h.contains("likelihood")
            || h.contains("how did you hear")
            || h.contains("would you attend")
            || h.contains("recommend")
            || h.contains("scale")
            || h.contains("score");

    if (isStructured) {
        return QuestionRole.IGNORE;
    }

    // 3. TRUE FEEDBACK (open-ended)
    boolean isFeedback =
            h.contains("feedback")
            || h.contains("comment")
            || h.contains("comments")
            || h.contains("suggestion")
            || h.contains("suggestions")
            || h.contains("review")
            || h.contains("opinion")
            || h.contains("thought")
            || h.contains("experience")
            || h.contains("what did you like")
            || h.contains("what did you enjoy")
            || h.contains("what went well")
            || h.contains("what could we improve")
            || h.contains("what can we improve")
            || h.contains("what did you dislike")
            || h.contains("anything else")
            || h.contains("additional feedback")
            || h.contains("additional comments");

    if (isFeedback) {
        return QuestionRole.FEEDBACK;
    }

    // 4. SAFE DEFAULT
    // Only generic TEXT fields become feedback
    if (type == QuestionType.TEXT) {
        return QuestionRole.FEEDBACK;
    }

    return QuestionRole.IGNORE;
}
}
