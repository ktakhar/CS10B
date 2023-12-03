## David Habermehl -- 04/30/09
## process_a_table.asm -- Uses a for loop to print integers in a table
##                        Shows how to access sequential words in a table

## For-loop to iterate through each integer in table
##
##  1.   for ( int i=0; i<table.length; i++ ) {
##  2.       System.out.print( table[i] );
##  3.       System.out.println();
##  4.   }

## UNIT 8 PROBLEM 1 REQUIRES YOU TO TAKE A DIFFERENT APPROACH TO PROCESSING 
## A TABLE. INSTEAD OF HAVING AN end_table: LABEL, YOU MUST USE A MEMORY LOCATION 
## LABELED N WHOSE CONTENTS IS THE NUMBER OF WORDS IN THE TABLE.

## Registers used:
##      $t0     - holds address of table[i].
##      $t1     - holds address following last word in table.
##      $a0     - syscall parameter.
##      $v0     - syscall parameter and return value.


## Data goes in the data section

        .data

newline:        .asciiz "\n"
table:          .word   10
                .word   99
                .word   -8
                .word    8
end_table:


## Code goes in the text section
        .text
main:
        la      $t0, table              # $t0 = address of table
        la      $t1, end_table          # $t1 = address following last word in table
for_loop:
        bgeu    $t0, $t1, end_for_loop  # terminate loop if i > upperLimit

        lw      $a0, ($t0)             # Print table[i]
        li      $v0, 1
        syscall                         # syscall 1 = print integer value in a0
        
        la      $a0, newline            # Print a newline
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

        addiu    $t0, $t0, 4            # i++ (Remember that a word is 4 bytes)
        b       for_loop
end_for_loop:

exit:                                   # Exit the program
        li      $v0, 10
        syscall                         # syscall 10 = exit program
