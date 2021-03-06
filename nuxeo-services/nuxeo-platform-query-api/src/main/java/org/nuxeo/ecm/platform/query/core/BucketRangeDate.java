/*
 * (C) Copyright 2014 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *     Benoit Delbosc
 */
package org.nuxeo.ecm.platform.query.core;

import org.joda.time.DateTime;
import org.nuxeo.ecm.platform.query.api.Bucket;

/**
 * Immutable bucket for date range.
 *
 * @since 6.0
 */
public class BucketRangeDate implements Bucket {

    private final BucketRange range;

    // joda DateTime are immutables
    private final DateTime fromDate;

    private final DateTime toDate;

    public BucketRangeDate(String key, DateTime from, DateTime to, long docCount) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        };
        // fromDate.
        range = new BucketRange(key, from != null ? from.getMillis() : null, to != null ? to.getMillis() : null,
                docCount);
        fromDate = from;
        toDate = to;
    }

    @Override
    public String getKey() {
        return range.getKey();
    }

    @Override
    public long getDocCount() {
        return range.getDocCount();
    }

    public Double getFrom() {
        return range.getFrom();
    }

    /**
     * @return null if there are no minimal limit
     */
    public DateTime getFromAsDate() {
        return fromDate;
    }

    public Double getTo() {
        return range.getTo();
    }

    /**
     * @return null if there are no maximal limit
     */
    public DateTime getToAsDate() {
        return toDate;
    }

    @Override
    public String toString() {
        return String.format("BucketRangeDate(%s, %d, %s, %s)", getKey(), getDocCount(), fromDate, toDate);
    }

}
