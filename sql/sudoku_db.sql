-- Create database
CREATE DATABASE IF NOT EXISTS sudoku_db;
USE sudoku_db;

-- Users table
CREATE TABLE users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50) UNIQUE NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- High scores table
CREATE TABLE high_scores (
  score_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  time_seconds INT,
  difficulty ENUM('EASY','MEDIUM','HARD'),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- Saved games table
CREATE TABLE saved_games (
  save_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  puzzle_text TEXT NOT NULL,
  current_board TEXT NOT NULL,
  time_elapsed INT,
  difficulty ENUM('EASY','MEDIUM','HARD'),
  saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(user_id)
);
