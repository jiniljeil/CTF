package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimatableView;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.internal.ExpandCollapseAnimationHelper;
import com.google.android.material.internal.MultiViewUpdateListener;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.search.SearchBar;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class SearchBarAnimationHelper {
    private static final long COLLAPSE_DURATION_MS = 250;
    private static final long COLLAPSE_FADE_IN_CHILDREN_DURATION_MS = 100;
    private static final long EXPAND_DURATION_MS = 300;
    private static final long EXPAND_FADE_OUT_CHILDREN_DURATION_MS = 75;
    private static final long ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_DURATION_MS = 250;
    private static final long ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_IN_START_DELAY_MS = 500;
    private static final long ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_OUT_START_DELAY_MS = 750;
    private static final long ON_LOAD_ANIM_SECONDARY_DURATION_MS = 250;
    private static final long ON_LOAD_ANIM_SECONDARY_START_DELAY_MS = 250;
    private final Set<AnimatorListenerAdapter> collapseAnimationListeners = new LinkedHashSet();
    /* access modifiers changed from: private */
    public boolean collapsing;
    private Animator defaultCenterViewAnimator;
    private final Set<AnimatorListenerAdapter> expandAnimationListeners = new LinkedHashSet();
    /* access modifiers changed from: private */
    public boolean expanding;
    private final Set<SearchBar.OnLoadAnimationCallback> onLoadAnimationCallbacks = new LinkedHashSet();
    private boolean onLoadAnimationFadeInEnabled = true;
    /* access modifiers changed from: private */
    public Animator runningExpandOrCollapseAnimator = null;
    private Animator secondaryViewAnimator;

    private interface OnLoadAnimationInvocation {
        void invoke(SearchBar.OnLoadAnimationCallback onLoadAnimationCallback);
    }

    SearchBarAnimationHelper() {
    }

    /* access modifiers changed from: package-private */
    public void startOnLoadAnimation(SearchBar searchBar) {
        dispatchOnLoadAnimation(new SearchBarAnimationHelper$$ExternalSyntheticLambda1());
        TextView textView = searchBar.getTextView();
        final View centerView = searchBar.getCenterView();
        View secondaryActionMenuItemView = ToolbarUtils.getSecondaryActionMenuItemView(searchBar);
        final Animator secondaryViewAnimator2 = getSecondaryViewAnimator(textView, secondaryActionMenuItemView);
        secondaryViewAnimator2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SearchBarAnimationHelper.this.dispatchOnLoadAnimation(new SearchBarAnimationHelper$1$$ExternalSyntheticLambda0());
            }
        });
        this.secondaryViewAnimator = secondaryViewAnimator2;
        textView.setAlpha(0.0f);
        if (secondaryActionMenuItemView != null) {
            secondaryActionMenuItemView.setAlpha(0.0f);
        }
        if (centerView instanceof AnimatableView) {
            Objects.requireNonNull(secondaryViewAnimator2);
            ((AnimatableView) centerView).startAnimation(new SearchBarAnimationHelper$$ExternalSyntheticLambda2(secondaryViewAnimator2));
        } else if (centerView != null) {
            centerView.setAlpha(0.0f);
            centerView.setVisibility(0);
            Animator defaultCenterViewAnimator2 = getDefaultCenterViewAnimator(centerView);
            this.defaultCenterViewAnimator = defaultCenterViewAnimator2;
            defaultCenterViewAnimator2.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    centerView.setVisibility(8);
                    secondaryViewAnimator2.start();
                }
            });
            defaultCenterViewAnimator2.start();
        } else {
            secondaryViewAnimator2.start();
        }
    }

    /* access modifiers changed from: package-private */
    public void stopOnLoadAnimation(SearchBar searchBar) {
        Animator animator = this.secondaryViewAnimator;
        if (animator != null) {
            animator.end();
        }
        Animator animator2 = this.defaultCenterViewAnimator;
        if (animator2 != null) {
            animator2.end();
        }
        View centerView = searchBar.getCenterView();
        if (centerView instanceof AnimatableView) {
            ((AnimatableView) centerView).stopAnimation();
        }
        if (centerView != null) {
            centerView.setAlpha(0.0f);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isOnLoadAnimationFadeInEnabled() {
        return this.onLoadAnimationFadeInEnabled;
    }

    /* access modifiers changed from: package-private */
    public void setOnLoadAnimationFadeInEnabled(boolean z) {
        this.onLoadAnimationFadeInEnabled = z;
    }

    /* access modifiers changed from: package-private */
    public void addOnLoadAnimationCallback(SearchBar.OnLoadAnimationCallback onLoadAnimationCallback) {
        this.onLoadAnimationCallbacks.add(onLoadAnimationCallback);
    }

    /* access modifiers changed from: package-private */
    public boolean removeOnLoadAnimationCallback(SearchBar.OnLoadAnimationCallback onLoadAnimationCallback) {
        return this.onLoadAnimationCallbacks.remove(onLoadAnimationCallback);
    }

    /* access modifiers changed from: private */
    public void dispatchOnLoadAnimation(OnLoadAnimationInvocation onLoadAnimationInvocation) {
        for (SearchBar.OnLoadAnimationCallback invoke : this.onLoadAnimationCallbacks) {
            onLoadAnimationInvocation.invoke(invoke);
        }
    }

    private Animator getDefaultCenterViewAnimator(View view) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener(view));
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        long j = 0;
        ofFloat.setDuration(this.onLoadAnimationFadeInEnabled ? 250 : 0);
        if (this.onLoadAnimationFadeInEnabled) {
            j = ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_IN_START_DELAY_MS;
        }
        ofFloat.setStartDelay(j);
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ofFloat2.addUpdateListener(MultiViewUpdateListener.alphaListener(view));
        ofFloat2.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        ofFloat2.setDuration(250);
        ofFloat2.setStartDelay(ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_OUT_START_DELAY_MS);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{ofFloat, ofFloat2});
        return animatorSet;
    }

    private Animator getSecondaryViewAnimator(TextView textView, View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setStartDelay(250);
        animatorSet.play(getTextViewAnimator(textView));
        if (view != null) {
            animatorSet.play(getSecondaryActionMenuItemAnimator(view));
        }
        return animatorSet;
    }

    private Animator getTextViewAnimator(TextView textView) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener(textView));
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        ofFloat.setDuration(250);
        return ofFloat;
    }

    private Animator getSecondaryActionMenuItemAnimator(View view) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener(view));
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        ofFloat.setDuration(250);
        return ofFloat;
    }

    /* access modifiers changed from: package-private */
    public void startExpandAnimation(SearchBar searchBar, View view, AppBarLayout appBarLayout, boolean z) {
        Animator animator;
        if (isCollapsing() && (animator = this.runningExpandOrCollapseAnimator) != null) {
            animator.cancel();
        }
        this.expanding = true;
        view.setVisibility(4);
        view.post(new SearchBarAnimationHelper$$ExternalSyntheticLambda3(this, searchBar, view, appBarLayout, z));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$startExpandAnimation$0$com-google-android-material-search-SearchBarAnimationHelper  reason: not valid java name */
    public /* synthetic */ void m115lambda$startExpandAnimation$0$comgoogleandroidmaterialsearchSearchBarAnimationHelper(SearchBar searchBar, View view, AppBarLayout appBarLayout, boolean z) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{getFadeOutChildrenAnimator(searchBar, view), getExpandAnimator(searchBar, view, appBarLayout)});
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                Animator unused = SearchBarAnimationHelper.this.runningExpandOrCollapseAnimator = null;
            }
        });
        for (AnimatorListenerAdapter addListener : this.expandAnimationListeners) {
            animatorSet.addListener(addListener);
        }
        if (z) {
            animatorSet.setDuration(0);
        }
        animatorSet.start();
        this.runningExpandOrCollapseAnimator = animatorSet;
    }

    private Animator getExpandAnimator(final SearchBar searchBar, View view, AppBarLayout appBarLayout) {
        return getExpandCollapseAnimationHelper(searchBar, view, appBarLayout).setDuration(EXPAND_DURATION_MS).addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                searchBar.setVisibility(4);
            }

            public void onAnimationEnd(Animator animator) {
                boolean unused = SearchBarAnimationHelper.this.expanding = false;
            }
        }).getExpandAnimator();
    }

    /* access modifiers changed from: package-private */
    public boolean isExpanding() {
        return this.expanding;
    }

    /* access modifiers changed from: package-private */
    public void addExpandAnimationListener(AnimatorListenerAdapter animatorListenerAdapter) {
        this.expandAnimationListeners.add(animatorListenerAdapter);
    }

    /* access modifiers changed from: package-private */
    public boolean removeExpandAnimationListener(AnimatorListenerAdapter animatorListenerAdapter) {
        return this.expandAnimationListeners.remove(animatorListenerAdapter);
    }

    /* access modifiers changed from: package-private */
    public void startCollapseAnimation(SearchBar searchBar, View view, AppBarLayout appBarLayout, boolean z) {
        Animator animator;
        if (isExpanding() && (animator = this.runningExpandOrCollapseAnimator) != null) {
            animator.cancel();
        }
        this.collapsing = true;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(new Animator[]{getCollapseAnimator(searchBar, view, appBarLayout), getFadeInChildrenAnimator(searchBar)});
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                Animator unused = SearchBarAnimationHelper.this.runningExpandOrCollapseAnimator = null;
            }
        });
        for (AnimatorListenerAdapter addListener : this.collapseAnimationListeners) {
            animatorSet.addListener(addListener);
        }
        if (z) {
            animatorSet.setDuration(0);
        }
        animatorSet.start();
        this.runningExpandOrCollapseAnimator = animatorSet;
    }

    private Animator getCollapseAnimator(final SearchBar searchBar, View view, AppBarLayout appBarLayout) {
        return getExpandCollapseAnimationHelper(searchBar, view, appBarLayout).setDuration(250).addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                searchBar.stopOnLoadAnimation();
            }

            public void onAnimationEnd(Animator animator) {
                searchBar.setVisibility(0);
                boolean unused = SearchBarAnimationHelper.this.collapsing = false;
            }
        }).getCollapseAnimator();
    }

    /* access modifiers changed from: package-private */
    public boolean isCollapsing() {
        return this.collapsing;
    }

    /* access modifiers changed from: package-private */
    public void addCollapseAnimationListener(AnimatorListenerAdapter animatorListenerAdapter) {
        this.collapseAnimationListeners.add(animatorListenerAdapter);
    }

    /* access modifiers changed from: package-private */
    public boolean removeCollapseAnimationListener(AnimatorListenerAdapter animatorListenerAdapter) {
        return this.collapseAnimationListeners.remove(animatorListenerAdapter);
    }

    private ExpandCollapseAnimationHelper getExpandCollapseAnimationHelper(SearchBar searchBar, View view, AppBarLayout appBarLayout) {
        return new ExpandCollapseAnimationHelper(searchBar, view).setAdditionalUpdateListener(getExpandedViewBackgroundUpdateListener(searchBar, view)).setCollapsedViewOffsetY(appBarLayout != null ? appBarLayout.getTop() : 0).addEndAnchoredViews((Collection<View>) getEndAnchoredViews(view));
    }

    private ValueAnimator.AnimatorUpdateListener getExpandedViewBackgroundUpdateListener(SearchBar searchBar, View view) {
        MaterialShapeDrawable createWithElevationOverlay = MaterialShapeDrawable.createWithElevationOverlay(view.getContext());
        createWithElevationOverlay.setCornerSize(searchBar.getCornerSize());
        createWithElevationOverlay.setElevation(ViewCompat.getElevation(searchBar));
        return new SearchBarAnimationHelper$$ExternalSyntheticLambda4(createWithElevationOverlay, view);
    }

    static /* synthetic */ void lambda$getExpandedViewBackgroundUpdateListener$1(MaterialShapeDrawable materialShapeDrawable, View view, ValueAnimator valueAnimator) {
        materialShapeDrawable.setInterpolation(1.0f - valueAnimator.getAnimatedFraction());
        ViewCompat.setBackground(view, materialShapeDrawable);
        view.setAlpha(1.0f);
    }

    private Animator getFadeOutChildrenAnimator(SearchBar searchBar, View view) {
        List<View> fadeChildren = getFadeChildren(searchBar);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener((Collection<View>) fadeChildren));
        ofFloat.addUpdateListener(new SearchBarAnimationHelper$$ExternalSyntheticLambda0(view));
        ofFloat.setDuration(75);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return ofFloat;
    }

    private Animator getFadeInChildrenAnimator(SearchBar searchBar) {
        List<View> fadeChildren = getFadeChildren(searchBar);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener((Collection<View>) fadeChildren));
        ofFloat.setDuration(COLLAPSE_FADE_IN_CHILDREN_DURATION_MS);
        ofFloat.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return ofFloat;
    }

    private List<View> getFadeChildren(SearchBar searchBar) {
        List<View> children = ViewUtils.getChildren(searchBar);
        if (searchBar.getCenterView() != null) {
            children.remove(searchBar.getCenterView());
        }
        return children;
    }

    private List<View> getEndAnchoredViews(View view) {
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(view);
        ArrayList arrayList = new ArrayList();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                if ((!isLayoutRtl && (childAt instanceof ActionMenuView)) || (isLayoutRtl && !(childAt instanceof ActionMenuView))) {
                    arrayList.add(childAt);
                }
            }
        }
        return arrayList;
    }
}
