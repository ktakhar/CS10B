## David Habermehl -- 04/30/09
## for3.asm -- Prints integers from lowerLimit to upperLimit
##             lowerLimit and upperLimit are read from keyboard input with prompts

## For-loop to iterate through each integer i between Lower_Limit and upperLimit
##
##  1.   System.out.print( "Lower Limit: " );
##  2.   int lowerLimit = keyboard.nextInt();
##  3.   System.out.print( "Upper Limit: " );
##  4.   int upperLimit = keyboard.nextInt();
##  5.   for ( int i=lowerLimit; i<=upperLimit; i++ ) {
##  6.       System.out.print( i );
##  7.       System.out.println();
##  8.   }

## Registers used:
##      $t0     - holds i.
##      $t1     - holds upperLimit.
##      $a0     - syscall parameter.
##      $v0     - syscall parameter and return value.


## Data goes in the data section
        .data
newline:        .asciiz "\n"
lowerLimit:     .word   0
upperLimit:     .word   0
lowerPrompt:    .asciiz "Lower Limit: "
upperPrompt:    .asciiz "Upper Limit: "


## Code goes in the text section

        .text
main:
        la      $a0, lowerPrompt        # Print lowerPrompt string
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

        li      $v0, 5                  # read lowerLimit
        syscall                         # syscall 5 = read integer value into v0
        sw      $v0, lowerLimit         # save it for later use
        
        la      $a0, upperPrompt        # Print upperPrompt string
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

        li  $v0, 5                      # read upperLimit
        syscall                         # syscall 5 = read integer value into v0
        sw  $v0, upperLimit             # save it for later use

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

        addiu   $t0, $t0, 1             # i++
        b       for_loop
end_for_loop:

exit:                                   # Exit the program
        li      $v0, 10
        syscall                         # syscall 10 = exit program
