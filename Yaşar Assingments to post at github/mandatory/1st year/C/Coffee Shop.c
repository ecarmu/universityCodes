#include <stdio.h>

void welcome(){
	
	printf("Welcome to the Yasar Coffee Shop\n");
}
 
int coffee_type(){
	
	int coffee;
	printf("Please select a coffee type(Latte(1), Mocha(2), Ice Chocolate Mocha(3)):\n");
	scanf_s("%d", &coffee);
	return coffee;
}

int coffee_size(){

	int size;
	printf("Please select a coffee size(Tall(1), Grande(2), Vetti(3)):\n");
	scanf_s("%d", &size);
	return size;
}

int coffee_sugar(){

	int sugar;
	printf("Would you like to add sugar to your coffee(Yes(1), No(0)):\n");
	scanf_s("%d", &sugar);
	return sugar;
}

int coffee_milk(){

	int milk;
	printf("Would you like to add milk to your coffee(Yes(1), No (0)):\n");
	scanf_s("%d", &milk);
	return milk;
	}


void cost(int coffeeType, int coffeeSize, int sugar, int milk){
	
	int price;
	if (coffeeType == 1){
		price = 2;
	}
	else if (coffeeType == 2){
		price = 4;
	}
	else if (coffeeType == 3){
		price = 5;
	}
	
	
	if (coffeeSize == 1){
		price = price * 1;
	}
	else if (coffeeSize == 2){
		price = price * 2;
	}
	else if (coffeeSize == 3){
		price = price * 3;
	}
	
	if(sugar == 1){
		price = price + 1;
	}
	
	if (milk == 1){
		price = price + 3;
	}
	printf("Price of your coffee is: %dTL\n",price);
}

void goodbye(){
	
	printf("Thank you for choosing us!\n");
}

void main(){


	int coffee;
	int size;
	int sugar;
	int milk;
	
	welcome();
	coffee = coffee_type();
	size = coffee_size();
	sugar = coffee_sugar();
	milk = coffee_milk();
	cost(coffee,size,sugar,milk);
	goodbye();
	
}






