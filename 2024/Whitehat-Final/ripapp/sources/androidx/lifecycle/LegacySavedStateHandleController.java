package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J,\u0010\r\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00042\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007J\u0018\u0010\u0012\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/lifecycle/LegacySavedStateHandleController;", "", "()V", "TAG_SAVED_STATE_HANDLE_CONTROLLER", "", "attachHandleIfNeeded", "", "viewModel", "Landroidx/lifecycle/ViewModel;", "registry", "Landroidx/savedstate/SavedStateRegistry;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "create", "Landroidx/lifecycle/SavedStateHandleController;", "key", "defaultArgs", "Landroid/os/Bundle;", "tryToAddRecreator", "OnRecreation", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: LegacySavedStateHandleController.kt */
public final class LegacySavedStateHandleController {
    public static final LegacySavedStateHandleController INSTANCE = new LegacySavedStateHandleController();
    public static final String TAG_SAVED_STATE_HANDLE_CONTROLLER = "androidx.lifecycle.savedstate.vm.tag";

    private LegacySavedStateHandleController() {
    }

    @JvmStatic
    public static final SavedStateHandleController create(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle, String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(savedStateRegistry, "registry");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNull(str);
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, SavedStateHandle.Companion.createHandle(savedStateRegistry.consumeRestoredStateForKey(str), bundle));
        savedStateHandleController.attachToLifecycle(savedStateRegistry, lifecycle);
        INSTANCE.tryToAddRecreator(savedStateRegistry, lifecycle);
        return savedStateHandleController;
    }

    @JvmStatic
    public static final void attachHandleIfNeeded(ViewModel viewModel, SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(savedStateRegistry, "registry");
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) viewModel.getTag("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController != null && !savedStateHandleController.isAttached()) {
            savedStateHandleController.attachToLifecycle(savedStateRegistry, lifecycle);
            INSTANCE.tryToAddRecreator(savedStateRegistry, lifecycle);
        }
    }

    private final void tryToAddRecreator(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        Lifecycle.State currentState = lifecycle.getCurrentState();
        if (currentState == Lifecycle.State.INITIALIZED || currentState.isAtLeast(Lifecycle.State.STARTED)) {
            savedStateRegistry.runOnNextRecreation(OnRecreation.class);
        } else {
            lifecycle.addObserver(new LegacySavedStateHandleController$tryToAddRecreator$1(lifecycle, savedStateRegistry));
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Landroidx/lifecycle/LegacySavedStateHandleController$OnRecreation;", "Landroidx/savedstate/SavedStateRegistry$AutoRecreated;", "()V", "onRecreated", "", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: LegacySavedStateHandleController.kt */
    public static final class OnRecreation implements SavedStateRegistry.AutoRecreated {
        public void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner) {
            Intrinsics.checkNotNullParameter(savedStateRegistryOwner, "owner");
            if (savedStateRegistryOwner instanceof ViewModelStoreOwner) {
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) savedStateRegistryOwner).getViewModelStore();
                SavedStateRegistry savedStateRegistry = savedStateRegistryOwner.getSavedStateRegistry();
                for (String str : viewModelStore.keys()) {
                    ViewModel viewModel = viewModelStore.get(str);
                    Intrinsics.checkNotNull(viewModel);
                    LegacySavedStateHandleController.attachHandleIfNeeded(viewModel, savedStateRegistry, savedStateRegistryOwner.getLifecycle());
                }
                if (!viewModelStore.keys().isEmpty()) {
                    savedStateRegistry.runOnNextRecreation(OnRecreation.class);
                    return;
                }
                return;
            }
            throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner".toString());
        }
    }
}
