package com.anotherdev.stackapp.rx;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmObject;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class RxRealmCache {

    private RxRealmCache() {
        throw new AssertionError("No instances");
    }

    public static <T extends RealmObject> Observable.Transformer<T,T> cache(Context context, final Class<T> clazz) {
        final Context appContext = context.getApplicationContext();
        return new Observable.Transformer<T,T>() {
            @Override
            public Observable<T> call(Observable<T> source) {
                return Observable
                        .create(new Observable.OnSubscribe<T>() {
                            @Override
                            public void call(Subscriber<? super T> subscriber) {
                                Realm realm = Realm.getInstance(appContext);
                                T cache = realm.where(clazz).findFirst();
                                if (cache != null) {
                                    subscriber.onNext(cache);
                                }
                                realm.close();
                                subscriber.onCompleted();
                            }
                        })
                        .concatWith(source)
                        .map(new Func1<T, T>() {
                            @Override
                            public T call(T t) {
                                Realm realm = Realm.getInstance(appContext);
                                realm.beginTransaction();
                                T managed = realm.copyToRealmOrUpdate(t);
                                realm.commitTransaction();
                                realm.close();
                                return managed;
                            }
                        });
            }
        };
    }
}
