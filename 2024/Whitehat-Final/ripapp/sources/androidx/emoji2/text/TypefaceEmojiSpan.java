package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;

public final class TypefaceEmojiSpan extends EmojiSpan {
    private static Paint sDebugPaint;
    private TextPaint mWorkingPaint;

    public TypefaceEmojiSpan(TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        super(typefaceEmojiRasterizer);
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        float f2 = f;
        int i6 = i3;
        int i7 = i5;
        CharSequence charSequence2 = charSequence;
        TextPaint textPaint = paint;
        TextPaint applyCharacterSpanStyles = applyCharacterSpanStyles(charSequence, i, i2, textPaint);
        if (!(applyCharacterSpanStyles == null || applyCharacterSpanStyles.bgColor == 0)) {
            drawBackground(canvas, applyCharacterSpanStyles, f, f2 + ((float) getWidth()), (float) i6, (float) i7);
        }
        if (EmojiCompat.get().isEmojiSpanIndicatorEnabled()) {
            canvas.drawRect(f, (float) i6, f2 + ((float) getWidth()), (float) i7, getDebugPaint());
        }
        TypefaceEmojiRasterizer typefaceRasterizer = getTypefaceRasterizer();
        float f3 = (float) i4;
        Canvas canvas2 = canvas;
        if (applyCharacterSpanStyles != null) {
            textPaint = applyCharacterSpanStyles;
        }
        typefaceRasterizer.draw(canvas, f2, f3, textPaint);
    }

    /* access modifiers changed from: package-private */
    public void drawBackground(Canvas canvas, TextPaint textPaint, float f, float f2, float f3, float f4) {
        int color = textPaint.getColor();
        Paint.Style style = textPaint.getStyle();
        textPaint.setColor(textPaint.bgColor);
        textPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(f, f3, f2, f4, textPaint);
        textPaint.setStyle(style);
        textPaint.setColor(color);
    }

    private TextPaint applyCharacterSpanStyles(CharSequence charSequence, int i, int i2, Paint paint) {
        if (charSequence instanceof Spanned) {
            CharacterStyle[] characterStyleArr = (CharacterStyle[]) ((Spanned) charSequence).getSpans(i, i2, CharacterStyle.class);
            if (characterStyleArr.length != 0) {
                if (!(characterStyleArr.length == 1 && characterStyleArr[0] == this)) {
                    TextPaint textPaint = this.mWorkingPaint;
                    if (textPaint == null) {
                        textPaint = new TextPaint();
                        this.mWorkingPaint = textPaint;
                    }
                    textPaint.set(paint);
                    for (CharacterStyle updateDrawState : characterStyleArr) {
                        updateDrawState.updateDrawState(textPaint);
                    }
                    return textPaint;
                }
            }
            if (paint instanceof TextPaint) {
                return (TextPaint) paint;
            }
            return null;
        } else if (paint instanceof TextPaint) {
            return (TextPaint) paint;
        } else {
            return null;
        }
    }

    private static Paint getDebugPaint() {
        if (sDebugPaint == null) {
            TextPaint textPaint = new TextPaint();
            sDebugPaint = textPaint;
            textPaint.setColor(EmojiCompat.get().getEmojiSpanIndicatorColor());
            sDebugPaint.setStyle(Paint.Style.FILL);
        }
        return sDebugPaint;
    }
}
