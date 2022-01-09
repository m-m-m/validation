/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation.collection;

import java.util.Collection;
import java.util.function.BiFunction;

import io.github.mmm.base.range.Range;
import io.github.mmm.base.range.RangeType;
import io.github.mmm.validation.AbstractValidator;
import io.github.mmm.validation.Validator;
import io.github.mmm.validation.main.ContainerValidatorBuilder;
import io.github.mmm.validation.main.ObjectValidatorBuilder;
import io.github.mmm.validation.main.ObjectValidatorBuilderFactory;

/**
 * {@link ContainerValidatorBuilder Validator builder} of for {@link Collection}.
 *
 * @param <E> the generic type of the {@link Collection#contains(Object) elements contained} in the {@link Collection}.
 * @param <V> the generic type of the value to {@link AbstractValidator#validate(Object) validate}.
 * @param <PARENT> the generic type of the {@link #and() parent builder}.
 * @param <SELF> the generic type of this builder itself (this).
 *
 * @since 1.0.0
 */
public abstract class AbstractCollectionValidatorBuilder<E, V extends Collection<E>, PARENT, SELF extends AbstractCollectionValidatorBuilder<E, V, PARENT, SELF>>
    extends ContainerValidatorBuilder<V, PARENT, SELF> {

  private ObjectValidatorBuilder<E, ? extends SELF, ?> subBuilder;

  /**
   * The constructor.
   *
   * @param parent the {@link #and() parent} builder.
   */
  public AbstractCollectionValidatorBuilder(PARENT parent) {

    super(parent);
  }

  /**
   * @see ValidatorCollectionSize
   *
   * @param range the {@link Range} to limit the {@link Collection#size() size} of the {@link Collection}.
   * @return this build instance for fluent API calls.
   */
  public SELF size(Range<Integer> range) {

    return add(new ValidatorCollectionSize(range));
  }

  /**
   * @see #size(Range)
   *
   * @param min the minimum {@link Collection#size() size} allowed.
   * @param max the maximum {@link Collection#size() size} allowed.
   * @return this build instance for fluent API calls.
   */
  public SELF size(int min, int max) {

    return size(RangeType.of(Integer.valueOf(min), Integer.valueOf(max)));
  }

  /**
   * @see #size(Range)
   *
   * @param max the maximum {@link Collection#size() size} allowed.
   * @return this build instance for fluent API calls.
   */
  public SELF max(int max) {

    return size(0, max);
  }

  @Override
  public SELF range(String min, String max) {

    if ((min != null) || (max != null)) {
      Integer iMin = null;
      if (min != null) {
        iMin = Integer.valueOf(min);
      }
      Integer iMax = null;
      if (max != null) {
        iMax = Integer.valueOf(max);
      }
      add(new ValidatorCollectionSize(RangeType.of(iMin, iMax)));
    }
    return self();
  }

  /**
   * Creates a new {@link ObjectValidatorBuilder builder} for the {@link AbstractValidator validators} to invoke for
   * each {@link Collection#contains(Object) element contained} in the {@link Collection}.<br/>
   * Use {@link #and()} to return to this builder after the sub-builder is complete.<br/>
   * A typical usage looks like this:
   *
   * <pre>
   * [...].with((f, v) -> f.create(v)).[...].and().[...].build()
   * </pre>
   *
   * @param <SUB> the generic type of the returned sub-builder.
   * @param factory lambda function used to create the returned sub-builder by calling the according {@code create}
   *        method on the supplied {@link ObjectValidatorBuilderFactory} with the given dummy element.
   * @return the new sub-builder.
   */
  public <SUB extends ObjectValidatorBuilder<E, ? extends SELF, ?>> SUB with(
      BiFunction<ObjectValidatorBuilderFactory<SELF>, E, SUB> factory) {

    if (this.subBuilder != null) {
      throw new IllegalStateException("subBuilder already exists!");
    }
    SUB sub = factory.apply(getSubFactory(), null);
    this.subBuilder = sub;
    return sub;
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public Validator<? super V> build() {

    if (this.subBuilder != null) {
      add(new ValidatorCollectionElements(getValidators(this.subBuilder)));
    }
    return super.build();
  }

}
