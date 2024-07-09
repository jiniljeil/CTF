/* router.go */
package router

import (
	"fmt"
	"log"
	"os"
	"time"

	"database/sql"
	"encoding/json"
	"io/ioutil"

	"github.com/google/uuid"

	/* http */
	"net/http"
	"text/template"

	"github.com/gorilla/mux"

	/* custom */
	"codegate.module/reg"
	"codegate.module/structure"
)

type MainPage struct {
	Message string
}

type NotFoundPage struct {
	Message string
}

type SignupPage struct {
	Message string
}

type HiddenPage struct {
	Message string
}

var notFoundFullPath string
var signupFullPath string
var chatFullPath string
var hiddenFullPath string

var store = 0

const mainTemplate = "/template/main.html"
const notFoundTemplate = "/template/404.html"
const signupTemplate = "/template/signup.html"
const chatTemplate = "/template/chat.html"
const hiddenTemplate = "/template/hidden.html"

type UserMessage interface {
	GetUserDetails() string
}

func InitLog() {
	var err error
	logfile, err := os.OpenFile("access.log", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0644)

	if err != nil {
		log.Fatal(err)
	}

	log.SetOutput(logfile)
}

func Init(db *sql.DB) {
	r := mux.NewRouter()

	InitLog()

	r.NotFoundHandler = http.HandlerFunc(NotFoundHandler)

	r.HandleFunc("/", func(w http.ResponseWriter, r *http.Request) {
		MainHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/signup", func(w http.ResponseWriter, r *http.Request) {
		SignupHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/chat_entrance", func(w http.ResponseWriter, r *http.Request) {
		ChatEntranceHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/api/userlist", func(w http.ResponseWriter, r *http.Request) {
		DropDownHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/api/addRoom", func(w http.ResponseWriter, r *http.Request) {
		AddRoomHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/api/sendMessage", func(w http.ResponseWriter, r *http.Request) {
		MessageHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/api/loadMessage", func(w http.ResponseWriter, r *http.Request) {
		LoadMessageHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/api/deleteRoom", func(w http.ResponseWriter, r *http.Request) {
		DeleteRoomHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/api/validate", func(w http.ResponseWriter, r *http.Request) {
		ValidateHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/api/logout", func(w http.ResponseWriter, r *http.Request) {
		LogoutHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/api/reload", func(w http.ResponseWriter, r *http.Request) {
		ReloadHandler(w, r, db)
	}).Schemes("http")

	r.HandleFunc("/api/hidden", func(w http.ResponseWriter, r *http.Request) {
		HiddenHandler(w, r, db)
	}).Schemes("http")

	err := http.ListenAndServe("0.0.0.0:7777", r)
	if err != nil {
		log.Fatal(err)
	}
}

func Strcat(temp string) string {
	path, _ := os.Getwd()
	fullPath := path
	fullPath += temp
	return fullPath
}

func NotFoundHandler(w http.ResponseWriter, r *http.Request) {
	page := NotFoundPage{
		Message: "페이지를 찾을 수 없습니다.",
	}

	notFoundFullPath := Strcat(notFoundTemplate)

	notFoundPageTemplate, err := template.ParseFiles(notFoundFullPath)
	if err != nil {
		log.Printf("[ 404 ] %s", notFoundFullPath)
		http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		return
	}

	err = notFoundPageTemplate.Execute(w, page)
	if err != nil {
		w.WriteHeader(http.StatusNotFound)
		log.Printf("[404] %s - %s - %s\n", r.RemoteAddr, r.URL.Path, time.Now())
		http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		return
	}
}

func MainHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	var ret int
	var username string
	var flag string

	mainFullPath := Strcat(mainTemplate)

	if r.Method == "GET" {
		page := MainPage{
			Message: "Welcome",
		}

		username, err := r.Cookie("UserName")
		if err != nil {

			mainPageTemplate, err := template.ParseFiles(mainFullPath)
			if err != nil {
				http.Error(w, "Internal Server Error", http.StatusInternalServerError)
				return
			}
			err = mainPageTemplate.Execute(w, page)
			if err != nil {
				http.Error(w, "Internal Server Error", http.StatusInternalServerError)
				return
			}
		}
		if len(username.Value) == 0 {
			fmt.Fprint(w, "ERROR")
		} else {
			comp_session, err := r.Cookie("Session")

			if err != nil {
				fmt.Printf("[+] 쿠키 Session 에러 : %v\n", err)
				mainPageTemplate, err := template.ParseFiles(mainFullPath)

				if err != nil {
					http.Error(w, "Internal Server Error", http.StatusInternalServerError)
					return
				}

				err = mainPageTemplate.Execute(w, page)
				if err != nil {
					http.Error(w, "Internal Server Error", http.StatusInternalServerError)
					return
				}
			}

			if len(comp_session.Value) == 0 {
				fmt.Fprint(w, "Session is empty")
			}

			userData := structure.User{
				Id:      username.Value,
				Session: comp_session.Value,
			}

			flag = reg.Checker(db, userData, 6)

			if flag == "1" {
				http.Redirect(w, r, "/chat_entrance", http.StatusSeeOther)
			} else {
				mainPageTemplate, err := template.ParseFiles(mainFullPath)
				if err != nil {
					http.Error(w, "Internal Server Error", http.StatusInternalServerError)
					return
				}
				err = mainPageTemplate.Execute(w, page)
				if err != nil {
					http.Error(w, "Internal Server Error", http.StatusInternalServerError)
					return
				}
			}

		}
	} else if r.Method == "POST" { // Sign in
		err := r.ParseForm()
		if err != nil {
			http.Error(w, "Bad Request", http.StatusBadRequest)
			return
		}

		username = r.FormValue("username")
		password := r.FormValue("password")
		data := structure.User{
			ID_num:   7777,
			Id:       username,
			Password: password,
			Session:  "NULL",
		}

		if len(username) == 0 || len(password) == 0 {
			http.Redirect(w, r, "/", http.StatusSeeOther)
			return
		}

		ret = reg.IsUser(db, data)
		fmt.Printf("ret value: %v\n", ret)

		if ret == -1 || ret == 0 {
			http.Redirect(w, r, "/", http.StatusSeeOther)
			return
		}

		if ret == 1 {
			if err != nil {
				http.Error(w, err.Error(), http.StatusInternalServerError)
				return
			}

			data = structure.User{
				ID_num:   7777,
				Id:       username,
				Password: password,
				Session:  "NULL",
			}
			ret = 0

			comp_session, err := r.Cookie("Session")

			if err != nil {
				fmt.Fprint(w, "Please add a cookie name as a 'Session' If you want to play it")
			} else {
				data.Session = comp_session.Value
				ret = reg.UpdateUser(w, r, db, data)

				if ret == -1 {
					fmt.Fprint(w, "alterChatTableDisableQuery ISSUE")
				}
				if ret == -2 {
					fmt.Fprint(w, "updateChatTableQuery ISSUE")
				}
				if ret == -3 {
					fmt.Fprint(w, "updateQuery ISSUE")
				}
				if ret == -4 {
					fmt.Fprint(w, "alterChatTableEnableQuery ISSUE")
				}

				http.Redirect(w, r, "/chat_entrance", http.StatusSeeOther)
			}
		}
	}
}

func SignupHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	signupFullPath := Strcat(signupTemplate)

	if r.Method == "GET" {
		page := SignupPage{
			Message: "Welcome",
		}

		signupPageTemplate, err := template.ParseFiles(signupFullPath)
		if err != nil {
			http.Error(w, "Internal Server Error", http.StatusInternalServerError)
			return
		}

		err = signupPageTemplate.Execute(w, page)
		if err != nil {
			http.Error(w, "Internal Server Error", http.StatusInternalServerError)
			return
		}
	} else if r.Method == "POST" {
		fmt.Printf("[+] 유저 생성 중...\n")
		err := r.ParseForm()
		if err != nil {
			http.Error(w, "Bad Request", http.StatusBadRequest)
			return
		}
		username := r.FormValue("username")
		password := r.FormValue("password")

		fmt.Printf("[+] 유저 생성 중 ID %v\n", username)
		fmt.Printf("[+] 유저 생성 중 PW %v\n", password)

		data := structure.User{
			ID_num:   7777,
			Id:       username,
			Password: password,
		}

		if len(username) == 0 || len(password) == 0 {
			http.Redirect(w, r, "/signup", http.StatusSeeOther)
			return
		}

		ret := reg.InsertUser(db, data)
		fmt.Printf("[+] 유저 생성 결과 값 반환: %v\n", ret)
		if ret == -1 || ret == 0 {
			fmt.Fprint(w, "User is already existed")
			return
		}

		if ret == 1 {
			uuid, _ := uuid.NewRandom()
			uuidString := uuid.String()
			expiration := time.Now().Add(365 * 24 * time.Hour)
			cookie := http.Cookie{Name: "UserName", Value: username, Expires: expiration, Path: "/"}
			cookie2 := http.Cookie{Name: "Session", Value: uuidString, Expires: expiration, Path: "/"}
			// Name:"Session", Value: uuidString,
			fmt.Printf("[+] 유저 쿠키 정보 %v\n", cookie)
			fmt.Printf("[+] 유저 쿠키 정보 %v\n", cookie2)

			http.SetCookie(w, &cookie)
			http.SetCookie(w, &cookie2)

			http.Redirect(w, r, "/", http.StatusSeeOther)
			return
		}
	}
}

func ChatEntranceHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	var flag string

	fmt.Printf("[ChatEntranceHandler] Entrance \n")

	chatFullPath := Strcat(chatTemplate)
	chatPageTemplate, err := template.ParseFiles(chatFullPath)
	if err != nil {
		http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		return
	}

	username, err := r.Cookie("UserName")
	if err != nil {
		fmt.Fprint(w, "Please check the cookie")
		return
	}

	sessionStored, err := r.Cookie("Session")
	if err != nil {
		fmt.Fprint(w, "Please check the cookie")
		return
	}

	userData := structure.User{
		Id: username.Value,
	}

	flag = reg.Checker(db, userData, 3)

	if flag == "0" {
		http.Redirect(w, r, "/error", http.StatusSeeOther)
	}

	flag = reg.Checker(db, userData, 4)

	if flag == "1" {
		http.Redirect(w, r, "/", http.StatusSeeOther)
	}
	if flag == "0" {
		userData := structure.User{
			Id:      username.Value,
			Session: sessionStored.Value,
		}
		if err != nil {
			fmt.Fprint(w, "Please check the cookie")
			return
		}
		flag = reg.Checker(db, userData, 5)
		if flag == "1" {
			err = chatPageTemplate.Execute(w, userData)
		}
		if err != nil {
			return
		}
	}
}

func AddRoomHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	fmt.Printf("[AddRoomHandler] Entrance\n")

	if r.Method != http.MethodPost {
		http.Error(w, "Method Not Allowed", http.StatusMethodNotAllowed)
		return
	}

	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		http.Error(w, "Failed to read request body", http.StatusInternalServerError)
		fmt.Printf("Error reading request body: %v\n", err)
		return
	}
	data := structure.AddRoomInfo{
		Username:      "NULL",
		ChatRoomIndex: 0,
		IsReload:      -1,
	}
	err = json.Unmarshal(body, &data)

	if err != nil {
		http.Error(w, "Failed to unmarshal request body", http.StatusInternalServerError)
		fmt.Printf("[AddRoomHandler] Error unmarshaling request body: %v\n", err)
		return
	}
	reg.AddRoom(db, data)
}

func MessageHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	var ret int

	fmt.Printf("[Message Handler] Entrance\n")
	if r.Method != http.MethodPost {
		http.Error(w, "Method Not Allowed", http.StatusMethodNotAllowed)
		return
	}

	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		http.Error(w, "Failed to read request body", http.StatusInternalServerError)
		fmt.Printf("Error reading request body: %v\n", err)
		return
	}
	data := structure.Message{
		Username:      "NULL",
		Message:       "NULL",
		ChatRoomIndex: 0,
	}

	err = json.Unmarshal(body, &data)

	if err != nil {
		http.Error(w, "Failed to unmarshal request body", http.StatusInternalServerError)
		fmt.Printf("Error unmarshaling request body: %v\n", err)
		return
	}

	ret = reg.InsertMessage(db, data)
	fmt.Println("ret : %v", ret)
}

func DeleteRoomHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	fmt.Printf("[DeleteRoomHandler] Entrance\n")
	if r.Method != http.MethodPost {
		http.Error(w, "Method Not Allowed", http.StatusMethodNotAllowed)
		return
	}

	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		http.Error(w, "Failed to read request body", http.StatusInternalServerError)
		fmt.Printf("Error reading request body: %v\n", err)
		return
	}
	data := structure.DeleteMessage{
		UserName:      "NULL",
		ChatRoomIndex: 0,
	}

	err = json.Unmarshal(body, &data)

	if err != nil {
		http.Error(w, "Failed to unmarshal request body", http.StatusInternalServerError)
		fmt.Printf("Error unmarshaling request body: %v\n", err)
		return
	}

	result := reg.DeleteMessage(db, data)

	var response structure.DeleteResponse
	if result == 1 {
		response.ReturnVal = 1
		w.WriteHeader(http.StatusOK)
	} else {
		response.ReturnVal = 0
		w.WriteHeader(http.StatusBadRequest)
	}

	jsonResponse, err := json.Marshal(response)
	if err != nil {
		http.Error(w, "Failed to marshal response", http.StatusInternalServerError)
		fmt.Printf("Error marshaling response: %v\n", err)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(jsonResponse)
}

