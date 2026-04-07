package entity;

import main.GamePanel;

public class Buku1_Hard extends Entity {

	public Buku1_Hard(GamePanel gp) {
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
		dialogues[0] = "Ihsan adalah beribadah seolah-olah kita melihat Allah, dan meskipun kita tidak melihat-Nya, kita yakin bahwa Allah selalu melihat kita.";
		dialogues[1] = "Beribadah dengan ihsan berarti berusaha dengan sungguh-sungguh, bukan sekadar menjalankan kewajiban secara asal-asalan. Ini berarti kita melaksanakan ibadah dengan sepenuh hati.";
		dialogues[2] = "Hadits yang mengatakan 'Ihsan adalah kamu menyembah Allah seolah-olah kamu melihat-Nya' mengajarkan kita untuk beribadah dengan kesadaran penuh, seolah-olah kita bisa merasakan kehadiran Allah.";
		dialogues[3] = "Ihsan berhubungan erat dengan perilaku sehari-hari. Berperilaku dengan ihsan berarti kita selalu berusaha berlaku baik kepada sesama, menjaga hubungan dengan Allah dan manusia.";
		dialogues[4] = "Orang yang paling berhak menerima perilaku ihsan adalah semua makhluk. Ihsan bukan hanya terkait dengan ibadah kepada Allah, tetapi juga dengan berbuat baik kepada sesama makhluk-Nya.";
		dialogues[5] = "Dasar dari ihsan dalam beribadah adalah rasa cinta kepada Allah, bukan karena takut dihukum atau terpaksa. Ketulusan dan ikhlas dalam beribadah adalah bentuk ibadah yang paling tinggi.";
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
