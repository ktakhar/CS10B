// StringLinkedList.java
/** Unit 7
  * @author: Henry Leitner
  * @version:  Last modified on April 17, 2020
  */

public class StringLinkedList
{
    private ListNode head;

    public StringLinkedList ()
    {
        head = null;
    }

    /*****************************************
     *Returns the number of nodes in the list.
     *****************************************/
    public int length ()
    {
        int count = 0;
        ListNode position = head;
        while (position != null)
        {
            count++;
            position = position.getLink();
        }
        return count;
    }

    /*****************************************************************
     *Adds a node at the start of the list. The added node has addData
     *as its data. The added node will be the first node in the list.
     *****************************************************************/
    public void addANodeToStart (String addData)
    {
        head = new ListNode (addData, head);
    }

    public void deleteHeadNode ()
    {
        if (head != null)
        {
            head = head.getLink();
        }
        else
        {
            System.out.println ("Deleting from an empty list.");
            System.exit (0);
        }
    }

    public boolean onList (String target)
    {
        return (find (target) != null);
    }

    /***********************************************************************
     *Finds the first node containing the target data, and returns a
     *reference to that node. If target is not in the list, null is returned
     ***********************************************************************/
    private ListNode find (String target)
    {
        ListNode position = head;
        String dataAtPosition;
        while (position != null)
        {
            dataAtPosition = position.getData();
            if (dataAtPosition.equals (target)) return position;
            else position = position.getLink();
        }
        return null; 
    }

    public void showList()
    {
        ListNode position = head;

        while (position != null)
        {
            System.out.println(position.getData());
            position = position.getLink();
        }
    }

    public void mystery() throws LinkedListException
    {
        ListNode p = this.head;
        if (p == null) throw new LinkedListException(
                  "You cannot delete from an empty list!");
        if (p.getLink () == null) 
        {
            this.head = null;
            return;
        }
        while (p.getLink().getLink() != null)
        {
            p = p.getLink();
        }
        p.setLink (null);
     }

}

