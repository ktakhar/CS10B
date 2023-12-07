.data
prompt1: .asciiz "Enter the first integer: "
prompt2: .asciiz "Enter the second integer: "
result: .asciiz "The larger number is: "

.text
.globl main

main:
    # Prompt for the first integer
    li $v0, 4            # Load syscall print_string into $v0
    la $a0, prompt1      # Load the address of prompt1 into $a0
    syscall

    # Read the first integer from the user
    li $v0, 5            # Load syscall read_int into $v0
    syscall
    move $t0, $v0        # Move the first number read into $t0

    # Prompt for the second integer
    li $v0, 4            # Load syscall print_string into $v0
    la $a0, prompt2      # Load the address of prompt2 into $a0
    syscall

    # Read the second integer from the user
    li $v0, 5            # Load syscall read_int into $v0
    syscall
    move $t1, $v0        # Move the second number read into $t1

    # Compare the two integers
    bgt $t0, $t1, t0isBigger
    move $t2, $t1        # $t1 is bigger, so move it to $t2
    j printResult

t0isBigger:
    move $t2, $t0        # $t0 is bigger, so move it to $t2

printResult:
    # Print the result
    li $v0, 4            # Load syscall print_string into $v0
    la $a0, result       # Load the address of result into $a0
    syscall

    # Print the larger number from $t2
    li $v0, 1            # Load syscall print_int into $v0
    move $a0, $t2        # Move the larger number to $a0
    syscall

    # Exit the program
    li $v0, 10           # Load syscall exit into $v0
    syscall
