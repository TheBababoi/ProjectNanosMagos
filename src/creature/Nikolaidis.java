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
        question[0] = "Let us begin with Question 1";
        answer[0][0] = "Wrong answer";
        answer[0][1] = "Wrong answer";
        answer[0][2] = "Wrong answer";
        answer[0][3] = "Correct answer";
        question[1] = "Question 2";
        answer[1][0] = "Wrong answer";
        answer[1][1] = "Correct answer";
        answer[1][2] = "Wrong answer";
        answer[1][3] = "Wrong answer";
        question[2] = "Question 3";
        answer[2][0] = "Wrong answer";
        answer[2][1] = "Wrong answer";
        answer[2][2] = "Correct answer";
        answer[2][3] = "Wrong answer";
        question[3] = "Question 4";
        answer[3][0] = "Wrong answer";
        answer[3][1] = "Wrong answer";
        answer[3][2] = "Wrong answer";
        answer[3][3] = "Correct answer";
        question[4] = "Question 5";
        answer[4][0] = "Correct answer";
        answer[4][1] = "Wrong answer";
        answer[4][2] = "Wrong answer";
        answer[4][3] = "Wrong answer";
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
        quizStatus = gamePanel.getQuizStatus();
        if (quizStatus == 0){
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
