## Last modified on March 13, 2020
## atoi-4.asm -- reads a line of text, converts it to an integer, and
##	prints the integer.
## Register usage:
##	$t0	- S.
##	$t1	- the character pointed to by S.
##	$t2	- the current sum.
##	$t3	- has the sign
##	$t4	- stores the constant 10

    .globl  positive

	.text
main:
	la     $a0, prompt	    	# prompt user for an integer
	li     $v0, 4
	syscall
	la     $a0, string_space	## read the string S:
	li     $a1, 1024
	li     $v0, 8		        # load "read_string" code into $v0.
	syscall	

	la     $t0, string_space	# Initialize S.
	li     $t2, 0		        # Initialize sum <- 0.

	li     $t4, 10
	li     $t3, 1
	lb     $t1, ($t0)
	bne    $t1, '-', positive
	li     $t3, -1
	addu   $t0, $t0, 1
positive:
sum_loop:
	lb     $t1, ($t0)			# load the byte *S into $t1,
	addu   $t0, $t0, 1			# and increment S.

	beq    $t1, '\n', end_sum_loop	# if $t1 == \n, branch out of loop.

 	blt    $t1, '0', end_sum_loop

	bgt    $t1, '9', end_sum_loop

	mult   $t2, $t4
	mfhi   $t5
	bnez   $t5, overflow
	mflo   $t2
	blt    $t2, $0, overflow

	sub    $t1, $t1, '0'
	add    $t2, $t2, $t1
    blt    $t2, $0, overflow
	b      sum_loop

end_sum_loop:
	mul    $t2, $t2, $t3
	move   $a0, $t2	            # print out the answer (t2).
	li     $v0, 1
	syscall

	la     $a0, newline	   		# and then print out a newline.
	li     $v0, 4
	syscall
 
exit:				    		## exit the program:
	li     $v0, 10		    	# load "exit" into $v0.
	syscall			   			# make the system call.

overflow:	
	la     $a0, overflow_msg
	li     $v0, 4
	syscall	
	b      exit
## Here's where the data for this program is stored:
	 .data
prompt:			 .asciiz "Type an integer: "
newline:         .asciiz  "\n"
string_space:	 .space	 1024		# set aside 1024 bytes for the string.
overflow_msg:	 .asciiz "Overflow occurred!\n"

## end of atoi-4.asm
