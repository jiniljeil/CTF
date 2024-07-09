package structure 

import (
	"fmt"
	"time"
)

type Info struct { 
	Host string 
	Port     string
    Username string
    Password string
    DBName   string
}

type User struct {
	ID_num   int
	Id       string
	Password string
	Session string
	Session_enable int
	Hidden_enable int
}

type AddRoomInfo struct {
	Username string `json:"username"`
	ChatRoomIndex int `json:"chatRoomIndex"`
	IsReload int `json:"isReloadFlag"`
}

type Message struct {
	Username      string `json:"username"`
	Message       string `json:"message"`
	ChatRoomIndex int    `json:"chatRoomIndex"`
}

type DeleteMessage struct {
	UserName string `json:"username"`
	ChatRoomIndex int    `json:"chatRoomIndex"`
}

type CheckRoomCount struct { 
	ReturnVal int `json:"returnVal"`
	ChatRoomCount int `json:"chatRoomCount"`
}

type DeleteResponse struct {
    ReturnVal int `json:"returnVal"`
}

type Session struct {
	SessionID string
}

type Loader struct {
    ChatID     int
    Message    string
    CreatedAt  time.Time
}

func (u *User) GetUserDetails() string {
    userDetails := fmt.Sprintf("%s", u.Id)
    return userDetails
}

func (m *Message) GetUserDetails() string {
    return fmt.Sprintf("%d", m.Username)
}
