package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;

public class Fade extends Visibility {
    public static final int IN = 1;
    private static final String LOG_TAG = "Fade";
    public static final int OUT = 2;
    private static final String PROPNAME_TRANSITION_ALPHA = "android:fade:transitionAlpha";

    public boolean isSeekingSupported() {
        return true;
    }

    public Fade(int i) {
        setMode(i);
    }

    public Fade() {
    }

    public Fade(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.FADE);
        setMode(TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "fadingMode", 0, getMode()));
        obtainStyledAttributes.recycle();
    }

    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        Float f = (Float) transitionValues.view.getTag(R.id.transition_pause_alpha);
        if (f == null) {
            if (transitionValues.view.getVisibility() == 0) {
                f = Float.valueOf(ViewUtils.getTransitionAlpha(transitionValues.view));
            } else {
                f = Float.valueOf(0.0f);
            }
        }
        transitionValues.values.put(PROPNAME_TRANSITION_ALPHA, f);
    }

    private Animator createAnimation(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        ViewUtils.setTransitionAlpha(view, f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, ViewUtils.TRANSITION_ALPHA, new float[]{f2});
        FadeAnimatorListener fadeAnimatorListener = new FadeAnimatorListener(view);
        ofFloat.addListener(fadeAnimatorListener);
        getRootTransition().addListener(fadeAnimatorListener);
        return ofFloat;
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewUtils.saveNonTransitionAlpha(view);
        return createAnimation(view, getStartAlpha(transitionValues, 0.0f), 1.0f);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewUtils.saveNonTransitionAlpha(view);
        Animator createAnimation = createAnimation(view, getStartAlpha(transitionValues, 1.0f), 0.0f);
        if (createAnimation == null) {
            ViewUtils.setTransitionAlpha(view, getStartAlpha(transitionValues2, 1.0f));
        }
        return createAnimation;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r1 = (java.lang.Float) r1.values.get(PROPNAME_TRANSITION_ALPHA);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static float getStartAlpha(androidx.transition.TransitionValues r1, float r2) {
        /*
            if (r1 == 0) goto L_0x0012
            java.util.Map<java.lang.String, java.lang.Object> r1 = r1.values
            java.lang.String r0 = "android:fade:transitionAlpha"
            java.lang.Object r1 = r1.get(r0)
            java.lang.Float r1 = (java.lang.Float) r1
            if (r1 == 0) goto L_0x0012
            float r2 = r1.floatValue()
        L_0x0012:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.Fade.getStartAlpha(androidx.transition.TransitionValues, float):float");
    }

    private static class FadeAnimatorListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private boolean mLayerTypeChanged = false;
        private final View mView;

        public void onTransitionCancel(Transition transition) {
        }

        public void onTransitionEnd(Transition transition) {
        }

        public void onTransitionStart(Transition transition) {
        }

        public void onTransitionStart(Transition transition, boolean z) {
        }

        FadeAnimatorListener(View view) {
            this.mView = view;
        }

        public void onAnimationStart(Animator animator) {
            if (this.mView.hasOverlappingRendering() && this.mView.getLayerType() == 0) {
                this.mLayerTypeChanged = true;
                this.mView.setLayerType(2, (Paint) null);
            }
        }

        public void onAnimationEnd(Animator animator) {
            onAnimationEnd(animator, false);
        }

        public void onAnimationEnd(Animator animator, boolean z) {
            if (this.mLayerTypeChanged) {
                this.mView.setLayerType(0, (Paint) null);
            }
            if (!z) {
                ViewUtils.setTransitionAlpha(this.mView, 1.0f);
                ViewUtils.clearNonTransitionAlpha(this.mView);
            }
        }

        public void onAnimationCancel(Animator animator) {
            ViewUtils.setTransitionAlpha(this.mView, 1.0f);
        }

        public void onTransitionPause(Transition transition) {
            this.mView.setTag(R.id.transition_pause_alpha, Float.valueOf(this.mView.getVisibility() == 0 ? ViewUtils.getTransitionAlpha(this.mView) : 0.0f));
        }

        public void onTransitionResume(Transition transition) {
            this.mView.setTag(R.id.transition_pause_alpha, (Object) null);
        }
    }
}
