/*---------------------------------------------------------------------
**
**  File:
**    pr1_a.asm  19/10/2022
**
**    (c) Daniel Báscones García
**    Introduction to Computers II
**    Facultad de Informática. Universidad Complutense de Madrid
**
**  Purpose:
**    Code file for project 1a
**
**  Design notes:
**
**	# define N 10
**	int res = 0;
**	for (int i = 0; i < N ; i ++) {
**		res += i ;
**	}
**
**-------------------------------------------------------------------*/

//constant N is defined
.equ N, 10
//space reserved for the result
.bss
	res: 	.space 4
//program
.text
.global main
main:
//Complete the code
