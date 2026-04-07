package entity;

import main.GamePanel;

public class Buku1_Easy extends Entity {

	public Buku1_Easy(GamePanel gp) {
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
		dialogues[0] = "Rukun Islam adalah lima pilar utama dalam Islam yang menjadi landasan bagi setiap muslim untuk menjalankan agamanya. Pilar-pilar ini meliputi syahadat, shalat, zakat, puasa, dan haji.";
		dialogues[1] = "Rukun Islam yang pertama adalah syahadat, yaitu pernyataan keimanan bahwa 'Tidak ada Tuhan selain Allah, dan Muhammad adalah utusan-Nya'. Syahadat menjadi pintu masuk seseorang ke dalam Islam.";
		dialogues[2] = "Shalat lima waktu adalah kewajiban yang harus dilakukan oleh setiap muslim. Hukum melaksanakan shalat lima waktu adalah fardhu ain, yaitu kewajiban individu yang tidak bisa diwakilkan.";
		dialogues[3] = "Zakat berasal dari kata 'zakat' yang artinya 'membersihkan' atau 'menyucikan'. Zakat bertujuan untuk membantu sesama dan membersihkan harta orang yang mampu.";
		dialogues[4] = "Puasa di bulan Ramadan adalah salah satu rukun Islam. Puasa ini wajib dilakukan oleh setiap muslim yang sudah baligh. Puasa Ramadan adalah rukun Islam yang keempat.";
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
