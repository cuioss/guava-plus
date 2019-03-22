/**
 * Copyright 2018, InterComponentWare AG
 *
 * NO WARRANTIES OR ANY FURTHER CONDITIONS are implied as to the availability
 * of this source code.
 *
 * In case you receive a copy of this source code you are not permitted
 * to modify, use, or distribute this copy without agreement and an explicit
 * license issued by InterComponentWare AG.
 */
package de.icw.util.collect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Default implementation of {@link PartialCollection} based on {@link ArrayList}.
 *
 * @param <T>
 */
public class PartialArrayList<T extends Serializable> extends ArrayList<T> implements PartialCollection<T> {

    private static final long serialVersionUID = -7548645400982124555L;

    private final boolean moreAvailable;

    /**
     * Default constructor.
     *
     * @param list the list of entities to store.
     * @param moreAvailable the flag to store.
     */
    public PartialArrayList(Collection<T> list, boolean moreAvailable) {
        super(list);
        this.moreAvailable = moreAvailable;
    }

    @Override
    public boolean isMoreAvailable() {
        return moreAvailable;
    }

    /**
     * Static constructor for an empty instance.
     *
     * @param <T>
     * @return an empty {@link PartialArrayList}.
     */
    public static <T extends Serializable> PartialArrayList<T> emptyList() {
        return new PartialArrayList<>(Collections.emptyList(), false);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moreAvailable);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        PartialArrayList<?> other = (PartialArrayList<?>) obj;
        return moreAvailable == other.moreAvailable;
    }

    /**
     * Convenience method for creating an {@link PartialArrayList} as sublist for the given
     * collection with setting the {@link PartialCollection#isMoreAvailable()} automatically
     *
     * @param full the complete List to be wrapped, may be larger than the limit. If so, a sublist
     *            will be used.
     * @param limit to be checked against
     *
     * @param <T> identifying the type of contained elements
     * @return an newly created {@link PartialArrayList}.
     */
    public static <T extends Serializable> PartialArrayList<T> of(List<T> full, int limit) {
        if (null == full || full.isEmpty()) {
            return emptyList();
        }
        int actualSize = full.size();
        if (actualSize <= limit) {
            return new PartialArrayList<>(full, false);
        } else {
            return new PartialArrayList<>(full.subList(0, limit), true);
        }
    }

}
