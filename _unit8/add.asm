# Last modified on April 13, 2020
# add.asm-- A program which computes the sum of 1 and 2,
#            leaving the result in register $t0.
# Registers used:
#       t0      - used to hold the result.
#       t1      - used to hold the constant 1
#       t2      - used to hold the constant 2
#       v0      - syscall parameter.

main:                       	# SPIM starts execution at main.
		li      $t1, -1        	# load 1 into $t1.
        li      $t2, 12
		add     $t0, $t1, $t2 	# $t0 <- $t1 + $t2
		li      $v0, 10       	# syscall code 10 is for exit
		syscall              	# make the syscall.

# end of add.asm
