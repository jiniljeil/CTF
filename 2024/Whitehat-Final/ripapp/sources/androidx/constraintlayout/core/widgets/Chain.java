package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import java.util.ArrayList;

public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            i3 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = 0;
        } else {
            i3 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i2 = 2;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            ChainHead chainHead = chainHeadArr[i4];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v0, resolved type: androidx.constraintlayout.core.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v1, resolved type: androidx.constraintlayout.core.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v2, resolved type: androidx.constraintlayout.core.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v3, resolved type: androidx.constraintlayout.core.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v4, resolved type: androidx.constraintlayout.core.widgets.ConstraintWidget} */
    /* JADX WARNING: type inference failed for: r4v12, types: [androidx.constraintlayout.core.SolverVariable] */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        if (r2.mHorizontalChainStyle == 2) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004a, code lost:
        if (r2.mVerticalChainStyle == 2) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
        r5 = false;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01b7  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x01f3  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0383  */
    /* JADX WARNING: Removed duplicated region for block: B:217:0x03dc  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x03f3  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x03f6  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x03fc  */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x04d9  */
    /* JADX WARNING: Removed duplicated region for block: B:290:0x0523  */
    /* JADX WARNING: Removed duplicated region for block: B:293:0x052e  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0533  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0539  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x053e  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0542  */
    /* JADX WARNING: Removed duplicated region for block: B:304:0x0553  */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x0556  */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x03de A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r38, androidx.constraintlayout.core.LinearSystem r39, int r40, int r41, androidx.constraintlayout.core.widgets.ChainHead r42) {
        /*
            r0 = r38
            r9 = r39
            r10 = r40
            r1 = r42
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r1.mFirst
            androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r1.mLast
            androidx.constraintlayout.core.widgets.ConstraintWidget r13 = r1.mFirstVisibleWidget
            androidx.constraintlayout.core.widgets.ConstraintWidget r14 = r1.mLastVisibleWidget
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r1.mHead
            float r3 = r1.mTotalWeight
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r1.mFirstMatchConstraintWidget
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r1.mLastMatchConstraintWidget
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r0.mListDimensionBehaviors
            r4 = r4[r10]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r6 = 1
            if (r4 != r5) goto L_0x0023
            r4 = r6
            goto L_0x0024
        L_0x0023:
            r4 = 0
        L_0x0024:
            r5 = 2
            if (r10 != 0) goto L_0x003a
            int r8 = r2.mHorizontalChainStyle
            if (r8 != 0) goto L_0x002d
            r8 = r6
            goto L_0x002e
        L_0x002d:
            r8 = 0
        L_0x002e:
            int r15 = r2.mHorizontalChainStyle
            if (r15 != r6) goto L_0x0034
            r15 = r6
            goto L_0x0035
        L_0x0034:
            r15 = 0
        L_0x0035:
            int r7 = r2.mHorizontalChainStyle
            if (r7 != r5) goto L_0x004e
            goto L_0x004c
        L_0x003a:
            int r7 = r2.mVerticalChainStyle
            if (r7 != 0) goto L_0x0040
            r8 = r6
            goto L_0x0041
        L_0x0040:
            r8 = 0
        L_0x0041:
            int r7 = r2.mVerticalChainStyle
            if (r7 != r6) goto L_0x0047
            r15 = r6
            goto L_0x0048
        L_0x0047:
            r15 = 0
        L_0x0048:
            int r7 = r2.mVerticalChainStyle
            if (r7 != r5) goto L_0x004e
        L_0x004c:
            r5 = r6
            goto L_0x004f
        L_0x004e:
            r5 = 0
        L_0x004f:
            r17 = r15
            r7 = 0
            r15 = r8
            r8 = r11
        L_0x0054:
            r22 = 0
            if (r7 != 0) goto L_0x014a
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r8.mListAnchors
            r6 = r6[r41]
            if (r5 == 0) goto L_0x0061
            r20 = 1
            goto L_0x0063
        L_0x0061:
            r20 = 4
        L_0x0063:
            int r24 = r6.getMargin()
            r25 = r3
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r8.mListDimensionBehaviors
            r3 = r3[r10]
            r26 = r7
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 != r7) goto L_0x007b
            int[] r3 = r8.mResolvedMatchConstraintDefault
            r3 = r3[r10]
            if (r3 != 0) goto L_0x007b
            r3 = 1
            goto L_0x007c
        L_0x007b:
            r3 = 0
        L_0x007c:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r6.mTarget
            if (r7 == 0) goto L_0x008a
            if (r8 == r11) goto L_0x008a
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r6.mTarget
            int r7 = r7.getMargin()
            int r24 = r24 + r7
        L_0x008a:
            r7 = r24
            if (r5 == 0) goto L_0x0097
            if (r8 == r11) goto L_0x0097
            if (r8 == r13) goto L_0x0097
            r24 = r15
            r20 = 8
            goto L_0x0099
        L_0x0097:
            r24 = r15
        L_0x0099:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r15 = r6.mTarget
            if (r15 == 0) goto L_0x00db
            if (r8 != r13) goto L_0x00ae
            androidx.constraintlayout.core.SolverVariable r15 = r6.mSolverVariable
            r27 = r2
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r6.mTarget
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
            r28 = r11
            r11 = 6
            r9.addGreaterThan(r15, r2, r7, r11)
            goto L_0x00bd
        L_0x00ae:
            r27 = r2
            r28 = r11
            androidx.constraintlayout.core.SolverVariable r2 = r6.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r6.mTarget
            androidx.constraintlayout.core.SolverVariable r11 = r11.mSolverVariable
            r15 = 8
            r9.addGreaterThan(r2, r11, r7, r15)
        L_0x00bd:
            if (r3 == 0) goto L_0x00c3
            if (r5 != 0) goto L_0x00c3
            r20 = 5
        L_0x00c3:
            if (r8 != r13) goto L_0x00cf
            if (r5 == 0) goto L_0x00cf
            boolean r2 = r8.isInBarrier(r10)
            if (r2 == 0) goto L_0x00cf
            r2 = 5
            goto L_0x00d1
        L_0x00cf:
            r2 = r20
        L_0x00d1:
            androidx.constraintlayout.core.SolverVariable r3 = r6.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r6.mTarget
            androidx.constraintlayout.core.SolverVariable r6 = r6.mSolverVariable
            r9.addEquality(r3, r6, r7, r2)
            goto L_0x00df
        L_0x00db:
            r27 = r2
            r28 = r11
        L_0x00df:
            if (r4 == 0) goto L_0x0117
            int r2 = r8.getVisibility()
            r3 = 8
            if (r2 == r3) goto L_0x0105
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r8.mListDimensionBehaviors
            r2 = r2[r10]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x0105
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r41 + 1
            r2 = r2[r3]
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r8.mListAnchors
            r3 = r3[r41]
            androidx.constraintlayout.core.SolverVariable r3 = r3.mSolverVariable
            r6 = 5
            r7 = 0
            r9.addGreaterThan(r2, r3, r7, r6)
            goto L_0x0106
        L_0x0105:
            r7 = 0
        L_0x0106:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            r2 = r2[r41]
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            r3 = r3[r41]
            androidx.constraintlayout.core.SolverVariable r3 = r3.mSolverVariable
            r6 = 8
            r9.addGreaterThan(r2, r3, r7, r6)
        L_0x0117:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r41 + 1
            r2 = r2[r3]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0138
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r2.mOwner
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r41]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0138
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r41]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 == r8) goto L_0x0136
            goto L_0x0138
        L_0x0136:
            r22 = r2
        L_0x0138:
            if (r22 == 0) goto L_0x013f
            r8 = r22
            r7 = r26
            goto L_0x0140
        L_0x013f:
            r7 = 1
        L_0x0140:
            r15 = r24
            r3 = r25
            r2 = r27
            r11 = r28
            goto L_0x0054
        L_0x014a:
            r27 = r2
            r25 = r3
            r28 = r11
            r24 = r15
            if (r14 == 0) goto L_0x01b4
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            int r3 = r41 + 1
            r2 = r2[r3]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x01b4
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r2 = r2[r3]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r6 = r14.mListDimensionBehaviors
            r6 = r6[r10]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r7) goto L_0x0188
            int[] r6 = r14.mResolvedMatchConstraintDefault
            r6 = r6[r10]
            if (r6 != 0) goto L_0x0188
            if (r5 != 0) goto L_0x0188
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r2.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r6.mOwner
            if (r6 != r0) goto L_0x0188
            androidx.constraintlayout.core.SolverVariable r6 = r2.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r2.mTarget
            androidx.constraintlayout.core.SolverVariable r7 = r7.mSolverVariable
            int r8 = r2.getMargin()
            int r8 = -r8
            r11 = 5
            r9.addEquality(r6, r7, r8, r11)
            goto L_0x01a0
        L_0x0188:
            r11 = 5
            if (r5 == 0) goto L_0x01a0
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r2.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r6.mOwner
            if (r6 != r0) goto L_0x01a0
            androidx.constraintlayout.core.SolverVariable r6 = r2.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r2.mTarget
            androidx.constraintlayout.core.SolverVariable r7 = r7.mSolverVariable
            int r8 = r2.getMargin()
            int r8 = -r8
            r15 = 4
            r9.addEquality(r6, r7, r8, r15)
        L_0x01a0:
            androidx.constraintlayout.core.SolverVariable r6 = r2.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r7 = r12.mListAnchors
            r3 = r7[r3]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            androidx.constraintlayout.core.SolverVariable r3 = r3.mSolverVariable
            int r2 = r2.getMargin()
            int r2 = -r2
            r7 = 6
            r9.addLowerThan(r6, r3, r2, r7)
            goto L_0x01b5
        L_0x01b4:
            r11 = 5
        L_0x01b5:
            if (r4 == 0) goto L_0x01d2
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r2 = r41 + 1
            r0 = r0[r2]
            androidx.constraintlayout.core.SolverVariable r0 = r0.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r12.mListAnchors
            r3 = r3[r2]
            androidx.constraintlayout.core.SolverVariable r3 = r3.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r12.mListAnchors
            r2 = r4[r2]
            int r2 = r2.getMargin()
            r4 = 8
            r9.addGreaterThan(r0, r3, r2, r4)
        L_0x01d2:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.ConstraintWidget> r0 = r1.mWeightedMatchConstraintsWidgets
            if (r0 == 0) goto L_0x0289
            int r2 = r0.size()
            r3 = 1
            if (r2 <= r3) goto L_0x0289
            boolean r4 = r1.mHasUndefinedWeights
            if (r4 == 0) goto L_0x01e9
            boolean r4 = r1.mHasComplexMatchWeights
            if (r4 != 0) goto L_0x01e9
            int r4 = r1.mWidgetsMatchCount
            float r4 = (float) r4
            goto L_0x01eb
        L_0x01e9:
            r4 = r25
        L_0x01eb:
            r6 = 0
            r30 = r6
            r8 = r22
            r7 = 0
        L_0x01f1:
            if (r7 >= r2) goto L_0x0289
            java.lang.Object r15 = r0.get(r7)
            androidx.constraintlayout.core.widgets.ConstraintWidget r15 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r15
            float[] r3 = r15.mWeight
            r3 = r3[r10]
            int r19 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r19 >= 0) goto L_0x021f
            boolean r3 = r1.mHasComplexMatchWeights
            if (r3 == 0) goto L_0x021a
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r15.mListAnchors
            int r19 = r41 + 1
            r3 = r3[r19]
            androidx.constraintlayout.core.SolverVariable r3 = r3.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r15 = r15.mListAnchors
            r15 = r15[r41]
            androidx.constraintlayout.core.SolverVariable r15 = r15.mSolverVariable
            r6 = 0
            r11 = 4
            r9.addEquality(r3, r15, r6, r11)
            r11 = r6
            goto L_0x0238
        L_0x021a:
            r11 = 4
            r3 = 1065353216(0x3f800000, float:1.0)
            r6 = 0
            goto L_0x0220
        L_0x021f:
            r11 = 4
        L_0x0220:
            int r19 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r19 != 0) goto L_0x023d
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r15.mListAnchors
            int r19 = r41 + 1
            r3 = r3[r19]
            androidx.constraintlayout.core.SolverVariable r3 = r3.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r15 = r15.mListAnchors
            r15 = r15[r41]
            androidx.constraintlayout.core.SolverVariable r15 = r15.mSolverVariable
            r6 = 8
            r11 = 0
            r9.addEquality(r3, r15, r11, r6)
        L_0x0238:
            r25 = r0
            r16 = r2
            goto L_0x027e
        L_0x023d:
            r11 = 0
            if (r8 == 0) goto L_0x0277
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r8.mListAnchors
            r6 = r6[r41]
            androidx.constraintlayout.core.SolverVariable r6 = r6.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r8 = r8.mListAnchors
            int r16 = r41 + 1
            r8 = r8[r16]
            androidx.constraintlayout.core.SolverVariable r8 = r8.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r11 = r15.mListAnchors
            r11 = r11[r41]
            androidx.constraintlayout.core.SolverVariable r11 = r11.mSolverVariable
            r25 = r0
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r15.mListAnchors
            r0 = r0[r16]
            androidx.constraintlayout.core.SolverVariable r0 = r0.mSolverVariable
            r16 = r2
            androidx.constraintlayout.core.ArrayRow r2 = r39.createRow()
            r29 = r2
            r31 = r4
            r32 = r3
            r33 = r6
            r34 = r8
            r35 = r11
            r36 = r0
            r29.createRowEqualMatchDimensions(r30, r31, r32, r33, r34, r35, r36)
            r9.addConstraint(r2)
            goto L_0x027b
        L_0x0277:
            r25 = r0
            r16 = r2
        L_0x027b:
            r30 = r3
            r8 = r15
        L_0x027e:
            int r7 = r7 + 1
            r2 = r16
            r0 = r25
            r3 = 1
            r6 = 0
            r11 = 5
            goto L_0x01f1
        L_0x0289:
            if (r13 == 0) goto L_0x02e6
            if (r13 == r14) goto L_0x028f
            if (r5 == 0) goto L_0x02e6
        L_0x028f:
            r11 = r28
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r11.mListAnchors
            r0 = r0[r41]
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            int r2 = r41 + 1
            r1 = r1[r2]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x02a5
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mTarget
            androidx.constraintlayout.core.SolverVariable r0 = r0.mSolverVariable
            r3 = r0
            goto L_0x02a7
        L_0x02a5:
            r3 = r22
        L_0x02a7:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r1.mTarget
            if (r0 == 0) goto L_0x02b1
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r1.mTarget
            androidx.constraintlayout.core.SolverVariable r0 = r0.mSolverVariable
            r5 = r0
            goto L_0x02b3
        L_0x02b1:
            r5 = r22
        L_0x02b3:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r41]
            if (r14 == 0) goto L_0x02bd
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r1 = r14.mListAnchors
            r1 = r1[r2]
        L_0x02bd:
            if (r3 == 0) goto L_0x0515
            if (r5 == 0) goto L_0x0515
            if (r10 != 0) goto L_0x02c8
            r2 = r27
            float r2 = r2.mHorizontalBiasPercent
            goto L_0x02cc
        L_0x02c8:
            r2 = r27
            float r2 = r2.mVerticalBiasPercent
        L_0x02cc:
            r4 = r2
            int r6 = r0.getMargin()
            int r7 = r1.getMargin()
            androidx.constraintlayout.core.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r8 = r1.mSolverVariable
            r10 = 7
            r0 = r39
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0515
        L_0x02e6:
            r11 = r28
            if (r24 == 0) goto L_0x03e3
            if (r13 == 0) goto L_0x03e3
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x02f9
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x02f9
            r18 = 1
            goto L_0x02fb
        L_0x02f9:
            r18 = 0
        L_0x02fb:
            r8 = r13
            r15 = r8
        L_0x02fd:
            if (r15 == 0) goto L_0x0515
            androidx.constraintlayout.core.widgets.ConstraintWidget[] r0 = r15.mNextChainWidget
            r0 = r0[r10]
            r7 = r0
        L_0x0304:
            if (r7 == 0) goto L_0x0313
            int r0 = r7.getVisibility()
            r6 = 8
            if (r0 != r6) goto L_0x0315
            androidx.constraintlayout.core.widgets.ConstraintWidget[] r0 = r7.mNextChainWidget
            r7 = r0[r10]
            goto L_0x0304
        L_0x0313:
            r6 = 8
        L_0x0315:
            if (r7 != 0) goto L_0x0322
            if (r15 != r14) goto L_0x031a
            goto L_0x0322
        L_0x031a:
            r21 = r7
        L_0x031c:
            r16 = r8
            r20 = 5
            goto L_0x03d3
        L_0x0322:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r15.mListAnchors
            r0 = r0[r41]
            androidx.constraintlayout.core.SolverVariable r1 = r0.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x0331
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.mTarget
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0333
        L_0x0331:
            r2 = r22
        L_0x0333:
            if (r8 == r15) goto L_0x033e
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r8.mListAnchors
            int r3 = r41 + 1
            r2 = r2[r3]
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0353
        L_0x033e:
            if (r15 != r13) goto L_0x0353
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            r2 = r2[r41]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0351
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            r2 = r2[r41]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
            goto L_0x0353
        L_0x0351:
            r2 = r22
        L_0x0353:
            int r0 = r0.getMargin()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r15.mListAnchors
            int r4 = r41 + 1
            r3 = r3[r4]
            int r3 = r3.getMargin()
            if (r7 == 0) goto L_0x036c
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r7.mListAnchors
            r5 = r5[r41]
            androidx.constraintlayout.core.SolverVariable r6 = r5.mSolverVariable
        L_0x0369:
            r38 = r7
            goto L_0x037b
        L_0x036c:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r12.mListAnchors
            r5 = r5[r4]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x0377
            androidx.constraintlayout.core.SolverVariable r6 = r5.mSolverVariable
            goto L_0x0369
        L_0x0377:
            r38 = r7
            r6 = r22
        L_0x037b:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r7 = r15.mListAnchors
            r7 = r7[r4]
            androidx.constraintlayout.core.SolverVariable r7 = r7.mSolverVariable
            if (r5 == 0) goto L_0x0388
            int r5 = r5.getMargin()
            int r3 = r3 + r5
        L_0x0388:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r5 = r5[r4]
            int r5 = r5.getMargin()
            int r0 = r0 + r5
            if (r1 == 0) goto L_0x03cf
            if (r2 == 0) goto L_0x03cf
            if (r6 == 0) goto L_0x03cf
            if (r7 == 0) goto L_0x03cf
            if (r15 != r13) goto L_0x03a3
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r41]
            int r0 = r0.getMargin()
        L_0x03a3:
            r5 = r0
            if (r15 != r14) goto L_0x03b1
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r4]
            int r0 = r0.getMargin()
            r16 = r0
            goto L_0x03b3
        L_0x03b1:
            r16 = r3
        L_0x03b3:
            if (r18 == 0) goto L_0x03b8
            r19 = 8
            goto L_0x03ba
        L_0x03b8:
            r19 = 5
        L_0x03ba:
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r39
            r3 = r5
            r5 = r6
            r20 = 5
            r6 = r7
            r21 = r38
            r7 = r16
            r16 = r8
            r8 = r19
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x03d3
        L_0x03cf:
            r21 = r38
            goto L_0x031c
        L_0x03d3:
            int r0 = r15.getVisibility()
            r8 = 8
            if (r0 == r8) goto L_0x03dc
            goto L_0x03de
        L_0x03dc:
            r15 = r16
        L_0x03de:
            r8 = r15
            r15 = r21
            goto L_0x02fd
        L_0x03e3:
            r8 = 8
            if (r17 == 0) goto L_0x0515
            if (r13 == 0) goto L_0x0515
            int r0 = r1.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x03f6
            int r0 = r1.mWidgetsCount
            int r1 = r1.mWidgetsMatchCount
            if (r0 != r1) goto L_0x03f6
            r18 = 1
            goto L_0x03f8
        L_0x03f6:
            r18 = 0
        L_0x03f8:
            r7 = r13
            r15 = r7
        L_0x03fa:
            if (r15 == 0) goto L_0x04c0
            androidx.constraintlayout.core.widgets.ConstraintWidget[] r0 = r15.mNextChainWidget
            r0 = r0[r10]
        L_0x0400:
            if (r0 == 0) goto L_0x040d
            int r1 = r0.getVisibility()
            if (r1 != r8) goto L_0x040d
            androidx.constraintlayout.core.widgets.ConstraintWidget[] r0 = r0.mNextChainWidget
            r0 = r0[r10]
            goto L_0x0400
        L_0x040d:
            if (r15 == r13) goto L_0x04ab
            if (r15 == r14) goto L_0x04ab
            if (r0 == 0) goto L_0x04ab
            if (r0 != r14) goto L_0x0418
            r6 = r22
            goto L_0x0419
        L_0x0418:
            r6 = r0
        L_0x0419:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r15.mListAnchors
            r0 = r0[r41]
            androidx.constraintlayout.core.SolverVariable r1 = r0.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.mTarget
            if (r2 == 0) goto L_0x0427
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.mTarget
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
        L_0x0427:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r7.mListAnchors
            int r3 = r41 + 1
            r2 = r2[r3]
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
            int r0 = r0.getMargin()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r15.mListAnchors
            r4 = r4[r3]
            int r4 = r4.getMargin()
            if (r6 == 0) goto L_0x0456
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r6.mListAnchors
            r5 = r5[r41]
            androidx.constraintlayout.core.SolverVariable r8 = r5.mSolverVariable
            r38 = r6
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r5.mTarget
            if (r6 == 0) goto L_0x044e
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r5.mTarget
            androidx.constraintlayout.core.SolverVariable r6 = r6.mSolverVariable
            goto L_0x0450
        L_0x044e:
            r6 = r22
        L_0x0450:
            r37 = r8
            r8 = r6
            r6 = r37
            goto L_0x0469
        L_0x0456:
            r38 = r6
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r14.mListAnchors
            r5 = r5[r41]
            if (r5 == 0) goto L_0x0461
            androidx.constraintlayout.core.SolverVariable r6 = r5.mSolverVariable
            goto L_0x0463
        L_0x0461:
            r6 = r22
        L_0x0463:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r8 = r15.mListAnchors
            r8 = r8[r3]
            androidx.constraintlayout.core.SolverVariable r8 = r8.mSolverVariable
        L_0x0469:
            if (r5 == 0) goto L_0x0470
            int r5 = r5.getMargin()
            int r4 = r4 + r5
        L_0x0470:
            r16 = r4
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r7.mListAnchors
            r3 = r4[r3]
            int r3 = r3.getMargin()
            int r3 = r3 + r0
            if (r18 == 0) goto L_0x0480
            r19 = 8
            goto L_0x0482
        L_0x0480:
            r19 = 4
        L_0x0482:
            if (r1 == 0) goto L_0x04a0
            if (r2 == 0) goto L_0x04a0
            if (r6 == 0) goto L_0x04a0
            if (r8 == 0) goto L_0x04a0
            r4 = 1056964608(0x3f000000, float:0.5)
            r0 = r39
            r5 = r6
            r20 = r38
            r21 = 4
            r6 = r8
            r23 = r7
            r7 = r16
            r10 = 8
            r8 = r19
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04a8
        L_0x04a0:
            r20 = r38
            r23 = r7
            r10 = 8
            r21 = 4
        L_0x04a8:
            r0 = r20
            goto L_0x04b0
        L_0x04ab:
            r23 = r7
            r10 = r8
            r21 = 4
        L_0x04b0:
            int r1 = r15.getVisibility()
            if (r1 == r10) goto L_0x04b8
            r7 = r15
            goto L_0x04ba
        L_0x04b8:
            r7 = r23
        L_0x04ba:
            r15 = r0
            r8 = r10
            r10 = r40
            goto L_0x03fa
        L_0x04c0:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r41]
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            r1 = r1[r41]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r1.mTarget
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            int r3 = r41 + 1
            r10 = r2[r3]
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r3]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r2.mTarget
            r15 = 5
            if (r1 == 0) goto L_0x0505
            if (r13 == r14) goto L_0x04e7
            androidx.constraintlayout.core.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r1 = r1.mSolverVariable
            int r0 = r0.getMargin()
            r9.addEquality(r2, r1, r0, r15)
            goto L_0x0505
        L_0x04e7:
            if (r11 == 0) goto L_0x0505
            androidx.constraintlayout.core.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r3 = r1.mSolverVariable
            int r4 = r0.getMargin()
            androidx.constraintlayout.core.SolverVariable r5 = r10.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r6 = r11.mSolverVariable
            int r7 = r10.getMargin()
            r8 = 1056964608(0x3f000000, float:0.5)
            r0 = r39
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r8
            r8 = r15
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x0505:
            if (r11 == 0) goto L_0x0515
            if (r13 == r14) goto L_0x0515
            androidx.constraintlayout.core.SolverVariable r0 = r10.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r1 = r11.mSolverVariable
            int r2 = r10.getMargin()
            int r2 = -r2
            r9.addEquality(r0, r1, r2, r15)
        L_0x0515:
            if (r24 != 0) goto L_0x0519
            if (r17 == 0) goto L_0x057e
        L_0x0519:
            if (r13 == 0) goto L_0x057e
            if (r13 == r14) goto L_0x057e
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r41]
            if (r14 != 0) goto L_0x0524
            r14 = r13
        L_0x0524:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r1 = r14.mListAnchors
            int r2 = r41 + 1
            r1 = r1[r2]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x0533
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r0.mTarget
            androidx.constraintlayout.core.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0535
        L_0x0533:
            r3 = r22
        L_0x0535:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r1.mTarget
            if (r4 == 0) goto L_0x053e
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r1.mTarget
            androidx.constraintlayout.core.SolverVariable r4 = r4.mSolverVariable
            goto L_0x0540
        L_0x053e:
            r4 = r22
        L_0x0540:
            if (r12 == r14) goto L_0x0553
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r12.mListAnchors
            r4 = r4[r2]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r4.mTarget
            if (r5 == 0) goto L_0x0550
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.mTarget
            androidx.constraintlayout.core.SolverVariable r4 = r4.mSolverVariable
            r22 = r4
        L_0x0550:
            r5 = r22
            goto L_0x0554
        L_0x0553:
            r5 = r4
        L_0x0554:
            if (r13 != r14) goto L_0x055e
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r13.mListAnchors
            r0 = r0[r41]
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            r1 = r1[r2]
        L_0x055e:
            if (r3 == 0) goto L_0x057e
            if (r5 == 0) goto L_0x057e
            int r4 = r0.getMargin()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r14.mListAnchors
            r2 = r6[r2]
            int r7 = r2.getMargin()
            androidx.constraintlayout.core.SolverVariable r2 = r0.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r6 = r1.mSolverVariable
            r8 = 5
            r10 = 1056964608(0x3f000000, float:0.5)
            r0 = r39
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x057e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.Chain.applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.LinearSystem, int, int, androidx.constraintlayout.core.widgets.ChainHead):void");
    }
}
