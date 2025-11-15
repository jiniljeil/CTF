package main

import (
   "bytes"
   "fmt"
   "io"
   "io/ioutil"
   "log"
   "net/http"
	"regexp"
	"strings"
   "mime/multipart"
)

type HttpConnection struct {
   Request  *http.Request
   Response *http.Response
}

type HttpConnectionChannel chan *HttpConnection

var connChannel = make(HttpConnectionChannel)

func PrintHTTP(conn *HttpConnection) {
   fmt.Printf("%v %v\n", conn.Request.Method, conn.Request.RequestURI)
   for k, v := range conn.Request.Header {
      fmt.Println(k, ":", v)
   }
   fmt.Println("==============================")
}

type Proxy struct {
}

func NewProxy() *Proxy { return &Proxy{} }

func (p *Proxy) ServeHTTP(wr http.ResponseWriter, r *http.Request) {
   var resp *http.Response
   var err error
   var req *http.Request

   buf, _ := ioutil.ReadAll(r.Body)
   rdr := ioutil.NopCloser(bytes.NewBuffer(buf))
   rdr2 := ioutil.NopCloser(bytes.NewBuffer(buf))
   r.Body = rdr

   client := &http.Client{}

   r.RequestURI = "http://backend:80" + r.RequestURI

   if strings.ToLower(r.Method) != "get" && strings.ToLower(r.Method) != "post" {
      r.Body.Close()
      wr.Write([]byte("Nop"))
      return
   }

   if r.Method == "POST" {
      mr, err := r.MultipartReader()
      if err != nil {
          r.Body.Close()
          fmt.Println("Http request is corrupted.")
          return
      } else {
          var b bytes.Buffer
          w := multipart.NewWriter(&b)
          reuseBody := true
  
          for {
              part, err := mr.NextPart()
              if err == io.EOF {
                  break
              }
              if err != nil {
                  r.Body.Close()
                  wr.Write([]byte("something wrong :("))
                  return
              }
              if part.FileName() != "" {
                  re := regexp.MustCompile(`[^a-zA-Z0-9\.]+`)
                  cleanFilename := re.ReplaceAllString(part.FileName(), "")
                  match, _ := regexp.MatchString(`\.(php|php2|php3|php4|php5|php6|php7|phps|pht|phtm|phtml|pgif|shtml|htaccess|inc|hphp|ctp|module|phar)$`, cleanFilename)
                  if match {
                      r.Body.Close()
                      wr.Write([]byte("WAF XD"))
                      return
                  }
                  partBuffer, _ := ioutil.ReadAll(part);
                  if strings.Contains(string(partBuffer), "<?php") {
                      r.Body.Close()
                      wr.Write([]byte("WAF XD"))
                      return
                  }
              } else {
                  fieldName := part.FormName()
                  fieldValue, _ := ioutil.ReadAll(part)
                  _ = w.WriteField(fieldName, string(fieldValue))
                  reuseBody = false
              }
          }

          if !reuseBody {
              w.Close()
              rdr2 = ioutil.NopCloser(&b)
              r.Header.Set("Content-Type", w.FormDataContentType())
          }
      }
  }  
   req, err = http.NewRequest(r.Method, r.RequestURI, rdr2)

   for name, value := range r.Header {
      if strings.Contains(strings.ToLower(value[0]), "charset") == true || strings.Contains(strings.ToLower(value[0]), "encod") == true {
         r.Body.Close()
         wr.Write([]byte("WAF XD"))
         return
      }
      req.Header.Set(name, value[0])
   }

   resp, err = client.Do(req)
   r.Body.Close()

   if err != nil {
      http.Error(wr, err.Error(), http.StatusInternalServerError)
      return
   }

   conn := &HttpConnection{r, resp}

   for k, v := range resp.Header {
      wr.Header().Set(k, v[0])
   }
   wr.WriteHeader(resp.StatusCode)
   io.Copy(wr, resp.Body)
   resp.Body.Close()

   PrintHTTP(conn)
}

func main() {
   proxy := NewProxy()
   fmt.Println("==============================")
   err := http.ListenAndServe(":8080", proxy)
   if err != nil {
      log.Fatal("ListenAndServe: ", err.Error())

   }
}
