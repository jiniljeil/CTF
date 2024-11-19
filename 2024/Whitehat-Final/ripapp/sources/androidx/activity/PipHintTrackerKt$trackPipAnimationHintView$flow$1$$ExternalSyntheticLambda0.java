package androidx.activity;

import android.view.View;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PipHintTrackerKt$trackPipAnimationHintView$flow$1$$ExternalSyntheticLambda0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ ProducerScope f$0;

    public /* synthetic */ PipHintTrackerKt$trackPipAnimationHintView$flow$1$$ExternalSyntheticLambda0(ProducerScope producerScope) {
        this.f$0 = producerScope;
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        PipHintTrackerKt$trackPipAnimationHintView$flow$1.invokeSuspend$lambda$0(this.f$0, view, i, i2, i3, i4, i5, i6, i7, i8);
    }
}
