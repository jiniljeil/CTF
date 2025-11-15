addEventListener("load", (event) => {
  params = new URLSearchParams(window.location.search);
  let requester = new Requester(params.get('F1'));
  try {
    let result = requester.makeRequest();
    result.then((resp) => {
        if (resp.headers.get('content-type') == 'image/jpeg') {
          var titleElem = document.getElementById("title-card");
          var dateElem = document.getElementById("date-card");
          var descElem = document.getElementById("desc-card");
          
          resp.arrayBuffer().then((imgBuf) => {
              const tags = ExifReader.load(imgBuf);
              descElem.innerHTML = tags['ImageDescription'].description;
              titleElem.innerHTML = tags['UserComment'].description;
              dateElem.innerHTML = tags['ICC Profile Date'].description;
          })
        }
    })
  } catch (e) {
    console.log("an error occurred with the Requester class.");
  }
});
