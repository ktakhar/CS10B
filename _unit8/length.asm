

        .data
        the_string:  .asciiz   "Now is the time"

        .text
        main:    move  $a0, $0   #initialize length to 0
                 la    $t0, the_string
                 
          loop:  lb    $t1, ($t0)  #indirect addressing (pointer derefence)
                 beq   $t1, $0, all_done
                 
                 addi  $a0, $a0, 1
                 addu  $t0, $t0, 1 
                 b     loop
                 
         all_done:        
                   li   $v0, 1
                   syscall
                   li   $v0, 10
                   syscall
