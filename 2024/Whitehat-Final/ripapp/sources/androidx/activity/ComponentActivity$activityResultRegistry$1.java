package androidx.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001JG\u0010\u0002\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\t2\u0006\u0010\n\u001a\u0002H\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"androidx/activity/ComponentActivity$activityResultRegistry$1", "Landroidx/activity/result/ActivityResultRegistry;", "onLaunch", "", "I", "O", "requestCode", "", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "input", "options", "Landroidx/core/app/ActivityOptionsCompat;", "(ILandroidx/activity/result/contract/ActivityResultContract;Ljava/lang/Object;Landroidx/core/app/ActivityOptionsCompat;)V", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ComponentActivity.kt */
public final class ComponentActivity$activityResultRegistry$1 extends ActivityResultRegistry {
    final /* synthetic */ ComponentActivity this$0;

    ComponentActivity$activityResultRegistry$1(ComponentActivity componentActivity) {
        this.this$0 = componentActivity;
    }

    public <I, O> void onLaunch(int i, ActivityResultContract<I, O> activityResultContract, I i2, ActivityOptionsCompat activityOptionsCompat) {
        Bundle bundle;
        Intrinsics.checkNotNullParameter(activityResultContract, "contract");
        ComponentActivity componentActivity = this.this$0;
        Context context = componentActivity;
        ActivityResultContract.SynchronousResult<O> synchronousResult = activityResultContract.getSynchronousResult(context, i2);
        if (synchronousResult != null) {
            new Handler(Looper.getMainLooper()).post(new ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda0(this, i, synchronousResult));
            return;
        }
        Intent createIntent = activityResultContract.createIntent(context, i2);
        if (createIntent.getExtras() != null) {
            Bundle extras = createIntent.getExtras();
            Intrinsics.checkNotNull(extras);
            if (extras.getClassLoader() == null) {
                createIntent.setExtrasClassLoader(componentActivity.getClassLoader());
            }
        }
        if (createIntent.hasExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE)) {
            bundle = createIntent.getBundleExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
            createIntent.removeExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
        } else {
            bundle = activityOptionsCompat != null ? activityOptionsCompat.toBundle() : null;
        }
        Bundle bundle2 = bundle;
        if (Intrinsics.areEqual((Object) ActivityResultContracts.RequestMultiplePermissions.ACTION_REQUEST_PERMISSIONS, (Object) createIntent.getAction())) {
            String[] stringArrayExtra = createIntent.getStringArrayExtra(ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSIONS);
            if (stringArrayExtra == null) {
                stringArrayExtra = new String[0];
            }
            ActivityCompat.requestPermissions(componentActivity, stringArrayExtra, i);
        } else if (Intrinsics.areEqual((Object) ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST, (Object) createIntent.getAction())) {
            IntentSenderRequest intentSenderRequest = (IntentSenderRequest) createIntent.getParcelableExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_INTENT_SENDER_REQUEST);
            try {
                Intrinsics.checkNotNull(intentSenderRequest);
                ActivityCompat.startIntentSenderForResult(componentActivity, intentSenderRequest.getIntentSender(), i, intentSenderRequest.getFillInIntent(), intentSenderRequest.getFlagsMask(), intentSenderRequest.getFlagsValues(), 0, bundle2);
            } catch (IntentSender.SendIntentException e) {
                new Handler(Looper.getMainLooper()).post(new ComponentActivity$activityResultRegistry$1$$ExternalSyntheticLambda1(this, i, e));
            }
        } else {
            ActivityCompat.startActivityForResult(componentActivity, createIntent, i, bundle2);
        }
    }

    /* access modifiers changed from: private */
    public static final void onLaunch$lambda$0(ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$1, int i, ActivityResultContract.SynchronousResult synchronousResult) {
        Intrinsics.checkNotNullParameter(componentActivity$activityResultRegistry$1, "this$0");
        componentActivity$activityResultRegistry$1.dispatchResult(i, synchronousResult.getValue());
    }

    /* access modifiers changed from: private */
    public static final void onLaunch$lambda$1(ComponentActivity$activityResultRegistry$1 componentActivity$activityResultRegistry$1, int i, IntentSender.SendIntentException sendIntentException) {
        Intrinsics.checkNotNullParameter(componentActivity$activityResultRegistry$1, "this$0");
        Intrinsics.checkNotNullParameter(sendIntentException, "$e");
        componentActivity$activityResultRegistry$1.dispatchResult(i, 0, new Intent().setAction(ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST).putExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_SEND_INTENT_EXCEPTION, sendIntentException));
    }
}
