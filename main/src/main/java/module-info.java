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

  exports io.github.mmm.validation.pattern;

  exports io.github.mmm.validation.range;

  exports io.github.mmm.validation.string;

  exports io.github.mmm.validation.time;

  exports io.github.mmm.validation.time.dayofweek;

  exports io.github.mmm.validation.time.instant;

  exports io.github.mmm.validation.time.localdate;

  exports io.github.mmm.validation.time.localdatetime;

  exports io.github.mmm.validation.time.localtime;

  exports io.github.mmm.validation.time.month;

  exports io.github.mmm.validation.time.offsetdatetime;

  exports io.github.mmm.validation.time.offsettime;

  exports io.github.mmm.validation.time.year;

  exports io.github.mmm.validation.time.zoneddatetime;
}
