// Copyright 2016 Twitter. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.apache.storm.metric.api;

public class ReducedMetric<T> implements IMetric {
  private final IReducer<T> reducer;
  private T accumulator;

  public ReducedMetric(IReducer<T> reducer) {
    this.reducer = reducer;
    this.accumulator = this.reducer.init();
  }

  public void update(T value) {
    accumulator = reducer.reduce(accumulator, value);
  }

  public Object getValueAndReset() {
    Object ret = reducer.extractResult(accumulator);
    accumulator = reducer.init();
    return ret;
  }
}
