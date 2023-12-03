            # Iterative program to compute n!
   
            # The value n is entered by the user

            .globl  main
main:
            li      $v0, 5
            syscall                 # Get user input for n
            move    $s0, $v0        # s0: n

            move    $a0, $s0        # put n into first parameter
            jal     factorial
            move    $s1, $v0        # s1: n!

            move    $a0, $s1    
            li      $v0, 1
            syscall                 # display n!            

            li  $v0, 10
            syscall

#-----------------------------------
            # Factorial Function
            #
            # Parameters:  a0 - n
            # Return Values: V0 - n!
factorial:
            #---------------------- Stuff at beginning of function
            addi    $sp, $sp, -12   # space for 3 values
            sw      $ra, 0($sp)
            sw      $s0, 4($sp)
            sw      $s1, 8($sp)

            #---------------------- body of functino
            move    $s0, $a0        # s0: n
            li      $s1, 1          # s1: n! (init to 1)
            li      $t1, 1          # t1: loop exit test value
floop:      ble     $s0, $t1, done  # when n decrements down to 1, exit
            mul     $s1, $s1, $s0   # s1 = s1 * n
            addi    $s0, $s0, -1    # n--
            j       floop

            #---------------------- Stuff at end of function
done:       move    $v0, $s1        # put n! into return register
            lw      $ra, 0($sp)
            lw      $s0, 4($sp)
            lw      $s1, 8($sp)
            addi    $sp, $sp, 12
            jr      $ra             # TTFN
