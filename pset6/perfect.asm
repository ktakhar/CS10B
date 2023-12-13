# perfect.asm 

# PSET 6 #3
# MIPS assembly program that checks perfect numbers within a specific range and prints them
# Calls subroutine named perfect (extra credit)

.data
perfect_msg: .asciiz "Perfect number found: "

.text
.globl main
.globl perfect

# Function to check if a number is perfect (returns 1 if perfect, 0 otherwise)
perfect:
    # Arguments:
    # $a0: The number to check

    # Initialize variables
    li $t0, 1     # Initialize the sum to 1 (1 is always a divisor)
    li $t1, 2     # Initialize the divisor to 2 (start from 2)

    loop:
    beq $t1, $a0, check_perfection  # If divisor equals the number, check perfection
    divu $a1, $a0, $t1             # Calculate quotient (a1 = a0 / t1)
    mfhi $a2                       # Get remainder (a2 = a0 % t1)
    beqz $a2, is_divisor           # If remainder is 0, t1 is a divisor

    continue_loop:
    addi $t1, $t1, 1               # Increment the divisor
    j loop                         # Repeat the loop

    is_divisor:
    add $t0, $t0, $t1              # Add divisor to the sum
    addi $t1, $t1, 1               # Increment the divisor
    j continue_loop                # Continue the loop

    check_perfection:
    beq $t0, $a0, perfect_number   # If sum equals the number, it's a perfect number
    li $v0, 0                      # Return 0 (not perfect)
    jr $ra

    perfect_number:
    li $v0, 1                      # Return 1 (perfect)
    jr $ra

main:
    li $t2, 5          # Start with the first number (5)
    li $t3, 500        # End with the last number (500)

    loop:
    beq $t2, $t3, done  # If we reached the end, exit the program

    # Call the perfect subroutine
    move $a0, $t2      # Pass the current number as an argument
    jal perfect

    # Check the result
    beq $v0, 1, print_perfect  # If perfect, print it

    continue_loop:
    addi $t2, $t2, 1  # Increment the current number
    j loop             # Repeat the loop

    print_perfect:
    # Print the perfect number
    li $v0, 4
    la $a0, perfect_msg
    syscall
    move $a0, $t2      # Print the perfect number
    li $v0, 1
    syscall

    # Continue the loop
    j continue_loop

    done:
    # Exit the program
    li $v0, 10
    syscall
