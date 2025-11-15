package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;
import java.util.Map;

public class ChangeBounds extends Transition {
    private static final Property<View, PointF> BOTTOM_RIGHT_ONLY_PROPERTY = new Property<View, PointF>(PointF.class, "bottomRight") {
        public PointF get(View view) {
            return null;
        }

        public void set(View view, PointF pointF) {
            ViewUtils.setLeftTopRightBottom(view, view.getLeft(), view.getTop(), Math.round(pointF.x), Math.round(pointF.y));
        }
    };
    private static final Property<ViewBounds, PointF> BOTTOM_RIGHT_PROPERTY = new Property<ViewBounds, PointF>(PointF.class, "bottomRight") {
        public PointF get(ViewBounds viewBounds) {
            return null;
        }

        public void set(ViewBounds viewBounds, PointF pointF) {
            viewBounds.setBottomRight(pointF);
        }
    };
    private static final Property<View, PointF> POSITION_PROPERTY = new Property<View, PointF>(PointF.class, "position") {
        public PointF get(View view) {
            return null;
        }

        public void set(View view, PointF pointF) {
            int round = Math.round(pointF.x);
            int round2 = Math.round(pointF.y);
            ViewUtils.setLeftTopRightBottom(view, round, round2, view.getWidth() + round, view.getHeight() + round2);
        }
    };
    private static final String PROPNAME_BOUNDS = "android:changeBounds:bounds";
    private static final String PROPNAME_CLIP = "android:changeBounds:clip";
    private static final String PROPNAME_PARENT = "android:changeBounds:parent";
    private static final String PROPNAME_WINDOW_X = "android:changeBounds:windowX";
    private static final String PROPNAME_WINDOW_Y = "android:changeBounds:windowY";
    private static final Property<View, PointF> TOP_LEFT_ONLY_PROPERTY = new Property<View, PointF>(PointF.class, "topLeft") {
        public PointF get(View view) {
            return null;
        }

        public void set(View view, PointF pointF) {
            ViewUtils.setLeftTopRightBottom(view, Math.round(pointF.x), Math.round(pointF.y), view.getRight(), view.getBottom());
        }
    };
    private static final Property<ViewBounds, PointF> TOP_LEFT_PROPERTY = new Property<ViewBounds, PointF>(PointF.class, "topLeft") {
        public PointF get(ViewBounds viewBounds) {
            return null;
        }

        public void set(ViewBounds viewBounds, PointF pointF) {
            viewBounds.setTopLeft(pointF);
        }
    };
    private static final RectEvaluator sRectEvaluator = new RectEvaluator();
    private static final String[] sTransitionProperties = {PROPNAME_BOUNDS, PROPNAME_CLIP, PROPNAME_PARENT, PROPNAME_WINDOW_X, PROPNAME_WINDOW_Y};
    private boolean mResizeClip = false;

    public boolean isSeekingSupported() {
        return true;
    }

    public ChangeBounds() {
    }

    public ChangeBounds(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.CHANGE_BOUNDS);
        boolean namedBoolean = TypedArrayUtils.getNamedBoolean(obtainStyledAttributes, (XmlResourceParser) attributeSet, "resizeClip", 0, false);
        obtainStyledAttributes.recycle();
        setResizeClip(namedBoolean);
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public void setResizeClip(boolean z) {
        this.mResizeClip = z;
    }

