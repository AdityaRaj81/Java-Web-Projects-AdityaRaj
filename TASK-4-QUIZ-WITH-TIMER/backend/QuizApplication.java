// Task - 4

import java.util.*;

class Question {
    String question;
    String[] options;
    int correctOption; // 1-based index of correct option

    public Question(String question, String[] options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

public class QuizApplication {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int TIME_LIMIT_SECONDS = 15;

    private static List<Question> loadQuestions() {
        List<Question> questions = new ArrayList<>();

        questions.add(new Question("What is the capital of France?",
                new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));

        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"1. Earth", "2. Mars", "3. Venus", "4. Jupiter"}, 2));

        questions.add(new Question("What is the largest ocean on Earth?",
                new String[]{"1. Atlantic", "2. Indian", "3. Pacific", "4. Arctic"}, 3));

        questions.add(new Question("Who wrote 'Romeo and Juliet'?",
                new String[]{"1. Charles Dickens", "2. William Shakespeare", "3. Mark Twain", "4. Jane Austen"}, 2));

        return questions;
    }

    public static void main(String[] args) {
        List<Question> questions = loadQuestions();
        int score = 0;
        int questionNumber = 1;
        Map<Integer, Boolean> resultSummary = new LinkedHashMap<>();

        System.out.println("🧠 Welcome to the Quiz!\nYou have 15 seconds per question.\n");

        for (Question q : questions) {
            System.out.println("Q" + questionNumber + ": " + q.question);
            for (String opt : q.options) {
                System.out.println(opt);
            }

            long startTime = System.currentTimeMillis();
            System.out.print("Enter your choice (1-4): ");

            int answer = -1;
            boolean timedOut = false;

            while ((System.currentTimeMillis() - startTime) < TIME_LIMIT_SECONDS * 1000 && !scanner.hasNextInt()) {
                // waiting for user input or timeout
            }

            if (scanner.hasNextInt()) {
                answer = scanner.nextInt();
            } else {
                timedOut = true;
            }

            if (timedOut) {
                System.out.println("\n⏰ Time's up! No answer submitted.");
                resultSummary.put(questionNumber, false);
            } else {
                if (answer == q.correctOption) {
                    System.out.println("✅ Correct!");
                    score++;
                    resultSummary.put(questionNumber, true);
                } else {
                    System.out.println("❌ Incorrect. Correct Answer: Option " + q.correctOption);
                    resultSummary.put(questionNumber, false);
                }
            }

            System.out.println("--------------------------------------------------");
            questionNumber++;
        }

        // Final Score
        System.out.println("\n🎉 Quiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());

        System.out.println("\n📋 Result Summary:");
        for (Map.Entry<Integer, Boolean> entry : resultSummary.entrySet()) {
            System.out.println("Q" + entry.getKey() + ": " + (entry.getValue() ? "Correct" : "Incorrect"));
        }
    }
}
