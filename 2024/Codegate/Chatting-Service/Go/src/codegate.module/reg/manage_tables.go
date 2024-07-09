/* manage_tables.go */
package reg

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"strings"

	"codegate.module/structure"
	"github.com/google/uuid"
)

func Checker(db *sql.DB, data structure.User, choice int) string {
	switch choice {
	case 1:
		var session string
		err := db.QueryRow("SELECT session FROM register WHERE username = $1", data.Id).Scan(&session)
		if err != nil {
			fmt.Printf("Checker Case 1 err ==> %v\n", err)
		}
		return session
	case 2:
		var username string
		err := db.QueryRow("SELECT username FROM register WHERE session = $1", data.Session).Scan(&username)
		if err != nil {
			fmt.Printf("Checker Case 2 err ==> %v\n", err)
		}
		return username
	case 3:
		var username_flag string
		err := db.QueryRow("SELECT count(username) FROM register WHERE username = $1", data.Id).Scan(&username_flag)
		if err != nil {
			fmt.Printf("Checker Case 3 err ==> %v\n", err)
		}
		return username_flag
	case 4:
		var session_enable_flag string
		err := db.QueryRow("SELECT count(session_enable) FROM register WHERE username = $1 and (session_enable=0 or session_enable=-1)", data.Id).Scan(&session_enable_flag)
		if err != nil {
			fmt.Printf("Checker Case 4 err ==> %v\n", err)
		}
		return session_enable_flag
	case 5:
		var session_compare_flag string
		err := db.QueryRow("SELECT count(session) FROM register WHERE username = $1 and session = $2", data.Id, data.Session).Scan(&session_compare_flag)
		if err != nil {
			fmt.Printf("Checker Case 5 err ==> %v\n", err)
		}
		return session_compare_flag
	case 6:
		var persistence_flag string
		err := db.QueryRow("SELECT count(username) FROM register WHERE session = $1 and session_enable=1 and username=$2", data.Session, data.Id).Scan(&persistence_flag)
		if err != nil {
			fmt.Printf("Checker Case 6 err ==> %v\n", err)
		}
		return persistence_flag
	case 7:
		var hidden_flag string
		err := db.QueryRow("SELECT hidden_open FROM register WHERE username=$1", data.Id).Scan(&hidden_flag)
		if err != nil {
			fmt.Printf("Checker Case 7 err ==> %v\n", err)
		}
		fmt.Printf("hidden_flag : %v\n", hidden_flag)

		return hidden_flag
	}
	return "NULL"
}

func ClearUser(w http.ResponseWriter, r *http.Request, db *sql.DB, data structure.User) int {
	cleanQuery := `
    UPDATE register
    SET session_enable=-1, hidden_open=0
    WHERE username = $1
    `

	cleanChatQuery := `
    DELETE FROM chat 
    WHERE user_id IN (
        SELECT id 
        FROM register 
        WHERE username = $1
    )
    `
	cleanChatLogQuery := `
    DELETE FROM chat_log 
    WHERE sender_id IN (
        SELECT session 
        FROM register 
        WHERE username = $1
    )
    `

	stmt, err := db.Prepare(cleanQuery)
	if err != nil {
		log.Printf("Error preparing updateQuery: %v\n", err)
		fmt.Printf("[ClearUser #1] Error preparing updateQuery: %v\n", err)
		return 0
	}

	defer stmt.Close()
	fmt.Printf("[ClearUser #2] username : %v\n", data.Id)

	_, err = stmt.Exec(data.Id)
	if err != nil {
		fmt.Printf("Error executing updateQuery : %v\n", err)
		return -1
	}

	stmt2, err2 := db.Prepare(cleanChatQuery)
	if err2 != nil {
		log.Printf("Error preparing updateQuery: %v\n", err2)
		fmt.Printf("[ClearUser #3] Error preparing updateQuery: %v\n", err2)
		return 0
	}

	defer stmt2.Close()
	_, err = stmt2.Exec(data.Id)
	if err != nil {
		fmt.Printf("Error executing updateQuery : %v\n", err)
		return -1
	}

	stmt3, err3 := db.Prepare(cleanChatLogQuery)
	if err3 != nil {
		log.Printf("Error preparing updateQuery: %v\n", err3)
		fmt.Printf("[ClearUser #4] Error preparing updateQuery: %v\n", err3)
		return 0
	}

	defer stmt3.Close()
	_, err = stmt3.Exec(data.Id)
	if err != nil {
		fmt.Printf("Error executing updateQuery : %v\n", err)
		return -1
	}

	fmt.Printf("[ClearUser #3] is done\n")

	return 1
}

