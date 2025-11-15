package androidx.core.location;

import androidx.core.location.LocationManagerCompat;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LocationManagerCompat$GnssMeasurementsTransport$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.GnssMeasurementsTransport f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ LocationManagerCompat$GnssMeasurementsTransport$$ExternalSyntheticLambda1(LocationManagerCompat.GnssMeasurementsTransport gnssMeasurementsTransport, Executor executor, int i) {
        this.f$0 = gnssMeasurementsTransport;
        this.f$1 = executor;
        this.f$2 = i;
    }

    public final void run() {
        this.f$0.m16lambda$onStatusChanged$1$androidxcorelocationLocationManagerCompat$GnssMeasurementsTransport(this.f$1, this.f$2);
    }
}
