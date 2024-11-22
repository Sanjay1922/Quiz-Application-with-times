import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class  Question{
    String question;
    String[] options;
    int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizGame {
    private static ArrayList<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        setupQuestions();
        for (Question q : questions) {
            askQuestion(q);
        }
        showResults();
    }

    private static void setupQuestions() {
        questions.add(new Question("What is the capital of France?", new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"}, 3));
        questions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 2));
        // Add more questions as needed
    }

    private static void askQuestion(Question q) {
        System.out.println(q.question);
        for (String option : q.options) {
            System.out.println(option);
        }

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                System.out.println("\nTime's up!");
                submitAnswer(-1, q.correctAnswer);
            }
        };
        timer.schedule(task, 10000); // 10 seconds to answer

        System.out.print("Enter your answer (1-4): ");
        int answer = scanner.nextInt();
        timer.cancel();
        submitAnswer(answer, q.correctAnswer);
    }

    private static void submitAnswer(int answer, int correctAnswer) {
        if (answer == correctAnswer) {
            System.out.println("Correct!\n");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer was option " + correctAnswer + ".\n");
        }
    }

    private static void showResults() {
        System.out.println("Quiz Over!");
        System.out.println("Your score: " + score + "/" + questions.size());
    }
}

