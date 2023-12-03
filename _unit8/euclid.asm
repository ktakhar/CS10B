## euclid.asm
## Henry H. Leitner
##
## Implements Euclid GCD algorithm as a subroutine
##

	.data	# Data declaration section
greeting:	.asciiz	"I will compute the GCD of 2 integers\n\n Input int #1: "
prompt2:	.asciiz	" Now input int #2: "
answer:		.asciiz " The GCD = " 

	.text

main:		# Start of code section
		li		$v0, 4			# load syscall to print string
		la		$a0, greeting	# prompt for first integer
		syscall

		li		$v0, 5			# load syscall to read an int
		syscall					# input first integer
		move	$t2, $v0		# and store it in $t2 for now
		li		$v0, 4			# load syscall to print string
		la		$a0, prompt2	# and load address of the second prompt
		syscall					# prompt for second integer
		li		$v0, 5			# load syscall to read an int
		syscall

		move	$t1, $v0		# store second integer in $t1 for now
		li		$v0, 4			# load syscall to print a string
		la		$a0, answer		# load address of answer message
		syscall					# print answer message
		move	$a0, $t2		# set up arg #1
		move	$a1, $t1		# set up arg #2

##  This is where we call the subroutine.  The arguments are in $a0 and $a1
		jal		gcd				# CALL the subroutine to compute the answer

##  We've returned from the subroutine, with the answer in $v0 - now to move it to $a0
		move	$a0, $v0		# prepare to print the actual answer
		li		$v0, 1			# load the syscall to print an int (the answer)
		syscall
		li		$v0, 10			# load syscall to exit the program.
		syscall

##  SUBROUTINE to compute Euclid's GCD, using his algorithm:
##       If you have two values, m / n...
##        1.  Divide m by n
##        2.  If the remainder is 0, you have the GCD
##	  3.  If not, replace m by n, n by the remainder, and go back to step 1
##
gcd:	div			$a0, $a1 	# divide $a0 by $a1
		mfhi		$t0			# the remainder is in HI - get it to check
		beqz		$t0, done	# if the remainder is 0, you're done!  Go to done...
		move		$a0, $a1	# if the remainder is not 0, put $a1 into $a0
		move		$a1, $t0	# put the remainder into $a1
		b			gcd			# go back to step 1 (div)

done:	move		$v0, $a1	# we have the answer - put it in $v0 to return
		jr			$ra			# jump back to main

# END OF PROGRAM
