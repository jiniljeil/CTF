package kotlin.jvm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;

@Target({ElementType.METHOD})
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY})
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lkotlin/jvm/JvmDefault;", "", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "Switch to new -Xjvm-default modes: `all` or `all-compatibility`")
@Retention(RetentionPolicy.RUNTIME)
/* compiled from: JvmDefault.kt */
public @interface JvmDefault {
}
