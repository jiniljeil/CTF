package androidx.localbroadcastmanager.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import java.util.ArrayList;
import java.util.HashMap;

public final class LocalBroadcastManager {
    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private static LocalBroadcastManager mInstance;
    private static final Object mLock = new Object();
    private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap<>();
    private final Context mAppContext;
    private final Handler mHandler;
    private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList<>();
    private final HashMap<BroadcastReceiver, ArrayList<ReceiverRecord>> mReceivers = new HashMap<>();

    private static final class ReceiverRecord {
        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
            this.filter = intentFilter;
            this.receiver = broadcastReceiver;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(128);
            sb.append("Receiver{");
            sb.append(this.receiver);
            sb.append(" filter=");
            sb.append(this.filter);
            if (this.dead) {
                sb.append(" DEAD");
            }
            sb.append("}");
            return sb.toString();
        }
    }

    private static final class BroadcastRecord {
        final Intent intent;
        final ArrayList<ReceiverRecord> receivers;

        BroadcastRecord(Intent intent2, ArrayList<ReceiverRecord> arrayList) {
            this.intent = intent2;
            this.receivers = arrayList;
        }
    }

    public static LocalBroadcastManager getInstance(Context context) {
        LocalBroadcastManager localBroadcastManager;
        synchronized (mLock) {
            if (mInstance == null) {
                mInstance = new LocalBroadcastManager(context.getApplicationContext());
            }
            localBroadcastManager = mInstance;
        }
        return localBroadcastManager;
    }

    private LocalBroadcastManager(Context context) {
        this.mAppContext = context;
        this.mHandler = new Handler(context.getMainLooper()) {
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    super.handleMessage(message);
                } else {
                    LocalBroadcastManager.this.executePendingBroadcasts();
                }
            }
        };
    }

    public void registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        synchronized (this.mReceivers) {
            ReceiverRecord receiverRecord = new ReceiverRecord(intentFilter, broadcastReceiver);
            ArrayList arrayList = this.mReceivers.get(broadcastReceiver);
            if (arrayList == null) {
                arrayList = new ArrayList(1);
                this.mReceivers.put(broadcastReceiver, arrayList);
            }
            arrayList.add(receiverRecord);
            for (int i = 0; i < intentFilter.countActions(); i++) {
                String action = intentFilter.getAction(i);
                ArrayList arrayList2 = this.mActions.get(action);
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(1);
                    this.mActions.put(action, arrayList2);
                }
                arrayList2.add(receiverRecord);
            }
        }
    }

    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        synchronized (this.mReceivers) {
            ArrayList remove = this.mReceivers.remove(broadcastReceiver);
            if (remove != null) {
                for (int size = remove.size() - 1; size >= 0; size--) {
                    ReceiverRecord receiverRecord = (ReceiverRecord) remove.get(size);
                    receiverRecord.dead = true;
                    for (int i = 0; i < receiverRecord.filter.countActions(); i++) {
                        String action = receiverRecord.filter.getAction(i);
                        ArrayList arrayList = this.mActions.get(action);
                        if (arrayList != null) {
                            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                                ReceiverRecord receiverRecord2 = (ReceiverRecord) arrayList.get(size2);
                                if (receiverRecord2.receiver == broadcastReceiver) {
                                    receiverRecord2.dead = true;
                                    arrayList.remove(size2);
                                }
                            }
                            if (arrayList.size() <= 0) {
                                this.mActions.remove(action);
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0170, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0175, code lost:
        return r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean sendBroadcast(android.content.Intent r23) {
        /*
            r22 = this;
            r1 = r22
            r0 = r23
            java.lang.String r2 = "Action list: "
            java.lang.String r3 = "Resolving type "
            java.util.HashMap<android.content.BroadcastReceiver, java.util.ArrayList<androidx.localbroadcastmanager.content.LocalBroadcastManager$ReceiverRecord>> r4 = r1.mReceivers
            monitor-enter(r4)
            java.lang.String r12 = r23.getAction()     // Catch:{ all -> 0x0176 }
            android.content.Context r5 = r1.mAppContext     // Catch:{ all -> 0x0176 }
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch:{ all -> 0x0176 }
            java.lang.String r13 = r0.resolveTypeIfNeeded(r5)     // Catch:{ all -> 0x0176 }
            android.net.Uri r14 = r23.getData()     // Catch:{ all -> 0x0176 }
            java.lang.String r15 = r23.getScheme()     // Catch:{ all -> 0x0176 }
            java.util.Set r16 = r23.getCategories()     // Catch:{ all -> 0x0176 }
            int r5 = r23.getFlags()     // Catch:{ all -> 0x0176 }
            r5 = r5 & 8
            r11 = 0
            r10 = 1
            if (r5 == 0) goto L_0x0032
            r17 = r10
            goto L_0x0034
        L_0x0032:
            r17 = r11
        L_0x0034:
            if (r17 == 0) goto L_0x005c
            java.lang.String r5 = "LocalBroadcastManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0176 }
            r6.<init>(r3)     // Catch:{ all -> 0x0176 }
            java.lang.StringBuilder r3 = r6.append(r13)     // Catch:{ all -> 0x0176 }
            java.lang.String r6 = " scheme "
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x0176 }
            java.lang.StringBuilder r3 = r3.append(r15)     // Catch:{ all -> 0x0176 }
            java.lang.String r6 = " of intent "
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch:{ all -> 0x0176 }
            java.lang.StringBuilder r3 = r3.append(r0)     // Catch:{ all -> 0x0176 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0176 }
            android.util.Log.v(r5, r3)     // Catch:{ all -> 0x0176 }
        L_0x005c:
            java.util.HashMap<java.lang.String, java.util.ArrayList<androidx.localbroadcastmanager.content.LocalBroadcastManager$ReceiverRecord>> r3 = r1.mActions     // Catch:{ all -> 0x0176 }
            java.lang.String r5 = r23.getAction()     // Catch:{ all -> 0x0176 }
            java.lang.Object r3 = r3.get(r5)     // Catch:{ all -> 0x0176 }
            java.util.ArrayList r3 = (java.util.ArrayList) r3     // Catch:{ all -> 0x0176 }
            if (r3 == 0) goto L_0x0173
            if (r17 == 0) goto L_0x007e
            java.lang.String r5 = "LocalBroadcastManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0176 }
            r6.<init>(r2)     // Catch:{ all -> 0x0176 }
            java.lang.StringBuilder r2 = r6.append(r3)     // Catch:{ all -> 0x0176 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0176 }
            android.util.Log.v(r5, r2)     // Catch:{ all -> 0x0176 }
        L_0x007e:
            r2 = 0
            r9 = r11
        L_0x0080:
            int r5 = r3.size()     // Catch:{ all -> 0x0176 }
            if (r9 >= r5) goto L_0x0142
            java.lang.Object r5 = r3.get(r9)     // Catch:{ all -> 0x0176 }
            r8 = r5
            androidx.localbroadcastmanager.content.LocalBroadcastManager$ReceiverRecord r8 = (androidx.localbroadcastmanager.content.LocalBroadcastManager.ReceiverRecord) r8     // Catch:{ all -> 0x0176 }
            if (r17 == 0) goto L_0x00a9
            java.lang.String r5 = "LocalBroadcastManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0176 }
            r6.<init>()     // Catch:{ all -> 0x0176 }
            java.lang.String r7 = "Matching against filter "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0176 }
            android.content.IntentFilter r7 = r8.filter     // Catch:{ all -> 0x0176 }
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0176 }
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0176 }
            android.util.Log.v(r5, r6)     // Catch:{ all -> 0x0176 }
        L_0x00a9:
            boolean r5 = r8.broadcasting     // Catch:{ all -> 0x0176 }
            if (r5 == 0) goto L_0x00bf
            if (r17 == 0) goto L_0x00b6
            java.lang.String r5 = "LocalBroadcastManager"
            java.lang.String r6 = "  Filter's target already added"
            android.util.Log.v(r5, r6)     // Catch:{ all -> 0x0176 }
        L_0x00b6:
            r19 = r3
            r20 = r9
            r21 = r12
            r12 = r10
            goto L_0x0138
        L_0x00bf:
            android.content.IntentFilter r5 = r8.filter     // Catch:{ all -> 0x0176 }
            java.lang.String r18 = "LocalBroadcastManager"
            r6 = r12
            r7 = r13
            r19 = r3
            r3 = r8
            r8 = r15
            r20 = r9
            r9 = r14
            r21 = r12
            r12 = r10
            r10 = r16
            r11 = r18
            int r5 = r5.match(r6, r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0176 }
            if (r5 < 0) goto L_0x0104
            if (r17 == 0) goto L_0x00f7
            java.lang.String r6 = "LocalBroadcastManager"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0176 }
            r7.<init>()     // Catch:{ all -> 0x0176 }
            java.lang.String r8 = "  Filter matched!  match=0x"
            java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x0176 }
            java.lang.String r5 = java.lang.Integer.toHexString(r5)     // Catch:{ all -> 0x0176 }
            java.lang.StringBuilder r5 = r7.append(r5)     // Catch:{ all -> 0x0176 }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x0176 }
            android.util.Log.v(r6, r5)     // Catch:{ all -> 0x0176 }
        L_0x00f7:
            if (r2 != 0) goto L_0x00fe
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0176 }
            r2.<init>()     // Catch:{ all -> 0x0176 }
        L_0x00fe:
            r2.add(r3)     // Catch:{ all -> 0x0176 }
            r3.broadcasting = r12     // Catch:{ all -> 0x0176 }
            goto L_0x0138
        L_0x0104:
            if (r17 == 0) goto L_0x0138
            r3 = -4
            if (r5 == r3) goto L_0x011e
            r3 = -3
            if (r5 == r3) goto L_0x011b
            r3 = -2
            if (r5 == r3) goto L_0x0118
            r3 = -1
            if (r5 == r3) goto L_0x0115
            java.lang.String r3 = "unknown reason"
            goto L_0x0120
        L_0x0115:
            java.lang.String r3 = "type"
            goto L_0x0120
        L_0x0118:
            java.lang.String r3 = "data"
            goto L_0x0120
        L_0x011b:
            java.lang.String r3 = "action"
            goto L_0x0120
        L_0x011e:
            java.lang.String r3 = "category"
        L_0x0120:
            java.lang.String r5 = "LocalBroadcastManager"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0176 }
            r6.<init>()     // Catch:{ all -> 0x0176 }
            java.lang.String r7 = "  Filter did not match: "
            java.lang.StringBuilder r6 = r6.append(r7)     // Catch:{ all -> 0x0176 }
            java.lang.StringBuilder r3 = r6.append(r3)     // Catch:{ all -> 0x0176 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0176 }
            android.util.Log.v(r5, r3)     // Catch:{ all -> 0x0176 }
        L_0x0138:
            int r9 = r20 + 1
            r10 = r12
            r3 = r19
            r12 = r21
            r11 = 0
            goto L_0x0080
        L_0x0142:
            r12 = r10
            if (r2 == 0) goto L_0x0171
            r11 = 0
        L_0x0146:
            int r3 = r2.size()     // Catch:{ all -> 0x0176 }
            if (r11 >= r3) goto L_0x0158
            java.lang.Object r3 = r2.get(r11)     // Catch:{ all -> 0x0176 }
            androidx.localbroadcastmanager.content.LocalBroadcastManager$ReceiverRecord r3 = (androidx.localbroadcastmanager.content.LocalBroadcastManager.ReceiverRecord) r3     // Catch:{ all -> 0x0176 }
            r5 = 0
            r3.broadcasting = r5     // Catch:{ all -> 0x0176 }
            int r11 = r11 + 1
            goto L_0x0146
        L_0x0158:
            java.util.ArrayList<androidx.localbroadcastmanager.content.LocalBroadcastManager$BroadcastRecord> r3 = r1.mPendingBroadcasts     // Catch:{ all -> 0x0176 }
            androidx.localbroadcastmanager.content.LocalBroadcastManager$BroadcastRecord r5 = new androidx.localbroadcastmanager.content.LocalBroadcastManager$BroadcastRecord     // Catch:{ all -> 0x0176 }
            r5.<init>(r0, r2)     // Catch:{ all -> 0x0176 }
            r3.add(r5)     // Catch:{ all -> 0x0176 }
            android.os.Handler r0 = r1.mHandler     // Catch:{ all -> 0x0176 }
            boolean r0 = r0.hasMessages(r12)     // Catch:{ all -> 0x0176 }
            if (r0 != 0) goto L_0x016f
            android.os.Handler r0 = r1.mHandler     // Catch:{ all -> 0x0176 }
            r0.sendEmptyMessage(r12)     // Catch:{ all -> 0x0176 }
        L_0x016f:
            monitor-exit(r4)     // Catch:{ all -> 0x0176 }
            return r12
        L_0x0171:
            r5 = 0
            goto L_0x0174
        L_0x0173:
            r5 = r11
        L_0x0174:
            monitor-exit(r4)     // Catch:{ all -> 0x0176 }
            return r5
        L_0x0176:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0176 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.localbroadcastmanager.content.LocalBroadcastManager.sendBroadcast(android.content.Intent):boolean");
    }

    public void sendBroadcastSync(Intent intent) {
        if (sendBroadcast(intent)) {
            executePendingBroadcasts();
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r3 >= r1) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        r4 = r2[r3];
        r5 = r4.receivers.size();
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        if (r6 >= r5) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        r7 = r4.receivers.get(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        if (r7.dead != false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        r7.receiver.onReceive(r10.mAppContext, r4.intent);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0041, code lost:
        r3 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        r3 = 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executePendingBroadcasts() {
        /*
            r10 = this;
        L_0x0000:
            java.util.HashMap<android.content.BroadcastReceiver, java.util.ArrayList<androidx.localbroadcastmanager.content.LocalBroadcastManager$ReceiverRecord>> r0 = r10.mReceivers
            monitor-enter(r0)
            java.util.ArrayList<androidx.localbroadcastmanager.content.LocalBroadcastManager$BroadcastRecord> r1 = r10.mPendingBroadcasts     // Catch:{ all -> 0x0044 }
            int r1 = r1.size()     // Catch:{ all -> 0x0044 }
            if (r1 > 0) goto L_0x000d
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            return
        L_0x000d:
            androidx.localbroadcastmanager.content.LocalBroadcastManager$BroadcastRecord[] r2 = new androidx.localbroadcastmanager.content.LocalBroadcastManager.BroadcastRecord[r1]     // Catch:{ all -> 0x0044 }
            java.util.ArrayList<androidx.localbroadcastmanager.content.LocalBroadcastManager$BroadcastRecord> r3 = r10.mPendingBroadcasts     // Catch:{ all -> 0x0044 }
            r3.toArray(r2)     // Catch:{ all -> 0x0044 }
            java.util.ArrayList<androidx.localbroadcastmanager.content.LocalBroadcastManager$BroadcastRecord> r3 = r10.mPendingBroadcasts     // Catch:{ all -> 0x0044 }
            r3.clear()     // Catch:{ all -> 0x0044 }
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            r0 = 0
            r3 = r0
        L_0x001c:
            if (r3 >= r1) goto L_0x0000
            r4 = r2[r3]
            java.util.ArrayList<androidx.localbroadcastmanager.content.LocalBroadcastManager$ReceiverRecord> r5 = r4.receivers
            int r5 = r5.size()
            r6 = r0
        L_0x0027:
            if (r6 >= r5) goto L_0x0041
            java.util.ArrayList<androidx.localbroadcastmanager.content.LocalBroadcastManager$ReceiverRecord> r7 = r4.receivers
            java.lang.Object r7 = r7.get(r6)
            androidx.localbroadcastmanager.content.LocalBroadcastManager$ReceiverRecord r7 = (androidx.localbroadcastmanager.content.LocalBroadcastManager.ReceiverRecord) r7
            boolean r8 = r7.dead
            if (r8 != 0) goto L_0x003e
            android.content.BroadcastReceiver r7 = r7.receiver
            android.content.Context r8 = r10.mAppContext
            android.content.Intent r9 = r4.intent
            r7.onReceive(r8, r9)
        L_0x003e:
            int r6 = r6 + 1
            goto L_0x0027
        L_0x0041:
            int r3 = r3 + 1
            goto L_0x001c
        L_0x0044:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.localbroadcastmanager.content.LocalBroadcastManager.executePendingBroadcasts():void");
    }
}
