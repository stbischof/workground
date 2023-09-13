package mondrian.calc.impl;

import java.util.List;

import org.eclipse.daanse.olap.api.ResultStyle;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompiler;
import org.eclipse.daanse.olap.calc.api.compiler.ExpressionCompilerFactory;

import aQute.bnd.component.annotations.Component;
import aQute.bnd.component.annotations.ServiceScope;
import mondrian.olap.Evaluator;
import mondrian.olap.Validator;
import mondrian.util.CreationException;

@Component(scope = ServiceScope.SINGLETON, service = ExpressionCompilerFactory.class)
public class BetterExpressionCompilerFactory implements ExpressionCompilerFactory {

	@Override
	public ExpressionCompiler createExpressionCompiler(Evaluator evaluator, Validator validator,
			List<ResultStyle> resultStyles) throws CreationException {
		return new BetterExpCompiler(evaluator, validator, resultStyles);
	}

}
