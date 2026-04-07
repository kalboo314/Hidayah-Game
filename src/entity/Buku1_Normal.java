package entity;

import main.GamePanel;

public class Buku1_Normal extends Entity {

	public Buku1_Normal(GamePanel gp) {
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
		dialogues[0] = "Iman adalah keyakinan dalam hati dan pengakuan dengan lisan, serta diwujudkan dalam tindakan. Iman adalah dasar dari kehidupan seorang Muslim.";
		dialogues[1] = "Rukun iman yang pertama adalah iman kepada Allah, yaitu meyakini bahwa Allah adalah Tuhan yang Maha Esa, Pencipta, Pemelihara, dan Pengatur alam semesta serta segala isinya.";
		dialogues[2] = "Malaikat adalah makhluk halus yang diciptakan oleh Allah dari cahaya. Mereka tidak tampak oleh mata manusia, tetapi tugas mereka sangat penting, seperti menyampaikan wahyu kepada para nabi dan rasul.";
		dialogues[3] = "Iman kepada kitab Allah berarti meyakini bahwa Allah menurunkan kitab-kitab-Nya sebagai petunjuk hidup bagi umat manusia. Kitab-kitab ini diwahyukan kepada para rasul sebagai panduan hidup.";
		dialogues[4] = "Kitab suci yang diturunkan kepada Nabi Musa adalah Taurat. Taurat mengandung wahyu dan hukum-hukum Allah untuk umatnya pada masa itu, dan menjadi sumber petunjuk bagi umat Islam.";
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
