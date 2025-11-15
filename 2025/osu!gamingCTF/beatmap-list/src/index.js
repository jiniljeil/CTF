import Swal from 'sweetalert2';

const $ = document.querySelector.bind(document);
let engine;

if (document.readyState === 'loading') {
  document.addEventListener('DOMContentLoaded', init);
} else {
  init();
}

function init() {
  const iframe = $("iframe[src='/engine.html']");
  
  if (iframe.contentWindow && iframe.contentWindow.document.readyState === 'complete') {
    initEngine(iframe);
  } else {
    iframe.addEventListener('load', () => initEngine(iframe));
  }
}

function initEngine(iframe) {
  engine = iframe.contentWindow;
  setupEventListeners();
  startHandshake();
}

let engineReady = false;
function startHandshake() {
  const handshakeInterval = setInterval(() => {
    if (!engineReady) {
      engine.postMessage({ type: 'ping' }, window.location.origin);
    } else {
      clearInterval(handshakeInterval);
    }
  }, 100);
  
  setTimeout(() => {
    if (!engineReady) {
      clearInterval(handshakeInterval);
      Swal.fire({ icon: 'error', title: 'Engine failed to load', heightAuto: false });
    }
  }, 10000);
}

function setupEventListeners() {
  $(".upload-link").onclick = async (e) => {
    e.preventDefault();
    const { value: file } = await Swal.fire({
      icon: "info",
      title: "Select osz",
      input: "file",
      inputAttributes: {
        "accept": ".osz",
        "aria-label": "Upload osz"
      },
      heightAuto: false
    });

    if (file) {
      const arrayBuffer = await file.arrayBuffer();
      uploadBeatmapSet(arrayBuffer);
    }
  };

  $(".clear-link").onclick = async (e) => {
    e.preventDefault();
    const result = await Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, clear all beatmaps!",
      heightAuto: false
    });

    if (result.isConfirmed) {
      clear();
    }
  };

  $("form.search-form").onsubmit = (e) => {
    e.preventDefault();
    const query = $("input[name=search]").value;

    if (!query) {
      getBeatmapSets();
      return;
    }

    search(query);
  };

  window.onmessage = (e) => {
    if (e.origin !== window.location.origin || e.source !== engine) {
      return;
    }

    const { type, result, error } = e.data;

    if (error) {
      Swal.fire({ icon: "error", title: "Error", text: error, heightAuto: false });
      return;
    }

    switch (type) {
      case "ready":
        engineReady = true;
        getBeatmapSets();
        break;
      
      case "getBeatmapSets":
        loadBeatmapSetListsView(result);
        break;
      
      case "uploadBeatmapSet":
        Swal.fire({ icon: "success", title: "Set uploaded!", heightAuto: false });
        loadBeatmapSetListsView(result);
        break;
      
      case "viewBeatmapSet":
        loadBeatmapSetView(result);
        break;
      
      case "deleteBeatmapSet":
        Swal.fire({ icon: "success", title: "Set deleted!", heightAuto: false });
        $(".beatmapset-container").replaceChildren();
        getBeatmapSets();
        break;
      
      case "search":
        if (result.length === 1) {
          viewBeatmapSet(result[0].beatmapSetId);
          loadBeatmapSetListsView([result[0]]);
        } else {
          loadBeatmapSetListsView(result);
        }
        break;
      
      case "clear":
        $(".beatmapset-container").replaceChildren();
        $(".beatmapsets").replaceChildren();
        Swal.fire({ icon: "success", title: "All beatmaps cleared!", heightAuto: false });
        break;
    }
  };
}

const beatmapSetToImage = (beatmapSet) => {
  const { background, backgroundPath } = beatmapSet;
  const ext = backgroundPath.split(".").pop();
  if (!ext || !/^\w+$/.test(ext)) {
    return "";
  }
  const mimeType = `image/${ext}`;
  return `data:${mimeType};base64,${background.toBase64()}`;
};

const uploadBeatmapSet = (osz) => {
  engine.postMessage({ type: "uploadBeatmapSet", osz }, window.location.origin);
};

const getBeatmapSets = () => {
  engine.postMessage({ type: "getBeatmapSets" }, window.location.origin);
};

const viewBeatmapSet = (beatmapSetId) => {
  engine.postMessage({ type: "viewBeatmapSet", beatmapSetId }, window.location.origin);
};

const deleteBeatmapSet = (beatmapSetId) => {
  engine.postMessage({ type: "deleteBeatmapSet", beatmapSetId }, window.location.origin);
};

const search = (query) => {
  engine.postMessage({ type: "search", query }, window.location.origin);
};

const clear = () => {
  engine.postMessage({ type: "clear" }, window.location.origin);
};

const loadBeatmapSetListsView = async (beatmapSets) => {
  const baseTemplate = $("template.beatmapset-panel-template").content;

  const elements = beatmapSets.map(beatmapSet => {
    const template = baseTemplate.cloneNode(true);
    template.querySelector("div.mapper-name").innerText = `mapped by ${beatmapSet.creator}`;
    template.querySelector("div.artist-name").innerText = `by ${beatmapSet.artist}`;
    template.querySelector("div.title-name").innerText = beatmapSet.title;
    template.querySelector("div.beatmapset-panel").onclick = () => viewBeatmapSet(beatmapSet.beatmapSetId);
    template.querySelector(".beatmapset-cover.beatmapset-cover--full").style.setProperty("--bg", `url("${beatmapSetToImage(beatmapSet)}")`);
    return template;
  });

  if ($(".beatmapsets__content")) {
    $(".beatmapsets__content").replaceChildren(...elements);
  }
};

