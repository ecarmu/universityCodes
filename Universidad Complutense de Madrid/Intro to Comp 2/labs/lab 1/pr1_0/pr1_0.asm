/*---------------------------------------------------------------------
**
**  File:
**    pr1_0.asm  19/10/2022
**
**    (c) Daniel Báscones García
**    Introduction to Computers II
**    Facultad de Informática. Universidad Complutense de Madrid
**
**  Purpose:
**    Code file for project 1
**
**  Design notes:
**
**-------------------------------------------------------------------*/

.data               //data with initial value section
    A:      .word 5 //data 'A', with value 5
    B:      .word 8 //data 'B' with value 8

.bss                //data without initial value section
    GREATER:  .space 4//data 'GREATER' takes 4 bytes

.text               //code section
.global main        //''main'' can be called from outside
main:               //code entry point
    la t2, A        //the address of A is loaded into t2
    lw t0, 0(t2)    //we use the address to load the value of A
    la t3, B        //the address of B is loaded into t3
    lw t1, 0(t3)    //we use the address to load the value of B
    ble t0, t1, mayb//if t0 (A) <= t1 (B), then branch (inverse condition)
                    //in this case we have not branched (A is greater)
    la t4, GREATER  //the address of GREATER is loaded into t4
    sw t0, 0(t4)    //we store t0 (A) into GREATER
    j end           //we branch to the end of the program
mayb:               //in this case, B is greater (or equal)
    la t4, GREATER  //the address of GREATER is loaded into t4
    sw t1, 0(t4)    //we store t1 (B) into GREATER
end:                //end of program
    j end           //the program stays here forever
