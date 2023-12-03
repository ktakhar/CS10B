.text

main:	  	la $a0, blah
		lw $t1, ($a0)
		lw $t1, 10010000
		lw $t1, foo
		lw $t1, foo+4
		lw $t1, foo+4($a1)
		li $a0, 10
		syscall	

	.data
blah:	   	 .word   11, 12
foo:	    	 .word   4, 5, 6, 7, 8
		
