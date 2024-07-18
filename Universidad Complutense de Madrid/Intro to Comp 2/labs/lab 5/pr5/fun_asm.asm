/*---------------------------------------------------------------------
**
**  File:
**    fun_asm.asm  19/10/2022
**
**    (c) Daniel Báscones García
**    Introduction to Computers II
**    Facultad de Informática. Universidad Complutense de Madrid
**
**  Purpose:
**    Code file for project 5
**


// Declare global variables (data section)
int array[] = {1, 2, 3, 4, 5};
int array_size = 5;
int result = 0;

// Function prototypes
void leaf_function(int *x); // Use pointer to modify x in-place
int non_leaf_function(int array[], int size);

// Main function (entry point)
int main() {
    // Perform operations and function calls

    // Conditional structure (if statement)
    if (array_size > 0) {
        // Call non-leaf function passing array and size as arguments
    	result = non_leaf_function(array, array_size);
    }


    // End of main function
    return 0;
}

// Leaf function (calculate x = x * x)
void leaf_function(int *x) {
    // Update x to x * x
    *x = *x * *x;
}

// Non-leaf function (calls leaf function)
int non_leaf_function(int array[], int size) {
    int sum = 0;
    for (int i = 0; i < size; i++) {
        sum += array[i];
    }
    leaf_function(&sum); // Call leaf function passing the address of sum
    return sum;
}

**-------------------------------------------------------------------*/

.global main

.data
array:	.word	1, 2, 3, 4, 5
size:	.word	5
.bss
result:	.space	4
.text
main:
la sp,_stack
la t0, array	// t0 -> @ of array
la t1, size	// t1 -> @ of size
lw t2, 0(t1)	// t2 -> value of size
la t3, result	// t3 -> @ of res
lw t4, 0(t3)	// t4 -> value of res
if:
ble t2, zero, end_main
mv a0, t0	// passing array as 0th parameter
mv a1, t2	// passing size as 1st parameter
call non_leaf_function
mv t4, a0	// result = non_leaf_function(array, array_size);
sw t4, 0(t1)

end_main:
j .


non_leaf_function:
#prologue
addi sp, sp, -12
sw a0, 0(sp)	// a0 -> @ of int array
sw a1, 4(sp)	// a1 -> int size
sw ra, 8(sp)

#body
li s8, 0	// s8 -> int sum = 0
li s9, 0	// s9 -> int i = 0
for_nonleaf:
bge s9, a1, endfor_nonleaf
slli s10, s9, 2	// s10 -> i^2
add s11, a0, s10// s11 -> @ effective of int array
lw s2, 0(s11)	// s2 -> array[i]
add s8, s8, s2	// sum += array[i]
add s9, s9, 1
j for_nonleaf
endfor_nonleaf:

mv a0, s8	// moving sum to a0
call leaf_function

#epilogue
lw ra, 8(sp)
addi sp, sp, 12
ret

leaf_function:
#prologue
addi sp, sp, -8
sw a0, 0(sp)	// a0 -> int x
sw ra, 4(sp)

#body
mul a0, a0, a0

#epilogue
lw ra, 4(sp)
addi sp, sp, 8

ret
