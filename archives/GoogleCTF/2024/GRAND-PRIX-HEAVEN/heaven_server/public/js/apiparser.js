addEventListener("load", (event) => {
    params = new URLSearchParams(window.location.search);
    let requester = new Requester(params.get('F1'));
    try {
      let result = requester.makeRequest();
      result.then((resp) => resp.json()).then((jsonBody) => {
        var titleElem = document.getElementById("title-card");
        var dateElem = document.getElementById("date-card");
        var descElem = document.getElementById("desc-card");
        var imgElem = document.getElementById("img-card");
    
        titleElem.textContent = `${jsonBody.model} ${jsonBody.make}`;
        dateElem.textContent = jsonBody.createdAt;
        if (jsonBody.img_id != "") {
    	    imgElem.src = `/media/${jsonBody.img_id}`;
	    }
      })
    } catch (e) {
      console.log("an error occurred with the Requester class.");
    }
  });
