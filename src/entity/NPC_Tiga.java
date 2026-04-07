package entity;

import main.GamePanel;

public class NPC_Tiga extends Entity {

	public NPC_Tiga(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getImage();
		setDialouge();
	}

	public void getImage() {
		up1 = setup("/npc/ustad1");
		down1 = setup("/npc/ustad2");
	}

	public void setDialouge() {
		dialogues[0] = "Hadirin Sidang Jum'at yang berbahagia";
		dialogues[1] = "Sabar berasal dari kata \"sobaro yasbiru\" yang artinya menahan. Menurut istilah, sabar adalah menahan diri dari kesusahan dan menyikapinya sesuai syariah dan akal.";
		dialogues[2] = "Sabar adalah pilar kebahagiaan seorang hamba, karena dengan kesabaran kita akan terjaga dari kemaksiatan, , dan tabah dalam menghadapi berbagai macam cobaan.";
		dialogues[3] = "Sabar merupakan ajaran yang banyak sekali disinggung dalam Al-Qur'an maupun hadis, sehingga manusia senantiasa diarahkan untuk selalu bersabar dalam kehidupannya.";
		dialogues[4] = "Kesabaran yang sebenarnya adalah kemampuan dalam mengendalikan sikap, sehingga bisa dengan ikhlas dan rela hati menerima kondisi yang sedang dihadapinya demi mendapat balasan yang baik di akhirat.";
//		dialogues[3] = "Sabar merupakan ajaran yang banyak sekali disinggung dalam Al-Qur'an maupun hadis, sehingga manusia senantiasa diarahkan untuk selalu bersabar dalam kehidupannya.";

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
