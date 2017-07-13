package com.krishnaraj;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 *
 */
public class Main {

    static final String QUESTION_FILE= "/home/krishnaraj/IdeaProjects/Assignment/src/com/krishnaraj/blueprint.txt";
    static final String ANSWER_SHEET_FILE= "/home/krishnaraj/IdeaProjects/Assignment/src/com/krishnaraj/answersheet.txt";

    public static void main(String[] args) throws Exception {
        Map<Integer,Question> questionMap = readFromQuestionFileIO(QUESTION_FILE);
        Map<Integer,AnswerSheet> answerSheet = readFromAnswerSheetFileIO(ANSWER_SHEET_FILE);

        int mark=0;
        for(Map.Entry<Integer,Question> entry1:questionMap.entrySet()){
            for(Map.Entry<Integer,AnswerSheet> entry2:answerSheet.entrySet()){
                if((entry1.getValue().answer==entry2.getValue().answer) && (entry1.getValue().qno == entry2.getValue().qno))
                    mark=mark+entry1.getValue().mark;
            }
        }
        System.out.println(mark);
        //writeToFile(DESTINATION_FILE,students);
    }


    public static Map<Integer,Question> readFromQuestionFileIO(String filename) throws IOException{
        Map<Integer,Question> questions = new TreeMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line=" ";
            Integer k=1;
            while ((line = br.readLine()) != null) {
                k=k+1;
              String tokens[] = line.split(" ");
              questions.put(k,new Question(Integer.parseInt(tokens[0]),tokens[1],tokens[2],Integer.parseInt(tokens[3])));
            }
        }
        return questions;
    }

    public static Map<Integer,AnswerSheet> readFromAnswerSheetFileIO(String filename) throws IOException{
        Map<Integer,AnswerSheet> answers = new TreeMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
           String ansline=" ";
           Integer k=1;
           while ((ansline = br.readLine())!=null){
               k=k+1;
               String ans[] = ansline.split(" ");
               answers.put(k,new AnswerSheet(Integer.parseInt(ans[0]),Integer.parseInt(ans[1]),ans[2]));
           }
        }
        return answers;
    }
}

class Question {
    Integer qno;
    String question;
    String answer;
    int mark;
    public Question(Integer qno, String question, String answer, Integer mark) {
        this.qno = qno;
        this.question = question;
        this.answer = answer;
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "qno=" + qno + '\'' +
                ", Question=" + question + '\'' +
                ", Answer=" + answer + '\'' +
                ", Mark=" +
                '}';
    }
}

class AnswerSheet{
    Integer rollno;
    Integer qno;
    String answer;
    public AnswerSheet(Integer rollno,Integer qno,String answer){
        this.rollno = rollno;
        this.qno = qno;
        this.answer = answer;
    }

    @Override
    public String toString(){
        return "AnswerSheet{" +
               "rollno=" + rollno + '\'' +
                "qno=" + qno + '\'' +
                "answer=" + answer + '\'' +
                '}';    }
}