func UpdateUser(w http.ResponseWriter, r *http.Request, db *sql.DB, data structure.User) int {
	var flag int
	var err error
	var stmt *sql.Stmt

	log.Printf("[DEBUG] Inside the UpdateUser function\n")
	fmt.Printf("[UpdateUser] [DEBUG] Inside the UpdateUser function\n")

	checkQuery := `
    SELECT session_enable 
    FROM register
    WHERE username=$1 and password=$2 
    `

	alterChatTableDisableQuery := `
    ALTER TABLE chat DROP CONSTRAINT IF EXISTS chat_room_session_fkey; 
    ALTER TABLE chat_log DROP CONSTRAINT IF EXISTS chat_log_sender_id_fkey;
    `

	alterChatTableEnableQuery := `
    ALTER TABLE chat_log ADD CONSTRAINT chat_log_sender_id_fkey FOREIGN KEY (sender_id) REFERENCES register(session);
    ALTER TABLE chat ADD CONSTRAINT chat_room_session_fkey FOREIGN KEY (room_session) REFERENCES register(session);
    `

	updateChatTableQuery := `
    UPDATE chat 
    SET room_session=$1
    WHERE user_id IN (select id from register where username=$2 and password=$3)
    `

	updateQuery := `
        UPDATE register 
        SET session = $1, session_enable=1 
        WHERE username = $2 and password = $3
    `

	_, err = db.Exec(alterChatTableDisableQuery)
	if err != nil {
		log.Printf("Error alter foreign key: %v\n", err)
		fmt.Printf("[alterChatTableDisableQuery] Error alter foreign key: %v\n", err)
		return -1
	}
	fmt.Printf("[+] alterChatTableDisableQuery is passed\n")

	err = db.QueryRow(checkQuery, data.Id, data.Password).Scan(&flag)
	if err != nil {
		fmt.Printf("[UpdateUser #1] Error checkQuery: %v\n", err)
	}
	fmt.Printf("[UpdateUser] session_enable value : %v\n", flag)
	fmt.Printf("[UpdateUser] data.Id %v\n", data.Id)

	stmt, err = db.Prepare(updateChatTableQuery)
	if err != nil {
		log.Printf("[updateChatTableQuery] Error preparing updateQuery: %v\n", err)
		fmt.Printf("[UpdateUser #1] Error preparing updateQuery: %v\n", err)
		return -2
	}
	fmt.Printf("[+] updateChatTableQuery is passed\n")

	defer stmt.Close()
	log.Printf("[NEW SESSION] [%v] [ID:%v]/[PW:%v]\n", data.Session, data.Id, data.Password)
	_, err = stmt.Exec(data.Session, data.Id, data.Password)
	if err != nil {
		fmt.Printf("[updateChatTableQuery] Error executing updateQuery : %v\n", err)
	}

	uuid, _ := uuid.NewRandom()
	uuidString := uuid.String()

	comp_session, err := r.Cookie("Session")
	fmt.Printf("[+] before session : %v\n", comp_session)
	fmt.Printf("[+] new session : %v\n", uuidString)
	if comp_session.Value != uuidString {
		flag = 0
	}

	if flag == 0 || flag == -1 {
		debug_cookie := http.Cookie{
			Name:     "UserName",
			HttpOnly: true,
			Value:    data.Id,
		}
		debug_cookie2 := http.Cookie{
			Name:     "Session",
			HttpOnly: true,
			Value:    uuidString,
		}
		w.Header().Set("Set-Cookie", debug_cookie.String())
		w.Header().Add("Set-Cookie", debug_cookie2.String())

		data.Session = uuidString

		stmt2, err := db.Prepare(updateQuery)
		if err != nil {
			log.Printf("Error preparing updateQuery: %v\n", err)
			fmt.Printf("[UpdateUser #1] Error preparing updateQuery: %v\n", err)
			return -3
		}
		fmt.Printf("[+] updateQuery is passed\n")
		defer stmt2.Close()
		log.Printf("[After comparaison -- NEW SESSION] [%v] [ID:%v]/[PW:%v]\n", data.Session, data.Id, data.Password)

		_, err = stmt2.Exec(data.Session, data.Id, data.Password)
		if err != nil {
			fmt.Printf("[updateQuery] Error executing updateQuery : %v\n", err)
		}

		_, err = db.Exec(alterChatTableEnableQuery)
		if err != nil {
			log.Printf("Error alter foreign key: %v\n", err)
			fmt.Printf("[alterChatTableEnableQuery] Error alter foreign key: %v\n", err)
			return -4
		}

		return 1
	} else {
		return 1
	}
}

