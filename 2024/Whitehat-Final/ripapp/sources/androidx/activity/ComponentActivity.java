package androidx.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.contextaware.ContextAware;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.NotificationCompat;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnNewIntentProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.OnUserLeaveHintProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuHostHelper;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import androidx.tracing.Trace;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000â\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 ¼\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000b2\u00020\f2\u00020\r2\u00020\u000e2\u00020\u000f2\u00020\u00102\u00020\u0011:\n»\u0001¼\u0001½\u0001¾\u0001¿\u0001B\u0011\b\u0017\u0012\b\b\u0001\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014B\u0005¢\u0006\u0002\u0010\u0015J\u001c\u0010[\u001a\u00020\\2\b\u0010]\u001a\u0004\u0018\u00010^2\b\u0010_\u001a\u0004\u0018\u00010`H\u0016J\u0010\u0010a\u001a\u00020\\2\u0006\u0010b\u001a\u00020cH\u0016J\u0018\u0010a\u001a\u00020\\2\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020\u0003H\u0016J \u0010a\u001a\u00020\\2\u0006\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020\u00032\u0006\u0010e\u001a\u00020fH\u0017J\u0010\u0010g\u001a\u00020\\2\u0006\u0010h\u001a\u00020=H\u0003J\u0014\u0010i\u001a\u00020\\2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020E0DJ\u000e\u0010k\u001a\u00020\\2\u0006\u0010j\u001a\u00020lJ\u0014\u0010m\u001a\u00020\\2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020G0DJ\u0014\u0010n\u001a\u00020\\2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020I0DJ\u0014\u0010o\u001a\u00020\\2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020K0DJ\u0014\u0010p\u001a\u00020\\2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00130DJ\u000e\u0010q\u001a\u00020\\2\u0006\u0010j\u001a\u00020NJ\b\u0010r\u001a\u00020PH\u0002J\b\u0010s\u001a\u00020\\H\u0002J\b\u0010t\u001a\u00020\\H\u0017J\b\u0010u\u001a\u00020\\H\u0016J\"\u0010v\u001a\u00020\\2\u0006\u0010w\u001a\u00020\u00132\u0006\u0010x\u001a\u00020\u00132\b\u0010y\u001a\u0004\u0018\u00010IH\u0015J\b\u0010z\u001a\u00020\\H\u0017J\u0010\u0010{\u001a\u00020\\2\u0006\u0010|\u001a\u00020EH\u0017J\u0012\u0010}\u001a\u00020\\2\b\u0010~\u001a\u0004\u0018\u00010H\u0014J\u001c\u0010\u0001\u001a\u00020)2\u0007\u0010\u0001\u001a\u00020\u00132\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u001c\u0010\u0001\u001a\u00020)2\u0007\u0010\u0001\u001a\u00020\u00132\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u0012\u0010\u0001\u001a\u00020\\2\u0007\u0010\u0001\u001a\u00020)H\u0017J\u001a\u0010\u0001\u001a\u00020\\2\u0007\u0010\u0001\u001a\u00020)2\u0006\u0010|\u001a\u00020EH\u0017J\u0012\u0010\u0001\u001a\u00020\\2\u0007\u0010\u0001\u001a\u00020IH\u0015J\u001c\u0010\u0001\u001a\u00020\\2\u0007\u0010\u0001\u001a\u00020\u00132\b\u0010\u0001\u001a\u00030\u0001H\u0016J\u0012\u0010\u0001\u001a\u00020\\2\u0007\u0010\u0001\u001a\u00020)H\u0017J\u001a\u0010\u0001\u001a\u00020\\2\u0007\u0010\u0001\u001a\u00020)2\u0006\u0010|\u001a\u00020EH\u0017J&\u0010\u0001\u001a\u00020)2\u0007\u0010\u0001\u001a\u00020\u00132\b\u0010]\u001a\u0004\u0018\u00010^2\b\u0010\u0001\u001a\u00030\u0001H\u0016J2\u0010\u0001\u001a\u00020\\2\u0006\u0010w\u001a\u00020\u00132\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030\u00010\u00012\b\u0010\u0001\u001a\u00030\u0001H\u0017¢\u0006\u0003\u0010\u0001J\u000b\u0010\u0001\u001a\u0004\u0018\u000101H\u0017J\t\u0010\u0001\u001a\u0004\u0018\u000101J\u0012\u0010\u0001\u001a\u00020\\2\u0007\u0010\u0001\u001a\u00020H\u0015J\u0012\u0010\u0001\u001a\u00020\\2\u0007\u0010\u0001\u001a\u00020\u0013H\u0017J\t\u0010\u0001\u001a\u00020\\H\u0015J\f\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0016JF\u0010\u0001\u001a\n\u0012\u0005\u0012\u0003H¡\u00010 \u0001\"\u0005\b\u0000\u0010¡\u0001\"\u0005\b\u0001\u0010¢\u00012\u0016\u0010£\u0001\u001a\u0011\u0012\u0005\u0012\u0003H¡\u0001\u0012\u0005\u0012\u0003H¢\u00010¤\u00012\u000f\u0010¥\u0001\u001a\n\u0012\u0005\u0012\u0003H¢\u00010¦\u0001JO\u0010\u0001\u001a\n\u0012\u0005\u0012\u0003H¡\u00010 \u0001\"\u0005\b\u0000\u0010¡\u0001\"\u0005\b\u0001\u0010¢\u00012\u0016\u0010£\u0001\u001a\u0011\u0012\u0005\u0012\u0003H¡\u0001\u0012\u0005\u0012\u0003H¢\u00010¤\u00012\u0007\u0010§\u0001\u001a\u00020\u00192\u000f\u0010¥\u0001\u001a\n\u0012\u0005\u0012\u0003H¢\u00010¦\u0001J\u0011\u0010¨\u0001\u001a\u00020\\2\u0006\u0010b\u001a\u00020cH\u0016J\u0015\u0010©\u0001\u001a\u00020\\2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020E0DJ\u000f\u0010ª\u0001\u001a\u00020\\2\u0006\u0010j\u001a\u00020lJ\u0015\u0010«\u0001\u001a\u00020\\2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020G0DJ\u0015\u0010¬\u0001\u001a\u00020\\2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020I0DJ\u0015\u0010­\u0001\u001a\u00020\\2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020K0DJ\u0015\u0010®\u0001\u001a\u00020\\2\f\u0010j\u001a\b\u0012\u0004\u0012\u00020\u00130DJ\u000f\u0010¯\u0001\u001a\u00020\\2\u0006\u0010j\u001a\u00020NJ\t\u0010°\u0001\u001a\u00020\\H\u0016J\u0013\u0010±\u0001\u001a\u00020\\2\b\u0010]\u001a\u0004\u0018\u00010^H\u0016J\u001d\u0010±\u0001\u001a\u00020\\2\b\u0010]\u001a\u0004\u0018\u00010^2\b\u0010_\u001a\u0004\u0018\u00010`H\u0016J\u0014\u0010±\u0001\u001a\u00020\\2\t\b\u0001\u0010²\u0001\u001a\u00020\u0013H\u0016J\u001a\u0010³\u0001\u001a\u00020\\2\u0007\u0010\u0001\u001a\u00020I2\u0006\u0010w\u001a\u00020\u0013H\u0017J%\u0010³\u0001\u001a\u00020\\2\u0007\u0010\u0001\u001a\u00020I2\u0006\u0010w\u001a\u00020\u00132\t\u0010´\u0001\u001a\u0004\u0018\u00010H\u0017JA\u0010µ\u0001\u001a\u00020\\2\b\u0010\u0001\u001a\u00030¶\u00012\u0006\u0010w\u001a\u00020\u00132\t\u0010·\u0001\u001a\u0004\u0018\u00010I2\u0007\u0010¸\u0001\u001a\u00020\u00132\u0007\u0010¹\u0001\u001a\u00020\u00132\u0007\u0010º\u0001\u001a\u00020\u0013H\u0017JL\u0010µ\u0001\u001a\u00020\\2\b\u0010\u0001\u001a\u00030¶\u00012\u0006\u0010w\u001a\u00020\u00132\t\u0010·\u0001\u001a\u0004\u0018\u00010I2\u0007\u0010¸\u0001\u001a\u00020\u00132\u0007\u0010¹\u0001\u001a\u00020\u00132\u0007\u0010º\u0001\u001a\u00020\u00132\t\u0010´\u0001\u001a\u0004\u0018\u00010H\u0017R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0012\u0010\u0012\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u001f8WX\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u001b\u0010\"\u001a\u00020#8VX\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b$\u0010%R\u000e\u0010(\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010+\u001a\u00020,8VX\u0002¢\u0006\f\n\u0004\b/\u0010'\u001a\u0004\b-\u0010.R\u0016\u00100\u001a\u0004\u0018\u0001018WX\u0004¢\u0006\u0006\u001a\u0004\b2\u00103R\u0014\u00104\u001a\u0002058VX\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u000e\u00108\u001a\u000209X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0004¢\u0006\u0002\n\u0000R!\u0010<\u001a\u00020=8FX\u0002¢\u0006\u0012\n\u0004\bA\u0010'\u0012\u0004\b>\u0010\u0015\u001a\u0004\b?\u0010@R\u001a\u0010B\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020E0D0CX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020G0D0CX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020I0D0CX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020K0D0CX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130D0CX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010M\u001a\b\u0012\u0004\u0012\u00020N0CX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020PX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010Q\u001a\u00020R8F¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0014\u0010U\u001a\u00020VX\u0004¢\u0006\b\n\u0000\u0012\u0004\bW\u0010\u0015R\u0014\u0010X\u001a\u00020\u00178VX\u0004¢\u0006\u0006\u001a\u0004\bY\u0010Z¨\u0006À\u0001"}, d2 = {"Landroidx/activity/ComponentActivity;", "Landroidx/core/app/ComponentActivity;", "Landroidx/activity/contextaware/ContextAware;", "Landroidx/lifecycle/LifecycleOwner;", "Landroidx/lifecycle/ViewModelStoreOwner;", "Landroidx/lifecycle/HasDefaultViewModelProviderFactory;", "Landroidx/savedstate/SavedStateRegistryOwner;", "Landroidx/activity/OnBackPressedDispatcherOwner;", "Landroidx/activity/result/ActivityResultRegistryOwner;", "Landroidx/activity/result/ActivityResultCaller;", "Landroidx/core/content/OnConfigurationChangedProvider;", "Landroidx/core/content/OnTrimMemoryProvider;", "Landroidx/core/app/OnNewIntentProvider;", "Landroidx/core/app/OnMultiWindowModeChangedProvider;", "Landroidx/core/app/OnPictureInPictureModeChangedProvider;", "Landroidx/core/app/OnUserLeaveHintProvider;", "Landroidx/core/view/MenuHost;", "Landroidx/activity/FullyDrawnReporterOwner;", "contentLayoutId", "", "(I)V", "()V", "_viewModelStore", "Landroidx/lifecycle/ViewModelStore;", "activityResultRegistry", "Landroidx/activity/result/ActivityResultRegistry;", "getActivityResultRegistry", "()Landroidx/activity/result/ActivityResultRegistry;", "contextAwareHelper", "Landroidx/activity/contextaware/ContextAwareHelper;", "defaultViewModelCreationExtras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "getDefaultViewModelCreationExtras", "()Landroidx/lifecycle/viewmodel/CreationExtras;", "defaultViewModelProviderFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "getDefaultViewModelProviderFactory", "()Landroidx/lifecycle/ViewModelProvider$Factory;", "defaultViewModelProviderFactory$delegate", "Lkotlin/Lazy;", "dispatchingOnMultiWindowModeChanged", "", "dispatchingOnPictureInPictureModeChanged", "fullyDrawnReporter", "Landroidx/activity/FullyDrawnReporter;", "getFullyDrawnReporter", "()Landroidx/activity/FullyDrawnReporter;", "fullyDrawnReporter$delegate", "lastCustomNonConfigurationInstance", "", "getLastCustomNonConfigurationInstance", "()Ljava/lang/Object;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "menuHostHelper", "Landroidx/core/view/MenuHostHelper;", "nextLocalRequestCode", "Ljava/util/concurrent/atomic/AtomicInteger;", "onBackPressedDispatcher", "Landroidx/activity/OnBackPressedDispatcher;", "getOnBackPressedDispatcher$annotations", "getOnBackPressedDispatcher", "()Landroidx/activity/OnBackPressedDispatcher;", "onBackPressedDispatcher$delegate", "onConfigurationChangedListeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/core/util/Consumer;", "Landroid/content/res/Configuration;", "onMultiWindowModeChangedListeners", "Landroidx/core/app/MultiWindowModeChangedInfo;", "onNewIntentListeners", "Landroid/content/Intent;", "onPictureInPictureModeChangedListeners", "Landroidx/core/app/PictureInPictureModeChangedInfo;", "onTrimMemoryListeners", "onUserLeaveHintListeners", "Ljava/lang/Runnable;", "reportFullyDrawnExecutor", "Landroidx/activity/ComponentActivity$ReportFullyDrawnExecutor;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "getSavedStateRegistry", "()Landroidx/savedstate/SavedStateRegistry;", "savedStateRegistryController", "Landroidx/savedstate/SavedStateRegistryController;", "getSavedStateRegistryController$annotations", "viewModelStore", "getViewModelStore", "()Landroidx/lifecycle/ViewModelStore;", "addContentView", "", "view", "Landroid/view/View;", "params", "Landroid/view/ViewGroup$LayoutParams;", "addMenuProvider", "provider", "Landroidx/core/view/MenuProvider;", "owner", "state", "Landroidx/lifecycle/Lifecycle$State;", "addObserverForBackInvoker", "dispatcher", "addOnConfigurationChangedListener", "listener", "addOnContextAvailableListener", "Landroidx/activity/contextaware/OnContextAvailableListener;", "addOnMultiWindowModeChangedListener", "addOnNewIntentListener", "addOnPictureInPictureModeChangedListener", "addOnTrimMemoryListener", "addOnUserLeaveHintListener", "createFullyDrawnExecutor", "ensureViewModelStore", "initializeViewTreeOwners", "invalidateMenu", "onActivityResult", "requestCode", "resultCode", "data", "onBackPressed", "onConfigurationChanged", "newConfig", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreatePanelMenu", "featureId", "menu", "Landroid/view/Menu;", "onMenuItemSelected", "item", "Landroid/view/MenuItem;", "onMultiWindowModeChanged", "isInMultiWindowMode", "onNewIntent", "intent", "onPanelClosed", "onPictureInPictureModeChanged", "isInPictureInPictureMode", "onPreparePanel", "onRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onRetainCustomNonConfigurationInstance", "onRetainNonConfigurationInstance", "onSaveInstanceState", "outState", "onTrimMemory", "level", "onUserLeaveHint", "peekAvailableContext", "Landroid/content/Context;", "registerForActivityResult", "Landroidx/activity/result/ActivityResultLauncher;", "I", "O", "contract", "Landroidx/activity/result/contract/ActivityResultContract;", "callback", "Landroidx/activity/result/ActivityResultCallback;", "registry", "removeMenuProvider", "removeOnConfigurationChangedListener", "removeOnContextAvailableListener", "removeOnMultiWindowModeChangedListener", "removeOnNewIntentListener", "removeOnPictureInPictureModeChangedListener", "removeOnTrimMemoryListener", "removeOnUserLeaveHintListener", "reportFullyDrawn", "setContentView", "layoutResID", "startActivityForResult", "options", "startIntentSenderForResult", "Landroid/content/IntentSender;", "fillInIntent", "flagsMask", "flagsValues", "extraFlags", "Api33Impl", "Companion", "NonConfigurationInstances", "ReportFullyDrawnExecutor", "ReportFullyDrawnExecutorImpl", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ComponentActivity.kt */
public class ComponentActivity extends androidx.core.app.ComponentActivity implements ContextAware, LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, ActivityResultCaller, OnConfigurationChangedProvider, OnTrimMemoryProvider, OnNewIntentProvider, OnMultiWindowModeChangedProvider, OnPictureInPictureModeChangedProvider, OnUserLeaveHintProvider, MenuHost, FullyDrawnReporterOwner {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private ViewModelStore _viewModelStore;
    private final ActivityResultRegistry activityResultRegistry;
    private int contentLayoutId;
    private final ContextAwareHelper contextAwareHelper;
    private final Lazy defaultViewModelProviderFactory$delegate;
    private boolean dispatchingOnMultiWindowModeChanged;
    private boolean dispatchingOnPictureInPictureModeChanged;
    private final Lazy fullyDrawnReporter$delegate;
    private final MenuHostHelper menuHostHelper;
    private final AtomicInteger nextLocalRequestCode;
    private final Lazy onBackPressedDispatcher$delegate;
    private final CopyOnWriteArrayList<Consumer<Configuration>> onConfigurationChangedListeners;
    private final CopyOnWriteArrayList<Consumer<MultiWindowModeChangedInfo>> onMultiWindowModeChangedListeners;
    private final CopyOnWriteArrayList<Consumer<Intent>> onNewIntentListeners;
    private final CopyOnWriteArrayList<Consumer<PictureInPictureModeChangedInfo>> onPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList<Consumer<Integer>> onTrimMemoryListeners;
    private final CopyOnWriteArrayList<Runnable> onUserLeaveHintListeners;
    /* access modifiers changed from: private */
    public final ReportFullyDrawnExecutor reportFullyDrawnExecutor;
    private final SavedStateRegistryController savedStateRegistryController;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bb\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/activity/ComponentActivity$ReportFullyDrawnExecutor;", "Ljava/util/concurrent/Executor;", "activityDestroyed", "", "viewCreated", "view", "Landroid/view/View;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ComponentActivity.kt */
    private interface ReportFullyDrawnExecutor extends Executor {
        void activityDestroyed();

        void viewCreated(View view);
    }

    public static /* synthetic */ void getOnBackPressedDispatcher$annotations() {
    }

    private static /* synthetic */ void getSavedStateRegistryController$annotations() {
    }

    @Deprecated(message = "Use a {@link androidx.lifecycle.ViewModel} to store non config state.")
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    public ComponentActivity() {
        this.contextAwareHelper = new ContextAwareHelper();
        this.menuHostHelper = new MenuHostHelper(new ComponentActivity$$ExternalSyntheticLambda0(this));
        SavedStateRegistryOwner savedStateRegistryOwner = this;
        SavedStateRegistryController create = SavedStateRegistryController.Companion.create(savedStateRegistryOwner);
        this.savedStateRegistryController = create;
        this.reportFullyDrawnExecutor = createFullyDrawnExecutor();
        this.fullyDrawnReporter$delegate = LazyKt.lazy(new ComponentActivity$fullyDrawnReporter$2(this));
        this.nextLocalRequestCode = new AtomicInteger();
        this.activityResultRegistry = new ComponentActivity$activityResultRegistry$1(this);
        this.onConfigurationChangedListeners = new CopyOnWriteArrayList<>();
        this.onTrimMemoryListeners = new CopyOnWriteArrayList<>();
        this.onNewIntentListeners = new CopyOnWriteArrayList<>();
        this.onMultiWindowModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onPictureInPictureModeChangedListeners = new CopyOnWriteArrayList<>();
        this.onUserLeaveHintListeners = new CopyOnWriteArrayList<>();
        if (getLifecycle() != null) {
            getLifecycle().addObserver(new ComponentActivity$$ExternalSyntheticLambda1(this));
            getLifecycle().addObserver(new ComponentActivity$$ExternalSyntheticLambda2(this));
            getLifecycle().addObserver(new LifecycleEventObserver(this) {
                final /* synthetic */ ComponentActivity this$0;

                {
                    this.this$0 = r1;
                }

                public void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                    Intrinsics.checkNotNullParameter(lifecycleOwner, "source");
                    Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
                    this.this$0.ensureViewModelStore();
                    this.this$0.getLifecycle().removeObserver(this);
                }
            });
            create.performAttach();
            SavedStateHandleSupport.enableSavedStateHandles(savedStateRegistryOwner);
            getSavedStateRegistry().registerSavedStateProvider(ACTIVITY_RESULT_TAG, new ComponentActivity$$ExternalSyntheticLambda3(this));
            addOnContextAvailableListener(new ComponentActivity$$ExternalSyntheticLambda4(this));
            this.defaultViewModelProviderFactory$delegate = LazyKt.lazy(new ComponentActivity$defaultViewModelProviderFactory$2(this));
            this.onBackPressedDispatcher$delegate = LazyKt.lazy(new ComponentActivity$onBackPressedDispatcher$2(this));
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.".toString());
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/activity/ComponentActivity$NonConfigurationInstances;", "", "()V", "custom", "getCustom", "()Ljava/lang/Object;", "setCustom", "(Ljava/lang/Object;)V", "viewModelStore", "Landroidx/lifecycle/ViewModelStore;", "getViewModelStore", "()Landroidx/lifecycle/ViewModelStore;", "setViewModelStore", "(Landroidx/lifecycle/ViewModelStore;)V", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ComponentActivity.kt */
    public static final class NonConfigurationInstances {
        private Object custom;
        private ViewModelStore viewModelStore;

        public final Object getCustom() {
            return this.custom;
        }

        public final void setCustom(Object obj) {
            this.custom = obj;
        }

        public final ViewModelStore getViewModelStore() {
            return this.viewModelStore;
        }

        public final void setViewModelStore(ViewModelStore viewModelStore2) {
            this.viewModelStore = viewModelStore2;
        }
    }

    /* access modifiers changed from: private */
    public static final void menuHostHelper$lambda$0(ComponentActivity componentActivity) {
        Intrinsics.checkNotNullParameter(componentActivity, "this$0");
        componentActivity.invalidateMenu();
    }

    public FullyDrawnReporter getFullyDrawnReporter() {
        return (FullyDrawnReporter) this.fullyDrawnReporter$delegate.getValue();
    }

    public final ActivityResultRegistry getActivityResultRegistry() {
        return this.activityResultRegistry;
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$2(ComponentActivity componentActivity, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Window window;
        View peekDecorView;
        Intrinsics.checkNotNullParameter(componentActivity, "this$0");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        if (event == Lifecycle.Event.ON_STOP && (window = componentActivity.getWindow()) != null && (peekDecorView = window.peekDecorView()) != null) {
            peekDecorView.cancelPendingInputEvents();
        }
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$3(ComponentActivity componentActivity, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(componentActivity, "this$0");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        if (event == Lifecycle.Event.ON_DESTROY) {
            componentActivity.contextAwareHelper.clearAvailableContext();
            if (!componentActivity.isChangingConfigurations()) {
                componentActivity.getViewModelStore().clear();
            }
            componentActivity.reportFullyDrawnExecutor.activityDestroyed();
        }
    }

    /* access modifiers changed from: private */
    public static final Bundle _init_$lambda$4(ComponentActivity componentActivity) {
        Intrinsics.checkNotNullParameter(componentActivity, "this$0");
        Bundle bundle = new Bundle();
        componentActivity.activityResultRegistry.onSaveInstanceState(bundle);
        return bundle;
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$5(ComponentActivity componentActivity, Context context) {
        Intrinsics.checkNotNullParameter(componentActivity, "this$0");
        Intrinsics.checkNotNullParameter(context, "it");
        Bundle consumeRestoredStateForKey = componentActivity.getSavedStateRegistry().consumeRestoredStateForKey(ACTIVITY_RESULT_TAG);
        if (consumeRestoredStateForKey != null) {
            componentActivity.activityResultRegistry.onRestoreInstanceState(consumeRestoredStateForKey);
        }
    }

    public ComponentActivity(int i) {
        this();
        this.contentLayoutId = i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.savedStateRegistryController.performRestore(bundle);
        this.contextAwareHelper.dispatchOnContextAvailable(this);
        super.onCreate(bundle);
        ReportFragment.Companion.injectIfNeededIn(this);
        int i = this.contentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        if (getLifecycle() instanceof LifecycleRegistry) {
            Lifecycle lifecycle = getLifecycle();
            Intrinsics.checkNotNull(lifecycle, "null cannot be cast to non-null type androidx.lifecycle.LifecycleRegistry");
            ((LifecycleRegistry) lifecycle).setCurrentState(Lifecycle.State.CREATED);
        }
        super.onSaveInstanceState(bundle);
        this.savedStateRegistryController.performSave(bundle);
    }

    public final Object onRetainNonConfigurationInstance() {
        NonConfigurationInstances nonConfigurationInstances;
        Object onRetainCustomNonConfigurationInstance = onRetainCustomNonConfigurationInstance();
        ViewModelStore viewModelStore = this._viewModelStore;
        if (viewModelStore == null && (nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance()) != null) {
            viewModelStore = nonConfigurationInstances.getViewModelStore();
        }
        if (viewModelStore == null && onRetainCustomNonConfigurationInstance == null) {
            return null;
        }
        NonConfigurationInstances nonConfigurationInstances2 = new NonConfigurationInstances();
        nonConfigurationInstances2.setCustom(onRetainCustomNonConfigurationInstance);
        nonConfigurationInstances2.setViewModelStore(viewModelStore);
        return nonConfigurationInstances2;
    }

    @Deprecated(message = "Use a {@link androidx.lifecycle.ViewModel} to store non config state.")
    public Object getLastCustomNonConfigurationInstance() {
        NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance();
        if (nonConfigurationInstances != null) {
            return nonConfigurationInstances.getCustom();
        }
        return null;
    }

    public void setContentView(int i) {
        initializeViewTreeOwners();
        ReportFullyDrawnExecutor reportFullyDrawnExecutor2 = this.reportFullyDrawnExecutor;
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "window.decorView");
        reportFullyDrawnExecutor2.viewCreated(decorView);
        super.setContentView(i);
    }

    public void setContentView(View view) {
        initializeViewTreeOwners();
        ReportFullyDrawnExecutor reportFullyDrawnExecutor2 = this.reportFullyDrawnExecutor;
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "window.decorView");
        reportFullyDrawnExecutor2.viewCreated(decorView);
        super.setContentView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        ReportFullyDrawnExecutor reportFullyDrawnExecutor2 = this.reportFullyDrawnExecutor;
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "window.decorView");
        reportFullyDrawnExecutor2.viewCreated(decorView);
        super.setContentView(view, layoutParams);
    }

    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        ReportFullyDrawnExecutor reportFullyDrawnExecutor2 = this.reportFullyDrawnExecutor;
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "window.decorView");
        reportFullyDrawnExecutor2.viewCreated(decorView);
        super.addContentView(view, layoutParams);
    }

    public void initializeViewTreeOwners() {
        View decorView = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView, "window.decorView");
        ViewTreeLifecycleOwner.set(decorView, this);
        View decorView2 = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView2, "window.decorView");
        ViewTreeViewModelStoreOwner.set(decorView2, this);
        View decorView3 = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView3, "window.decorView");
        ViewTreeSavedStateRegistryOwner.set(decorView3, this);
        View decorView4 = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView4, "window.decorView");
        ViewTreeOnBackPressedDispatcherOwner.set(decorView4, this);
        View decorView5 = getWindow().getDecorView();
        Intrinsics.checkNotNullExpressionValue(decorView5, "window.decorView");
        ViewTreeFullyDrawnReporterOwner.set(decorView5, this);
    }

    public Context peekAvailableContext() {
        return this.contextAwareHelper.peekAvailableContext();
    }

    public final void addOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener) {
        Intrinsics.checkNotNullParameter(onContextAvailableListener, "listener");
        this.contextAwareHelper.addOnContextAvailableListener(onContextAvailableListener);
    }

    public final void removeOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener) {
        Intrinsics.checkNotNullParameter(onContextAvailableListener, "listener");
        this.contextAwareHelper.removeOnContextAvailableListener(onContextAvailableListener);
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (i != 0) {
            return true;
        }
        super.onPreparePanel(i, view, menu);
        this.menuHostHelper.onPrepareMenu(menu);
        return true;
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        if (i != 0) {
            return true;
        }
        super.onCreatePanelMenu(i, menu);
        this.menuHostHelper.onCreateMenu(menu, getMenuInflater());
        return true;
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.menuHostHelper.onMenuItemSelected(menuItem);
        }
        return false;
    }

    public void onPanelClosed(int i, Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        this.menuHostHelper.onMenuClosed(menu);
        super.onPanelClosed(i, menu);
    }

    public void addMenuProvider(MenuProvider menuProvider) {
        Intrinsics.checkNotNullParameter(menuProvider, "provider");
        this.menuHostHelper.addMenuProvider(menuProvider);
    }

    public void addMenuProvider(MenuProvider menuProvider, LifecycleOwner lifecycleOwner) {
        Intrinsics.checkNotNullParameter(menuProvider, "provider");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        this.menuHostHelper.addMenuProvider(menuProvider, lifecycleOwner);
    }

    public void addMenuProvider(MenuProvider menuProvider, LifecycleOwner lifecycleOwner, Lifecycle.State state) {
        Intrinsics.checkNotNullParameter(menuProvider, "provider");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "owner");
        Intrinsics.checkNotNullParameter(state, "state");
        this.menuHostHelper.addMenuProvider(menuProvider, lifecycleOwner, state);
    }

    public void removeMenuProvider(MenuProvider menuProvider) {
        Intrinsics.checkNotNullParameter(menuProvider, "provider");
        this.menuHostHelper.removeMenuProvider(menuProvider);
    }

    public void invalidateMenu() {
        invalidateOptionsMenu();
    }

    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }

    public ViewModelStore getViewModelStore() {
        if (getApplication() != null) {
            ensureViewModelStore();
            ViewModelStore viewModelStore = this._viewModelStore;
            Intrinsics.checkNotNull(viewModelStore);
            return viewModelStore;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.".toString());
    }

    /* access modifiers changed from: private */
    public final void ensureViewModelStore() {
        if (this._viewModelStore == null) {
            NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance();
            if (nonConfigurationInstances != null) {
                this._viewModelStore = nonConfigurationInstances.getViewModelStore();
            }
            if (this._viewModelStore == null) {
                this._viewModelStore = new ViewModelStore();
            }
        }
    }

    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return (ViewModelProvider.Factory) this.defaultViewModelProviderFactory$delegate.getValue();
    }

    public CreationExtras getDefaultViewModelCreationExtras() {
        Bundle bundle = null;
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras((CreationExtras) null, 1, (DefaultConstructorMarker) null);
        if (getApplication() != null) {
            CreationExtras.Key<Application> key = ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY;
            Application application = getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "application");
            mutableCreationExtras.set(key, application);
        }
        mutableCreationExtras.set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this);
        mutableCreationExtras.set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getExtras();
        }
        if (bundle != null) {
            mutableCreationExtras.set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, bundle);
        }
        return mutableCreationExtras;
    }

    @Deprecated(message = "This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    public void onBackPressed() {
        getOnBackPressedDispatcher().onBackPressed();
    }

    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return (OnBackPressedDispatcher) this.onBackPressedDispatcher$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final void addObserverForBackInvoker(OnBackPressedDispatcher onBackPressedDispatcher) {
        getLifecycle().addObserver(new ComponentActivity$$ExternalSyntheticLambda5(onBackPressedDispatcher, this));
    }

    /* access modifiers changed from: private */
    public static final void addObserverForBackInvoker$lambda$7(OnBackPressedDispatcher onBackPressedDispatcher, ComponentActivity componentActivity, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(onBackPressedDispatcher, "$dispatcher");
        Intrinsics.checkNotNullParameter(componentActivity, "this$0");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(event, NotificationCompat.CATEGORY_EVENT);
        if (event == Lifecycle.Event.ON_CREATE) {
            onBackPressedDispatcher.setOnBackInvokedDispatcher(Api33Impl.INSTANCE.getOnBackInvokedDispatcher(componentActivity));
        }
    }

    public final SavedStateRegistry getSavedStateRegistry() {
        return this.savedStateRegistryController.getSavedStateRegistry();
    }

    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      passing in a {@link StartActivityForResult} object for the {@link ActivityResultContract}.")
    public void startActivityForResult(Intent intent, int i) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.startActivityForResult(intent, i);
    }

    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      passing in a {@link StartActivityForResult} object for the {@link ActivityResultContract}.")
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.startActivityForResult(intent, i, bundle);
    }

    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      passing in a {@link StartIntentSenderForResult} object for the\n      {@link ActivityResultContract}.")
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) throws IntentSender.SendIntentException {
        Intrinsics.checkNotNullParameter(intentSender, "intent");
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      passing in a {@link StartIntentSenderForResult} object for the\n      {@link ActivityResultContract}.")
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) throws IntentSender.SendIntentException {
        Intrinsics.checkNotNullParameter(intentSender, "intent");
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    /* access modifiers changed from: protected */
    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    public void onActivityResult(int i, int i2, Intent intent) {
        if (!this.activityResultRegistry.dispatchResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
        }
    }

    @Deprecated(message = "This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)} passing\n      in a {@link RequestMultiplePermissions} object for the {@link ActivityResultContract} and\n      handling the result in the {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        Intrinsics.checkNotNullParameter(strArr, "permissions");
        Intrinsics.checkNotNullParameter(iArr, "grantResults");
        if (!this.activityResultRegistry.dispatchResult(i, -1, new Intent().putExtra(ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSIONS, strArr).putExtra(ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSION_GRANT_RESULTS, iArr))) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(ActivityResultContract<I, O> activityResultContract, ActivityResultRegistry activityResultRegistry2, ActivityResultCallback<O> activityResultCallback) {
        Intrinsics.checkNotNullParameter(activityResultContract, "contract");
        Intrinsics.checkNotNullParameter(activityResultRegistry2, "registry");
        Intrinsics.checkNotNullParameter(activityResultCallback, "callback");
        return activityResultRegistry2.register("activity_rq#" + this.nextLocalRequestCode.getAndIncrement(), this, activityResultContract, activityResultCallback);
    }

    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(ActivityResultContract<I, O> activityResultContract, ActivityResultCallback<O> activityResultCallback) {
        Intrinsics.checkNotNullParameter(activityResultContract, "contract");
        Intrinsics.checkNotNullParameter(activityResultCallback, "callback");
        return registerForActivityResult(activityResultContract, this.activityResultRegistry, activityResultCallback);
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        Iterator<Consumer<Configuration>> it = this.onConfigurationChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(configuration);
        }
    }

    public final void addOnConfigurationChangedListener(Consumer<Configuration> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "listener");
        this.onConfigurationChangedListeners.add(consumer);
    }

    public final void removeOnConfigurationChangedListener(Consumer<Configuration> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "listener");
        this.onConfigurationChangedListeners.remove(consumer);
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Iterator<Consumer<Integer>> it = this.onTrimMemoryListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(Integer.valueOf(i));
        }
    }

    public final void addOnTrimMemoryListener(Consumer<Integer> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "listener");
        this.onTrimMemoryListeners.add(consumer);
    }

    public final void removeOnTrimMemoryListener(Consumer<Integer> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "listener");
        this.onTrimMemoryListeners.remove(consumer);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        Iterator<Consumer<Intent>> it = this.onNewIntentListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(intent);
        }
    }

    public final void addOnNewIntentListener(Consumer<Intent> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "listener");
        this.onNewIntentListeners.add(consumer);
    }

    public final void removeOnNewIntentListener(Consumer<Intent> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "listener");
        this.onNewIntentListeners.remove(consumer);
    }

    @Deprecated(message = "Deprecated in android.app.Activity")
    public void onMultiWindowModeChanged(boolean z) {
        if (!this.dispatchingOnMultiWindowModeChanged) {
            Iterator<Consumer<MultiWindowModeChangedInfo>> it = this.onMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new MultiWindowModeChangedInfo(z));
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        this.dispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(z, configuration);
            this.dispatchingOnMultiWindowModeChanged = false;
            Iterator<Consumer<MultiWindowModeChangedInfo>> it = this.onMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new MultiWindowModeChangedInfo(z, configuration));
            }
        } catch (Throwable th) {
            this.dispatchingOnMultiWindowModeChanged = false;
            throw th;
        }
    }

    public final void addOnMultiWindowModeChangedListener(Consumer<MultiWindowModeChangedInfo> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "listener");
        this.onMultiWindowModeChangedListeners.add(consumer);
    }

    public final void removeOnMultiWindowModeChangedListener(Consumer<MultiWindowModeChangedInfo> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "listener");
        this.onMultiWindowModeChangedListeners.remove(consumer);
    }

    @Deprecated(message = "Deprecated in android.app.Activity")
    public void onPictureInPictureModeChanged(boolean z) {
        if (!this.dispatchingOnPictureInPictureModeChanged) {
            Iterator<Consumer<PictureInPictureModeChangedInfo>> it = this.onPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new PictureInPictureModeChangedInfo(z));
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        this.dispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration);
            this.dispatchingOnPictureInPictureModeChanged = false;
            Iterator<Consumer<PictureInPictureModeChangedInfo>> it = this.onPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new PictureInPictureModeChangedInfo(z, configuration));
            }
        } catch (Throwable th) {
            this.dispatchingOnPictureInPictureModeChanged = false;
            throw th;
        }
    }

    public final void addOnPictureInPictureModeChangedListener(Consumer<PictureInPictureModeChangedInfo> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "listener");
        this.onPictureInPictureModeChangedListeners.add(consumer);
    }

    public final void removeOnPictureInPictureModeChangedListener(Consumer<PictureInPictureModeChangedInfo> consumer) {
        Intrinsics.checkNotNullParameter(consumer, "listener");
        this.onPictureInPictureModeChangedListeners.remove(consumer);
    }

    /* access modifiers changed from: protected */
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        Iterator<Runnable> it = this.onUserLeaveHintListeners.iterator();
        while (it.hasNext()) {
            it.next().run();
        }
    }

    public final void addOnUserLeaveHintListener(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "listener");
        this.onUserLeaveHintListeners.add(runnable);
    }

    public final void removeOnUserLeaveHintListener(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "listener");
        this.onUserLeaveHintListeners.remove(runnable);
    }

    public void reportFullyDrawn() {
        try {
            if (Trace.isEnabled()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            getFullyDrawnReporter().fullyDrawnReported();
        } finally {
            Trace.endSection();
        }
    }

    private final ReportFullyDrawnExecutor createFullyDrawnExecutor() {
        return new ReportFullyDrawnExecutorImpl();
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/activity/ComponentActivity$Api33Impl;", "", "()V", "getOnBackInvokedDispatcher", "Landroid/window/OnBackInvokedDispatcher;", "activity", "Landroid/app/Activity;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ComponentActivity.kt */
    private static final class Api33Impl {
        public static final Api33Impl INSTANCE = new Api33Impl();

        private Api33Impl() {
        }

        public final OnBackInvokedDispatcher getOnBackInvokedDispatcher(Activity activity) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            OnBackInvokedDispatcher onBackInvokedDispatcher = activity.getOnBackInvokedDispatcher();
            Intrinsics.checkNotNullExpressionValue(onBackInvokedDispatcher, "activity.getOnBackInvokedDispatcher()");
            return onBackInvokedDispatcher;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0003H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cH\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u001d"}, d2 = {"Landroidx/activity/ComponentActivity$ReportFullyDrawnExecutorImpl;", "Landroidx/activity/ComponentActivity$ReportFullyDrawnExecutor;", "Landroid/view/ViewTreeObserver$OnDrawListener;", "Ljava/lang/Runnable;", "(Landroidx/activity/ComponentActivity;)V", "currentRunnable", "getCurrentRunnable", "()Ljava/lang/Runnable;", "setCurrentRunnable", "(Ljava/lang/Runnable;)V", "endWatchTimeMillis", "", "getEndWatchTimeMillis", "()J", "onDrawScheduled", "", "getOnDrawScheduled", "()Z", "setOnDrawScheduled", "(Z)V", "activityDestroyed", "", "execute", "runnable", "onDraw", "run", "viewCreated", "view", "Landroid/view/View;", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ComponentActivity.kt */
    private final class ReportFullyDrawnExecutorImpl implements ReportFullyDrawnExecutor, ViewTreeObserver.OnDrawListener, Runnable {
        private Runnable currentRunnable;
        private final long endWatchTimeMillis = (SystemClock.uptimeMillis() + ((long) 10000));
        private boolean onDrawScheduled;

        public ReportFullyDrawnExecutorImpl() {
        }

        public final long getEndWatchTimeMillis() {
            return this.endWatchTimeMillis;
        }

        public final Runnable getCurrentRunnable() {
            return this.currentRunnable;
        }

        public final void setCurrentRunnable(Runnable runnable) {
            this.currentRunnable = runnable;
        }

        public final boolean getOnDrawScheduled() {
            return this.onDrawScheduled;
        }

        public final void setOnDrawScheduled(boolean z) {
            this.onDrawScheduled = z;
        }

        public void viewCreated(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            if (!this.onDrawScheduled) {
                this.onDrawScheduled = true;
                view.getViewTreeObserver().addOnDrawListener(this);
            }
        }

        public void activityDestroyed() {
            ComponentActivity.this.getWindow().getDecorView().removeCallbacks(this);
            ComponentActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        public void execute(Runnable runnable) {
            Intrinsics.checkNotNullParameter(runnable, "runnable");
            this.currentRunnable = runnable;
            View decorView = ComponentActivity.this.getWindow().getDecorView();
            Intrinsics.checkNotNullExpressionValue(decorView, "window.decorView");
            if (!this.onDrawScheduled) {
                decorView.postOnAnimation(new ComponentActivity$ReportFullyDrawnExecutorImpl$$ExternalSyntheticLambda0(this));
            } else if (Intrinsics.areEqual((Object) Looper.myLooper(), (Object) Looper.getMainLooper())) {
                decorView.invalidate();
            } else {
                decorView.postInvalidate();
            }
        }

        /* access modifiers changed from: private */
        public static final void execute$lambda$0(ReportFullyDrawnExecutorImpl reportFullyDrawnExecutorImpl) {
            Intrinsics.checkNotNullParameter(reportFullyDrawnExecutorImpl, "this$0");
            Runnable runnable = reportFullyDrawnExecutorImpl.currentRunnable;
            if (runnable != null) {
                Intrinsics.checkNotNull(runnable);
                runnable.run();
                reportFullyDrawnExecutorImpl.currentRunnable = null;
            }
        }

        public void onDraw() {
            Runnable runnable = this.currentRunnable;
            if (runnable != null) {
                runnable.run();
                this.currentRunnable = null;
                if (ComponentActivity.this.getFullyDrawnReporter().isFullyDrawnReported()) {
                    this.onDrawScheduled = false;
                    ComponentActivity.this.getWindow().getDecorView().post(this);
                }
            } else if (SystemClock.uptimeMillis() > this.endWatchTimeMillis) {
                this.onDrawScheduled = false;
                ComponentActivity.this.getWindow().getDecorView().post(this);
            }
        }

        public void run() {
            ComponentActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/activity/ComponentActivity$Companion;", "", "()V", "ACTIVITY_RESULT_TAG", "", "activity_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ComponentActivity.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
