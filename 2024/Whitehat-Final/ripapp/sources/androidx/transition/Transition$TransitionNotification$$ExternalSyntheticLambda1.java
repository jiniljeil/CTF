package androidx.transition;

import androidx.transition.Transition;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Transition$TransitionNotification$$ExternalSyntheticLambda1 implements Transition.TransitionNotification {
    public final void notifyListener(Transition.TransitionListener transitionListener, Transition transition, boolean z) {
        transitionListener.onTransitionEnd(transition, z);
    }
}
