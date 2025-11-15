1. prototype pollution => XSS 
=> 단서 : `JSON.parse()`

=== GET === 
/ : 사진 파일 가져오기
```javascript
<% if (files) { %>
     // 문자열 디코딩
     // prototype pollution ?
    const fileNames = JSON.parse(atob('<%= files %>'))
    
        for(i=0;i<fileNames.length;i++){
          fileName = fileNames[i]
          const imgElement = document.createElement('img');
          // very safe
          imgElement.src = `/<%= id %>/${fileName}`;

          imgElement.alt = `Image: ${fileName}`;

          galleryDiv.appendChild(imgElement);
        }

  <% } %>
```

=== POST === 
/upload : ./public/${req.cookies.sid}/${uploadedFile.name} 경로에 파일 업로드

/share : `http://localhost:3000/?f=${id}` 방문

```javascript
const urlParams = new URLSearchParams(window.location.search);
  const file = urlParams.get('f');
  if(file){
    const modal = new bootstrap.Modal(document.getElementById('imageModal'));
    const modalImage = document.getElementById('modalImage');
    modalImage.src = file
    modal.show();
  }
```  