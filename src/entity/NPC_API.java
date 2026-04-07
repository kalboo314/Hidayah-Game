package entity;

import java.util.Random;

import main.GamePanel;
import main.UtilityTool;

public class NPC_API extends Entity {

	public NPC_API(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 1;

		getImage();
//		setDialouge();
	}

	public void getImage() {

		up1 = setup("/npc/api_1");
//		up2 = setup("/npc/body_up_2");
		down1 = setup("/npc/api_2");
//		down2 = setup("/npc/body_down_2");
//		left1 = setup("/npc/body_left_1");
//		left2 = setup("/npc/body_left_2");
//		right1 = setup("/npc/body_right_1");
//		right2 = setup("/npc/body_right_2");
	}

//	public void setAction() {
//		
//		actionLockCounter ++;
//		if(actionLockCounter == 120) {
//			Random random = new Random();
//			int i = random.nextInt(100)+1; 
//			
//			if (i <= 25) {
//				direction = "up";
//			}
//			if(i > 25 && i <= 50) {
//				direction = "down";
//			}
//			if(i > 50 && i <= 75) {
//				direction = "left";
//			}
//			if (i > 75 && i <= 100) {
//				direction = "right";
//			}
//			
//			actionLockCounter = 0;
//			
//		}
//		
//		
//	}

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
		super.speak();

	}
}
