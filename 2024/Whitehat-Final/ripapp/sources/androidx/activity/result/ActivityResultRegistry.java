package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.os.BundleCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000 72\u00020\u0001:\u0003678B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J#\u0010\u0015\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u00172\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u0002H\u0017H\u0007¢\u0006\u0002\u0010\u001aJ\"\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0007J8\u0010\u001e\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u00172\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u0002H\u0017\u0018\u00010\u0006H\u0002J\b\u0010 \u001a\u00020\nH\u0002JG\u0010!\u001a\u00020\u0012\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u00172\u0006\u0010\u0018\u001a\u00020\n2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H\u00170$2\u0006\u0010%\u001a\u0002H\"2\b\u0010&\u001a\u0004\u0018\u00010'H'¢\u0006\u0002\u0010(J\u0010\u0010)\u001a\u00020\u00122\b\u0010*\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010+\u001a\u00020\u00122\u0006\u0010,\u001a\u00020\u000fJB\u0010-\u001a\b\u0012\u0004\u0012\u0002H\"0.\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u00172\u0006\u0010\u0014\u001a\u00020\u00052\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H\u00170$2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H\u001700JJ\u0010-\u001a\b\u0012\u0004\u0012\u0002H\"0.\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010\u00172\u0006\u0010\u0014\u001a\u00020\u00052\u0006\u00101\u001a\u0002022\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H\u00170$2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H\u001700J\u0010\u00103\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0005H\u0002J\u0015\u00104\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0005H\u0001¢\u0006\u0002\b5R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u0005\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\n0\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00050\fX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Landroidx/activity/result/ActivityResultRegistry;", "", "()V", "keyToCallback", "", "", "Landroidx/activity/result/ActivityResultRegistry$CallbackAndContract;", "keyToLifecycleContainers", "Landroidx/activity/result/ActivityResultRegistry$LifecycleContainer;", "keyToRc", "", "launchedKeys", "", "parsedPendingResults", "pendingResults", "Landroid/os/Bundle;", "rcToKey", "bindRcKey", "", "rc", "key", "dispatchResult", "", "O", "requestCode", "result", "(ILjava/lang/Object;)Z", "resultCode", "data", "Landroid/content/Intent;", "doDispatch", "callbackAndContract", "generateRandomNumber", "onLaunch", "I", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "input", "options", "Landroidx/core/app/ActivityOptionsCompat;", "(ILandroidx/activity/result/contract/ActivityResultContract;Ljava/lang/Object;Landroidx/core/app/ActivityOptionsCompat;)V", "onRestoreInstanceState", "savedInstanceState", "onSaveInstanceState", "outState", "register", "Landroidx/activity/result/ActivityResultLauncher;", "callback", "Landroidx/activity/result/ActivityResultCallback;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "registerKey", "unregister", "unregister$activity_release", "CallbackAndContract", "Companion", "LifecycleContainer", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ActivityResultRegistry.kt */
public abstract class ActivityResultRegistry {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int INITIAL_REQUEST_CODE_VALUE = 65536;
    private static final String KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS = "KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS";
    private static final String KEY_COMPONENT_ACTIVITY_PENDING_RESULTS = "KEY_COMPONENT_ACTIVITY_PENDING_RESULT";
    private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS = "KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS";
    private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_RCS = "KEY_COMPONENT_ACTIVITY_REGISTERED_RCS";
    private static final String LOG_TAG = "ActivityResultRegistry";
    private final transient Map<String, CallbackAndContract<?>> keyToCallback = new LinkedHashMap();
    private final Map<String, LifecycleContainer> keyToLifecycleContainers = new LinkedHashMap();
    /* access modifiers changed from: private */
    public final Map<String, Integer> keyToRc = new LinkedHashMap();
    /* access modifiers changed from: private */
    public final List<String> launchedKeys = new ArrayList();
    private final Map<String, Object> parsedPendingResults = new LinkedHashMap();
    private final Bundle pendingResults = new Bundle();
    private final Map<Integer, String> rcToKey = new LinkedHashMap();

    public abstract <I, O> void onLaunch(int i, ActivityResultContract<I, O> activityResultContract, I i2, ActivityOptionsCompat activityOptionsCompat);

