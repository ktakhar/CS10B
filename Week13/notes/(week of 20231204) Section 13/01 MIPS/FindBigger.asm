# Last modified on April 28, 2020
# findBigger-- A program which prints the larger of two numbers specified at 
# runtime by the user. More specifically, to manage ties the number printed
# is not less than the other number.
# Registers used:
#       $t0     - used to hold the first number.
#       $t1     - used to hold the second number.
#       $a0     - used to hold the larger of $t1 and $t2.
#       $v0     - syscall parameter and return value.
#       $a0     - syscall parameter.

        .data
newline:        .asciiz "\n"
        .text

main:
        ## Get first number from user, put into $t0.
        li      $v0, 5          # load syscall read_int into $v0.
        syscall                 # make the syscall.
        move    $t0, $v0        # move the number read into $t0.

        ## Get second number from user, put into $t1.
        li      $v0, 5          # load syscall read_int into $v0.
        syscall                 # make the syscall.
        move    $t1, $v0        # move the number read into $t1.

        ## 1st number is in $t0, 2nd number is in $t1
        bgt     $t0, $t1, t0isBigger    # Branch if 1st number > 2nd number
        
        ## If we didn't branch, then 2nd number >= 1st number
        move    $a0, $t1        # Put 2nd number into $t2
        b       printBigger
        
t0isBigger:
        ## If we get here, then 1st number > 2nd number
        move    $a0, $t0        # Put 1st number into $t2
        
printBigger:
        ## $a0 contains the value to be printed
        li      $v0, 1          # load syscall print_int into $v0.
        syscall                 # make the syscall.
        
        ## Print a newline
        la      $a0, newline
        li      $v0, 4          # load syscall print_string into $v0.
        syscall                 # make the syscall.

        ## Exit the program
        li      $v0, 10         # load syscall exit_program into $v0.
        syscall                 # make the syscall.
