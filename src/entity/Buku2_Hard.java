package entity;

import main.GamePanel;

public class Buku2_Hard extends Entity {

	public Buku2_Hard(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getImage();
		setDialouge();
	}

	public void getImage() {
		up1 = setup("/npc/buku_5");
		down1 = setup("/npc/buku_6");
	}

	public void setDialouge() {
		dialogues[0] = "Dasar dari ihsan dalam beribadah adalah rasa cinta kepada Allah, bukan karena takut dihukum atau terpaksa. Ketulusan dan ikhlas dalam beribadah adalah bentuk ibadah yang paling tinggi.";
		dialogues[1] = "Untuk meningkatkan ihsan, kita perlu berlatih kesabaran dan menjaga kualitas ibadah kita. Menghindari sikap buruk dan berlatih ikhlas adalah cara untuk meningkatkan ihsan.";
		dialogues[2] = "Ihsan memiliki kaitan yang sangat erat dengan akhlak. Akhlak yang baik adalah buah dari ihsan. Seseorang yang beribadah dengan ihsan akan memiliki akhlak yang mulia dan akan berlaku baik kepada sesama.";
		dialogues[3] = "Beribadah dengan ihsan memberikan ketentraman hati, kedamaian batin, dan kepuasan spiritual. Hal ini terjadi karena kita menjalankan ibadah dengan penuh keikhlasan dan kesadaran, serta mengharapkan ridha Allah.";
		dialogues[4] = "Tanda seseorang memiliki ihsan adalah apabila ia selalu berusaha berlaku baik kepada semua orang, bukan hanya di tempat ibadah. Ihsan menuntut kita untuk membawa perilaku baik dalam segala aspek.";
	}

	public void setAction() {
		actionLockCounter++;
		if (actionLockCounter == 30) {
			// Menghasilkan angka berurutan dari 1-100
			int i = sequenceCounter;

			// Cetak angka ke konsol untuk verifikasi
//	        System.out.println("Angka yang dihasilkan: " + i);

			// Cek apakah angka ganjil atau genap
			if (i % 2 != 0) {
				direction = "up";
			} else {
				direction = "down";
			}

			// Perbarui sequenceCounter untuk angka berikutnya
			sequenceCounter++;
			if (sequenceCounter > 100) {
				sequenceCounter = 1; // Reset ke 1 jika sudah mencapai 100
			}

			// Reset actionLockCounter
			actionLockCounter = 0;
		}
	}

	public void speak() {
		if (hasNextDialogue()) {
			nextDialogue();
			gp.gameState = gp.dialougeState; // Ubah state menjadi dialog
		} else {
			System.out.println("Dialog selesai.");
			gp.gameState = gp.playState; // Kembali ke playState jika dialog selesai
		}
	}
}
