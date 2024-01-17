package org.eclipse.daanse.db.jdbc.dataloader.csv;

import org.eclipse.daanse.util.io.watcher.api.PathListenerConfig;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition()
@PathListenerConfig
public @interface CsvDataLoaderConfig {

	/**
	 * @return Null Value
	 */
	@AttributeDefinition(description = "nullValue")
	String nullValue() default "NULL";

	/**
	 * @return Quote
	 */
	@AttributeDefinition(description = "quoteCharacter")
	char quoteCharacter() default '"';

	/**
	 * @return Delimiter
	 */
	@AttributeDefinition(description = "fieldSeparator")
    char fieldSeparator() default ',';

	/**
	 * @return Encoding default UTF-8
	 */
	@AttributeDefinition(description = "encoding", options = { @Option(value = "UTF-8"), @Option(value = "US_ASCII"),
			@Option(value = "ISO_8859_1"), @Option(value = "UTF_16BE"), @Option(value = "UTF_16LE"),
			@Option(value = "UTF_16") })
	String encoding() default "UTF-8";

	/**
	 * @return Clear Table Before Load Data
	 */
	@AttributeDefinition(description = "clearTableBeforeLoad")
	boolean clearTableBeforeLoad() default true;

	/**
	 * @return Batch Size. Use Batch operation if dialect support it
	 */
	@AttributeDefinition(description = "batchSize", defaultValue = "1000")
	int batchSize() default 1000;

    /**
     * @return Skip Empty Lines
     */
    @AttributeDefinition(description = "skipEmptyLines", defaultValue = "true")
    boolean skipEmptyLines() default true;

    /**
     * @return Comment Character
     */
    @AttributeDefinition(description = "commentCharacter", defaultValue = "#")
    char commentCharacter() default '#';

    /**
     * @return Ignore Different Field Count
     */
    @AttributeDefinition(description = "commentCharacter", defaultValue = "true")
    boolean ignoreDifferentFieldCount() default true;
}
