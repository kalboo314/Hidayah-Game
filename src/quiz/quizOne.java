package quiz;

import main.FileManager;
import main.GamePanel;
import main.KeyHandler;

public class quizOne extends Quiz {

	public boolean isQuizFinished = false;
	public boolean isKeySpawned = false;

	public quizOne(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);

		setQuestion();
		setAnswer();
	}

	public void setQuestion() {
		// TEN QUESTION WITH EACH QUESTION HAS A MULTIPLE CHOICE (A TO D)
		questions[0] = "Apa itu rukun Islam? \na. Aturan tambahan dalam Islam \nb. Pilar-pilar agama Islam \nc. Sejarah Islam \nd. Tradisi umat Islam.";
		questions[1] = "Apa rukun Islam yang pertama? \na. Membayar zakat \nb. Shalat \nc. Puasa \nd. Mengucapkan syahadat.";
		questions[2] = "Hukum melaksanakan shalat \nlima waktu adalah? \na. Sunah \nb. Fardhu kifayah \nc. Fardhu ain \nd. Mubah.";
		questions[3] = "Apa arti dari 'zakat'? \na. Membersihkan \nb. Menyumbang \nc. Membayar pajak \nd. Sedekah.";
		questions[4] = "Puasa di bulan Ramadan adalah \nrukun Islam yang ke? \na. Ketiga \nb. Keempat \nc. Kelima \nd. Kedua.";
		questions[5] = "Haji menjadi wajib bagi? \na. Semua orang \nb. Orang yang mampu \nc. Anak-anak \nd. Semua muslim di usia tertentu.";
		questions[6] = "Berapa kali shalat wajib dilakukan \ndalam sehari? \na. 3 kali \nb. 5 kali \nc. 7 kali \nd. 1 kali.";
		questions[7] = "Apa syarat sah puasa Ramadan? \na. Niat \nb. Membaca Al-Qur'an \nc. Berwudhu \nd. Menghafal doa.";
		questions[8] = "Apa arti dari kata 'zakat fitrah'? \na. Sedekah biasa \nb. Zakat wajib saat Ramadan \nc. Bantuan sosial \nd. Pengganti shalat.";
		questions[9] = "Apa tujuan utama dari rukun Islam? \na. Menambah pahala \nb. Mendapatkan keberuntungan \nc. Mendekatkan diri kepada Allah \nd. Mematuhi tradisi.";
	}

	public void setAnswer() {
		// 0 FOR A, 1 FOR B, 2 FOR C, 3 FOR D
		answer[0] = 1;
		answer[1] = 3;
		answer[2] = 2;
		answer[3] = 0;
		answer[4] = 1;
		answer[5] = 1;
		answer[6] = 1;
		answer[7] = 0;
		answer[8] = 1;
		answer[9] = 2;
	}

	public boolean isFinished() {
		if (questionIndex >= questions.length || questions[questionIndex] == null) {
			gp.ui.showNotification("Kuis selesai! Skor Anda: " + individualScore);
			int quizScore = gp.quiz.getScore(); // Ambil skor quiz
			System.out.println("Quiz finished! Score: " + quizScore);
			gp.updateIndividualScore("quizOne", quizScore);

			if (individualScore >= 80) {
				totalScore += individualScore;
				isQuizFinished = true; // Tandai bahwa kuis telah selesai
				if (!isKeySpawned) { // Pastikan kunci hanya muncul sekali
					gp.spawnKey(23, 45); // Contoh koordinat lokasi kunci
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
