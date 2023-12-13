## Last modified on March 13, 2019
## atoi-2.asm -- reads a line of text, converts it to an integer, and
##	prints the integer.
## Register usage:
##	$t0	- S.
##	$t1	- the character pointed to by S.
##	$t2	- the current sum.

	.text
main:
	la     $a0, prompt	    	# prompt user for an integer
	li     $v0, 4
	syscall
	la	$a0, string_space    	## read the string S:
	li	$a1, 1024
	li	$v0, 8		          	# load "read_string" code into $v0.
	syscall	

	la	$t0, string_space    	# Initialize S.
	li	$t2, 0		          	# Initialize sum <- 0.
get_sign:
	li  	$t3, 1
	lb  	$t1, ($t0)
	bne 	$t1, '-', positive
	li  	$t3, -1
	addu 	$t0, $t0, 1

positive:

sum_loop:
	lb		$t1, ($t0)	    	# load the byte *S into $t1,
	addu	$t0, $t0, 1	     	# and increment S.

	beq	$t1, '\n', end_sum_loop	# if $t1 == \n, branch out of loop.

	mul	$t2, $t2, 10		  	# t2 *= 10.

	sub	$t1, $t1, '0'		  	# t1 -= '0'.
	add	$t2, $t2, $t1		  	# t2 += t1.

	b	sum_loop		 		#  and repeat the loop.
end_sum_loop:

	mul		$t2, $t2, $t3
	move	$a0, $t2		  	# print out the answer (t2).
	li		$v0, 1
	syscall

	la		$a0, newline	 	# and then print out a newline.
	li		$v0, 4
	syscall

exit:					  		## exit the program:
	li	$v0, 10			  		# load "exit" into $v0.
	syscall				  		# make the system call.

## Here's where the data for this program is stored:
	.data
prompt:			.asciiz "Type an integer: "
newline:		.asciiz	"\n"
string_space:	.space	1024		# set aside 1024 bytes for the string.

## end of atoi-2.asm
