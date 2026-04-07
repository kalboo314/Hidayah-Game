package entity;

import main.GamePanel;

public class Buku2_Easy extends Entity {

	public Buku2_Easy(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getImage();
		setDialouge();
	}

	public void getImage() {
		up1 = setup("/npc/buku_1");
		down1 = setup("/npc/buku_2");
	}

	public void setDialouge() {
		dialogues[0] = "Haji adalah ibadah yang diwajibkan bagi setiap muslim yang mampu secara fisik dan finansial. Ibadah ini hanya dilakukan sekali seumur hidup jika memenuhi syarat.";
		dialogues[1] = "Shalat wajib dilakukan lima kali dalam sehari. Waktu shalat tersebut adalah subuh, zuhur, asar, maghrib, dan isya.";
		dialogues[2] = "Salah satu syarat sah puasa Ramadan adalah niat. Niat dilakukan sebelum memulai puasa untuk menegaskan kesungguhan menjalankan ibadah ini.";
		dialogues[3] = "Zakat fitrah adalah zakat wajib yang diberikan saat Ramadan, menjelang Idul Fitri. Zakat ini bertujuan untuk menyucikan jiwa dan membantu mereka yang membutuhkan.";
		dialogues[4] = "Tujuan utama dari rukun Islam adalah untuk mendekatkan diri kepada Allah dan menjalankan perintah-Nya. Rukun Islam menjadi panduan untuk menjalani kehidupan yang sesuai dengan ajaran Islam.";
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
