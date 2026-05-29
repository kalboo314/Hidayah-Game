package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

//	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);

		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle();
		// ADJUST ACCORDINGLY
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;

		setDefaultValues();
		getPlayerImage();
	}

	public void setDefaultValues() {

		worldX = gp.tileSize * 13;
		worldY = gp.tileSize * 50;
		speed = 4;
		direction = "down";
		hasKey = 0;
	}

	public void getPlayerImage() {

		up1 = setup("/player/badan_atas_1");
		up2 = setup("/player/badan_atas_2");
		down1 = setup("/player/badan_bawah_1");
		down2 = setup("/player/badan_bawah_2");
		left1 = setup("/player/badan_kiri_1");
		left2 = setup("/player/badan_kiri_2");
		right1 = setup("/player/badan_kanan_1");
		right2 = setup("/player/badan_kanan_2");
	}

	public void update() {

		if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
				|| keyH.rightPressed == true) {

			if (keyH.upPressed == true) {
				direction = "up";
			} else if (keyH.downPressed == true) {
				direction = "down";
			} else if (keyH.leftPressed == true) {
				direction = "left";
			} else if (keyH.rightPressed == true) {
				direction = "right";
			}

			if (keyH.shiftPressed == true) {
				speed = 6;
			} else {
				speed = 4;
			}

			// CHECK TILE COLLISION
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// CHECK OBJECT COLLISION
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			intersectObject(objIndex);

			// CHECK NPC COLLISION
			int npcIndex = gp.cChecker.chechEntity(this, gp.npc);
			interactNPC(npcIndex);

			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if (collisionOn == false) {

				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}

			spriteCounter++;
			if (spriteCounter > 10) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}

	}

	public void pickUpObject(int i) {
		if (i != 999 && gp.obj[i] != null) { // Pastikan obj[i] tidak null
			String objectName = gp.obj[i].name;

			switch (objectName) {
			case "Key":
				hasKey++;
				gp.obj[i] = null; // Hapus objek dari game setelah diambil
				System.out.println("Key : " + hasKey);
				break;

			case "Door":
				if (hasKey > 0) {
					gp.obj[i] = null; // Hapus pintu setelah dibuka
					hasKey--;
					System.out.println("Key : " + hasKey);
				} else {
					System.out.println("You need a key to open this door!");
				}
				break;
				
			case "Diploma":
				gp.obj[i] = null;
				gp.gameState = gp.gameFinalState;
				break;
			}
		}
	}

	public void intersectObject(int i) {
		if (i != 999 && gp.obj[i] != null && gp.keyH.enterPressed == true) { // Pastikan obj[i] tidak null
			String objectName = gp.obj[i].name;

			switch (objectName) {
			case "Kursi":
				// Masuk ke mode kuis
				gp.gameState = gp.quizState;
				gp.quiz = gp.quizOne;
				gp.quiz.displayQuiz();

				// Evaluasi setelah kuis selesai
				if (gp.quizOne.isFinished()) { // Pastikan quiz memiliki method isFinished()
					int quizScore = gp.quiz.getScore(); // Ambil skor quiz
					System.out.println("Quiz finished! Score: " + quizScore);
					gp.updateIndividualScore("quizOne", quizScore);

				}
				break;

			case "Kursi1":
				// Masuk ke mode kuis
				gp.gameState = gp.quizState;
				gp.quiz = gp.quizTwo;
				gp.quiz.displayQuiz();

				// Evaluasi setelah kuis selesai
				if (gp.quizTwo.isFinished()) { // Pastikan quiz memiliki method isFinished()
					int quizScore = gp.quiz.getScore(); // Ambil skor quiz
					System.out.println("Quiz finished! Score: " + quizScore);
					gp.updateIndividualScore("quizTwo", quizScore);
				}
				break;

			case "Kursi2":
				// Masuk ke mode kuis
				gp.gameState = gp.quizState;
				gp.quiz = gp.quizThree;
				gp.quiz.displayQuiz();

				// Evaluasi setelah kuis selesai
				if (gp.quizThree.isFinished()) { // Pastikan quiz memiliki method isFinished()
					int quizScore = gp.quiz.getScore(); // Ambil skor quiz
					System.out.println("Quiz finished! Score: " + quizScore);
					gp.updateIndividualScore("quizThree", quizScore);
				}
				break;

			default:
				System.out.println("No interaction defined for this object.");
				break;
			}
		}
	}

	public void interactNPC(int i) {
		if (i != 999) { // Pastikan ada NPC yang dapat diinteraksi
			if (gp.keyH.enterPressed) {
				gp.currentNPCIndex = i;
				if (!gp.npc[i].hasNextDialogue()) {
					gp.npc[i].resetDialogue();
				}
				gp.npc[i].speak(); // Gunakan metode speak() NPC
			}
		}
		gp.keyH.enterPressed = false;
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, null);

	}
}
