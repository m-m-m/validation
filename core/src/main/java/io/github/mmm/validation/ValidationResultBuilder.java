/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package io.github.mmm.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder to {@link #add(ValidationResult) collect} {@link ValidationResult}s and {@link #build(String) build} the
 * aggregated end-result.
 */
public class ValidationResultBuilder {

  private ValidationResult result;

  private List<ValidationResult> failureList;

  /**
   * The constructor.
   */
  public ValidationResultBuilder() {

    super();
    this.result = ValidationResultValid.getInstance();
  }

  /**
   * @param validationResult the {@link ValidationResult} to collect and compose.
   */
  public void add(ValidationResult validationResult) {

    if ((validationResult == null) || validationResult.isValid()) {
      return;
    }
    if (this.failureList == null) {
      if (this.result.isValid()) {
        this.result = validationResult;
      } else {
        this.failureList = new ArrayList<>();
        this.failureList.add(this.result);
      }
    }
    if (this.failureList != null) {
      this.failureList.add(validationResult);
    }

  }

  /**
   * @param valueSource the {@link ValidationResult#getSource() source}.
   * @return the final {@link ValidationResult}.
   */
  public ValidationResult build(String valueSource) {

    if (this.failureList != null) {
      this.result = new ComposedValidationFailure(valueSource,
          this.failureList.toArray(new ValidationResult[this.failureList.size()]));
    }
    return this.result;
  }

}
