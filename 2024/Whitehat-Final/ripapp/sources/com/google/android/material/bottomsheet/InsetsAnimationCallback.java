package com.google.android.material.bottomsheet;

import android.view.View;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.animation.AnimationUtils;
import java.util.Iterator;
import java.util.List;

class InsetsAnimationCallback extends WindowInsetsAnimationCompat.Callback {
    private int startTranslationY;
    private int startY;
    private final int[] tmpLocation = new int[2];
    private final View view;

    public InsetsAnimationCallback(View view2) {
        super(0);
        this.view = view2;
    }

    public void onPrepare(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        this.view.getLocationOnScreen(this.tmpLocation);
        this.startY = this.tmpLocation[1];
    }

    public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat windowInsetsAnimationCompat, WindowInsetsAnimationCompat.BoundsCompat boundsCompat) {
        this.view.getLocationOnScreen(this.tmpLocation);
        int i = this.startY - this.tmpLocation[1];
        this.startTranslationY = i;
        this.view.setTranslationY((float) i);
        return boundsCompat;
    }

    public WindowInsetsCompat onProgress(WindowInsetsCompat windowInsetsCompat, List<WindowInsetsAnimationCompat> list) {
        Iterator<WindowInsetsAnimationCompat> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            WindowInsetsAnimationCompat next = it.next();
            if ((next.getTypeMask() & WindowInsetsCompat.Type.ime()) != 0) {
                this.view.setTranslationY((float) AnimationUtils.lerp(this.startTranslationY, 0, next.getInterpolatedFraction()));
                break;
            }
        }
        return windowInsetsCompat;
    }

    public void onEnd(WindowInsetsAnimationCompat windowInsetsAnimationCompat) {
        this.view.setTranslationY(0.0f);
    }
}
