# Last modified on March 21, 2019
# pseudo.asm-- A program which computes the absolute value 
#              of what is in $t1, leaving the result in register $t2.

main:                         # SPIM starts execution at main.
		li      $t1, -1       # load -1 into $t1.
        abs     $t2, $t1
		move    $v1, $t2 
		li      $v0, 10       # syscall code 10 is for exit
		syscall               # make the syscall.

# end of pseudo.asm
