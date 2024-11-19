package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class FontProvider {
    private static final Comparator<byte[]> sByteArrayComparator = new FontProvider$$ExternalSyntheticLambda0();

    private FontProvider() {
    }

    static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context context, FontRequest fontRequest, CancellationSignal cancellationSignal) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return FontsContractCompat.FontFamilyResult.create(1, (FontsContractCompat.FontInfo[]) null);
        }
        return FontsContractCompat.FontFamilyResult.create(0, query(context, fontRequest, provider.authority, cancellationSignal));
    }

    static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(convertToByteArrayList, sByteArrayComparator);
            List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
            for (int i = 0; i < certificates.size(); i++) {
                ArrayList arrayList = new ArrayList(certificates.get(i));
                Collections.sort(arrayList, sByteArrayComparator);
                if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static androidx.core.provider.FontsContractCompat.FontInfo[] query(android.content.Context r21, androidx.core.provider.FontRequest r22, java.lang.String r23, android.os.CancellationSignal r24) {
        /*
            r0 = r23
            java.lang.String r1 = "result_code"
            java.lang.String r2 = "font_italic"
            java.lang.String r3 = "font_weight"
            java.lang.String r4 = "font_ttc_index"
            java.lang.String r5 = "file_id"
            java.lang.String r6 = "_id"
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            android.net.Uri$Builder r8 = new android.net.Uri$Builder
            r8.<init>()
            java.lang.String r9 = "content"
            android.net.Uri$Builder r8 = r8.scheme(r9)
            android.net.Uri$Builder r8 = r8.authority(r0)
            android.net.Uri r8 = r8.build()
            android.net.Uri$Builder r10 = new android.net.Uri$Builder
            r10.<init>()
            android.net.Uri$Builder r9 = r10.scheme(r9)
            android.net.Uri$Builder r0 = r9.authority(r0)
            java.lang.String r9 = "file"
            android.net.Uri$Builder r0 = r0.appendPath(r9)
            android.net.Uri r0 = r0.build()
            r9 = r21
            androidx.core.provider.FontProvider$ContentQueryWrapper r9 = androidx.core.provider.FontProvider.ContentQueryWrapper.make(r9, r8)
            r10 = 7
            r17 = 0
            java.lang.String[] r12 = new java.lang.String[r10]     // Catch:{ all -> 0x010b }
            r15 = 0
            r12[r15] = r6     // Catch:{ all -> 0x010b }
            r14 = 1
            r12[r14] = r5     // Catch:{ all -> 0x010b }
            r10 = 2
            r12[r10] = r4     // Catch:{ all -> 0x010b }
            java.lang.String r10 = "font_variation_settings"
            r11 = 3
            r12[r11] = r10     // Catch:{ all -> 0x010b }
            r10 = 4
            r12[r10] = r3     // Catch:{ all -> 0x010b }
            r10 = 5
            r12[r10] = r2     // Catch:{ all -> 0x010b }
            r10 = 6
            r12[r10] = r1     // Catch:{ all -> 0x010b }
            java.lang.String r13 = "query = ?"
            java.lang.String[] r11 = new java.lang.String[r14]     // Catch:{ all -> 0x010b }
            java.lang.String r10 = r22.getQuery()     // Catch:{ all -> 0x010b }
            r11[r15] = r10     // Catch:{ all -> 0x010b }
            r16 = 0
            r10 = r9
            r18 = r11
            r11 = r8
            r19 = r7
            r7 = r14
            r14 = r18
            r15 = r16
            r16 = r24
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16)     // Catch:{ all -> 0x010b }
            if (r10 == 0) goto L_0x00f7
            int r11 = r10.getCount()     // Catch:{ all -> 0x00f3 }
            if (r11 <= 0) goto L_0x00f7
            int r1 = r10.getColumnIndex(r1)     // Catch:{ all -> 0x00f3 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ all -> 0x00f3 }
            r11.<init>()     // Catch:{ all -> 0x00f3 }
            int r6 = r10.getColumnIndex(r6)     // Catch:{ all -> 0x00f3 }
            int r5 = r10.getColumnIndex(r5)     // Catch:{ all -> 0x00f3 }
            int r4 = r10.getColumnIndex(r4)     // Catch:{ all -> 0x00f3 }
            int r3 = r10.getColumnIndex(r3)     // Catch:{ all -> 0x00f3 }
            int r2 = r10.getColumnIndex(r2)     // Catch:{ all -> 0x00f3 }
        L_0x00a1:
            boolean r12 = r10.moveToNext()     // Catch:{ all -> 0x00f3 }
            if (r12 == 0) goto L_0x00f1
            r12 = -1
            if (r1 == r12) goto L_0x00af
            int r15 = r10.getInt(r1)     // Catch:{ all -> 0x00f3 }
            goto L_0x00b0
        L_0x00af:
            r15 = 0
        L_0x00b0:
            if (r4 == r12) goto L_0x00b7
            int r13 = r10.getInt(r4)     // Catch:{ all -> 0x00f3 }
            goto L_0x00b8
        L_0x00b7:
            r13 = 0
        L_0x00b8:
            if (r5 != r12) goto L_0x00c4
            r14 = r13
            long r12 = r10.getLong(r6)     // Catch:{ all -> 0x00f3 }
            android.net.Uri r12 = android.content.ContentUris.withAppendedId(r8, r12)     // Catch:{ all -> 0x00f3 }
            goto L_0x00cd
        L_0x00c4:
            r14 = r13
            long r12 = r10.getLong(r5)     // Catch:{ all -> 0x00f3 }
            android.net.Uri r12 = android.content.ContentUris.withAppendedId(r0, r12)     // Catch:{ all -> 0x00f3 }
        L_0x00cd:
            r13 = -1
            if (r3 == r13) goto L_0x00d5
            int r16 = r10.getInt(r3)     // Catch:{ all -> 0x00f3 }
            goto L_0x00d7
        L_0x00d5:
            r16 = 400(0x190, float:5.6E-43)
        L_0x00d7:
            r20 = r16
            if (r2 == r13) goto L_0x00e5
            int r13 = r10.getInt(r2)     // Catch:{ all -> 0x00f3 }
            if (r13 != r7) goto L_0x00e5
            r13 = r7
            r7 = r20
            goto L_0x00e8
        L_0x00e5:
            r7 = r20
            r13 = 0
        L_0x00e8:
            androidx.core.provider.FontsContractCompat$FontInfo r7 = androidx.core.provider.FontsContractCompat.FontInfo.create(r12, r14, r7, r13, r15)     // Catch:{ all -> 0x00f3 }
            r11.add(r7)     // Catch:{ all -> 0x00f3 }
            r7 = 1
            goto L_0x00a1
        L_0x00f1:
            r7 = r11
            goto L_0x00f9
        L_0x00f3:
            r0 = move-exception
            r17 = r10
            goto L_0x010c
        L_0x00f7:
            r7 = r19
        L_0x00f9:
            if (r10 == 0) goto L_0x00fe
            r10.close()
        L_0x00fe:
            r9.close()
            r0 = 0
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = new androidx.core.provider.FontsContractCompat.FontInfo[r0]
            java.lang.Object[] r0 = r7.toArray(r0)
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = (androidx.core.provider.FontsContractCompat.FontInfo[]) r0
            return r0
        L_0x010b:
            r0 = move-exception
        L_0x010c:
            if (r17 == 0) goto L_0x0111
            r17.close()
        L_0x0111:
            r9.close()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontProvider.query(android.content.Context, androidx.core.provider.FontRequest, java.lang.String, android.os.CancellationSignal):androidx.core.provider.FontsContractCompat$FontInfo[]");
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    static /* synthetic */ int lambda$static$0(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            byte b2 = bArr2[i];
            if (b != b2) {
                return b - b2;
            }
        }
        return 0;
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    private interface ContentQueryWrapper {
        void close();

        Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);

        static ContentQueryWrapper make(Context context, Uri uri) {
            return new ContentQueryWrapperApi24Impl(context, uri);
        }
    }

    private static class ContentQueryWrapperApi16Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        ContentQueryWrapperApi16Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }

        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.release();
            }
        }
    }

    private static class ContentQueryWrapperApi24Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        ContentQueryWrapperApi24Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }

        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.close();
            }
        }
    }
}
