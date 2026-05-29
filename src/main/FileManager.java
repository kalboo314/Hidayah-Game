package main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileManager {
	private static final Path SAVE_FILE = Path.of("database", "leaderboard.json");
	private static final Pattern SCORE_ENTRY_PATTERN = Pattern.compile("\"((?:\\\\.|[^\"])*)\"\\s*:\\s*(\\d+)");

	// Menampilkan data dalam bentuk tabel
	public static void displayGameData() {
		HashMap<String, Integer> gameData = loadGameData();

		System.out.println("+--------------------+-------+");
		System.out.println("|     Player Name    | Score |");
		System.out.println("+--------------------+-------+");

		for (String name : gameData.keySet()) {
			System.out.printf("| %-18s | %-5d |\n", name, gameData.get(name));
		}

		System.out.println("+--------------------+-------+");
	}

	// Memuat data dari file JSON dan mengembalikan dalam bentuk HashMap
	public static HashMap<String, Integer> loadGameData() {
		HashMap<String, Integer> gameData = new HashMap<>();

		if (!Files.exists(SAVE_FILE)) {
			return gameData;
		}

		try {
			String json = Files.readString(SAVE_FILE, StandardCharsets.UTF_8);
			Matcher matcher = SCORE_ENTRY_PATTERN.matcher(json);

			while (matcher.find()) {
				String name = unescapeJson(matcher.group(1));
				int score = Integer.parseInt(matcher.group(2));
				gameData.put(name, score);
			}
		} catch (IOException | NumberFormatException e) {
			System.out.println("Gagal memuat data JSON: " + e.getMessage());
		}

		return gameData;
	}

	// Menyimpan data pemain ke file JSON
	public static void saveGameData(String name, int score) {
		HashMap<String, Integer> gameData = loadGameData();
		int highScore = Math.max(score, gameData.getOrDefault(name, 0));
		gameData.put(name, highScore);

		try {
			Files.createDirectories(SAVE_FILE.getParent());
			Files.writeString(SAVE_FILE, toJson(gameData), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Gagal menyimpan data JSON: " + e.getMessage());
		}
	}

	private static String toJson(HashMap<String, Integer> gameData) {
		StringBuilder json = new StringBuilder();
		TreeMap<String, Integer> sortedGameData = new TreeMap<>(gameData);

		json.append("{\n");
		int index = 0;
		for (Map.Entry<String, Integer> entry : sortedGameData.entrySet()) {
			json.append("  \"")
					.append(escapeJson(entry.getKey()))
					.append("\": ")
					.append(entry.getValue());

			if (index < sortedGameData.size() - 1) {
				json.append(",");
			}
			json.append("\n");
			index++;
		}
		json.append("}\n");

		return json.toString();
	}

	private static String escapeJson(String text) {
		return text.replace("\\", "\\\\").replace("\"", "\\\"");
	}

	private static String unescapeJson(String text) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			char current = text.charAt(i);
			if (current == '\\' && i + 1 < text.length()) {
				char next = text.charAt(i + 1);
				if (next == '\\' || next == '"') {
					result.append(next);
					i++;
					continue;
				}
			}
			result.append(current);
		}

		return result.toString();
	}
}
