.data
string_space:   .space 1024    # Set aside 1024 bytes for the string.
is_palin_msg:   .asciiz "The string is a palindrome.\n"
not_palin_msg:  .asciiz "The string is not a palindrome.\n"

.text
.globl main
.globl length_loop
.globl test_loop

main:
    # Read the string S:
    la  $a0, string_space
    li  $a1, 1024
    li  $v0, 8             # Load "read_string" code into $v0.
    syscall

    la  $t1, string_space  # A <- S.

    # Move B to the end of the string:
length_loop:
    lb  $t3, ($t2)         # Load the byte *B into $t3.
    beqz $t3, end_length_loop  # If $t3 == 0, branch out of the loop.
    addu $t2, $t2, 1       # Otherwise, increment B,
    b length_loop           # and repeat the loop.
end_length_loop:
    subu $t2, $t2, 2       # Subtract 2 to move B back past the '\0' and '\n'.
    j test_loop

test_loop:
    bgeu $t1, $t2, is_palin   # If A >= B, it is a palindrome.

    lb $t3, ($t1)             # Load the byte *A into $t3.
    lb $t4, ($t2)             # Load the byte *B into $t4.

    # Ignore non-alphanumeric characters and convert to lowercase:
    is_alnum:
        lbu $t5, ($t3)        # Load the byte *A into $t5.
        beqz $t5, skip_char   # If $t5 == 0, skip to the next character.
        blt $t5, 65, not_upper  # If $t5 < 'A', it's not uppercase.
        bgt $t5, 90, not_upper  # If $t5 > 'Z', it's not uppercase.
        addi $t5, $t5, 32     # Convert uppercase to lowercase.
        sb $t5, ($t3)         # Store the lowercase character back.
    not_upper:
        blt $t5, 97, not_lower  # If $t5 < 'a', it's not lowercase.
        bgt $t5, 122, not_lower  # If $t5 > 'z', it's not lowercase.
    not_lower:
        beq $t5, $t4, match   # If $t5 == $t4, they match.

    # If characters don't match, it's not a palindrome:
    not_palin:
        la $a0, not_palin_msg
        li $v0, 4
        syscall
        j exit

    # Characters match, so increment A and decrement B:
    match:
        addu $t1, $t1, 1
        subu $t2, $t2, 1
        j test_loop

    # Skip non-alphanumeric characters:
    skip_char:
        addu $t1, $t1, 1
        subu $t2, $t2, 1
        j test_loop

is_palin:
    la $a0, is_palin_msg
    li $v0, 4
    syscall
    j exit

exit:
    li $v0, 10
    syscall
