package io.github.reline.realmsearchadaptersample;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.setDefaultConfiguration(new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .initialData(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.insertOrUpdate(new Cat("Fwuffy"));
                        realm.insertOrUpdate(new Cat("Fifi"));
                        realm.insertOrUpdate(new Cat("Fido"));
                        realm.insertOrUpdate(new Cat("Furry"));
                        realm.insertOrUpdate(new Cat("Feline"));
                    }
                })
                .build());
    }
}
