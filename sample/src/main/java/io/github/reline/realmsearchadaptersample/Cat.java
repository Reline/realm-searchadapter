package io.github.reline.realmsearchadaptersample;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class Cat implements RealmModel {

    @PrimaryKey
    String name;

    public Cat() {
        // no-op
    }

    public Cat(String name) {
        this.name = name;
    }

}
