package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.Transition;

public class ChangeClipBounds extends Transition {
    static final Rect NULL_SENTINEL = new Rect();
    private static final String PROPNAME_BOUNDS = "android:clipBounds:bounds";
    private static final String PROPNAME_CLIP = "android:clipBounds:clip";
    private static final String[] sTransitionProperties = {PROPNAME_CLIP};

    public boolean isSeekingSupported() {
        return true;
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public ChangeClipBounds() {
    }

    public ChangeClipBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void captureValues(TransitionValues transitionValues, boolean z) {
        View view = transitionValues.view;
        if (view.getVisibility() != 8) {
            Rect rect = null;
            Rect rect2 = z ? (Rect) view.getTag(R.id.transition_clip) : null;
            if (rect2 == null) {
                rect2 = view.getClipBounds();
            }
            if (rect2 != NULL_SENTINEL) {
                rect = rect2;
            }
            transitionValues.values.put(PROPNAME_CLIP, rect);
            if (rect == null) {
                transitionValues.values.put(PROPNAME_BOUNDS, new Rect(0, 0, view.getWidth(), view.getHeight()));
            }
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues, true);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues, false);
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null || !transitionValues.values.containsKey(PROPNAME_CLIP) || !transitionValues2.values.containsKey(PROPNAME_CLIP)) {
            return null;
        }
        Rect rect = (Rect) transitionValues.values.get(PROPNAME_CLIP);
        Rect rect2 = (Rect) transitionValues2.values.get(PROPNAME_CLIP);
        if (rect == null && rect2 == null) {
            return null;
        }
        Rect rect3 = rect == null ? (Rect) transitionValues.values.get(PROPNAME_BOUNDS) : rect;
        Rect rect4 = rect2 == null ? (Rect) transitionValues2.values.get(PROPNAME_BOUNDS) : rect2;
        if (rect3.equals(rect4)) {
            return null;
        }
        transitionValues2.view.setClipBounds(rect);
        ObjectAnimator ofObject = ObjectAnimator.ofObject(transitionValues2.view, ViewUtils.CLIP_BOUNDS, new RectEvaluator(new Rect()), new Rect[]{rect3, rect4});
        Listener listener = new Listener(transitionValues2.view, rect, rect2);
        ofObject.addListener(listener);
        addListener(listener);
        return ofObject;
    }

    private static class Listener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private final Rect mEnd;
        private final Rect mStart;
        private final View mView;

        public void onTransitionCancel(Transition transition) {
        }

        public void onTransitionEnd(Transition transition) {
        }

        public void onTransitionStart(Transition transition) {
        }

        Listener(View view, Rect rect, Rect rect2) {
            this.mView = view;
            this.mStart = rect;
            this.mEnd = rect2;
        }

        public void onTransitionPause(Transition transition) {
            Rect clipBounds = this.mView.getClipBounds();
            if (clipBounds == null) {
                clipBounds = ChangeClipBounds.NULL_SENTINEL;
            }
            this.mView.setTag(R.id.transition_clip, clipBounds);
            this.mView.setClipBounds(this.mEnd);
        }

        public void onTransitionResume(Transition transition) {
            this.mView.setClipBounds((Rect) this.mView.getTag(R.id.transition_clip));
            this.mView.setTag(R.id.transition_clip, (Object) null);
        }

        public void onAnimationEnd(Animator animator) {
            onAnimationEnd(animator, false);
        }

        public void onAnimationEnd(Animator animator, boolean z) {
            if (!z) {
                this.mView.setClipBounds(this.mEnd);
            } else {
                this.mView.setClipBounds(this.mStart);
            }
        }
    }
}
