package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

	GamePanel gp;
	public int worldX, worldY;
	public int speed;

	public BufferedImage up, up1, up2, down, down1, down2, left, left1, left2, right, right1, right2;
	public String direction;

	public int spriteCounter = 0;
	public int spriteNum = 1;

	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	public int sequenceCounter = 1; // Counter khusus untuk menghasilkan angka 1-100
	String dialogues[] = new String[20];
	int dialogueIndex = 0;
	public boolean isCompleted = false; // Menandakan apakah interaksi selesai

	
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void setAction() {
	}

	public void speak() {
		if (!isCompleted && dialogues[dialogueIndex] != null) {
			gp.ui.currentDialouge = dialogues[dialogueIndex];
			dialogueIndex++;
		} else {
			isCompleted = true;
			gp.gameState = gp.playState; // Kembali ke playState jika selesai
		}
	}

	public boolean hasNextDialogue() {
		return !isCompleted && dialogueIndex < dialogues.length && dialogues[dialogueIndex] != null;
	}

	public void nextDialogue() {
		if (hasNextDialogue()) {
			gp.ui.currentDialouge = dialogues[dialogueIndex];
			dialogueIndex++;
		} else {
			dialogueIndex = 0; // Reset dialog jika sudah selesai
		}
	}

	public void resetDialogue() {
		dialogueIndex = 0;
		isCompleted = false;
	}

	public void update() {
		setAction();

		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkPlayer(this);
	}

	public void drawNpcStay(Graphics2D g2) {

		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

			switch (direction) {
			case "up":
				if (spriteNum == 1) {
					image = up1;

				}
				break;
			case "down":
				if (spriteNum == 1) {
					image = down1;
				}
				break;
			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}

	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;

		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
				&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
				&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
				&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

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

			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}

	public BufferedImage setup(String imagePath) {

		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;

		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return image;

	}
}
