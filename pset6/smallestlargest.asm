# smallestlargest.asm

# PSET 6 #1
# prints the smallest and largest values found in a non-empty table of n word sized integers. 
# address of first entry should be named table and N should be defined constant. 

.data
N:      .word  9
table:  .word  3, -1, 6, 5, 7, -3, -15, 18, 2
#N:     .word  1
#table: .word  3

smallest: .word 0      # Variable to store the smallest value
largest:  .word 0      # Variable to store the largest value

.text
.globl main

main:
    lw  $t0, N           # Load N into $t0
    lw  $t1, table       # Load the base address of the table into $t1
    lw  $t2, 0($t1)      # Load the first element into $t2 (initial smallest and largest)

loop:
    beqz $t0, done       # If N == 0, exit the loop
    lw   $t3, 0($t1)     # Load the current element into $t3
    slt  $t4, $t3, $t2   # Compare $t3 with the smallest value
    beq  $t4, $zero, not_smallest
    move $t2, $t3        # Update the smallest value

not_smallest:
    slt  $t5, $t2, $t3   # Compare $t2 with the largest value
    beq  $t5, $zero, not_largest
    move $t2, $t3        # Update the largest value

not_largest:
    addi $t1, $t1, 4     # Move to the next element in the table
    addi $t0, $t0, -1    # Decrement N
    j    loop            # Repeat the loop

done:
    # Print the smallest and largest values
    li   $v0, 4
    la   $a0, smallest
    syscall

    li   $v0, 1
    lw   $a0, largest
    syscall

    # Exit the program
    li   $v0, 10
    syscall
