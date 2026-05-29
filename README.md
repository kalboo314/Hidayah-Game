# Hidayah Game

Hidayah Game is a Java Swing tile-based educational RPG. The player explores a 2D world, reads learning material from books, talks to NPCs, completes Islamic education quizzes, and collects rewards to progress.

## Screenshots

![Title/game screenshot](image.png)
![Gameplay screenshot](image-1.png)
![Quiz screenshot](image-2.png)

## Features

- 2D tile-based world with explorable school, mosque, and outdoor areas
- NPC dialogue for guidance and progression
- Book-based learning material before quiz challenges
- Books disappear after the player finishes reading them
- Three quiz stages: Easy, Normal, and Hard
- Keys and final reward spawn automatically after passing quiz stages
- Local JSON leaderboard storage with no database server required
- Java Swing UI, keyboard controls, music, and sound effects

## Requirements

| Tool | Version |
|------|---------|
| Java JDK | 21 recommended |
| IDE | IntelliJ IDEA or VS Code |

No MySQL, XAMPP, or JDBC connector is required. Leaderboard data is stored in:

```text
database/leaderboard.json
```

## Running In IntelliJ IDEA

1. Open this project folder in IntelliJ IDEA.
2. Go to `File > Project Structure > Project`.
3. Set `SDK` to JDK 21.
4. Set `Language level` to 21.
5. Make sure `res` is marked as a resources folder.
6. Run `src/main/Main.java`.

## Running From Terminal

From the project root:

```bash
javac -encoding UTF-8 -d out/production/Hidayah-Game -sourcepath src src/main/Main.java
java -cp "out/production/Hidayah-Game;res" main.Main
```

On macOS/Linux, use `:` instead of `;` in the classpath:

```bash
java -cp "out/production/Hidayah-Game:res" main.Main
```

## Controls

| Key | Action |
|-----|--------|
| `W` | Move up |
| `S` | Move down |
| `A` | Move left |
| `D` | Move right |
| `Shift` | Move faster |
| `Enter` | Interact / confirm |
| `Space` | Pause |
| `Escape` | Options menu / back |

## Gameplay Notes

- Read books to learn the material for each quiz.
- After a book's dialogue is finished, that book is removed from the map.
- Start quizzes by interacting with the quiz chairs.
- Passing a quiz with a score of at least 80 automatically spawns the next key or reward.
- Leaderboard scores are saved locally in JSON format.

## Project Structure

```text
Hidayah-Game/
|-- src/
|   |-- main/       Core game loop, UI, input, assets, sound, save data
|   |-- entity/     Player, NPCs, and book entities
|   |-- object/     Keys, diploma, chairs, barriers, and base object class
|   |-- quiz/       Quiz logic and quiz stages
|   `-- tile/       Tile loading and map rendering
|-- res/
|   |-- font/       Game font
|   |-- maps/       Active map data
|   |-- npc/        NPC and book sprites
|   |-- objects/    Object sprites
|   |-- player/     Player sprites
|   |-- sound/      Active music and sound effects
|   `-- tiles/      Active tile sprites
|-- database/
|   `-- leaderboard.json
|-- .idea/          IntelliJ project settings
|-- .vscode/        VS Code settings
`-- README.md
```

## Technology

- Java 21
- Java Swing
- Java Sound API
- JSON file storage

## Authors

- Adidya Darmawan
- Haikal Jamil
- Faroz Syakir
- Lukmanul Hakim

## License

This project is made for educational purposes.