func Hidden(db *sql.DB, data structure.User, flag int) {
	updateQuery := `
    UPDATE register 
    SET hidden_open=1
    WHERE username=$1
    `

	updateQuery2 := `
    UPDATE register 
    SET hidden_open=0
    WHERE username=$1
    `

	if flag == 1 {
		stmt, err := db.Prepare(updateQuery)
		if err != nil {
			log.Printf("Error preparing updateQuery: %v\n", err)
			fmt.Printf("[HiddenOpen #1] Error preparing updateQuery: %v\n", err)
		}

		defer stmt.Close()
		fmt.Printf("[HiddenOpen #1] username : %v\n", data.Id)

		_, err = stmt.Exec(data.Id)
		if err != nil {
			fmt.Printf("Error executing updateQuery : %v\n", err)
		}
	}
	if flag == 0 {
		stmt, err := db.Prepare(updateQuery2)
		if err != nil {
			log.Printf("Error preparing updateQuery: %v\n", err)
			fmt.Printf("[HiddenOpen #2] Error preparing updateQuery: %v\n", err)
		}

		defer stmt.Close()
		fmt.Printf("[HiddenOpen #2] username : %v\n", data.Id)

		_, err = stmt.Exec(data.Id)
		if err != nil {
			fmt.Printf("Error executing updateQuery : %v\n", err)
		}
	}

}

func CheckSessionCounter(db *sql.DB, data structure.User) (int, int) {
	log.Printf("[CheckSessionCounter] [DEBUG] Inside the UpdateUser function\n")

	checkSessionCountQuery := `
        SELECT session_enable FROM register where username = $1
    `

	stmt, err := db.Prepare(checkSessionCountQuery)
	if err != nil {
		log.Printf("Error preparing updateQuery: %v\n", err)
		return 0, 0
	}
	defer stmt.Close()

	_, err = stmt.Exec(data.Id)
	if err != nil {
		log.Printf("Error executing updateQuery: %v\n", err)
		return 0, 0
	} else {
		//  log.Printf("[+++++++++++++] %v\n",data)
		log.Printf("[DEBUG] Update DB is done\n")
	}
	return 1, 1
}

