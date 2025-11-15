class Requester {
    constructor(url) {
        const clean = (path) => {
          try {
            if (!path) throw new Error("no path");
            let re = new RegExp(/^[A-z0-9\s_-]+$/i);
            if (re.test(path)) {
              // normalize
              let cleaned = path.replaceAll(/\s/g, "");
              return cleaned;
            } else {
              throw new Error("regex fail");
            }
          } catch (e) {
            console.log(e);
            return "dfv";
          }
          };
        url = clean(url);
        this.url = new URL(url, 'https://grandprixheaven-web.2024.ctfcompetition.com/api/get-car/');
      }
    makeRequest() {
        return fetch(this.url).then((resp) => {
            if (!resp.ok){
                throw new Error('Error occurred when attempting to retrieve media data');
            }
            return resp;
        });
    }
  }
