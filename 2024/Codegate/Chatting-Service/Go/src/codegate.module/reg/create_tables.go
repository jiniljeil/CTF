/* create_tables.go */

package reg 

import (
	"database/sql"
	"fmt"
	"log"
)

func CleanTable(db *sql.DB) {
	cleanTableQuery := `
    DROP TABLE IF EXISTS chat_log;
    DROP TABLE IF EXISTS chat;
	DROP TABLE IF EXISTS register; 
	DROP SEQUENCE IF EXISTS room_id_seq;
    `
	_,err := db.Exec(cleanTableQuery)
	if err != nil {
		fmt.Println("Clean all tables were failed")
        log.Fatal(err)
	}
	fmt.Println("DB is clear")
}

func CreateRegisterTable(db *sql.DB) {
    createTableQuery := `
    CREATE TABLE IF NOT EXISTS register (
        id SERIAL PRIMARY KEY, 
        username VARCHAR(50) NOT NULL,
		password VARCHAR(50) NOT NULL,
        session VARCHAR(50) UNIQUE,
        session_enable int DEFAULT 0,
        hidden_open     int DEFAULT 0
    );`
    _, err := db.Exec(createTableQuery)
    if err != nil {
        fmt.Println("[1] Create register table was failed")
        log.Fatal(err)
    }
    fmt.Println("Register table created successfully")
}

func CreateChatTable(db *sql.DB) {
    createTableQuery := `
    CREATE TABLE IF NOT EXISTS chat (
        no SERIAL PRIMARY KEY,
        user_id INT REFERENCES register(id),
        room_id VARCHAR(50),
		room_session VARCHAR(50) REFERENCES register(session),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );`
    _, err := db.Exec(createTableQuery)
    if err != nil {
        fmt.Println("Create chat table was failed")
        log.Fatal(err)
    }
    fmt.Println("Chat table created successfully")
}

func CreateChatLogTable(db *sql.DB) {
    createChatTableQuery := `
    CREATE TABLE IF NOT EXISTS chat_log (
        id SERIAL PRIMARY KEY,
        room_num INT,
        message TEXT NOT NULL,
        sender_id VARCHAR(50) REFERENCES register(session),
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );
    `
    _, err := db.Exec(createChatTableQuery)
    if err != nil {
        fmt.Println("Create chat log table was failed")
        log.Fatal(err)
    }
    fmt.Println("Chat log table created successfully")
}
