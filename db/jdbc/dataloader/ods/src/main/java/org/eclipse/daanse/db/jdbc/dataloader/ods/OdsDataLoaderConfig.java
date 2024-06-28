/*
 * Copyright (c) 2022 Contributors to the Eclipse Foundation.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   SmartCity Jena, Stefan Bischof - initial
 *
 */
package org.eclipse.daanse.db.jdbc.dataloader.ods;

import org.eclipse.daanse.io.fs.watcher.api.propertytypes.FileSystemWatcherListenerProperties;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition
@FileSystemWatcherListenerProperties
public @interface OdsDataLoaderConfig {

    /**
     * @return Encoding default UTF-8
     */
    @AttributeDefinition(description = "encoding", options = { @Option(value = "UTF_8"), @Option(value = "US_ASCII"),
        @Option(value = "ISO_8859_1"), @Option(value = "UTF_16BE"), @Option(value = "UTF_16LE"),
        @Option(value = "UTF_16") })
    String encoding() default "UTF_8";

    /**
     * @return Clear Table Before Load Data
     */
    @AttributeDefinition(description = "clearTableBeforeLoad")
    boolean clearTableBeforeLoad() default true;

    /**
     * @return Batch Size. Use Batch operation if dialect support it
     */
    @AttributeDefinition(description = "batchSize")
    int batchSize() default 1000;

}
