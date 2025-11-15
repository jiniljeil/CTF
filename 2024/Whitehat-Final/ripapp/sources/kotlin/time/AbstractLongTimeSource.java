package kotlin.time;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.ComparableTimeMark;
import kotlin.time.Duration;
import kotlin.time.TimeSource;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH$R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\f"}, d2 = {"Lkotlin/time/AbstractLongTimeSource;", "Lkotlin/time/TimeSource$WithComparableMarks;", "unit", "Lkotlin/time/DurationUnit;", "(Lkotlin/time/DurationUnit;)V", "getUnit", "()Lkotlin/time/DurationUnit;", "markNow", "Lkotlin/time/ComparableTimeMark;", "read", "", "LongTimeMark", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TimeSources.kt */
public abstract class AbstractLongTimeSource implements TimeSource.WithComparableMarks {
    private final DurationUnit unit;

    /* access modifiers changed from: protected */
    public abstract long read();

    public AbstractLongTimeSource(DurationUnit durationUnit) {
        Intrinsics.checkNotNullParameter(durationUnit, "unit");
        this.unit = durationUnit;
    }

    /* access modifiers changed from: protected */
    public final DurationUnit getUnit() {
        return this.unit;
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0015\u0010\n\u001a\u00020\u0007H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010\r\u001a\u00020\u0007H\u0016ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\fJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u001e\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0001H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u001b\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u0016\u0010\u0006\u001a\u00020\u0007X\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u001e"}, d2 = {"Lkotlin/time/AbstractLongTimeSource$LongTimeMark;", "Lkotlin/time/ComparableTimeMark;", "startedAt", "", "timeSource", "Lkotlin/time/AbstractLongTimeSource;", "offset", "Lkotlin/time/Duration;", "(JLkotlin/time/AbstractLongTimeSource;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "effectiveDuration", "effectiveDuration-UwyO8pc$kotlin_stdlib", "()J", "elapsedNow", "elapsedNow-UwyO8pc", "equals", "", "other", "", "hashCode", "", "minus", "minus-UwyO8pc", "(Lkotlin/time/ComparableTimeMark;)J", "plus", "duration", "plus-LRDsOJo", "(J)Lkotlin/time/ComparableTimeMark;", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TimeSources.kt */
    private static final class LongTimeMark implements ComparableTimeMark {
        private final long offset;
        private final long startedAt;
        private final AbstractLongTimeSource timeSource;

        public /* synthetic */ LongTimeMark(long j, AbstractLongTimeSource abstractLongTimeSource, long j2, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, abstractLongTimeSource, j2);
        }

        private LongTimeMark(long j, AbstractLongTimeSource abstractLongTimeSource, long j2) {
            Intrinsics.checkNotNullParameter(abstractLongTimeSource, "timeSource");
            this.startedAt = j;
            this.timeSource = abstractLongTimeSource;
            this.offset = j2;
        }

        public int compareTo(ComparableTimeMark comparableTimeMark) {
            return ComparableTimeMark.DefaultImpls.compareTo(this, comparableTimeMark);
        }

        public boolean hasNotPassedNow() {
            return ComparableTimeMark.DefaultImpls.hasNotPassedNow(this);
        }

        public boolean hasPassedNow() {
            return ComparableTimeMark.DefaultImpls.hasPassedNow(this);
        }

        /* renamed from: minus-LRDsOJo  reason: not valid java name */
        public ComparableTimeMark m1471minusLRDsOJo(long j) {
            return ComparableTimeMark.DefaultImpls.m1482minusLRDsOJo(this, j);
        }

        /* renamed from: elapsedNow-UwyO8pc  reason: not valid java name */
        public long m1469elapsedNowUwyO8pc() {
            return Duration.m1519isInfiniteimpl(this.offset) ? Duration.m1539unaryMinusUwyO8pc(this.offset) : Duration.m1522minusLRDsOJo(DurationKt.toDuration(this.timeSource.read() - this.startedAt, this.timeSource.getUnit()), this.offset);
        }

        /* renamed from: plus-LRDsOJo  reason: not valid java name */
        public ComparableTimeMark m1474plusLRDsOJo(long j) {
            return new LongTimeMark(this.startedAt, this.timeSource, Duration.m1523plusLRDsOJo(this.offset, j), (DefaultConstructorMarker) null);
        }

        /* renamed from: minus-UwyO8pc  reason: not valid java name */
        public long m1472minusUwyO8pc(ComparableTimeMark comparableTimeMark) {
            Intrinsics.checkNotNullParameter(comparableTimeMark, "other");
            if (comparableTimeMark instanceof LongTimeMark) {
                LongTimeMark longTimeMark = (LongTimeMark) comparableTimeMark;
                if (Intrinsics.areEqual((Object) this.timeSource, (Object) longTimeMark.timeSource)) {
                    if (Duration.m1492equalsimpl0(this.offset, longTimeMark.offset) && Duration.m1519isInfiniteimpl(this.offset)) {
                        return Duration.Companion.m1589getZEROUwyO8pc();
                    }
                    long r2 = Duration.m1522minusLRDsOJo(this.offset, longTimeMark.offset);
                    long duration = DurationKt.toDuration(this.startedAt - longTimeMark.startedAt, this.timeSource.getUnit());
                    return Duration.m1492equalsimpl0(duration, Duration.m1539unaryMinusUwyO8pc(r2)) ? Duration.Companion.m1589getZEROUwyO8pc() : Duration.m1523plusLRDsOJo(duration, r2);
                }
            }
            throw new IllegalArgumentException("Subtracting or comparing time marks from different time sources is not possible: " + this + " and " + comparableTimeMark);
        }

        public boolean equals(Object obj) {
            return (obj instanceof LongTimeMark) && Intrinsics.areEqual((Object) this.timeSource, (Object) ((LongTimeMark) obj).timeSource) && Duration.m1492equalsimpl0(m1472minusUwyO8pc((ComparableTimeMark) obj), Duration.Companion.m1589getZEROUwyO8pc());
        }

        /* renamed from: effectiveDuration-UwyO8pc$kotlin_stdlib  reason: not valid java name */
        public final long m1468effectiveDurationUwyO8pc$kotlin_stdlib() {
            if (Duration.m1519isInfiniteimpl(this.offset)) {
                return this.offset;
            }
            DurationUnit unit = this.timeSource.getUnit();
            if (unit.compareTo(DurationUnit.MILLISECONDS) >= 0) {
                return Duration.m1523plusLRDsOJo(DurationKt.toDuration(this.startedAt, unit), this.offset);
            }
            long convertDurationUnit = DurationUnitKt.convertDurationUnit(1, DurationUnit.MILLISECONDS, unit);
            long j = this.startedAt;
            long j2 = j / convertDurationUnit;
            long j3 = j % convertDurationUnit;
            long j4 = this.offset;
            long r7 = Duration.m1508getInWholeSecondsimpl(j4);
            int r1 = Duration.m1510getNanosecondsComponentimpl(j4);
            int i = r1 / DurationKt.NANOS_IN_MILLIS;
            int i2 = r1 % DurationKt.NANOS_IN_MILLIS;
            long duration = DurationKt.toDuration(j3, unit);
            Duration.Companion companion = Duration.Companion;
            long r0 = Duration.m1523plusLRDsOJo(duration, DurationKt.toDuration(i2, DurationUnit.NANOSECONDS));
            Duration.Companion companion2 = Duration.Companion;
            long r02 = Duration.m1523plusLRDsOJo(r0, DurationKt.toDuration(j2 + ((long) i), DurationUnit.MILLISECONDS));
            Duration.Companion companion3 = Duration.Companion;
            return Duration.m1523plusLRDsOJo(r02, DurationKt.toDuration(r7, DurationUnit.SECONDS));
        }

        public int hashCode() {
            return Duration.m1515hashCodeimpl(m1468effectiveDurationUwyO8pc$kotlin_stdlib());
        }

        public String toString() {
            return "LongTimeMark(" + this.startedAt + DurationUnitKt.shortName(this.timeSource.getUnit()) + " + " + Duration.m1536toStringimpl(this.offset) + " (=" + Duration.m1536toStringimpl(m1468effectiveDurationUwyO8pc$kotlin_stdlib()) + "), " + this.timeSource + ')';
        }
    }

    public ComparableTimeMark markNow() {
        return new LongTimeMark(read(), this, Duration.Companion.m1589getZEROUwyO8pc(), (DefaultConstructorMarker) null);
    }
}
