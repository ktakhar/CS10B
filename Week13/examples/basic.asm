    .text
    .globl main

main:
    li  $t1, 1           # Load 1 into $t1.
    li  $t2, 2           # Load 2 into $t2.
    add $t0, $t1, $t2    # Add $t2 to $t1 into $t0.
    
    li  $v0, 10          # Load syscall code 10 into $v0 to exit.
    syscall              # Make the syscall.
    