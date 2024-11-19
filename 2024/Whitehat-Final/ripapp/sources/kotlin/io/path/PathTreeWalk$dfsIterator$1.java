package kotlin.io.path;

import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/nio/file/Path;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "kotlin.io.path.PathTreeWalk$dfsIterator$1", f = "PathTreeWalk.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3}, l = {184, 190, 199, 205}, m = "invokeSuspend", n = {"$this$iterator", "stack", "entriesReader", "startNode", "this_$iv", "path$iv", "$this$iterator", "stack", "entriesReader", "$this$iterator", "stack", "entriesReader", "pathNode", "this_$iv", "path$iv", "$this$iterator", "stack", "entriesReader"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2"})
/* compiled from: PathTreeWalk.kt */
final class PathTreeWalk$dfsIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ PathTreeWalk this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PathTreeWalk$dfsIterator$1(PathTreeWalk pathTreeWalk, Continuation<? super PathTreeWalk$dfsIterator$1> continuation) {
        super(2, continuation);
        this.this$0 = pathTreeWalk;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PathTreeWalk$dfsIterator$1 pathTreeWalk$dfsIterator$1 = new PathTreeWalk$dfsIterator$1(this.this$0, continuation);
        pathTreeWalk$dfsIterator$1.L$0 = obj;
        return pathTreeWalk$dfsIterator$1;
    }

    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PathTreeWalk$dfsIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: kotlin.sequences.SequenceScope} */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0220 A[SYNTHETIC] */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = 0
            r8 = 1
            if (r2 == 0) goto L_0x006c
            if (r2 == r8) goto L_0x004f
            if (r2 == r6) goto L_0x003e
            if (r2 == r5) goto L_0x0021
            if (r2 != r4) goto L_0x0019
            goto L_0x003e
        L_0x0019:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0021:
            java.lang.Object r2 = r0.L$5
            java.nio.file.Path r2 = (java.nio.file.Path) r2
            java.lang.Object r6 = r0.L$4
            kotlin.io.path.PathTreeWalk r6 = (kotlin.io.path.PathTreeWalk) r6
            java.lang.Object r9 = r0.L$3
            kotlin.io.path.PathNode r9 = (kotlin.io.path.PathNode) r9
            java.lang.Object r10 = r0.L$2
            kotlin.io.path.DirectoryEntriesReader r10 = (kotlin.io.path.DirectoryEntriesReader) r10
            java.lang.Object r11 = r0.L$1
            kotlin.collections.ArrayDeque r11 = (kotlin.collections.ArrayDeque) r11
            java.lang.Object r12 = r0.L$0
            kotlin.sequences.SequenceScope r12 = (kotlin.sequences.SequenceScope) r12
            kotlin.ResultKt.throwOnFailure(r19)
            goto L_0x01b3
        L_0x003e:
            java.lang.Object r2 = r0.L$2
            kotlin.io.path.DirectoryEntriesReader r2 = (kotlin.io.path.DirectoryEntriesReader) r2
            java.lang.Object r6 = r0.L$1
            kotlin.collections.ArrayDeque r6 = (kotlin.collections.ArrayDeque) r6
            java.lang.Object r9 = r0.L$0
            kotlin.sequences.SequenceScope r9 = (kotlin.sequences.SequenceScope) r9
            kotlin.ResultKt.throwOnFailure(r19)
            goto L_0x0142
        L_0x004f:
            java.lang.Object r2 = r0.L$5
            java.nio.file.Path r2 = (java.nio.file.Path) r2
            java.lang.Object r6 = r0.L$4
            kotlin.io.path.PathTreeWalk r6 = (kotlin.io.path.PathTreeWalk) r6
            java.lang.Object r9 = r0.L$3
            kotlin.io.path.PathNode r9 = (kotlin.io.path.PathNode) r9
            java.lang.Object r10 = r0.L$2
            kotlin.io.path.DirectoryEntriesReader r10 = (kotlin.io.path.DirectoryEntriesReader) r10
            java.lang.Object r11 = r0.L$1
            kotlin.collections.ArrayDeque r11 = (kotlin.collections.ArrayDeque) r11
            java.lang.Object r12 = r0.L$0
            kotlin.sequences.SequenceScope r12 = (kotlin.sequences.SequenceScope) r12
            kotlin.ResultKt.throwOnFailure(r19)
            goto L_0x00e6
        L_0x006c:
            kotlin.ResultKt.throwOnFailure(r19)
            java.lang.Object r2 = r0.L$0
            r9 = r2
            kotlin.sequences.SequenceScope r9 = (kotlin.sequences.SequenceScope) r9
            kotlin.collections.ArrayDeque r2 = new kotlin.collections.ArrayDeque
            r2.<init>()
            kotlin.io.path.DirectoryEntriesReader r10 = new kotlin.io.path.DirectoryEntriesReader
            kotlin.io.path.PathTreeWalk r11 = r0.this$0
            boolean r11 = r11.getFollowLinks()
            r10.<init>(r11)
            kotlin.io.path.PathNode r11 = new kotlin.io.path.PathNode
            kotlin.io.path.PathTreeWalk r12 = r0.this$0
            java.nio.file.Path r12 = r12.start
            kotlin.io.path.PathTreeWalk r13 = r0.this$0
            java.nio.file.Path r13 = r13.start
            kotlin.io.path.PathTreeWalk r14 = r0.this$0
            java.nio.file.LinkOption[] r14 = r14.getLinkOptions()
            java.lang.Object r13 = kotlin.io.path.PathTreeWalkKt.keyOf(r13, r14)
            r11.<init>(r12, r13, r7)
            kotlin.io.path.PathTreeWalk r12 = r0.this$0
            java.nio.file.Path r13 = r11.getPath()
            java.nio.file.LinkOption[] r14 = r12.getLinkOptions()
            int r15 = r14.length
            java.lang.Object[] r14 = java.util.Arrays.copyOf(r14, r15)
            java.nio.file.LinkOption[] r14 = (java.nio.file.LinkOption[]) r14
            int r15 = r14.length
            java.lang.Object[] r14 = java.util.Arrays.copyOf(r14, r15)
            java.nio.file.LinkOption[] r14 = (java.nio.file.LinkOption[]) r14
            boolean r14 = java.nio.file.Files.isDirectory(r13, r14)
            if (r14 == 0) goto L_0x011c
            boolean r6 = kotlin.io.path.PathTreeWalkKt.createsCycle(r11)
            if (r6 != 0) goto L_0x0112
            boolean r6 = r12.getIncludeDirectories()
            if (r6 == 0) goto L_0x00eb
            r6 = r0
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r0.L$0 = r9
            r0.L$1 = r2
            r0.L$2 = r10
            r0.L$3 = r11
            r0.L$4 = r12
            r0.L$5 = r13
            r0.label = r8
            java.lang.Object r6 = r9.yield(r13, r6)
            if (r6 != r1) goto L_0x00e1
            return r1
        L_0x00e1:
            r6 = r12
            r12 = r9
            r9 = r11
            r11 = r2
            r2 = r13
        L_0x00e6:
            r13 = r2
            r2 = r11
            r11 = r9
            r9 = r12
            r12 = r6
        L_0x00eb:
            java.nio.file.LinkOption[] r6 = r12.getLinkOptions()
            int r12 = r6.length
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r12)
            java.nio.file.LinkOption[] r6 = (java.nio.file.LinkOption[]) r6
            int r12 = r6.length
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r12)
            java.nio.file.LinkOption[] r6 = (java.nio.file.LinkOption[]) r6
            boolean r6 = java.nio.file.Files.isDirectory(r13, r6)
            if (r6 == 0) goto L_0x0140
            java.util.List r6 = r10.readEntries(r11)
            java.util.Iterator r6 = r6.iterator()
            r11.setContentIterator(r6)
            r2.addLast(r11)
            goto L_0x0140
        L_0x0112:
            java.nio.file.FileSystemLoopException r1 = new java.nio.file.FileSystemLoopException
            java.lang.String r2 = r13.toString()
            r1.<init>(r2)
            throw r1
        L_0x011c:
            java.nio.file.LinkOption[] r11 = new java.nio.file.LinkOption[r8]
            java.nio.file.LinkOption r12 = java.nio.file.LinkOption.NOFOLLOW_LINKS
            r11[r3] = r12
            java.lang.Object[] r11 = java.util.Arrays.copyOf(r11, r8)
            java.nio.file.LinkOption[] r11 = (java.nio.file.LinkOption[]) r11
            boolean r11 = java.nio.file.Files.exists(r13, r11)
            if (r11 == 0) goto L_0x0140
            r11 = r0
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r0.L$0 = r9
            r0.L$1 = r2
            r0.L$2 = r10
            r0.label = r6
            java.lang.Object r6 = r9.yield(r13, r11)
            if (r6 != r1) goto L_0x0140
            return r1
        L_0x0140:
            r6 = r2
            r2 = r10
        L_0x0142:
            r10 = r6
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            r10 = r10 ^ r8
            if (r10 == 0) goto L_0x0220
            java.lang.Object r10 = r6.last()
            kotlin.io.path.PathNode r10 = (kotlin.io.path.PathNode) r10
            java.util.Iterator r10 = r10.getContentIterator()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x021b
            java.lang.Object r10 = r10.next()
            kotlin.io.path.PathNode r10 = (kotlin.io.path.PathNode) r10
            kotlin.io.path.PathTreeWalk r11 = r0.this$0
            java.nio.file.Path r12 = r10.getPath()
            java.nio.file.LinkOption[] r13 = r11.getLinkOptions()
            int r14 = r13.length
            java.lang.Object[] r13 = java.util.Arrays.copyOf(r13, r14)
            java.nio.file.LinkOption[] r13 = (java.nio.file.LinkOption[]) r13
            int r14 = r13.length
            java.lang.Object[] r13 = java.util.Arrays.copyOf(r13, r14)
            java.nio.file.LinkOption[] r13 = (java.nio.file.LinkOption[]) r13
            boolean r13 = java.nio.file.Files.isDirectory(r12, r13)
            if (r13 == 0) goto L_0x01f1
            boolean r13 = kotlin.io.path.PathTreeWalkKt.createsCycle(r10)
            if (r13 != 0) goto L_0x01e7
            boolean r13 = r11.getIncludeDirectories()
            if (r13 == 0) goto L_0x01bf
            r13 = r0
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            r0.L$0 = r9
            r0.L$1 = r6
            r0.L$2 = r2
            r0.L$3 = r10
            r0.L$4 = r11
            r0.L$5 = r12
            r0.label = r5
            java.lang.Object r13 = r9.yield(r12, r13)
            if (r13 != r1) goto L_0x01a7
            return r1
        L_0x01a7:
            r16 = r10
            r10 = r2
            r2 = r12
            r12 = r9
            r9 = r16
            r17 = r11
            r11 = r6
            r6 = r17
        L_0x01b3:
            r16 = r12
            r12 = r2
            r2 = r10
            r10 = r9
            r9 = r16
            r17 = r11
            r11 = r6
            r6 = r17
        L_0x01bf:
            java.nio.file.LinkOption[] r11 = r11.getLinkOptions()
            int r13 = r11.length
            java.lang.Object[] r11 = java.util.Arrays.copyOf(r11, r13)
            java.nio.file.LinkOption[] r11 = (java.nio.file.LinkOption[]) r11
            int r13 = r11.length
            java.lang.Object[] r11 = java.util.Arrays.copyOf(r11, r13)
            java.nio.file.LinkOption[] r11 = (java.nio.file.LinkOption[]) r11
            boolean r11 = java.nio.file.Files.isDirectory(r12, r11)
            if (r11 == 0) goto L_0x0142
            java.util.List r11 = r2.readEntries(r10)
            java.util.Iterator r11 = r11.iterator()
            r10.setContentIterator(r11)
            r6.addLast(r10)
            goto L_0x0142
        L_0x01e7:
            java.nio.file.FileSystemLoopException r1 = new java.nio.file.FileSystemLoopException
            java.lang.String r2 = r12.toString()
            r1.<init>(r2)
            throw r1
        L_0x01f1:
            java.nio.file.LinkOption[] r10 = new java.nio.file.LinkOption[r8]
            java.nio.file.LinkOption r11 = java.nio.file.LinkOption.NOFOLLOW_LINKS
            r10[r3] = r11
            java.lang.Object[] r10 = java.util.Arrays.copyOf(r10, r8)
            java.nio.file.LinkOption[] r10 = (java.nio.file.LinkOption[]) r10
            boolean r10 = java.nio.file.Files.exists(r12, r10)
            if (r10 == 0) goto L_0x0142
            r10 = r0
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r0.L$0 = r9
            r0.L$1 = r6
            r0.L$2 = r2
            r0.L$3 = r7
            r0.L$4 = r7
            r0.L$5 = r7
            r0.label = r4
            java.lang.Object r10 = r9.yield(r12, r10)
            if (r10 != r1) goto L_0x0142
            return r1
        L_0x021b:
            r6.removeLast()
            goto L_0x0142
        L_0x0220:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathTreeWalk$dfsIterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
