class DB {
    constructor() {
        const dbrequest = indexedDB.open("Files", 1);
        dbrequest.onupgradeneeded = function () {
            let db = dbrequest.result;
            if (!db.objectStoreNames.contains("files")) {
                db.createObjectStore("files", { keyPath: "name" });
                db.createObjectStore("info", { keyPath: "name" });
            }
        };

        this.dbPromise = new Promise((resolve) => {
            dbrequest.onsuccess = function () {
                resolve(dbrequest.result);
            };
        });
    }

    async addFile(file) {
        const db = await this.dbPromise;
        const transaction = db.transaction(["files", "info"], "readwrite");
        const filesdb = transaction.objectStore("files");
        const infodb = transaction.objectStore("info");

        const req = filesdb.put(file);
        return new Promise((resolve) => {
            req.onsuccess = () => {
                const fileInfo = { name: file.name, date: Date.now() };
                const req = infodb.put(fileInfo);
                req.onsuccess = () => {
                    resolve(fileInfo);
                };
                req.onerror = () => {
                    throw new Error("Error while adding a file");
                };
            };
            req.onerror = () => {
                throw new Error("Error while adding a file");
            };
        });
    }

    async getFiles() {
        const db = await this.dbPromise;
        const filesdb = db.transaction("info", "readonly").objectStore("info");
        const req = filesdb.getAll();
        return new Promise((resolve) => {
            req.onsuccess = () => {
                resolve(req.result);
            };
        });
    }

    async getFile(name) {
        const db = await this.dbPromise;
        const filesdb = db.transaction("files", "readonly").objectStore("files");
        const req = filesdb.get(name);
        return new Promise((resolve) => {
            req.onsuccess = () => {
                resolve(req.result);
            };
        });
    }

    async clear() {
        const db = await this.dbPromise;
        const transaction = db.transaction(["files", "info"], "readwrite");
        return new Promise(async (resolve) => {
            const req1 = transaction.objectStore("files").clear(),
                req2 = transaction.objectStore("info").clear();
            await Promise.all([
                new Promise((r) => (req1.onsuccess = r)),
                new Promise((r) => (req2.onsuccess = r)),
            ]);
            resolve(true);
        });
    }
}
