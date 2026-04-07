package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Pager_Normal extends SuperObject {

	GamePanel gp;

	public Pager_Normal(GamePanel gp) {

		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/pager_penutup_2.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
