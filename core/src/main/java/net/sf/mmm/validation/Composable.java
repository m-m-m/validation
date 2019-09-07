/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.validation;

import java.util.Iterator;

import net.sf.mmm.validation.impl.ComposableIterator;

/**
 * Interface for an object that may contain {@link #getChild(int) children}.
 *
 * @param <C> type of the children. Should typically be the sub-type implementing this interface itself.
 * @since 1.0.0
 */
public interface Composable<C> extends Iterable<C> {

  /**
   * @return the number of contained {@link #getChild(int) children}.
   * @see #getChild(int)
   * @see java.util.Collection#size()
   */
  default int getChildCount() {

    return 0;
  }

  /**
   * Gets the child-object at the given {@code index}.
   *
   * @param index is the index of the {@link Validator} to get.
   * @return the requested {@link Validator}.
   * @throws IndexOutOfBoundsException if the given {@code index} is not in the range from {@code 0} to
   *         <code>{@link #getChildCount() child-count} - 1</code>.
   * @see java.util.List#get(int)
   */
  default C getChild(int index) {

    throw new IndexOutOfBoundsException(index);
  }

  @Override
  default Iterator<C> iterator() {

    return new ComposableIterator<>(this);
  }

}