    public boolean getResizeClip() {
        return this.mResizeClip;
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view.isLaidOut() || view.getWidth() != 0 || view.getHeight() != 0) {
            transitionValues.values.put(PROPNAME_BOUNDS, new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            transitionValues.values.put(PROPNAME_PARENT, transitionValues.view.getParent());
            if (this.mResizeClip) {
                transitionValues.values.put(PROPNAME_CLIP, view.getClipBounds());
            }
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        Rect rect;
        captureValues(transitionValues);
        if (this.mResizeClip && (rect = (Rect) transitionValues.view.getTag(R.id.transition_clip)) != null) {
            transitionValues.values.put(PROPNAME_CLIP, rect);
        }
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        int i;
        View view;
        Animator animator;
        int i2;
        int i3;
        int i4;
        ObjectAnimator objectAnimator;
        boolean z;
        ObjectAnimator objectAnimator2;
        TransitionValues transitionValues3 = transitionValues;
        TransitionValues transitionValues4 = transitionValues2;
        if (transitionValues3 == null || transitionValues4 == null) {
            return null;
        }
        Map<String, Object> map = transitionValues3.values;
        Map<String, Object> map2 = transitionValues4.values;
        ViewGroup viewGroup2 = (ViewGroup) map.get(PROPNAME_PARENT);
        ViewGroup viewGroup3 = (ViewGroup) map2.get(PROPNAME_PARENT);
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = transitionValues4.view;
        Rect rect = (Rect) transitionValues3.values.get(PROPNAME_BOUNDS);
        Rect rect2 = (Rect) transitionValues4.values.get(PROPNAME_BOUNDS);
        int i5 = rect.left;
        int i6 = rect2.left;
        int i7 = rect.top;
        int i8 = rect2.top;
        int i9 = rect.right;
        int i10 = rect2.right;
        int i11 = rect.bottom;
        int i12 = rect2.bottom;
        int i13 = i9 - i5;
        int i14 = i11 - i7;
        int i15 = i10 - i6;
        int i16 = i12 - i8;
        View view3 = view2;
        Rect rect3 = (Rect) transitionValues3.values.get(PROPNAME_CLIP);
        Rect rect4 = (Rect) transitionValues4.values.get(PROPNAME_CLIP);
        if ((i13 == 0 || i14 == 0) && (i15 == 0 || i16 == 0)) {
            i = 0;
        } else {
            i = (i5 == i6 && i7 == i8) ? 0 : 1;
            if (!(i9 == i10 && i11 == i12)) {
                i++;
            }
        }
        if ((rect3 != null && !rect3.equals(rect4)) || (rect3 == null && rect4 != null)) {
            i++;
        }
        int i17 = i;
        if (i17 <= 0) {
            return null;
        }
        Rect rect5 = rect4;
        if (!this.mResizeClip) {
            View view4 = view3;
            ViewUtils.setLeftTopRightBottom(view4, i5, i7, i9, i11);
            if (i17 == 2) {
                if (i13 == i15 && i14 == i16) {
                    animator = ObjectAnimatorUtils.ofPointF(view4, POSITION_PROPERTY, getPathMotion().getPath((float) i5, (float) i7, (float) i6, (float) i8));
                } else {
                    ViewBounds viewBounds = new ViewBounds(view4);
                    ObjectAnimator ofPointF = ObjectAnimatorUtils.ofPointF(viewBounds, TOP_LEFT_PROPERTY, getPathMotion().getPath((float) i5, (float) i7, (float) i6, (float) i8));
                    ObjectAnimator ofPointF2 = ObjectAnimatorUtils.ofPointF(viewBounds, BOTTOM_RIGHT_PROPERTY, getPathMotion().getPath((float) i9, (float) i11, (float) i10, (float) i12));
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(new Animator[]{ofPointF, ofPointF2});
                    animatorSet.addListener(new AnimatorListenerAdapter(viewBounds) {
                        private final ViewBounds mViewBounds;
                        final /* synthetic */ ViewBounds val$viewBounds;

                        {
                            this.val$viewBounds = r2;
                            this.mViewBounds = r2;
                        }
                    });
                    view = view4;
                    animator = animatorSet;
                }
            } else if (i5 == i6 && i7 == i8) {
                animator = ObjectAnimatorUtils.ofPointF(view4, BOTTOM_RIGHT_ONLY_PROPERTY, getPathMotion().getPath((float) i9, (float) i11, (float) i10, (float) i12));
            } else {
                animator = ObjectAnimatorUtils.ofPointF(view4, TOP_LEFT_ONLY_PROPERTY, getPathMotion().getPath((float) i5, (float) i7, (float) i6, (float) i8));
            }
            view = view4;
        } else {
            View view5 = view3;
            int i18 = i12;
            ViewUtils.setLeftTopRightBottom(view5, i5, i7, Math.max(i13, i15) + i5, i7 + Math.max(i14, i16));
            if (i5 == i6 && i7 == i8) {
                i4 = i10;
                i3 = i9;
                i2 = i6;
                objectAnimator = null;
            } else {
                i4 = i10;
                i3 = i9;
                i2 = i6;
                objectAnimator = ObjectAnimatorUtils.ofPointF(view5, POSITION_PROPERTY, getPathMotion().getPath((float) i5, (float) i7, (float) i6, (float) i8));
            }
            boolean z2 = rect3 == null;
            if (z2) {
                z = false;
                rect3 = new Rect(0, 0, i13, i14);
            } else {
                z = false;
            }
            boolean z3 = rect5 == null ? true : z;
            Rect rect6 = z3 ? new Rect(z ? 1 : 0, z, i15, i16) : rect5;
            if (!rect3.equals(rect6)) {
                view5.setClipBounds(rect3);
                objectAnimator2 = ObjectAnimator.ofObject(view5, "clipBounds", sRectEvaluator, new Object[]{rect3, rect6});
                int i19 = i2;
                view = view5;
                ClipListener clipListener = r6;
                ClipListener clipListener2 = new ClipListener(view5, rect3, z2, rect6, z3, i5, i7, i3, i11, i19, i8, i4, i18);
                objectAnimator2.addListener(clipListener);
                addListener(clipListener);
            } else {
                view = view5;
                objectAnimator2 = null;
            }
            animator = TransitionUtils.mergeAnimators(objectAnimator, objectAnimator2);
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            ViewGroupUtils.suppressLayout(viewGroup4, true);
            getRootTransition().addListener(new SuppressLayoutListener(viewGroup4));
        }
        return animator;
    }

    private static class ViewBounds {
        private int mBottom;
        private int mBottomRightCalls;
        private int mLeft;
        private int mRight;
        private int mTop;
        private int mTopLeftCalls;
        private final View mView;

        ViewBounds(View view) {
            this.mView = view;
        }

        /* access modifiers changed from: package-private */
        public void setTopLeft(PointF pointF) {
            this.mLeft = Math.round(pointF.x);
            this.mTop = Math.round(pointF.y);
            int i = this.mTopLeftCalls + 1;
            this.mTopLeftCalls = i;
            if (i == this.mBottomRightCalls) {
                setLeftTopRightBottom();
            }
        }

        /* access modifiers changed from: package-private */
        public void setBottomRight(PointF pointF) {
            this.mRight = Math.round(pointF.x);
            this.mBottom = Math.round(pointF.y);
            int i = this.mBottomRightCalls + 1;
            this.mBottomRightCalls = i;
            if (this.mTopLeftCalls == i) {
                setLeftTopRightBottom();
            }
        }

        private void setLeftTopRightBottom() {
            ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mTopLeftCalls = 0;
            this.mBottomRightCalls = 0;
        }
    }

    private static class ClipListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private final int mEndBottom;
        private final Rect mEndClip;
        private final boolean mEndClipIsNull;
        private final int mEndLeft;
        private final int mEndRight;
        private final int mEndTop;
        private boolean mIsCanceled;
        private final int mStartBottom;
        private final Rect mStartClip;
        private final boolean mStartClipIsNull;
        private final int mStartLeft;
        private final int mStartRight;
        private final int mStartTop;
        private final View mView;

        public void onTransitionEnd(Transition transition) {
        }

        public void onTransitionStart(Transition transition) {
        }

        ClipListener(View view, Rect rect, boolean z, Rect rect2, boolean z2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.mView = view;
            this.mStartClip = rect;
            this.mStartClipIsNull = z;
            this.mEndClip = rect2;
            this.mEndClipIsNull = z2;
            this.mStartLeft = i;
            this.mStartTop = i2;
            this.mStartRight = i3;
            this.mStartBottom = i4;
            this.mEndLeft = i5;
            this.mEndTop = i6;
            this.mEndRight = i7;
            this.mEndBottom = i8;
        }

        public void onAnimationStart(Animator animator) {
            onAnimationStart(animator, false);
        }

        public void onAnimationEnd(Animator animator) {
            onAnimationEnd(animator, false);
        }

        public void onAnimationStart(Animator animator, boolean z) {
            int max = Math.max(this.mStartRight - this.mStartLeft, this.mEndRight - this.mEndLeft);
            int max2 = Math.max(this.mStartBottom - this.mStartTop, this.mEndBottom - this.mEndTop);
            int i = z ? this.mEndLeft : this.mStartLeft;
            int i2 = z ? this.mEndTop : this.mStartTop;
            ViewUtils.setLeftTopRightBottom(this.mView, i, i2, max + i, max2 + i2);
            this.mView.setClipBounds(z ? this.mEndClip : this.mStartClip);
        }

        public void onAnimationEnd(Animator animator, boolean z) {
            if (!this.mIsCanceled) {
                Rect rect = null;
                if (z) {
                    if (!this.mStartClipIsNull) {
                        rect = this.mStartClip;
                    }
                } else if (!this.mEndClipIsNull) {
                    rect = this.mEndClip;
                }
                this.mView.setClipBounds(rect);
                if (z) {
                    ViewUtils.setLeftTopRightBottom(this.mView, this.mStartLeft, this.mStartTop, this.mStartRight, this.mStartBottom);
                } else {
                    ViewUtils.setLeftTopRightBottom(this.mView, this.mEndLeft, this.mEndTop, this.mEndRight, this.mEndBottom);
                }
            }
        }

        public void onTransitionCancel(Transition transition) {
            this.mIsCanceled = true;
        }

        public void onTransitionPause(Transition transition) {
            this.mView.setTag(R.id.transition_clip, this.mView.getClipBounds());
            this.mView.setClipBounds(this.mEndClipIsNull ? null : this.mEndClip);
        }

        public void onTransitionResume(Transition transition) {
            this.mView.setTag(R.id.transition_clip, (Object) null);
            this.mView.setClipBounds((Rect) this.mView.getTag(R.id.transition_clip));
        }
    }

    private static class SuppressLayoutListener extends TransitionListenerAdapter {
        boolean mCanceled = false;
        final ViewGroup mParent;

        SuppressLayoutListener(ViewGroup viewGroup) {
            this.mParent = viewGroup;
        }

        public void onTransitionCancel(Transition transition) {
            ViewGroupUtils.suppressLayout(this.mParent, false);
            this.mCanceled = true;
        }

        public void onTransitionEnd(Transition transition) {
            if (!this.mCanceled) {
                ViewGroupUtils.suppressLayout(this.mParent, false);
            }
            transition.removeListener(this);
        }

        public void onTransitionPause(Transition transition) {
            ViewGroupUtils.suppressLayout(this.mParent, false);
        }

        public void onTransitionResume(Transition transition) {
            ViewGroupUtils.suppressLayout(this.mParent, true);
        }
    }
}
