package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.MetadataRepo;
import java.util.Arrays;
import java.util.Set;

final class EmojiProcessor {
    private static final int ACTION_ADVANCE_BOTH = 1;
    private static final int ACTION_ADVANCE_END = 2;
    private static final int ACTION_FLUSH = 3;
    private static final int MAX_LOOK_AROUND_CHARACTER = 16;
    private final int[] mEmojiAsDefaultStyleExceptions;
    private EmojiCompat.GlyphChecker mGlyphChecker;
    private final MetadataRepo mMetadataRepo;
    private final EmojiCompat.SpanFactory mSpanFactory;
    private final boolean mUseEmojiAsDefaultStyle;

    private interface EmojiProcessCallback<T> {
        T getResult();

        boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer);
    }

    private static boolean hasInvalidSelection(int i, int i2) {
        return i == -1 || i2 == -1 || i != i2;
    }

    EmojiProcessor(MetadataRepo metadataRepo, EmojiCompat.SpanFactory spanFactory, EmojiCompat.GlyphChecker glyphChecker, boolean z, int[] iArr, Set<int[]> set) {
        this.mSpanFactory = spanFactory;
        this.mMetadataRepo = metadataRepo;
        this.mGlyphChecker = glyphChecker;
        this.mUseEmojiAsDefaultStyle = z;
        this.mEmojiAsDefaultStyleExceptions = iArr;
        initExclusions(set);
    }

    private void initExclusions(Set<int[]> set) {
        if (!set.isEmpty()) {
            for (int[] next : set) {
                String str = new String(next, 0, next.length);
                process(str, 0, str.length(), 1, true, new MarkExclusionCallback(str));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getEmojiMatch(CharSequence charSequence) {
        return getEmojiMatch(charSequence, this.mMetadataRepo.getMetadataVersion());
    }

    /* access modifiers changed from: package-private */
    public int getEmojiMatch(CharSequence charSequence, int i) {
        ProcessorSm processorSm = new ProcessorSm(this.mMetadataRepo.getRootNode(), this.mUseEmojiAsDefaultStyle, this.mEmojiAsDefaultStyleExceptions);
        int length = charSequence.length();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int codePointAt = Character.codePointAt(charSequence, i2);
            int check = processorSm.check(codePointAt);
            TypefaceEmojiRasterizer currentMetadata = processorSm.getCurrentMetadata();
            if (check == 1) {
                i2 += Character.charCount(codePointAt);
                i4 = 0;
            } else if (check == 2) {
                i2 += Character.charCount(codePointAt);
            } else if (check == 3) {
                currentMetadata = processorSm.getFlushMetadata();
                if (currentMetadata.getCompatAdded() <= i) {
                    i3++;
                }
            }
            if (currentMetadata != null && currentMetadata.getCompatAdded() <= i) {
                i4++;
            }
        }
        if (i3 != 0) {
            return 2;
        }
        if (!processorSm.isInFlushableState() || processorSm.getCurrentMetadata().getCompatAdded() > i) {
            return i4 == 0 ? 0 : 2;
        }
        return 1;
    }

    /* access modifiers changed from: package-private */
    public int getEmojiStart(CharSequence charSequence, int i) {
        if (i < 0 || i >= charSequence.length()) {
            return -1;
        }
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            EmojiSpan[] emojiSpanArr = (EmojiSpan[]) spanned.getSpans(i, i + 1, EmojiSpan.class);
            if (emojiSpanArr.length > 0) {
                return spanned.getSpanStart(emojiSpanArr[0]);
            }
        }
        return ((EmojiProcessLookupCallback) process(charSequence, Math.max(0, i - 16), Math.min(charSequence.length(), i + 16), Integer.MAX_VALUE, true, new EmojiProcessLookupCallback(i))).start;
    }

    /* access modifiers changed from: package-private */
    public int getEmojiEnd(CharSequence charSequence, int i) {
        if (i < 0 || i >= charSequence.length()) {
            return -1;
        }
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            EmojiSpan[] emojiSpanArr = (EmojiSpan[]) spanned.getSpans(i, i + 1, EmojiSpan.class);
            if (emojiSpanArr.length > 0) {
                return spanned.getSpanEnd(emojiSpanArr[0]);
            }
        }
        return ((EmojiProcessLookupCallback) process(charSequence, Math.max(0, i - 16), Math.min(charSequence.length(), i + 16), Integer.MAX_VALUE, true, new EmojiProcessLookupCallback(i))).end;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048 A[Catch:{ all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0066 A[Catch:{ all -> 0x00b3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.CharSequence process(java.lang.CharSequence r10, int r11, int r12, int r13, boolean r14) {
        /*
            r9 = this;
            boolean r0 = r10 instanceof androidx.emoji2.text.SpannableBuilder
            if (r0 == 0) goto L_0x000a
            r1 = r10
            androidx.emoji2.text.SpannableBuilder r1 = (androidx.emoji2.text.SpannableBuilder) r1
            r1.beginBatchEdit()
        L_0x000a:
            if (r0 != 0) goto L_0x002c
            boolean r1 = r10 instanceof android.text.Spannable     // Catch:{ all -> 0x00b3 }
            if (r1 == 0) goto L_0x0011
            goto L_0x002c
        L_0x0011:
            boolean r1 = r10 instanceof android.text.Spanned     // Catch:{ all -> 0x00b3 }
            if (r1 == 0) goto L_0x002a
            r1 = r10
            android.text.Spanned r1 = (android.text.Spanned) r1     // Catch:{ all -> 0x00b3 }
            int r2 = r11 + -1
            int r3 = r12 + 1
            java.lang.Class<androidx.emoji2.text.EmojiSpan> r4 = androidx.emoji2.text.EmojiSpan.class
            int r1 = r1.nextSpanTransition(r2, r3, r4)     // Catch:{ all -> 0x00b3 }
            if (r1 > r12) goto L_0x002a
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r1 = new androidx.emoji2.text.UnprecomputeTextOnModificationSpannable     // Catch:{ all -> 0x00b3 }
            r1.<init>((java.lang.CharSequence) r10)     // Catch:{ all -> 0x00b3 }
            goto L_0x0034
        L_0x002a:
            r1 = 0
            goto L_0x0034
        L_0x002c:
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r1 = new androidx.emoji2.text.UnprecomputeTextOnModificationSpannable     // Catch:{ all -> 0x00b3 }
            r2 = r10
            android.text.Spannable r2 = (android.text.Spannable) r2     // Catch:{ all -> 0x00b3 }
            r1.<init>((android.text.Spannable) r2)     // Catch:{ all -> 0x00b3 }
        L_0x0034:
            r2 = 0
            if (r1 == 0) goto L_0x0062
            java.lang.Class<androidx.emoji2.text.EmojiSpan> r3 = androidx.emoji2.text.EmojiSpan.class
            java.lang.Object[] r3 = r1.getSpans(r11, r12, r3)     // Catch:{ all -> 0x00b3 }
            androidx.emoji2.text.EmojiSpan[] r3 = (androidx.emoji2.text.EmojiSpan[]) r3     // Catch:{ all -> 0x00b3 }
            if (r3 == 0) goto L_0x0062
            int r4 = r3.length     // Catch:{ all -> 0x00b3 }
            if (r4 <= 0) goto L_0x0062
            int r4 = r3.length     // Catch:{ all -> 0x00b3 }
            r5 = r2
        L_0x0046:
            if (r5 >= r4) goto L_0x0062
            r6 = r3[r5]     // Catch:{ all -> 0x00b3 }
            int r7 = r1.getSpanStart(r6)     // Catch:{ all -> 0x00b3 }
            int r8 = r1.getSpanEnd(r6)     // Catch:{ all -> 0x00b3 }
            if (r7 == r12) goto L_0x0057
            r1.removeSpan(r6)     // Catch:{ all -> 0x00b3 }
        L_0x0057:
            int r11 = java.lang.Math.min(r7, r11)     // Catch:{ all -> 0x00b3 }
            int r12 = java.lang.Math.max(r8, r12)     // Catch:{ all -> 0x00b3 }
            int r5 = r5 + 1
            goto L_0x0046
        L_0x0062:
            r3 = r11
            r4 = r12
            if (r3 == r4) goto L_0x00aa
            int r11 = r10.length()     // Catch:{ all -> 0x00b3 }
            if (r3 < r11) goto L_0x006d
            goto L_0x00aa
        L_0x006d:
            r11 = 2147483647(0x7fffffff, float:NaN)
            if (r13 == r11) goto L_0x0082
            if (r1 == 0) goto L_0x0082
            int r11 = r1.length()     // Catch:{ all -> 0x00b3 }
            java.lang.Class<androidx.emoji2.text.EmojiSpan> r12 = androidx.emoji2.text.EmojiSpan.class
            java.lang.Object[] r11 = r1.getSpans(r2, r11, r12)     // Catch:{ all -> 0x00b3 }
            androidx.emoji2.text.EmojiSpan[] r11 = (androidx.emoji2.text.EmojiSpan[]) r11     // Catch:{ all -> 0x00b3 }
            int r11 = r11.length     // Catch:{ all -> 0x00b3 }
            int r13 = r13 - r11
        L_0x0082:
            r5 = r13
            androidx.emoji2.text.EmojiProcessor$EmojiProcessAddSpanCallback r7 = new androidx.emoji2.text.EmojiProcessor$EmojiProcessAddSpanCallback     // Catch:{ all -> 0x00b3 }
            androidx.emoji2.text.EmojiCompat$SpanFactory r11 = r9.mSpanFactory     // Catch:{ all -> 0x00b3 }
            r7.<init>(r1, r11)     // Catch:{ all -> 0x00b3 }
            r1 = r9
            r2 = r10
            r6 = r14
            java.lang.Object r11 = r1.process(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x00b3 }
            androidx.emoji2.text.UnprecomputeTextOnModificationSpannable r11 = (androidx.emoji2.text.UnprecomputeTextOnModificationSpannable) r11     // Catch:{ all -> 0x00b3 }
            if (r11 == 0) goto L_0x00a1
            android.text.Spannable r11 = r11.getUnwrappedSpannable()     // Catch:{ all -> 0x00b3 }
            if (r0 == 0) goto L_0x00a0
            androidx.emoji2.text.SpannableBuilder r10 = (androidx.emoji2.text.SpannableBuilder) r10
            r10.endBatchEdit()
        L_0x00a0:
            return r11
        L_0x00a1:
            if (r0 == 0) goto L_0x00a9
            r11 = r10
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
            r11.endBatchEdit()
        L_0x00a9:
            return r10
        L_0x00aa:
            if (r0 == 0) goto L_0x00b2
            r11 = r10
            androidx.emoji2.text.SpannableBuilder r11 = (androidx.emoji2.text.SpannableBuilder) r11
            r11.endBatchEdit()
        L_0x00b2:
            return r10
        L_0x00b3:
            r11 = move-exception
            if (r0 == 0) goto L_0x00bb
            androidx.emoji2.text.SpannableBuilder r10 = (androidx.emoji2.text.SpannableBuilder) r10
            r10.endBatchEdit()
        L_0x00bb:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.EmojiProcessor.process(java.lang.CharSequence, int, int, int, boolean):java.lang.CharSequence");
    }

    private <T> T process(CharSequence charSequence, int i, int i2, int i3, boolean z, EmojiProcessCallback<T> emojiProcessCallback) {
        int i4;
        ProcessorSm processorSm = new ProcessorSm(this.mMetadataRepo.getRootNode(), this.mUseEmojiAsDefaultStyle, this.mEmojiAsDefaultStyleExceptions);
        int i5 = 0;
        boolean z2 = true;
        int codePointAt = Character.codePointAt(charSequence, i);
        loop0:
        while (true) {
            i4 = i;
            while (i < i2 && i5 < i3 && z2) {
                int check = processorSm.check(codePointAt);
                if (check == 1) {
                    i4 += Character.charCount(Character.codePointAt(charSequence, i4));
                    if (i4 < i2) {
                        codePointAt = Character.codePointAt(charSequence, i4);
                    }
                    i = i4;
                } else if (check == 2) {
                    i += Character.charCount(codePointAt);
                    if (i < i2) {
                        codePointAt = Character.codePointAt(charSequence, i);
                    }
                } else if (check == 3) {
                    if (z || !hasGlyph(charSequence, i4, i, processorSm.getFlushMetadata())) {
                        i5++;
                        z2 = emojiProcessCallback.handleEmoji(charSequence, i4, i, processorSm.getFlushMetadata());
                    }
                }
            }
        }
        if (processorSm.isInFlushableState() && i5 < i3 && z2 && (z || !hasGlyph(charSequence, i4, i, processorSm.getCurrentMetadata()))) {
            emojiProcessCallback.handleEmoji(charSequence, i4, i, processorSm.getCurrentMetadata());
        }
        return emojiProcessCallback.getResult();
    }

    static boolean handleOnKeyDown(Editable editable, int i, KeyEvent keyEvent) {
        boolean z;
        if (i != 67) {
            z = i != 112 ? false : delete(editable, keyEvent, true);
        } else {
            z = delete(editable, keyEvent, false);
        }
        if (!z) {
            return false;
        }
        MetaKeyKeyListener.adjustMetaAfterKeypress(editable);
        return true;
    }

    private static boolean delete(Editable editable, KeyEvent keyEvent, boolean z) {
        EmojiSpan[] emojiSpanArr;
        if (hasModifiers(keyEvent)) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (!hasInvalidSelection(selectionStart, selectionEnd) && (emojiSpanArr = (EmojiSpan[]) editable.getSpans(selectionStart, selectionEnd, EmojiSpan.class)) != null && emojiSpanArr.length > 0) {
            int length = emojiSpanArr.length;
            int i = 0;
            while (i < length) {
                EmojiSpan emojiSpan = emojiSpanArr[i];
                int spanStart = editable.getSpanStart(emojiSpan);
                int spanEnd = editable.getSpanEnd(emojiSpan);
                if ((!z || spanStart != selectionStart) && ((z || spanEnd != selectionStart) && (selectionStart <= spanStart || selectionStart >= spanEnd))) {
                    i++;
                } else {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    static boolean handleDeleteSurroundingText(InputConnection inputConnection, Editable editable, int i, int i2, boolean z) {
        int i3;
        int i4;
        if (editable != null && inputConnection != null && i >= 0 && i2 >= 0) {
            int selectionStart = Selection.getSelectionStart(editable);
            int selectionEnd = Selection.getSelectionEnd(editable);
            if (hasInvalidSelection(selectionStart, selectionEnd)) {
                return false;
            }
            if (z) {
                i4 = CodepointIndexFinder.findIndexBackward(editable, selectionStart, Math.max(i, 0));
                i3 = CodepointIndexFinder.findIndexForward(editable, selectionEnd, Math.max(i2, 0));
                if (i4 == -1 || i3 == -1) {
                    return false;
                }
            } else {
                i4 = Math.max(selectionStart - i, 0);
                i3 = Math.min(selectionEnd + i2, editable.length());
            }
            EmojiSpan[] emojiSpanArr = (EmojiSpan[]) editable.getSpans(i4, i3, EmojiSpan.class);
            if (emojiSpanArr != null && emojiSpanArr.length > 0) {
                for (EmojiSpan emojiSpan : emojiSpanArr) {
                    int spanStart = editable.getSpanStart(emojiSpan);
                    int spanEnd = editable.getSpanEnd(emojiSpan);
                    i4 = Math.min(spanStart, i4);
                    i3 = Math.max(spanEnd, i3);
                }
                int max = Math.max(i4, 0);
                int min = Math.min(i3, editable.length());
                inputConnection.beginBatchEdit();
                editable.delete(max, min);
                inputConnection.endBatchEdit();
                return true;
            }
        }
        return false;
    }

    private static boolean hasModifiers(KeyEvent keyEvent) {
        return !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState());
    }

    private boolean hasGlyph(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        if (typefaceEmojiRasterizer.getHasGlyph() == 0) {
            typefaceEmojiRasterizer.setHasGlyph(this.mGlyphChecker.hasGlyph(charSequence, i, i2, typefaceEmojiRasterizer.getSdkAdded()));
        }
        return typefaceEmojiRasterizer.getHasGlyph() == 2;
    }

    static final class ProcessorSm {
        private static final int STATE_DEFAULT = 1;
        private static final int STATE_WALKING = 2;
        private int mCurrentDepth;
        private MetadataRepo.Node mCurrentNode;
        private final int[] mEmojiAsDefaultStyleExceptions;
        private MetadataRepo.Node mFlushNode;
        private int mLastCodepoint;
        private final MetadataRepo.Node mRootNode;
        private int mState = 1;
        private final boolean mUseEmojiAsDefaultStyle;

        private static boolean isEmojiStyle(int i) {
            return i == 65039;
        }

        private static boolean isTextStyle(int i) {
            return i == 65038;
        }

        ProcessorSm(MetadataRepo.Node node, boolean z, int[] iArr) {
            this.mRootNode = node;
            this.mCurrentNode = node;
            this.mUseEmojiAsDefaultStyle = z;
            this.mEmojiAsDefaultStyleExceptions = iArr;
        }

        /* access modifiers changed from: package-private */
        public int check(int i) {
            MetadataRepo.Node node = this.mCurrentNode.get(i);
            int i2 = 2;
            if (this.mState != 2) {
                if (node == null) {
                    i2 = reset();
                } else {
                    this.mState = 2;
                    this.mCurrentNode = node;
                    this.mCurrentDepth = 1;
                }
            } else if (node != null) {
                this.mCurrentNode = node;
                this.mCurrentDepth++;
            } else if (isTextStyle(i)) {
                i2 = reset();
            } else if (!isEmojiStyle(i)) {
                if (this.mCurrentNode.getData() != null) {
                    i2 = 3;
                    if (this.mCurrentDepth != 1) {
                        this.mFlushNode = this.mCurrentNode;
                        reset();
                    } else if (shouldUseEmojiPresentationStyleForSingleCodepoint()) {
                        this.mFlushNode = this.mCurrentNode;
                        reset();
                    } else {
                        i2 = reset();
                    }
                } else {
                    i2 = reset();
                }
            }
            this.mLastCodepoint = i;
            return i2;
        }

        private int reset() {
            this.mState = 1;
            this.mCurrentNode = this.mRootNode;
            this.mCurrentDepth = 0;
            return 1;
        }

        /* access modifiers changed from: package-private */
        public TypefaceEmojiRasterizer getFlushMetadata() {
            return this.mFlushNode.getData();
        }

        /* access modifiers changed from: package-private */
        public TypefaceEmojiRasterizer getCurrentMetadata() {
            return this.mCurrentNode.getData();
        }

        /* access modifiers changed from: package-private */
        public boolean isInFlushableState() {
            if (this.mState != 2 || this.mCurrentNode.getData() == null || (this.mCurrentDepth <= 1 && !shouldUseEmojiPresentationStyleForSingleCodepoint())) {
                return false;
            }
            return true;
        }

        private boolean shouldUseEmojiPresentationStyleForSingleCodepoint() {
            if (this.mCurrentNode.getData().isDefaultEmoji() || isEmojiStyle(this.mLastCodepoint)) {
                return true;
            }
            if (this.mUseEmojiAsDefaultStyle) {
                if (this.mEmojiAsDefaultStyleExceptions == null) {
                    return true;
                }
                if (Arrays.binarySearch(this.mEmojiAsDefaultStyleExceptions, this.mCurrentNode.getData().getCodepointAt(0)) < 0) {
                    return true;
                }
            }
            return false;
        }
    }

    private static final class CodepointIndexFinder {
        private static final int INVALID_INDEX = -1;

        private CodepointIndexFinder() {
        }

        static int findIndexBackward(CharSequence charSequence, int i, int i2) {
            int length = charSequence.length();
            if (i < 0 || length < i || i2 < 0) {
                return -1;
            }
            while (true) {
                boolean z = false;
                while (i2 != 0) {
                    i--;
                    if (i < 0) {
                        return z ? -1 : 0;
                    }
                    char charAt = charSequence.charAt(i);
                    if (z) {
                        if (!Character.isHighSurrogate(charAt)) {
                            return -1;
                        }
                        i2--;
                    } else if (!Character.isSurrogate(charAt)) {
                        i2--;
                    } else if (Character.isHighSurrogate(charAt)) {
                        return -1;
                    } else {
                        z = true;
                    }
                }
                return i;
            }
        }

        static int findIndexForward(CharSequence charSequence, int i, int i2) {
            int length = charSequence.length();
            if (i < 0 || length < i || i2 < 0) {
                return -1;
            }
            while (true) {
                boolean z = false;
                while (i2 != 0) {
                    if (r7 < length) {
                        char charAt = charSequence.charAt(r7);
                        if (z) {
                            if (!Character.isLowSurrogate(charAt)) {
                                return -1;
                            }
                            i2--;
                            i = r7 + 1;
                        } else if (!Character.isSurrogate(charAt)) {
                            i2--;
                            r7++;
                        } else if (Character.isLowSurrogate(charAt)) {
                            return -1;
                        } else {
                            r7++;
                            z = true;
                        }
                    } else if (z) {
                        return -1;
                    } else {
                        return length;
                    }
                }
                return r7;
            }
        }
    }

    private static class EmojiProcessAddSpanCallback implements EmojiProcessCallback<UnprecomputeTextOnModificationSpannable> {
        private final EmojiCompat.SpanFactory mSpanFactory;
        public UnprecomputeTextOnModificationSpannable spannable;

        EmojiProcessAddSpanCallback(UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable, EmojiCompat.SpanFactory spanFactory) {
            this.spannable = unprecomputeTextOnModificationSpannable;
            this.mSpanFactory = spanFactory;
        }

        public boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            Spannable spannable2;
            if (typefaceEmojiRasterizer.isPreferredSystemRender()) {
                return true;
            }
            if (this.spannable == null) {
                if (charSequence instanceof Spannable) {
                    spannable2 = (Spannable) charSequence;
                } else {
                    spannable2 = new SpannableString(charSequence);
                }
                this.spannable = new UnprecomputeTextOnModificationSpannable(spannable2);
            }
            this.spannable.setSpan(this.mSpanFactory.createSpan(typefaceEmojiRasterizer), i, i2, 33);
            return true;
        }

        public UnprecomputeTextOnModificationSpannable getResult() {
            return this.spannable;
        }
    }

    private static class EmojiProcessLookupCallback implements EmojiProcessCallback<EmojiProcessLookupCallback> {
        public int end = -1;
        private final int mOffset;
        public int start = -1;

        public EmojiProcessLookupCallback getResult() {
            return this;
        }

        EmojiProcessLookupCallback(int i) {
            this.mOffset = i;
        }

        public boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            int i3 = this.mOffset;
            if (i <= i3 && i3 < i2) {
                this.start = i;
                this.end = i2;
                return false;
            } else if (i2 <= i3) {
                return true;
            } else {
                return false;
            }
        }
    }

    private static class MarkExclusionCallback implements EmojiProcessCallback<MarkExclusionCallback> {
        private final String mExclusion;

        public MarkExclusionCallback getResult() {
            return this;
        }

        MarkExclusionCallback(String str) {
            this.mExclusion = str;
        }

        public boolean handleEmoji(CharSequence charSequence, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            if (!TextUtils.equals(charSequence.subSequence(i, i2), this.mExclusion)) {
                return true;
            }
            typefaceEmojiRasterizer.setExclusion(true);
            return false;
        }
    }
}
