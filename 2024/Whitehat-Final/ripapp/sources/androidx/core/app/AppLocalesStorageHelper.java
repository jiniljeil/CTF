package androidx.core.app;

public class AppLocalesStorageHelper {
    static final String APPLICATION_LOCALES_RECORD_FILE = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file";
    static final boolean DEBUG = false;
    static final String LOCALE_RECORD_ATTRIBUTE_TAG = "application_locales";
    static final String LOCALE_RECORD_FILE_TAG = "locales";
    static final String TAG = "AppLocalesStorageHelper";
    private static final Object sAppLocaleStorageSync = new Object();

    private AppLocalesStorageHelper() {
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:23|(2:37|38)|39|40) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0041, code lost:
        if (r2 != null) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0050, code lost:
        if (r2 == null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0057, code lost:
        if (r1.isEmpty() == false) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005a, code lost:
        r8.deleteFile(APPLICATION_LOCALES_RECORD_FILE);
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0053 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0066 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:29:0x0053=Splitter:B:29:0x0053, B:39:0x0066=Splitter:B:39:0x0066} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readLocales(android.content.Context r8) {
        /*
            java.lang.Object r0 = sAppLocaleStorageSync
            monitor-enter(r0)
            java.lang.String r1 = ""
            java.lang.String r2 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.io.FileInputStream r2 = r8.openFileInput(r2)     // Catch:{ FileNotFoundException -> 0x0067 }
            org.xmlpull.v1.XmlPullParser r3 = android.util.Xml.newPullParser()     // Catch:{ IOException | XmlPullParserException -> 0x0049 }
            java.lang.String r4 = "UTF-8"
            r3.setInput(r2, r4)     // Catch:{ IOException | XmlPullParserException -> 0x0049 }
            int r4 = r3.getDepth()     // Catch:{ IOException | XmlPullParserException -> 0x0049 }
        L_0x0018:
            int r5 = r3.next()     // Catch:{ IOException | XmlPullParserException -> 0x0049 }
            r6 = 1
            if (r5 == r6) goto L_0x0041
            r6 = 3
            if (r5 != r6) goto L_0x0028
            int r7 = r3.getDepth()     // Catch:{ IOException | XmlPullParserException -> 0x0049 }
            if (r7 <= r4) goto L_0x0041
        L_0x0028:
            if (r5 == r6) goto L_0x0018
            r6 = 4
            if (r5 != r6) goto L_0x002e
            goto L_0x0018
        L_0x002e:
            java.lang.String r5 = r3.getName()     // Catch:{ IOException | XmlPullParserException -> 0x0049 }
            java.lang.String r6 = "locales"
            boolean r5 = r5.equals(r6)     // Catch:{ IOException | XmlPullParserException -> 0x0049 }
            if (r5 == 0) goto L_0x0018
            java.lang.String r4 = "application_locales"
            r5 = 0
            java.lang.String r1 = r3.getAttributeValue(r5, r4)     // Catch:{ IOException | XmlPullParserException -> 0x0049 }
        L_0x0041:
            if (r2 == 0) goto L_0x0053
        L_0x0043:
            r2.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0053
        L_0x0047:
            r8 = move-exception
            goto L_0x0061
        L_0x0049:
            java.lang.String r3 = "AppLocalesStorageHelper"
            java.lang.String r4 = "Reading app Locales : Unable to parse through file :androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            android.util.Log.w(r3, r4)     // Catch:{ all -> 0x0047 }
            if (r2 == 0) goto L_0x0053
            goto L_0x0043
        L_0x0053:
            boolean r2 = r1.isEmpty()     // Catch:{ all -> 0x0069 }
            if (r2 != 0) goto L_0x005a
            goto L_0x005f
        L_0x005a:
            java.lang.String r2 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            r8.deleteFile(r2)     // Catch:{ all -> 0x0069 }
        L_0x005f:
            monitor-exit(r0)     // Catch:{ all -> 0x0069 }
            return r1
        L_0x0061:
            if (r2 == 0) goto L_0x0066
            r2.close()     // Catch:{ IOException -> 0x0066 }
        L_0x0066:
            throw r8     // Catch:{ all -> 0x0069 }
        L_0x0067:
            monitor-exit(r0)     // Catch:{ all -> 0x0069 }
            return r1
        L_0x0069:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0069 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.AppLocalesStorageHelper.readLocales(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(3:26|27|28) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:20|(2:30|31)|32|33) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003d, code lost:
        if (r5 != null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004d, code lost:
        if (r5 == null) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0051, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:26:0x0050 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:32:0x0057 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:26:0x0050=Splitter:B:26:0x0050, B:32:0x0057=Splitter:B:32:0x0057} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void persistLocales(android.content.Context r5, java.lang.String r6) {
        /*
            java.lang.Object r0 = sAppLocaleStorageSync
            monitor-enter(r0)
            java.lang.String r1 = ""
            boolean r1 = r6.equals(r1)     // Catch:{ all -> 0x006b }
            if (r1 == 0) goto L_0x0012
            java.lang.String r6 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            r5.deleteFile(r6)     // Catch:{ all -> 0x006b }
            monitor-exit(r0)     // Catch:{ all -> 0x006b }
            return
        L_0x0012:
            java.lang.String r1 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            r2 = 0
            java.io.FileOutputStream r5 = r5.openFileOutput(r1, r2)     // Catch:{ FileNotFoundException -> 0x0058 }
            org.xmlpull.v1.XmlSerializer r1 = android.util.Xml.newSerializer()     // Catch:{ all -> 0x006b }
            r2 = 0
            r1.setOutput(r5, r2)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r3 = "UTF-8"
            r4 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ Exception -> 0x0045 }
            r1.startDocument(r3, r4)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r3 = "locales"
            r1.startTag(r2, r3)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r3 = "application_locales"
            r1.attribute(r2, r3, r6)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r6 = "locales"
            r1.endTag(r2, r6)     // Catch:{ Exception -> 0x0045 }
            r1.endDocument()     // Catch:{ Exception -> 0x0045 }
            if (r5 == 0) goto L_0x0050
        L_0x003f:
            r5.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0050
        L_0x0043:
            r6 = move-exception
            goto L_0x0052
        L_0x0045:
            r6 = move-exception
            java.lang.String r1 = "AppLocalesStorageHelper"
            java.lang.String r2 = "Storing App Locales : Failed to persist app-locales in storage "
            android.util.Log.w(r1, r2, r6)     // Catch:{ all -> 0x0043 }
            if (r5 == 0) goto L_0x0050
            goto L_0x003f
        L_0x0050:
            monitor-exit(r0)     // Catch:{ all -> 0x006b }
            return
        L_0x0052:
            if (r5 == 0) goto L_0x0057
            r5.close()     // Catch:{ IOException -> 0x0057 }
        L_0x0057:
            throw r6     // Catch:{ all -> 0x006b }
        L_0x0058:
            java.lang.String r5 = "AppLocalesStorageHelper"
            java.lang.String r6 = "Storing App Locales : FileNotFoundException: Cannot open file %s for writing "
            java.lang.String r1 = "androidx.appcompat.app.AppCompatDelegate.application_locales_record_file"
            java.lang.Object[] r1 = new java.lang.Object[]{r1}     // Catch:{ all -> 0x006b }
            java.lang.String r6 = java.lang.String.format(r6, r1)     // Catch:{ all -> 0x006b }
            android.util.Log.w(r5, r6)     // Catch:{ all -> 0x006b }
            monitor-exit(r0)     // Catch:{ all -> 0x006b }
            return
        L_0x006b:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x006b }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.app.AppLocalesStorageHelper.persistLocales(android.content.Context, java.lang.String):void");
    }
}
