main:
        ## Get first number from user, put into $t0.
        li      $v0, 5          # load syscall read_int into $v0.
        syscall                 # make the syscall.
        move    $t0, $v0        # move the number read into $t0.

        ## Get second number from user, put into $t1.
        li      $v0, 5          # load syscall read_int into $v0.
        syscall                 # make the syscall.
        move    $t1, $v0        # move the number read into $t1.

bgt $t0, $t1, t0isBigger

        move $a0, $t1
        back:
        li $v0, 1
        syscall 
        li $v0, 10
        syscall
        t0isBigger
        
        move $a0, $t0
        
        b back
