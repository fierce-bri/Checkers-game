Java Checkers

A local two-player checkers game built with Java Swing. The project demonstrates object-oriented design, event-driven input, game-state management, move validation, captures, king promotion, status feedback, and win detection.

What the Project Does

Players take turns selecting and moving pieces with the mouse on an 8×8 board. The application validates diagonal movement, prevents control of an opponent's pieces, handles captures, promotes pieces that reach the opposite side of the board, tracks remaining pieces, and reports the winner.

Features

Local two-player gameplay

Mouse-based piece selection and movement

Turn management for black and red players

Move validation and user-facing error messages

Piece capture and remaining-piece tracking

King promotion

Win detection

Java Swing interface with game-status feedback

Optional background-audio controls

What I Implemented

I worked on the Java application structure and the core game systems, including:

Board and square representation

Piece state and king promotion

Mouse-event handling

Turn and movement rules

Capture logic and win conditions

Swing-based rendering and status messages

Technology

Java

Swing and AWT

Object-Oriented Programming

Event-driven programming

Java Sound API

Project Structure

.
├── images/                 # Piece and interface images
├── sound/                  # Audio asset used by the game
├── src/checkers/
│   ├── Board.java          # Board state, move validation, captures and turns
│   ├── GamePanel.java      # Main game panel and audio controls
│   ├── InvalidMoveException.java
│   ├── Main.java           # Application entry point
│   ├── Piece.java          # Piece state and rendering
│   └── Square.java         # Board-square state and rendering
└── docs/
    └── checkers-game.png   # Gameplay screenshot

Requirements

JDK 16 or newer

A desktop environment capable of displaying Java Swing applications

The application uses relative paths for its image and sound assets, so run the commands from the repository root.

Run from the Command Line

Windows PowerShell

New-Item -ItemType Directory -Force out
javac -d out src/checkers/*.java
java -cp out checkers.Main

macOS or Linux

mkdir -p out
javac -d out src/checkers/*.java
java -cp out checkers.Main

Run in an IDE

Clone the repository.

Open or import it as a Java project.

Configure a JDK.

Use src as the source directory.

Run src/checkers/Main.java.

Keep the working directory set to the repository root so the application can locate images/ and sound/.

Current Scope

This version is a local two-player desktop game. Automated tests, computer-controlled opponents, forced-capture rules, and packaged releases are possible future improvements.