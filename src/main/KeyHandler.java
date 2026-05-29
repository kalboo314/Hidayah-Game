package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shiftPressed;

	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();

		// TITLE STATE
		if (gp.gameState == gp.titleState) {
			titleState(code);
		}

		// PLAY STATE
		if (gp.gameState == gp.playState) {
			playState(code);
		}

		// PAUSE STATE
		else if (gp.gameState == gp.pauseState) {
			pauseState(code);
		}

		// Dialogue State
		else if (gp.gameState == gp.dialougeState) {
			dialogueState(code);
		}

		// QUIZ STATE
		else if (gp.gameState == gp.quizState) {
			quizState(code);
		}

		// OPTIONS STATE
		else if (gp.gameState == gp.optionsState) {
			optionsState(code);
		}

		// GAME OVER STATE
		else if (gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		
		else if (gp.gameState == gp.gameFinalState) {
			gameOverState(code);
		}
	}

	public void titleState(int code) {
		if (gp.ui.titleScreenState == 0) {
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if (gp.ui.commandNum < 0) {
					gp.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if (gp.ui.commandNum > 3) {
					gp.ui.commandNum = 3;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gp.ui.commandNum == 0) { // New Game
					// Meminta nama pemain
					String playerName = javax.swing.JOptionPane.showInputDialog(null, "Masukkan nama Anda:", "New Game",
							javax.swing.JOptionPane.QUESTION_MESSAGE);

					if (playerName != null && !playerName.trim().isEmpty()) {
						gp.playerName = playerName.trim(); // Simpan nama pemain
						gp.startNewGame(); // Panggil metode untuk memulai permainan baru
						gp.gameState = gp.playState; // Ubah ke playState
						gp.playMusic(0); // Mulai musik
					} else {
						javax.swing.JOptionPane.showMessageDialog(null, "Nama tidak boleh kosong!", "Peringatan",
								javax.swing.JOptionPane.WARNING_MESSAGE);
					}
				}
				if (gp.ui.commandNum == 1) { // Load Game
					gp.leaderboardGUI(); // Panggil metode untuk memuat data permainan
				}
				if (gp.ui.commandNum == 2) { // Options
					gp.ui.titleScreenState = 1;
				}
				if (gp.ui.commandNum == 3) { // Quit
					System.exit(0);
				}
			}
		} else if (gp.ui.titleScreenState == 1) {
			if (code == KeyEvent.VK_ENTER) {
				gp.ui.titleScreenState = 0;
			}
		}
	}

	public void playState(int code) {
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.optionsState;
		}
		if (code == KeyEvent.VK_SPACE) {
			gp.gameState = gp.pauseState;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if (code == KeyEvent.VK_SHIFT) {
			shiftPressed = true;
		}
	}

	public void welcomeState(int code) {
	    if (code == KeyEvent.VK_ENTER) {
	        gp.gameState = gp.playState; // Pindah ke state bermain
	    }
	}

	public void pauseState(int code) {
		if (code == KeyEvent.VK_SPACE) {
			gp.gameState = gp.playState;
		}
	}

	public void gameOverState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.titleState;
			gp.quiz.failCounter = 0;
			gp.quiz.resetQuiz();
		}
	}

	public void dialogueState(int code) {
		if (code == KeyEvent.VK_ENTER) {
			if (gp.currentNPCIndex < 0 || gp.currentNPCIndex >= gp.npc.length || gp.npc[gp.currentNPCIndex] == null) {
				gp.gameState = gp.playState;
			} else if (gp.npc[gp.currentNPCIndex].hasNextDialogue()) {
				gp.npc[gp.currentNPCIndex].nextDialogue();
			} else {
				gp.finishCurrentNPCDialogue(); // Kembali ke Play State jika dialog selesai
			}
		}
	}

	public void quizState(int code) {
		if (code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = 3;
			}
		}
		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if (gp.ui.commandNum > 3) {
				gp.ui.commandNum = 0;
			}
		}
		if (code == KeyEvent.VK_ENTER) {
			int playerAnswer = gp.ui.commandNum; // Ambil jawaban berdasarkan commandNum
			gp.quiz.checkAnswer(playerAnswer);
			if (!gp.quiz.isFinished()) {
				gp.quiz.displayQuiz(); // Tampilkan pertanyaan berikutnya
			}
		}
	}

	public void optionsState(int code) {
		if (code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
		}
		if (code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		int maxCommandNum = 0;
		switch (gp.ui.subState) {
		case 0:
			maxCommandNum = 4;
			break;
		case 2:
			maxCommandNum = 1;
			break;
		}

		if (code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
//			gp.playSFX(9);
			if (gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if (code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
//			gp.playSFX(9);
			if (gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}

		if (code == KeyEvent.VK_A) {
			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 0 && gp.sound.volumeScale > 0) {
					gp.sound.volumeScale--;
					gp.sound.checkVolume();
//					gp.playSFX(9);
				}
				if (gp.ui.commandNum == 1 && gp.sfx.volumeScale > 0) {
					gp.sfx.volumeScale--;
//					gp.playSFX(9);
				}
			}
		}
		if (code == KeyEvent.VK_D) {
			if (gp.ui.subState == 0) {
				if (gp.ui.commandNum == 0 && gp.sound.volumeScale < 5) {
					gp.sound.volumeScale++;
					gp.sound.checkVolume();
//					gp.playSFX(9);
				}
				if (gp.ui.commandNum == 1 && gp.sfx.volumeScale < 5) {
					gp.sfx.volumeScale++;
//					gp.playSFX(9);
				}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_SHIFT) {
			shiftPressed = false;
		}

	}

}
