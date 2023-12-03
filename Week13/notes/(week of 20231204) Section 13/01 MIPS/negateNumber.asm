## David Habermehl -- 23_Apr_2020
## negateNumber.asm -- Prompts user, reads a number, prints that number negated.

##  1.   System.out.print( "Enter an int (e.g. 4, or -7): ");
##  2.   int number = (new Scanner( System.in)).nextInt();
##  3.   int numberNegated = 0-number;
##  4.   System.out.print( "That number negated is: ");
##  5.   System.out.print( numberNegated );


## Registers used:
##      $t0     - holds 0-number (i.e. number negated).
##      $a0     - syscall parameter.
##      $v0     - syscall parameter and return value.


## Data goes in the data section
        .data
prompt:         .asciiz "\nEnter an int (e.g. 4, or -7, 0 to quit): "
result:         .asciiz "That number negated is: "
bye:            .asciiz "\n\nBye!\n"


## Code goes in the text section

        .text
main:
        la      $a0, prompt             # Print prompt string
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

        li      $v0, 5                  # read number
        syscall                         # syscall 5 = read integer value into v0

        beqz    $v0, exit               # zero means quit
        
        subu    $t0, $zero, $v0         # Store 0-number (i.e. the number negated) in $t0
        
        la      $a0, result             # Print result string
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0

        move    $a0, $t0                # Print the number negated
        li      $v0, 1
        syscall                         # syscall 1 = print integer value in a0
        
        b main                          # Do it again

exit:
        la      $a0, bye                # Say boodbye
        li      $v0, 4
        syscall                         # syscall 4 = print string pointed to by a0
        
        li      $v0, 10
        syscall                         # syscall 10 = exit program
