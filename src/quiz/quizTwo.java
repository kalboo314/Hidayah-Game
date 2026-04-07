package quiz;

import main.GamePanel;
import main.KeyHandler;

public class quizTwo extends Quiz {

	public boolean isQuizFinished = false;
	public boolean isKeySpawned = false;

	public quizTwo(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);

		setQuestion();
		setAnswer();
	}

	public void setQuestion() {

		questions[0] = "Apa itu iman? \na. Kepercayaan tanpa dasar \nb. Percaya dan yakin kepada Allah \nc. Keberanian mengambil risiko \nd. Sikap skeptis.";
		questions[1] = "Berapa jumlah rukun iman? \na. 3 \nb. 5 \nc. 6 \nd. 10.";
		questions[2] = "Siapakah malaikat yang bertugas \nmencabut nyawa? \na. Jibril \nb. Israfil \nc. Izrail \nd. Mikail.";
		questions[3] = "Apa arti dari 'iman kepada kitab \nAllah'? \na. Membaca kitab suci \nb. Menghafal Al-Qur'an \nc. Mempercayai kitab-kitab Allah \nd. Melaksanakan syariat.";
		questions[4] = "Apa kitab suci yang diturunkan \nkepada Nabi Musa? \na. Injil \nb. Taurat \nc. Zabur \nd. Al-Qur'an.";
		questions[5] = "Iman kepada qada dan qadar berarti? \na. Meyakini takdir Allah \nb. Menentukan nasib sendiri \nc. Berharap pada keberuntungan \nd. Mengikuti ramalan.";
		questions[6] = "Siapakah malaikat yang \nmenyampaikan wahyu? \na. Mikail \nb. Jibril \nc. Israfil \nd. Izrail.";
		questions[7] = "Iman kepada malaikat termasuk \ndalam rukun iman yang ke? \na. Pertama \nb. Kedua \nc. Ketiga \nd. Keempat.";
		questions[8] = "Siapakah Rasul terakhir yang \ndiutus Allah? \na. Nabi Isa \nb. Nabi Musa \nc. Nabi Muhammad \nd. Nabi Nuh.";
		questions[9] = "Beriman kepada Allah dapat \ndiwujudkan dengan? \na. Mengabaikan perintah-Nya \nb. Mengikuti aturan-Nya \nc. Menghindari ibadah \nd. Bersikap acuh.";

	}

	public void setAnswer() {

		// 0 FOR A, 1 FOR B, 2 FOR C, 3 FOR D
		answer[0] = 1;
		answer[1] = 2;
		answer[2] = 2;
		answer[3] = 2;
		answer[4] = 1;
		answer[5] = 0;
		answer[6] = 1;
		answer[7] = 1;
		answer[8] = 2;
		answer[9] = 1;
	}

	public boolean isFinished() {
		if (questionIndex >= questions.length || questions[questionIndex] == null) {
			gp.ui.showNotification("Kuis selesai! Skor Anda: " + individualScore);

			if (individualScore >= 80) {
				isQuizFinished = true; // Tandai bahwa kuis telah selesai
				totalScore += individualScore;
				if (!isKeySpawned) { // Pastikan kunci hanya muncul sekali
					gp.spawnKey(29, 20); // Contoh koordinat lokasi kunci
					gp.ui.showNotification("Kunci baru telah muncul!");
					isKeySpawned = true; // Tandai kunci sudah muncul
					gp.savePlayerScore(totalScore);
				}
				gp.gameState = gp.playState; // Kembali ke game state
			} else {
				gp.ui.showNotification("Skor Anda kurang dari 80. Coba lagi!");
				resetQuiz(); // Reset kuis jika skor kurang dari 80
				failCounter++;
				System.out.println("Fail Counter " + failCounter);
				gp.gameState = gp.playState; // Kembali ke game state
				gameOver();
			}
		}
		return isQuizFinished;
	}

	public void displayQuiz() {

		super.displayQuiz();
	}
}
