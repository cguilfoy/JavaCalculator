/* ********* Java Calculator *************
 * ** Created by Chris Guilfoy ***********
 * ***************************************
 * ***************************************
 * This is a calculator created using JavaFX
 * as a GUI. At the moment it is capable
 * of basic arithmetic and can execute proper
 * order of operations.
 */

import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaCalculator extends Application {
	
	// Creation of the Buttons and Labels for the calculator
	private Label formulaLabel = new Label();
	private Label inputLabel = new Label();
	private Button backspaceButton = new Button("<-");
	private Button clearAllButton = new Button("CE");
	private Button clearButton = new Button("C");
	private Button divideButton = new Button("/");
	private Button additionButton = new Button("+");
	private Button subtractionButton = new Button("-");
	private Button multiplyButton = new Button("*");
	private Button equalsButton = new Button("=");
	private Button decimalButton = new Button(".");
	private Button negativeButton = new Button("+/-");
	private Button oneButton = new Button("1");
	private Button twoButton = new Button("2");
	private Button threeButton = new Button("3");
	private Button fourButton = new Button("4");
	private Button fiveButton = new Button("5");
	private Button sixButton = new Button("6");
	private Button sevenButton = new Button("7");
	private Button eightButton = new Button("8");
	private Button nineButton = new Button("9");
	private Button zeroButton = new Button("0");
	
	/* Creation of the arrays, Strings, and char variables needed to run
	 * the program. numList will hold the number values entered, operatorList
	 * will hold the operators entered, numString will hold the current
	 * number being entered, nextNumber holds the next number to be entered
	 * into numString, currentFormula is what the formula looks like in 
	 * String form displayed on the calculators screen, and lastEntry
	 * is a way of checking whether a number or operator was last
	 * entered into the calculator to stop errors from happening
	 */
	
	static ArrayList<Double> numList = new ArrayList<Double>();
	static ArrayList<Character> operatorList = new ArrayList<Character>();
	static String numString = "";
	static String nextNumber = "";
	static String currentFormula = "";
	static char lastEntry = ' ';
	
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		
		/* Creates 3 Panes for the calculator. One Pane is for all of the buttons
		 * in a GridPane. Another GridPane holds the 2 displays at the top. 
		 * Finally the BorderPane acts as an anchor for the other 2 Panes.
		 */
		
		GridPane ioGrid = new GridPane();
		GridPane calculatorGrid = new GridPane();
		BorderPane mainCalcPane = new BorderPane();
		
		// Sets the display to be on top, and the buttons on bottom
		mainCalcPane.setPadding(new Insets(5, 5, 5, 5));
		mainCalcPane.setTop(ioGrid);
		mainCalcPane.setBottom(calculatorGrid);
		
		//Changes the colors of the backgrounds to look more calculator like
		calculatorGrid.setStyle("-fx-background-color: #000000;");
		ioGrid.setStyle("-fx-background-color: #72cc4e;");
		mainCalcPane.setStyle("-fx-background-color: #000000;");
		
		
		formulaLabel.setFont(Font.font("Arial Narrow", 18));
		ioGrid.add(formulaLabel, 0, 0);
		inputLabel.setFont(Font.font("Arial Narrow", 18));
		ioGrid.add(inputLabel, 0, 1);
		ioGrid.setAlignment(Pos.BASELINE_RIGHT);
		
		/* Adds all of the buttons to their corresponding Pane, sets their
		 * font to Tahoma, and the size to 18 point. Also sets their physical
		 * size to be the size of the larger button in the same column for
		 * conformity among button sizes.
		 */
		
	    calculatorGrid.setHgap(5);
	    calculatorGrid.setVgap(5);
	    backspaceButton.setFont(Font.font("Tahoma", 18));
	    backspaceButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(backspaceButton, 0, 0);
	    clearAllButton.setFont(Font.font("Tahoma", 18));
	    clearAllButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(clearAllButton, 1, 0);
	    clearButton.setFont(Font.font("Tahoma", 18));
	    clearButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(clearButton, 2, 0);
	    divideButton.setFont(Font.font("Tahoma", 18));
	    divideButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(divideButton, 3, 0);
	    oneButton.setFont(Font.font("Tahoma", 18));
	    oneButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(oneButton, 0, 1);
	    twoButton.setFont(Font.font("Tahoma", 18));
	    twoButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(twoButton, 1, 1);
	    threeButton.setFont(Font.font("Tahoma", 18));
	    threeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(threeButton, 2,  1);
	    additionButton.setFont(Font.font("Tahoma", 18));
	    additionButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(additionButton, 3,  1);
	    fourButton.setFont(Font.font("Tahoma", 18));
	    fourButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(fourButton, 0,  2);
	    fiveButton.setFont(Font.font("Tahoma", 18));
	    fiveButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(fiveButton, 1,  2);
	    sixButton.setFont(Font.font("Tahoma", 18));
	    sixButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(sixButton, 2,  2);
	    subtractionButton.setFont(Font.font("Tahoma", 18));
	    subtractionButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(subtractionButton, 3,  2);
	    sevenButton.setFont(Font.font("Tahoma", 18));
	    sevenButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(sevenButton, 0,  3);
	    eightButton.setFont(Font.font("Tahoma", 18));
	    eightButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(eightButton, 1,  3);
	    nineButton.setFont(Font.font("Tahoma", 18));
	    nineButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(nineButton, 2,  3);
	    multiplyButton.setFont(Font.font("Tahoma", 18));
	    multiplyButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(multiplyButton, 3,  3);
	    negativeButton.setFont(Font.font("Tahoma", 18));
	    negativeButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(negativeButton, 0,  4);
	    zeroButton.setFont(Font.font("Tahoma", 18));
	    zeroButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(zeroButton, 1, 4);
	    decimalButton.setFont(Font.font("Tahoma", 18));
	    decimalButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(decimalButton, 2,  4);
	    equalsButton.setFont(Font.font("Tahoma", 18));
	    equalsButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    calculatorGrid.add(equalsButton, 3,  4);
	    calculatorGrid.setAlignment(Pos.BOTTOM_CENTER);
	    
	    //Sets the scene's size, gives it a title, makes the background black,
	    //and displays it
	    Scene scene = new Scene(mainCalcPane, 200, 250);
	    scene.setFill(Color.BLACK);
	    primaryStage.setTitle("Java Calculator");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	    
	    /* The following list of buttons sets the event handlers for each of the
	     * buttons on the calculator when they are clicked by the mouse, 
	     * not to be confused with when keys on the keyboard are pressed.
	     * That event handler comes after these. The number keys when pressed
	     * will add whatever corresponding number, decimal, or negative sign
	     * to numString and then report the updated numString to the 
	     * calculator screen so they user can see what number they are typing.
	     * When an operator is pressed, numString is turned into a double and
	     * inserted into numList to be stored for later calculation. The 
	     * operator that was pushed is added to operatorList for later use.
	     * The clear button clears whatever is in numString. The clearAll
	     * button will clear everything that is in the arrays and all 
	     * current Strings. The equalsButton first places whatever is in 
	     * numString into numList, then goes through 2 while loops.
	     * The first while loop will calculate any multiplication and
	     * division problems under the control of a for loop. The second
	     * while loop then calculates any addition or subtraction with 
	     * another for loop. The final result is displayed on the screen,
	     * and the arrays / Strings are cleared for another calculation.
	     */
	    
	    oneButton.setOnAction(e -> {
	    	
	    	nextNumber = "1";
	    	numString = numString.concat(nextNumber);
	    	inputLabel.setText(numString);
	    	lastEntry = 'n';
	    });
	    
	    twoButton.setOnAction(e -> {
	    	
	    	nextNumber = "2";
	    	numString = numString.concat(nextNumber);
	    	inputLabel.setText(numString);
	    	lastEntry = 'n';
	    	
	    });
	    
	    threeButton.setOnAction(e -> {
	    	
	    	nextNumber = "3";
	    	numString = numString.concat(nextNumber);
	    	inputLabel.setText(numString);
	    	lastEntry = 'n';
	    	
	    });
	    
	    fourButton.setOnAction(e -> {
	    	
	    	nextNumber = "4";
	    	numString = numString.concat(nextNumber);
	    	inputLabel.setText(numString);
	    	lastEntry = 'n';
	    	
	    });
	    
	    fiveButton.setOnAction(e -> {
	    	
	    	nextNumber = "5";
	    	numString = numString.concat(nextNumber);
	    	inputLabel.setText(numString);
	    	lastEntry = 'n';
	    	
	    });
	    
	    sixButton.setOnAction(e -> {
	    	
	    	nextNumber = "6";
	    	numString = numString.concat(nextNumber);
	    	inputLabel.setText(numString);
	    	lastEntry = 'n';
	    	
	    });
	    
	    sevenButton.setOnAction(e -> {
	    	
	    	nextNumber = "7";
	    	numString = numString.concat(nextNumber);
	    	inputLabel.setText(numString);
	    	lastEntry = 'n';
	    	
	    });
	    
	    eightButton.setOnAction(e -> {
	    	
	    	nextNumber = "8";
	    	numString = numString.concat(nextNumber);
	    	inputLabel.setText(numString);
	    	lastEntry = 'n';
	    	
	    });
	
	    nineButton.setOnAction(e -> {
	    	
	    	nextNumber = "9";
	    	numString = numString.concat(nextNumber);
	    	inputLabel.setText(numString);
	    	lastEntry = 'n';
	    	
	    });
	    
	    zeroButton.setOnAction(e -> {
	    	
	    	nextNumber = "0";
	    	numString = numString.concat(nextNumber);
	    	inputLabel.setText(numString);
	    	lastEntry = 'n';
	    	
	    });
	
	    decimalButton.setOnAction(e -> {
	    	
	    	if(!numString.contains(".")){
	    		
	    		nextNumber = ".";
		    	numString = numString.concat(nextNumber);
		    	inputLabel.setText(numString);
	    	}
	    	
	    });
	  
	    negativeButton.setOnAction(e -> {
	    	
	    	if(!numString.contains("-")){
	    		
	    		nextNumber = "-";
		    	numString = numString.concat(nextNumber);
		    	inputLabel.setText(numString);
	    	}
	    });
	
	    backspaceButton.setOnAction(e -> {
	    	
	    	if (numString.length() > 0) {
	    		numString = numString.substring(0, numString.length() - 1);
		    	inputLabel.setText(numString);
	    	}
	    });
	 
	    clearAllButton.setOnAction(e -> {
	    	try {
	    		start(primaryStage);
	    	}
	    	catch (Exception el){
	    		el.printStackTrace();
	    	}
	    	
	    	numString = "";
	    	inputLabel.setText("");
	    	formulaLabel.setText("");
	    	numList.clear();
	    	operatorList.clear();
	    	currentFormula = "";
	    });
	   
	    clearButton.setOnAction(e -> {
	    	inputLabel.setText("");
	    	numString = "";
	    });
	    
	    additionButton.setOnAction(e -> {
	    	
	    	if (lastEntry == 'n') {
	    		double currentNumber = Double.parseDouble(numString);
	    		numList.add(currentNumber);
	    		operatorList.add('+');
	    		currentFormula = currentFormula.concat(numString + "+");
	    		formulaLabel.setText(currentFormula);
	    		numString = "";
	    	}
	    	
	    	lastEntry = 'o';
	    });
	 
	    subtractionButton.setOnAction(e -> {
	    	
	    	if (lastEntry == 'n') {
	    		double currentNumber = Double.parseDouble(numString);
		    	numList.add(currentNumber);
		    	operatorList.add('-');
		    	currentFormula = currentFormula.concat(numString + "-");
		    	formulaLabel.setText(currentFormula);
		    	numString = "";
	    	}
	    	
	    	lastEntry = 'o';
	    	
	    });
	    
	    divideButton.setOnAction(e -> {
	    	
	    	if (lastEntry == 'n'){
	    		double currentNumber = Double.parseDouble(numString);
		    	numList.add(currentNumber);
		    	operatorList.add('/');
		    	currentFormula = currentFormula.concat(numString + "/");
		    	formulaLabel.setText(currentFormula);
		    	numString = "";
	    	}
	    	
	    	lastEntry = 'o';
	    	
	    });
	    
	    multiplyButton.setOnAction(e -> {
	    	
	    	if (lastEntry == 'n'){
	    		double currentNumber = Double.parseDouble(numString);
		    	numList.add(currentNumber);
		    	operatorList.add('*');
		    	currentFormula = currentFormula.concat(numString + "*");
		    	formulaLabel.setText(currentFormula);
		    	numString = "";
	    	}
	    	
	    	lastEntry = 'o';
	    	
	    });
	    
	    equalsButton.setOnAction(e -> {
	    	
	    	if (lastEntry == 'n') {
	    		double currentNumber = Double.parseDouble(numString);
		    	numList.add(currentNumber);
	    	}
	    	
	    	if (lastEntry == 'o') {
	    		operatorList.remove(operatorList.size() - 1);
	    	}
	    	
	    	while (operatorList.size() > 0) {
	    		
	    			while (operatorList.contains('*') || operatorList.contains('/'))
	    			{
	    				for (int j = 0; j < operatorList.size(); j++){
	    					if (operatorList.get(j) == '*'){
	    						int position = 0;
				    			position = operatorList.indexOf('*');
				    			double result = numList.get(position) * numList.get(position + 1);
				    			numList.set(position, result);
				    			numList.remove(position + 1);
				    			operatorList.remove(position);
	    					}
	    					else if (operatorList.get(j) == '/'){
	    						int position = 0;
				    			position = operatorList.indexOf('/');
				    			double result = numList.get(position) / numList.get(position + 1);
				    			numList.set(position, result);
				    			numList.remove(position + 1);
				    			operatorList.remove(position);
	    					}
	    				}
	    			}
	    			
	    			while (operatorList.contains('+') || operatorList.contains('-')) {
	    				for (int k = 0; k < operatorList.size(); k++) {
	    					if (operatorList.get(k) == '+') {
	    						int position = 0;
		    	    			position = operatorList.indexOf('+');
		    	    			double result = numList.get(position) + numList.get(position + 1);
		    	    			numList.set(position, result);
		    	    			numList.remove(position + 1);
		    	    			operatorList.remove(position);
	    					}
	    					else if (operatorList.get(k) == ('-')) {
	    						int position = 0;
	    		    			position = operatorList.indexOf('-'); 
	    		    			double result = numList.get(position) - numList.get(position + 1);
	    		    			numList.set(position, result);
	    		    			numList.remove(position + 1);
	    		    			operatorList.remove(position);
	    					}
	    				}
	    			}
	    		}
	    		
	    		currentFormula = Double.toString(numList.get(0));
		    	formulaLabel.setText("");
		    	inputLabel.setText(currentFormula);
		    	numString = Double.toString(numList.get(0));
		    	operatorList.clear();
		    	lastEntry = 'n';
		    	numList.clear();
		    	currentFormula = "";
	    });
	    
	    /* ******************** End of manual click event handlers ***************************
	     * ***********************************************************************************
	     * ***********************************************************************************
	     */
	
	    
	    /* The following block of code is the keyboard event handler that will handle
	     * if a key on the keyboard is pressed. For example any of the buttons on the number pad,
	     * as well as the operators and enter button on the number pad. The delete key (not backspace)
	     * acts as the clear all button, and the backspace button will delete one character
	     * from what is currently being typed in. It works by using a long string of if elses.
	     * The key that is pressed is recorded by the event handler, and the if else statements
	     * go through and see which key was pressed, and takes the appropriate action.
	     */
	    
	    mainCalcPane.setOnKeyPressed(e -> { 
	    	if(e.getCode() == KeyCode.NUMPAD1 || e.getCode() == KeyCode.DIGIT1) {
	    		nextNumber = "1";
	    		numString = numString.concat(nextNumber);
	    		inputLabel.setText(numString);
	    		lastEntry = 'n';
	    	}
	    	else if(e.getCode() == KeyCode.NUMPAD2 || e.getCode() == KeyCode.DIGIT2) {
	    			nextNumber = "2";
	    			numString = numString.concat(nextNumber);
	    			inputLabel.setText(numString);
	    			lastEntry = 'n';
	    	}
	    	else if(e.getCode() == KeyCode.NUMPAD3 || e.getCode() == KeyCode.DIGIT3) { 
		    		nextNumber = "3";
		    		numString = numString.concat(nextNumber);
		    		inputLabel.setText(numString);
		    		lastEntry = 'n';
	    	}
	    	else if(e.getCode() == KeyCode.NUMPAD4 || e.getCode() == KeyCode.DIGIT4) { 
		    		nextNumber = "4";
		    		numString = numString.concat(nextNumber);
		    		inputLabel.setText(numString);
		    		lastEntry = 'n';
	    	}
	    	else if(e.getCode() == KeyCode.NUMPAD5 || e.getCode() == KeyCode.DIGIT5) { 
	    			nextNumber = "5";
	    			numString = numString.concat(nextNumber);
	    			inputLabel.setText(numString);
	    			lastEntry = 'n';
	    	}
	    	else if(e.getCode() == KeyCode.NUMPAD6 || e.getCode() == KeyCode.DIGIT6) { 
		    		nextNumber = "6";
		    		numString = numString.concat(nextNumber);
		    		inputLabel.setText(numString);
		    		lastEntry = 'n';
	    	}
	    	else if(e.getCode() == KeyCode.NUMPAD7 || e.getCode() == KeyCode.DIGIT7) { 
		    		nextNumber = "7";
		    		numString = numString.concat(nextNumber);
		    		inputLabel.setText(numString);
		    		lastEntry = 'n';
	    	}
	    	else if(e.getCode() == KeyCode.NUMPAD8 || e.getCode() == KeyCode.DIGIT8) { 
		    		nextNumber = "8";
		    		numString = numString.concat(nextNumber);
		    		inputLabel.setText(numString);
		    		lastEntry = 'n';
	    	}
	    	else if(e.getCode() == KeyCode.NUMPAD9 || e.getCode() == KeyCode.DIGIT9) { 
		    		nextNumber = "9";
		    		numString = numString.concat(nextNumber);
		    		inputLabel.setText(numString);
		    		lastEntry = 'n';
	    	}
	    	else if(e.getCode() == KeyCode.NUMPAD0 || e.getCode() == KeyCode.DIGIT0) { 
		    		nextNumber = "0";
		    		numString = numString.concat(nextNumber);
		    		inputLabel.setText(numString);
		    		lastEntry = 'n';
	    	}
	    	else if(e.getCode() == KeyCode.PERIOD || e.getCode() == KeyCode.DECIMAL) { 
		    	
		    		if(!numString.contains(".")){
		    		
		    			nextNumber = ".";
		    			numString = numString.concat(nextNumber);
		    			inputLabel.setText(numString);
		    	}
	    	}
	    	else if(e.getCode() == KeyCode.MINUS) { 
		    	
		    		if(!numString.contains("-")){
		    		
		    			nextNumber = "-";
		    			numString = numString.concat(nextNumber);
		    			inputLabel.setText(numString);
		    	}
	    	}
	    	else if(e.getCode() == KeyCode.BACK_SPACE) { 
		    	
	    		if (numString.length() > 0) {
		    		numString = numString.substring(0, numString.length() - 1);
			    	inputLabel.setText(numString);
		    	}
	    	}
	    	else if(e.getCode() == KeyCode.DELETE) {
	    			try {
	    				start(primaryStage);
	    			}
	    			catch (Exception el){
	    				el.printStackTrace();
	    			}	
	    		
	    			numString = "";
	    			inputLabel.setText("");
	    			formulaLabel.setText("");
	    			numList.clear();
	    			operatorList.clear();
	    			currentFormula = "";
	    	}
	    	else if(e.getCode() == KeyCode.ADD) {
	    		if (lastEntry == 'n') {
		    		double currentNumber = Double.parseDouble(numString);
		    		numList.add(currentNumber);
		    		operatorList.add('+');
		    		currentFormula = currentFormula.concat(numString + "+");
		    		formulaLabel.setText(currentFormula);
		    		numString = "";
		    	}
		    	
		    	lastEntry = 'o';
	    	}
	    	else if(e.getCode() == KeyCode.SUBTRACT) {
	    		if (lastEntry == 'n') {
		    		double currentNumber = Double.parseDouble(numString);
			    	numList.add(currentNumber);
			    	operatorList.add('-');
			    	currentFormula = currentFormula.concat(numString + "-");
			    	formulaLabel.setText(currentFormula);
			    	numString = "";
		    	}
		    	
		    	lastEntry = 'o';
	    	}
	    	else if(e.getCode() == KeyCode.MULTIPLY) {
	    		if (lastEntry == 'n'){
		    		double currentNumber = Double.parseDouble(numString);
			    	numList.add(currentNumber);
			    	operatorList.add('*');
			    	currentFormula = currentFormula.concat(numString + "*");
			    	formulaLabel.setText(currentFormula);
			    	numString = "";
		    	}
		    	
		    	lastEntry = 'o';
	    	}
	    	else if(e.getCode() == KeyCode.DIVIDE) {
	    		if (lastEntry == 'n'){
		    		double currentNumber = Double.parseDouble(numString);
			    	numList.add(currentNumber);
			    	operatorList.add('/');
			    	currentFormula = currentFormula.concat(numString + "/");
			    	formulaLabel.setText(currentFormula);
			    	numString = "";
		    	}
		    	
		    	lastEntry = 'o';
	    	}
	    	else if(e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.ACCEPT) {
	    		
	    		if (lastEntry == 'n') {
		    		double currentNumber = Double.parseDouble(numString);
			    	numList.add(currentNumber);
		    	}
	    		
	    		if (lastEntry == 'o') {
		    		operatorList.remove(operatorList.size() - 1);
		    	}
		    	
		    	while (operatorList.size() > 0) {
		    		
		    			while (operatorList.contains('*') || operatorList.contains('/'))
		    			{
		    				for (int j = 0; j < operatorList.size(); j++){
		    					if (operatorList.get(j) == '*'){
		    						int position = 0;
					    			position = operatorList.indexOf('*');
					    			double result = numList.get(position) * numList.get(position + 1);
					    			numList.set(position, result);
					    			numList.remove(position + 1);
					    			operatorList.remove(position);
		    					}
		    					else if (operatorList.get(j) == '/'){
		    						int position = 0;
					    			position = operatorList.indexOf('/');
					    			double result = numList.get(position) / numList.get(position + 1);
					    			numList.set(position, result);
					    			numList.remove(position + 1);
					    			operatorList.remove(position);
		    					}
		    				}
		    			}
		    			
		    			while (operatorList.contains('+') || operatorList.contains('-')) {
		    				for (int k = 0; k < operatorList.size(); k++) {
		    					if (operatorList.get(k) == '+') {
		    						int position = 0;
			    	    			position = operatorList.indexOf('+');
			    	    			double result = numList.get(position) + numList.get(position + 1);
			    	    			numList.set(position, result);
			    	    			numList.remove(position + 1);
			    	    			operatorList.remove(position);
		    					}
		    					else if (operatorList.get(k) == ('-')) {
		    						int position = 0;
		    		    			position = operatorList.indexOf('-'); 
		    		    			double result = numList.get(position) - numList.get(position + 1);
		    		    			numList.set(position, result);
		    		    			numList.remove(position + 1);
		    		    			operatorList.remove(position);
		    					}
		    				}
		    			}
		    		}
		    		
		    		currentFormula = Double.toString(numList.get(0));
			    	formulaLabel.setText("");
			    	inputLabel.setText(currentFormula);
			    	numString = Double.toString(numList.get(0));
			    	operatorList.clear();
			    	lastEntry = 'n';
			    	numList.clear();
			    	currentFormula = "";
		    	}
	     });
	}
	    
	    /* ************************ End of keyboard event handler ****************************
	     * ***********************************************************************************
	     * ***********************************************************************************
	     */
	    
	public static void main(String[] args) {
	    launch(args);
	  }
}
