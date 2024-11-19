package com.google.android.material.search;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.BackEventCompat;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.internal.FadeThroughDrawable;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.motion.MaterialMainContainerBackHelper;
import com.google.android.material.shape.MaterialShapeUtils;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class SearchView extends FrameLayout implements CoordinatorLayout.AttachedBehavior, MaterialBackHandler {
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_SearchView;
    private static final long TALKBACK_FOCUS_CHANGE_DELAY_MS = 100;
    private boolean animatedMenuItems;
    private boolean animatedNavigationIcon;
    private boolean autoShowKeyboard;
    private final boolean backHandlingEnabled;
    private final MaterialBackOrchestrator backOrchestrator;
    private final int backgroundColor;
    final View backgroundView;
    private Map<View, Integer> childImportantForAccessibilityMap;
    final ImageButton clearButton;
    final TouchObserverFrameLayout contentContainer;
    private TransitionState currentTransitionState;
    final View divider;
    final Toolbar dummyToolbar;
    final EditText editText;
    private final ElevationOverlayProvider elevationOverlayProvider;
    final FrameLayout headerContainer;
    private final boolean layoutInflated;
    final ClippableRoundedCornerLayout rootView;
    final View scrim;
    private SearchBar searchBar;
    final TextView searchPrefix;
    private final SearchViewAnimationHelper searchViewAnimationHelper;
    private int softInputMode;
    final View statusBarSpacer;
    private boolean statusBarSpacerEnabledOverride;
    final MaterialToolbar toolbar;
    final FrameLayout toolbarContainer;
    private final Set<TransitionListener> transitionListeners;
    private boolean useWindowInsetsController;

    public interface TransitionListener {
        void onStateChanged(SearchView searchView, TransitionState transitionState, TransitionState transitionState2);
    }

    public enum TransitionState {
        HIDING,
        HIDDEN,
        SHOWING,
        SHOWN
    }

    static /* synthetic */ boolean lambda$setUpRootView$0(View view, MotionEvent motionEvent) {
        return true;
    }

    public SearchView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialSearchViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SearchView(android.content.Context r9, android.util.AttributeSet r10, int r11) {
        /*
            r8 = this;
            int r4 = DEF_STYLE_RES
            android.content.Context r9 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r9, r10, r11, r4)
            r8.<init>(r9, r10, r11)
            com.google.android.material.motion.MaterialBackOrchestrator r9 = new com.google.android.material.motion.MaterialBackOrchestrator
            r9.<init>(r8)
            r8.backOrchestrator = r9
            java.util.LinkedHashSet r9 = new java.util.LinkedHashSet
            r9.<init>()
            r8.transitionListeners = r9
            r9 = 16
            r8.softInputMode = r9
            com.google.android.material.search.SearchView$TransitionState r9 = com.google.android.material.search.SearchView.TransitionState.HIDDEN
            r8.currentTransitionState = r9
            android.content.Context r9 = r8.getContext()
            int[] r2 = com.google.android.material.R.styleable.SearchView
            r6 = 0
            int[] r5 = new int[r6]
            r0 = r9
            r1 = r10
            r3 = r11
            android.content.res.TypedArray r10 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r0, r1, r2, r3, r4, r5)
            int r11 = com.google.android.material.R.styleable.SearchView_backgroundTint
            int r11 = r10.getColor(r11, r6)
            r8.backgroundColor = r11
            int r11 = com.google.android.material.R.styleable.SearchView_headerLayout
            r0 = -1
            int r11 = r10.getResourceId(r11, r0)
            int r1 = com.google.android.material.R.styleable.SearchView_android_textAppearance
            int r0 = r10.getResourceId(r1, r0)
            int r1 = com.google.android.material.R.styleable.SearchView_android_text
            java.lang.String r1 = r10.getString(r1)
            int r2 = com.google.android.material.R.styleable.SearchView_android_hint
            java.lang.String r2 = r10.getString(r2)
            int r3 = com.google.android.material.R.styleable.SearchView_searchPrefixText
            java.lang.String r3 = r10.getString(r3)
            int r4 = com.google.android.material.R.styleable.SearchView_useDrawerArrowDrawable
            boolean r4 = r10.getBoolean(r4, r6)
            int r5 = com.google.android.material.R.styleable.SearchView_animateNavigationIcon
            r7 = 1
            boolean r5 = r10.getBoolean(r5, r7)
            r8.animatedNavigationIcon = r5
            int r5 = com.google.android.material.R.styleable.SearchView_animateMenuItems
            boolean r5 = r10.getBoolean(r5, r7)
            r8.animatedMenuItems = r5
            int r5 = com.google.android.material.R.styleable.SearchView_hideNavigationIcon
            boolean r5 = r10.getBoolean(r5, r6)
            int r6 = com.google.android.material.R.styleable.SearchView_autoShowKeyboard
            boolean r6 = r10.getBoolean(r6, r7)
            r8.autoShowKeyboard = r6
            int r6 = com.google.android.material.R.styleable.SearchView_backHandlingEnabled
            boolean r6 = r10.getBoolean(r6, r7)
            r8.backHandlingEnabled = r6
            r10.recycle()
            android.view.LayoutInflater r10 = android.view.LayoutInflater.from(r9)
            int r6 = com.google.android.material.R.layout.mtrl_search_view
            r10.inflate(r6, r8)
            r8.layoutInflated = r7
            int r10 = com.google.android.material.R.id.open_search_view_scrim
            android.view.View r10 = r8.findViewById(r10)
            r8.scrim = r10
            int r10 = com.google.android.material.R.id.open_search_view_root
            android.view.View r10 = r8.findViewById(r10)
            com.google.android.material.internal.ClippableRoundedCornerLayout r10 = (com.google.android.material.internal.ClippableRoundedCornerLayout) r10
            r8.rootView = r10
            int r10 = com.google.android.material.R.id.open_search_view_background
            android.view.View r10 = r8.findViewById(r10)
            r8.backgroundView = r10
            int r10 = com.google.android.material.R.id.open_search_view_status_bar_spacer
            android.view.View r10 = r8.findViewById(r10)
            r8.statusBarSpacer = r10
            int r10 = com.google.android.material.R.id.open_search_view_header_container
            android.view.View r10 = r8.findViewById(r10)
            android.widget.FrameLayout r10 = (android.widget.FrameLayout) r10
            r8.headerContainer = r10
            int r10 = com.google.android.material.R.id.open_search_view_toolbar_container
            android.view.View r10 = r8.findViewById(r10)
            android.widget.FrameLayout r10 = (android.widget.FrameLayout) r10
            r8.toolbarContainer = r10
            int r10 = com.google.android.material.R.id.open_search_view_toolbar
            android.view.View r10 = r8.findViewById(r10)
            com.google.android.material.appbar.MaterialToolbar r10 = (com.google.android.material.appbar.MaterialToolbar) r10
            r8.toolbar = r10
            int r10 = com.google.android.material.R.id.open_search_view_dummy_toolbar
            android.view.View r10 = r8.findViewById(r10)
            androidx.appcompat.widget.Toolbar r10 = (androidx.appcompat.widget.Toolbar) r10
            r8.dummyToolbar = r10
            int r10 = com.google.android.material.R.id.open_search_view_search_prefix
            android.view.View r10 = r8.findViewById(r10)
            android.widget.TextView r10 = (android.widget.TextView) r10
            r8.searchPrefix = r10
            int r10 = com.google.android.material.R.id.open_search_view_edit_text
            android.view.View r10 = r8.findViewById(r10)
            android.widget.EditText r10 = (android.widget.EditText) r10
            r8.editText = r10
            int r10 = com.google.android.material.R.id.open_search_view_clear_button
            android.view.View r10 = r8.findViewById(r10)
            android.widget.ImageButton r10 = (android.widget.ImageButton) r10
            r8.clearButton = r10
            int r10 = com.google.android.material.R.id.open_search_view_divider
            android.view.View r10 = r8.findViewById(r10)
            r8.divider = r10
            int r10 = com.google.android.material.R.id.open_search_view_content_container
            android.view.View r10 = r8.findViewById(r10)
            com.google.android.material.internal.TouchObserverFrameLayout r10 = (com.google.android.material.internal.TouchObserverFrameLayout) r10
            r8.contentContainer = r10
            com.google.android.material.search.SearchViewAnimationHelper r10 = new com.google.android.material.search.SearchViewAnimationHelper
            r10.<init>(r8)
            r8.searchViewAnimationHelper = r10
            com.google.android.material.elevation.ElevationOverlayProvider r10 = new com.google.android.material.elevation.ElevationOverlayProvider
            r10.<init>(r9)
            r8.elevationOverlayProvider = r10
            r8.setUpRootView()
            r8.setUpBackgroundViewElevationOverlay()
            r8.setUpHeaderLayout(r11)
            r8.setSearchPrefixText(r3)
            r8.setUpEditText(r0, r1, r2)
            r8.setUpBackButton(r4, r5)
            r8.setUpClearButton()
            r8.setUpContentOnTouchListener()
            r8.setUpInsetListeners()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.search.SearchView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.layoutInflated) {
            this.contentContainer.addView(view, i, layoutParams);
        } else {
            super.addView(view, i, layoutParams);
        }
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        updateSoftInputMode();
    }

    public void setElevation(float f) {
        super.setElevation(f);
        setUpBackgroundViewElevationOverlay(f);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    public CoordinatorLayout.Behavior<SearchView> getBehavior() {
        return new Behavior();
    }

    public void startBackProgress(BackEventCompat backEventCompat) {
        if (!isHiddenOrHiding() && this.searchBar != null) {
            this.searchViewAnimationHelper.startBackProgress(backEventCompat);
        }
    }

    public void updateBackProgress(BackEventCompat backEventCompat) {
        if (!isHiddenOrHiding() && this.searchBar != null && Build.VERSION.SDK_INT >= 34) {
            this.searchViewAnimationHelper.updateBackProgress(backEventCompat);
        }
    }

    public void handleBackInvoked() {
        if (!isHiddenOrHiding()) {
            BackEventCompat onHandleBackInvoked = this.searchViewAnimationHelper.onHandleBackInvoked();
            if (Build.VERSION.SDK_INT < 34 || this.searchBar == null || onHandleBackInvoked == null) {
                hide();
            } else {
                this.searchViewAnimationHelper.finishBackProgress();
            }
        }
    }

    public void cancelBackProgress() {
        if (!isHiddenOrHiding() && this.searchBar != null && Build.VERSION.SDK_INT >= 34) {
            this.searchViewAnimationHelper.cancelBackProgress();
        }
    }

    /* access modifiers changed from: package-private */
    public MaterialMainContainerBackHelper getBackHelper() {
        return this.searchViewAnimationHelper.getBackHelper();
    }

    private boolean isHiddenOrHiding() {
        return this.currentTransitionState.equals(TransitionState.HIDDEN) || this.currentTransitionState.equals(TransitionState.HIDING);
    }

    private Window getActivityWindow() {
        Activity activity = ContextUtils.getActivity(getContext());
        if (activity == null) {
            return null;
        }
        return activity.getWindow();
    }

    private void setUpRootView() {
        this.rootView.setOnTouchListener(new SearchView$$ExternalSyntheticLambda7());
    }

    private void setUpBackgroundViewElevationOverlay() {
        setUpBackgroundViewElevationOverlay(getOverlayElevation());
    }

    private void setUpBackgroundViewElevationOverlay(float f) {
        ElevationOverlayProvider elevationOverlayProvider2 = this.elevationOverlayProvider;
        if (elevationOverlayProvider2 != null && this.backgroundView != null) {
            this.backgroundView.setBackgroundColor(elevationOverlayProvider2.compositeOverlayIfNeeded(this.backgroundColor, f));
        }
    }

    private float getOverlayElevation() {
        SearchBar searchBar2 = this.searchBar;
        if (searchBar2 != null) {
            return searchBar2.getCompatElevation();
        }
        return getResources().getDimension(R.dimen.m3_searchview_elevation);
    }

    private void setUpHeaderLayout(int i) {
        if (i != -1) {
            addHeaderView(LayoutInflater.from(getContext()).inflate(i, this.headerContainer, false));
        }
    }

    private void setUpEditText(int i, String str, String str2) {
        if (i != -1) {
            TextViewCompat.setTextAppearance(this.editText, i);
        }
        this.editText.setText(str);
        this.editText.setHint(str2);
    }

    private void setUpBackButton(boolean z, boolean z2) {
        if (z2) {
            this.toolbar.setNavigationIcon((Drawable) null);
            return;
        }
        this.toolbar.setNavigationOnClickListener(new SearchView$$ExternalSyntheticLambda9(this));
        if (z) {
            DrawerArrowDrawable drawerArrowDrawable = new DrawerArrowDrawable(getContext());
            drawerArrowDrawable.setColor(MaterialColors.getColor(this, R.attr.colorOnSurface));
            this.toolbar.setNavigationIcon(drawerArrowDrawable);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setUpBackButton$1$com-google-android-material-search-SearchView  reason: not valid java name */
    public /* synthetic */ void m118lambda$setUpBackButton$1$comgoogleandroidmaterialsearchSearchView(View view) {
        hide();
    }

    private void setUpClearButton() {
        this.clearButton.setOnClickListener(new SearchView$$ExternalSyntheticLambda1(this));
        this.editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SearchView.this.clearButton.setVisibility(charSequence.length() > 0 ? 0 : 8);
            }
        });
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setUpClearButton$2$com-google-android-material-search-SearchView  reason: not valid java name */
    public /* synthetic */ void m119lambda$setUpClearButton$2$comgoogleandroidmaterialsearchSearchView(View view) {
        clearText();
        requestFocusAndShowKeyboardIfNeeded();
    }

    private void setUpContentOnTouchListener() {
        this.contentContainer.setOnTouchListener(new SearchView$$ExternalSyntheticLambda8(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setUpContentOnTouchListener$3$com-google-android-material-search-SearchView  reason: not valid java name */
    public /* synthetic */ boolean m120lambda$setUpContentOnTouchListener$3$comgoogleandroidmaterialsearchSearchView(View view, MotionEvent motionEvent) {
        if (!isAdjustNothingSoftInputMode()) {
            return false;
        }
        clearFocusAndHideKeyboard();
        return false;
    }

    private void setUpStatusBarSpacer(int i) {
        if (this.statusBarSpacer.getLayoutParams().height != i) {
            this.statusBarSpacer.getLayoutParams().height = i;
            this.statusBarSpacer.requestLayout();
        }
    }

    private int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private void updateNavigationIconIfNeeded() {
        MaterialToolbar materialToolbar = this.toolbar;
        if (materialToolbar != null && !isNavigationIconDrawerArrowDrawable(materialToolbar)) {
            int defaultNavigationIconResource = getDefaultNavigationIconResource();
            if (this.searchBar == null) {
                this.toolbar.setNavigationIcon(defaultNavigationIconResource);
                return;
            }
            Drawable wrap = DrawableCompat.wrap(AppCompatResources.getDrawable(getContext(), defaultNavigationIconResource).mutate());
            if (this.toolbar.getNavigationIconTint() != null) {
                DrawableCompat.setTint(wrap, this.toolbar.getNavigationIconTint().intValue());
            }
            this.toolbar.setNavigationIcon(new FadeThroughDrawable(this.searchBar.getNavigationIcon(), wrap));
            updateNavigationIconProgressIfNeeded();
        }
    }

    private boolean isNavigationIconDrawerArrowDrawable(Toolbar toolbar2) {
        return DrawableCompat.unwrap(toolbar2.getNavigationIcon()) instanceof DrawerArrowDrawable;
    }

    private void setUpInsetListeners() {
        setUpToolbarInsetListener();
        setUpDividerInsetListener();
        setUpStatusBarSpacerInsetListener();
    }

    private void setUpToolbarInsetListener() {
        ViewUtils.doOnApplyWindowInsets(this.toolbar, new SearchView$$ExternalSyntheticLambda3(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setUpToolbarInsetListener$4$com-google-android-material-search-SearchView  reason: not valid java name */
    public /* synthetic */ WindowInsetsCompat m122lambda$setUpToolbarInsetListener$4$comgoogleandroidmaterialsearchSearchView(View view, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this.toolbar);
        this.toolbar.setPadding((isLayoutRtl ? relativePadding.end : relativePadding.start) + windowInsetsCompat.getSystemWindowInsetLeft(), relativePadding.top, (isLayoutRtl ? relativePadding.start : relativePadding.end) + windowInsetsCompat.getSystemWindowInsetRight(), relativePadding.bottom);
        return windowInsetsCompat;
    }

    private void setUpStatusBarSpacerInsetListener() {
        setUpStatusBarSpacer(getStatusBarHeight());
        ViewCompat.setOnApplyWindowInsetsListener(this.statusBarSpacer, new SearchView$$ExternalSyntheticLambda4(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setUpStatusBarSpacerInsetListener$5$com-google-android-material-search-SearchView  reason: not valid java name */
    public /* synthetic */ WindowInsetsCompat m121lambda$setUpStatusBarSpacerInsetListener$5$comgoogleandroidmaterialsearchSearchView(View view, WindowInsetsCompat windowInsetsCompat) {
        int systemWindowInsetTop = windowInsetsCompat.getSystemWindowInsetTop();
        setUpStatusBarSpacer(systemWindowInsetTop);
        if (!this.statusBarSpacerEnabledOverride) {
            setStatusBarSpacerEnabledInternal(systemWindowInsetTop > 0);
        }
        return windowInsetsCompat;
    }

    private void setUpDividerInsetListener() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.divider.getLayoutParams();
        ViewCompat.setOnApplyWindowInsetsListener(this.divider, new SearchView$$ExternalSyntheticLambda0(marginLayoutParams, marginLayoutParams.leftMargin, marginLayoutParams.rightMargin));
    }

    static /* synthetic */ WindowInsetsCompat lambda$setUpDividerInsetListener$6(ViewGroup.MarginLayoutParams marginLayoutParams, int i, int i2, View view, WindowInsetsCompat windowInsetsCompat) {
        marginLayoutParams.leftMargin = i + windowInsetsCompat.getSystemWindowInsetLeft();
        marginLayoutParams.rightMargin = i2 + windowInsetsCompat.getSystemWindowInsetRight();
        return windowInsetsCompat;
    }

    public boolean isSetupWithSearchBar() {
        return this.searchBar != null;
    }

    public void setupWithSearchBar(SearchBar searchBar2) {
        this.searchBar = searchBar2;
        this.searchViewAnimationHelper.setSearchBar(searchBar2);
        if (searchBar2 != null) {
            searchBar2.setOnClickListener(new SearchView$$ExternalSyntheticLambda5(this));
            if (Build.VERSION.SDK_INT >= 34) {
                try {
                    searchBar2.setHandwritingDelegatorCallback(new SearchView$$ExternalSyntheticLambda6(this));
                    this.editText.setIsHandwritingDelegate(true);
                } catch (LinkageError unused) {
                }
            }
        }
        updateNavigationIconIfNeeded();
        setUpBackgroundViewElevationOverlay();
        updateListeningForBackCallbacks(getCurrentTransitionState());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setupWithSearchBar$7$com-google-android-material-search-SearchView  reason: not valid java name */
    public /* synthetic */ void m123lambda$setupWithSearchBar$7$comgoogleandroidmaterialsearchSearchView(View view) {
        show();
    }

    public void addHeaderView(View view) {
        this.headerContainer.addView(view);
        this.headerContainer.setVisibility(0);
    }

    public void removeHeaderView(View view) {
        this.headerContainer.removeView(view);
        if (this.headerContainer.getChildCount() == 0) {
            this.headerContainer.setVisibility(8);
        }
    }

    public void removeAllHeaderViews() {
        this.headerContainer.removeAllViews();
        this.headerContainer.setVisibility(8);
    }

    public void setAnimatedNavigationIcon(boolean z) {
        this.animatedNavigationIcon = z;
    }

    public boolean isAnimatedNavigationIcon() {
        return this.animatedNavigationIcon;
    }

    public void setMenuItemsAnimated(boolean z) {
        this.animatedMenuItems = z;
    }

    public boolean isMenuItemsAnimated() {
        return this.animatedMenuItems;
    }

    public void setAutoShowKeyboard(boolean z) {
        this.autoShowKeyboard = z;
    }

    public boolean isAutoShowKeyboard() {
        return this.autoShowKeyboard;
    }

    public void setUseWindowInsetsController(boolean z) {
        this.useWindowInsetsController = z;
    }

    public boolean isUseWindowInsetsController() {
        return this.useWindowInsetsController;
    }

    public void addTransitionListener(TransitionListener transitionListener) {
        this.transitionListeners.add(transitionListener);
    }

    public void removeTransitionListener(TransitionListener transitionListener) {
        this.transitionListeners.remove(transitionListener);
    }

    public void inflateMenu(int i) {
        this.toolbar.inflateMenu(i);
    }

    public void setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        this.toolbar.setOnMenuItemClickListener(onMenuItemClickListener);
    }

    public TextView getSearchPrefix() {
        return this.searchPrefix;
    }

    public void setSearchPrefixText(CharSequence charSequence) {
        this.searchPrefix.setText(charSequence);
        this.searchPrefix.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    public CharSequence getSearchPrefixText() {
        return this.searchPrefix.getText();
    }

    public Toolbar getToolbar() {
        return this.toolbar;
    }

    public EditText getEditText() {
        return this.editText;
    }

    public Editable getText() {
        return this.editText.getText();
    }

    public void setText(CharSequence charSequence) {
        this.editText.setText(charSequence);
    }

    public void setText(int i) {
        this.editText.setText(i);
    }

    public void clearText() {
        this.editText.setText("");
    }

    public CharSequence getHint() {
        return this.editText.getHint();
    }

    public void setHint(CharSequence charSequence) {
        this.editText.setHint(charSequence);
    }

    public void setHint(int i) {
        this.editText.setHint(i);
    }

    public int getSoftInputMode() {
        return this.softInputMode;
    }

    public void updateSoftInputMode() {
        Window activityWindow = getActivityWindow();
        if (activityWindow != null) {
            this.softInputMode = activityWindow.getAttributes().softInputMode;
        }
    }

    public void setStatusBarSpacerEnabled(boolean z) {
        this.statusBarSpacerEnabledOverride = true;
        setStatusBarSpacerEnabledInternal(z);
    }

    private void setStatusBarSpacerEnabledInternal(boolean z) {
        this.statusBarSpacer.setVisibility(z ? 0 : 8);
    }

    public TransitionState getCurrentTransitionState() {
        return this.currentTransitionState;
    }

    /* access modifiers changed from: package-private */
    public void setTransitionState(TransitionState transitionState) {
        setTransitionState(transitionState, true);
    }

    private void setTransitionState(TransitionState transitionState, boolean z) {
        if (!this.currentTransitionState.equals(transitionState)) {
            if (z) {
                if (transitionState == TransitionState.SHOWN) {
                    setModalForAccessibility(true);
                } else if (transitionState == TransitionState.HIDDEN) {
                    setModalForAccessibility(false);
                }
            }
            TransitionState transitionState2 = this.currentTransitionState;
            this.currentTransitionState = transitionState;
            for (TransitionListener onStateChanged : new LinkedHashSet(this.transitionListeners)) {
                onStateChanged.onStateChanged(this, transitionState2, transitionState);
            }
            updateListeningForBackCallbacks(transitionState);
        }
    }

    private void updateListeningForBackCallbacks(TransitionState transitionState) {
        if (this.searchBar != null && this.backHandlingEnabled) {
            if (transitionState.equals(TransitionState.SHOWN)) {
                this.backOrchestrator.startListeningForBackCallbacks();
            } else if (transitionState.equals(TransitionState.HIDDEN)) {
                this.backOrchestrator.stopListeningForBackCallbacks();
            }
        }
    }

    public boolean isShowing() {
        return this.currentTransitionState.equals(TransitionState.SHOWN) || this.currentTransitionState.equals(TransitionState.SHOWING);
    }

    public void show() {
        if (!this.currentTransitionState.equals(TransitionState.SHOWN) && !this.currentTransitionState.equals(TransitionState.SHOWING)) {
            this.searchViewAnimationHelper.show();
        }
    }

    public void hide() {
        if (!this.currentTransitionState.equals(TransitionState.HIDDEN) && !this.currentTransitionState.equals(TransitionState.HIDING)) {
            this.searchViewAnimationHelper.hide();
        }
    }

    public void setVisible(boolean z) {
        boolean z2 = true;
        boolean z3 = this.rootView.getVisibility() == 0;
        this.rootView.setVisibility(z ? 0 : 8);
        updateNavigationIconProgressIfNeeded();
        TransitionState transitionState = z ? TransitionState.SHOWN : TransitionState.HIDDEN;
        if (z3 == z) {
            z2 = false;
        }
        setTransitionState(transitionState, z2);
    }

    private void updateNavigationIconProgressIfNeeded() {
        ImageButton navigationIconButton = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if (navigationIconButton != null) {
            int i = this.rootView.getVisibility() == 0 ? 1 : 0;
            Drawable unwrap = DrawableCompat.unwrap(navigationIconButton.getDrawable());
            if (unwrap instanceof DrawerArrowDrawable) {
                ((DrawerArrowDrawable) unwrap).setProgress((float) i);
            }
            if (unwrap instanceof FadeThroughDrawable) {
                ((FadeThroughDrawable) unwrap).setProgress((float) i);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void requestFocusAndShowKeyboardIfNeeded() {
        if (this.autoShowKeyboard) {
            requestFocusAndShowKeyboard();
        }
    }

    public void requestFocusAndShowKeyboard() {
        this.editText.postDelayed(new SearchView$$ExternalSyntheticLambda2(this), TALKBACK_FOCUS_CHANGE_DELAY_MS);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$requestFocusAndShowKeyboard$8$com-google-android-material-search-SearchView  reason: not valid java name */
    public /* synthetic */ void m117lambda$requestFocusAndShowKeyboard$8$comgoogleandroidmaterialsearchSearchView() {
        if (this.editText.requestFocus()) {
            this.editText.sendAccessibilityEvent(8);
        }
        ViewUtils.showKeyboard(this.editText, this.useWindowInsetsController);
    }

    public void clearFocusAndHideKeyboard() {
        this.editText.post(new SearchView$$ExternalSyntheticLambda10(this));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$clearFocusAndHideKeyboard$9$com-google-android-material-search-SearchView  reason: not valid java name */
    public /* synthetic */ void m116lambda$clearFocusAndHideKeyboard$9$comgoogleandroidmaterialsearchSearchView() {
        this.editText.clearFocus();
        SearchBar searchBar2 = this.searchBar;
        if (searchBar2 != null) {
            searchBar2.requestFocus();
        }
        ViewUtils.hideKeyboard(this.editText, this.useWindowInsetsController);
    }

    /* access modifiers changed from: package-private */
    public boolean isAdjustNothingSoftInputMode() {
        return this.softInputMode == 48;
    }

    public void setModalForAccessibility(boolean z) {
        ViewGroup viewGroup = (ViewGroup) getRootView();
        if (z) {
            this.childImportantForAccessibilityMap = new HashMap(viewGroup.getChildCount());
        }
        updateChildImportantForAccessibility(viewGroup, z);
        if (!z) {
            this.childImportantForAccessibilityMap = null;
        }
    }

    public void setToolbarTouchscreenBlocksFocus(boolean z) {
        this.toolbar.setTouchscreenBlocksFocus(z);
    }

    private void updateChildImportantForAccessibility(ViewGroup viewGroup, boolean z) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != this) {
                if (childAt.findViewById(this.rootView.getId()) != null) {
                    updateChildImportantForAccessibility((ViewGroup) childAt, z);
                } else if (!z) {
                    Map<View, Integer> map = this.childImportantForAccessibilityMap;
                    if (map != null && map.containsKey(childAt)) {
                        ViewCompat.setImportantForAccessibility(childAt, this.childImportantForAccessibilityMap.get(childAt).intValue());
                    }
                } else {
                    this.childImportantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    ViewCompat.setImportantForAccessibility(childAt, 4);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public int getDefaultNavigationIconResource() {
        return R.drawable.ic_arrow_back_black_24;
    }

    public static class Behavior extends CoordinatorLayout.Behavior<SearchView> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, SearchView searchView, View view) {
            if (searchView.isSetupWithSearchBar() || !(view instanceof SearchBar)) {
                return false;
            }
            searchView.setupWithSearchBar((SearchBar) view);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        String str;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Editable text = getText();
        if (text == null) {
            str = null;
        } else {
            str = text.toString();
        }
        savedState.text = str;
        savedState.visibility = this.rootView.getVisibility();
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setText((CharSequence) savedState.text);
        setVisible(savedState.visibility == 0);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        String text;
        int visibility;

        public SavedState(Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.text = parcel.readString();
            this.visibility = parcel.readInt();
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.text);
            parcel.writeInt(this.visibility);
        }
    }
}
