## David Habermehl -- 04/30/09
## if_then_else.asm -- implements if, then, else pseudo code in MIPS assembler

## 1.    System.out.print( "Enter a positive integer: " );
## 2.    int foo = keyboard.nextInt();
## 3.    if ( foo > 9 ) {
## 4.        System.out.println( "The integer is > 9" );
## 5.    else {
## 6.        System.out.println( "The integer is not > 9" );
## 7.    }

## Registers used:
##      $t0     - holds foo (the number being tested).
##      $t1     - holds the constant 9 (against which foo is tested).
##      $a0     - syscall parameter.
##      $v0     - syscall parameter and return value.


## Data goes in the data section
        .data
intPrompt:      .asciiz "Enter a positive integer: "
greaterThan:    .asciiz "The integer is > 9"
notGreaterThan: .asciiz "The integer is not > 9"
foo:            .word   10


## Code goes in the text section
        .text
main:
        la      $a0, intPrompt          # Print intprompt string
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

        li      $v0, 5                  # read foo
        syscall                         # syscall 5 = read integer value into v0
        sw      $v0, foo                # save it for later use

        .globl if
if:
        lw      $t0, foo
        li      $t1, 9                
        ble     $t0, $t1, do_else_part

do_if_part:
        la      $a0, greaterThan        # Print "foo is > 9"
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0
        b       end_if

do_else_part:
        la      $a0, notGreaterThan     # Print "foo is not > 9"
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

end_if:

exit:                                   # Exit the program
        li      $v0, 10
        syscall                         # syscall 10 = exit program
