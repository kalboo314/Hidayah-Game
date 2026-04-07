package entity;

import main.GamePanel;

public class NPC_Satu extends Entity {

	public NPC_Satu(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getImage();
		setDialouge();
	}

	public void getImage() {
		up1 = setup("/npc/npc_3_napas");
		down1 = setup("/npc/npc_3");
	}

	public void setDialouge() {
		dialogues[0] = "Selamat datang, anak-anak! Sebelum kita mulai menjawab pertanyaan, ada sesuatu yang harus kalian cari terlebih dahulu.";
		dialogues[1] = "Di sekitar sini ada buku-buku yang bisa membantu kalian memahami soal yang akan saya tanyakan. Cari buku tersebut dan baca baik-baik isinya.";
		dialogues[2] = "Jika sudah menemukan buku, kembali ke sini dan saya akan memberikan pertanyaannya. Buku itu akan membantumu menjawab dengan lebih mudah!";
		dialogues[3] = "Bagus sekali! Dengan membaca buku terlebih dahulu, kamu sudah menunjukkan sikap seorang pembelajar sejati. Sekarang, mari kita mulai pertanyaannya!";
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
