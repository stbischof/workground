package org.eclipse.daanse.mdx.parser.tck;

import org.eclipse.daanse.mdx.model.api.DrillthroughStatement;
import org.eclipse.daanse.mdx.model.api.ExplainStatement;
import org.eclipse.daanse.mdx.model.api.SelectStatement;
import org.eclipse.daanse.mdx.parser.api.MdxParserException;
import org.eclipse.daanse.mdx.parser.api.MdxParserProvider;
import org.junit.jupiter.api.Test;
import org.osgi.service.component.annotations.RequireServiceComponentRuntime;
import org.osgi.test.common.annotation.InjectService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.daanse.mdx.parser.tck.CubeTest.propertyWords;

@RequireServiceComponentRuntime
class ExplainStatementTest {

	@Test
	void test1(@InjectService MdxParserProvider mdxParserProvider) throws MdxParserException {
		String mdx = """
				EXPLAIN PLAN FOR
				SELECT [Customer].[Gender].[Gender].Membmers ON COLUMNS,
				         {[Customer].[Customer].[Aaron A. Allen],
				          [Customer].[Customer].[Abigail Clark]} ON ROWS
				   FROM [Adventure Works]
				   WHERE [Measures].[Internet Sales Amount]
				""";
		ExplainStatement explainStatement = mdxParserProvider.newParser(mdx, propertyWords).parseExplainStatement();
		assertThat(explainStatement).isNotNull();
		assertThat(explainStatement.mdxStatement()).isNotNull().isInstanceOf(SelectStatement.class);
	}

	@Test
	void test2(@InjectService MdxParserProvider mdxParserProvider) throws MdxParserException {
		String mdx = """
				EXPLAIN PLAN FOR
				DRILLTHROUGH MAXROWS 10
				FIRSTROWSET 1
				SELECT *
				FROM [Adventure Works]
				RETURN a
				""";
		ExplainStatement explainStatement = mdxParserProvider.newParser(mdx, propertyWords).parseExplainStatement();
		assertThat(explainStatement).isNotNull();
		assertThat(explainStatement.mdxStatement()).isNotNull().isInstanceOf(DrillthroughStatement.class);
	}
}
