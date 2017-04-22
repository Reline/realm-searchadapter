package xyz.projectplay.realmsearchadapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import io.realm.Case;
import io.realm.OrderedRealmCollection;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmRecyclerViewAdapter;
import io.realm.Sort;

public abstract class RealmSearchAdapter<T extends RealmObject, VH extends RecyclerView.ViewHolder>
        extends RealmRecyclerViewAdapter<T, VH> {

    private OrderedRealmCollection<T> originalData;
    private String filterKey;
    private boolean useContains;
    private Case casing;
    private Sort sortOrder;
    private String sortKey;
    private String basePredicate;

    /**
     * Creates a {@link xyz.projectplay.realmsearchadapter.RealmSearchAdapter with only the filter columnKey. The defaults are:
     * - useContains: true
     * - casing: insensitive
     * - sortOrder: ascending
     * - sortKey: filterKey
     * - basePredicate: not set
     */
    public RealmSearchAdapter(@Nullable OrderedRealmCollection<T> data, @NonNull String filterKey) {
        this(data, filterKey, true, Case.INSENSITIVE, Sort.ASCENDING, filterKey, null);
    }

    /**
     * Creates a {@link xyz.projectplay.realmsearchadapter.RealmSearchAdapter} with parameters for all options.
     */
    public RealmSearchAdapter(
            @Nullable OrderedRealmCollection<T> data,
            @NonNull String filterKey,
            boolean useContains,
            Case casing,
            Sort sortOrder,
            String sortKey,
            String basePredicate) {
        super(data, true);
        this.originalData = data;
        this.filterKey = filterKey;
        this.useContains = useContains;
        this.casing = casing;
        this.sortOrder = sortOrder;
        this.sortKey = sortKey;
        this.basePredicate = basePredicate;
    }

    public void filter(String input) {
        RealmQuery<T> where = originalData.where();
        OrderedRealmCollection<T> data;
        if (input.isEmpty() && basePredicate != null) {
            if (useContains) {
                where = where.contains(filterKey, basePredicate, casing);
            } else {
                where = where.beginsWith(filterKey, basePredicate, casing);
            }
        } else if (!input.isEmpty()) {
            if (useContains) {
                where = where.contains(filterKey, input, casing);
            } else {
                where = where.beginsWith(filterKey, input, casing);
            }
        }

        if (sortKey == null) {
            data = where.findAll();
        } else {
            data = where.findAllSorted(sortKey, sortOrder);
        }
        super.updateData(data);
    }

    /**
     * The columnKey by which the results are filtered.
     */
    public void setFilterKey(String filterKey) {
        if (filterKey == null) {
            throw new IllegalStateException("The filterKey cannot be null.");
        }
        this.filterKey = filterKey;
    }

    /**
     * If true, {@link RealmQuery#contains} is used else {@link RealmQuery#beginsWith}.
     */
    public void setUseContains(boolean useContains) {
        this.useContains = useContains;
    }

    /**
     * Sets if the filtering is case sensitive or case insensitive.
     */
    public void setCasing(Case casing) {
        this.casing = casing;
    }

    /**
     * Sets if the sort order is ascending or descending.
     */
    public void setSortOrder(Sort sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * Sets the sort columnKey.
     */
    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    /**
     * Sets the basePredicate which is used filters the results when the search query is empty.
     */
    public void setBasePredicate(String basePredicate) {
        this.basePredicate = basePredicate;
    }

    @Override
    public void updateData(@Nullable OrderedRealmCollection<T> data) {
        originalData = data;
    }
}
