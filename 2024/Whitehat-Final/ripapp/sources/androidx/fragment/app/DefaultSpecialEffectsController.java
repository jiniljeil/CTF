package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class DefaultSpecialEffectsController extends SpecialEffectsController {
    DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
    }

    /* access modifiers changed from: package-private */
    public void executeOperations(List<SpecialEffectsController.Operation> list, boolean z) {
        boolean z2 = z;
        SpecialEffectsController.Operation operation = null;
        SpecialEffectsController.Operation operation2 = null;
        for (SpecialEffectsController.Operation next : list) {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(next.getFragment().mView);
            int i = AnonymousClass10.$SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State[next.getFinalState().ordinal()];
            if (i == 1 || i == 2 || i == 3) {
                if (from == SpecialEffectsController.Operation.State.VISIBLE && operation == null) {
                    operation = next;
                }
            } else if (i == 4 && from != SpecialEffectsController.Operation.State.VISIBLE) {
                operation2 = next;
            }
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Executing operations from " + operation + " to " + operation2);
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        final ArrayList<SpecialEffectsController.Operation> arrayList3 = new ArrayList<>(list);
        syncAnimations(list);
        for (final SpecialEffectsController.Operation next2 : list) {
            CancellationSignal cancellationSignal = new CancellationSignal();
            next2.markStartedSpecialEffect(cancellationSignal);
            arrayList.add(new AnimationInfo(next2, cancellationSignal, z2));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            next2.markStartedSpecialEffect(cancellationSignal2);
            boolean z3 = false;
            if (z2) {
                if (next2 != operation) {
                    arrayList2.add(new TransitionInfo(next2, cancellationSignal2, z2, z3));
                    next2.addCompletionListener(new Runnable() {
                        public void run() {
                            if (arrayList3.contains(next2)) {
                                arrayList3.remove(next2);
                                DefaultSpecialEffectsController.this.applyContainerChanges(next2);
                            }
                        }
                    });
                }
            } else if (next2 != operation2) {
                arrayList2.add(new TransitionInfo(next2, cancellationSignal2, z2, z3));
                next2.addCompletionListener(new Runnable() {
                    public void run() {
                        if (arrayList3.contains(next2)) {
                            arrayList3.remove(next2);
                            DefaultSpecialEffectsController.this.applyContainerChanges(next2);
                        }
                    }
                });
            }
            z3 = true;
            arrayList2.add(new TransitionInfo(next2, cancellationSignal2, z2, z3));
            next2.addCompletionListener(new Runnable() {
                public void run() {
                    if (arrayList3.contains(next2)) {
                        arrayList3.remove(next2);
                        DefaultSpecialEffectsController.this.applyContainerChanges(next2);
                    }
                }
            });
        }
        Map<SpecialEffectsController.Operation, Boolean> startTransitions = startTransitions(arrayList2, arrayList3, z, operation, operation2);
        startAnimations(arrayList, arrayList3, startTransitions.containsValue(true), startTransitions);
        for (SpecialEffectsController.Operation applyContainerChanges : arrayList3) {
            applyContainerChanges(applyContainerChanges);
        }
        arrayList3.clear();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Completed executing operations from " + operation + " to " + operation2);
        }
    }

    /* renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10  reason: invalid class name */
    static /* synthetic */ class AnonymousClass10 {
        static final /* synthetic */ int[] $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                androidx.fragment.app.SpecialEffectsController$Operation$State[] r0 = androidx.fragment.app.SpecialEffectsController.Operation.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State = r0
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.GONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.INVISIBLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.REMOVED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.fragment.app.SpecialEffectsController$Operation$State r1 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.AnonymousClass10.<clinit>():void");
        }
    }

    private void syncAnimations(List<SpecialEffectsController.Operation> list) {
        Fragment fragment = list.get(list.size() - 1).getFragment();
        for (SpecialEffectsController.Operation next : list) {
            next.getFragment().mAnimationInfo.mEnterAnim = fragment.mAnimationInfo.mEnterAnim;
            next.getFragment().mAnimationInfo.mExitAnim = fragment.mAnimationInfo.mExitAnim;
            next.getFragment().mAnimationInfo.mPopEnterAnim = fragment.mAnimationInfo.mPopEnterAnim;
            next.getFragment().mAnimationInfo.mPopExitAnim = fragment.mAnimationInfo.mPopExitAnim;
        }
    }

    private void startAnimations(List<AnimationInfo> list, List<SpecialEffectsController.Operation> list2, boolean z, Map<SpecialEffectsController.Operation, Boolean> map) {
        int i;
        Context context;
        boolean z2;
        View view;
        int i2;
        final SpecialEffectsController.Operation operation;
        ViewGroup container = getContainer();
        Context context2 = container.getContext();
        ArrayList arrayList = new ArrayList();
        Iterator<AnimationInfo> it = list.iterator();
        boolean z3 = false;
        while (true) {
            i = 2;
            if (!it.hasNext()) {
                break;
            }
            AnimationInfo next = it.next();
            if (next.isVisibilityUnchanged()) {
                next.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator animation = next.getAnimation(context2);
                if (animation == null) {
                    next.completeSpecialEffect();
                } else {
                    Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(next);
                    } else {
                        SpecialEffectsController.Operation operation2 = next.getOperation();
                        Fragment fragment = operation2.getFragment();
                        if (Boolean.TRUE.equals(map.get(operation2))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            next.completeSpecialEffect();
                        } else {
                            boolean z4 = operation2.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            List<SpecialEffectsController.Operation> list3 = list2;
                            if (z4) {
                                list3.remove(operation2);
                            }
                            View view2 = fragment.mView;
                            container.startViewTransition(view2);
                            AnonymousClass2 r20 = r0;
                            View view3 = view2;
                            final ViewGroup viewGroup = container;
                            final View view4 = view3;
                            SpecialEffectsController.Operation operation3 = operation2;
                            final boolean z5 = z4;
                            Animator animator2 = animator;
                            final SpecialEffectsController.Operation operation4 = operation3;
                            final Animator animator3 = animator2;
                            final AnimationInfo animationInfo = next;
                            AnonymousClass2 r0 = new AnimatorListenerAdapter() {
                                public void onAnimationEnd(Animator animator) {
                                    viewGroup.endViewTransition(view4);
                                    if (z5) {
                                        operation4.getFinalState().applyState(view4);
                                    }
                                    animationInfo.completeSpecialEffect();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v(FragmentManager.TAG, "Animator from operation " + operation4 + " has ended.");
                                    }
                                }
                            };
                            animator3.addListener(r0);
                            animator3.setTarget(view3);
                            animator3.start();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                operation = operation3;
                                Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has started.");
                            } else {
                                operation = operation3;
                            }
                            next.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() {
                                public void onCancel() {
                                    animator3.end();
                                    if (FragmentManager.isLoggingEnabled(2)) {
                                        Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has been canceled.");
                                    }
                                }
                            });
                            z3 = true;
                        }
                    }
                }
            }
            Map<SpecialEffectsController.Operation, Boolean> map2 = map;
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            AnimationInfo animationInfo2 = (AnimationInfo) it2.next();
            SpecialEffectsController.Operation operation5 = animationInfo2.getOperation();
            Fragment fragment2 = operation5.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(i)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (z3) {
                if (FragmentManager.isLoggingEnabled(i)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                View view5 = fragment2.mView;
                Animation animation2 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(animationInfo2.getAnimation(context2))).animation);
                if (operation5.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                    view5.startAnimation(animation2);
                    animationInfo2.completeSpecialEffect();
                    z2 = z3;
                    context = context2;
                    i2 = i;
                    view = view5;
                } else {
                    container.startViewTransition(view5);
                    final SpecialEffectsController.Operation operation6 = operation5;
                    AnonymousClass4 r14 = r0;
                    final ViewGroup viewGroup2 = container;
                    z2 = z3;
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, container, view5);
                    final View view6 = view5;
                    context = context2;
                    view = view5;
                    final AnimationInfo animationInfo3 = animationInfo2;
                    AnonymousClass4 r02 = new Animation.AnimationListener() {
                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationStart(Animation animation) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Animation from operation " + operation6 + " has reached onAnimationStart.");
                            }
                        }

                        public void onAnimationEnd(Animation animation) {
                            viewGroup2.post(new Runnable() {
                                public void run() {
                                    viewGroup2.endViewTransition(view6);
                                    animationInfo3.completeSpecialEffect();
                                }
                            });
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Animation from operation " + operation6 + " has ended.");
                            }
                        }
                    };
                    endViewTransitionAnimation.setAnimationListener(r14);
                    view.startAnimation(endViewTransitionAnimation);
                    i2 = 2;
                    if (FragmentManager.isLoggingEnabled(2)) {
                        Log.v(FragmentManager.TAG, "Animation from operation " + operation5 + " has started.");
                    }
                }
                CancellationSignal signal = animationInfo2.getSignal();
                final View view7 = view;
                final ViewGroup viewGroup3 = container;
                final AnimationInfo animationInfo4 = animationInfo2;
                AnonymousClass5 r8 = r0;
                final SpecialEffectsController.Operation operation7 = operation5;
                AnonymousClass5 r03 = new CancellationSignal.OnCancelListener() {
                    public void onCancel() {
                        view7.clearAnimation();
                        viewGroup3.endViewTransition(view7);
                        animationInfo4.completeSpecialEffect();
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Log.v(FragmentManager.TAG, "Animation from operation " + operation7 + " has been cancelled.");
                        }
                    }
                };
                signal.setOnCancelListener(r8);
                i = i2;
                z3 = z2;
                context2 = context;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0334, code lost:
        r0 = (android.view.View) r12.get(r10.get(r1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Map<androidx.fragment.app.SpecialEffectsController.Operation, java.lang.Boolean> startTransitions(java.util.List<androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo> r34, java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r35, boolean r36, androidx.fragment.app.SpecialEffectsController.Operation r37, androidx.fragment.app.SpecialEffectsController.Operation r38) {
        /*
            r33 = this;
            r6 = r33
            r7 = r36
            r8 = r37
            r9 = r38
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            java.util.Iterator r0 = r34.iterator()
            r15 = 0
        L_0x0012:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0063
            java.lang.Object r1 = r0.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r1 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r1
            boolean r2 = r1.isVisibilityUnchanged()
            if (r2 == 0) goto L_0x0025
            goto L_0x0012
        L_0x0025:
            androidx.fragment.app.FragmentTransitionImpl r2 = r1.getHandlingImpl()
            if (r15 != 0) goto L_0x002d
            r15 = r2
            goto L_0x0012
        L_0x002d:
            if (r2 == 0) goto L_0x0012
            if (r15 != r2) goto L_0x0032
            goto L_0x0012
        L_0x0032:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Mixing framework transitions and AndroidX transitions is not allowed. Fragment "
            r2.<init>(r3)
            androidx.fragment.app.SpecialEffectsController$Operation r3 = r1.getOperation()
            androidx.fragment.app.Fragment r3 = r3.getFragment()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = " returned Transition "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.Object r1 = r1.getTransition()
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r2 = " which uses a different Transition  type than other Fragments."
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0063:
            r14 = 0
            if (r15 != 0) goto L_0x0086
            java.util.Iterator r0 = r34.iterator()
        L_0x006a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0085
            java.lang.Object r1 = r0.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r1 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r1
            androidx.fragment.app.SpecialEffectsController$Operation r2 = r1.getOperation()
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r14)
            r10.put(r2, r3)
            r1.completeSpecialEffect()
            goto L_0x006a
        L_0x0085:
            return r10
        L_0x0086:
            android.view.View r13 = new android.view.View
            android.view.ViewGroup r0 = r33.getContainer()
            android.content.Context r0 = r0.getContext()
            r13.<init>(r0)
            android.graphics.Rect r12 = new android.graphics.Rect
            r12.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            androidx.collection.ArrayMap r3 = new androidx.collection.ArrayMap
            r3.<init>()
            java.util.Iterator r20 = r34.iterator()
            r21 = r14
            r0 = 0
            r2 = 0
        L_0x00af:
            boolean r1 = r20.hasNext()
            r22 = 2
            java.lang.String r14 = "FragmentManager"
            if (r1 == 0) goto L_0x03a1
            java.lang.Object r1 = r20.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r1 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r1
            boolean r17 = r1.hasSharedElementTransition()
            if (r17 == 0) goto L_0x0387
            if (r8 == 0) goto L_0x0387
            if (r9 == 0) goto L_0x0387
            java.lang.Object r0 = r1.getSharedElementTransition()
            java.lang.Object r0 = r15.cloneTransition(r0)
            java.lang.Object r1 = r15.wrapTransitionInSet(r0)
            androidx.fragment.app.Fragment r0 = r38.getFragment()
            java.util.ArrayList r0 = r0.getSharedElementSourceNames()
            androidx.fragment.app.Fragment r17 = r37.getFragment()
            java.util.ArrayList r11 = r17.getSharedElementSourceNames()
            androidx.fragment.app.Fragment r17 = r37.getFragment()
            r18 = r1
            java.util.ArrayList r1 = r17.getSharedElementTargetNames()
            r17 = r2
            r23 = r10
            r2 = 0
        L_0x00f4:
            int r10 = r1.size()
            if (r2 >= r10) goto L_0x0115
            java.lang.Object r10 = r1.get(r2)
            int r10 = r0.indexOf(r10)
            r19 = r1
            r1 = -1
            if (r10 == r1) goto L_0x0110
            java.lang.Object r1 = r11.get(r2)
            java.lang.String r1 = (java.lang.String) r1
            r0.set(r10, r1)
        L_0x0110:
            int r2 = r2 + 1
            r1 = r19
            goto L_0x00f4
        L_0x0115:
            androidx.fragment.app.Fragment r1 = r38.getFragment()
            java.util.ArrayList r10 = r1.getSharedElementTargetNames()
            if (r7 != 0) goto L_0x0130
            androidx.fragment.app.Fragment r1 = r37.getFragment()
            androidx.core.app.SharedElementCallback r1 = r1.getExitTransitionCallback()
            androidx.fragment.app.Fragment r2 = r38.getFragment()
            androidx.core.app.SharedElementCallback r2 = r2.getEnterTransitionCallback()
            goto L_0x0140
        L_0x0130:
            androidx.fragment.app.Fragment r1 = r37.getFragment()
            androidx.core.app.SharedElementCallback r1 = r1.getEnterTransitionCallback()
            androidx.fragment.app.Fragment r2 = r38.getFragment()
            androidx.core.app.SharedElementCallback r2 = r2.getExitTransitionCallback()
        L_0x0140:
            int r11 = r0.size()
            r19 = r13
            r13 = 0
        L_0x0147:
            if (r13 >= r11) goto L_0x0167
            java.lang.Object r24 = r0.get(r13)
            r25 = r11
            r11 = r24
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r24 = r10.get(r13)
            r26 = r12
            r12 = r24
            java.lang.String r12 = (java.lang.String) r12
            r3.put(r11, r12)
            int r13 = r13 + 1
            r11 = r25
            r12 = r26
            goto L_0x0147
        L_0x0167:
            r26 = r12
            boolean r11 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r22)
            if (r11 == 0) goto L_0x01c5
            java.lang.String r11 = ">>> entering view names <<<"
            android.util.Log.v(r14, r11)
            java.util.Iterator r11 = r10.iterator()
        L_0x0178:
            boolean r12 = r11.hasNext()
            java.lang.String r13 = "Name: "
            if (r12 == 0) goto L_0x019b
            java.lang.Object r12 = r11.next()
            java.lang.String r12 = (java.lang.String) r12
            r24 = r11
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r13)
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            android.util.Log.v(r14, r11)
            r11 = r24
            goto L_0x0178
        L_0x019b:
            java.lang.String r11 = ">>> exiting view names <<<"
            android.util.Log.v(r14, r11)
            java.util.Iterator r11 = r0.iterator()
        L_0x01a4:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x01c5
            java.lang.Object r12 = r11.next()
            java.lang.String r12 = (java.lang.String) r12
            r24 = r11
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r13)
            java.lang.StringBuilder r11 = r11.append(r12)
            java.lang.String r11 = r11.toString()
            android.util.Log.v(r14, r11)
            r11 = r24
            goto L_0x01a4
        L_0x01c5:
            androidx.collection.ArrayMap r11 = new androidx.collection.ArrayMap
            r11.<init>()
            androidx.fragment.app.Fragment r12 = r37.getFragment()
            android.view.View r12 = r12.mView
            r6.findNamedViews(r11, r12)
            r11.retainAll(r0)
            if (r1 == 0) goto L_0x0230
            boolean r12 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r22)
            if (r12 == 0) goto L_0x01f0
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r13 = "Executing exit callback for operation "
            r12.<init>(r13)
            java.lang.StringBuilder r12 = r12.append(r8)
            java.lang.String r12 = r12.toString()
            android.util.Log.v(r14, r12)
        L_0x01f0:
            r1.onMapSharedElements(r0, r11)
            int r1 = r0.size()
            r12 = 1
            int r1 = r1 - r12
        L_0x01f9:
            if (r1 < 0) goto L_0x022d
            java.lang.Object r12 = r0.get(r1)
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r13 = r11.get(r12)
            android.view.View r13 = (android.view.View) r13
            if (r13 != 0) goto L_0x020f
            r3.remove(r12)
            r24 = r0
            goto L_0x0228
        L_0x020f:
            r24 = r0
            java.lang.String r0 = androidx.core.view.ViewCompat.getTransitionName(r13)
            boolean r0 = r12.equals(r0)
            if (r0 != 0) goto L_0x0228
            java.lang.Object r0 = r3.remove(r12)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r12 = androidx.core.view.ViewCompat.getTransitionName(r13)
            r3.put(r12, r0)
        L_0x0228:
            int r1 = r1 + -1
            r0 = r24
            goto L_0x01f9
        L_0x022d:
            r24 = r0
            goto L_0x0239
        L_0x0230:
            r24 = r0
            java.util.Set r0 = r11.keySet()
            r3.retainAll(r0)
        L_0x0239:
            androidx.collection.ArrayMap r12 = new androidx.collection.ArrayMap
            r12.<init>()
            androidx.fragment.app.Fragment r0 = r38.getFragment()
            android.view.View r0 = r0.mView
            r6.findNamedViews(r12, r0)
            r12.retainAll(r10)
            java.util.Collection r0 = r3.values()
            r12.retainAll(r0)
            if (r2 == 0) goto L_0x02a8
            boolean r0 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r22)
            if (r0 == 0) goto L_0x026b
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Executing enter callback for operation "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r9)
            java.lang.String r0 = r0.toString()
            android.util.Log.v(r14, r0)
        L_0x026b:
            r2.onMapSharedElements(r10, r12)
            int r0 = r10.size()
            r1 = 1
            int r0 = r0 - r1
        L_0x0274:
            if (r0 < 0) goto L_0x02ab
            java.lang.Object r1 = r10.get(r0)
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r12.get(r1)
            android.view.View r2 = (android.view.View) r2
            if (r2 != 0) goto L_0x028e
            java.lang.String r1 = androidx.fragment.app.FragmentTransition.findKeyForValue(r3, r1)
            if (r1 == 0) goto L_0x02a5
            r3.remove(r1)
            goto L_0x02a5
        L_0x028e:
            java.lang.String r13 = androidx.core.view.ViewCompat.getTransitionName(r2)
            boolean r13 = r1.equals(r13)
            if (r13 != 0) goto L_0x02a5
            java.lang.String r1 = androidx.fragment.app.FragmentTransition.findKeyForValue(r3, r1)
            if (r1 == 0) goto L_0x02a5
            java.lang.String r2 = androidx.core.view.ViewCompat.getTransitionName(r2)
            r3.put(r1, r2)
        L_0x02a5:
            int r0 = r0 + -1
            goto L_0x0274
        L_0x02a8:
            androidx.fragment.app.FragmentTransition.retainValues(r3, r12)
        L_0x02ab:
            java.util.Set r0 = r3.keySet()
            r6.retainMatchingViews(r11, r0)
            java.util.Collection r0 = r3.values()
            r6.retainMatchingViews(r12, r0)
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L_0x02d7
            r5.clear()
            r4.clear()
            r25 = r3
            r3 = r9
            r11 = r15
            r2 = r17
            r10 = r23
            r0 = 0
            r1 = 0
            r9 = r4
            r15 = r5
            r5 = r19
            r4 = r26
            goto L_0x0394
        L_0x02d7:
            androidx.fragment.app.Fragment r0 = r38.getFragment()
            androidx.fragment.app.Fragment r1 = r37.getFragment()
            r2 = 1
            androidx.fragment.app.FragmentTransition.callSharedElementStartEnd(r0, r1, r7, r11, r2)
            android.view.ViewGroup r13 = r33.getContainer()
            androidx.fragment.app.DefaultSpecialEffectsController$6 r14 = new androidx.fragment.app.DefaultSpecialEffectsController$6
            r2 = r24
            r0 = r14
            r7 = r18
            r1 = r33
            r27 = r17
            r2 = r38
            r25 = r3
            r3 = r37
            r9 = r4
            r4 = r36
            r8 = r5
            r5 = r12
            r0.<init>(r2, r3, r4, r5)
            androidx.core.view.OneShotPreDrawListener.add(r13, r14)
            java.util.Collection r0 = r11.values()
            r8.addAll(r0)
            boolean r0 = r24.isEmpty()
            if (r0 != 0) goto L_0x0324
            r0 = r24
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r11.get(r0)
            android.view.View r0 = (android.view.View) r0
            r15.setEpicenter((java.lang.Object) r7, (android.view.View) r0)
            r2 = r0
            goto L_0x0327
        L_0x0324:
            r1 = 0
            r2 = r27
        L_0x0327:
            java.util.Collection r0 = r12.values()
            r9.addAll(r0)
            boolean r0 = r10.isEmpty()
            if (r0 != 0) goto L_0x0355
            java.lang.Object r0 = r10.get(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r0 = r12.get(r0)
            android.view.View r0 = (android.view.View) r0
            if (r0 == 0) goto L_0x0355
            android.view.ViewGroup r3 = r33.getContainer()
            androidx.fragment.app.DefaultSpecialEffectsController$7 r4 = new androidx.fragment.app.DefaultSpecialEffectsController$7
            r5 = r26
            r4.<init>(r15, r0, r5)
            androidx.core.view.OneShotPreDrawListener.add(r3, r4)
            r0 = r19
            r21 = 1
            goto L_0x0359
        L_0x0355:
            r5 = r26
            r0 = r19
        L_0x0359:
            r15.setSharedElementTargets(r7, r0, r8)
            r16 = 0
            r17 = 0
            r14 = 0
            r3 = 0
            r4 = r5
            r12 = r15
            r5 = r0
            r13 = r7
            r11 = r15
            r15 = r3
            r18 = r7
            r19 = r9
            r12.scheduleRemoveTargets(r13, r14, r15, r16, r17, r18, r19)
            r0 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            r15 = r8
            r10 = r23
            r8 = r37
            r10.put(r8, r3)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r3 = r38
            r10.put(r3, r0)
            r0 = r7
            goto L_0x0394
        L_0x0387:
            r27 = r2
            r25 = r3
            r3 = r9
            r11 = r15
            r1 = 0
            r9 = r4
            r15 = r5
            r4 = r12
            r5 = r13
            r2 = r27
        L_0x0394:
            r7 = r36
            r14 = r1
            r12 = r4
            r13 = r5
            r4 = r9
            r5 = r15
            r9 = r3
            r15 = r11
            r3 = r25
            goto L_0x00af
        L_0x03a1:
            r27 = r2
            r25 = r3
            r3 = r9
            r11 = r15
            r1 = 0
            r9 = r4
            r15 = r5
            r4 = r12
            r5 = r13
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r7 = r34.iterator()
            r12 = 0
            r13 = 0
        L_0x03b7:
            boolean r16 = r7.hasNext()
            if (r16 == 0) goto L_0x04f7
            java.lang.Object r16 = r7.next()
            r20 = r16
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r20 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r20
            boolean r16 = r20.isVisibilityUnchanged()
            if (r16 == 0) goto L_0x03e2
            r36 = r7
            androidx.fragment.app.SpecialEffectsController$Operation r7 = r20.getOperation()
            r16 = r12
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r1)
            r10.put(r7, r12)
            r20.completeSpecialEffect()
            r7 = r36
            r12 = r16
            goto L_0x03b7
        L_0x03e2:
            r36 = r7
            r16 = r12
            java.lang.Object r7 = r20.getTransition()
            java.lang.Object r7 = r11.cloneTransition(r7)
            androidx.fragment.app.SpecialEffectsController$Operation r12 = r20.getOperation()
            if (r0 == 0) goto L_0x03fb
            if (r12 == r8) goto L_0x03f8
            if (r12 != r3) goto L_0x03fb
        L_0x03f8:
            r17 = 1
            goto L_0x03fd
        L_0x03fb:
            r17 = r1
        L_0x03fd:
            if (r7 != 0) goto L_0x041c
            if (r17 != 0) goto L_0x040b
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r1)
            r10.put(r12, r7)
            r20.completeSpecialEffect()
        L_0x040b:
            r12 = r35
            r29 = r5
            r30 = r9
            r5 = r13
            r31 = r14
            r32 = r15
            r1 = r27
            r13 = 0
            r14 = 1
            goto L_0x04e5
        L_0x041c:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r18 = r13
            androidx.fragment.app.Fragment r13 = r12.getFragment()
            android.view.View r13 = r13.mView
            r6.captureTransitioningViews(r1, r13)
            if (r17 == 0) goto L_0x0437
            if (r12 != r8) goto L_0x0434
            r1.removeAll(r15)
            goto L_0x0437
        L_0x0434:
            r1.removeAll(r9)
        L_0x0437:
            boolean r13 = r1.isEmpty()
            if (r13 == 0) goto L_0x0450
            r11.addTarget(r7, r5)
            r29 = r5
            r30 = r9
            r13 = r12
            r31 = r14
            r32 = r15
            r9 = r16
            r5 = r18
            r12 = r35
            goto L_0x04b0
        L_0x0450:
            r11.addTargets(r7, r1)
            r19 = 0
            r24 = 0
            r17 = 0
            r26 = 0
            r28 = r12
            r13 = r16
            r12 = r11
            r29 = r5
            r30 = r9
            r9 = r13
            r5 = r18
            r13 = r7
            r31 = r14
            r14 = r7
            r32 = r15
            r15 = r1
            r16 = r17
            r17 = r26
            r18 = r19
            r19 = r24
            r12.scheduleRemoveTargets(r13, r14, r15, r16, r17, r18, r19)
            androidx.fragment.app.SpecialEffectsController$Operation$State r12 = r28.getFinalState()
            androidx.fragment.app.SpecialEffectsController$Operation$State r13 = androidx.fragment.app.SpecialEffectsController.Operation.State.GONE
            if (r12 != r13) goto L_0x04ac
            r12 = r35
            r13 = r28
            r12.remove(r13)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>(r1)
            androidx.fragment.app.Fragment r15 = r13.getFragment()
            android.view.View r15 = r15.mView
            r14.remove(r15)
            androidx.fragment.app.Fragment r15 = r13.getFragment()
            android.view.View r15 = r15.mView
            r11.scheduleHideFragmentView(r7, r15, r14)
            android.view.ViewGroup r14 = r33.getContainer()
            androidx.fragment.app.DefaultSpecialEffectsController$8 r15 = new androidx.fragment.app.DefaultSpecialEffectsController$8
            r15.<init>(r1)
            androidx.core.view.OneShotPreDrawListener.add(r14, r15)
            goto L_0x04b0
        L_0x04ac:
            r12 = r35
            r13 = r28
        L_0x04b0:
            androidx.fragment.app.SpecialEffectsController$Operation$State r14 = r13.getFinalState()
            androidx.fragment.app.SpecialEffectsController$Operation$State r15 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r14 != r15) goto L_0x04c3
            r2.addAll(r1)
            if (r21 == 0) goto L_0x04c0
            r11.setEpicenter((java.lang.Object) r7, (android.graphics.Rect) r4)
        L_0x04c0:
            r1 = r27
            goto L_0x04c8
        L_0x04c3:
            r1 = r27
            r11.setEpicenter((java.lang.Object) r7, (android.view.View) r1)
        L_0x04c8:
            r14 = 1
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r14)
            r10.put(r13, r15)
            boolean r13 = r20.isOverlapAllowed()
            if (r13 == 0) goto L_0x04de
            r13 = 0
            java.lang.Object r5 = r11.mergeTransitionsTogether(r5, r7, r13)
            r16 = r9
            goto L_0x04e5
        L_0x04de:
            r13 = 0
            java.lang.Object r7 = r11.mergeTransitionsTogether(r9, r7, r13)
            r16 = r7
        L_0x04e5:
            r7 = r36
            r27 = r1
            r13 = r5
            r12 = r16
            r5 = r29
            r9 = r30
            r14 = r31
            r15 = r32
            r1 = 0
            goto L_0x03b7
        L_0x04f7:
            r30 = r9
            r9 = r12
            r5 = r13
            r31 = r14
            r32 = r15
            r14 = 1
            java.lang.Object r1 = r11.mergeTransitionsInSequence(r5, r9, r0)
            if (r1 != 0) goto L_0x0507
            return r10
        L_0x0507:
            java.util.Iterator r4 = r34.iterator()
        L_0x050b:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0589
            java.lang.Object r5 = r4.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r5 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r5
            boolean r7 = r5.isVisibilityUnchanged()
            if (r7 == 0) goto L_0x051e
            goto L_0x050b
        L_0x051e:
            java.lang.Object r7 = r5.getTransition()
            androidx.fragment.app.SpecialEffectsController$Operation r9 = r5.getOperation()
            if (r0 == 0) goto L_0x052e
            if (r9 == r8) goto L_0x052c
            if (r9 != r3) goto L_0x052e
        L_0x052c:
            r12 = r14
            goto L_0x052f
        L_0x052e:
            r12 = 0
        L_0x052f:
            if (r7 != 0) goto L_0x0537
            if (r12 == 0) goto L_0x0534
            goto L_0x0537
        L_0x0534:
            r12 = r31
            goto L_0x0586
        L_0x0537:
            android.view.ViewGroup r7 = r33.getContainer()
            boolean r7 = androidx.core.view.ViewCompat.isLaidOut(r7)
            if (r7 != 0) goto L_0x0570
            boolean r7 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r22)
            if (r7 == 0) goto L_0x056a
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r12 = "SpecialEffectsController: Container "
            r7.<init>(r12)
            android.view.ViewGroup r12 = r33.getContainer()
            java.lang.StringBuilder r7 = r7.append(r12)
            java.lang.String r12 = " has not been laid out. Completing operation "
            java.lang.StringBuilder r7 = r7.append(r12)
            java.lang.StringBuilder r7 = r7.append(r9)
            java.lang.String r7 = r7.toString()
            r12 = r31
            android.util.Log.v(r12, r7)
            goto L_0x056c
        L_0x056a:
            r12 = r31
        L_0x056c:
            r5.completeSpecialEffect()
            goto L_0x0586
        L_0x0570:
            r12 = r31
            androidx.fragment.app.SpecialEffectsController$Operation r7 = r5.getOperation()
            androidx.fragment.app.Fragment r7 = r7.getFragment()
            androidx.core.os.CancellationSignal r13 = r5.getSignal()
            androidx.fragment.app.DefaultSpecialEffectsController$9 r15 = new androidx.fragment.app.DefaultSpecialEffectsController$9
            r15.<init>(r5, r9)
            r11.setListenerForTransitionEnd(r7, r1, r13, r15)
        L_0x0586:
            r31 = r12
            goto L_0x050b
        L_0x0589:
            r12 = r31
            android.view.ViewGroup r3 = r33.getContainer()
            boolean r3 = androidx.core.view.ViewCompat.isLaidOut(r3)
            if (r3 != 0) goto L_0x0596
            return r10
        L_0x0596:
            r3 = 4
            androidx.fragment.app.FragmentTransition.setViewVisibility(r2, r3)
            r3 = r30
            java.util.ArrayList r16 = r11.prepareSetNameOverridesReordered(r3)
            boolean r4 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r22)
            if (r4 == 0) goto L_0x0613
            java.lang.String r4 = ">>>>> Beginning transition <<<<<"
            android.util.Log.v(r12, r4)
            java.lang.String r4 = ">>>>> SharedElementFirstOutViews <<<<<"
            android.util.Log.v(r12, r4)
            java.util.Iterator r4 = r32.iterator()
        L_0x05b4:
            boolean r5 = r4.hasNext()
            java.lang.String r7 = " Name: "
            java.lang.String r8 = "View: "
            if (r5 == 0) goto L_0x05e1
            java.lang.Object r5 = r4.next()
            android.view.View r5 = (android.view.View) r5
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r8)
            java.lang.StringBuilder r8 = r9.append(r5)
            java.lang.StringBuilder r7 = r8.append(r7)
            java.lang.String r5 = androidx.core.view.ViewCompat.getTransitionName(r5)
            java.lang.StringBuilder r5 = r7.append(r5)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r12, r5)
            goto L_0x05b4
        L_0x05e1:
            java.lang.String r4 = ">>>>> SharedElementLastInViews <<<<<"
            android.util.Log.v(r12, r4)
            java.util.Iterator r4 = r3.iterator()
        L_0x05ea:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0613
            java.lang.Object r5 = r4.next()
            android.view.View r5 = (android.view.View) r5
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r8)
            java.lang.StringBuilder r9 = r9.append(r5)
            java.lang.StringBuilder r9 = r9.append(r7)
            java.lang.String r5 = androidx.core.view.ViewCompat.getTransitionName(r5)
            java.lang.StringBuilder r5 = r9.append(r5)
            java.lang.String r5 = r5.toString()
            android.util.Log.v(r12, r5)
            goto L_0x05ea
        L_0x0613:
            android.view.ViewGroup r4 = r33.getContainer()
            r11.beginDelayedTransition(r4, r1)
            android.view.ViewGroup r13 = r33.getContainer()
            r12 = r11
            r14 = r32
            r15 = r3
            r17 = r25
            r12.setNameOverridesReordered(r13, r14, r15, r16, r17)
            r1 = 0
            androidx.fragment.app.FragmentTransition.setViewVisibility(r2, r1)
            r1 = r32
            r11.swapSharedElementTargets(r0, r1, r3)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.startTransitions(java.util.List, java.util.List, boolean, androidx.fragment.app.SpecialEffectsController$Operation, androidx.fragment.app.SpecialEffectsController$Operation):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    public void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName((View) it.next().getValue()))) {
                it.remove();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!ViewGroupCompat.isTransitionGroup(viewGroup)) {
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt.getVisibility() == 0) {
                        captureTransitioningViews(arrayList, childAt);
                    }
                }
            } else if (!arrayList.contains(view)) {
                arrayList.add(viewGroup);
            }
        } else if (!arrayList.contains(view)) {
            arrayList.add(view);
        }
    }

    /* access modifiers changed from: package-private */
    public void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void applyContainerChanges(SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        /* access modifiers changed from: package-private */
        public SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        /* access modifiers changed from: package-private */
        public CancellationSignal getSignal() {
            return this.mSignal;
        }

        /* access modifiers changed from: package-private */
        public boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State from = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return from == finalState || !(from == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        /* access modifiers changed from: package-private */
        public void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }
    }

    private static class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mIsPop;
        private boolean mLoadedAnim = false;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            this.mIsPop = z;
        }

        /* access modifiers changed from: package-private */
        public FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.mIsPop);
            this.mAnimation = loadAnimation;
            this.mLoadedAnim = true;
            return loadAnimation;
        }
    }

    private static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            Object obj;
            Object obj2;
            boolean z3;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (z) {
                    obj2 = operation.getFragment().getReenterTransition();
                } else {
                    obj2 = operation.getFragment().getEnterTransition();
                }
                this.mTransition = obj2;
                if (z) {
                    z3 = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    z3 = operation.getFragment().getAllowEnterTransitionOverlap();
                }
                this.mOverlapAllowed = z3;
            } else {
                if (z) {
                    obj = operation.getFragment().getReturnTransition();
                } else {
                    obj = operation.getFragment().getExitTransition();
                }
                this.mTransition = obj;
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        /* access modifiers changed from: package-private */
        public Object getTransition() {
            return this.mTransition;
        }

        /* access modifiers changed from: package-private */
        public boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        /* access modifiers changed from: package-private */
        public FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
