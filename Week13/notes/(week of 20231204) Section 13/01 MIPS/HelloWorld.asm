## David Habermehl -- 04/30/09
## HelloWorld.asm -- Prints "Hello, World!\n"

## Prints "Hello, World
##
##  1.   System.out.println( "Hello, World!" );

## Registers used:
##      $a0     - syscall parameter.
##      $v0     - syscall parameter.


## Data goes in the data section
        .data
hello:  .asciiz "Hello, World!\n"


## Code goes in the text section
        .text
main:
        la      $a0, hello              # Print "Hello, World!\n"
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

        li      $v0, 10                 # Exit the program
        syscall                         # syscall 10 = exit program
