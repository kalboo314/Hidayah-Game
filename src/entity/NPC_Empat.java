package entity;

import main.GamePanel;

public class NPC_Empat extends Entity {

	public NPC_Empat(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getImage();
		setDialouge();
	}

	public void getImage() {
		up1 = setup("/npc/npc_4_1");
		down1 = setup("/npc/npc_4_2");
	}

	public void setDialouge() {
		dialogues[0] = "Kamu telah mencapai level yang lebih sulit! Di sini, kesabaran dan ketelitianmu akan benar-benar diuji.";
		dialogues[1] = "Buku-buku yang kamu butuhkan untuk menjawab pertanyaan kali ini tersembunyi dengan sangat baik. Hanya yang benar-benar teliti yang bisa menemukannya.";
		dialogues[2] = "Ingat, tidak semua buku berisi informasi yang kamu butuhkan. Beberapa di antaranya mungkin hanya petunjuk tambahan, jadi pikirkan baik-baik setiap langkahmu.";
		dialogues[3] = "Luar biasa! Kamu telah membuktikan dirimu layak untuk menghadapi tantangan ini. Sekarang, bersiaplah untuk pertanyaan paling sulit yang pernah ada!";
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
