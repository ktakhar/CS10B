To remove a linked list's last node from the list, we must locate the list's 2nd-to-the-last node (the node that points to the last node) in order to set that 2nd-to-the-last node's link field to null.

FindNodeThatPointsToLastNode.java is a program class that creates linked lists with 0, 1, 2, and 3 nodes. For each of those linked lists, the program uses a method named KnownGoodLinkedList.get2ndToLastNode() to locate the list's 2nd-to-the-last list node.

Starting at line 47, FindNodeThatPointsToLastNode.java has an incomplete get2ndToLastNode() instance method that merely returns the value null, which is obviously not the correct implementation. The method includes comments that describe how it *should* work. See also this week's slides starting at slide 19.

You can also execute the FindNodeThatPointsToLastNodeTEACHER program class to see how the get2ndToLastNode() method should work.

We'll collaboratively finish the method in this week's section. Can you create working code before section?
