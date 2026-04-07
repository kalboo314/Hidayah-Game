package main;

import java.sql.*;
import java.util.HashMap;

public class FileManager {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/game_database";
	private static final String DB_USER = "root"; // Ganti dengan username MySQL Anda
	private static final String DB_PASSWORD = ""; // Ganti dengan password MySQL Anda

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

	// Memuat data dari database dan mengembalikan dalam bentuk HashMap
	public static HashMap<String, Integer> loadGameData() {
		HashMap<String, Integer> gameData = new HashMap<>();
		String query = "SELECT player_name, score FROM leaderboard";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query)) {

			while (resultSet.next()) {
				String name = resultSet.getString("player_name");
				int score = resultSet.getInt("score");
				gameData.put(name, score);
			}
		} catch (SQLException e) {
			System.out.println("Gagal memuat data: " + e.getMessage());
		}
		return gameData;
	}

	// Menyimpan data pemain ke database
	public static void saveGameData(String name, int score) {
		String query = "INSERT INTO leaderboard (player_name, score) VALUES (?, ?) ON DUPLICATE KEY UPDATE score = GREATEST(score, ?)";

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement(query)) {

			statement.setString(1, name);
			statement.setInt(2, score);
			statement.setInt(3, score);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Gagal menyimpan data: " + e.getMessage());
		}
	}
}
