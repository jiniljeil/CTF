package androidx.core.location;

import android.location.GnssMeasurementsEvent;
import androidx.core.location.LocationManagerCompat;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LocationManagerCompat$GnssMeasurementsTransport$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.GnssMeasurementsTransport f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ GnssMeasurementsEvent f$2;

    public /* synthetic */ LocationManagerCompat$GnssMeasurementsTransport$$ExternalSyntheticLambda0(LocationManagerCompat.GnssMeasurementsTransport gnssMeasurementsTransport, Executor executor, GnssMeasurementsEvent gnssMeasurementsEvent) {
        this.f$0 = gnssMeasurementsTransport;
        this.f$1 = executor;
        this.f$2 = gnssMeasurementsEvent;
    }

    public final void run() {
        this.f$0.m15lambda$onGnssMeasurementsReceived$0$androidxcorelocationLocationManagerCompat$GnssMeasurementsTransport(this.f$1, this.f$2);
    }
}
