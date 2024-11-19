package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Landroid/view/View;", "child", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ViewGroup.kt */
final class ViewGroupKt$descendants$1$1 extends Lambda implements Function1<View, Iterator<? extends View>> {
    public static final ViewGroupKt$descendants$1$1 INSTANCE = new ViewGroupKt$descendants$1$1();

    ViewGroupKt$descendants$1$1() {
        super(1);
    }

    public final Iterator<View> invoke(View view) {
        Sequence<View> children;
        ViewGroup viewGroup = view instanceof ViewGroup ? (ViewGroup) view : null;
        if (viewGroup == null || (children = ViewGroupKt.getChildren(viewGroup)) == null) {
            return null;
        }
        return children.iterator();
    }
}
