package com.google.android.material.color;

import android.content.Context;
import android.content.res.Configuration;
import android.view.ContextThemeWrapper;
import com.google.android.material.R;
import java.util.Map;

class ResourcesLoaderColorResourcesOverride implements ColorResourcesOverride {
    private ResourcesLoaderColorResourcesOverride() {
    }

    public boolean applyIfPossible(Context context, Map<Integer, Integer> map) {
        if (!ResourcesLoaderUtils.addResourcesLoaderToContext(context, map)) {
            return false;
        }
        ThemeUtils.applyThemeOverlay(context, R.style.ThemeOverlay_Material3_PersonalizedColors);
        return true;
    }

    public Context wrapContextIfPossible(Context context, Map<Integer, Integer> map) {
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, R.style.ThemeOverlay_Material3_PersonalizedColors);
        contextThemeWrapper.applyOverrideConfiguration(new Configuration());
        return ResourcesLoaderUtils.addResourcesLoaderToContext(contextThemeWrapper, map) ? contextThemeWrapper : context;
    }

    static ColorResourcesOverride getInstance() {
        return ResourcesLoaderColorResourcesOverrideSingleton.INSTANCE;
    }

    private static class ResourcesLoaderColorResourcesOverrideSingleton {
        /* access modifiers changed from: private */
        public static final ResourcesLoaderColorResourcesOverride INSTANCE = new ResourcesLoaderColorResourcesOverride();

        private ResourcesLoaderColorResourcesOverrideSingleton() {
        }
    }
}
