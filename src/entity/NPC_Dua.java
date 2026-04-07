package entity;

import main.GamePanel;

public class NPC_Dua extends Entity {

	public NPC_Dua(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getImage();
		setDialouge();
	}

	public void getImage() {
		up1 = setup("/npc/npc_2_napas");
		down1 = setup("/npc/npc_2");
	}

	public void setDialouge() {
		dialogues[0] = "Selamat datang di level berikutnya! Kali ini, tantangan akan sedikit lebih sulit, tapi aku yakin kalian bisa mengatasinya.";
		dialogues[1] = "Di sekitar sini ada buku-buku dengan informasi penting untuk menjawab soal yang akan saya berikan. Tapi, hati-hati! Buku-buku tersebut tersembunyi lebih baik daripada sebelumnya.";
		dialogues[2] = "Jelajahi lingkungan ini dengan teliti, dan pastikan kamu membaca setiap buku yang kamu temukan. Semua informasi yang kamu butuhkan ada di sana.";
		dialogues[3] = "Luar biasa! Kamu berhasil menemukan bukunya. Dengan persiapan seperti ini, kamu pasti bisa menjawab pertanyaannya dengan baik. Ayo, kita mulai!";
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
