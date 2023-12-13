## Modified by Henry Leitner 
## fib-s.asm-- A program to compute Fibonacci numbers.
##	Uses CALLEE-saved registers to store temporary variables.
## Registers used:
##	$v0     - syscall parameter and return value.
##	$a0     - syscall parameter-- the string to print.
	.globl  fib
	.globl  ret		       # The .globls are for debugging
	.globl  r1
	.globl  r2

	.text
main: 
	la	    $a0, greeting
	li	    $v0, 4
	syscall

	## Get n from the user, put into $a0.
	li      $v0, 5          	# load syscall read_int into $v0.
	syscall                 	# make the syscall.
	move    $a0, $v0        	# move the number read into $a0.

	jal		fib		            # call fib.
ret:
	move	$a0, $v0
	li      $v0, 1          	# load syscall print_int into $v0.
	syscall                 	# make the syscall.

	la      $a0, newline
	li      $v0, 4			    # print_string
	syscall                 	# make the syscall.

	li      $v0, 10         	# 10 is the exit syscall.
	syscall                 	# do the syscall.

# fib-- (callee-save method)
# Registers used:
#	$a0     - initially n.
#	$s0     - parameter n.
#	$s1     - fib (n - 1).
#	$s2     - fib (n - 2).

fib: 							# first, callee preamble...
	 subu   $sp, $sp, 20		# frame size =  20
	 sw     $ra, 16($sp)		# preserve the Return Address.
	 sw     $fp, 12($sp)		# preserve the Frame Pointer.
	 sw     $s0,  8 ($sp)	    # preserve $s0.
	 sw     $s1,  4($sp)		# preserve $s1.
	 sw     $s2,  0($sp)		# preserve $s2.
	 addu   $fp, $sp, 20		# move frame ptr to base of frame.

	 move	$s0, $a0	       	# get n from caller.

	 blt	$s0, 2, fib_base_case	# if n < 2, then do base case.

	 sub	$a0, $s0, 1	     	# compute fib (n - 1)
	 jal	fib			        # RECURSION!
r1:	 move	$s1, $v0	    	# s1 <- fib (n - 1).

	 sub	$a0, $s0, 2	    	# compute fib (n - 2)
	 jal	fib					# RECURSION!
r2:	 move	$s2, $v0	    	# $s2 <- fib (n - 2).

	 add	$v0, $s1, $s2	    # $v0 <- fib (n - 1) + fib (n - 2).
	 b      fib_return

fib_base_case:		            # in the base case, return 1.
	li      $v0, 1

fib_return:
	lw      $ra, 16($sp)	    # restore the Return Address.
	lw      $fp, 12($sp)	    # restore the Frame Pointer.
	lw      $s0,  8($sp)	    # restore $s0.
	lw      $s1,  4($sp)	    # restore $s1.
	lw      $s2,  0($sp)	    # restore $s2.
	addu	$sp, $sp, 20	    # restore the Stack Pointer.
	jr      $ra		    		# return

## data for fib-s.asm:
	.data
greeting:	.asciiz	"Recursively compute the nth Fibonacci number  n = "
newline:	.asciiz	"\n"

# end of fib.asm
