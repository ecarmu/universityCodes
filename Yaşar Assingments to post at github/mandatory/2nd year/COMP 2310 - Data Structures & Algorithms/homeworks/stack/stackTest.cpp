#include "stack.h"

#define prettyPrint(value) (value?"Yes":"No")

// Example input   -> "a+b*c+(d*e+f)*g"
// Expected output -> "abc*+de*f+g*+"


// 21070006054- ARDA HARMAN
int precedence(char chr){
	if(chr == '+' || chr == '-'){
		return 0;
	}
	if(chr == '*' || chr == '/'){
		return 1;
	}
	if(chr == '(' || chr == ')'){
		return 2;
	}
}

string inFixToPostFix(string word) {
    Stack<char> stack = Stack<char>(word.length());
    string result;

	char sum = '+';
	
	char substr = '-';
	
	char mult = '*';

	char divis = '/';
	
    
    
    int counter = 0;
    char chr = ' ';
    
    char openingParanthesis = '(';
    char closingParanthesis = ')';
    
    while(counter<word.length()){
    	chr = word[counter];
    	
    	if(chr>='a' && chr<='z'){
    		result += chr;
    		
		}
		else{
			while(precedence(chr) <= precedence(stack.getTopIndex() ) ){
				
				result += stack.pop();
				
			}
			stack.push(chr);
		}
		counter++;
	}
	
	while(!(stack.isEmpty()) )
	result += stack.pop();
	
	cout<<result;
	return result;
}

// Expected input   -> "abc*+de*f+g*+"
// Example output   -> "((a+(b*c))+(((d*e)+f)*g))"
/* HINT
 * Store the operands (a,b,c...), create a string on operators (+,-..)
 * While creating a string, put the 'top' element to the end (after operator)
 * Always add parenthesis to satisfy precedence
 */
 



int main() {
    Stack<int> numbers = Stack<int>(3);
    cout << "Is stack empty ? " << prettyPrint(numbers.isEmpty()) << endl;
    cout << "Is stack full ? " << prettyPrint(numbers.isFull()) << endl;
    numbers.push(1);
    cout << "Is test case 1 correct ? : " << prettyPrint(numbers.isEmpty() == false) << endl;
    numbers.printStack();
    int value = numbers.pop();
    cout << "Is test case 2 correct ? : " << prettyPrint(numbers.isEmpty() == true) << endl;
    numbers.printStack();
    cout << "Is test case 3 correct ? :" << prettyPrint(value == 1) << endl;
    numbers.push(1);
    numbers.push(2);
    numbers.push(3);
    cout << "Is test case 4 correct ? :" << prettyPrint(numbers.isFull()) << endl;
    numbers.push(4);
    cout << "Is test case 5 correct ? :" << prettyPrint(numbers.getTopIndex() == 2) << endl;
	cout << "Is test case 7 correct ? :" << prettyPrint(inFixToPostFix("a+b*c+(d*e+f)*g") == "abc*+de*f+g*+") << endl;
    return 0;
}
