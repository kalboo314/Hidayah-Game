package main;

import entity.Buku1_Easy;
import entity.Buku1_Hard;
import entity.Buku1_Normal;
import entity.Buku2_Easy;
import entity.Buku2_Hard;
import entity.Buku2_Normal;
import entity.NPC_API;
import entity.NPC_Dua;
import entity.NPC_Empat;
import entity.NPC_Satu;
import entity.NPC_Tiga;
import object.OBJ_Kursi;
import object.OBJ_Kursi1;
import object.OBJ_Kursi2;
import object.Pager_Normal;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		gp.obj[0] = new OBJ_Kursi(gp);
		gp.obj[0].worldX = gp.tileSize * 17;
		gp.obj[0].worldY = gp.tileSize * 45;
        
		gp.obj[1] = new Pager_Normal(gp);
		gp.obj[1].worldX = 29 * gp.tileSize;
		gp.obj[1].worldY = 34 * gp.tileSize;   
		
		gp.obj[2] = new OBJ_Kursi1(gp);
		gp.obj[2].worldX = gp.tileSize * 22;
		gp.obj[2].worldY = gp.tileSize * 20;
       
		gp.obj[3] = new OBJ_Kursi2(gp);
		gp.obj[3].worldX = gp.tileSize * 54;
		gp.obj[3].worldY = gp.tileSize * 41;

		gp.obj[4] = new Pager_Normal(gp);
		gp.obj[4].worldX = gp.tileSize * 48;
		gp.obj[4].worldY = gp.tileSize * 33;
	}

	public void setNPC() {

		gp.npc[0] = new NPC_Satu(gp);
		gp.npc[0].worldX = gp.tileSize * 23;
		gp.npc[0].worldY = gp.tileSize * 44;

		gp.npc[1] = new NPC_Dua(gp);
		gp.npc[1].worldX = gp.tileSize * 29;
		gp.npc[1].worldY = gp.tileSize * 19;

		gp.npc[2] = new NPC_Tiga(gp);
		gp.npc[2].worldX = gp.tileSize * 43;
		gp.npc[2].worldY = gp.tileSize * 15;

		gp.npc[3] = new NPC_API(gp);
		gp.npc[3].worldX = gp.tileSize * 74;
		gp.npc[3].worldY = gp.tileSize * 44;

		gp.npc[4] = new NPC_Empat(gp);
		gp.npc[4].worldX = gp.tileSize * 58;
		gp.npc[4].worldY = gp.tileSize * 40;

		gp.npc[5] = new Buku1_Easy(gp);
		gp.npc[5].worldX = gp.tileSize * 30;
		gp.npc[5].worldY = gp.tileSize * 64;
//		
		gp.npc[6] = new Buku2_Easy(gp);
		gp.npc[6].worldX = gp.tileSize * 20;
		gp.npc[6].worldY = gp.tileSize * 58;

		gp.npc[7] = new Buku1_Normal(gp);
		gp.npc[7].worldX = gp.tileSize * 12;
		gp.npc[7].worldY = gp.tileSize * 19;

		gp.npc[8] = new Buku2_Normal(gp);
		gp.npc[8].worldX = gp.tileSize * 50;
		gp.npc[8].worldY = gp.tileSize * 6;

		gp.npc[9] = new Buku1_Hard(gp);
		gp.npc[9].worldX = gp.tileSize * 77;
		gp.npc[9].worldY = gp.tileSize * 47;

		gp.npc[10] = new Buku2_Hard(gp);
		gp.npc[10].worldX = gp.tileSize * 61;
		gp.npc[10].worldY = gp.tileSize * 53;

		gp.npc[11] = new NPC_API(gp);
		gp.npc[11].worldX = gp.tileSize * 51;
		gp.npc[11].worldY = gp.tileSize * 50;
	}
}