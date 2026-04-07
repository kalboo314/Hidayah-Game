package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import object.OBJ_Key;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	Font pixelFont;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialouge = "";
	public String currentQuestion = "";
	public String notificationText = ""; // Untuk menyimpan teks notifikasi
	public int notificationCounter = 0; // Untuk menghitung durasi notifikasi
	private int charIndex = 0; // Menyimpan indeks karakter yang sedang ditampilkan
	private long lastCharTime = 0; // Waktu terakhir karakter baru ditampilkan
	private final int charDisplayDelay = 50; // Delay antar karakter dalam milidetik
	private boolean isWelcomeMessageDisplayed = false; // Status untuk welcome message
	private int welcomeCharIndex = 0; // Indeks karakter yang sedang ditampilkan untuk welcome message
	private long welcomeLastCharTime = 0; // Waktu terakhir karakter baru ditampilkan
	private final int welcomeCharDisplayDelay = 50; // Delay antar karakter dalam milidetik
	private BufferedImage heartFull;
	private BufferedImage heartBlank;
	
	public boolean inputNameState = false; // Apakah sedang dalam mode input nama
	public String playerName = ""; // Menyimpan nama pemain

	public int commandNum = 0;
	public int titleScreenState = 0;
	int subState = 0;

	public UI(GamePanel gp) {
		this.gp = gp;

		InputStream is = getClass().getResourceAsStream("/font/PixelatedEleganceRegular-ovyAA.ttf");
		try {
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		 // Muat gambar hati
	    try {
	        heartFull = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
	        heartBlank = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void showMessage(String text) {

		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		this.g2 = g2;

		g2.setFont(pixelFont);

		g2.setColor(Color.white);

		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			drawTitleScreen();
		}

		// PLAY STATE
		if (gp.gameState == gp.playState) {
		    // Gambar welcome message jika masih diperlukan
		    if (!isWelcomeMessageDisplayed) {
		        drawWelcomeMessage();
		    }

		    // Gambar hati di pojok kanan atas
		    drawHearts(g2);
		}


		// GAME OVER STATE
		if (gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		
		if (gp.gameState == gp.gameFinalState) {
			drawFinalScreen();
		}

		// PAUSE STATE
		if (gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
		// DIALOUGE STATE
		if (gp.gameState == gp.dialougeState) {
			drawDialougeScreen();
		}

		// OPTIONS STATE
		if (gp.gameState == gp.optionsState) {
			drawOptionsScreen();
		}

		// QUIZ STATE
		if (gp.gameState == gp.quizState) {
			drawQuizScreen();
		}
		if (!notificationText.isEmpty()) {
			g2.setFont(new Font("Arial", Font.BOLD, 20));
			g2.setColor(Color.WHITE);
			int x = gp.screenWidth / 2 - 100; // Sesuaikan posisi
			int y = gp.screenHeight / 2 - 50;
			g2.drawString(notificationText, x, y);
		}
		if (gp.gameState == gp.playState && gp.quiz != null && gp.quiz.isScoreVisible()) {
			g2.setFont(new Font("Arial", Font.BOLD, 30));
			g2.setColor(Color.YELLOW);
			String scoreText = "Skor Akhir Anda: " + gp.quiz.totalScore;
			int x = gp.screenWidth / 2 - 150; // Sesuaikan posisi
			int y = gp.screenHeight / 2;
			g2.drawString(scoreText, x, y);
		}

	}

	
	private void drawHearts(Graphics2D g2) {
	    int maxHearts = 3; // Maksimum jumlah hati
	    int x = gp.screenWidth - 150; // Posisi awal X (pojok kanan atas)
	    int y = 20; // Posisi Y
	    int heartSize = 32; // Ukuran hati

	    for (int i = 0; i < maxHearts; i++) {
	        if (i < maxHearts - gp.quiz.failCounter) {
	            g2.drawImage(heartFull, x + (heartSize + 10) * i, y, heartSize, heartSize, null);
	        } else {
	            g2.drawImage(heartBlank, x + (heartSize + 10) * i, y, heartSize, heartSize, null);
	        }
	    }
	}

	
	public void drawInputNameScreen() {
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

		String text = "Enter Your Name:";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2 - gp.tileSize;
		g2.drawString(text, x, y);

		// Tampilkan input nama
		text = playerName;
		x = getXforCenteredText(text);
		y += gp.tileSize * 2;
		g2.drawString(text, x, y);

		text = "Press ENTER to confirm";
		x = getXforCenteredText(text);
		y += gp.tileSize * 2;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
		g2.drawString(text, x, y);
	}

	public void showNotification(String text) {
		notificationText = text;
		notificationCounter = 120; // Notifikasi akan muncul selama 2 detik (120 frame)
	}

	public void drawTitleScreen() {

		if (titleScreenState == 0) {
			g2.setColor(new Color(0, 0, 0));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

			// TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
			String text = "HIDAYAH";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3;

			// SHADOW
			g2.setColor(Color.gray);
			g2.drawString(text, x + 5, y + 5);

			// MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);

			// IMAGE TITLE
			x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
			y += gp.tileSize;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

			// MENU
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize * 4;
			g2.drawString(text, x, y);
			if (commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "LEADERBOARD";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "HELP";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}

			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if (commandNum == 3) {
				g2.drawString(">", x - gp.tileSize, y);
			}
		}
		if (titleScreenState == 1) {

			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(32F));

			String text = "CONTROL";
			int x = getXforCenteredText(text);
			int y = gp.tileSize * 3;
			g2.drawString(text, x, y);

			text = "W = Forward";
			x = getXforCenteredText(text);
			y += gp.tileSize * 2;
			g2.drawString(text, x, y);

			text = "S = Backward";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);

			text = "A = Left";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);

			text = "D = Right";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);

			text = "> MAIN MENU";
			x = getXforCenteredText(text);
			y += gp.tileSize * 3;
			g2.drawString(text, x, y);
		} else if (titleScreenState == 2) {
			drawInputNameScreen();
		}

	}

	public void drawPauseScreen() {

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;

		g2.drawString(text, x, y);
	}

	public void drawGameOverScreen() {

		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80));
		String text = "GAME OVER";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;
		g2.drawString(text, x, y);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
		text = "> MAIN MENU";
		x = getXforCenteredText(text);
		y += gp.tileSize * 4;
		g2.drawString(text, x, y);

	}
	

	public void drawFinalScreen() {

		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

		g2.setColor(Color.WHITE);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40));
		
		String text = "SELAMAT";
		int x = getXforCenteredText(text);
		int y =gp.tileSize*2;
		g2.drawString(text, x, y);
		
		text = "GAME SELESAI";
		y = gp.screenHeight / 2;
		g2.drawString(text, x, y);
		
		text = "ANDA TELAH LULUS";
		y += gp.tileSize;
		g2.drawString(text, x, y);

		text = "> MAIN MENU";
		y += gp.tileSize * 3;
		g2.drawString(text, x, y);

	}


	public void drawDialougeScreen() {
		// Kotak dialog
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 5;

		drawSubWindow(x, y, width, height);

		// Atur font dan warna teks
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
		g2.setColor(Color.WHITE);

		// Margin teks
		int textX = x + gp.tileSize;
		int textY = y + gp.tileSize;
		int lineHeight = 40; // Jarak antar baris teks

		// Jika teks dialog belum selesai ditampilkan, tambahkan karakter secara
		// bertahap
		if (System.currentTimeMillis() - lastCharTime > charDisplayDelay) {
			if (charIndex < currentDialouge.length()) {
				charIndex++; // Tambahkan satu karakter
				lastCharTime = System.currentTimeMillis();
			}
		}

		// Ambil teks yang sudah siap untuk ditampilkan
		String partialText = currentDialouge.substring(0, Math.min(charIndex, currentDialouge.length()));

		// Bungkus teks agar tidak melebihi lebar kotak dialog
		int textWidth = width - (gp.tileSize * 2); // Ruang untuk teks dalam kotak
		String[] lines = wrapText(partialText, textWidth);

		// Gambar setiap baris teks
		for (String line : lines) {
			g2.drawString(line, textX, textY);
			textY += lineHeight;
		}

		// Reset charIndex jika dialog selesai ditampilkan dan pemain menekan tombol
		if (charIndex == currentDialouge.length() && gp.keyH.enterPressed) {
			charIndex = 0; // Reset animasi
			gp.keyH.enterPressed = false; // Hindari input ganda
			gp.gameState = gp.playState; // Kembali ke state bermain
		}
	}

	
	public void drawWelcomeMessage() {
	    // Kotak dialog
	    int x = gp.tileSize * 2;
	    int y = gp.tileSize / 2;
	    int width = gp.screenWidth - (gp.tileSize * 4); // Lebar kolom
	    int height = gp.tileSize * 5;

	    drawSubWindow(x, y, width, height);

	    // Atur font dan warna teks
	    g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
	    g2.setColor(Color.WHITE);

	    // Margin teks
	    int textX = x + gp.tileSize;
	    int textY = y + gp.tileSize;
	    int lineHeight = 40; // Jarak antar baris teks

	    // Teks welcome message yang disesuaikan
	    String welcomeMessage = "SELAMAT DATANG DI PETUALANGAN HIDAYAH!\n"
	            + "Temukan Bapak/Ibu Guru .\n"
	            + "untuk mendapatkan petunjuk!";

	    // Jika teks belum selesai ditampilkan, tambahkan karakter secara bertahap
	    if (System.currentTimeMillis() - welcomeLastCharTime > welcomeCharDisplayDelay) {
	        if (welcomeCharIndex < welcomeMessage.length()) {
	            welcomeCharIndex++; // Tambahkan satu karakter
	            welcomeLastCharTime = System.currentTimeMillis();
	        }
	    }

	    // Ambil teks yang sudah siap untuk ditampilkan
	    String partialText = welcomeMessage.substring(0, Math.min(welcomeCharIndex, welcomeMessage.length()));

	    // Bungkus teks agar tidak melebihi lebar kotak dialog
	    int textWidth = width - (gp.tileSize * 2); // Ruang untuk teks dalam kotak
	    String[] lines = wrapText(partialText, textWidth);

	    // Gambar setiap baris teks
	    for (String line : lines) {
	        g2.drawString(line, textX, textY);
	        textY += lineHeight;
	    }

	    // Jika semua teks selesai ditampilkan
	    if (welcomeCharIndex == welcomeMessage.length()) {
	        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18));
	        g2.setColor(Color.YELLOW);
	        g2.drawString("Tekan ENTER untuk melanjutkan...", x + width / 6, y + height - gp.tileSize/7);

	        // Tunggu pemain menekan ENTER untuk menutup pesan
	        if (gp.keyH.enterPressed) {
	            gp.keyH.enterPressed = false; // Hindari input ganda
	            isWelcomeMessageDisplayed = true; // Tandai bahwa welcome message sudah selesai
	            gp.gameState = gp.playState; // Masuk ke state bermain
	        }
	    }
	}



	
	// Fungsi untuk membungkus teks berdasarkan lebar maksimal
	private String[] wrapText(String text, int maxWidth) {
		FontMetrics fm = g2.getFontMetrics();
		StringBuilder wrappedText = new StringBuilder();
		String[] words = text.split(" ");
		StringBuilder line = new StringBuilder();

		for (String word : words) {
			// Tambahkan kata ke baris jika masih muat
			if (fm.stringWidth(line + word) < maxWidth) {
				line.append(word).append(" ");
			} else {
				// Tambahkan baris ke teks yang dibungkus
				wrappedText.append(line).append("\n");
				line = new StringBuilder(word).append(" ");
			}
		}
		// Tambahkan baris terakhir
		wrappedText.append(line);

		// Pecah teks menjadi array baris
		return wrappedText.toString().split("\n");
	}

	public void drawQuizScreen() {

		// WINDOW FOR QUESTION PART
		int xQuestion = gp.tileSize * 2;
		int yQuestion = gp.tileSize / 2;
		int widthQuestion = gp.screenWidth - (gp.tileSize * 4);
		int heightQuestion = gp.tileSize * 5;

		drawSubWindow(xQuestion, yQuestion, widthQuestion, heightQuestion);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
		xQuestion += gp.tileSize;
		yQuestion += gp.tileSize;

		for (String line : currentQuestion.split("\n")) {
			g2.drawString(line, xQuestion, yQuestion);
			yQuestion += 40; // Spasi antar baris soal
		}

		// WINDOW FOR ANSWER PART
		int xAnswer = gp.tileSize * 2;
		int yAnswer = gp.tileSize * 6;
		int widthAnswer = gp.screenWidth / 3;
		int heightAnswer = gp.tileSize * 5;

		drawSubWindow(xAnswer, yAnswer, widthAnswer, heightAnswer);

		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
		xAnswer += gp.tileSize;

		yAnswer += gp.tileSize;
		g2.drawString("A. Pilihan a", xAnswer, yAnswer);
		if (commandNum == 0) {
			g2.drawString("> ", xAnswer - 25, yAnswer);
		}

		yAnswer += gp.tileSize;
		g2.drawString("B. Pilihan b", xAnswer, yAnswer);
		if (commandNum == 1) {
			g2.drawString(">", xAnswer - 25, yAnswer);
		}

		yAnswer += gp.tileSize;
		g2.drawString("C. Pilihan c", xAnswer, yAnswer);
		if (commandNum == 2) {
			g2.drawString(">", xAnswer - 25, yAnswer);
		}

		yAnswer += gp.tileSize;
		g2.drawString("D. Pilihan d", xAnswer, yAnswer);
		if (commandNum == 3) {
			g2.drawString(">", xAnswer - 25, yAnswer);
		}

	}

	public void drawOptionsScreen() {
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));

		// SUB WINDOW
		int frameX = gp.tileSize * 4;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize * 8;
		int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);

		switch (subState) {
		case 0:
			options_top(frameX, frameY);
			break;
		case 1:
			options_control(frameX, frameY);
			break;
		case 2:
			options_endGameConfirmation(frameX, frameY);
			break;

		}

		gp.keyH.enterPressed = false;

	}

	public void options_top(int frameX, int frameY) {
		int textX;
		int textY;

		// TITTLE
		String text = "Options";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		textX = frameX + gp.tileSize;
		textY += gp.tileSize;
		// MUSIC
		textY += gp.tileSize;
		g2.drawString("Music", textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
		}

		// SE
		textY += gp.tileSize;
		g2.drawString("SE", textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}

		// CONTROL
		textY += gp.tileSize;
		g2.drawString("Control", textX, textY);
		if (commandNum == 2) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 1;
				commandNum = 0;
			}
		}

		// END GAME
		textY += gp.tileSize;
		g2.drawString("End Game", textX, textY);
		if (commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 2;
				commandNum = 0;
			}
		}

		// BACK
		textY += gp.tileSize * 2;
		g2.drawString("Back", textX, textY);
		if (commandNum == 4) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				gp.gameState = gp.playState;
				commandNum = 0;
			}
		}

		textX = frameX + (int) (gp.tileSize * 4.5);
		textY = frameY + (int) (gp.tileSize * 1.5);
		g2.setStroke(new BasicStroke(3));

		// MUSIC VOLUME
		textY += gp.tileSize;
		int volumeWidth = 35 * gp.sound.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 35);

		// SE VOLUME
		textY += gp.tileSize;
		volumeWidth = 35 * gp.sfx.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 35);
	}

	public void options_control(int frameX, int frameY) {

		int textX;
		int textY;

		// TITLE
		String text = "Control";
		textX = getXforCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);

		// getXforCenteredText(text)//
		textX = frameX + gp.tileSize;
		textY += gp.tileSize * 3;
		g2.drawString("Move", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Pause", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Options", textX, textY);
		textY += gp.tileSize;

		textX = frameX + gp.tileSize * 5;
		textY = frameY + gp.tileSize * 4;
		g2.drawString("WASD", textX, textY);
		textY += gp.tileSize;
		g2.drawString("SPACE", textX, textY);
		textY += gp.tileSize;
		g2.drawString("ESC", textX, textY);
		textY += gp.tileSize;

		// BACK
