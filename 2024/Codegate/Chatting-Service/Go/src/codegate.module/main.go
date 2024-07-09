package main

import (
	/* sql */
	"database/sql"

	_ "github.com/lib/pq"

	/* default */
	"fmt"
	"log"
	"os"

	/* custom */
	// $GOPATH/src is location
	"codegate.module/reg"
	"codegate.module/router"
	"codegate.module/structure"
)

var db *sql.DB

func Init() {
	info := structure.Info{
		Host:     os.Getenv("DB_HOST"),
		Port:     os.Getenv("DB_PORT"),
		Username: os.Getenv("DB_USER"),
		Password: os.Getenv("DB_PASSWORD"),
		DBName:   os.Getenv("DB_NAME"),
	}
	fmt.Println(info)
	psqlInfo := fmt.Sprintf("host=%s port=%s user=%s "+
		"password=%s dbname=%s sslmode=disable",
		info.Host, info.Port, info.Username, info.Password, info.DBName)

	var err error
	db, err = sql.Open("postgres", psqlInfo)
	if err != nil {
		fmt.Println("WHY")
		log.Fatal(err)
	}

	err = db.Ping()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Successfully connected to PostgreSQL database")
}

func main() {
	Init()
	reg.CleanTable(db)
	reg.CreateRegisterTable(db)
	reg.CreateChatTable(db)
	reg.CreateChatLogTable(db)

	user := structure.User{
		ID_num:   8888,
		Id:       "debug",
		Password: "debug",
	}
	reg.InsertUser(db, user)

	router.Init(db)
}
