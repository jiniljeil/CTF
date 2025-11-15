package com.google.android.material.color.utilities;

import java.util.function.Function;

public final class MaterialDynamicColors {
    public DynamicColor highestSurface(DynamicScheme dynamicScheme) {
        return dynamicScheme.isDark ? surfaceBright() : surfaceDim();
    }

    public DynamicColor primaryPaletteKeyColor() {
        return DynamicColor.fromPalette("primary_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda72(), new MaterialDynamicColors$$ExternalSyntheticLambda73());
    }

    public DynamicColor secondaryPaletteKeyColor() {
        return DynamicColor.fromPalette("secondary_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda79(), new MaterialDynamicColors$$ExternalSyntheticLambda80());
    }

    public DynamicColor tertiaryPaletteKeyColor() {
        return DynamicColor.fromPalette("tertiary_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda91(), new MaterialDynamicColors$$ExternalSyntheticLambda92());
    }

    public DynamicColor neutralPaletteKeyColor() {
        return DynamicColor.fromPalette("neutral_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda11(), new MaterialDynamicColors$$ExternalSyntheticLambda22());
    }

    public DynamicColor neutralVariantPaletteKeyColor() {
        return DynamicColor.fromPalette("neutral_variant_palette_key_color", new MaterialDynamicColors$$ExternalSyntheticLambda144(), new MaterialDynamicColors$$ExternalSyntheticLambda145());
    }

    public DynamicColor background() {
        return new DynamicColor("background", new MaterialDynamicColors$$ExternalSyntheticLambda100(), new MaterialDynamicColors$$ExternalSyntheticLambda101(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$background$11(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 6.0d : 98.0d);
    }

    public DynamicColor onBackground() {
        return new DynamicColor("on_background", new MaterialDynamicColors$$ExternalSyntheticLambda104(), new MaterialDynamicColors$$ExternalSyntheticLambda105(), false, new MaterialDynamicColors$$ExternalSyntheticLambda106(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(3.0d, 3.0d, 4.5d, 7.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onBackground$13(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onBackground$14$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m70lambda$onBackground$14$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return background();
    }

    public DynamicColor surface() {
        return new DynamicColor("surface", new MaterialDynamicColors$$ExternalSyntheticLambda0(), new MaterialDynamicColors$$ExternalSyntheticLambda74(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$surface$16(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 6.0d : 98.0d);
    }

    public DynamicColor surfaceDim() {
        return new DynamicColor("surface_dim", new MaterialDynamicColors$$ExternalSyntheticLambda33(), new MaterialDynamicColors$$ExternalSyntheticLambda44(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$surfaceDim$18(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 6.0d : 87.0d);
    }

    public DynamicColor surfaceBright() {
        return new DynamicColor("surface_bright", new MaterialDynamicColors$$ExternalSyntheticLambda110(), new MaterialDynamicColors$$ExternalSyntheticLambda111(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$surfaceBright$20(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 24.0d : 98.0d);
    }

    public DynamicColor surfaceContainerLowest() {
        return new DynamicColor("surface_container_lowest", new MaterialDynamicColors$$ExternalSyntheticLambda7(), new MaterialDynamicColors$$ExternalSyntheticLambda8(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$surfaceContainerLowest$22(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 4.0d : 100.0d);
    }

    public DynamicColor surfaceContainerLow() {
        return new DynamicColor("surface_container_low", new MaterialDynamicColors$$ExternalSyntheticLambda81(), new MaterialDynamicColors$$ExternalSyntheticLambda82(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$surfaceContainerLow$24(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 10.0d : 96.0d);
    }

    public DynamicColor surfaceContainer() {
        return new DynamicColor("surface_container", new MaterialDynamicColors$$ExternalSyntheticLambda38(), new MaterialDynamicColors$$ExternalSyntheticLambda39(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$surfaceContainer$26(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 12.0d : 94.0d);
    }

    public DynamicColor surfaceContainerHigh() {
        return new DynamicColor("surface_container_high", new MaterialDynamicColors$$ExternalSyntheticLambda102(), new MaterialDynamicColors$$ExternalSyntheticLambda103(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$surfaceContainerHigh$28(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 17.0d : 92.0d);
    }

    public DynamicColor surfaceContainerHighest() {
        return new DynamicColor("surface_container_highest", new MaterialDynamicColors$$ExternalSyntheticLambda150(), new MaterialDynamicColors$$ExternalSyntheticLambda152(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$surfaceContainerHighest$30(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 22.0d : 90.0d);
    }

    public DynamicColor onSurface() {
        return new DynamicColor("on_surface", new MaterialDynamicColors$$ExternalSyntheticLambda140(), new MaterialDynamicColors$$ExternalSyntheticLambda151(), false, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onSurface$32(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
    }

    public DynamicColor surfaceVariant() {
        return new DynamicColor("surface_variant", new MaterialDynamicColors$$ExternalSyntheticLambda142(), new MaterialDynamicColors$$ExternalSyntheticLambda143(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$surfaceVariant$34(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
    }

    public DynamicColor onSurfaceVariant() {
        return new DynamicColor("on_surface_variant", new MaterialDynamicColors$$ExternalSyntheticLambda40(), new MaterialDynamicColors$$ExternalSyntheticLambda41(), false, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onSurfaceVariant$36(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 30.0d);
    }

    public DynamicColor inverseSurface() {
        return new DynamicColor("inverse_surface", new MaterialDynamicColors$$ExternalSyntheticLambda42(), new MaterialDynamicColors$$ExternalSyntheticLambda43(), false, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$inverseSurface$38(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 20.0d);
    }

    public DynamicColor inverseOnSurface() {
        return new DynamicColor("inverse_on_surface", new MaterialDynamicColors$$ExternalSyntheticLambda18(), new MaterialDynamicColors$$ExternalSyntheticLambda19(), false, new MaterialDynamicColors$$ExternalSyntheticLambda20(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$inverseOnSurface$40(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 20.0d : 95.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$inverseOnSurface$41$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m68lambda$inverseOnSurface$41$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return inverseSurface();
    }

    public DynamicColor outline() {
        return new DynamicColor("outline", new MaterialDynamicColors$$ExternalSyntheticLambda9(), new MaterialDynamicColors$$ExternalSyntheticLambda10(), false, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.5d, 3.0d, 4.5d, 7.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$outline$43(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 60.0d : 50.0d);
    }

    public DynamicColor outlineVariant() {
        return new DynamicColor("outline_variant", new MaterialDynamicColors$$ExternalSyntheticLambda108(), new MaterialDynamicColors$$ExternalSyntheticLambda109(), false, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$outlineVariant$45(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 80.0d);
    }

    public DynamicColor shadow() {
        return new DynamicColor("shadow", new MaterialDynamicColors$$ExternalSyntheticLambda148(), new MaterialDynamicColors$$ExternalSyntheticLambda149(), false, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    public DynamicColor scrim() {
        return new DynamicColor("scrim", new MaterialDynamicColors$$ExternalSyntheticLambda60(), new MaterialDynamicColors$$ExternalSyntheticLambda61(), false, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    public DynamicColor surfaceTint() {
        return new DynamicColor("surface_tint", new MaterialDynamicColors$$ExternalSyntheticLambda12(), new MaterialDynamicColors$$ExternalSyntheticLambda13(), true, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$surfaceTint$51(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
    }

    public DynamicColor primary() {
        return new DynamicColor("primary", new MaterialDynamicColors$$ExternalSyntheticLambda57(), new MaterialDynamicColors$$ExternalSyntheticLambda58(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), new MaterialDynamicColors$$ExternalSyntheticLambda59(this));
    }

    static /* synthetic */ Double lambda$primary$53(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 100.0d : 0.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$primary$54$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m94lambda$primary$54$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(primaryContainer(), primary(), 15.0d, TonePolarity.NEARER, false);
    }

    public DynamicColor onPrimary() {
        return new DynamicColor("on_primary", new MaterialDynamicColors$$ExternalSyntheticLambda112(), new MaterialDynamicColors$$ExternalSyntheticLambda113(), false, new MaterialDynamicColors$$ExternalSyntheticLambda114(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onPrimary$56(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 20.0d : 100.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimary$57$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m73lambda$onPrimary$57$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return primary();
    }

    public DynamicColor primaryContainer() {
        return new DynamicColor("primary_container", new MaterialDynamicColors$$ExternalSyntheticLambda97(), new MaterialDynamicColors$$ExternalSyntheticLambda98(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda99(this));
    }

    static /* synthetic */ Double lambda$primaryContainer$59(DynamicScheme dynamicScheme) {
        if (isFidelity(dynamicScheme)) {
            return Double.valueOf(performAlbers(dynamicScheme.sourceColorHct, dynamicScheme));
        }
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 85.0d : 25.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$primaryContainer$60$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m95lambda$primaryContainer$60$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(primaryContainer(), primary(), 15.0d, TonePolarity.NEARER, false);
    }

    public DynamicColor onPrimaryContainer() {
        return new DynamicColor("on_primary_container", new MaterialDynamicColors$$ExternalSyntheticLambda135(), new MaterialDynamicColors$$ExternalSyntheticLambda136(this), false, new MaterialDynamicColors$$ExternalSyntheticLambda137(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimaryContainer$62$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ Double m74lambda$onPrimaryContainer$62$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        if (isFidelity(dynamicScheme)) {
            return Double.valueOf(DynamicColor.foregroundTone(primaryContainer().tone.apply(dynamicScheme).doubleValue(), 4.5d));
        }
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 0.0d : 100.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimaryContainer$63$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m75lambda$onPrimaryContainer$63$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return primaryContainer();
    }

    public DynamicColor inversePrimary() {
        return new DynamicColor("inverse_primary", new MaterialDynamicColors$$ExternalSyntheticLambda115(), new MaterialDynamicColors$$ExternalSyntheticLambda116(), false, new MaterialDynamicColors$$ExternalSyntheticLambda117(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$inversePrimary$65(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 40.0d : 80.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$inversePrimary$66$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m69lambda$inversePrimary$66$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return inverseSurface();
    }

    public DynamicColor secondary() {
        return new DynamicColor("secondary", new MaterialDynamicColors$$ExternalSyntheticLambda4(), new MaterialDynamicColors$$ExternalSyntheticLambda5(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), new MaterialDynamicColors$$ExternalSyntheticLambda6(this));
    }

    static /* synthetic */ Double lambda$secondary$68(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$secondary$69$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m98lambda$secondary$69$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(secondaryContainer(), secondary(), 15.0d, TonePolarity.NEARER, false);
    }

    public DynamicColor onSecondary() {
        return new DynamicColor("on_secondary", new MaterialDynamicColors$$ExternalSyntheticLambda1(), new MaterialDynamicColors$$ExternalSyntheticLambda2(), false, new MaterialDynamicColors$$ExternalSyntheticLambda3(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onSecondary$71(DynamicScheme dynamicScheme) {
        double d = 100.0d;
        if (isMonochrome(dynamicScheme)) {
            if (dynamicScheme.isDark) {
                d = 10.0d;
            }
            return Double.valueOf(d);
        }
        if (dynamicScheme.isDark) {
            d = 20.0d;
        }
        return Double.valueOf(d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondary$72$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m80lambda$onSecondary$72$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return secondary();
    }

    public DynamicColor secondaryContainer() {
        return new DynamicColor("secondary_container", new MaterialDynamicColors$$ExternalSyntheticLambda83(), new MaterialDynamicColors$$ExternalSyntheticLambda84(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda86(this));
    }

    static /* synthetic */ Double lambda$secondaryContainer$74(DynamicScheme dynamicScheme) {
        double d = 30.0d;
        double d2 = dynamicScheme.isDark ? 30.0d : 90.0d;
        if (isMonochrome(dynamicScheme)) {
            if (!dynamicScheme.isDark) {
                d = 85.0d;
            }
            return Double.valueOf(d);
        } else if (!isFidelity(dynamicScheme)) {
            return Double.valueOf(d2);
        } else {
            return Double.valueOf(performAlbers(dynamicScheme.secondaryPalette.getHct(findDesiredChromaByTone(dynamicScheme.secondaryPalette.getHue(), dynamicScheme.secondaryPalette.getChroma(), d2, !dynamicScheme.isDark)), dynamicScheme));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$secondaryContainer$75$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m99lambda$secondaryContainer$75$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(secondaryContainer(), secondary(), 15.0d, TonePolarity.NEARER, false);
    }

    public DynamicColor onSecondaryContainer() {
        return new DynamicColor("on_secondary_container", new MaterialDynamicColors$$ExternalSyntheticLambda25(), new MaterialDynamicColors$$ExternalSyntheticLambda26(this), false, new MaterialDynamicColors$$ExternalSyntheticLambda27(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondaryContainer$77$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ Double m81lambda$onSecondaryContainer$77$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        if (isFidelity(dynamicScheme)) {
            return Double.valueOf(DynamicColor.foregroundTone(secondaryContainer().tone.apply(dynamicScheme).doubleValue(), 4.5d));
        }
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondaryContainer$78$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m82lambda$onSecondaryContainer$78$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return secondaryContainer();
    }

    public DynamicColor tertiary() {
        return new DynamicColor("tertiary", new MaterialDynamicColors$$ExternalSyntheticLambda67(), new MaterialDynamicColors$$ExternalSyntheticLambda68(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), new MaterialDynamicColors$$ExternalSyntheticLambda69(this));
    }

    static /* synthetic */ Double lambda$tertiary$80(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 90.0d : 25.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$tertiary$81$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m102lambda$tertiary$81$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(tertiaryContainer(), tertiary(), 15.0d, TonePolarity.NEARER, false);
    }

    public DynamicColor onTertiary() {
        return new DynamicColor("on_tertiary", new MaterialDynamicColors$$ExternalSyntheticLambda107(), new MaterialDynamicColors$$ExternalSyntheticLambda118(), false, new MaterialDynamicColors$$ExternalSyntheticLambda129(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onTertiary$83(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
        }
        return Double.valueOf(dynamicScheme.isDark ? 20.0d : 100.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiary$84$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m87lambda$onTertiary$84$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return tertiary();
    }

    public DynamicColor tertiaryContainer() {
        return new DynamicColor("tertiary_container", new MaterialDynamicColors$$ExternalSyntheticLambda159(), new MaterialDynamicColors$$ExternalSyntheticLambda160(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda161(this));
    }

    static /* synthetic */ Double lambda$tertiaryContainer$86(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 60.0d : 49.0d);
        } else if (!isFidelity(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
        } else {
            return Double.valueOf(DislikeAnalyzer.fixIfDisliked(dynamicScheme.tertiaryPalette.getHct(performAlbers(dynamicScheme.tertiaryPalette.getHct(dynamicScheme.sourceColorHct.getTone()), dynamicScheme))).getTone());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$tertiaryContainer$87$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m103lambda$tertiaryContainer$87$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(tertiaryContainer(), tertiary(), 15.0d, TonePolarity.NEARER, false);
    }

    public DynamicColor onTertiaryContainer() {
        return new DynamicColor("on_tertiary_container", new MaterialDynamicColors$$ExternalSyntheticLambda21(), new MaterialDynamicColors$$ExternalSyntheticLambda23(this), false, new MaterialDynamicColors$$ExternalSyntheticLambda24(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiaryContainer$89$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ Double m88lambda$onTertiaryContainer$89$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        if (isMonochrome(dynamicScheme)) {
            return Double.valueOf(dynamicScheme.isDark ? 0.0d : 100.0d);
        } else if (isFidelity(dynamicScheme)) {
            return Double.valueOf(DynamicColor.foregroundTone(tertiaryContainer().tone.apply(dynamicScheme).doubleValue(), 4.5d));
        } else {
            return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiaryContainer$90$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m89lambda$onTertiaryContainer$90$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return tertiaryContainer();
    }

    public DynamicColor error() {
        return new DynamicColor("error", new MaterialDynamicColors$$ExternalSyntheticLambda32(), new MaterialDynamicColors$$ExternalSyntheticLambda34(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), new MaterialDynamicColors$$ExternalSyntheticLambda35(this));
    }

    static /* synthetic */ Double lambda$error$92(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 40.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$error$93$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m66lambda$error$93$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(errorContainer(), error(), 15.0d, TonePolarity.NEARER, false);
    }

    public DynamicColor onError() {
        return new DynamicColor("on_error", new MaterialDynamicColors$$ExternalSyntheticLambda130(), new MaterialDynamicColors$$ExternalSyntheticLambda131(), false, new MaterialDynamicColors$$ExternalSyntheticLambda132(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onError$95(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 20.0d : 100.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onError$96$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m71lambda$onError$96$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return error();
    }

    public DynamicColor errorContainer() {
        return new DynamicColor("error_container", new MaterialDynamicColors$$ExternalSyntheticLambda50(), new MaterialDynamicColors$$ExternalSyntheticLambda51(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda52(this));
    }

    static /* synthetic */ Double lambda$errorContainer$98(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$errorContainer$99$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m67lambda$errorContainer$99$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(errorContainer(), error(), 15.0d, TonePolarity.NEARER, false);
    }

    public DynamicColor onErrorContainer() {
        return new DynamicColor("on_error_container", new MaterialDynamicColors$$ExternalSyntheticLambda45(), new MaterialDynamicColors$$ExternalSyntheticLambda46(), false, new MaterialDynamicColors$$ExternalSyntheticLambda47(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onErrorContainer$101(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 90.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onErrorContainer$102$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m72lambda$onErrorContainer$102$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return errorContainer();
    }

    public DynamicColor primaryFixed() {
        return new DynamicColor("primary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda153(), new MaterialDynamicColors$$ExternalSyntheticLambda154(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda155(this));
    }

    static /* synthetic */ Double lambda$primaryFixed$104(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 40.0d : 90.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$primaryFixed$105$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m96lambda$primaryFixed$105$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(primaryFixed(), primaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    public DynamicColor primaryFixedDim() {
        return new DynamicColor("primary_fixed_dim", new MaterialDynamicColors$$ExternalSyntheticLambda156(), new MaterialDynamicColors$$ExternalSyntheticLambda157(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda158(this));
    }

    static /* synthetic */ Double lambda$primaryFixedDim$107(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 30.0d : 80.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$primaryFixedDim$108$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m97lambda$primaryFixedDim$108$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(primaryFixed(), primaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    public DynamicColor onPrimaryFixed() {
        return new DynamicColor("on_primary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda28(), new MaterialDynamicColors$$ExternalSyntheticLambda29(), false, new MaterialDynamicColors$$ExternalSyntheticLambda30(this), new MaterialDynamicColors$$ExternalSyntheticLambda31(this), new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onPrimaryFixed$110(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 100.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimaryFixed$111$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m76lambda$onPrimaryFixed$111$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return primaryFixedDim();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimaryFixed$112$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m77lambda$onPrimaryFixed$112$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return primaryFixed();
    }

    public DynamicColor onPrimaryFixedVariant() {
        return new DynamicColor("on_primary_fixed_variant", new MaterialDynamicColors$$ExternalSyntheticLambda122(), new MaterialDynamicColors$$ExternalSyntheticLambda123(), false, new MaterialDynamicColors$$ExternalSyntheticLambda124(this), new MaterialDynamicColors$$ExternalSyntheticLambda125(this), new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onPrimaryFixedVariant$114(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 90.0d : 30.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimaryFixedVariant$115$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m78lambda$onPrimaryFixedVariant$115$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return primaryFixedDim();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onPrimaryFixedVariant$116$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m79lambda$onPrimaryFixedVariant$116$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return primaryFixed();
    }

    public DynamicColor secondaryFixed() {
        return new DynamicColor("secondary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda119(), new MaterialDynamicColors$$ExternalSyntheticLambda120(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda121(this));
    }

    static /* synthetic */ Double lambda$secondaryFixed$118(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 80.0d : 90.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$secondaryFixed$119$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m100lambda$secondaryFixed$119$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(secondaryFixed(), secondaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    public DynamicColor secondaryFixedDim() {
        return new DynamicColor("secondary_fixed_dim", new MaterialDynamicColors$$ExternalSyntheticLambda138(), new MaterialDynamicColors$$ExternalSyntheticLambda139(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda141(this));
    }

    static /* synthetic */ Double lambda$secondaryFixedDim$121(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 70.0d : 80.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$secondaryFixedDim$122$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m101lambda$secondaryFixedDim$122$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(secondaryFixed(), secondaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    public DynamicColor onSecondaryFixed() {
        return new DynamicColor("on_secondary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda14(), new MaterialDynamicColors$$ExternalSyntheticLambda15(), false, new MaterialDynamicColors$$ExternalSyntheticLambda16(this), new MaterialDynamicColors$$ExternalSyntheticLambda17(this), new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondaryFixed$125$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m83lambda$onSecondaryFixed$125$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return secondaryFixedDim();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondaryFixed$126$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m84lambda$onSecondaryFixed$126$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return secondaryFixed();
    }

    public DynamicColor onSecondaryFixedVariant() {
        return new DynamicColor("on_secondary_fixed_variant", new MaterialDynamicColors$$ExternalSyntheticLambda62(), new MaterialDynamicColors$$ExternalSyntheticLambda63(), false, new MaterialDynamicColors$$ExternalSyntheticLambda64(this), new MaterialDynamicColors$$ExternalSyntheticLambda65(this), new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onSecondaryFixedVariant$128(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 25.0d : 30.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondaryFixedVariant$129$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m85lambda$onSecondaryFixedVariant$129$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return secondaryFixedDim();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onSecondaryFixedVariant$130$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m86lambda$onSecondaryFixedVariant$130$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return secondaryFixed();
    }

    public DynamicColor tertiaryFixed() {
        return new DynamicColor("tertiary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda53(), new MaterialDynamicColors$$ExternalSyntheticLambda54(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda56(this));
    }

    static /* synthetic */ Double lambda$tertiaryFixed$132(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 40.0d : 90.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$tertiaryFixed$133$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m104lambda$tertiaryFixed$133$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(tertiaryFixed(), tertiaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    public DynamicColor tertiaryFixedDim() {
        return new DynamicColor("tertiary_fixed_dim", new MaterialDynamicColors$$ExternalSyntheticLambda126(), new MaterialDynamicColors$$ExternalSyntheticLambda127(), true, new MaterialDynamicColors$$ExternalSyntheticLambda162(this), (Function<DynamicScheme, DynamicColor>) null, new ContrastCurve(1.0d, 1.0d, 3.0d, 7.0d), new MaterialDynamicColors$$ExternalSyntheticLambda128(this));
    }

    static /* synthetic */ Double lambda$tertiaryFixedDim$135(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 30.0d : 80.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$tertiaryFixedDim$136$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ ToneDeltaPair m105lambda$tertiaryFixedDim$136$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return new ToneDeltaPair(tertiaryFixed(), tertiaryFixedDim(), 10.0d, TonePolarity.LIGHTER, true);
    }

    public DynamicColor onTertiaryFixed() {
        return new DynamicColor("on_tertiary_fixed", new MaterialDynamicColors$$ExternalSyntheticLambda87(), new MaterialDynamicColors$$ExternalSyntheticLambda88(), false, new MaterialDynamicColors$$ExternalSyntheticLambda89(this), new MaterialDynamicColors$$ExternalSyntheticLambda90(this), new ContrastCurve(4.5d, 7.0d, 11.0d, 21.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onTertiaryFixed$138(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 100.0d : 10.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiaryFixed$139$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m90lambda$onTertiaryFixed$139$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return tertiaryFixedDim();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiaryFixed$140$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m91lambda$onTertiaryFixed$140$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return tertiaryFixed();
    }

    public DynamicColor onTertiaryFixedVariant() {
        return new DynamicColor("on_tertiary_fixed_variant", new MaterialDynamicColors$$ExternalSyntheticLambda75(), new MaterialDynamicColors$$ExternalSyntheticLambda76(), false, new MaterialDynamicColors$$ExternalSyntheticLambda77(this), new MaterialDynamicColors$$ExternalSyntheticLambda78(this), new ContrastCurve(3.0d, 4.5d, 7.0d, 11.0d), (Function<DynamicScheme, ToneDeltaPair>) null);
    }

    static /* synthetic */ Double lambda$onTertiaryFixedVariant$142(DynamicScheme dynamicScheme) {
        return Double.valueOf(isMonochrome(dynamicScheme) ? 90.0d : 30.0d);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiaryFixedVariant$143$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m92lambda$onTertiaryFixedVariant$143$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return tertiaryFixedDim();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$onTertiaryFixedVariant$144$com-google-android-material-color-utilities-MaterialDynamicColors  reason: not valid java name */
    public /* synthetic */ DynamicColor m93lambda$onTertiaryFixedVariant$144$comgoogleandroidmaterialcolorutilitiesMaterialDynamicColors(DynamicScheme dynamicScheme) {
        return tertiaryFixed();
    }

    public DynamicColor controlActivated() {
        return DynamicColor.fromPalette("control_activated", new MaterialDynamicColors$$ExternalSyntheticLambda70(), new MaterialDynamicColors$$ExternalSyntheticLambda71());
    }

    static /* synthetic */ Double lambda$controlActivated$146(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 90.0d);
    }

    public DynamicColor controlNormal() {
        return DynamicColor.fromPalette("control_normal", new MaterialDynamicColors$$ExternalSyntheticLambda55(), new MaterialDynamicColors$$ExternalSyntheticLambda66());
    }

    static /* synthetic */ Double lambda$controlNormal$148(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 80.0d : 30.0d);
    }

    public DynamicColor controlHighlight() {
        return new DynamicColor("control_highlight", new MaterialDynamicColors$$ExternalSyntheticLambda93(), new MaterialDynamicColors$$ExternalSyntheticLambda94(), false, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, DynamicColor>) null, (ContrastCurve) null, (Function<DynamicScheme, ToneDeltaPair>) null, new MaterialDynamicColors$$ExternalSyntheticLambda95());
    }

    static /* synthetic */ Double lambda$controlHighlight$150(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 100.0d : 0.0d);
    }

    static /* synthetic */ Double lambda$controlHighlight$151(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 0.2d : 0.12d);
    }

    public DynamicColor textPrimaryInverse() {
        return DynamicColor.fromPalette("text_primary_inverse", new MaterialDynamicColors$$ExternalSyntheticLambda36(), new MaterialDynamicColors$$ExternalSyntheticLambda37());
    }

    static /* synthetic */ Double lambda$textPrimaryInverse$153(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
    }

    public DynamicColor textSecondaryAndTertiaryInverse() {
        return DynamicColor.fromPalette("text_secondary_and_tertiary_inverse", new MaterialDynamicColors$$ExternalSyntheticLambda48(), new MaterialDynamicColors$$ExternalSyntheticLambda49());
    }

    static /* synthetic */ Double lambda$textSecondaryAndTertiaryInverse$155(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 30.0d : 80.0d);
    }

    public DynamicColor textPrimaryInverseDisableOnly() {
        return DynamicColor.fromPalette("text_primary_inverse_disable_only", new MaterialDynamicColors$$ExternalSyntheticLambda133(), new MaterialDynamicColors$$ExternalSyntheticLambda134());
    }

    static /* synthetic */ Double lambda$textPrimaryInverseDisableOnly$157(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
    }

    public DynamicColor textSecondaryAndTertiaryInverseDisabled() {
        return DynamicColor.fromPalette("text_secondary_and_tertiary_inverse_disabled", new MaterialDynamicColors$$ExternalSyntheticLambda85(), new MaterialDynamicColors$$ExternalSyntheticLambda96());
    }

    static /* synthetic */ Double lambda$textSecondaryAndTertiaryInverseDisabled$159(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
    }

    public DynamicColor textHintInverse() {
        return DynamicColor.fromPalette("text_hint_inverse", new MaterialDynamicColors$$ExternalSyntheticLambda146(), new MaterialDynamicColors$$ExternalSyntheticLambda147());
    }

    static /* synthetic */ Double lambda$textHintInverse$161(DynamicScheme dynamicScheme) {
        return Double.valueOf(dynamicScheme.isDark ? 10.0d : 90.0d);
    }

    private static ViewingConditions viewingConditionsForAlbers(DynamicScheme dynamicScheme) {
        return ViewingConditions.defaultWithBackgroundLstar(dynamicScheme.isDark ? 30.0d : 80.0d);
    }

    private static boolean isFidelity(DynamicScheme dynamicScheme) {
        return dynamicScheme.variant == Variant.FIDELITY || dynamicScheme.variant == Variant.CONTENT;
    }

    private static boolean isMonochrome(DynamicScheme dynamicScheme) {
        return dynamicScheme.variant == Variant.MONOCHROME;
    }

    static double findDesiredChromaByTone(double d, double d2, double d3, boolean z) {
        Hct from = Hct.from(d, d2, d3);
        if (from.getChroma() >= d2) {
            return d3;
        }
        Hct hct = from;
        double chroma = from.getChroma();
        double d4 = d3;
        while (hct.getChroma() < d2) {
            double d5 = d4 + (z ? -1.0d : 1.0d);
            Hct from2 = Hct.from(d, d2, d5);
            if (chroma > from2.getChroma() || Math.abs(from2.getChroma() - d2) < 0.4d) {
                return d5;
            }
            if (Math.abs(from2.getChroma() - d2) < Math.abs(hct.getChroma() - d2)) {
                hct = from2;
            }
            chroma = Math.max(chroma, from2.getChroma());
            d4 = d5;
        }
        return d4;
    }

    static double performAlbers(Hct hct, DynamicScheme dynamicScheme) {
        Hct inViewingConditions = hct.inViewingConditions(viewingConditionsForAlbers(dynamicScheme));
        if (!DynamicColor.tonePrefersLightForeground(hct.getTone()) || DynamicColor.toneAllowsLightForeground(inViewingConditions.getTone())) {
            return DynamicColor.enableLightForeground(inViewingConditions.getTone());
        }
        return DynamicColor.enableLightForeground(hct.getTone());
    }
}
