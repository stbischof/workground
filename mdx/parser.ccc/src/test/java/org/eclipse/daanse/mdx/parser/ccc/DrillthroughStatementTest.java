package org.eclipse.daanse.mdx.parser.ccc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.daanse.mdx.parser.ccc.CubeTest.propertyWords;

import org.eclipse.daanse.mdx.model.api.DrillthroughStatement;
import org.eclipse.daanse.mdx.model.api.expression.NameObjectIdentifier;
import org.eclipse.daanse.mdx.model.api.expression.ObjectIdentifier;
import org.eclipse.daanse.mdx.model.api.select.SelectSubcubeClauseName;
import org.eclipse.daanse.mdx.parser.api.MdxParserException;
import org.junit.jupiter.api.Test;

class DrillthroughStatementTest {

	@Test
	void test1() throws MdxParserException {
		String mdx = """
				DRILLTHROUGH MAXROWS 10
				SELECT *
				FROM [Adventure Works]
				""";
		DrillthroughStatement clause = new MdxParserWrapper(mdx, propertyWords).parseDrillthroughStatement();
		assertThat(clause).isNotNull().isInstanceOf(DrillthroughStatement.class);
		assertThat(clause.firstRowSet()).isNotPresent();
		assertThat(clause.maxRows()).isPresent().contains(10);
		assertThat(clause.selectStatement()).isNotNull();
		assertThat(clause.selectStatement().selectSubcubeClause()).isNotNull()
				.isInstanceOf(SelectSubcubeClauseName.class);
		SelectSubcubeClauseName selectSubcubeClauseName = (SelectSubcubeClauseName) clause.selectStatement()
				.selectSubcubeClause();
		assertThat(selectSubcubeClauseName.cubeName().name()).isEqualTo("Adventure Works");
		assertThat(selectSubcubeClauseName.cubeName().quoting()).isEqualTo(ObjectIdentifier.Quoting.QUOTED);
		assertThat(clause.returnItems()).isNull();
	}

	@Test
	void test2() throws MdxParserException {
		String mdx = """
				DRILLTHROUGH MAXROWS 10
				FIRSTROWSET 1
				SELECT *
				FROM [Adventure Works]
				RETURN a
				""";
		DrillthroughStatement clause = new MdxParserWrapper(mdx, propertyWords).parseDrillthroughStatement();
		assertThat(clause).isNotNull().isInstanceOf(DrillthroughStatement.class);
		assertThat(clause.firstRowSet()).isPresent().contains(1);
		assertThat(clause.maxRows()).isPresent().contains(10);
		assertThat(clause.selectStatement()).isNotNull();
		assertThat(clause.selectStatement().selectSubcubeClause()).isNotNull()
				.isInstanceOf(SelectSubcubeClauseName.class);
		SelectSubcubeClauseName selectSubcubeClauseName = (SelectSubcubeClauseName) clause.selectStatement()
				.selectSubcubeClause();
		assertThat(selectSubcubeClauseName.cubeName().name()).isEqualTo("Adventure Works");
		assertThat(selectSubcubeClauseName.cubeName().quoting()).isEqualTo(ObjectIdentifier.Quoting.QUOTED);
		assertThat(clause.returnItems()).isNotNull().hasSize(1);
		assertThat(clause.returnItems().get(0).compoundId()).isNotNull();
		assertThat(clause.returnItems().get(0).compoundId().objectIdentifiers()).isNotNull().hasSize(1);
		assertThat(clause.returnItems().get(0).compoundId().objectIdentifiers().get(0))
				.isInstanceOf(NameObjectIdentifier.class);
		assertThat(((NameObjectIdentifier) clause.returnItems().get(0).compoundId().objectIdentifiers().get(0)).name())
				.isEqualTo("a");
		assertThat(clause.returnItems().get(0).compoundId().objectIdentifiers().get(0).quoting())
				.isEqualTo(ObjectIdentifier.Quoting.UNQUOTED);
	}

}
