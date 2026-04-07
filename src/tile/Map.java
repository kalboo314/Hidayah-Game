//package tile;
//
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//
//import awt.image.BuffegImage;
//
//import main.GamePanel;
//
//public class Map {
//	
//	GamePanel gp;
//	BufferedImage worldMap[];
//	public boolean miniMapOn = false
//			
//	
//	public Map(GamePanel gp) {
//		super (gp);
//		this.gp = gp;
//		
//		
//	}
//	
//	public void createWorldMap() {
//		worldMap = new BufferedImage[gp.maxMap];
//		int worldMapWidth = gp.tileSize + gp.maxWorldCol;
//		int worldMapHeight = gp.tileSize + gp.maxWorldRow;
//		
//		for(int i = 0; i < gp.maxMap; i++) {
//			worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
//			Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();
//			
//			int col = 0;
//			int row = 0;
//			
//			while(col < go.maxWorldCol && row< maxWorldRow) {
//				int tileNum = mapTileNum[i][col][row];
//				int x = gp.tileSize * col;
//				int y = gp.tileSize * row;
//				g2.drawImage(tile[tileNum].image, x, y, null);
//				
//				col++;
//				if(col == go.maxWorldCol) {
//					col = 0;
//					row++;
//				}
//				
//			}
//		}
//		
//	}
//	
//	
//	public void drawFullMapScreen(Graphics2D g2) {
//		g2.setColor(Color.black);
//		g2.fillRect(0, 0, gp.screenWith, gp.screenHeight);
//		
//		
//		//DRAW MAP
//		int width = 500;
//		int height = 500;
//		int x = gp.screenWidth/2 - width/2;
//		int y = gp.screenHeight/2 - height/2;
//		g2.drawImage(worldMap[gp.currentMap], x, y, x, y, x, y, x, y, gp)
//		
//	}
//	
//	
//	
//	
//	
//	
//}
