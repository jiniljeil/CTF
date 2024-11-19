package androidx.activity;

import android.app.Application;
import androidx.lifecycle.SavedStateViewModelFactory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroidx/lifecycle/SavedStateViewModelFactory;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ComponentActivity.kt */
final class ComponentActivity$defaultViewModelProviderFactory$2 extends Lambda implements Function0<SavedStateViewModelFactory> {
    final /* synthetic */ ComponentActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ComponentActivity$defaultViewModelProviderFactory$2(ComponentActivity componentActivity) {
        super(0);
        this.this$0 = componentActivity;
    }

    public final SavedStateViewModelFactory invoke() {
        Application application = this.this$0.getApplication();
        ComponentActivity componentActivity = this.this$0;
        return new SavedStateViewModelFactory(application, componentActivity, componentActivity.getIntent() != null ? this.this$0.getIntent().getExtras() : null);
    }
}
