package quiz;

import main.GamePanel;
import main.KeyHandler;

public class quizThree extends Quiz {

	public boolean isQuizFinished = false;
	public boolean isKeySpawned = false;

	public quizThree(GamePanel gp, KeyHandler keyH) {
		super(gp, keyH);

		setQuestion();
		setAnswer();
	}

	public void setQuestion() {

		questions[0] = "Apa itu ihsan? \na. Beribadah seolah-olah melihat Allah \nb. Membantu orang lain \nc. Menjaga diri dari dosa \nd. Menghafal ayat-ayat.";
		questions[1] = "Beribadah dengan ihsan berarti? \na. Melakukan secara asal-asalan \nb. Berusaha dengan sungguh-sungguh \nc. Meniru orang lain \nd. Menghindari ibadah.";
		questions[2] = "Apa arti dari hadits 'Ihsan adalah \nkamu menyembah Allah seolah-olah \nkamu melihat-Nya'? \na. Beribadah dengan penuh kesadaran \nb. Melakukan ibadah karena terpaksa \nc. Beribadah tanpa niat \nd. Menghindari dosa.";
		questions[3] = "Apa kaitan ihsan dengan perilaku \nsehari-hari? \na. Mengabaikan perintah agama \nb. Berlaku baik kepada sesama \nc. Menjauhi orang lain \nd. Tidak ada kaitan.";
		questions[4] = "Siapakah yang paling berhak \nmenerima perilaku ihsan? \na. Orang tua \nb. Tetangga \nc. Sahabat \nd. Semua makhluk.";
		questions[5] = "Apa dasar dari ihsan dalam \nberibadah? \na. Takut dihukum \nb. Cinta kepada Allah \nc. Rasa malu \nd. Kebiasaan.";
		questions[6] = "Bagaimana cara meningkatkan ihsan? \na. Berlatih kesabaran \nb. Menghindari ibadah \nc. Berdebat dengan orang lain \nd. Mengikuti perasaan.";
		questions[7] = "Apa hubungan ihsan dengan akhlak? \na. Akhlak adalah buah dari ihsan \nb. Akhlak tidak terkait \nc. Akhlak adalah syarat utama \nd. Ihsan hanya untuk ibadah.";
		questions[8] = "Beribadah dengan ihsan \nmemberikan apa? \na. Ketentraman hati \nb. Kekayaan \nc. Ketakutan \nd. Kesenangan sementara.";
		questions[9] = "Apa tanda seseorang memiliki ihsan? \na. Berlaku baik kepada semua orang \nb. Hanya ibadah di tempat ibadah \nc. Menghindari tanggung jawab \nd. Berlaku acuh.";

	}

	public void setAnswer() {

		// 0 FOR A, 1 FOR B, 2 FOR C, 3 FOR D
		answer[0] = 0;
		answer[1] = 1;
		answer[2] = 0;
		answer[3] = 1;
		answer[4] = 3;
		answer[5] = 1;
		answer[6] = 0;
		answer[7] = 0;
		answer[8] = 0;
		answer[9] = 0;
	}

	public boolean isFinished() {
		if (questionIndex >= questions.length || questions[questionIndex] == null) {
			gp.ui.showNotification("Kuis selesai! Skor Anda: " + individualScore);

			if (individualScore >= 80) {
				isQuizFinished = true; // Tandai bahwa kuis telah selesai
				totalScore += individualScore;
				if (!isKeySpawned) { // Pastikan kunci hanya muncul sekali
					gp.spawnDiploma(58, 41); // Contoh koordinat lokasi kunci
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
