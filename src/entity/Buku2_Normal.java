package entity;

import main.GamePanel;

public class Buku2_Normal extends Entity {

	public Buku2_Normal(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getImage();
		setDialouge();
	}

	public void getImage() {
		up1 = setup("/npc/buku_3");
		down1 = setup("/npc/buku_4");
	}

	public void setDialouge() {
		dialogues[0] = "Iman kepada qada dan qadar adalah keyakinan bahwa segala sesuatu yang terjadi di dunia ini sudah ditentukan oleh Allah.";
		dialogues[1] = "Malaikat Jibril adalah malaikat yang bertugas menyampaikan wahyu dari Allah kepada para nabi dan rasul. Ia juga dikenal sebagai penyampai wahyu yang terakhir kepada Nabi Muhammad SAW.";
		dialogues[2] = "Iman kepada malaikat adalah rukun iman yang kedua. Keyakinan ini mengharuskan umat Islam untuk meyakini bahwa malaikat adalah makhluk ciptaan Allah.";
		dialogues[3] = "Rasul terakhir yang diutus Allah adalah Nabi Muhammad SAW. Beliau diutus sebagai rahmat bagi seluruh umat manusia dan membawa wahyu terakhir, yaitu Al-Qur'an, yang menjadi petunjuk hidup bagi umat Islam.";
		dialogues[4] = "Beriman kepada Allah dapat diwujudkan dengan menjalankan perintah-Nya, menjauhi larangan-Nya, serta berusaha menjalani hidup sesuai dengan ajaran Islam.";
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
