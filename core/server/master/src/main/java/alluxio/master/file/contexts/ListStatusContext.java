/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.master.file.contexts;

import alluxio.conf.ServerConfiguration;
import alluxio.grpc.ListStatusPOptions;
import alluxio.util.FileSystemOptions;

import com.google.common.base.MoreObjects;

/**
 * Used to merge and wrap {@link ListStatusPOptions}.
 */
public class ListStatusContext extends OperationContext<ListStatusPOptions.Builder> {

  /**
   * Creates context with given option data.
   *
   * @param optionsBuilder options builder
   * @param callTracker client call tracker
   */
  private ListStatusContext(ListStatusPOptions.Builder optionsBuilder, CallTracker callTracker) {
    super(optionsBuilder, callTracker);
  }

  /**
   * @param optionsBuilder Builder for proto {@link ListStatusPOptions}
   * @return the instance of {@link ListStatusContext} with the given options
   */
  public static ListStatusContext create(ListStatusPOptions.Builder optionsBuilder) {
    return new ListStatusContext(optionsBuilder, CallTracker.NOOP_TRACKER);
  }

  /**
   * @param optionsBuilder Builder for proto {@link ListStatusPOptions}
   * @param callTracker client call tracker
   * @return the instance of {@link ListStatusContext} with the given options
   */
  public static ListStatusContext create(ListStatusPOptions.Builder optionsBuilder,
      CallTracker callTracker) {
    return new ListStatusContext(optionsBuilder, callTracker);
  }

  /**
   * Merges and embeds the given {@link ListStatusPOptions} with the corresponding master options.
   *
   * @param optionsBuilder Builder for proto {@link ListStatusPOptions} to merge with defaults
   * @return the instance of {@link ListStatusContext} with default values for master
   */
  public static ListStatusContext mergeFrom(ListStatusPOptions.Builder optionsBuilder) {
    ListStatusPOptions masterOptions =
        FileSystemOptions.listStatusDefaults(ServerConfiguration.global());
    ListStatusPOptions.Builder mergedOptionsBuilder =
        masterOptions.toBuilder().mergeFrom(optionsBuilder.build());
    return create(mergedOptionsBuilder);
  }

  /**
   * @return the instance of {@link ListStatusContext} with default values for master
   */
  public static ListStatusContext defaults() {
    return create(FileSystemOptions.listStatusDefaults(ServerConfiguration.global()).toBuilder());
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("ProtoOptions", getOptions().build())
        .toString();
  }
}
