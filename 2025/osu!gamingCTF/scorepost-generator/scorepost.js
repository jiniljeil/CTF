const execFileAsync = require('util').promisify(require('child_process').execFile);
const gm = require('gm').subClass({ imageMagick: '7+' });
const path = require('path');

// shoutout to https://github.com/wesleyyxie/osu-scorepost/tree/main where i copied most of the assets / logic from :)
const ASSETS_DIR = path.join(__dirname, 'assets');
const ARISTIA_DIR = path.join(ASSETS_DIR, 'Aristia');
const FONT_PATH = path.join(ASSETS_DIR, 'Aller_Lt.ttf');
const SKELETON_PATH = path.join(ASSETS_DIR, 'osu_replay_skeleton.png');

const MOD_IMAGES = {
    'EZ': 'selection-mod-easy@2x.png',
    'NF': 'selection-mod-nofail@2x.png',
    'HT': 'selection-mod-halftime@2x.png',
    'HR': 'selection-mod-hardrock@2x.png',
    'SD': 'selection-mod-suddendeath@2x.png',
    'PF': 'selection-mod-perfect@2x.png',
    'DT': 'selection-mod-doubletime@2x.png',
    'NC': 'selection-mod-nightcore@2x.png',
    'HD': 'selection-mod-hidden@2x.png',
    'FL': 'selection-mod-flashlight@2x.png',
    'RX': 'selection-mod-relax@2x.png',
    'AP': 'selection-mod-relax2@2x.png',
    'SO': 'selection-mod-spunout@2x.png',
};

const RANK_IMAGES = {
    'A': 'Ranking-A@2x.png',
    'B': 'Ranking-B@2x.png',
    'C': 'Ranking-C@2x.png',
    'D': 'Ranking-D@2x.png',
    'S': 'Ranking-S@2x.png',
    'SH': 'Ranking-SH@2x.png',
    'X': 'Ranking-X@2x.png',
    'XH': 'Ranking-XH@2x.png',
    'F': 'Ranking-D@2x.png'
};

function getNumberComposites(text, x, y, size) {
    const numDir = path.join(ARISTIA_DIR, 'num');
    const sizeStr = size.toFixed(1);
    
    const composites = [];
    let offsetX = 0;
    const spacing = Math.round(25 * size);
    
    for (const char of text) {
        let filename, charSpacing = spacing;
        
        if (char >= '0' && char <= '9') {
            filename = `berlin-${char}x${sizeStr}.png`;
        } else if (char === '.') {
            filename = 'berlin-dot.png';
            charSpacing = Math.round(14 * size);
        } else if (char === '%') {
            filename = 'berlin-percent.png';
        } else {
            continue;
        }
        
        composites.push({ path: path.join(numDir, filename), x: x + offsetX, y });
        offsetX += charSpacing;
    }
    
    return composites;
}

async function generateScreenshot(tempDir, bgImagePath, score) {
    const basePath = path.join(tempDir, 'base.png');
    const outputPath = path.join(tempDir, 'output.png');
    
    const size = await new Promise((resolve, reject) => {
        gm(bgImagePath).size((err, size) => {
            if (err) reject(err);
            else resolve(size);
        });
    });
    
    const targetRatio = 16 / 9;
    const currentRatio = size.width / size.height;
    
    let cropGeometry;
    if (currentRatio > targetRatio) {
        const newWidth = Math.round(size.height * targetRatio);
        const offsetX = Math.floor((size.width - newWidth) / 2);
        cropGeometry = `${newWidth}x${size.height}+${offsetX}+0`;
    } else {
        const newHeight = Math.round(size.width / targetRatio);
        const offsetY = Math.floor((size.height - newHeight) / 2);
        cropGeometry = `${size.width}x${newHeight}+0+${offsetY}`;
    }
    
    const title = `${score.beatmapset_artist} - ${score.beatmapset_title} [${score.beatmap_version}]`;
    const creator = `Beatmap by ${score.beatmapset_creator}`;
    const player = `Played by ${score.username} on ${score.created_at}`;
    
    const baseArgs = [
        bgImagePath,
        '-crop', cropGeometry,
        '+repage',
        '-resize', '1920x1080!',
        '-brightness-contrast', '-20,-20',
        '-colorspace', 'RGB',
        SKELETON_PATH,
        '-geometry', '+0+0',
        '-composite',
        '-font', FONT_PATH,
        '-fill', 'white',
        '-pointsize', '45',
        '-annotate', '+10+55', title,
        '-pointsize', '30',
        '-annotate', '+13+90', creator,
        '-annotate', '+13+124', player,
        basePath
    ];
    
    await execFileAsync('convert', baseArgs, { maxBuffer: 50 * 1024 * 1024 });
    
    const compositeArgs = [basePath];
    
    const rankImg = RANK_IMAGES[score.rank] || RANK_IMAGES['D'];
    compositeArgs.push(path.join(ARISTIA_DIR, rankImg), '-geometry', '+1460+260', '-composite');
    
    if (score.mods && score.mods !== 'NM') {
        let offsetX = 1834 - 50;
        for (let i = 0; i < score.mods.length; i += 2) {
            const mod = score.mods.substring(i, i + 2);
            if (MOD_IMAGES[mod]) {
                compositeArgs.push(
                    path.join(ARISTIA_DIR, MOD_IMAGES[mod]),
                    '-geometry', `+${offsetX}+550`,
                    '-composite'
                );
                offsetX -= 50;
            }
        }
    }
    
    const scoreStr = String(score.score).padStart(8, '0');
    const accuracy = `${(score.accuracy * 100).toFixed(2)}%`;
    
    const numberComposites = [
        ...getNumberComposites(scoreStr, 265, 180, 1.8),
        ...getNumberComposites(String(score.count_300), 180, 325, 1.5),
        ...getNumberComposites(String(score.count_100), 180, 455, 1.5),
        ...getNumberComposites(String(score.count_50), 180, 575, 1.5),
        ...getNumberComposites(String(score.count_miss), 608, 575, 1.5),
        ...getNumberComposites(String(score.max_combo), 35, 740, 1.5),
        ...getNumberComposites(accuracy, 440, 740, 1.5)
    ];
    
    if (score.count_geki != null && score.count_katu != null) {
        numberComposites.push(
            ...getNumberComposites(String(score.count_geki), 608, 325, 1.5),
            ...getNumberComposites(String(score.count_katu), 608, 455, 1.5)
        );
    }
    
    for (const { path: imgPath, x, y } of numberComposites) {
        compositeArgs.push(imgPath, '-geometry', `+${x}+${y}`, '-composite');
    }
    
    compositeArgs.push('-quality', '100', outputPath);
    
    await execFileAsync('convert', compositeArgs, { maxBuffer: 50 * 1024 * 1024 });
    return outputPath;
}

module.exports = { generateScreenshot };