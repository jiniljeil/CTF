import localforage from 'localforage';
import JSZip from 'jszip';
import { BeatmapDecoder } from 'osu-parsers';
import { StandardRuleset } from 'osu-standard-stable';

window.onload = async () => {
  if (!window.top || window.top.location.origin !== window.location.origin) {
    throw new Error("engine cannot be loaded here");
  }

  const getBeatmapSets = async () => {
    return (await localforage.getItem("beatmapSets")) ?? [];
  };

  const getBeatmapSet = async (beatmapSetId) => {
    return (await getBeatmapSets()).find(s => s.beatmapSetId === beatmapSetId);
  };

  const addBeatmapSet = async (beatmapSet) => {
    const beatmapSets = await getBeatmapSets();

    if (await getBeatmapSet(beatmapSet.beatmapSetId)) {
      throw new Error("a beatmap set already exists with that id");
    }

    beatmapSets.push(beatmapSet);
    localforage.setItem("beatmapSets", beatmapSets);
  };

  const handlers = {
    "getBeatmapSets": async () => {
      return await getBeatmapSets();
    },
    "uploadBeatmapSet": async ({ osz }) => {
      if (!osz || !(osz instanceof ArrayBuffer)) {
        throw new Error("osz must be an ArrayBuffer");
      }

      if (osz.byteLength > 10 * 1024 * 1024) {
        throw new Error("osz is too large");
      }
      
      const jszip = new JSZip();
      const oszZip = await jszip.loadAsync(osz);
      const osuZipFiles = Object.entries(oszZip.files).filter(([name, file]) => name.endsWith(".osu") && file._data.uncompressedSize < 1 * 1024 * 1024);

      if (osuZipFiles.length === 0) {
        throw new Error("invalid number of maps in osz");
      }

      const osuFiles = await Promise.all(osuZipFiles.map(([name]) => jszip.file(name).async("uint8array")));
      const osuMaps = osuFiles.map(map => ({ osu: map, map: (new BeatmapDecoder()).decodeFromBuffer(map) })).filter(data => data.map.originalMode === 0);
      if (osuMaps.length === 0) {
        throw new Error("only standard maps are supported :(");
      }

      if (osuMaps.length > 16) {
        throw new Error("please be serious");
      }

      const beatmapSetId = osuMaps[0].map.metadata.beatmapSetId;
      if (!beatmapSetId) {
        throw new Error("beatmap set id is missing");
      }

      const bgFile = await jszip.file(osuMaps[0].map.events.backgroundPath);
      if (!bgFile || !bgFile._data.uncompressedSize || bgFile._data.uncompressedSize > 4 * 1024 * 1024) {
        throw new Error("invalid background image");
      }

      const beatmapBG = await bgFile.async("uint8array");
      const beatmapSetData = {
        backgroundPath: osuMaps[0].map.events.backgroundPath,
        background: beatmapBG,
        artist: osuMaps[0].map.metadata.artist,
        beatmapSetId: osuMaps[0].map.metadata.beatmapSetId,
        creator: osuMaps[0].map.metadata.creator,
        title: osuMaps[0].map.metadata.title,
        maps: osuMaps.map(m => ({
          beatmapId: m.map.metadata.beatmapId,
          version: m.map.metadata.version,
          osu: m.osu
        }))
      };

      await addBeatmapSet(beatmapSetData);
      return await getBeatmapSets();
    },
    "viewBeatmapSet": async ({ beatmapSetId }) => {
      if (!beatmapSetId || typeof beatmapSetId !== "number") {
        throw new Error("beatmapSetId must be a number");
      }

      const beatmapSet = await getBeatmapSet(beatmapSetId);
      if (!beatmapSet) {
        throw new Error("could not find beatmap set with that id");
      }

      beatmapSet.maps.map(m => {
        const osu = m.osu;
        const beatmap = (new BeatmapDecoder()).decodeFromBuffer(osu);
        const ruleset = new StandardRuleset();
        const standardBeatmap = ruleset.applyToBeatmap(beatmap);
        const difficultyCalculator = ruleset.createDifficultyCalculator(standardBeatmap);
        const difficultyAttributes = difficultyCalculator.calculate();
        difficultyAttributes.circleSize = beatmap.difficulty._CS;
        m.difficulty = difficultyAttributes;
      });

      return beatmapSet;
    },
    "deleteBeatmapSet": async ({ beatmapSetId }) => {
      if (!beatmapSetId || typeof beatmapSetId !== "number") {
        throw new Error("beatmapSetId must be a number");
      }

      const beatmapSets = await getBeatmapSets();
      const index = beatmapSets.findIndex(s => s.beatmapSetId === beatmapSetId);
      if (index === -1) {
        throw new Error("could not find beatmap set with that id");
      }

      beatmapSets.splice(index, 1);
      await localforage.setItem("beatmapSets", beatmapSets);
      return true;
    },
    "search": async ({ query }) => {
      if (!query || typeof query !== "string") {
        throw new Error("query must be a string");
      }

      const beatmapSets = await getBeatmapSets();
      return beatmapSets.filter(s => {
        const q = query.toLowerCase();
        if (s.artist.toLowerCase().includes(q)) {
          return true;
        }
        if (s.creator.toLowerCase().includes(q)) {
          return true;
        }
        if (s.title.toLowerCase().includes(q)) {
          return true;
        }
        if (s.maps.some(m => m.version.toLowerCase().includes(q))) {
          return true;
        }
        return false;
      })
    },
    "clear": async () => {
      await localforage.clear();
      return true;
    }
  };

  window.onmessage = async (e) => {
    const { type } = e.data;
    if (!type || typeof type !== "string") {
      return;
    }

    if (type === 'ping') {
      window.top.postMessage({ type: 'ready' }, window.location.origin);
      return;
    }

    const handler = handlers[type];
    if (!handler) {
      return;
    }

    try {
      const result = await handler(e.data);
      window.top.postMessage({ type, result }, window.location.origin);
    }
    catch (e) {
      window.top.postMessage({ type, error: e.message }, window.location.origin);
    }
  };
};
