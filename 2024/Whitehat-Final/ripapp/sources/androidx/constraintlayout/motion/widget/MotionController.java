package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.motion.utils.ViewTimeCycle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class MotionController {
    static final int BOUNCE = 4;
    private static final boolean DEBUG = false;
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final boolean FAVOR_FIXED_SIZE_VIEWS = false;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final int INTERPOLATOR_UNDEFINED = -3;
    static final int LINEAR = 3;
    static final int OVERSHOOT = 5;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    public static final int ROTATION_LEFT = 2;
    public static final int ROTATION_RIGHT = 1;
    private static final int SPLINE_STRING = -1;
    private static final String TAG = "MotionController";
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    private int MAX_DIMENSION = 4;
    String[] attributeTable;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpolatorCount;
    private String[] mAttributeNames;
    private HashMap<String, ViewSpline> mAttributesMap;
    String mConstraintTag;
    float mCurrentCenterX;
    float mCurrentCenterY;
    private int mCurveFitType = -1;
    private HashMap<String, ViewOscillator> mCycleMap;
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    boolean mForceMeasure = false;
    int mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList<Key> mKeyList = new ArrayList<>();
    private KeyTrigger[] mKeyTriggers;
    private ArrayList<MotionPaths> mMotionPaths = new ArrayList<>();
    float mMotionStagger = Float.NaN;
    private boolean mNoMovement = false;
    private int mPathMotionArc = Key.UNSET;
    private Interpolator mQuantizeMotionInterpolator = null;
    private float mQuantizeMotionPhase = Float.NaN;
    private int mQuantizeMotionSteps = Key.UNSET;
    private CurveFit[] mSpline;
    float mStaggerOffset = 0.0f;
    float mStaggerScale = 1.0f;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    Rect mTempRect = new Rect();
    private HashMap<String, ViewTimeCycle> mTimeCycleAttributesMap;
    private int mTransformPivotTarget = Key.UNSET;
    private View mTransformPivotView = null;
    private float[] mValuesBuff = new float[4];
    private float[] mVelocity = new float[1];
    View mView;

    public int getTransformPivotTarget() {
        return this.mTransformPivotTarget;
    }

    public void setTransformPivotTarget(int i) {
        this.mTransformPivotTarget = i;
        this.mTransformPivotView = null;
    }

    /* access modifiers changed from: package-private */
    public MotionPaths getKeyFrame(int i) {
        return this.mMotionPaths.get(i);
    }

    MotionController(View view) {
        setView(view);
    }

    public float getStartX() {
        return this.mStartMotionPath.x;
    }

    public float getStartY() {
        return this.mStartMotionPath.y;
    }

    public float getFinalX() {
        return this.mEndMotionPath.x;
    }

    public float getFinalY() {
        return this.mEndMotionPath.y;
    }

    public float getStartWidth() {
        return this.mStartMotionPath.width;
    }

    public float getStartHeight() {
        return this.mStartMotionPath.height;
    }

    public float getFinalWidth() {
        return this.mEndMotionPath.width;
    }

    public float getFinalHeight() {
        return this.mEndMotionPath.height;
    }

    public int getAnimateRelativeTo() {
        return this.mStartMotionPath.mAnimateRelativeTo;
    }

    public void setupRelative(MotionController motionController) {
        this.mStartMotionPath.setupRelative(motionController, motionController.mStartMotionPath);
        this.mEndMotionPath.setupRelative(motionController, motionController.mEndMotionPath);
    }

    public float getCenterX() {
        return this.mCurrentCenterX;
    }

    public float getCenterY() {
        return this.mCurrentCenterY;
    }

    public void getCenter(double d, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.mSpline[0].getPos(d, dArr);
        this.mSpline[0].getSlope(d, dArr2);
        Arrays.fill(fArr2, 0.0f);
        this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, dArr, fArr, dArr2, fArr2);
    }

    public void remeasure() {
        this.mForceMeasure = true;
    }

    /* access modifiers changed from: package-private */
    public void buildPath(float[] fArr, int i) {
        int i2 = i;
        float f = 1.0f;
        float f2 = 1.0f / ((float) (i2 - 1));
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        ViewOscillator viewOscillator = null;
        SplineSet splineSet = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = this.mAttributesMap;
        SplineSet splineSet2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, ViewOscillator> hashMap3 = this.mCycleMap;
        ViewOscillator viewOscillator2 = hashMap3 == null ? null : hashMap3.get("translationX");
        HashMap<String, ViewOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            viewOscillator = hashMap4.get("translationY");
        }
        ViewOscillator viewOscillator3 = viewOscillator;
        int i3 = 0;
        while (i3 < i2) {
            float f3 = ((float) i3) * f2;
            float f4 = this.mStaggerScale;
            float f5 = 0.0f;
            if (f4 != f) {
                float f6 = this.mStaggerOffset;
                if (f3 < f6) {
                    f3 = 0.0f;
                }
                if (f3 > f6 && ((double) f3) < 1.0d) {
                    f3 = Math.min((f3 - f6) * f4, f);
                }
            }
            float f7 = f3;
            double d = (double) f7;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f8 = Float.NaN;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                if (next.mKeyFrameEasing != null) {
                    if (next.time < f7) {
                        easing = next.mKeyFrameEasing;
                        f5 = next.time;
                    } else if (Float.isNaN(f8)) {
                        f8 = next.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f8)) {
                    f8 = 1.0f;
                }
                float f9 = f8 - f5;
                d = (double) ((((float) easing.get((double) ((f7 - f5) / f9))) * f9) + f5);
            }
            double d2 = d;
            this.mSpline[0].getPos(d2, this.mInterpolateData);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d2, dArr);
                }
            }
            int i4 = i3 * 2;
            float f10 = f7;
            int i5 = i3;
            this.mStartMotionPath.getCenter(d2, this.mInterpolateVariables, this.mInterpolateData, fArr, i4);
            if (viewOscillator2 != null) {
                fArr[i4] = fArr[i4] + viewOscillator2.get(f10);
            } else if (splineSet != null) {
                fArr[i4] = fArr[i4] + splineSet.get(f10);
            }
            if (viewOscillator3 != null) {
                int i6 = i4 + 1;
                fArr[i6] = fArr[i6] + viewOscillator3.get(f10);
            } else if (splineSet2 != null) {
                int i7 = i4 + 1;
                fArr[i7] = fArr[i7] + splineSet2.get(f10);
            }
            i3 = i5 + 1;
            f = 1.0f;
        }
    }

    /* access modifiers changed from: package-private */
    public double[] getPos(double d) {
        this.mSpline[0].getPos(d, this.mInterpolateData);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                curveFit.getPos(d, dArr);
            }
        }
        return this.mInterpolateData;
    }

    /* access modifiers changed from: package-private */
    public void buildBounds(float[] fArr, int i) {
        int i2 = i;
        float f = 1.0f / ((float) (i2 - 1));
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            SplineSet splineSet = hashMap.get("translationX");
        }
        HashMap<String, ViewSpline> hashMap2 = this.mAttributesMap;
        if (hashMap2 != null) {
            SplineSet splineSet2 = hashMap2.get("translationY");
        }
        HashMap<String, ViewOscillator> hashMap3 = this.mCycleMap;
        if (hashMap3 != null) {
            ViewOscillator viewOscillator = hashMap3.get("translationX");
        }
        HashMap<String, ViewOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            ViewOscillator viewOscillator2 = hashMap4.get("translationY");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            float f2 = ((float) i3) * f;
            float f3 = this.mStaggerScale;
            float f4 = 0.0f;
            if (f3 != 1.0f) {
                float f5 = this.mStaggerOffset;
                if (f2 < f5) {
                    f2 = 0.0f;
                }
                if (f2 > f5 && ((double) f2) < 1.0d) {
                    f2 = Math.min((f2 - f5) * f3, 1.0f);
                }
            }
            double d = (double) f2;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f6 = Float.NaN;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                if (next.mKeyFrameEasing != null) {
                    if (next.time < f2) {
                        easing = next.mKeyFrameEasing;
                        f4 = next.time;
                    } else if (Float.isNaN(f6)) {
                        f6 = next.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f6)) {
                    f6 = 1.0f;
                }
                float f7 = f6 - f4;
                d = (double) ((((float) easing.get((double) ((f2 - f4) / f7))) * f7) + f4);
            }
            this.mSpline[0].getPos(d, this.mInterpolateData);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d, dArr);
                }
            }
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, fArr, i3 * 2);
        }
    }

    private float getPreCycleDistance() {
        float[] fArr = new float[2];
        float f = 1.0f / ((float) 99);
        double d = 0.0d;
        double d2 = 0.0d;
        int i = 0;
        float f2 = 0.0f;
        while (i < 100) {
            float f3 = ((float) i) * f;
            double d3 = (double) f3;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f4 = Float.NaN;
            float f5 = 0.0f;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                if (next.mKeyFrameEasing != null) {
                    if (next.time < f3) {
                        easing = next.mKeyFrameEasing;
                        f5 = next.time;
                    } else if (Float.isNaN(f4)) {
                        f4 = next.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f4)) {
                    f4 = 1.0f;
                }
                float f6 = f4 - f5;
                d3 = (double) ((((float) easing.get((double) ((f3 - f5) / f6))) * f6) + f5);
            }
            this.mSpline[0].getPos(d3, this.mInterpolateData);
            float f7 = f2;
            int i2 = i;
            this.mStartMotionPath.getCenter(d3, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
            f2 = i2 > 0 ? (float) (((double) f7) + Math.hypot(d2 - ((double) fArr[1]), d - ((double) fArr[0]))) : f7;
            d = (double) fArr[0];
            i = i2 + 1;
            d2 = (double) fArr[1];
        }
        return f2;
    }

    /* access modifiers changed from: package-private */
    public KeyPositionBase getPositionKeyframe(int i, int i2, float f, float f2) {
        RectF rectF = new RectF();
        rectF.left = this.mStartMotionPath.x;
        rectF.top = this.mStartMotionPath.y;
        rectF.right = rectF.left + this.mStartMotionPath.width;
        rectF.bottom = rectF.top + this.mStartMotionPath.height;
        RectF rectF2 = new RectF();
        rectF2.left = this.mEndMotionPath.x;
        rectF2.top = this.mEndMotionPath.y;
        rectF2.right = rectF2.left + this.mEndMotionPath.width;
        rectF2.bottom = rectF2.top + this.mEndMotionPath.height;
        Iterator<Key> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            Key next = it.next();
            if (next instanceof KeyPositionBase) {
                KeyPositionBase keyPositionBase = (KeyPositionBase) next;
                if (keyPositionBase.intersects(i, i2, rectF, rectF2, f, f2)) {
                    return keyPositionBase;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int buildKeyFrames(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.mSpline[0].getTimePoints();
        if (iArr != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().mMode;
                i++;
            }
        }
        int i2 = 0;
        for (int i3 = 0; i3 < timePoints.length; i3++) {
            this.mSpline[0].getPos(timePoints[i3], this.mInterpolateData);
            this.mStartMotionPath.getCenter(timePoints[i3], this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    /* access modifiers changed from: package-private */
    public int buildKeyBounds(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.mSpline[0].getTimePoints();
        if (iArr != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().mMode;
                i++;
            }
        }
        int i2 = 0;
        for (double pos : timePoints) {
            this.mSpline[0].getPos(pos, this.mInterpolateData);
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    /* access modifiers changed from: package-private */
    public int getAttributeValues(String str, float[] fArr, int i) {
        SplineSet splineSet = this.mAttributesMap.get(str);
        if (splineSet == null) {
            return -1;
        }
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = splineSet.get((float) (i2 / (fArr.length - 1)));
        }
        return fArr.length;
    }

    /* access modifiers changed from: package-private */
    public void buildRect(float f, float[] fArr, int i) {
        this.mSpline[0].getPos((double) getAdjustedPosition(f, (float[]) null), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i);
    }

    /* access modifiers changed from: package-private */
    public void buildRectangles(float[] fArr, int i) {
        float f = 1.0f / ((float) (i - 1));
        for (int i2 = 0; i2 < i; i2++) {
            this.mSpline[0].getPos((double) getAdjustedPosition(((float) i2) * f, (float[]) null), this.mInterpolateData);
            this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i2 * 8);
        }
    }

    /* access modifiers changed from: package-private */
    public float getKeyFrameParameter(int i, float f, float f2) {
        float f3 = this.mEndMotionPath.x - this.mStartMotionPath.x;
        float f4 = this.mEndMotionPath.y - this.mStartMotionPath.y;
        float f5 = this.mStartMotionPath.x + (this.mStartMotionPath.width / 2.0f);
        float f6 = this.mStartMotionPath.y + (this.mStartMotionPath.height / 2.0f);
        float hypot = (float) Math.hypot((double) f3, (double) f4);
        if (((double) hypot) < 1.0E-7d) {
            return Float.NaN;
        }
        float f7 = f - f5;
        float f8 = f2 - f6;
        if (((float) Math.hypot((double) f7, (double) f8)) == 0.0f) {
            return 0.0f;
        }
        float f9 = (f7 * f3) + (f8 * f4);
        if (i == 0) {
            return f9 / hypot;
        }
        if (i == 1) {
            return (float) Math.sqrt((double) ((hypot * hypot) - (f9 * f9)));
        }
        if (i == 2) {
            return f7 / f3;
        }
        if (i == 3) {
            return f8 / f3;
        }
        if (i == 4) {
            return f7 / f4;
        }
        if (i != 5) {
            return 0.0f;
        }
        return f8 / f4;
    }

    private void insertKey(MotionPaths motionPaths) {
        int binarySearch = Collections.binarySearch(this.mMotionPaths, motionPaths);
        if (binarySearch == 0) {
            Log.e(TAG, " KeyPath position \"" + motionPaths.position + "\" outside of range");
        }
        this.mMotionPaths.add((-binarySearch) - 1, motionPaths);
    }

    /* access modifiers changed from: package-private */
    public void addKeys(ArrayList<Key> arrayList) {
        this.mKeyList.addAll(arrayList);
    }

    public void addKey(Key key) {
        this.mKeyList.add(key);
    }

    public void setPathMotionArc(int i) {
        this.mPathMotionArc = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0155, code lost:
        r11 = (java.lang.Integer) r4.get(r7);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setup(int r17, int r18, float r19, long r20) {
        /*
            r16 = this;
            r0 = r16
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            int r5 = r0.mPathMotionArc
            int r6 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r5 == r6) goto L_0x0027
            androidx.constraintlayout.motion.widget.MotionPaths r5 = r0.mStartMotionPath
            int r6 = r0.mPathMotionArc
            r5.mPathMotionArc = r6
        L_0x0027:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r5 = r0.mStartPoint
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r6 = r0.mEndPoint
            r5.different(r6, r2)
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r5 = r0.mKeyList
            if (r5 == 0) goto L_0x008e
            java.util.Iterator r5 = r5.iterator()
            r7 = 0
        L_0x0037:
            boolean r8 = r5.hasNext()
            if (r8 == 0) goto L_0x008f
            java.lang.Object r8 = r5.next()
            androidx.constraintlayout.motion.widget.Key r8 = (androidx.constraintlayout.motion.widget.Key) r8
            boolean r9 = r8 instanceof androidx.constraintlayout.motion.widget.KeyPosition
            if (r9 == 0) goto L_0x0066
            androidx.constraintlayout.motion.widget.KeyPosition r8 = (androidx.constraintlayout.motion.widget.KeyPosition) r8
            androidx.constraintlayout.motion.widget.MotionPaths r9 = new androidx.constraintlayout.motion.widget.MotionPaths
            androidx.constraintlayout.motion.widget.MotionPaths r14 = r0.mStartMotionPath
            androidx.constraintlayout.motion.widget.MotionPaths r15 = r0.mEndMotionPath
            r10 = r9
            r11 = r17
            r12 = r18
            r13 = r8
            r10.<init>(r11, r12, r13, r14, r15)
            r0.insertKey(r9)
            int r9 = r8.mCurveFit
            int r10 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r9 == r10) goto L_0x0037
            int r8 = r8.mCurveFit
            r0.mCurveFitType = r8
            goto L_0x0037
        L_0x0066:
            boolean r9 = r8 instanceof androidx.constraintlayout.motion.widget.KeyCycle
            if (r9 == 0) goto L_0x006e
            r8.getAttributeNames(r3)
            goto L_0x0037
        L_0x006e:
            boolean r9 = r8 instanceof androidx.constraintlayout.motion.widget.KeyTimeCycle
            if (r9 == 0) goto L_0x0076
            r8.getAttributeNames(r1)
            goto L_0x0037
        L_0x0076:
            boolean r9 = r8 instanceof androidx.constraintlayout.motion.widget.KeyTrigger
            if (r9 == 0) goto L_0x0087
            if (r7 != 0) goto L_0x0081
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
        L_0x0081:
            androidx.constraintlayout.motion.widget.KeyTrigger r8 = (androidx.constraintlayout.motion.widget.KeyTrigger) r8
            r7.add(r8)
            goto L_0x0037
        L_0x0087:
            r8.setInterpolation(r4)
            r8.getAttributeNames(r2)
            goto L_0x0037
        L_0x008e:
            r7 = 0
        L_0x008f:
            r5 = 0
            if (r7 == 0) goto L_0x009c
            androidx.constraintlayout.motion.widget.KeyTrigger[] r8 = new androidx.constraintlayout.motion.widget.KeyTrigger[r5]
            java.lang.Object[] r7 = r7.toArray(r8)
            androidx.constraintlayout.motion.widget.KeyTrigger[] r7 = (androidx.constraintlayout.motion.widget.KeyTrigger[]) r7
            r0.mKeyTriggers = r7
        L_0x009c:
            boolean r7 = r2.isEmpty()
            java.lang.String r8 = ","
            java.lang.String r9 = "CUSTOM,"
            r10 = 1
            if (r7 != 0) goto L_0x0171
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            r0.mAttributesMap = r7
            java.util.Iterator r7 = r2.iterator()
        L_0x00b2:
            boolean r11 = r7.hasNext()
            if (r11 == 0) goto L_0x010b
            java.lang.Object r11 = r7.next()
            java.lang.String r11 = (java.lang.String) r11
            boolean r12 = r11.startsWith(r9)
            if (r12 == 0) goto L_0x00fb
            android.util.SparseArray r12 = new android.util.SparseArray
            r12.<init>()
            java.lang.String[] r13 = r11.split(r8)
            r13 = r13[r10]
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r14 = r0.mKeyList
            java.util.Iterator r14 = r14.iterator()
        L_0x00d5:
            boolean r15 = r14.hasNext()
            if (r15 == 0) goto L_0x00f6
            java.lang.Object r15 = r14.next()
            androidx.constraintlayout.motion.widget.Key r15 = (androidx.constraintlayout.motion.widget.Key) r15
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r6 = r15.mCustomConstraints
            if (r6 != 0) goto L_0x00e6
            goto L_0x00d5
        L_0x00e6:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r6 = r15.mCustomConstraints
            java.lang.Object r6 = r6.get(r13)
            androidx.constraintlayout.widget.ConstraintAttribute r6 = (androidx.constraintlayout.widget.ConstraintAttribute) r6
            if (r6 == 0) goto L_0x00d5
            int r15 = r15.mFramePosition
            r12.append(r15, r6)
            goto L_0x00d5
        L_0x00f6:
            androidx.constraintlayout.motion.utils.ViewSpline r6 = androidx.constraintlayout.motion.utils.ViewSpline.makeCustomSpline(r11, r12)
            goto L_0x00ff
        L_0x00fb:
            androidx.constraintlayout.motion.utils.ViewSpline r6 = androidx.constraintlayout.motion.utils.ViewSpline.makeSpline(r11)
        L_0x00ff:
            if (r6 != 0) goto L_0x0102
            goto L_0x00b2
        L_0x0102:
            r6.setType(r11)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r12 = r0.mAttributesMap
            r12.put(r11, r6)
            goto L_0x00b2
        L_0x010b:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r6 = r0.mKeyList
            if (r6 == 0) goto L_0x0129
            java.util.Iterator r6 = r6.iterator()
        L_0x0113:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0129
            java.lang.Object r7 = r6.next()
            androidx.constraintlayout.motion.widget.Key r7 = (androidx.constraintlayout.motion.widget.Key) r7
            boolean r11 = r7 instanceof androidx.constraintlayout.motion.widget.KeyAttributes
            if (r11 == 0) goto L_0x0113
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r11 = r0.mAttributesMap
            r7.addValues(r11)
            goto L_0x0113
        L_0x0129:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r6 = r0.mStartPoint
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r7 = r0.mAttributesMap
            r6.addValues(r7, r5)
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r6 = r0.mEndPoint
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r7 = r0.mAttributesMap
            r11 = 100
            r6.addValues(r7, r11)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r6 = r0.mAttributesMap
            java.util.Set r6 = r6.keySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x0143:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0171
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            boolean r11 = r4.containsKey(r7)
            if (r11 == 0) goto L_0x0162
            java.lang.Object r11 = r4.get(r7)
            java.lang.Integer r11 = (java.lang.Integer) r11
            if (r11 == 0) goto L_0x0162
            int r11 = r11.intValue()
            goto L_0x0163
        L_0x0162:
            r11 = r5
        L_0x0163:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r12 = r0.mAttributesMap
            java.lang.Object r7 = r12.get(r7)
            androidx.constraintlayout.core.motion.utils.SplineSet r7 = (androidx.constraintlayout.core.motion.utils.SplineSet) r7
            if (r7 == 0) goto L_0x0143
            r7.setup(r11)
            goto L_0x0143
        L_0x0171:
            boolean r6 = r1.isEmpty()
            if (r6 != 0) goto L_0x0240
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r6 = r0.mTimeCycleAttributesMap
            if (r6 != 0) goto L_0x0182
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            r0.mTimeCycleAttributesMap = r6
        L_0x0182:
            java.util.Iterator r1 = r1.iterator()
        L_0x0186:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x01ec
            java.lang.Object r6 = r1.next()
            java.lang.String r6 = (java.lang.String) r6
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r7 = r0.mTimeCycleAttributesMap
            boolean r7 = r7.containsKey(r6)
            if (r7 == 0) goto L_0x019b
            goto L_0x0186
        L_0x019b:
            boolean r7 = r6.startsWith(r9)
            if (r7 == 0) goto L_0x01da
            android.util.SparseArray r7 = new android.util.SparseArray
            r7.<init>()
            java.lang.String[] r11 = r6.split(r8)
            r11 = r11[r10]
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r12 = r0.mKeyList
            java.util.Iterator r12 = r12.iterator()
        L_0x01b2:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L_0x01d3
            java.lang.Object r13 = r12.next()
            androidx.constraintlayout.motion.widget.Key r13 = (androidx.constraintlayout.motion.widget.Key) r13
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r14 = r13.mCustomConstraints
            if (r14 != 0) goto L_0x01c3
            goto L_0x01b2
        L_0x01c3:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r14 = r13.mCustomConstraints
            java.lang.Object r14 = r14.get(r11)
            androidx.constraintlayout.widget.ConstraintAttribute r14 = (androidx.constraintlayout.widget.ConstraintAttribute) r14
            if (r14 == 0) goto L_0x01b2
            int r13 = r13.mFramePosition
            r7.append(r13, r14)
            goto L_0x01b2
        L_0x01d3:
            androidx.constraintlayout.motion.utils.ViewTimeCycle r7 = androidx.constraintlayout.motion.utils.ViewTimeCycle.makeCustomSpline(r6, r7)
            r11 = r20
            goto L_0x01e0
        L_0x01da:
            r11 = r20
            androidx.constraintlayout.motion.utils.ViewTimeCycle r7 = androidx.constraintlayout.motion.utils.ViewTimeCycle.makeSpline(r6, r11)
        L_0x01e0:
            if (r7 != 0) goto L_0x01e3
            goto L_0x0186
        L_0x01e3:
            r7.setType(r6)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r13 = r0.mTimeCycleAttributesMap
            r13.put(r6, r7)
            goto L_0x0186
        L_0x01ec:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r1 = r0.mKeyList
            if (r1 == 0) goto L_0x020c
            java.util.Iterator r1 = r1.iterator()
        L_0x01f4:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x020c
            java.lang.Object r6 = r1.next()
            androidx.constraintlayout.motion.widget.Key r6 = (androidx.constraintlayout.motion.widget.Key) r6
            boolean r7 = r6 instanceof androidx.constraintlayout.motion.widget.KeyTimeCycle
            if (r7 == 0) goto L_0x01f4
            androidx.constraintlayout.motion.widget.KeyTimeCycle r6 = (androidx.constraintlayout.motion.widget.KeyTimeCycle) r6
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r7 = r0.mTimeCycleAttributesMap
            r6.addTimeValues(r7)
            goto L_0x01f4
        L_0x020c:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r1 = r0.mTimeCycleAttributesMap
            java.util.Set r1 = r1.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0216:
            boolean r6 = r1.hasNext()
            if (r6 == 0) goto L_0x0240
            java.lang.Object r6 = r1.next()
            java.lang.String r6 = (java.lang.String) r6
            boolean r7 = r4.containsKey(r6)
            if (r7 == 0) goto L_0x0233
            java.lang.Object r7 = r4.get(r6)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            goto L_0x0234
        L_0x0233:
            r7 = r5
        L_0x0234:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r8 = r0.mTimeCycleAttributesMap
            java.lang.Object r6 = r8.get(r6)
            androidx.constraintlayout.motion.utils.ViewTimeCycle r6 = (androidx.constraintlayout.motion.utils.ViewTimeCycle) r6
            r6.setup(r7)
            goto L_0x0216
        L_0x0240:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionPaths> r1 = r0.mMotionPaths
            int r1 = r1.size()
            int r4 = r1 + 2
            androidx.constraintlayout.motion.widget.MotionPaths[] r6 = new androidx.constraintlayout.motion.widget.MotionPaths[r4]
            androidx.constraintlayout.motion.widget.MotionPaths r7 = r0.mStartMotionPath
            r6[r5] = r7
            int r1 = r1 + r10
            androidx.constraintlayout.motion.widget.MotionPaths r7 = r0.mEndMotionPath
            r6[r1] = r7
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionPaths> r1 = r0.mMotionPaths
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x0262
            int r1 = r0.mCurveFitType
            r7 = -1
            if (r1 != r7) goto L_0x0262
            r0.mCurveFitType = r5
        L_0x0262:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.MotionPaths> r1 = r0.mMotionPaths
            java.util.Iterator r1 = r1.iterator()
            r7 = r10
        L_0x0269:
            boolean r8 = r1.hasNext()
            if (r8 == 0) goto L_0x027b
            java.lang.Object r8 = r1.next()
            androidx.constraintlayout.motion.widget.MotionPaths r8 = (androidx.constraintlayout.motion.widget.MotionPaths) r8
            int r11 = r7 + 1
            r6[r7] = r8
            r7 = r11
            goto L_0x0269
        L_0x027b:
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            androidx.constraintlayout.motion.widget.MotionPaths r7 = r0.mEndMotionPath
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r7 = r7.attributes
            java.util.Set r7 = r7.keySet()
            java.util.Iterator r7 = r7.iterator()
        L_0x028c:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x02b9
            java.lang.Object r8 = r7.next()
            java.lang.String r8 = (java.lang.String) r8
            androidx.constraintlayout.motion.widget.MotionPaths r11 = r0.mStartMotionPath
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r11 = r11.attributes
            boolean r11 = r11.containsKey(r8)
            if (r11 == 0) goto L_0x028c
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>(r9)
            java.lang.StringBuilder r11 = r11.append(r8)
            java.lang.String r11 = r11.toString()
            boolean r11 = r2.contains(r11)
            if (r11 != 0) goto L_0x028c
            r1.add(r8)
            goto L_0x028c
        L_0x02b9:
            java.lang.String[] r2 = new java.lang.String[r5]
            java.lang.Object[] r1 = r1.toArray(r2)
            java.lang.String[] r1 = (java.lang.String[]) r1
            r0.mAttributeNames = r1
            int r1 = r1.length
            int[] r1 = new int[r1]
            r0.mAttributeInterpolatorCount = r1
            r1 = r5
        L_0x02c9:
            java.lang.String[] r2 = r0.mAttributeNames
            int r7 = r2.length
            if (r1 >= r7) goto L_0x02ff
            r2 = r2[r1]
            int[] r7 = r0.mAttributeInterpolatorCount
            r7[r1] = r5
            r7 = r5
        L_0x02d5:
            if (r7 >= r4) goto L_0x02fc
            r8 = r6[r7]
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r8 = r8.attributes
            boolean r8 = r8.containsKey(r2)
            if (r8 == 0) goto L_0x02f9
            r8 = r6[r7]
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r8 = r8.attributes
            java.lang.Object r8 = r8.get(r2)
            androidx.constraintlayout.widget.ConstraintAttribute r8 = (androidx.constraintlayout.widget.ConstraintAttribute) r8
            if (r8 == 0) goto L_0x02f9
            int[] r2 = r0.mAttributeInterpolatorCount
            r7 = r2[r1]
            int r8 = r8.numberOfInterpolatedValues()
            int r7 = r7 + r8
            r2[r1] = r7
            goto L_0x02fc
        L_0x02f9:
            int r7 = r7 + 1
            goto L_0x02d5
        L_0x02fc:
            int r1 = r1 + 1
            goto L_0x02c9
        L_0x02ff:
            r1 = r6[r5]
            int r1 = r1.mPathMotionArc
            int r2 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r1 == r2) goto L_0x0309
            r1 = r10
            goto L_0x030a
        L_0x0309:
            r1 = r5
        L_0x030a:
            java.lang.String[] r2 = r0.mAttributeNames
            int r2 = r2.length
            r7 = 18
            int r7 = r7 + r2
            boolean[] r2 = new boolean[r7]
            r8 = r10
        L_0x0313:
            if (r8 >= r4) goto L_0x0323
            r9 = r6[r8]
            int r11 = r8 + -1
            r11 = r6[r11]
            java.lang.String[] r12 = r0.mAttributeNames
            r9.different(r11, r2, r12, r1)
            int r8 = r8 + 1
            goto L_0x0313
        L_0x0323:
            r8 = r5
            r1 = r10
        L_0x0325:
            if (r1 >= r7) goto L_0x0330
            boolean r9 = r2[r1]
            if (r9 == 0) goto L_0x032d
            int r8 = r8 + 1
        L_0x032d:
            int r1 = r1 + 1
            goto L_0x0325
        L_0x0330:
            int[] r1 = new int[r8]
            r0.mInterpolateVariables = r1
            r1 = 2
            int r8 = java.lang.Math.max(r1, r8)
            double[] r9 = new double[r8]
            r0.mInterpolateData = r9
            double[] r8 = new double[r8]
            r0.mInterpolateVelocity = r8
            r9 = r5
            r8 = r10
        L_0x0343:
            if (r8 >= r7) goto L_0x0353
            boolean r11 = r2[r8]
            if (r11 == 0) goto L_0x0350
            int[] r11 = r0.mInterpolateVariables
            int r12 = r9 + 1
            r11[r9] = r8
            r9 = r12
        L_0x0350:
            int r8 = r8 + 1
            goto L_0x0343
        L_0x0353:
            int[] r2 = r0.mInterpolateVariables
            int r2 = r2.length
            int[] r7 = new int[r1]
            r7[r10] = r2
            r7[r5] = r4
            java.lang.Class r2 = java.lang.Double.TYPE
            java.lang.Object r2 = java.lang.reflect.Array.newInstance(r2, r7)
            double[][] r2 = (double[][]) r2
            double[] r7 = new double[r4]
            r8 = r5
        L_0x0367:
            if (r8 >= r4) goto L_0x037c
            r9 = r6[r8]
            r11 = r2[r8]
            int[] r12 = r0.mInterpolateVariables
            r9.fillStandard(r11, r12)
            r9 = r6[r8]
            float r9 = r9.time
            double r11 = (double) r9
            r7[r8] = r11
            int r8 = r8 + 1
            goto L_0x0367
        L_0x037c:
            r8 = r5
        L_0x037d:
            int[] r9 = r0.mInterpolateVariables
            int r11 = r9.length
            if (r8 >= r11) goto L_0x03c2
            r9 = r9[r8]
            java.lang.String[] r11 = androidx.constraintlayout.motion.widget.MotionPaths.names
            int r11 = r11.length
            if (r9 >= r11) goto L_0x03bf
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String[] r11 = androidx.constraintlayout.motion.widget.MotionPaths.names
            int[] r12 = r0.mInterpolateVariables
            r12 = r12[r8]
            r11 = r11[r12]
            java.lang.StringBuilder r9 = r9.append(r11)
            java.lang.String r11 = " ["
            java.lang.StringBuilder r9 = r9.append(r11)
            java.lang.String r9 = r9.toString()
            r11 = r5
        L_0x03a5:
            if (r11 >= r4) goto L_0x03bf
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.StringBuilder r9 = r12.append(r9)
            r12 = r2[r11]
            r13 = r12[r8]
            java.lang.StringBuilder r9 = r9.append(r13)
            java.lang.String r9 = r9.toString()
            int r11 = r11 + 1
            goto L_0x03a5
        L_0x03bf:
            int r8 = r8 + 1
            goto L_0x037d
        L_0x03c2:
            java.lang.String[] r8 = r0.mAttributeNames
            int r8 = r8.length
            int r8 = r8 + r10
            androidx.constraintlayout.core.motion.utils.CurveFit[] r8 = new androidx.constraintlayout.core.motion.utils.CurveFit[r8]
            r0.mSpline = r8
            r8 = r5
        L_0x03cb:
            java.lang.String[] r9 = r0.mAttributeNames
            int r11 = r9.length
            if (r8 >= r11) goto L_0x042f
            r9 = r9[r8]
            r11 = r5
            r13 = r11
            r12 = 0
            r14 = 0
        L_0x03d6:
            if (r11 >= r4) goto L_0x0413
            r15 = r6[r11]
            boolean r15 = r15.hasCustomData(r9)
            if (r15 == 0) goto L_0x040b
            if (r14 != 0) goto L_0x03f8
            double[] r12 = new double[r4]
            r14 = r6[r11]
            int r14 = r14.getCustomDataCount(r9)
            int[] r15 = new int[r1]
            r15[r10] = r14
            r15[r5] = r4
            java.lang.Class r14 = java.lang.Double.TYPE
            java.lang.Object r14 = java.lang.reflect.Array.newInstance(r14, r15)
            double[][] r14 = (double[][]) r14
        L_0x03f8:
            r15 = r6[r11]
            float r15 = r15.time
            r18 = r2
            double r1 = (double) r15
            r12[r13] = r1
            r1 = r6[r11]
            r2 = r14[r13]
            r1.getCustomData(r9, r2, r5)
            int r13 = r13 + 1
            goto L_0x040d
        L_0x040b:
            r18 = r2
        L_0x040d:
            int r11 = r11 + 1
            r2 = r18
            r1 = 2
            goto L_0x03d6
        L_0x0413:
            r18 = r2
            double[] r1 = java.util.Arrays.copyOf(r12, r13)
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r14, r13)
            double[][] r2 = (double[][]) r2
            androidx.constraintlayout.core.motion.utils.CurveFit[] r9 = r0.mSpline
            int r8 = r8 + 1
            int r11 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r11, r1, r2)
            r9[r8] = r1
            r2 = r18
            r1 = 2
            goto L_0x03cb
        L_0x042f:
            r18 = r2
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            int r2 = r0.mCurveFitType
            r8 = r18
            androidx.constraintlayout.core.motion.utils.CurveFit r2 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r2, r7, r8)
            r1[r5] = r2
            r1 = r6[r5]
            int r1 = r1.mPathMotionArc
            int r2 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r1 == r2) goto L_0x0483
            int[] r1 = new int[r4]
            double[] r2 = new double[r4]
            r7 = 2
            int[] r8 = new int[r7]
            r8[r10] = r7
            r8[r5] = r4
            java.lang.Class r7 = java.lang.Double.TYPE
            java.lang.Object r7 = java.lang.reflect.Array.newInstance(r7, r8)
            double[][] r7 = (double[][]) r7
            r8 = r5
        L_0x0459:
            if (r8 >= r4) goto L_0x047d
            r9 = r6[r8]
            int r9 = r9.mPathMotionArc
            r1[r8] = r9
            r9 = r6[r8]
            float r9 = r9.time
            double r11 = (double) r9
            r2[r8] = r11
            r9 = r7[r8]
            r11 = r6[r8]
            float r11 = r11.x
            double r11 = (double) r11
            r9[r5] = r11
            r9 = r7[r8]
            r11 = r6[r8]
            float r11 = r11.y
            double r11 = (double) r11
            r9[r10] = r11
            int r8 = r8 + 1
            goto L_0x0459
        L_0x047d:
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = androidx.constraintlayout.core.motion.utils.CurveFit.getArc(r1, r2, r7)
            r0.mArcSpline = r1
        L_0x0483:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r0.mCycleMap = r1
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r1 = r0.mKeyList
            if (r1 == 0) goto L_0x04f8
            java.util.Iterator r1 = r3.iterator()
            r2 = 2143289344(0x7fc00000, float:NaN)
        L_0x0494:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x04c0
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            androidx.constraintlayout.motion.utils.ViewOscillator r4 = androidx.constraintlayout.motion.utils.ViewOscillator.makeSpline(r3)
            if (r4 != 0) goto L_0x04a7
            goto L_0x0494
        L_0x04a7:
            boolean r5 = r4.variesByPath()
            if (r5 == 0) goto L_0x04b7
            boolean r5 = java.lang.Float.isNaN(r2)
            if (r5 == 0) goto L_0x04b7
            float r2 = r16.getPreCycleDistance()
        L_0x04b7:
            r4.setType(r3)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r5 = r0.mCycleMap
            r5.put(r3, r4)
            goto L_0x0494
        L_0x04c0:
            java.util.ArrayList<androidx.constraintlayout.motion.widget.Key> r1 = r0.mKeyList
            java.util.Iterator r1 = r1.iterator()
        L_0x04c6:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x04de
            java.lang.Object r3 = r1.next()
            androidx.constraintlayout.motion.widget.Key r3 = (androidx.constraintlayout.motion.widget.Key) r3
            boolean r4 = r3 instanceof androidx.constraintlayout.motion.widget.KeyCycle
            if (r4 == 0) goto L_0x04c6
            androidx.constraintlayout.motion.widget.KeyCycle r3 = (androidx.constraintlayout.motion.widget.KeyCycle) r3
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r4 = r0.mCycleMap
            r3.addCycleValues(r4)
            goto L_0x04c6
        L_0x04de:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r1 = r0.mCycleMap
            java.util.Collection r1 = r1.values()
            java.util.Iterator r1 = r1.iterator()
        L_0x04e8:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x04f8
            java.lang.Object r3 = r1.next()
            androidx.constraintlayout.motion.utils.ViewOscillator r3 = (androidx.constraintlayout.motion.utils.ViewOscillator) r3
            r3.setup(r2)
            goto L_0x04e8
        L_0x04f8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionController.setup(int, int, float, long):void");
    }

    public String toString() {
        return " start: x: " + this.mStartMotionPath.x + " y: " + this.mStartMotionPath.y + " end: x: " + this.mEndMotionPath.x + " y: " + this.mEndMotionPath.y;
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((float) ((int) this.mView.getX()), (float) ((int) this.mView.getY()), (float) this.mView.getWidth(), (float) this.mView.getHeight());
    }

    public void setView(View view) {
        this.mView = view;
        this.mId = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            this.mConstraintTag = ((ConstraintLayout.LayoutParams) layoutParams).getConstraintTag();
        }
    }

    public View getView() {
        return this.mView;
    }

    /* access modifiers changed from: package-private */
    public void setStartCurrentState(View view) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mStartMotionPath.setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        this.mStartPoint.setState(view);
    }

    public void setStartState(ViewState viewState, View view, int i, int i2, int i3) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        Rect rect = new Rect();
        if (i == 1) {
            int i4 = viewState.left + viewState.right;
            rect.left = ((viewState.top + viewState.bottom) - viewState.width()) / 2;
            rect.top = i2 - ((i4 + viewState.height()) / 2);
            rect.right = rect.left + viewState.width();
            rect.bottom = rect.top + viewState.height();
        } else if (i == 2) {
            int i5 = viewState.left + viewState.right;
            rect.left = i3 - (((viewState.top + viewState.bottom) + viewState.width()) / 2);
            rect.top = (i5 - viewState.height()) / 2;
            rect.right = rect.left + viewState.width();
            rect.bottom = rect.top + viewState.height();
        }
        this.mStartMotionPath.setBounds((float) rect.left, (float) rect.top, (float) rect.width(), (float) rect.height());
        this.mStartPoint.setState(rect, view, i, viewState.rotation);
    }

    /* access modifiers changed from: package-private */
    public void rotate(Rect rect, Rect rect2, int i, int i2, int i3) {
        if (i == 1) {
            int i4 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i3 - ((i4 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 2) {
            int i5 = rect.left + rect.right;
            rect2.left = i2 - (((rect.top + rect.bottom) + rect.width()) / 2);
            rect2.top = (i5 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 3) {
            int i6 = rect.left + rect.right;
            int i7 = rect.top;
            int i8 = rect.bottom;
            rect2.left = ((rect.height() / 2) + rect.top) - (i6 / 2);
            rect2.top = i3 - ((i6 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 4) {
            int i9 = rect.left + rect.right;
            rect2.left = i2 - (((rect.bottom + rect.top) + rect.width()) / 2);
            rect2.top = (i9 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        }
    }

    /* access modifiers changed from: package-private */
    public void setStartState(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        int i3 = constraintSet.mRotate;
        if (i3 != 0) {
            rotate(rect, this.mTempRect, i3, i, i2);
        }
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        readView(this.mStartMotionPath);
        this.mStartMotionPath.setBounds((float) rect.left, (float) rect.top, (float) rect.width(), (float) rect.height());
        ConstraintSet.Constraint parameters = constraintSet.getParameters(this.mId);
        this.mStartMotionPath.applyParameters(parameters);
        this.mMotionStagger = parameters.motion.mMotionStagger;
        this.mStartPoint.setState(rect, constraintSet, i3, this.mId);
        this.mTransformPivotTarget = parameters.transform.transformPivotTarget;
        this.mQuantizeMotionSteps = parameters.motion.mQuantizeMotionSteps;
        this.mQuantizeMotionPhase = parameters.motion.mQuantizeMotionPhase;
        this.mQuantizeMotionInterpolator = getInterpolator(this.mView.getContext(), parameters.motion.mQuantizeInterpolatorType, parameters.motion.mQuantizeInterpolatorString, parameters.motion.mQuantizeInterpolatorID);
    }

    private static Interpolator getInterpolator(Context context, int i, String str, int i2) {
        if (i == -2) {
            return AnimationUtils.loadInterpolator(context, i2);
        }
        if (i == -1) {
            final Easing interpolator = Easing.getInterpolator(str);
            return new Interpolator() {
                public float getInterpolation(float f) {
                    return (float) Easing.this.get((double) f);
                }
            };
        } else if (i == 0) {
            return new AccelerateDecelerateInterpolator();
        } else {
            if (i == 1) {
                return new AccelerateInterpolator();
            }
            if (i == 2) {
                return new DecelerateInterpolator();
            }
            if (i == 4) {
                return new BounceInterpolator();
            }
            if (i != 5) {
                return null;
            }
            return new OvershootInterpolator();
        }
    }

    /* access modifiers changed from: package-private */
    public void setEndState(Rect rect, ConstraintSet constraintSet, int i, int i2) {
        int i3 = constraintSet.mRotate;
        if (i3 != 0) {
            rotate(rect, this.mTempRect, i3, i, i2);
            rect = this.mTempRect;
        }
        this.mEndMotionPath.time = 1.0f;
        this.mEndMotionPath.position = 1.0f;
        readView(this.mEndMotionPath);
        this.mEndMotionPath.setBounds((float) rect.left, (float) rect.top, (float) rect.width(), (float) rect.height());
        this.mEndMotionPath.applyParameters(constraintSet.getParameters(this.mId));
        this.mEndPoint.setState(rect, constraintSet, i3, this.mId);
    }

    /* access modifiers changed from: package-private */
    public void setBothStates(View view) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mNoMovement = true;
        this.mStartMotionPath.setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        this.mEndMotionPath.setBounds(view.getX(), view.getY(), (float) view.getWidth(), (float) view.getHeight());
        this.mStartPoint.setState(view);
        this.mEndPoint.setState(view);
    }

    private float getAdjustedPosition(float f, float[] fArr) {
        float f2 = 0.0f;
        float f3 = 1.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f4 = this.mStaggerScale;
            if (((double) f4) != 1.0d) {
                float f5 = this.mStaggerOffset;
                if (f < f5) {
                    f = 0.0f;
                }
                if (f > f5 && ((double) f) < 1.0d) {
                    f = Math.min((f - f5) * f4, 1.0f);
                }
            }
        }
        Easing easing = this.mStartMotionPath.mKeyFrameEasing;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        float f6 = Float.NaN;
        while (it.hasNext()) {
            MotionPaths next = it.next();
            if (next.mKeyFrameEasing != null) {
                if (next.time < f) {
                    easing = next.mKeyFrameEasing;
                    f2 = next.time;
                } else if (Float.isNaN(f6)) {
                    f6 = next.time;
                }
            }
        }
        if (easing != null) {
            if (!Float.isNaN(f6)) {
                f3 = f6;
            }
            float f7 = f3 - f2;
            double d = (double) ((f - f2) / f7);
            f = (((float) easing.get(d)) * f7) + f2;
            if (fArr != null) {
                fArr[0] = (float) easing.getDiff(d);
            }
        }
        return f;
    }

    /* access modifiers changed from: package-private */
    public void endTrigger(boolean z) {
        if ("button".equals(Debug.getName(this.mView)) && this.mKeyTriggers != null) {
            int i = 0;
            while (true) {
                KeyTrigger[] keyTriggerArr = this.mKeyTriggers;
                if (i < keyTriggerArr.length) {
                    keyTriggerArr[i].conditionallyFire(z ? -100.0f : 100.0f, this.mView);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v66, resolved type: androidx.constraintlayout.motion.utils.ViewTimeCycle} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: androidx.constraintlayout.motion.utils.ViewTimeCycle$PathRotate} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean interpolate(android.view.View r21, float r22, long r23, androidx.constraintlayout.core.motion.utils.KeyCache r25) {
        /*
            r20 = this;
            r0 = r20
            r11 = r21
            r1 = 0
            r2 = r22
            float r2 = r0.getAdjustedPosition(r2, r1)
            int r3 = r0.mQuantizeMotionSteps
            int r4 = androidx.constraintlayout.motion.widget.Key.UNSET
            r13 = 1065353216(0x3f800000, float:1.0)
            if (r3 == r4) goto L_0x0044
            int r3 = r0.mQuantizeMotionSteps
            float r3 = (float) r3
            float r3 = r13 / r3
            float r4 = r2 / r3
            double r4 = (double) r4
            double r4 = java.lang.Math.floor(r4)
            float r4 = (float) r4
            float r4 = r4 * r3
            float r2 = r2 % r3
            float r2 = r2 / r3
            float r5 = r0.mQuantizeMotionPhase
            boolean r5 = java.lang.Float.isNaN(r5)
            if (r5 != 0) goto L_0x002f
            float r5 = r0.mQuantizeMotionPhase
            float r2 = r2 + r5
            float r2 = r2 % r13
        L_0x002f:
            android.view.animation.Interpolator r5 = r0.mQuantizeMotionInterpolator
            if (r5 == 0) goto L_0x0038
            float r2 = r5.getInterpolation(r2)
            goto L_0x0042
        L_0x0038:
            double r5 = (double) r2
            r7 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 <= 0) goto L_0x0041
            r2 = r13
            goto L_0x0042
        L_0x0041:
            r2 = 0
        L_0x0042:
            float r2 = r2 * r3
            float r2 = r2 + r4
        L_0x0044:
            r14 = r2
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r2 = r0.mAttributesMap
            if (r2 == 0) goto L_0x0061
            java.util.Collection r2 = r2.values()
            java.util.Iterator r2 = r2.iterator()
        L_0x0051:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0061
            java.lang.Object r3 = r2.next()
            androidx.constraintlayout.motion.utils.ViewSpline r3 = (androidx.constraintlayout.motion.utils.ViewSpline) r3
            r3.setProperty(r11, r14)
            goto L_0x0051
        L_0x0061:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewTimeCycle> r2 = r0.mTimeCycleAttributesMap
            r15 = 0
            if (r2 == 0) goto L_0x0095
            java.util.Collection r2 = r2.values()
            java.util.Iterator r7 = r2.iterator()
            r8 = r1
            r9 = r15
        L_0x0070:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0091
            java.lang.Object r1 = r7.next()
            androidx.constraintlayout.motion.utils.ViewTimeCycle r1 = (androidx.constraintlayout.motion.utils.ViewTimeCycle) r1
            boolean r2 = r1 instanceof androidx.constraintlayout.motion.utils.ViewTimeCycle.PathRotate
            if (r2 == 0) goto L_0x0084
            r8 = r1
            androidx.constraintlayout.motion.utils.ViewTimeCycle$PathRotate r8 = (androidx.constraintlayout.motion.utils.ViewTimeCycle.PathRotate) r8
            goto L_0x0070
        L_0x0084:
            r2 = r21
            r3 = r14
            r4 = r23
            r6 = r25
            boolean r1 = r1.setProperty(r2, r3, r4, r6)
            r9 = r9 | r1
            goto L_0x0070
        L_0x0091:
            r16 = r9
            r9 = r8
            goto L_0x0098
        L_0x0095:
            r9 = r1
            r16 = r15
        L_0x0098:
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            r10 = 1
            if (r1 == 0) goto L_0x01f7
            r1 = r1[r15]
            double r7 = (double) r14
            double[] r2 = r0.mInterpolateData
            r1.getPos((double) r7, (double[]) r2)
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            r1 = r1[r15]
            double[] r2 = r0.mInterpolateVelocity
            r1.getSlope((double) r7, (double[]) r2)
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = r0.mArcSpline
            if (r1 == 0) goto L_0x00c1
            double[] r2 = r0.mInterpolateData
            int r3 = r2.length
            if (r3 <= 0) goto L_0x00c1
            r1.getPos((double) r7, (double[]) r2)
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = r0.mArcSpline
            double[] r2 = r0.mInterpolateVelocity
            r1.getSlope((double) r7, (double[]) r2)
        L_0x00c1:
            boolean r1 = r0.mNoMovement
            if (r1 != 0) goto L_0x00e1
            androidx.constraintlayout.motion.widget.MotionPaths r1 = r0.mStartMotionPath
            int[] r4 = r0.mInterpolateVariables
            double[] r5 = r0.mInterpolateData
            double[] r6 = r0.mInterpolateVelocity
            r17 = 0
            boolean r3 = r0.mForceMeasure
            r2 = r14
            r18 = r3
            r3 = r21
            r12 = r7
            r7 = r17
            r8 = r18
            r1.setView(r2, r3, r4, r5, r6, r7, r8)
            r0.mForceMeasure = r15
            goto L_0x00e2
        L_0x00e1:
            r12 = r7
        L_0x00e2:
            int r1 = r0.mTransformPivotTarget
            int r2 = androidx.constraintlayout.motion.widget.Key.UNSET
            if (r1 == r2) goto L_0x0144
            android.view.View r1 = r0.mTransformPivotView
            if (r1 != 0) goto L_0x00fa
            android.view.ViewParent r1 = r21.getParent()
            android.view.View r1 = (android.view.View) r1
            int r2 = r0.mTransformPivotTarget
            android.view.View r1 = r1.findViewById(r2)
            r0.mTransformPivotView = r1
        L_0x00fa:
            android.view.View r1 = r0.mTransformPivotView
            if (r1 == 0) goto L_0x0144
            int r1 = r1.getTop()
            android.view.View r2 = r0.mTransformPivotView
            int r2 = r2.getBottom()
            int r1 = r1 + r2
            float r1 = (float) r1
            r2 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r2
            android.view.View r3 = r0.mTransformPivotView
            int r3 = r3.getLeft()
            android.view.View r4 = r0.mTransformPivotView
            int r4 = r4.getRight()
            int r3 = r3 + r4
            float r3 = (float) r3
            float r3 = r3 / r2
            int r2 = r21.getRight()
            int r4 = r21.getLeft()
            int r2 = r2 - r4
            if (r2 <= 0) goto L_0x0144
            int r2 = r21.getBottom()
            int r4 = r21.getTop()
            int r2 = r2 - r4
            if (r2 <= 0) goto L_0x0144
            int r2 = r21.getLeft()
            float r2 = (float) r2
            float r3 = r3 - r2
            int r2 = r21.getTop()
            float r2 = (float) r2
            float r1 = r1 - r2
            r11.setPivotX(r3)
            r11.setPivotY(r1)
        L_0x0144:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewSpline> r1 = r0.mAttributesMap
            if (r1 == 0) goto L_0x0172
            java.util.Collection r1 = r1.values()
            java.util.Iterator r8 = r1.iterator()
        L_0x0150:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x0172
            java.lang.Object r1 = r8.next()
            androidx.constraintlayout.core.motion.utils.SplineSet r1 = (androidx.constraintlayout.core.motion.utils.SplineSet) r1
            boolean r2 = r1 instanceof androidx.constraintlayout.motion.utils.ViewSpline.PathRotate
            if (r2 == 0) goto L_0x0150
            double[] r2 = r0.mInterpolateVelocity
            int r3 = r2.length
            if (r3 <= r10) goto L_0x0150
            androidx.constraintlayout.motion.utils.ViewSpline$PathRotate r1 = (androidx.constraintlayout.motion.utils.ViewSpline.PathRotate) r1
            r4 = r2[r15]
            r6 = r2[r10]
            r2 = r21
            r3 = r14
            r1.setPathRotate(r2, r3, r4, r6)
            goto L_0x0150
        L_0x0172:
            if (r9 == 0) goto L_0x018f
            double[] r1 = r0.mInterpolateVelocity
            r7 = r1[r15]
            r17 = r1[r10]
            r1 = r9
            r2 = r21
            r3 = r25
            r4 = r14
            r5 = r23
            r19 = r10
            r9 = r17
            boolean r1 = r1.setPathRotate(r2, r3, r4, r5, r7, r9)
            r1 = r16 | r1
            r16 = r1
            goto L_0x0191
        L_0x018f:
            r19 = r10
        L_0x0191:
            r10 = r19
        L_0x0193:
            androidx.constraintlayout.core.motion.utils.CurveFit[] r1 = r0.mSpline
            int r2 = r1.length
            if (r10 >= r2) goto L_0x01b7
            r1 = r1[r10]
            float[] r2 = r0.mValuesBuff
            r1.getPos((double) r12, (float[]) r2)
            androidx.constraintlayout.motion.widget.MotionPaths r1 = r0.mStartMotionPath
            java.util.LinkedHashMap<java.lang.String, androidx.constraintlayout.widget.ConstraintAttribute> r1 = r1.attributes
            java.lang.String[] r2 = r0.mAttributeNames
            int r3 = r10 + -1
            r2 = r2[r3]
            java.lang.Object r1 = r1.get(r2)
            androidx.constraintlayout.widget.ConstraintAttribute r1 = (androidx.constraintlayout.widget.ConstraintAttribute) r1
            float[] r2 = r0.mValuesBuff
            androidx.constraintlayout.motion.utils.CustomSupport.setInterpolatedValue(r1, r11, r2)
            int r10 = r10 + 1
            goto L_0x0193
        L_0x01b7:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r1 = r0.mStartPoint
            int r1 = r1.mVisibilityMode
            if (r1 != 0) goto L_0x01e5
            r1 = 0
            int r1 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x01ca
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r1 = r0.mStartPoint
            int r1 = r1.visibility
            r11.setVisibility(r1)
            goto L_0x01e5
        L_0x01ca:
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x01d8
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r1 = r0.mEndPoint
            int r1 = r1.visibility
            r11.setVisibility(r1)
            goto L_0x01e5
        L_0x01d8:
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r1 = r0.mEndPoint
            int r1 = r1.visibility
            androidx.constraintlayout.motion.widget.MotionConstrainedPoint r2 = r0.mStartPoint
            int r2 = r2.visibility
            if (r1 == r2) goto L_0x01e5
            r11.setVisibility(r15)
        L_0x01e5:
            androidx.constraintlayout.motion.widget.KeyTrigger[] r1 = r0.mKeyTriggers
            if (r1 == 0) goto L_0x0271
            r1 = r15
        L_0x01ea:
            androidx.constraintlayout.motion.widget.KeyTrigger[] r2 = r0.mKeyTriggers
            int r3 = r2.length
            if (r1 >= r3) goto L_0x0271
            r2 = r2[r1]
            r2.conditionallyFire(r14, r11)
            int r1 = r1 + 1
            goto L_0x01ea
        L_0x01f7:
            r19 = r10
            androidx.constraintlayout.motion.widget.MotionPaths r1 = r0.mStartMotionPath
            float r1 = r1.x
            androidx.constraintlayout.motion.widget.MotionPaths r2 = r0.mEndMotionPath
            float r2 = r2.x
            androidx.constraintlayout.motion.widget.MotionPaths r3 = r0.mStartMotionPath
            float r3 = r3.x
            float r2 = r2 - r3
            float r2 = r2 * r14
            float r1 = r1 + r2
            androidx.constraintlayout.motion.widget.MotionPaths r2 = r0.mStartMotionPath
            float r2 = r2.y
            androidx.constraintlayout.motion.widget.MotionPaths r3 = r0.mEndMotionPath
            float r3 = r3.y
            androidx.constraintlayout.motion.widget.MotionPaths r4 = r0.mStartMotionPath
            float r4 = r4.y
            float r3 = r3 - r4
            float r3 = r3 * r14
            float r2 = r2 + r3
            androidx.constraintlayout.motion.widget.MotionPaths r3 = r0.mStartMotionPath
            float r3 = r3.width
            androidx.constraintlayout.motion.widget.MotionPaths r4 = r0.mEndMotionPath
            float r4 = r4.width
            androidx.constraintlayout.motion.widget.MotionPaths r5 = r0.mStartMotionPath
            float r5 = r5.width
            float r4 = r4 - r5
            float r4 = r4 * r14
            float r3 = r3 + r4
            androidx.constraintlayout.motion.widget.MotionPaths r4 = r0.mStartMotionPath
            float r4 = r4.height
            androidx.constraintlayout.motion.widget.MotionPaths r5 = r0.mEndMotionPath
            float r5 = r5.height
            androidx.constraintlayout.motion.widget.MotionPaths r6 = r0.mStartMotionPath
            float r6 = r6.height
            float r5 = r5 - r6
            float r5 = r5 * r14
            float r4 = r4 + r5
            r5 = 1056964608(0x3f000000, float:0.5)
            float r1 = r1 + r5
            int r6 = (int) r1
            float r2 = r2 + r5
            int r5 = (int) r2
            float r1 = r1 + r3
            int r1 = (int) r1
            float r2 = r2 + r4
            int r2 = (int) r2
            int r3 = r1 - r6
            int r4 = r2 - r5
            androidx.constraintlayout.motion.widget.MotionPaths r7 = r0.mEndMotionPath
            float r7 = r7.width
            androidx.constraintlayout.motion.widget.MotionPaths r8 = r0.mStartMotionPath
            float r8 = r8.width
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 != 0) goto L_0x025f
            androidx.constraintlayout.motion.widget.MotionPaths r7 = r0.mEndMotionPath
            float r7 = r7.height
            androidx.constraintlayout.motion.widget.MotionPaths r8 = r0.mStartMotionPath
            float r8 = r8.height
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 != 0) goto L_0x025f
            boolean r7 = r0.mForceMeasure
            if (r7 == 0) goto L_0x026e
        L_0x025f:
            r7 = 1073741824(0x40000000, float:2.0)
            int r3 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r7)
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r4, r7)
            r11.measure(r3, r4)
            r0.mForceMeasure = r15
        L_0x026e:
            r11.layout(r6, r5, r1, r2)
        L_0x0271:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.motion.utils.ViewOscillator> r1 = r0.mCycleMap
            if (r1 == 0) goto L_0x02a0
            java.util.Collection r1 = r1.values()
            java.util.Iterator r8 = r1.iterator()
        L_0x027d:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x02a0
            java.lang.Object r1 = r8.next()
            androidx.constraintlayout.motion.utils.ViewOscillator r1 = (androidx.constraintlayout.motion.utils.ViewOscillator) r1
            boolean r2 = r1 instanceof androidx.constraintlayout.motion.utils.ViewOscillator.PathRotateSet
            if (r2 == 0) goto L_0x029c
            androidx.constraintlayout.motion.utils.ViewOscillator$PathRotateSet r1 = (androidx.constraintlayout.motion.utils.ViewOscillator.PathRotateSet) r1
            double[] r2 = r0.mInterpolateVelocity
            r4 = r2[r15]
            r6 = r2[r19]
            r2 = r21
            r3 = r14
            r1.setPathRotate(r2, r3, r4, r6)
            goto L_0x027d
        L_0x029c:
            r1.setProperty(r11, r14)
            goto L_0x027d
        L_0x02a0:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionController.interpolate(android.view.View, float, long, androidx.constraintlayout.core.motion.utils.KeyCache):boolean");
    }

    /* access modifiers changed from: package-private */
    public void getDpDt(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float adjustedPosition = getAdjustedPosition(f, this.mVelocity);
        CurveFit[] curveFitArr = this.mSpline;
        int i = 0;
        if (curveFitArr != null) {
            double d = (double) adjustedPosition;
            curveFitArr[0].getSlope(d, this.mInterpolateVelocity);
            this.mSpline[0].getPos(d, this.mInterpolateData);
            float f4 = this.mVelocity[0];
            while (true) {
                dArr = this.mInterpolateVelocity;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = dArr[i] * ((double) f4);
                i++;
            }
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr2 = this.mInterpolateData;
                if (dArr2.length > 0) {
                    curveFit.getPos(d, dArr2);
                    this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                    this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
                    return;
                }
                return;
            }
            this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, dArr, this.mInterpolateData);
            return;
        }
        float f5 = this.mEndMotionPath.x - this.mStartMotionPath.x;
        float f6 = this.mEndMotionPath.y - this.mStartMotionPath.y;
        float f7 = (this.mEndMotionPath.height - this.mStartMotionPath.height) + f6;
        fArr[0] = (f5 * (1.0f - f2)) + (((this.mEndMotionPath.width - this.mStartMotionPath.width) + f5) * f2);
        fArr[1] = (f6 * (1.0f - f3)) + (f7 * f3);
    }

    /* access modifiers changed from: package-private */
    public void getPostLayoutDvDp(float f, int i, int i2, float f2, float f3, float[] fArr) {
        float adjustedPosition = getAdjustedPosition(f, this.mVelocity);
        HashMap<String, ViewSpline> hashMap = this.mAttributesMap;
        ViewOscillator viewOscillator = null;
        SplineSet splineSet = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, ViewSpline> hashMap2 = this.mAttributesMap;
        SplineSet splineSet2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, ViewSpline> hashMap3 = this.mAttributesMap;
        SplineSet splineSet3 = hashMap3 == null ? null : hashMap3.get(Key.ROTATION);
        HashMap<String, ViewSpline> hashMap4 = this.mAttributesMap;
        SplineSet splineSet4 = hashMap4 == null ? null : hashMap4.get("scaleX");
        HashMap<String, ViewSpline> hashMap5 = this.mAttributesMap;
        SplineSet splineSet5 = hashMap5 == null ? null : hashMap5.get("scaleY");
        HashMap<String, ViewOscillator> hashMap6 = this.mCycleMap;
        ViewOscillator viewOscillator2 = hashMap6 == null ? null : hashMap6.get("translationX");
        HashMap<String, ViewOscillator> hashMap7 = this.mCycleMap;
        ViewOscillator viewOscillator3 = hashMap7 == null ? null : hashMap7.get("translationY");
        HashMap<String, ViewOscillator> hashMap8 = this.mCycleMap;
        ViewOscillator viewOscillator4 = hashMap8 == null ? null : hashMap8.get(Key.ROTATION);
        HashMap<String, ViewOscillator> hashMap9 = this.mCycleMap;
        ViewOscillator viewOscillator5 = hashMap9 == null ? null : hashMap9.get("scaleX");
        HashMap<String, ViewOscillator> hashMap10 = this.mCycleMap;
        if (hashMap10 != null) {
            viewOscillator = hashMap10.get("scaleY");
        }
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.clear();
        velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
        velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
        velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
        velocityMatrix.setRotationVelocity((KeyCycleOscillator) viewOscillator4, adjustedPosition);
        velocityMatrix.setTranslationVelocity((KeyCycleOscillator) viewOscillator2, (KeyCycleOscillator) viewOscillator3, adjustedPosition);
        velocityMatrix.setScaleVelocity((KeyCycleOscillator) viewOscillator5, (KeyCycleOscillator) viewOscillator, adjustedPosition);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                double d = (double) adjustedPosition;
                curveFit.getPos(d, dArr);
                this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            }
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.mSpline != null) {
            double adjustedPosition2 = (double) getAdjustedPosition(adjustedPosition, this.mVelocity);
            this.mSpline[0].getSlope(adjustedPosition2, this.mInterpolateVelocity);
            this.mSpline[0].getPos(adjustedPosition2, this.mInterpolateData);
            float f4 = this.mVelocity[0];
            while (true) {
                double[] dArr2 = this.mInterpolateVelocity;
                if (i3 < dArr2.length) {
                    dArr2[i3] = dArr2[i3] * ((double) f4);
                    i3++;
                } else {
                    float f5 = f2;
                    float f6 = f3;
                    this.mStartMotionPath.setDpDt(f5, f6, fArr, this.mInterpolateVariables, dArr2, this.mInterpolateData);
                    velocityMatrix.applyTransform(f5, f6, i, i2, fArr);
                    return;
                }
            }
        } else {
            float f7 = this.mEndMotionPath.x - this.mStartMotionPath.x;
            float f8 = this.mEndMotionPath.y - this.mStartMotionPath.y;
            ViewOscillator viewOscillator6 = viewOscillator;
            float f9 = (this.mEndMotionPath.height - this.mStartMotionPath.height) + f8;
            fArr[0] = (f7 * (1.0f - f2)) + (((this.mEndMotionPath.width - this.mStartMotionPath.width) + f7) * f2);
            fArr[1] = (f8 * (1.0f - f3)) + (f9 * f3);
            velocityMatrix.clear();
            velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
            velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
            velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
            velocityMatrix.setRotationVelocity((KeyCycleOscillator) viewOscillator4, adjustedPosition);
            velocityMatrix.setTranslationVelocity((KeyCycleOscillator) viewOscillator2, (KeyCycleOscillator) viewOscillator3, adjustedPosition);
            velocityMatrix.setScaleVelocity((KeyCycleOscillator) viewOscillator5, (KeyCycleOscillator) viewOscillator6, adjustedPosition);
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
        }
    }

    public int getDrawPath() {
        int i = this.mStartMotionPath.mDrawPath;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            i = Math.max(i, it.next().mDrawPath);
        }
        return Math.max(i, this.mEndMotionPath.mDrawPath);
    }

    public void setDrawPath(int i) {
        this.mStartMotionPath.mDrawPath = i;
    }

    /* access modifiers changed from: package-private */
    public String name() {
        return this.mView.getContext().getResources().getResourceEntryName(this.mView.getId());
    }

    /* access modifiers changed from: package-private */
    public void positionKeyframe(View view, KeyPositionBase keyPositionBase, float f, float f2, String[] strArr, float[] fArr) {
        RectF rectF = new RectF();
        rectF.left = this.mStartMotionPath.x;
        rectF.top = this.mStartMotionPath.y;
        rectF.right = rectF.left + this.mStartMotionPath.width;
        rectF.bottom = rectF.top + this.mStartMotionPath.height;
        RectF rectF2 = new RectF();
        rectF2.left = this.mEndMotionPath.x;
        rectF2.top = this.mEndMotionPath.y;
        rectF2.right = rectF2.left + this.mEndMotionPath.width;
        rectF2.bottom = rectF2.top + this.mEndMotionPath.height;
        keyPositionBase.positionAttributes(view, rectF, rectF2, f, f2, strArr, fArr);
    }

    public int getKeyFramePositions(int[] iArr, float[] fArr) {
        Iterator<Key> it = this.mKeyList.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            iArr[i] = next.mFramePosition + (next.mType * 1000);
            double d = (double) (((float) next.mFramePosition) / 100.0f);
            this.mSpline[0].getPos(d, this.mInterpolateData);
            this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
            i++;
        }
        return i;
    }

    public int getKeyFrameInfo(int i, int[] iArr) {
        float[] fArr = new float[2];
        Iterator<Key> it = this.mKeyList.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            Key next = it.next();
            if (next.mType == i || i != -1) {
                iArr[i3] = 0;
                iArr[i3 + 1] = next.mType;
                iArr[i3 + 2] = next.mFramePosition;
                double d = (double) (((float) next.mFramePosition) / 100.0f);
                this.mSpline[0].getPos(d, this.mInterpolateData);
                this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
                iArr[i3 + 3] = Float.floatToIntBits(fArr[0]);
                int i4 = i3 + 4;
                iArr[i4] = Float.floatToIntBits(fArr[1]);
                if (next instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) next;
                    iArr[i3 + 5] = keyPosition.mPositionType;
                    iArr[i3 + 6] = Float.floatToIntBits(keyPosition.mPercentX);
                    i4 = i3 + 7;
                    iArr[i4] = Float.floatToIntBits(keyPosition.mPercentY);
                }
                int i5 = i4 + 1;
                iArr[i3] = i5 - i3;
                i2++;
                i3 = i5;
            }
        }
        return i2;
    }
}
