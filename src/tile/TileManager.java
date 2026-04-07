package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {

		this.gp = gp;

		tile = new Tile[200];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/maps/world01.txt");
	}

	public void getTileImage() {

		// setup(0, " ", false);
				setup(1, "lantai_1", false);
				setup(2, "jendela_kelas_1", true);
				setup(3, "pintu_2", false);
				setup(4, "bingkai_1", true);
				setup(5, "pintu_1", false);
				setup(6, "rak buku_1", true);
				// setup(7, " ", true);
				setup(8, "tebing_bawah_4", true);
				setup(9, "tebing_bawah_5", true);
				setup(10, "tebing_kiri_1", true);
				setup(11, "tebing_kiri_2", true);
				setup(12, "tebing_kiri_3", true);
				setup(13, "tebing_kiri_4", true);
				setup(14, "tebing_kiri_5", true);
				setup(15, "tebing_kiri_6", true);
				setup(16, "tebing_kiri_7", true);
				setup(17, "tebing_kanan_1", true);
				setup(18, "tebing_kanan_2", true);
				setup(19, "tebing_kanan_3", true);
				setup(20, "tebing_kanan_4", true);
				setup(21, "tebing_kanan_5", true);
				setup(22, "tebing_kanan_6", true);
				setup(23, "tebing_kanan_7", true);
				setup(24, "tebing_atas_1", true);
				setup(25, "tebing_atas_2", true);
				setup(26, "tebing_atas_3", true);
				setup(27, "tebing_atas_4", true);
				setup(28, "tebing_bawah_1", true);
				setup(29, "tebing_bawah_2", true);
				setup(30, "tebing_bawah_3", true);
				setup(31, "air_wudhu_1", true);
				setup(32, "air_wudhu_2", true);
				setup(33, "karpet_masjid_3", false);
				setup(34, "karpet_masjid_2", false);
				setup(35, "karpet_masjid_1", false);
				setup(36, "airr_atas", true);
				setup(37, "airr_kiri", true);
				setup(38, "airr_kanan", true);
				setup(39, "airr_bawah", true);
				setup(40, "jalan_1", false);
				setup(41, "jalan_2", false);
				setup(42, "jalan_3", false);
				setup(43, "jalan_4", false);
				setup(44, "jalan_5", false);
				setup(45, "jalan_dalam_1", false);
				setup(46, "jalan_dalam_2", false);
				setup(47, "jalan_dalam_3", false);
				setup(48, "jalan_dalam_4", false);
				// setup(49, " ", false);
				setup(50, "pasir_1", false);
				setup(51, "tembok_luar_1", true);
				setup(52, "easy_sign", true);
				setup(53, "normal_sign", true);
				setup(54, "hard_sign", true);
				// setup(55, " ", true);
				setup(56, "kaktus_3", true);
				setup(57, "kaktus_4", true);
				setup(58, "airr_1", true);
				setup(59, "airr_2", true);
				setup(60, "airr_3", true);
				setup(61, "airr_4", true);
				setup(62, "airr_5", true);
				setup(63, "airr_6", true);
				setup(64, "airr_7", true);
				setup(65, "airr_8", true);
				setup(66, "airr_9", true);
				setup(67, "pager_jalan_1", true);
				setup(68, "pager_jalan_2", true);
				setup(69, "pager_jalan_3", true);
				setup(70, "pager_jalan_4", false);
				setup(71, "jalan_luar_1", false);
				setup(72, "jalan_luar_2", false);
				setup(73, "jalan_luar_3", false);
				setup(74, "jalan_luar_4", false);
				// setup(75, " ", true);
				setup(76, "pohon_kelapa_1", true);
				setup(77, "pohon_kelapa_2", true);
				setup(78, "tebing_air_kanan_1", true);
				setup(79, "tebing_air_kanan_2", true);
				setup(80, "tebing_air_kanan_3", true);
				setup(81, "tebing_air_kanan_4", true);
				setup(82, "tebing_air_kanan_5", true);
				setup(83, "tebing_air_kiri_1", true);
				setup(84, "tebing_air_kiri_2", true);
				setup(85, "tebing_air_kiri_3", true);
				setup(86, "tebing_air_kiri_4", true);
				setup(87, "tebing_air_kiri_5", true);
				setup(88, "tebing_air_tengah_1", true);
				setup(89, "tebing_air_tengah_2", true);
				setup(90, "tebing_air_tengah_3", true);
				setup(91, "tebing_air_bawah_1", true);
				setup(92, "tebing_air_bawah_2", true);
				setup(93, "tebing_air_bawah_3", true);
				setup(94, "tebing_air_bawah_4", false);
				setup(95, "tebing_air_bawah_5", false);
				setup(96, "tembok_luar_atas", true);
				setup(97, "tembok_luar_bawah", true);
				setup(98, "tembok_luar_kanan", true);
				setup(99, "tembok_luar_kiri", true);
				// setup(100, " ", true);
				setup(101, "ikan_kecil_mini_1", true);
				setup(102, "ikan_kecil_mini_2", true);
				setup(103, "ikan_salmon_1", true);
				setup(104, "ikan_salmon_2", true);
				setup(105, "ikan_mas_1", true);
				setup(106, "ikan_mas_2", true);
				setup(107, "ikan_dori_1", true);
				setup(108, "ikan_dori_2", true);
				setup(109, "tembok_dalem_1", true);
				setup(110, "tembok_dalem_2", true);
				setup(111, "tembok_dalem_3", true);
				setup(112, "tembok_dalem_4", true);
				setup(113, "tembok_dalem_5", true);
				setup(114, "tembok_dalem_6", true);
				setup(115, "tembok_dalem_7", true);
				setup(116, "tembok_dalem_8", true);
				setup(117, "tembok_dalem_9", true);
				setup(118, "tembok_atas", true);
				setup(119, "tembok_bawah", true);
				setup(120, "tembok_kiri", true);
				setup(121, "tembok_kanan", true);
				setup(122, "sudut_atas_kanan", true);
				setup(123, "sudut_atas_kiri", true);
				setup(124, "sudut_bawah_kanan", true);
				setup(125, "sudut_bawah_kiri", true);
				setup(126, "tiker_1", false);
				setup(127, "tiker_2", false);
				setup(128, "tiker_3", false);
				setup(129, "tiker_4", false);
				setup(130, "tiker_5", false);
				setup(131, "tiker_6", false);
				setup(132, "tiker_7", false);
				setup(133, "tiker_8", false);
				setup(134, "tiker_9", false);
				setup(135, "pager_penutup_1", true);
				setup(136, "pager_penutup_3", false);
				setup(137, "pager_penutup_4", true);
				setup(138, "kursi_kayu_1", true);
				setup(139, "kursi_kayu_2", true);
				setup(140, "kursi_kayu_3", true);
				setup(141, "kursi_kayu_4", true);
				setup(142, "rak buku_atas_kiri", true);
				setup(143, "rak buku_atas_kanan", true);
				setup(144, "rak buku_bawah_kiri", true);
				setup(145, "rak buku_bawah_kanan", true);
				setup(146, "meja_1", true);
				setup(147, "meja_2", true);
				setup(148, "meja_3", true);
				setup(149, "meja_4", true);
				setup(150, "meja_5", true);
				setup(151, "meja_6", true);
				setup(152, "meja_7", true);
				setup(153, "papan tulis_kiri", true);
				setup(154, "papan tulis_kanan", true);
				setup(155, "sajadah_atas_kiri", false);
				setup(156, "sajadah_atas_kanan", false);
				setup(157, "sajadah_bawah_kiri", false);
				setup(158, "sajadah_bawah_kanan", false);
				setup(159, "sajadah_buku_kiri", true);
				setup(160, "sajadah_buku_kanan", true);
	}

	public void setup(int index, String imageName, boolean collision) {

		UtilityTool uTool = new UtilityTool();

		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String filepath) {

		try {
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = br.readLine();

				while (col < gp.maxWorldCol) {

					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();

		} catch (Exception e) {

		}
	}

	public void draw(Graphics2D g2) {

		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
					&& worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
					&& worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
					&& worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}

	}

}
