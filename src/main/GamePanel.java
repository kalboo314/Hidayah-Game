package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entity.Entity;
import entity.Player;
import object.OBJ_Diploma;
import object.OBJ_Key;
import object.SuperObject;
import quiz.Quiz;
import quiz.quizOne;
import quiz.quizTwo;
import quiz.quizThree;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	public final int originalTileSize = 16; // 16x16 tile
	final int scale = 4;

	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

	// WORLD SETTINGS
	public final int maxWorldCol = 90;
	public final int maxWorldRow = 70;

	// FPS
	int FPS = 60;

	// SYSTEM
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound sound = new Sound();
	Sound sfx = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;

	// ENTITY AND OBJECT
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	public Entity npc[] = new Entity[20];

	// QUIZ
	public Quiz quiz;
	public Quiz question[] = new Quiz[10];
	public quizOne quizOne;
	public quizTwo quizTwo;
	public quizThree quizThree;
	public String playerName = "DefaultPlayer"; // Atau ganti sesuai nama pemain yang diinput saat login
	private HashMap<String, Integer> individualScores = new HashMap<>();
	public int totalScore = 0;

	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialougeState = 3;
	public final int quizState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	public final int gameFinalState = 7;
	public int currentNPCIndex;

	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);

		this.quiz = new quizOne(this, keyH);

	}

	public void setupGame() {

		initializeQuizzes();
		aSetter.setObject();
		aSetter.setNPC();
		gameState = titleState;
	}

	private void initializeQuizzes() {
		if (quiz == null) {
			quiz = new quizOne(this, keyH);
		}
		if (quizOne == null) {
			quizOne = new quizOne(this, keyH);
		}
		if (quizTwo == null) {
			quizTwo = new quizTwo(this, keyH);
		}
		if (quizThree == null) {
			quizThree = new quizThree(this, keyH);
		}
	}

	public void handleMenuSelection(String selection) {
		if ("New Game".equals(selection)) {
			startNewGame();
			// Lanjutkan ke permainan baru
		} else if ("Load Game".equals(selection)) {
			leaderboardGUI();
		}
	}

	public boolean startNewGame() {
		// Inisialisasi objek quiz jika null
		if (quiz == null) {
			quiz = new quizOne(this, keyH); // Atau instance quiz lainnya sesuai logika
		}

		// Meminta nama pemain
		String inputName = JOptionPane.showInputDialog(this, "Masukkan nama Anda:", "Nama Pemain",
				JOptionPane.PLAIN_MESSAGE);

		// Validasi input
		if (inputName == null) {
			return false;
		}
		if (inputName.trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nama tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
			return startNewGame();
		}
		inputName = inputName.trim();

		// Cek apakah nama sudah ada di database
		HashMap<String, Integer> gameData = FileManager.loadGameData();
		if (gameData.containsKey(inputName)) {
//			quiz.totalScore = gameData.get(playerName);
//			totalScore = quiz.totalScore; // Sinkronisasi skor
//			JOptionPane.showMessageDialog(this,
//					"Selamat datang kembali, " + playerName + "! Skor Anda: " + quiz.totalScore, "Informasi",
//					JOptionPane.INFORMATION_MESSAGE);
			
			JOptionPane.showMessageDialog(this, "Nama tidak boleh sama!", "Peringatan", JOptionPane.WARNING_MESSAGE);
			return startNewGame();
		} else {
			playerName = inputName;
			quiz.totalScore = 0;
			totalScore = 0; // Pastikan skor baru diinisialisasi
			JOptionPane.showMessageDialog(this, "Selamat datang, " + playerName + "! Permainan dimulai dari awal.",
					"Informasi", JOptionPane.INFORMATION_MESSAGE);
			resetGame();
			quiz.resetQuiz();
			gameState = playState;
			return true;
		}

	}

	// Reset permainan
	public void resetGame() {
		obj = new SuperObject[10];
		npc = new Entity[20];
		quiz = new quizOne(this, keyH);
		quizOne = new quizOne(this, keyH);
		quizTwo = new quizTwo(this, keyH);
		quizThree = new quizThree(this, keyH);
		aSetter.setObject();
		aSetter.setNPC();
		individualScores.clear();
		Quiz.totalScore = 0;
		totalScore = 0;
		currentNPCIndex = 0;
		quizOne.isQuizFinished = false;
		quizOne.isKeySpawned = false;
		quizTwo.isQuizFinished = false;
		quizTwo.isKeySpawned = false;
		quizThree.isKeySpawned = false;
		quizThree.isQuizFinished = false;
		quizOne.failCounter = 0;
		quizTwo.failCounter = 0;
		quizThree.failCounter = 0;
		player.setDefaultValues();
		keyH.upPressed = false;
		keyH.downPressed = false;
		keyH.leftPressed = false;
		keyH.rightPressed = false;
		keyH.enterPressed = false;
		keyH.shiftPressed = false;
		ui.resetForNewGame();

		// Reset data lainnya
	}

	// Memperbarui skor setelah jawaban benar
	public void updateScore(int points) {
		quiz.totalScore += points;
		System.out.println("Skor diperbarui: " + totalScore);
	}

	// Menyimpan skor pemain
	public void savePlayerScore(int totalScore) {
		if (playerName != null && !playerName.isEmpty()) {
			FileManager.saveGameData(playerName, quiz.totalScore);
			System.out.println("Skor " + quiz.totalScore + " disimpan untuk pemain: " + playerName);
		} else {
			System.out.println("Nama pemain tidak valid, skor tidak dapat disimpan.");
		}
	}

	// Menampilkan leaderboard
	public void leaderboardGUI() {
		HashMap<String, Integer> gameData = FileManager.loadGameData();

		// Mengurutkan data berdasarkan skor (descending)
		List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(gameData.entrySet());
		sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

		// Membuat data untuk JTable
		String[] columnNames = { "Player Name", "Score" };
		String[][] tableData = new String[sortedEntries.size()][2];

		int i = 0;
		for (Map.Entry<String, Integer> entry : sortedEntries) {
			tableData[i][0] = entry.getKey();
			tableData[i][1] = String.valueOf(entry.getValue());
			i++;
		}

		// Membuat JTable dengan data dan kolom
		JTable leaderboardTable = new JTable(new DefaultTableModel(tableData, columnNames));
		leaderboardTable.setEnabled(false); // Nonaktifkan edit data
		leaderboardTable.setRowHeight(30);

		// Membungkus tabel dengan JScrollPane
		JScrollPane scrollPane = new JScrollPane(leaderboardTable);

		// Membuat JFrame untuk menampilkan leaderboard
		JFrame leaderboardFrame = new JFrame("Leaderboard");
		leaderboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		leaderboardFrame.setSize(400, 300);
		leaderboardFrame.setLayout(new BorderLayout());

		// Menambahkan label judul dan tabel ke JFrame
		JLabel titleLabel = new JLabel("Leaderboard", JLabel.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		leaderboardFrame.add(titleLabel, BorderLayout.NORTH);
		leaderboardFrame.add(scrollPane, BorderLayout.CENTER);

		leaderboardFrame.setLocationRelativeTo(null);
		leaderboardFrame.setVisible(true);
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		double drawInterval = 1000000000 / FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;

		while (gameThread != null) {

			// 1 Update : update information such as character position
			update();

			// 2 Draw = draw the screen with the upadated information
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);

				nextDrawTime += drawInterval;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void update() {

		if (gameState == playState) {
			// PLAYER
			player.update();
			// NPC
			for (int i = 0; i < npc.length; i++) {
				if ((npc[i] != null)) {
					npc[i].update();
				}
			}
		}

		// Decrement score counter jika skor masih ditampilkan
		if (quiz != null && quiz.isScoreVisible()) {
			quiz.decrementScoreCounter();
		}

		if (ui.notificationCounter > 0) {
			ui.notificationCounter--;
			if (ui.notificationCounter == 0) {
				ui.notificationText = ""; // Hapus teks setelah durasi selesai
			}
		}
	}

	public void updateIndividualScore(String quizName, int score) {
		individualScores.put(quizName, score); // Simpan skor berdasarkan nama kuis
		updateTotalScore(); // Memperbarui totalScore setelah skor individu diupdate
	}

	private void updateTotalScore() {
		totalScore = individualScores.values().stream().mapToInt(Integer::intValue).sum();
	}

	public int getTotalScore() {
		return totalScore;
	}

	public HashMap<String, Integer> getIndividualScores() {
		return individualScores;
	}

	public void finishCurrentNPCDialogue() {
		if (currentNPCIndex >= 0 && currentNPCIndex < npc.length && npc[currentNPCIndex] != null) {
			String npcClassName = npc[currentNPCIndex].getClass().getSimpleName();
			if (npcClassName.startsWith("Buku")) {
				npc[currentNPCIndex] = null;
			} else {
				npc[currentNPCIndex].resetDialogue();
			}
		}
		gameState = playState;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		// TITLE SCREEN
		if (gameState == titleState) {
			ui.draw(g2);
		}
		// OTHERS
		else {

			// TILE
			tileM.draw(g2);

			// OBJECT
			for (int i = 0; i < obj.length; i++) {
				if (obj[i] != null) {
					obj[i].draw(g2, this);
				}
			}

			// NPC
			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].drawNpcStay(g2);
				}
			}

			// PLAYER
			player.draw(g2);

			// UI
			ui.draw(g2);

			// Display the current score at the top left corner
			if (quiz != null) {
				g2.setColor(Color.WHITE); // Set text color
				g2.setFont(new Font("Arial", Font.BOLD, 24)); // Set font dan ukuran
				g2.drawString("Score: " + getTotalScore(), 10, 30); // Tampilkan totalScore yang diperbarui
			}

			g2.dispose();
		}
	}

	public void startQuiz() {
		quiz.resetQuiz();
		gameState = quizState; // Masuk ke state kuis
	}

	public void spawnKey(int x, int y) {
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] == null) {
				obj[i] = new OBJ_Key(this); // Pastikan menggunakan konstruktor yang menerima GamePanel
				obj[i].worldX = x * tileSize; // Konversi koordinat ke ukuran dunia
				obj[i].worldY = y * tileSize; // Konversi koordinat ke ukuran dunia
				break;
			}
		}
	}
	
	public void spawnDiploma(int x, int y) {
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] == null) {
				obj[i] = new OBJ_Diploma(this); // Pastikan menggunakan konstruktor yang menerima GamePanel
				obj[i].worldX = x * tileSize; // Konversi koordinat ke ukuran dunia
				obj[i].worldY = y * tileSize; // Konversi koordinat ke ukuran dunia
				break;
			}
		}
	}


	public void startNewGame(String playerName) {
		this.playerName = playerName;
		gameState = playState;
	}

	public void playMusic(int i) {

		sound.setFile(i);
		sound.play();
		sound.loop();
	}

	public void stopMusic() {

		sound.stop();
	}

	public void playSFX(int i) {

		sfx.setFile(i);
		sfx.play();
	}
}
