package quiz;

import entity.Player;
import main.GamePanel;
import main.KeyHandler;

public class Quiz {

	GamePanel gp;
	KeyHandler keyH;
	public String[] questions = new String[10];
	int answer[] = new int[10];
	public int questionIndex = 0;
	int answerIndex = 0;
	public static int totalScore = 0; // Variabel statis untuk akumulasi skor semua quiz
	private int scoreDisplayCounter = 0; // Durasi tampilan skor akhir
	public int individualScore = 0; // Variabel untuk skor masing-masing quiz
	public int failCounter = 0;

	public Quiz(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
	}

	public void displayQuiz() {
		if (questionIndex >= questions.length || questions[questionIndex] == null) {
			gp.gameState = gp.playState; // Kembali ke game state
		} else {
			// Tampilkan pertanyaan saat ini
			gp.ui.currentQuestion = questions[questionIndex];
		}
	}

	public boolean checkAnswer(int playerAnswer) {
		boolean isCorrect = answer[questionIndex] == playerAnswer;

		if (isCorrect) {
			individualScore += 10; // Tambah skor 10 untuk jawaban benar
			gp.ui.showNotification("Jawaban benar!");
		} else {
			gp.ui.showNotification("Jawaban salah!");
		}
		questionIndex++;
		return isCorrect;
	}

	public int getScore() {
		return individualScore;
	}

	public void resetQuiz() {
		questionIndex = 0;
		individualScore = 0;
	}

	public void gameOver() {
		if (failCounter >= 3) {
			gp.gameState = gp.gameOverState;
		}
		keyH.gameOverState(hashCode());
	}

	// Method untuk mengecek apakah kuis selesai
	public boolean isScoreVisible() {
		return scoreDisplayCounter > 0;
	}

	public void decrementScoreCounter() {
		if (scoreDisplayCounter > 0) {
			scoreDisplayCounter--;
		}
	}
}