// 미완성
func DropDownHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	fmt.Println("[DropDownHandler] Entrance")

	rows, err := db.Query("SELECT username FROM register")
	if err != nil {
		http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		fmt.Printf("Error querying database: %v\n", err)
		return
	}
	defer rows.Close()

	var userList []string

	for rows.Next() {
		var username string
		if err := rows.Scan(&username); err != nil {
			http.Error(w, "Internal Server Error", http.StatusInternalServerError)
			fmt.Printf("Error scanning rows: %v\n", err)
			return
		}
		userList = append(userList, username)
	}

	if err := rows.Err(); err != nil {
		http.Error(w, "Internal Server Error", http.StatusInternalServerError)
		fmt.Printf("Error iterating rows: %v\n", err)
		return
	}

	json.NewEncoder(w).Encode(userList)
}

// 미완성
func LoadMessageHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	// Work still not performed yet
}

func ValidateHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	var count int
	fmt.Println("[ValidateHandler] Entrance")

	if r.Method != http.MethodPost {
		http.Error(w, "Method Not Allowed", http.StatusMethodNotAllowed)
		return
	}

	body, err := ioutil.ReadAll(r.Body)
	if err != nil {
		http.Error(w, "Failed to read request body", http.StatusInternalServerError)
		fmt.Printf("Error reading request body: %v\n", err)
		return
	}
	data := structure.AddRoomInfo{
		Username:      "NULL",
		ChatRoomIndex: 0,
	}

	err = json.Unmarshal(body, &data)

	if err != nil {
		http.Error(w, "Failed to unmarshal request body", http.StatusInternalServerError)
		fmt.Printf("Error unmarshaling request body: %v\n", err)
		return
	}

	data2 := structure.User{
		Id: data.Username,
	}

	count = reg.IsValidRoomManage(db, data)

	var response structure.CheckRoomCount

	response.ChatRoomCount = count

	if count >= 0 && count <= 9 {
		reg.Hidden(db, data2, 0)
		response.ReturnVal = 1
		w.WriteHeader(http.StatusOK)
	}
	if count == 10 {
		reg.Hidden(db, data2, 0)
		response.ReturnVal = -1
		w.WriteHeader(http.StatusOK)
	}
	if count > 10 {
		reg.Hidden(db, data2, 1)
		response.ReturnVal = 2
		w.WriteHeader(http.StatusOK)
	}

	jsonResponse, err := json.Marshal(response)
	if err != nil {
		http.Error(w, "Failed to marshal response", http.StatusInternalServerError)
		fmt.Printf("Error marshaling response: %v\n", err)
		return
	}

	w.Header().Set("Content-Type", "application/json")
	w.Write(jsonResponse)
}

func HiddenHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	var flag string
	fmt.Println("[HiddenHandler] Entrance")

	username, _ := r.Cookie("UserName")

	Userdata := structure.User{
		Id: username.Value,
	}
	fmt.Printf("Userdata %v\n", Userdata)
	flag = reg.Checker(db, Userdata, 7)

	if flag == "0" {
		http.Error(w, "ACCESS DENIED", http.StatusInternalServerError)
	}

	if flag == "1" {
		if r.Method == "GET" {
			page := HiddenPage{
				Message: "Welcome",
			}
			hiddenFullPath := Strcat(hiddenTemplate)
			hiddenPageTemplate, err := template.ParseFiles(hiddenFullPath)
			if err != nil {
				http.Error(w, "Internal Server Error", http.StatusInternalServerError)
				return
			}
			err = hiddenPageTemplate.Execute(w, page)
			if err != nil {
				http.Error(w, "Internal Server Error", http.StatusInternalServerError)
				return
			}

		} else if r.Method == "POST" {
			err := r.ParseForm()
			if err != nil {
				http.Error(w, "Bad Request", http.StatusBadRequest)
				return
			}
			username := r.FormValue("username")
			session := r.FormValue("session")
			command := r.FormValue("command")

			fmt.Printf("[Hidden] username : %v\n", username)
			fmt.Printf("[Hidden] session : %v\n", session)
			fmt.Printf("[Hidden] command : %v\n", command)
			return
		}
	}
}

func LogoutHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	fmt.Println("[LogoutHandler] Entrance")

	username, _ := r.Cookie("UserName")

	data := structure.User{
		ID_num:   7777,
		Id:       username.Value,
		Password: "NULL",
		Session:  "NULL",
	}

	if username.Value == "" {
		http.Redirect(w, r, "/error", http.StatusSeeOther)
	} else {
		reg.ClearUser(w, r, db, data)

		debug_cookie := http.Cookie{
			Name:     "UserName",
			HttpOnly: true,
			MaxAge:   -1,
			Value:    "NULL",
		}
		w.Header().Add("Set-Cookie", debug_cookie.String())
	}
}

func ReloadHandler(w http.ResponseWriter, r *http.Request, db *sql.DB) {
	fmt.Println("[ReloadHandler] Entrance")
	username, _ := r.Cookie("UserName")

	data := structure.User{
		Id: username.Value,
	}

	reg.ReloaderChat(w, r, db, data)
}
