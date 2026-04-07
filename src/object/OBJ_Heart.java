package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject {

	GamePanel gp;

	public OBJ_Heart(GamePanel gp) {

		name = "Heart";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}

//		solidArea.x = 5;
	}

}