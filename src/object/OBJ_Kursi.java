package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Kursi extends SuperObject {

	GamePanel gp;

	public OBJ_Kursi(GamePanel gp) {

		name = "Kursi";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/kursi_1.png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;

	}

}
