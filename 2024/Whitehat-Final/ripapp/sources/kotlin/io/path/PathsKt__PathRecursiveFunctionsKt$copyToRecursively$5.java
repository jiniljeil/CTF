package kotlin.io.path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lkotlin/io/path/FileVisitorBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PathRecursiveFunctions.kt */
final class PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5 extends Lambda implements Function1<FileVisitorBuilder, Unit> {
    final /* synthetic */ Function3<CopyActionContext, Path, Path, CopyActionResult> $copyAction;
    final /* synthetic */ Function3<Path, Path, Exception, OnErrorResult> $onError;
    final /* synthetic */ Path $target;
    final /* synthetic */ Path $this_copyToRecursively;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PathsKt__PathRecursiveFunctionsKt$copyToRecursively$5(Function3<? super CopyActionContext, ? super Path, ? super Path, ? extends CopyActionResult> function3, Path path, Path path2, Function3<? super Path, ? super Path, ? super Exception, ? extends OnErrorResult> function32) {
        super(1);
        this.$copyAction = function3;
        this.$this_copyToRecursively = path;
        this.$target = path2;
        this.$onError = function32;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((FileVisitorBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(FileVisitorBuilder fileVisitorBuilder) {
        Intrinsics.checkNotNullParameter(fileVisitorBuilder, "$this$visitFileTree");
        final Function3<CopyActionContext, Path, Path, CopyActionResult> function3 = this.$copyAction;
        final Path path = this.$this_copyToRecursively;
        final Path path2 = this.$target;
        final Function3<Path, Path, Exception, OnErrorResult> function32 = this.$onError;
        fileVisitorBuilder.onPreVisitDirectory(new Function2<Path, BasicFileAttributes, FileVisitResult>() {
            public final FileVisitResult invoke(Path path, BasicFileAttributes basicFileAttributes) {
                Intrinsics.checkNotNullParameter(path, "p0");
                Intrinsics.checkNotNullParameter(basicFileAttributes, "p1");
                return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(function3, path, path2, function32, path, basicFileAttributes);
            }
        });
        final Function3<CopyActionContext, Path, Path, CopyActionResult> function33 = this.$copyAction;
        final Path path3 = this.$this_copyToRecursively;
        final Path path4 = this.$target;
        final Function3<Path, Path, Exception, OnErrorResult> function34 = this.$onError;
        fileVisitorBuilder.onVisitFile(new Function2<Path, BasicFileAttributes, FileVisitResult>() {
            public final FileVisitResult invoke(Path path, BasicFileAttributes basicFileAttributes) {
                Intrinsics.checkNotNullParameter(path, "p0");
                Intrinsics.checkNotNullParameter(basicFileAttributes, "p1");
                return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(function33, path3, path4, function34, path, basicFileAttributes);
            }
        });
        final Function3<Path, Path, Exception, OnErrorResult> function35 = this.$onError;
        final Path path5 = this.$this_copyToRecursively;
        final Path path6 = this.$target;
        fileVisitorBuilder.onVisitFileFailed(new Function2<Path, Exception, FileVisitResult>() {
            public final FileVisitResult invoke(Path path, Exception exc) {
                Intrinsics.checkNotNullParameter(path, "p0");
                Intrinsics.checkNotNullParameter(exc, "p1");
                return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(function35, path5, path6, path, exc);
            }
        });
        final Function3<Path, Path, Exception, OnErrorResult> function36 = this.$onError;
        final Path path7 = this.$this_copyToRecursively;
        final Path path8 = this.$target;
        fileVisitorBuilder.onPostVisitDirectory(new Function2<Path, IOException, FileVisitResult>() {
            public final FileVisitResult invoke(Path path, IOException iOException) {
                Intrinsics.checkNotNullParameter(path, "directory");
                if (iOException == null) {
                    return FileVisitResult.CONTINUE;
                }
                return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(function36, path7, path8, path, iOException);
            }
        });
    }
}
