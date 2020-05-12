package Commands;

import Errors.WrongArgumentErrors.WrongArgumentLengthError;
import Errors.WrongArgumentErrors.WrongArgumentLengthFullError;
import Expectations.Argument;
import Utils.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Command implements Serializable {
	private final List<Argument> arguments;
	private final int argumentsLength;
	
	protected Context context;
	protected String[] commandArguments;
	
	public Command() {
		arguments = new ArrayList<>();
		addArgumentValidators(this.arguments);
		
		this.argumentsLength = this.arguments.size();
	}
	
	public void validateArguments(String[] commandArguments) {
		int argumentsLengthExpected = this.argumentsLength;
		int argumentsLengthActual = commandArguments.length;
		
		if (argumentsLengthExpected != argumentsLengthActual) {
			if (argumentsLengthExpected == 0)
				throw new WrongArgumentLengthError(argumentsLengthExpected, argumentsLengthActual);
			else
				throw new WrongArgumentLengthFullError(argumentsLengthExpected, argumentsLengthActual, getArgumentsDescription());
		}
		
		for (int i = 0; i < argumentsLengthActual; i++)
			this.arguments.get(i).checkArgument(commandArguments[i]);
		
		this.commandArguments = commandArguments;
	}
	
	public void printDescriptionAndExecute(Context context) {
		this.context = context;
		System.out.println(getDescription());
		this.execute();
	}
	
	public abstract void execute();
	
	public String getArgumentsDescription() {
		StringBuilder stringBuilder = new StringBuilder();
		
		for (Argument argument : this.arguments) {
			stringBuilder.append(" {");
			stringBuilder.append(argument.getName());
			stringBuilder.append(": ");
			stringBuilder.append(argument.getExpectationsMessage());
			stringBuilder.append("}");
			stringBuilder.append("; ");
		}
		
		if (! this.arguments.isEmpty())
			stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
		
		return stringBuilder.toString();
	}
	
	public String getNameWithArgumentsDescription() {
		return getName() + getArgumentsDescription();
	}
	
	public String getFullInformation() {
		return getNameWithArgumentsDescription() +
				": " +
				this.getDescription();
	}
	
	public abstract String getName();
	
	public abstract String getDescription();
	
	protected void addArgumentValidators(List<Argument> arguments) {
	}
}