//		textX =  getXforCenteredText(text);
		textX = frameX + gp.tileSize;
//		textY += gp.tileSize*2.5;
		textY = frameY + gp.tileSize * 9;
		g2.drawString("Back", textX, textY);

		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 2;
			}
		}

	}

	public void options_endGameConfirmation(int frameX, int frameY) {

		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize * 2;

		currentDialouge = "Quit the game \nreturn to the \ntitle screen?";

		for (String line : currentDialouge.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}

		// YES
		String text = "Yes";
		textX = getXforCenteredText(text);
		textY += gp.tileSize * 1.5;
		g2.drawString(text, textX, textY);
		if (commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
				gp.gameState = gp.titleState;

			}
		}

		// NO
		text = "No";
		textX = getXforCenteredText(text);
		textY += gp.tileSize * 3;
		g2.drawString(text, textX, textY);
		if (commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
			if (gp.keyH.enterPressed == true) {
				subState = 0;
				commandNum = 3;

			}
		}

	}

	public void drawSubWindow(int z, int y, int width, int height) {

		Color c = new Color(0, 0, 0, 200);
		g2.setColor(c);
		g2.fillRoundRect(z, y, width, height, 35, 35);

		c = new Color(255, 255, 255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(z + 5, y + 5, width - 10, height - 10, 25, 25);

	}

	public int getXforCenteredText(String text) {

		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		return x;
	}
}
