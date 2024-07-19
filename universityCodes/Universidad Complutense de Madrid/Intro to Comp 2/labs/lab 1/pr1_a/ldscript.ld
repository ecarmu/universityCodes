/*---------------------------------------------------------------------
**
**  File:
**    lab1.ld  2/2/2013
**
**    (c) J.M. Mendias
** 
**    Facultad de Informática. Universidad Complutense de Madrid
**
**  Purpose:
**    Basic link script
**
**  Design notes:
**
**-------------------------------------------------------------------*/
ENTRY( main )
SECTIONS
{
    . = 0x0;
    .text   : { * (.text) }
    . = 0x10000;
    .data   : { *(.data) } 
    .bss    : { *(.bss) }     
    .rodata : { *(.rodata) } 
    _stack = 0x20000;
}
