package androidx.core.os;

import android.os.BadParcelableException;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ParcelCompat {
    public static boolean readBoolean(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    public static <T> void readList(Parcel parcel, List<? super T> list, ClassLoader classLoader, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            Api33Impl.readList(parcel, list, classLoader, cls);
        } else {
            parcel.readList(list, classLoader);
        }
    }

    public static <T> ArrayList<T> readArrayList(Parcel parcel, ClassLoader classLoader, Class<? extends T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readArrayList(parcel, classLoader, cls);
        }
        return parcel.readArrayList(classLoader);
    }

    public static <T> Object[] readArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readArray(parcel, classLoader, cls);
        }
        return parcel.readArray(classLoader);
    }

    public static <T> SparseArray<T> readSparseArray(Parcel parcel, ClassLoader classLoader, Class<? extends T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readSparseArray(parcel, classLoader, cls);
        }
        return parcel.readSparseArray(classLoader);
    }

    public static <K, V> void readMap(Parcel parcel, Map<? super K, ? super V> map, ClassLoader classLoader, Class<K> cls, Class<V> cls2) {
        if (Build.VERSION.SDK_INT >= 34) {
            Api33Impl.readMap(parcel, map, classLoader, cls, cls2);
        } else {
            parcel.readMap(map, classLoader);
        }
    }

    public static <K, V> HashMap<K, V> readHashMap(Parcel parcel, ClassLoader classLoader, Class<? extends K> cls, Class<? extends V> cls2) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readHashMap(parcel, classLoader, cls, cls2);
        }
        return parcel.readHashMap(classLoader);
    }

    public static <T extends Parcelable> T readParcelable(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readParcelable(parcel, classLoader, cls);
        }
        T readParcelable = parcel.readParcelable(classLoader);
        if (readParcelable == null || cls.isInstance(readParcelable)) {
            return readParcelable;
        }
        throw new BadParcelableException("Parcelable " + readParcelable.getClass() + " is not a subclass of required class " + cls.getName() + " provided in the parameter");
    }

    public static <T> Parcelable.Creator<T> readParcelableCreator(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readParcelableCreator(parcel, classLoader, cls);
        }
        return Api30Impl.readParcelableCreator(parcel, classLoader);
    }

    @Deprecated
    public static <T> T[] readParcelableArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readParcelableArray(parcel, classLoader, cls);
        }
        T[] readParcelableArray = parcel.readParcelableArray(classLoader);
        if (cls.isAssignableFrom(Parcelable.class)) {
            return (Object[]) readParcelableArray;
        }
        T[] tArr = (Object[]) Array.newInstance(cls, readParcelableArray.length);
        int i = 0;
        while (i < readParcelableArray.length) {
            try {
                tArr[i] = cls.cast(readParcelableArray[i]);
                i++;
            } catch (ClassCastException unused) {
                throw new BadParcelableException("Parcelable at index " + i + " is not a subclass of required class " + cls.getName() + " provided in the parameter");
            }
        }
        return tArr;
    }

    public static <T> Parcelable[] readParcelableArrayTyped(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (Parcelable[]) Api33Impl.readParcelableArray(parcel, classLoader, cls);
        }
        return parcel.readParcelableArray(classLoader);
    }

    public static <T> List<T> readParcelableList(Parcel parcel, List<T> list, ClassLoader classLoader, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readParcelableList(parcel, list, classLoader, cls);
        }
        return Api29Impl.readParcelableList(parcel, list, classLoader);
    }

    public static <T extends Serializable> T readSerializable(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.readSerializable(parcel, classLoader, cls);
        }
        return parcel.readSerializable();
    }

    private ParcelCompat() {
    }

    static class Api29Impl {
        private Api29Impl() {
        }

        static <T extends Parcelable> List<T> readParcelableList(Parcel parcel, List<T> list, ClassLoader classLoader) {
            return parcel.readParcelableList(list, classLoader);
        }
    }

    static class Api30Impl {
        private Api30Impl() {
        }

        static Parcelable.Creator<?> readParcelableCreator(Parcel parcel, ClassLoader classLoader) {
            return parcel.readParcelableCreator(classLoader);
        }
    }

    static class Api33Impl {
        private Api33Impl() {
        }

        static <T extends Serializable> T readSerializable(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return (Serializable) parcel.readSerializable(classLoader, cls);
        }

        static <T extends Parcelable> T readParcelable(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return (Parcelable) parcel.readParcelable(classLoader, cls);
        }

        static <T> Parcelable.Creator<T> readParcelableCreator(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return parcel.readParcelableCreator(classLoader, cls);
        }

        static <T> T[] readParcelableArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return parcel.readParcelableArray(classLoader, cls);
        }

        static <T> List<T> readParcelableList(Parcel parcel, List<T> list, ClassLoader classLoader, Class<T> cls) {
            return parcel.readParcelableList(list, classLoader, cls);
        }

        static <T> void readList(Parcel parcel, List<? super T> list, ClassLoader classLoader, Class<T> cls) {
            parcel.readList(list, classLoader, cls);
        }

        static <T> ArrayList<T> readArrayList(Parcel parcel, ClassLoader classLoader, Class<? extends T> cls) {
            return parcel.readArrayList(classLoader, cls);
        }

        static <T> T[] readArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return parcel.readArray(classLoader, cls);
        }

        static <T> SparseArray<T> readSparseArray(Parcel parcel, ClassLoader classLoader, Class<? extends T> cls) {
            return parcel.readSparseArray(classLoader, cls);
        }

        static <K, V> void readMap(Parcel parcel, Map<? super K, ? super V> map, ClassLoader classLoader, Class<K> cls, Class<V> cls2) {
            parcel.readMap(map, classLoader, cls, cls2);
        }

        static <V, K> HashMap<K, V> readHashMap(Parcel parcel, ClassLoader classLoader, Class<? extends K> cls, Class<? extends V> cls2) {
            return parcel.readHashMap(classLoader, cls, cls2);
        }
    }
}
