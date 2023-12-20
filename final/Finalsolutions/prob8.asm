# E50b Practice Final Exam
# Decodes a message
        .text
main:            # SPIM starts by jumping to main.
                 # First read the string from keyboard
        la      $a0, string_space
        li      $a1, 1024
        li      $v0, 8 # load read_string code into $v0
        syscall 

        la      $a0, string_space      
        jal     decode
        la      $a0, string_space
        li      $v0, 4
        syscall
        li      $v0, 10  	# load "exit" into $v0
        syscall  

decode: # Here is the subroutine!
        subu    $sp, $sp, 8	# first install stack frame
        sw      $ra, 4($sp)
        sw      $fp, 0($sp)
        addu    $fp, $sp, 8

loop:   lb      $t3, ($a0) 	# load one char into $t3
        beqz    $t3, exit  	# if $t3==null char, branch 
        blt     $t3, 'a', incr
        bgt     $t3, 'z', incr
# 7 MISSING STATEMENTS GET INSERTED BELOW
		sub 	$t3, $t3, 3
		bge 	$t3, 'a', incr
		li  	$t4, 'a'
		sub  	$t3, $t4, $t3
		li   	$t4, 'z'
		sub 	$t3, $t4, $t3
		addi 	$t3, $t3, 1


incr:   sb      $t3, ($a0)
        addu    $a0, $a0, 1
        b       loop

exit:   #4 MORE MISSING STATEMENTS GET INSERTED BELOW
		lw		$fp, 0($sp)
		lw		$ra, 4($sp)
		addu	$sp, $sp, 8
		
		jr  $ra				#return from this subroutine








## Here's where data for this program is stored:
                .data
string_space:   .space  1024  