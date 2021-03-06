/*
 * Copyright 2012-2014 Continuuity, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.continuuity.tephra.persist;

import java.io.Closeable;
import java.io.IOException;

/**
 * Common interface for transaction log writers used by classes extending {@link AbstractTransactionLog}.
 */
public interface TransactionLogWriter extends Closeable {
  /**
   * Adds a new transaction entry to the log.  Note that this does not guarantee that the entry has been flushed
   * to persistent storage until {@link #sync()} has been called.
   *
   * @param entry The transaction edit to append.
   * @throws IOException If an error occurs while writing the edit to storage.
   */
  void append(AbstractTransactionLog.Entry entry) throws IOException;

  /**
   * Syncs any pending transaction edits added through {@link #append(AbstractTransactionLog.Entry)},
   * but not yet flushed to durable storage.
   *
   * @throws IOException If an error occurs while flushing the outstanding edits.
   */
  void sync() throws IOException;
}
