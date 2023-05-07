package creature;


import main.GamePanel;
import main.UI;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Nikolaidis extends NPC{
    private BufferedImage  nikolaidisInitial,mamaSpawn,mamaDefault,mamaHappy,mamaSad,nikolaidisHappy;
    private String[] question = new String[5];
    private String[][] answer = new String[5][4];
    private int[] correctAnswer = new int[5];
    private int quizStatus = 0;



    public Nikolaidis(GamePanel gamePanel) {
        super(gamePanel);
        getSprites("src/sprites/nikolaidis");
        direction = "down";
        speed = 0;
        actionCounterMax = 300;
        setDialogue();
        setQuiz();
    }

    public void setQuiz() {
        question[0] = "Mama asks: Which of the following measures of\n central tendency is most affected by outliers?";
        answer[0][0] = "Range";
        answer[0][1] = "Mode";
        answer[0][2] = "Median";
        answer[0][3] = "Mean";
        question[1] = "Mama asks: Which of the following is most \n commonly used to represent the spread of a data set?";
        answer[1][0] = "Range";
        answer[1][1] = "Standard Deviation";
        answer[1][2] = "Variance";
        answer[1][3] = "Interquartile range";
        question[2] = "Mama asks: Which of the following graphs is used \n to display categorical data?";
        answer[2][0] = "Historygram";
        answer[2][1] = "Box Plot";
        answer[2][2] = "Pie Chart";
        answer[2][3] = "Scatterplot";
        question[3] = "Which of the following is a characteristic \nof a normal distribution?";
        answer[3][0] = "It is skewed to the left";
        answer[3][1] = "It has a uniform shape";
        answer[3][2] = "It has a high kurtosis value";
        answer[3][3] = " It is bell-shaped";
        question[4] = "Mama asks: What kind of art is Statistics?";
        answer[4][0] = "The art of approximation";
        answer[4][1] = "The art of math expansion";
        answer[4][2] = "It's not art";
        answer[4][3] = "But is it art?";
        correctAnswer[0] = 3;
        correctAnswer[1] = 1;
        correctAnswer[2] = 2;
        correctAnswer[3] = 3;
        correctAnswer[4] = 0;


    }

    @Override
    public void setDialogue() {
        dialogues[0][0] = "Are you ready for the Mama's \n Quiz Challenge?!?";
        dialogues[1][0] = "You made Mama proud!";
        dialogues[2][0] = "You made Mama very sad!";




    }

    @Override
    public void getSprites(String filePath) {
        try{
            up1 = ImageIO.read(new FileInputStream(filePath+"/down1.png"));
            up2 = ImageIO.read(new FileInputStream(filePath+"/down2.png"));
            down1 = ImageIO.read(new FileInputStream(filePath+"/down1.png"));
            down2 = ImageIO.read(new FileInputStream(filePath+"/down2.png"));
            left1 = ImageIO.read(new FileInputStream(filePath+"/down1.png"));
            left2 = ImageIO.read(new FileInputStream(filePath+"/down2.png"));
            right1 = ImageIO.read(new FileInputStream(filePath+"/down1.png"));
            right2 = ImageIO.read(new FileInputStream(filePath+"/down2.png"));
            mamaSpawn = ImageIO.read(new FileInputStream(filePath+"/mamaSpawn.png"));
            mamaDefault = ImageIO.read(new FileInputStream(filePath+"/mamaDefault.png"));
            mamaSad = ImageIO.read(new FileInputStream(filePath+"/mamaSad.png"));
            mamaHappy = ImageIO.read(new FileInputStream(filePath+"/mamaHappy.png"));
            nikolaidisHappy = ImageIO.read(new FileInputStream(filePath+"/nikolaidisHappy.png"));
            nikolaidisInitial = ImageIO.read(new FileInputStream(filePath+"/down1.png"));



        }catch (IOException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void speak() {
        System.out.println(quizStatus);
        quizStatus = gamePanel.getQuizStatus();
        if (quizStatus == 0){
            System.out.println("wtf");
            faceHero();
            startDialogue(this,dialogueSet);
            dialogueSet = 0;
            gamePanel.setGameState(GamePanel.Gamestate.QUIZMENU);
            gamePanel.getUi().setQuizState(UI.QuizState.SELECT);
            gamePanel.getUi().setNikolaidis(this);
        } else if (quizStatus == 1){
            dialogueSet = 1;
            faceHero();
            startDialogue(this,dialogueSet);

        } else if (quizStatus == 2) {
            dialogueSet = 2;
            faceHero();
            startDialogue(this,dialogueSet);

        }


    }

    public BufferedImage getMamaSpawn() {
        return mamaSpawn;
    }

    public BufferedImage getMamaDefault() {
        return mamaDefault;
    }

    public BufferedImage getMamaHappy() {
        return mamaHappy;
    }

    public BufferedImage getMamaSad() {
        return mamaSad;
    }

    public BufferedImage getNikolaidisHappy() {
        return nikolaidisHappy;
    }

    public String[] getQuestion() {
        return question;
    }

    public String[][] getAnswer() {
        return answer;
    }

    public int[] getCorrectAnswer() {
        return correctAnswer;
    }

    public BufferedImage getNikolaidisInitial() {
        return nikolaidisInitial;
    }

    public int getQuizStatus() {
        return quizStatus;
    }

    public void setQuizStatus(int quizStatus) {
        this.quizStatus = quizStatus;
    }
}
