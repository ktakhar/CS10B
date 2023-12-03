## David Habermehl -- 04/30/09
## for1.asm -- Prints integers from lowerLimit to upperLimit
##             lowerLimit and upperLimit are hard-coded constants in the data segment

## For-loop to print each integer i between Lower_Limit and upperLimit
##
##  1.   for ( int i=lowerLimit; i<=upperLimit; i++ ) {
##  2.       System.out.print( i );
##  3.       System.out.println();
##  4.   }

## Registers used:
##      $t0     - holds i.
##      $t1     - holds upperLimit.
##      $a0     - syscall parameter.
##      $v0     - syscall parameter.


## Data goes in the data section
        .data
newline:        .asciiz "\n"
lowerLimit:     .word   -3
upperLimit:     .word   8


## Code goes in the text section
        .text
main:
        lw      $t0, lowerLimit         # $t0 = i = lowerLimit
        lw      $t1, upperLimit         # $t1 used by for-loop termination test

for_loop:
        bgt     $t0, $t1, end_for_loop  # terminate loop if i > upperLimit

        move    $a0, $t0                # Print i
        li      $v0, 1
        syscall                         # syscall 1 = print integer value in a0
        
        la      $a0, newline            # Print a newline
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

        addi    $t0, $t0, 1             # i++
        b       for_loop
end_for_loop:

exit:
        li      $v0, 10                 # Exit the program
        syscall                         # syscall 10 = exit program