func IsUser(db *sql.DB, data structure.User) int {
	var count int

	log.Printf("[DEBUG] Inside the SelectUser function\n")

	checkExistValQuery := `
        SELECT COUNT(username) FROM register WHERE username = $1
    `

	checkExistValQuery2 := `
        SELECT COUNT(password) FROM register WHERE password = $1
    `

	stmt, err := db.Prepare(checkExistValQuery)
	if err != nil {
		log.Printf("Error preparing checkExistValQuery: %v\n", err)
		return 0
	}
	defer stmt.Close()

	err = stmt.QueryRow(data.Id).Scan(&count)
	if err != nil && err != sql.ErrNoRows {
		log.Printf("Error querying username count: %v\n", err)
		return 0
	} else {
		count += 1
	}

	stmt2, err := db.Prepare(checkExistValQuery2)
	if err != nil {
		log.Printf("Error preparing checkExistValQuery: %v\n", err)
		return 0
	}
	defer stmt2.Close()

	err = stmt2.QueryRow(data.Password).Scan(&count)
	fmt.Printf("[+] User Query count %v\n", count)

	if err != nil && err != sql.ErrNoRows {
		log.Printf("Error querying username count: %v\n", err)
		return 0
	} else {
		count += 1
	}

	fmt.Printf("[++] User Query count %v\n", count)
	return 1

}

func InsertUser(db *sql.DB, data structure.User) int {
	fmt.Printf("[InsertUser] Entrance ]\n")

	checkExistValQuery := `
        SELECT COUNT(username) FROM register WHERE username = $1
    `

	insertTableQuery := `
        INSERT INTO register(username, password) 
        VALUES ($1, $2)
    `
	var count int

	stmt, err := db.Prepare(checkExistValQuery)
	if err != nil {
		log.Printf("Error preparing checkExistValQuery: %v\n", err)
		return 0
	}
	defer stmt.Close()

	err = stmt.QueryRow(data.Id).Scan(&count)
	if err != nil && err != sql.ErrNoRows {
		log.Printf("Error querying username count: %v\n", err)
		return 0
	}

	if count > 0 {
		fmt.Printf("%s is already existed\n", data.Id)
		log.Printf("%s is already existed\n", data.Id)
		return -1
	}

	_, err = db.Exec(insertTableQuery, data.Id, data.Password)
	if err != nil {
		log.Printf("Error inserting new member: %v\n", err)
		return 0
	} else {
		log.Printf("[+] Inserted a new member %s/%s\n", data.Id, data.Password)
		fmt.Printf("[+] Inserted a new member %s/%s\n", data.Id, data.Password)
		return 1
	}
}

func AddRoom(db *sql.DB, data structure.AddRoomInfo) int {
	var tmp string
	tmp = "ROOM_"
	tmp += fmt.Sprintf("%d", data.ChatRoomIndex)

	if data.IsReload == 1 {
		fmt.Println("=====================================")
		return 1
	}
	fmt.Println("*************************************")

	insertChatMessageQuery := `
    INSERT INTO chat(user_id, room_id, room_session, created_at) 
    VALUES (
        (select id from register where username=$1),
        ($2),
        (select session from register where username=$1),
        CURRENT_TIMESTAMP 
    )
    `
	_, err := db.Exec(insertChatMessageQuery, data.Username, tmp)
	if err != nil {
		log.Printf("Error inserting new member: %v\n", err)
		fmt.Printf("Error inserting new member: %v\n", err)
		return 0
	}
	return 1
}

func InsertMessage(db *sql.DB, data structure.Message) int {
	fmt.Printf("[InsertMessage] Entrance\n")

	insertChatLogMessageQuery := `
    INSERT INTO chat_log(room_num, message, sender_id, created_at)
    VALUES (
        ($1),
        ($2),
        (select session from register where username=$3),
        CURRENT_TIMESTAMP
    )
    `
	_, err2 := db.Exec(insertChatLogMessageQuery, data.ChatRoomIndex, data.Message, data.Username)
	if err2 != nil {
		log.Printf("Error inserting new member: %v\n", err2)
		fmt.Printf("Error inserting new member: %v\n", err2)
		return 0
	} else {
		log.Printf("[+] Inserted a new chat log %v | %v", data.ChatRoomIndex, data.Message)
		fmt.Printf("[+] Inserted a new chat log %v | %v\n", data.ChatRoomIndex, data.Message)
		return 1
	}
}