function getDiffColor(rating) {
  const stops = [
    { value: 0.1, color: '#4290FB' },
    { value: 1.25, color: '#4FC0FF' },
    { value: 2, color: '#4FFFD5' },
    { value: 2.5, color: '#7CFF4F' },
    { value: 3.3, color: '#F6F05C' },
    { value: 4.2, color: '#FF8068' },
    { value: 4.9, color: '#FF4E6F' },
    { value: 5.8, color: '#C645B8' },
    { value: 6.7, color: '#6563DE' },
    { value: 7.7, color: '#18158E' },
    { value: 9, color: '#000000' }
  ];
  
  if (rating < 0.1) return '#AAAAAA';
  if (rating >= 9) return '#000000';
  
  let i = 0;
  while (i < stops.length - 1 && rating > stops[i + 1].value) {
    i++;
  }
  
  const start = stops[i];
  const end = stops[i + 1];
  const factor = (rating - start.value) / (end.value - start.value);
  
  const c1 = parseInt(start.color.slice(1), 16);
  const c2 = parseInt(end.color.slice(1), 16);
  
  const r = Math.round((c1 >> 16) + ((c2 >> 16) - (c1 >> 16)) * factor);
  const g = Math.round(((c1 >> 8) & 0xff) + (((c2 >> 8) & 0xff) - ((c1 >> 8) & 0xff)) * factor);
  const b = Math.round((c1 & 0xff) + ((c2 & 0xff) - (c1 & 0xff)) * factor);
  
  return '#' + ((r << 16) | (g << 8) | b).toString(16).padStart(6, '0');
}

const loadBeatmapSetView = async (beatmapSet) => {
  const template = $("template.beatmapset-template").content.cloneNode(true);
  template.querySelector("div.mapper-name").innerText = `mapped by ${beatmapSet.creator}`;
  template.querySelector("a.artist-name").innerText = `by ${beatmapSet.artist}`;
  template.querySelector("a.title-name").innerText = beatmapSet.title;
  template.querySelector("div.beatmapset-cover").style.setProperty("--bg", `url("${beatmapSetToImage(beatmapSet)}")`);
  template.querySelector("button[title='back']").onclick = () => {
    $(".beatmapset-container").replaceChildren();
  };
  template.querySelector("button[title='delete']").onclick = async () => {
    const result = await Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!",
      heightAuto: false
    });

    if (result.isConfirmed) {
      deleteBeatmapSet(beatmapSet.beatmapSetId);
    }
  };

  const beatmaps = beatmapSet.maps.sort((a, b) => a.difficulty.starRating - b.difficulty.starRating);
  template.querySelector("span.difficulty-name").innerText = beatmaps[0].version;

  const circleDifficulties = beatmaps.map((beatmap, i) => {
    const circleTemplate = template.querySelector("template.beatmap-difficulty-circle-template").content.cloneNode(true);
    circleTemplate.querySelector("a").onclick = () => {
      if ($(".beatmapset-beatmap-picker__beatmap--active")) {
        $(".beatmapset-beatmap-picker__beatmap--active").classList.remove("beatmapset-beatmap-picker__beatmap--active");
      }
      $(".beatmapset-beatmap-picker").children[i].classList.add("beatmapset-beatmap-picker__beatmap--active");
      $(".difficulty-name").innerText = beatmap.version;
      $(".circle-size-fill").style.setProperty("--fill", `${(beatmap.difficulty.circleSize / 10) * 100}%`);
      $(".circle-size").innerText = beatmap.difficulty.circleSize.toFixed(1);
      $(".hp-drain-fill").style.setProperty("--fill", `${(beatmap.difficulty.drainRate / 10) * 100}%`);
      $(".hp-drain").innerText = beatmap.difficulty.drainRate.toFixed(1);
      $(".overall-difficulty-fill").style.setProperty("--fill", `${(beatmap.difficulty.starRating / 10) * 100}%`);
      $(".overall-difficulty").innerText = beatmap.difficulty.starRating.toFixed(1);
      $(".approach-rate-fill").style.setProperty("--fill", `${(beatmap.difficulty.approachRate / 10) * 100}%`);
      $(".approach-rate").innerText = beatmap.difficulty.approachRate.toFixed(1);
      $(".star-rating-fill").style.setProperty("--fill", `${(beatmap.difficulty.starRating / 10) * 100}%`);
      $(".star-rating").innerText = beatmap.difficulty.starRating.toFixed(1);
      $(".osu-direct-url").href = `osu://b/${beatmap.beatmapId}`;
    };
    circleTemplate.querySelector("div.beatmap-icon").style.setProperty("--diff", getDiffColor(beatmap.difficulty.starRating));
    return circleTemplate;
  });
  template.querySelector(".beatmapset-beatmap-picker").replaceChildren(...circleDifficulties);

  $(".beatmapset-container").replaceChildren(template);

  // select first difficulty
  $(".beatmapset-beatmap-picker").querySelector("a").click();
};
