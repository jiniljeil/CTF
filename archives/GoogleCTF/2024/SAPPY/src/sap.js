goog.module("sap");

const Uri = goog.require("goog.Uri");

function getHost(options) {
  if (!options.host) {
    const u = Uri.parse(document.location);

    return u.scheme + "://sappy-web.2024.ctfcompetition.com";
  }
  return validate(options.host);
}

function validate(host) {
  const h = Uri.parse(host);
  console.log(h);

  if (h.hasQuery()) {
    throw "invalid host";
  }
  if (h.getDomain() !== "sappy-web.2024.ctfcompetition.com") {
    throw "invalid host";
  }
  return host;
}

function buildUrl(options) {
  return getHost(options) + "/sap/" + options.page;
}

exports = { buildUrl };

window.buildUrl = buildUrl;

const API = { host: location.origin };

const output = document.getElementById("output");

window.addEventListener(
  "message",
  async (event) => {
    let data = event.data;
    if (typeof data !== "string") return;
    data = JSON.parse(data);
    const method = data.method;
    switch (method) {
      case "initialize": {
        if (!data.host) return;
        API.host = data.host;
        break;
      }
      case "render": {
        if (typeof data.page !== "string") return;
        const url = buildUrl({
          host: API.host,
          page: data.page,
        });
        const resp = await fetch(url);
        if (resp.status !== 200) {
          console.error("something went wrong");
          return;
        }

        const json = await resp.json();

        if (typeof json.html === "string") {
          output.innerHTML = json.html;
        }
        break;
      }
    }
  },
  false
);
