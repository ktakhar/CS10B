## David Habermehl -- 04/30/09
## while.asm -- Prints integers from 0 to upperLimit
##              upperLimit is read from keyboard input with prompts

##  1.   System.out.print( "Upper Limit: " );
##  2.   int upperLimit = keyboard.nextInt();
##  3.   int i = 0;
##  4.   while ( i<=upperLimit ) {
##  5.       System.out.print( i );
##  6.       System.out.println();
##  7.       i++;
##  8.   }

## Registers used:
##      $t0     - holds i.
##      $t1     - holds upperLimit.
##      $a0     - syscall parameter.
##      $v0     - syscall parameter and return value.


## Data goes in the data section
        .data
newline:        .asciiz "\n"
upperPrompt:    .asciiz "Upper Limit: "
upperLimit:     .word   10


## Code goes in the text section
        .text
main:
        la      $a0, upperPrompt        # Print upperPrompt string
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

        li      $v0, 5                  # read upperLimit
        syscall                         # syscall 5 = read integer value into v0
        sw      $v0, upperLimit         # save it for later use

        li      $t0, 0                  # $t0 = i = 0
        lw      $t1, upperLimit         # $t1 = upperLimit
while:
        bgt     $t0, $t1, end_while     # terminate loop if i > upperLimit

        move    $a0, $t0                # Print i
        li      $v0, 1
        syscall                         # syscall 1 = print integer value in a0

        la      $a0, newline            # Print a newline
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

        addi    $t0, $t0, 1             # i++
        b       while
end_while:

exit:                                   # Exit the program
        li      $v0, 10
        syscall                         # syscall 10 = exit program