    public final <I, O> ActivityResultLauncher<I> register(String str, LifecycleOwner lifecycleOwner, ActivityResultContract<I, O> activityResultContract, ActivityResultCallback<O> activityResultCallback) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(activityResultContract, "contract");
        Intrinsics.checkNotNullParameter(activityResultCallback, "callback");
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (!lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            registerKey(str);
            LifecycleContainer lifecycleContainer = this.keyToLifecycleContainers.get(str);
            if (lifecycleContainer == null) {
                lifecycleContainer = new LifecycleContainer(lifecycle);
            }
            lifecycleContainer.addObserver(new ActivityResultRegistry$$ExternalSyntheticLambda0(this, str, activityResultCallback, activityResultContract));
            this.keyToLifecycleContainers.put(str, lifecycleContainer);
            return new ActivityResultRegistry$register$2(this, str, activityResultContract);
        }
        throw new IllegalStateException(("LifecycleOwner " + lifecycleOwner + " is attempting to register while current state is " + lifecycle.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.").toString());
    }

    /* access modifiers changed from: private */
    public static final void register$lambda$1(ActivityResultRegistry activityResultRegistry, String str, ActivityResultCallback activityResultCallback, ActivityResultContract activityResultContract, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(activityResultRegistry, "this$0");
        Intrinsics.checkNotNullParameter(str, "$key");
        Intrinsics.checkNotNullParameter(activityResultCallback, "$callback");
        Intrinsics.checkNotNullParameter(activityResultContract, "$contract");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        if (Lifecycle.Event.ON_START == event) {
            activityResultRegistry.keyToCallback.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
            if (activityResultRegistry.parsedPendingResults.containsKey(str)) {
                Object obj = activityResultRegistry.parsedPendingResults.get(str);
                activityResultRegistry.parsedPendingResults.remove(str);
                activityResultCallback.onActivityResult(obj);
            }
            ActivityResult activityResult = (ActivityResult) BundleCompat.getParcelable(activityResultRegistry.pendingResults, str, ActivityResult.class);
            if (activityResult != null) {
                activityResultRegistry.pendingResults.remove(str);
                activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
            }
        } else if (Lifecycle.Event.ON_STOP == event) {
            activityResultRegistry.keyToCallback.remove(str);
        } else if (Lifecycle.Event.ON_DESTROY == event) {
            activityResultRegistry.unregister$activity_release(str);
        }
    }

    public final <I, O> ActivityResultLauncher<I> register(String str, ActivityResultContract<I, O> activityResultContract, ActivityResultCallback<O> activityResultCallback) {
        Intrinsics.checkNotNullParameter(str, "key");
        Intrinsics.checkNotNullParameter(activityResultContract, "contract");
        Intrinsics.checkNotNullParameter(activityResultCallback, "callback");
        registerKey(str);
        this.keyToCallback.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
        if (this.parsedPendingResults.containsKey(str)) {
            Object obj = this.parsedPendingResults.get(str);
            this.parsedPendingResults.remove(str);
            activityResultCallback.onActivityResult(obj);
        }
        ActivityResult activityResult = (ActivityResult) BundleCompat.getParcelable(this.pendingResults, str, ActivityResult.class);
        if (activityResult != null) {
            this.pendingResults.remove(str);
            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
        }
        return new ActivityResultRegistry$register$3(this, str, activityResultContract);
    }

    public final void unregister$activity_release(String str) {
        Integer remove;
        Intrinsics.checkNotNullParameter(str, "key");
        if (!this.launchedKeys.contains(str) && (remove = this.keyToRc.remove(str)) != null) {
            this.rcToKey.remove(remove);
        }
        this.keyToCallback.remove(str);
        if (this.parsedPendingResults.containsKey(str)) {
            Log.w(LOG_TAG, "Dropping pending result for request " + str + ": " + this.parsedPendingResults.get(str));
            this.parsedPendingResults.remove(str);
        }
        if (this.pendingResults.containsKey(str)) {
            Log.w(LOG_TAG, "Dropping pending result for request " + str + ": " + ((ActivityResult) BundleCompat.getParcelable(this.pendingResults, str, ActivityResult.class)));
            this.pendingResults.remove(str);
        }
        LifecycleContainer lifecycleContainer = this.keyToLifecycleContainers.get(str);
        if (lifecycleContainer != null) {
            lifecycleContainer.clearObservers();
            this.keyToLifecycleContainers.remove(str);
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        bundle.putIntegerArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_RCS, new ArrayList(this.keyToRc.values()));
        bundle.putStringArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS, new ArrayList(this.keyToRc.keySet()));
        bundle.putStringArrayList(KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS, new ArrayList(this.launchedKeys));
        bundle.putBundle(KEY_COMPONENT_ACTIVITY_PENDING_RESULTS, new Bundle(this.pendingResults));
    }

