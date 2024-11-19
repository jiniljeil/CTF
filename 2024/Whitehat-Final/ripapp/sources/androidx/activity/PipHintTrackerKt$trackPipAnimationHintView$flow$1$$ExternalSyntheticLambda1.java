package androidx.activity;

import android.view.View;
import android.view.ViewTreeObserver;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PipHintTrackerKt$trackPipAnimationHintView$flow$1$$ExternalSyntheticLambda1 implements ViewTreeObserver.OnScrollChangedListener {
    public final /* synthetic */ ProducerScope f$0;
    public final /* synthetic */ View f$1;

    public /* synthetic */ PipHintTrackerKt$trackPipAnimationHintView$flow$1$$ExternalSyntheticLambda1(ProducerScope producerScope, View view) {
        this.f$0 = producerScope;
        this.f$1 = view;
    }

    public final void onScrollChanged() {
        PipHintTrackerKt$trackPipAnimationHintView$flow$1.invokeSuspend$lambda$1(this.f$0, this.f$1);
    }
}
