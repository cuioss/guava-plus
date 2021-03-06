package de.icw.util.collect;

import java.io.Serializable;
import java.util.Collection;

/**
 * Represents a partial collection / sub-collection. It extends the {@link Collection} interface
 * with {@link #isMoreAvailable()} flag. This indicates that the original {@link Collection}
 * provides more data than the current {@link PartialCollection}
 *
 * @param <T> the type of the entity
 */
public interface PartialCollection<T extends Serializable> extends Collection<T>, Serializable {

    /**
     * @return {@code true} if more entities are available and ignored due to the given limit.
     */
    boolean isMoreAvailable();

}
