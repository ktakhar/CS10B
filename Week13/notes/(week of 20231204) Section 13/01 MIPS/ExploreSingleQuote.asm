## This program tests whether a single quote character in an ascii string is equal 
## to an escaped single quote character.

        .data

singleQuoteString:
        .asciiz "'"
    
singleQuoteChar:
        .byte   0x27

yesTheyAreEqualString:
        .asciiz "The single quote character is equal to the hex constant 0x27\n"
    
noTheyAreNotEqualString:
        .asciiz "The single quote character is NOT equal to the hex constant 0x27\n"
    
    
        .text
main:
        lb      $t0, singleQuoteString
        lb      $t1, singleQuoteChar
        beq     $t0, $t1, yesTheyAreEqual

        # They are not equal    
        la      $a0, noTheyAreNotEqualString
        li      $v0, 4
        syscall
        b       done

yesTheyAreEqual:
        # They are equal    
        la      $a0, yesTheyAreEqualString
        li      $v0, 4
        syscall

done:
        li      $v0, 10
        syscall
