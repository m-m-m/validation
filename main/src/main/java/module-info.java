/*
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */
/**
 * Provides standard validators with NLS and typed builders.
 */
module io.github.mmm.validation.main {

  requires transitive io.github.mmm.validation;

  requires transitive io.github.mmm.nls;

  exports io.github.mmm.validation.collection;

  exports io.github.mmm.validation.main;

  exports io.github.mmm.validation.number;

  exports io.github.mmm.validation.string;

  exports io.github.mmm.validation.temporal;

  exports io.github.mmm.validation.temporal.instant;

  exports io.github.mmm.validation.temporal.localdate;

  exports io.github.mmm.validation.temporal.localdatetime;

  exports io.github.mmm.validation.temporal.localtime;

  exports io.github.mmm.validation.temporal.offsetdatetime;

  exports io.github.mmm.validation.temporal.offsettime;

  exports io.github.mmm.validation.temporal.zoneddatetime;
}
