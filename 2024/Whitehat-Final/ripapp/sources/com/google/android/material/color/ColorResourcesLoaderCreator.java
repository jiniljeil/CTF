package com.google.android.material.color;

final class ColorResourcesLoaderCreator {
    private static final String TAG = "ColorResLoaderCreator";

    private ColorResourcesLoaderCreator() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0069, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r5.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x006e, code lost:
        r5 = th;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:10:0x0029, B:40:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0074 A[SYNTHETIC, Splitter:B:50:0x0074] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.content.res.loader.ResourcesLoader create(android.content.Context r5, java.util.Map<java.lang.Integer, java.lang.Integer> r6) {
        /*
            java.lang.String r0 = "ColorResLoaderCreator"
            java.lang.String r1 = "Table created, length: "
            r2 = 0
            byte[] r5 = com.google.android.material.color.ColorResourcesTableCreator.create(r5, r6)     // Catch:{ Exception -> 0x0078 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0078 }
            r6.<init>(r1)     // Catch:{ Exception -> 0x0078 }
            int r1 = r5.length     // Catch:{ Exception -> 0x0078 }
            java.lang.StringBuilder r6 = r6.append(r1)     // Catch:{ Exception -> 0x0078 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x0078 }
            android.util.Log.i(r0, r6)     // Catch:{ Exception -> 0x0078 }
            int r6 = r5.length     // Catch:{ Exception -> 0x0078 }
            if (r6 != 0) goto L_0x001e
            return r2
        L_0x001e:
            java.lang.String r6 = "temp.arsc"
            r1 = 0
            java.io.FileDescriptor r6 = android.system.Os.memfd_create(r6, r1)     // Catch:{ all -> 0x0070 }
            if (r6 != 0) goto L_0x0032
            java.lang.String r5 = "Cannot create memory file descriptor."
            android.util.Log.w(r0, r5)     // Catch:{ all -> 0x006e }
            if (r6 == 0) goto L_0x0031
            android.system.Os.close(r6)     // Catch:{ Exception -> 0x0078 }
        L_0x0031:
            return r2
        L_0x0032:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x006e }
            r1.<init>(r6)     // Catch:{ all -> 0x006e }
            r1.write(r5)     // Catch:{ all -> 0x0064 }
            android.os.ParcelFileDescriptor r5 = android.os.ParcelFileDescriptor.dup(r6)     // Catch:{ all -> 0x0064 }
            android.content.res.loader.ResourcesLoader r3 = new android.content.res.loader.ResourcesLoader     // Catch:{ all -> 0x0058 }
            r3.<init>()     // Catch:{ all -> 0x0058 }
            android.content.res.loader.ResourcesProvider r4 = android.content.res.loader.ResourcesProvider.loadFromTable(r5, r2)     // Catch:{ all -> 0x0058 }
            r3.addProvider(r4)     // Catch:{ all -> 0x0058 }
            if (r5 == 0) goto L_0x004f
            r5.close()     // Catch:{ all -> 0x0064 }
        L_0x004f:
            r1.close()     // Catch:{ all -> 0x006e }
            if (r6 == 0) goto L_0x0057
            android.system.Os.close(r6)     // Catch:{ Exception -> 0x0078 }
        L_0x0057:
            return r3
        L_0x0058:
            r3 = move-exception
            if (r5 == 0) goto L_0x0063
            r5.close()     // Catch:{ all -> 0x005f }
            goto L_0x0063
        L_0x005f:
            r5 = move-exception
            r3.addSuppressed(r5)     // Catch:{ all -> 0x0064 }
        L_0x0063:
            throw r3     // Catch:{ all -> 0x0064 }
        L_0x0064:
            r5 = move-exception
            r1.close()     // Catch:{ all -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r1 = move-exception
            r5.addSuppressed(r1)     // Catch:{ all -> 0x006e }
        L_0x006d:
            throw r5     // Catch:{ all -> 0x006e }
        L_0x006e:
            r5 = move-exception
            goto L_0x0072
        L_0x0070:
            r5 = move-exception
            r6 = r2
        L_0x0072:
            if (r6 == 0) goto L_0x0077
            android.system.Os.close(r6)     // Catch:{ Exception -> 0x0078 }
        L_0x0077:
            throw r5     // Catch:{ Exception -> 0x0078 }
        L_0x0078:
            r5 = move-exception
            java.lang.String r6 = "Failed to create the ColorResourcesTableCreator."
            android.util.Log.e(r0, r6, r5)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.ColorResourcesLoaderCreator.create(android.content.Context, java.util.Map):android.content.res.loader.ResourcesLoader");
    }
}