func LoadMessage(db *sql.DB, data structure.Session) ([]structure.Loader, error) {
	loadMessageQuery := `
    SELECT room_num, message, created_at 
    FROM chat_log
    WHERE sender_id=$1
    `
	rows, err := db.Query(loadMessageQuery, data.SessionID)
	if err != nil {
		fmt.Printf("Error Message Load: %v\n", err)
		return nil, err
	}
	defer rows.Close()

	var loaders []structure.Loader

	for rows.Next() {
		var loader structure.Loader
		if err := rows.Scan(&loader.ChatID, &loader.Message, &loader.CreatedAt); err != nil {
			fmt.Printf("Error scanning row: %v\n", err)
			return nil, err
		}
		loaders = append(loaders, loader)
		fmt.Printf("[+] Loaders Structure :%v\n", loaders)
	}

	if err := rows.Err(); err != nil {
		fmt.Printf("Error iterating rows: %v\n", err)
		return nil, err
	}

	fmt.Println("[+] Message Loader is succeed")
	return loaders, nil
}

func IsValidRoomManage(db *sql.DB, data structure.AddRoomInfo) int {
	var count int

	fmt.Printf("[IsValidRoomManage] Entrance\n")

	roomValidationQuery := `
    SELECT COUNT(room_id)
    FROM chat 
    WHERE room_session = (SELECT session from register where username=$1)
    `
	stmt, err := db.Prepare(roomValidationQuery)
	if err != nil {
		fmt.Printf("Error occured :%v\n", err)
		return -1
	}
	defer stmt.Close()

	err = stmt.QueryRow(data.Username).Scan(&count)
	if err != nil && err != sql.ErrNoRows {
		fmt.Printf("Error querying : %v\n", err)
		return -1
	} else {
		return count
	}
}

func DeleteMessage(db *sql.DB, data structure.DeleteMessage) int {
	var tmp string
	tmp = "ROOM_"
	tmp += fmt.Sprintf("%d", data.ChatRoomIndex)

	fmt.Printf("[DeleteMessage] Entrance\n")
	fmt.Printf("UserName:%v  | chat room index:%v\n", data.UserName, tmp)

	deleteRoomQuery := `
    DELETE FROM chat
    where room_id=$1 and room_session
    IN (select session from register where username=$2)
    `

	_, err := db.Exec(deleteRoomQuery, tmp, data.UserName)
	if err != nil {
		fmt.Printf("Error delete chat room: %v\n", err)
		return 0
	} else {
		log.Printf("[+] Delete a chatting room")
		fmt.Printf("[+] Delete a chatting room\n")
		return 1
	}

	deleteMessageQuery := `
    DELETE FROM chat_log 
    where room_num=$1 and sender_id 
    IN (select session from register where username=$2)
    `
	_, err2 := db.Exec(deleteMessageQuery, data.ChatRoomIndex, data.UserName)
	if err2 != nil {
		fmt.Printf("Error delete chat log: %v\n", err2)
		return 0
	} else {
		log.Printf("[+] Delete a chatting log")
		fmt.Printf("[+] Delete a chatting log\n")
		return 1
	}
}

func ReloaderChat(w http.ResponseWriter, r *http.Request, db *sql.DB, data structure.User) {

	var roomIDs []string

	reloaderQuery := `
    SELECT room_id
    FROM chat
    WHERE user_id 
    IN (
        SELECT id 
        FROM register
        WHERE username=$1
    )
    `
	rows, err := db.Query(reloaderQuery, data.Id)

	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	defer rows.Close()

	for rows.Next() {
		var roomID string

		if err := rows.Scan(&roomID); err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
		roomID = strings.Replace(roomID, "ROOM_", "", 1)
		roomIDs = append(roomIDs, roomID)
	}

	if err := rows.Err(); err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	jsonResponse, err := json.Marshal(roomIDs)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.WriteHeader(http.StatusOK)
	w.Write(jsonResponse)
}
