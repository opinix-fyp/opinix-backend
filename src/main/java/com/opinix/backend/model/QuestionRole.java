package com.opinix.backend.model;

public enum QuestionRole {
    METADATA, //this is for user related stuff.. or just personal info not to be parsed into the ml
    FEEDBACK, //this is the actual stuff we want to do sentiment analysis on
    IGNORE //this is just irrelevant stuff to be ignored
}
