package androidx.activity;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ComponentActivity$$ExternalSyntheticLambda5 implements LifecycleEventObserver {
    public final /* synthetic */ OnBackPressedDispatcher f$0;
    public final /* synthetic */ ComponentActivity f$1;

    public /* synthetic */ ComponentActivity$$ExternalSyntheticLambda5(OnBackPressedDispatcher onBackPressedDispatcher, ComponentActivity componentActivity) {
        this.f$0 = onBackPressedDispatcher;
        this.f$1 = componentActivity;
    }

    public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        ComponentActivity.addObserverForBackInvoker$lambda$7(this.f$0, this.f$1, lifecycleOwner, event);
    }
}
