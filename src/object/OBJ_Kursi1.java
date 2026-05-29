package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Kursi1 extends SuperObject {

	GamePanel gp;

	public OBJ_Kursi1(GamePanel gp) {

		name = "Kursi1";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/kursi_1.png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;

	}

}
