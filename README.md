# A filterable Recycler Adapter powered by Realm

Built on [Realm's `RealmRecyclerViewAdapter`](https://github.com/realm/realm-android-adapters)

Adapted from [Thorben Primke's realm-searchview](https://github.com/thorbenprimke/realm-searchview)

##RealmSearchAdapter

The following list of parameters are available to customize the filtering.

* `filterKey`: The filterKey is required as it is the columnName that the results are filtered by.

* `useContains`: If true, uses `contains`, otherwise uses `beginsWith`.

* `useCaseSensitive`: If true, ensures that the filter is respecting casing. If false, ignores any casing.

* `sortAscending`: If true, ascending, otherwise descending.

* `sortKey`: The columnName by which the results should be sorted.

* `basePredicate`: The basePredicate is used to filter the results whenever the searchBar is empty.

The `RealmSearchAdapter` has two constructors. One that only takes the `filterKey` parameter and one that takes all parameters.
In addition, the adapter has to be provided with an OrderedRealmCollection of the objects to be filtered. It is used throughout the life of view to requery the results.

###Example
```java
OrderedRealmCollection<Person> people = realm.where(Person.class).greaterThan("age", 21).findAll();
MyRealmSearchAdapter adapter = new MyRealmSearchAdapter(context, people, "name");
myRecyclerView.setAdapter(adapter);
```

The adapter is now ready to be filtered

```java
adapter.filter("Nathan");
```

or with a SearchView

```java
searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
```