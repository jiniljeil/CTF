package androidx.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionSet extends Transition {
    private static final int FLAG_CHANGE_EPICENTER = 8;
    private static final int FLAG_CHANGE_INTERPOLATOR = 1;
    private static final int FLAG_CHANGE_PATH_MOTION = 4;
    private static final int FLAG_CHANGE_PROPAGATION = 2;
    public static final int ORDERING_SEQUENTIAL = 1;
    public static final int ORDERING_TOGETHER = 0;
    private int mChangeFlags = 0;
    int mCurrentListeners;
    private boolean mPlayTogether = true;
    boolean mStarted = false;
    ArrayList<Transition> mTransitions = new ArrayList<>();

    public TransitionSet() {
    }

    public TransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.TRANSITION_SET);
        setOrdering(TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        obtainStyledAttributes.recycle();
    }

    public TransitionSet setOrdering(int i) {
        if (i == 0) {
            this.mPlayTogether = true;
        } else if (i == 1) {
            this.mPlayTogether = false;
        } else {
            throw new AndroidRuntimeException("Invalid parameter for TransitionSet ordering: " + i);
        }
        return this;
    }

    public int getOrdering() {
        return this.mPlayTogether ^ true ? 1 : 0;
    }

    public TransitionSet addTransition(Transition transition) {
        addTransitionInternal(transition);
        if (this.mDuration >= 0) {
            transition.setDuration(this.mDuration);
        }
        if ((this.mChangeFlags & 1) != 0) {
            transition.setInterpolator(getInterpolator());
        }
        if ((this.mChangeFlags & 2) != 0) {
            transition.setPropagation(getPropagation());
        }
        if ((this.mChangeFlags & 4) != 0) {
            transition.setPathMotion(getPathMotion());
        }
        if ((this.mChangeFlags & 8) != 0) {
            transition.setEpicenterCallback(getEpicenterCallback());
        }
        return this;
    }

    private void addTransitionInternal(Transition transition) {
        this.mTransitions.add(transition);
        transition.mParent = this;
    }

    public int getTransitionCount() {
        return this.mTransitions.size();
    }

    public Transition getTransitionAt(int i) {
        if (i < 0 || i >= this.mTransitions.size()) {
            return null;
        }
        return this.mTransitions.get(i);
    }

    public TransitionSet setDuration(long j) {
        ArrayList<Transition> arrayList;
        super.setDuration(j);
        if (this.mDuration >= 0 && (arrayList = this.mTransitions) != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.mTransitions.get(i).setDuration(j);
            }
        }
        return this;
    }

    public TransitionSet setStartDelay(long j) {
        return (TransitionSet) super.setStartDelay(j);
    }

    public TransitionSet setInterpolator(TimeInterpolator timeInterpolator) {
        this.mChangeFlags |= 1;
        ArrayList<Transition> arrayList = this.mTransitions;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                this.mTransitions.get(i).setInterpolator(timeInterpolator);
            }
        }
        return (TransitionSet) super.setInterpolator(timeInterpolator);
    }

    public TransitionSet addTarget(View view) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            this.mTransitions.get(i).addTarget(view);
        }
        return (TransitionSet) super.addTarget(view);
    }

    public TransitionSet addTarget(int i) {
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            this.mTransitions.get(i2).addTarget(i);
        }
        return (TransitionSet) super.addTarget(i);
    }

    public TransitionSet addTarget(String str) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            this.mTransitions.get(i).addTarget(str);
        }
        return (TransitionSet) super.addTarget(str);
    }

    public TransitionSet addTarget(Class<?> cls) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            this.mTransitions.get(i).addTarget(cls);
        }
        return (TransitionSet) super.addTarget(cls);
    }

    public TransitionSet addListener(Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.addListener(transitionListener);
    }

    public TransitionSet removeTarget(int i) {
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            this.mTransitions.get(i2).removeTarget(i);
        }
        return (TransitionSet) super.removeTarget(i);
    }

    public TransitionSet removeTarget(View view) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            this.mTransitions.get(i).removeTarget(view);
        }
        return (TransitionSet) super.removeTarget(view);
    }

    public TransitionSet removeTarget(Class<?> cls) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            this.mTransitions.get(i).removeTarget(cls);
        }
        return (TransitionSet) super.removeTarget(cls);
    }

    public TransitionSet removeTarget(String str) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            this.mTransitions.get(i).removeTarget(str);
        }
        return (TransitionSet) super.removeTarget(str);
    }

    public Transition excludeTarget(View view, boolean z) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            this.mTransitions.get(i).excludeTarget(view, z);
        }
        return super.excludeTarget(view, z);
    }

    public Transition excludeTarget(String str, boolean z) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            this.mTransitions.get(i).excludeTarget(str, z);
        }
        return super.excludeTarget(str, z);
    }

    public Transition excludeTarget(int i, boolean z) {
        for (int i2 = 0; i2 < this.mTransitions.size(); i2++) {
            this.mTransitions.get(i2).excludeTarget(i, z);
        }
        return super.excludeTarget(i, z);
    }

    public Transition excludeTarget(Class<?> cls, boolean z) {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            this.mTransitions.get(i).excludeTarget(cls, z);
        }
        return super.excludeTarget(cls, z);
    }

    public TransitionSet removeListener(Transition.TransitionListener transitionListener) {
        return (TransitionSet) super.removeListener(transitionListener);
    }

    public void setPathMotion(PathMotion pathMotion) {
        super.setPathMotion(pathMotion);
        this.mChangeFlags |= 4;
        if (this.mTransitions != null) {
            for (int i = 0; i < this.mTransitions.size(); i++) {
                this.mTransitions.get(i).setPathMotion(pathMotion);
            }
        }
    }

    public TransitionSet removeTransition(Transition transition) {
        this.mTransitions.remove(transition);
        transition.mParent = null;
        return this;
    }

    private void setupStartEndListeners() {
        TransitionSetListener transitionSetListener = new TransitionSetListener(this);
        Iterator<Transition> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            it.next().addListener(transitionSetListener);
        }
        this.mCurrentListeners = this.mTransitions.size();
    }

    static class TransitionSetListener extends TransitionListenerAdapter {
        TransitionSet mTransitionSet;

        TransitionSetListener(TransitionSet transitionSet) {
            this.mTransitionSet = transitionSet;
        }

        public void onTransitionStart(Transition transition) {
            if (!this.mTransitionSet.mStarted) {
                this.mTransitionSet.start();
                this.mTransitionSet.mStarted = true;
            }
        }

        public void onTransitionEnd(Transition transition) {
            TransitionSet transitionSet = this.mTransitionSet;
            transitionSet.mCurrentListeners--;
            if (this.mTransitionSet.mCurrentListeners == 0) {
                this.mTransitionSet.mStarted = false;
                this.mTransitionSet.end();
            }
            transition.removeListener(this);
        }
    }

    /* access modifiers changed from: package-private */
    public void createAnimators(ViewGroup viewGroup, TransitionValuesMaps transitionValuesMaps, TransitionValuesMaps transitionValuesMaps2, ArrayList<TransitionValues> arrayList, ArrayList<TransitionValues> arrayList2) {
        long startDelay = getStartDelay();
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            Transition transition = this.mTransitions.get(i);
            if (startDelay > 0 && (this.mPlayTogether || i == 0)) {
                long startDelay2 = transition.getStartDelay();
                if (startDelay2 > 0) {
                    transition.setStartDelay(startDelay2 + startDelay);
                } else {
                    transition.setStartDelay(startDelay);
                }
            }
            transition.createAnimators(viewGroup, transitionValuesMaps, transitionValuesMaps2, arrayList, arrayList2);
        }
    }

    /* access modifiers changed from: protected */
    public void runAnimators() {
        if (this.mTransitions.isEmpty()) {
            start();
            end();
            return;
        }
        setupStartEndListeners();
        if (!this.mPlayTogether) {
            for (int i = 1; i < this.mTransitions.size(); i++) {
                final Transition transition = this.mTransitions.get(i);
                this.mTransitions.get(i - 1).addListener(new TransitionListenerAdapter() {
                    public void onTransitionEnd(Transition transition) {
                        transition.runAnimators();
                        transition.removeListener(this);
                    }
                });
            }
            Transition transition2 = this.mTransitions.get(0);
            if (transition2 != null) {
                transition2.runAnimators();
                return;
            }
            return;
        }
        Iterator<Transition> it = this.mTransitions.iterator();
        while (it.hasNext()) {
            it.next().runAnimators();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasAnimators() {
        for (int i = 0; i < this.mTransitions.size(); i++) {
            if (this.mTransitions.get(i).hasAnimators()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void prepareAnimatorsForSeeking() {
        this.mTotalDuration = 0;
        AnonymousClass2 r0 = new TransitionListenerAdapter() {
            public void onTransitionCancel(Transition transition) {
                TransitionSet.this.mTransitions.remove(transition);
                if (!TransitionSet.this.hasAnimators()) {
                    TransitionSet.this.notifyListeners(Transition.TransitionNotification.ON_CANCEL, false);
                    TransitionSet.this.mEnded = true;
                    TransitionSet.this.notifyListeners(Transition.TransitionNotification.ON_END, false);
                }
            }
        };
        for (int i = 0; i < this.mTransitions.size(); i++) {
            Transition transition = this.mTransitions.get(i);
            transition.addListener(r0);
            transition.prepareAnimatorsForSeeking();
            long totalDurationMillis = transition.getTotalDurationMillis();
            if (this.mPlayTogether) {
                this.mTotalDuration = Math.max(this.mTotalDuration, totalDurationMillis);
            } else {
                transition.mSeekOffsetInParent = this.mTotalDuration;
                this.mTotalDuration += totalDurationMillis;
            }
        }
    }

    private int indexOfTransition(long j) {
        for (int i = 1; i < this.mTransitions.size(); i++) {
            if (this.mTransitions.get(i).mSeekOffsetInParent > j) {
                return i - 1;
            }
        }
        return this.mTransitions.size() - 1;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setCurrentPlayTimeMillis(long r19, long r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r3 = r21
            long r5 = r18.getTotalDurationMillis()
            androidx.transition.TransitionSet r7 = r0.mParent
            r8 = 0
            if (r7 == 0) goto L_0x0021
            int r7 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r7 >= 0) goto L_0x0018
            int r7 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r7 < 0) goto L_0x0020
        L_0x0018:
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0021
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0021
        L_0x0020:
            return
        L_0x0021:
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r10 = 0
            if (r7 >= 0) goto L_0x0028
            r12 = 1
            goto L_0x0029
        L_0x0028:
            r12 = r10
        L_0x0029:
            int r13 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r13 < 0) goto L_0x0031
            int r14 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r14 < 0) goto L_0x0039
        L_0x0031:
            int r14 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r14 > 0) goto L_0x0040
            int r14 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r14 <= 0) goto L_0x0040
        L_0x0039:
            r0.mEnded = r10
            androidx.transition.Transition$TransitionNotification r14 = androidx.transition.Transition.TransitionNotification.ON_START
            r0.notifyListeners(r14, r12)
        L_0x0040:
            boolean r14 = r0.mPlayTogether
            if (r14 == 0) goto L_0x005d
        L_0x0044:
            java.util.ArrayList<androidx.transition.Transition> r7 = r0.mTransitions
            int r7 = r7.size()
            if (r10 >= r7) goto L_0x005a
            java.util.ArrayList<androidx.transition.Transition> r7 = r0.mTransitions
            java.lang.Object r7 = r7.get(r10)
            androidx.transition.Transition r7 = (androidx.transition.Transition) r7
            r7.setCurrentPlayTimeMillis(r1, r3)
            int r10 = r10 + 1
            goto L_0x0044
        L_0x005a:
            r16 = r12
            goto L_0x00a5
        L_0x005d:
            int r10 = r0.indexOfTransition(r3)
            if (r7 < 0) goto L_0x0088
        L_0x0063:
            java.util.ArrayList<androidx.transition.Transition> r7 = r0.mTransitions
            int r7 = r7.size()
            if (r10 >= r7) goto L_0x005a
            java.util.ArrayList<androidx.transition.Transition> r7 = r0.mTransitions
            java.lang.Object r7 = r7.get(r10)
            androidx.transition.Transition r7 = (androidx.transition.Transition) r7
            long r14 = r7.mSeekOffsetInParent
            r16 = r12
            long r11 = r1 - r14
            int r17 = (r11 > r8 ? 1 : (r11 == r8 ? 0 : -1))
            if (r17 >= 0) goto L_0x007e
            goto L_0x00a5
        L_0x007e:
            long r14 = r3 - r14
            r7.setCurrentPlayTimeMillis(r11, r14)
            int r10 = r10 + 1
            r12 = r16
            goto L_0x0063
        L_0x0088:
            r16 = r12
        L_0x008a:
            if (r10 < 0) goto L_0x00a5
            java.util.ArrayList<androidx.transition.Transition> r7 = r0.mTransitions
            java.lang.Object r7 = r7.get(r10)
            androidx.transition.Transition r7 = (androidx.transition.Transition) r7
            long r11 = r7.mSeekOffsetInParent
            long r14 = r1 - r11
            long r11 = r3 - r11
            r7.setCurrentPlayTimeMillis(r14, r11)
            int r7 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r7 < 0) goto L_0x00a2
            goto L_0x00a5
        L_0x00a2:
            int r10 = r10 + -1
            goto L_0x008a
        L_0x00a5:
            androidx.transition.TransitionSet r7 = r0.mParent
            if (r7 == 0) goto L_0x00c3
            int r1 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x00b1
            int r2 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r2 <= 0) goto L_0x00b7
        L_0x00b1:
            if (r13 >= 0) goto L_0x00c3
            int r2 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x00c3
        L_0x00b7:
            if (r1 <= 0) goto L_0x00bc
            r1 = 1
            r0.mEnded = r1
        L_0x00bc:
            androidx.transition.Transition$TransitionNotification r1 = androidx.transition.Transition.TransitionNotification.ON_END
            r11 = r16
            r0.notifyListeners(r1, r11)
        L_0x00c3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.TransitionSet.setCurrentPlayTimeMillis(long, long):void");
    }

    public boolean isSeekingSupported() {
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            if (!this.mTransitions.get(i).isSeekingSupported()) {
                return false;
            }
        }
        return true;
    }

    public void captureStartValues(TransitionValues transitionValues) {
        if (isValidTarget(transitionValues.view)) {
            Iterator<Transition> it = this.mTransitions.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.isValidTarget(transitionValues.view)) {
                    next.captureStartValues(transitionValues);
                    transitionValues.mTargetedTransitions.add(next);
                }
            }
        }
    }

    public void captureEndValues(TransitionValues transitionValues) {
        if (isValidTarget(transitionValues.view)) {
            Iterator<Transition> it = this.mTransitions.iterator();
            while (it.hasNext()) {
                Transition next = it.next();
                if (next.isValidTarget(transitionValues.view)) {
                    next.captureEndValues(transitionValues);
                    transitionValues.mTargetedTransitions.add(next);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void capturePropagationValues(TransitionValues transitionValues) {
        super.capturePropagationValues(transitionValues);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).capturePropagationValues(transitionValues);
        }
    }

    public void pause(View view) {
        super.pause(view);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).pause(view);
        }
    }

    public void resume(View view) {
        super.resume(view);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).resume(view);
        }
    }

    /* access modifiers changed from: protected */
    public void cancel() {
        super.cancel();
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public void forceToEnd(ViewGroup viewGroup) {
        super.forceToEnd(viewGroup);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).forceToEnd(viewGroup);
        }
    }

    /* access modifiers changed from: package-private */
    public void setCanRemoveViews(boolean z) {
        super.setCanRemoveViews(z);
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).setCanRemoveViews(z);
        }
    }

    public void setPropagation(TransitionPropagation transitionPropagation) {
        super.setPropagation(transitionPropagation);
        this.mChangeFlags |= 2;
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).setPropagation(transitionPropagation);
        }
    }

    public void setEpicenterCallback(Transition.EpicenterCallback epicenterCallback) {
        super.setEpicenterCallback(epicenterCallback);
        this.mChangeFlags |= 8;
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            this.mTransitions.get(i).setEpicenterCallback(epicenterCallback);
        }
    }

    /* access modifiers changed from: package-private */
    public String toString(String str) {
        String transition = super.toString(str);
        for (int i = 0; i < this.mTransitions.size(); i++) {
            transition = transition + "\n" + this.mTransitions.get(i).toString(str + "  ");
        }
        return transition;
    }

    public Transition clone() {
        TransitionSet transitionSet = (TransitionSet) super.clone();
        transitionSet.mTransitions = new ArrayList<>();
        int size = this.mTransitions.size();
        for (int i = 0; i < size; i++) {
            transitionSet.addTransitionInternal(this.mTransitions.get(i).clone());
        }
        return transitionSet;
    }
}