    public final void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_RCS);
            ArrayList<String> stringArrayList = bundle.getStringArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS);
            if (stringArrayList != null && integerArrayList != null) {
                ArrayList<String> stringArrayList2 = bundle.getStringArrayList(KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS);
                if (stringArrayList2 != null) {
                    this.launchedKeys.addAll(stringArrayList2);
                }
                Bundle bundle2 = bundle.getBundle(KEY_COMPONENT_ACTIVITY_PENDING_RESULTS);
                if (bundle2 != null) {
                    this.pendingResults.putAll(bundle2);
                }
                int size = stringArrayList.size();
                for (int i = 0; i < size; i++) {
                    String str = stringArrayList.get(i);
                    if (this.keyToRc.containsKey(str)) {
                        Integer remove = this.keyToRc.remove(str);
                        if (!this.pendingResults.containsKey(str)) {
                            TypeIntrinsics.asMutableMap(this.rcToKey).remove(remove);
                        }
                    }
                    Integer num = integerArrayList.get(i);
                    Intrinsics.checkNotNullExpressionValue(num, "rcs[i]");
                    int intValue = num.intValue();
                    String str2 = stringArrayList.get(i);
                    Intrinsics.checkNotNullExpressionValue(str2, "keys[i]");
                    bindRcKey(intValue, str2);
                }
            }
        }
    }

    public final boolean dispatchResult(int i, int i2, Intent intent) {
        String str = this.rcToKey.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        doDispatch(str, i2, intent, this.keyToCallback.get(str));
        return true;
    }

    public final <O> boolean dispatchResult(int i, O o) {
        String str = this.rcToKey.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        CallbackAndContract callbackAndContract = this.keyToCallback.get(str);
        if ((callbackAndContract != null ? callbackAndContract.getCallback() : null) == null) {
            this.pendingResults.remove(str);
            this.parsedPendingResults.put(str, o);
            return true;
        }
        ActivityResultCallback callback = callbackAndContract.getCallback();
        Intrinsics.checkNotNull(callback, "null cannot be cast to non-null type androidx.activity.result.ActivityResultCallback<O of androidx.activity.result.ActivityResultRegistry.dispatchResult>");
        if (!this.launchedKeys.remove(str)) {
            return true;
        }
        callback.onActivityResult(o);
        return true;
    }

    private final <O> void doDispatch(String str, int i, Intent intent, CallbackAndContract<O> callbackAndContract) {
        if ((callbackAndContract != null ? callbackAndContract.getCallback() : null) == null || !this.launchedKeys.contains(str)) {
            this.parsedPendingResults.remove(str);
            this.pendingResults.putParcelable(str, new ActivityResult(i, intent));
            return;
        }
        callbackAndContract.getCallback().onActivityResult(callbackAndContract.getContract().parseResult(i, intent));
        this.launchedKeys.remove(str);
    }

    private final void registerKey(String str) {
        if (this.keyToRc.get(str) == null) {
            bindRcKey(generateRandomNumber(), str);
        }
    }

    private final int generateRandomNumber() {
        for (Number number : SequencesKt.generateSequence(ActivityResultRegistry$generateRandomNumber$1.INSTANCE)) {
            if (!this.rcToKey.containsKey(Integer.valueOf(number.intValue()))) {
                return number.intValue();
            }
        }
        throw new NoSuchElementException("Sequence contains no element matching the predicate.");
    }

    private final void bindRcKey(int i, String str) {
        this.rcToKey.put(Integer.valueOf(i), str);
        this.keyToRc.put(str, Integer.valueOf(i));
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B%\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0010\u0010\u0005\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\u0005\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/activity/result/ActivityResultRegistry$CallbackAndContract;", "O", "", "callback", "Landroidx/activity/result/ActivityResultCallback;", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "(Landroidx/activity/result/ActivityResultCallback;Landroidx/activity/result/contract/ActivityResultContract;)V", "getCallback", "()Landroidx/activity/result/ActivityResultCallback;", "getContract", "()Landroidx/activity/result/contract/ActivityResultContract;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ActivityResultRegistry.kt */
    private static final class CallbackAndContract<O> {
        private final ActivityResultCallback<O> callback;
        private final ActivityResultContract<?, O> contract;

        public CallbackAndContract(ActivityResultCallback<O> activityResultCallback, ActivityResultContract<?, O> activityResultContract) {
            Intrinsics.checkNotNullParameter(activityResultCallback, "callback");
            Intrinsics.checkNotNullParameter(activityResultContract, "contract");
            this.callback = activityResultCallback;
            this.contract = activityResultContract;
        }

        public final ActivityResultCallback<O> getCallback() {
            return this.callback;
        }

        public final ActivityResultContract<?, O> getContract() {
            return this.contract;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tJ\u0006\u0010\r\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/activity/result/ActivityResultRegistry$LifecycleContainer;", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "(Landroidx/lifecycle/Lifecycle;)V", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "observers", "", "Landroidx/lifecycle/LifecycleEventObserver;", "addObserver", "", "observer", "clearObservers", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ActivityResultRegistry.kt */
    private static final class LifecycleContainer {
        private final Lifecycle lifecycle;
        private final List<LifecycleEventObserver> observers = new ArrayList();

        public LifecycleContainer(Lifecycle lifecycle2) {
            Intrinsics.checkNotNullParameter(lifecycle2, "lifecycle");
            this.lifecycle = lifecycle2;
        }

        public final Lifecycle getLifecycle() {
            return this.lifecycle;
        }

        public final void addObserver(LifecycleEventObserver lifecycleEventObserver) {
            Intrinsics.checkNotNullParameter(lifecycleEventObserver, "observer");
            this.lifecycle.addObserver(lifecycleEventObserver);
            this.observers.add(lifecycleEventObserver);
        }

        public final void clearObservers() {
            for (LifecycleEventObserver removeObserver : this.observers) {
                this.lifecycle.removeObserver(removeObserver);
            }
            this.observers.clear();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Landroidx/activity/result/ActivityResultRegistry$Companion;", "", "()V", "INITIAL_REQUEST_CODE_VALUE", "", "KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS", "", "KEY_COMPONENT_ACTIVITY_PENDING_RESULTS", "KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS", "KEY_COMPONENT_ACTIVITY_REGISTERED_RCS", "LOG_TAG", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ActivityResultRegistry.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
